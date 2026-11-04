package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import model.Sala;
import model.Sessao;
import model.enums.CupomPromocional;
import model.enums.Produtos;
import java.util.ArrayList;
import exceptions.CompraException;
import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import exceptions.VendasException;
import model.Bilhete;
import model.Compra;
import model.ItemCompra;
import model.Usuario;
import controller.IFachada;


// Tela "Comprar ingressos"
// Fluxo: escolha da sala -> escolha da sessão -> poltronas -> lanchonete -> finalizar

public class CompraView extends JPanel implements Atualizavel {

    public static final String SEM_CUPOM = "Sem cupom";

    //  1. Sessão e poltronas 
    private JComboBox<Sala> cbSala = new JComboBox<Sala>();
    private JComboBox<Sessao> cbSessao = new JComboBox<Sessao>();
    private JLabel lblInfoSessao = Estilo.textoSuave(" ");
    private MapaCadeirasPanel mapa = new MapaCadeirasPanel();
    private JSpinner spQtdIngressos = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));
    private JButton btnSugerir = Estilo.botaoSecundario("Sugerir lugares juntos");
    private JButton btnLimparSelecao = Estilo.botaoSecundario("Limpar seleção");
    private JLabel lblSelecao = Estilo.textoSuave("Nenhuma poltrona selecionada.");
    private JButton btnAdicionarIngressos = Estilo.botao("Adicionar ingressos ao carrinho", Estilo.AZUL);

    //  2. Lanchonete e carrinho 
    private JComboBox<Produtos> cbProduto = new JComboBox<Produtos>(Produtos.values());
    private JSpinner spQtdProduto = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
    private JButton btnAdicionarProduto = Estilo.botao("Adicionar", Estilo.AZUL);
    private JComboBox<Object> cbCupom = new JComboBox<Object>();

    private DefaultTableModel modeloCarrinho = new DefaultTableModel(
            new Object[]{"Item", "Detalhe", "Valor"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabelaCarrinho = new JTable(modeloCarrinho);
    private JButton btnRemoverItem = Estilo.botaoSecundario("Remover item");
    private JButton btnEsvaziar = Estilo.botaoSecundario("Esvaziar");
    private JLabel lblIngressos = new JLabel("Ingressos: R$ 0,00");
    private JLabel lblProdutos = new JLabel("Lanchonete: R$ 0,00");
    private JLabel lblTotal = new JLabel("TOTAL  R$ 0,00");
    private JButton btnFinalizar = Estilo.botao("Finalizar compra", Estilo.VERDE);

    public CompraView(IFachada fachada, Usuario usuario) {
        setLayout(new BorderLayout(16, 0));
        add(Estilo.cabecalho("Comprar ingressos",
                "1) escolha a sala e a sessão   2) escolha as poltronas   3) adicione lanches   4) finalize"),
                BorderLayout.NORTH);
        add(montarPainelSessao(), BorderLayout.CENTER);
        add(montarPainelCarrinho(), BorderLayout.EAST);

        //  eventos da tela 
        this.fachada = fachada;
        this.usuario = usuario;
        this.carrinho = fachada.novaCompra(usuario);

        for (Sala sala : fachada.getSalas()) {
            getCbSala().addItem(sala);
        }

        getCbSala().addActionListener(e -> carregarSessoes());
        getCbSessao().addActionListener(e -> carregarMapa());
        getMapa().adicionarOuvinte(e -> mostrarSelecao());
        getBtnSugerir().addActionListener(e -> sugerirLugares());
        getBtnLimparSelecao().addActionListener(e -> getMapa().limparSelecao());
        getBtnAdicionarIngressos().addActionListener(e -> adicionarIngressos());
        getBtnAdicionarProduto().addActionListener(e -> adicionarProduto());
        getBtnRemoverItem().addActionListener(e -> removerItem());
        getBtnEsvaziar().addActionListener(e -> esvaziarCarrinho());
        getBtnFinalizar().addActionListener(e -> finalizar());

        carregarSessoes();
    }

    private JPanel montarPainelSessao() {
        JPanel cartao = Estilo.cartao();
        cartao.setLayout(new BorderLayout(0, 12));

        JPanel escolha = new JPanel(new GridLayout(1, 2, 12, 0));
        escolha.setOpaque(false);
        escolha.add(Estilo.campo("Sala", cbSala));
        escolha.add(Estilo.campo("Sessão", cbSessao));

        JPanel topo = new JPanel(new BorderLayout(0, 6));
        topo.setOpaque(false);
        topo.add(Estilo.subtitulo("Sessão e poltronas"), BorderLayout.NORTH);
        topo.add(escolha, BorderLayout.CENTER);
        topo.add(lblInfoSessao, BorderLayout.SOUTH);
        cartao.add(topo, BorderLayout.NORTH);

        cartao.add(mapa, BorderLayout.CENTER);

        JPanel grupo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        grupo.setOpaque(false);
        grupo.add(new JLabel("Quantas pessoas?"));
        grupo.add(spQtdIngressos);
        grupo.add(btnSugerir);
        grupo.add(btnLimparSelecao);

        JPanel rodape = new JPanel(new BorderLayout(0, 8));
        rodape.setOpaque(false);
        rodape.add(grupo, BorderLayout.NORTH);
        rodape.add(lblSelecao, BorderLayout.CENTER);
        rodape.add(btnAdicionarIngressos, BorderLayout.SOUTH);
        cartao.add(rodape, BorderLayout.SOUTH);
        return cartao;
    }

    private JPanel montarPainelCarrinho() {
        JPanel cartao = Estilo.cartao();
        cartao.setLayout(new BorderLayout(0, 12));
        cartao.setPreferredSize(new Dimension(380, 10));

        // Lanchonete
        JPanel lanchonete = new JPanel(new BorderLayout(0, 6));
        lanchonete.setOpaque(false);
        lanchonete.add(Estilo.subtitulo("Lanchonete"), BorderLayout.NORTH);
        JPanel linhaProduto = new JPanel(new BorderLayout(0, 6));
        linhaProduto.setOpaque(false);
        linhaProduto.add(cbProduto, BorderLayout.NORTH);
        JPanel qtd = new JPanel(new BorderLayout(8, 0));
        qtd.setOpaque(false);
        spQtdProduto.setPreferredSize(new Dimension(60, 30));
        JPanel rotuloQtd = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rotuloQtd.setOpaque(false);
        rotuloQtd.add(new JLabel("Quantidade  "));
        rotuloQtd.add(spQtdProduto);
        qtd.add(rotuloQtd, BorderLayout.WEST);
        qtd.add(btnAdicionarProduto, BorderLayout.CENTER);
        linhaProduto.add(qtd, BorderLayout.SOUTH);
        lanchonete.add(linhaProduto, BorderLayout.CENTER);

        cbCupom.addItem(SEM_CUPOM);
        for (CupomPromocional cupom : CupomPromocional.values()) {
            cbCupom.addItem(cupom);
        }
        cbCupom.setToolTipText("O cupom vale para a compra inteira e é aplicado a cada item adicionado.");
        lanchonete.add(Estilo.campo("Cupom promocional", cbCupom), BorderLayout.SOUTH);
        cartao.add(lanchonete, BorderLayout.NORTH);

        // Carrinho
        Estilo.estilizarTabela(tabelaCarrinho);
        tabelaCarrinho.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabelaCarrinho.getColumnModel().getColumn(1).setPreferredWidth(110);
        tabelaCarrinho.getColumnModel().getColumn(2).setPreferredWidth(70);
        JPanel carrinho = new JPanel(new BorderLayout(0, 6));
        carrinho.setOpaque(false);
        JPanel tituloCarrinho = new JPanel(new BorderLayout());
        tituloCarrinho.setOpaque(false);
        tituloCarrinho.add(Estilo.subtitulo("Carrinho"), BorderLayout.WEST);
        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        acoes.setOpaque(false);
        acoes.add(btnRemoverItem);
        acoes.add(btnEsvaziar);
        tituloCarrinho.add(acoes, BorderLayout.EAST);
        carrinho.add(tituloCarrinho, BorderLayout.NORTH);
        carrinho.add(Estilo.rolagem(tabelaCarrinho), BorderLayout.CENTER);
        cartao.add(carrinho, BorderLayout.CENTER);

        // Totais
        JPanel totais = new JPanel(new GridLayout(0, 1, 0, 2));
        totais.setOpaque(false);
        totais.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Estilo.BORDA),
                BorderFactory.createEmptyBorder(10, 0, 0, 0)));
        lblIngressos.setForeground(Estilo.TEXTO_SUAVE);
        lblProdutos.setForeground(Estilo.TEXTO_SUAVE);
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 20));
        totais.add(lblIngressos);
        totais.add(lblProdutos);
        totais.add(lblTotal);
        totais.add(btnFinalizar);
        cartao.add(totais, BorderLayout.SOUTH);
        return cartao;
    }

    public void mostrarTotais(double ingressos, double produtos, double total) {
        lblIngressos.setText("Ingressos: " + Estilo.dinheiro(ingressos));
        lblProdutos.setText("Lanchonete: " + Estilo.dinheiro(produtos));
        lblTotal.setText("TOTAL  " + Estilo.dinheiro(total));
    }

    //  getters usados pelo CompraController 
    public JComboBox<Sala> getCbSala() {
        return cbSala;
    }

    public JComboBox<Sessao> getCbSessao() {
        return cbSessao;
    }

    public JLabel getLblInfoSessao() {
        return lblInfoSessao;
    }

    public MapaCadeirasPanel getMapa() {
        return mapa;
    }

    public int getQtdIngressos() {
        return (Integer) spQtdIngressos.getValue();
    }

    public JButton getBtnSugerir() {
        return btnSugerir;
    }

    public JButton getBtnLimparSelecao() {
        return btnLimparSelecao;
    }

    public JLabel getLblSelecao() {
        return lblSelecao;
    }

    public JButton getBtnAdicionarIngressos() {
        return btnAdicionarIngressos;
    }

    public Produtos getProduto() {
        return (Produtos) cbProduto.getSelectedItem();
    }

    public int getQtdProduto() {
        return (Integer) spQtdProduto.getValue();
    }

    public JButton getBtnAdicionarProduto() {
        return btnAdicionarProduto;
    }

    public JComboBox<Object> getCbCupom() {
        return cbCupom;
    }

    // Retorna o cupom escolhido ou null para "Sem cupom"
    public CupomPromocional getCupom() {
        Object item = cbCupom.getSelectedItem();
        if (item instanceof CupomPromocional) {
            return (CupomPromocional) item;
        }
        return null;
    }

    public DefaultTableModel getModeloCarrinho() {
        return modeloCarrinho;
    }

    public JTable getTabelaCarrinho() {
        return tabelaCarrinho;
    }

    public JButton getBtnRemoverItem() {
        return btnRemoverItem;
    }

    public JButton getBtnEsvaziar() {
        return btnEsvaziar;
    }

    public JButton getBtnFinalizar() {
        return btnFinalizar;
    }

 
    //  EVENTOS DA TELA - chamam a IFachada
  
    private IFachada fachada;
    private Usuario usuario;
    private Compra carrinho;

    // Controle das sugestões de lugares juntos
    private ArrayList<int[]> sugestoes = new ArrayList<int[]>();
    private int proximaSugestao = 0;
    private int quantidadeSugerida = 0;

    @Override
    public void atualizar() {
        carregarMapa(); // outra compra pode ter ocupado/liberado poltronas
    }

    //  SALA, SESSÃO E MAPA 

    private Sala salaSelecionada() {
        return (Sala) getCbSala().getSelectedItem();
    }

    private Sessao sessaoSelecionada() {
        return (Sessao) getCbSessao().getSelectedItem();
    }

    private void carregarSessoes() {
        getCbSessao().removeAllItems();
        Sala sala = salaSelecionada();
        if (sala != null) {
            for (Sessao sessao : sala.getSessoesDisponiveis()) {
                getCbSessao().addItem(sessao);
            }
        }
        carregarMapa();
    }

    private void carregarMapa() {
        Sala sala = salaSelecionada();
        Sessao sessao = sessaoSelecionada();
        sugestoes.clear();

        if (sala == null || sessao == null) {
            getMapa().desabilitar();
            getLblInfoSessao().setText("Nenhuma sessão nesta sala.");
            return;
        }

        boolean[][] ocupadas = new boolean[Sessao.LINHAS][Sessao.COLUNAS];
        boolean[][] noCarrinho = new boolean[Sessao.LINHAS][Sessao.COLUNAS];
        for (int l = 0; l < Sessao.LINHAS; l++) {
            for (int c = 0; c < Sessao.COLUNAS; c++) {
                ocupadas[l][c] = !sessao.cadeiraDisponivel(l, c);
                noCarrinho[l][c] = carrinho.contemCadeira(sala.getNumero(), sessao.getHorario(), l, c);
            }
        }
        getMapa().carregar(ocupadas, noCarrinho);

        String info = sessao.getFilme().getNome() + "  |  " + sessao.getFilme().getDuracao() + " min  |  "
                + "Ingresso: " + Estilo.dinheiro(sala.calcularValorIngresso(sessao.getFilme()));
        if (usuario.getTipo().equals("Estudante")) {
            info += "  (você paga meia)";
        } else if (usuario.getTipo().equals("Crítico")) {
            info += "  (cortesia para crítico)";
        }
        getLblInfoSessao().setText(info);
    }

    private void mostrarSelecao() {
        ArrayList<int[]> selecionadas = getMapa().getSelecionadas();
        if (selecionadas.isEmpty()) {
            getLblSelecao().setText("Nenhuma poltrona selecionada. Clique em uma poltrona livre.");
            return;
        }
        String nomes = "";
        for (int[] cadeira : selecionadas) {
            nomes += Sessao.nomeDaCadeira(cadeira[0], cadeira[1]) + " ";
        }
        getLblSelecao().setText(selecionadas.size() + " poltrona(s) selecionada(s): " + nomes);
    }

    // Compra de vários bilhetes: o sistema sugere poltronas juntas
    private void sugerirLugares() {
        try {
            int quantidade = getQtdIngressos();

            // Se mudou a quantidade ou ainda não buscou, busca novamente
            if (sugestoes.isEmpty() || quantidade != quantidadeSugerida) {
                sugestoes = fachada.sugerirCadeirasJuntas(carrinho, salaSelecionada(),
                        sessaoSelecionada(), quantidade);
                quantidadeSugerida = quantidade;
                proximaSugestao = 0;
            }

            if (sugestoes.isEmpty()) {
                Mensagens.aviso(this, "Não há " + getQtdIngressos()
                        + " poltronas livres lado a lado nesta sessão. Escolha as poltronas manualmente.");
                return;
            }

            int[] sugestao = sugestoes.get(proximaSugestao % sugestoes.size());
            getMapa().selecionarGrupo(sugestao[0], sugestao[1], quantidade);
            getLblSelecao().setText("Sugestão " + (proximaSugestao % sugestoes.size() + 1) + " de "
                    + sugestoes.size() + ": fileira " + (char) ('A' + sugestao[0]) + ", poltronas "
                    + (sugestao[1] + 1) + " a " + (sugestao[1] + quantidade)
                    + ".  Clique de novo para outra opção.");
            proximaSugestao++;

        } catch (ValidacaoException e) {
            Mensagens.aviso(this, e.getMessage());
        }
    }

    //  CARRINHO 

    private void adicionarIngressos() {
        try {
            fachada.adicionarIngressos(carrinho, usuario, salaSelecionada(), sessaoSelecionada(),
                    getMapa().getSelecionadas(), getQtdIngressos(), getCupom());
            atualizarCarrinho();
            carregarMapa();

        } catch (VendasException e) {
            // Os 3 erros de venda pedidos na Parte 3
            Mensagens.erro(this, "Venda não permitida - " + e.getMotivo(), e.getMessage());
            carregarMapa();
        } catch (ValidacaoException e) {
            Mensagens.aviso(this, e.getMessage());
        }
    }

    private void adicionarProduto() {
        try {
            fachada.adicionarProduto(carrinho, getProduto(), getQtdProduto(), getCupom());
            atualizarCarrinho();
        } catch (ValidacaoException e) {
            Mensagens.aviso(this, e.getMessage());
        }
    }

    private void removerItem() {
        int linha = getTabelaCarrinho().getSelectedRow();
        if (linha < 0) {
            Mensagens.aviso(this, "Selecione um item do carrinho.");
            return;
        }
        try {
            fachada.removerItemDoCarrinho(carrinho, linha);
            atualizarCarrinho();
            carregarMapa();
        } catch (CompraException e) {
            Mensagens.erro(this, e.getMessage());
        }
    }

    private void esvaziarCarrinho() {
        if (!carrinho.estaVazia() && !Mensagens.confirmar(this, "Remover todos os itens do carrinho?")) {
            return;
        }
        carrinho = fachada.novaCompra(usuario);
        atualizarCarrinho();
        carregarMapa();
    }

    private void atualizarCarrinho() {
        getModeloCarrinho().setRowCount(0);

        for (Bilhete b : carrinho.getBilhetes()) {
            getModeloCarrinho().addRow(new Object[]{
                    "Ingresso " + b.getNomeCadeira() + " - " + b.getFilme(),
                    "Sala " + b.getNumeroSala() + ", " + b.getHorarioSessao(),
                    Estilo.dinheiro(b.getValor())
            });
        }
        for (ItemCompra item : carrinho.getProdutos()) {
            getModeloCarrinho().addRow(new Object[]{
                    item.getProduto().getNome(),
                    item.getQuantidade() + " un.",
                    Estilo.dinheiro(item.getSubtotal())
            });
        }

        mostrarTotais(carrinho.calcularTotalBilhetes(), carrinho.calcularTotalProdutos(),
                carrinho.calcularTotal());

        // Depois do primeiro item o cupom fica travado (vale para a compra toda)
        getCbCupom().setEnabled(carrinho.estaVazia());
    }

    //  FINALIZAR (REALIZAR COMPRA) 

    private void finalizar() {
        if (carrinho.getBilhetes().isEmpty()) {
            Mensagens.aviso(this, "Adicione pelo menos um ingresso ao carrinho.");
            return;
        }
        String resumo = carrinho.getBilhetes().size() + " ingresso(s) e " + carrinho.getProdutos().size()
                + " produto(s).\nTotal: " + Estilo.dinheiro(carrinho.calcularTotal())
                + "\nCartão final " + finalDoCartao() + "\n\nConfirmar a compra?";
        if (!Mensagens.confirmar(this, resumo)) {
            return;
        }

        try {
            fachada.finalizarCompra(carrinho);
            Mensagens.sucesso(this, "Compra nº " + carrinho.getId() + " realizada com sucesso!\n"
                    + "Veja seus ingressos em \"Minhas compras\".");
            carrinho = fachada.novaCompra(usuario);
            atualizarCarrinho();
            carregarMapa();

        } catch (VendasException e) {
            Mensagens.erro(this, "Venda não permitida - " + e.getMotivo(), e.getMessage());
        } catch (CompraException e) {
            Mensagens.erro(this, e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }

    private String finalDoCartao() {
        String numero = usuario.getNumeroCartao();
        return numero.substring(numero.length() - 4);
    }
}
