package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import model.enums.Produtos;
import java.util.ArrayList;
import exceptions.CompraException;
import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import model.Bilhete;
import model.Compra;
import model.ItemCompra;
import model.Usuario;
import controller.IFachada;


// Tela "Minhas compras": o usuário pode ALTERAR (remover/adicionar itens) ou CANCELAR uma compra já realizada.

public class MinhasComprasView extends JPanel implements Atualizavel {

    private DefaultTableModel modeloCompras = new DefaultTableModel(
            new Object[]{"Nº", "Data", "Ingressos", "Produtos", "Cupom", "Total"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabelaCompras = new JTable(modeloCompras);

    private DefaultTableModel modeloItens = new DefaultTableModel(
            new Object[]{"Item", "Detalhe", "Valor"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabelaItens = new JTable(modeloItens);
    private JLabel lblCompra = Estilo.subtitulo("Itens da compra");

    private JComboBox<Produtos> cbProduto = new JComboBox<Produtos>(Produtos.values());
    private JSpinner spQtd = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
    private JButton btnAdicionarProduto = Estilo.botao("Adicionar à compra", Estilo.AZUL);
    private JButton btnRemoverItem = Estilo.botaoSecundario("Remover item selecionado");
    private JButton btnCancelarCompra = Estilo.botao("Cancelar compra", Estilo.PRIMARIA);

    public MinhasComprasView(IFachada fachada, Usuario usuario) {
        setLayout(new BorderLayout(0, 14));
        add(Estilo.cabecalho("Minhas compras",
                "Selecione uma compra para ver, alterar ou cancelar."), BorderLayout.NORTH);

        Estilo.estilizarTabela(tabelaCompras);
        Estilo.estilizarTabela(tabelaItens);
        tabelaItens.getColumnModel().getColumn(1).setPreferredWidth(320);

        JPanel centro = new JPanel(new GridLayout(2, 1, 0, 14));
        centro.setOpaque(false);
        centro.add(Estilo.rolagem(tabelaCompras));

        JPanel detalhes = Estilo.cartao();
        detalhes.setLayout(new BorderLayout(0, 8));
        JPanel topo = new JPanel(new BorderLayout());
        topo.setOpaque(false);
        topo.add(lblCompra, BorderLayout.WEST);
        topo.add(btnCancelarCompra, BorderLayout.EAST);
        detalhes.add(topo, BorderLayout.NORTH);
        detalhes.add(Estilo.rolagem(tabelaItens), BorderLayout.CENTER);

        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        acoes.setOpaque(false);
        acoes.add(btnRemoverItem);
        acoes.add(new JLabel("     Adicionar lanche:"));
        acoes.add(cbProduto);
        acoes.add(spQtd);
        acoes.add(btnAdicionarProduto);
        detalhes.add(acoes, BorderLayout.SOUTH);

        centro.add(detalhes);
        add(centro, BorderLayout.CENTER);

        //  eventos da tela 
        this.fachada = fachada;
        this.usuario = usuario;

        getTabelaCompras().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarItens();
            }
        });
        getBtnCancelarCompra().addActionListener(e -> cancelarCompra());
        getBtnRemoverItem().addActionListener(e -> removerItem());
        getBtnAdicionarProduto().addActionListener(e -> adicionarProduto());
    }

    public void setTituloCompra(String texto) {
        lblCompra.setText(texto);
    }

    public DefaultTableModel getModeloCompras() {
        return modeloCompras;
    }

    public JTable getTabelaCompras() {
        return tabelaCompras;
    }

    public DefaultTableModel getModeloItens() {
        return modeloItens;
    }

    public JTable getTabelaItens() {
        return tabelaItens;
    }

    public Produtos getProduto() {
        return (Produtos) cbProduto.getSelectedItem();
    }

    public int getQtd() {
        return (Integer) spQtd.getValue();
    }

    public JButton getBtnAdicionarProduto() {
        return btnAdicionarProduto;
    }

    public JButton getBtnRemoverItem() {
        return btnRemoverItem;
    }

    public JButton getBtnCancelarCompra() {
        return btnCancelarCompra;
    }

    //  EVENTOS DA TELA - chamam a IFachada
    private IFachada fachada;
    private Usuario usuario;
    private ArrayList<Compra> compras = new ArrayList<Compra>();

    @Override
    public void atualizar() {
        int selecionada = getTabelaCompras().getSelectedRow();
        compras = fachada.comprasDoUsuario(usuario.getCpf());
        getModeloCompras().setRowCount(0);

        for (Compra compra : compras) {
            getModeloCompras().addRow(new Object[]{
                    compra.getId(),
                    compra.getDataHora(),
                    compra.getBilhetes().size(),
                    compra.getProdutos().size(),
                    compra.getCupom() == null ? "-" : compra.getCupom().name(),
                    Estilo.dinheiro(compra.calcularTotal())
            });
        }

        if (compras.isEmpty()) {
            getModeloItens().setRowCount(0);
            setTituloCompra("Você ainda não fez nenhuma compra.");
        } else {
            if (selecionada < 0 || selecionada >= compras.size()) {
                selecionada = compras.size() - 1;
            }
            getTabelaCompras().setRowSelectionInterval(selecionada, selecionada);
            mostrarItens();
        }
    }

    private Compra compraSelecionada() {
        int linha = getTabelaCompras().getSelectedRow();
        if (linha < 0 || linha >= compras.size()) {
            return null;
        }
        return compras.get(linha);
    }

    private void mostrarItens() {
        Compra compra = compraSelecionada();
        getModeloItens().setRowCount(0);
        if (compra == null) {
            return;
        }
        setTituloCompra("Compra nº " + compra.getId() + "  -  " + Estilo.dinheiro(compra.calcularTotal()));

        for (Bilhete b : compra.getBilhetes()) {
            getModeloItens().addRow(new Object[]{
                    "Ingresso " + b.getNomeCadeira(),
                    "Sala " + b.getNumeroSala() + " (" + b.getTipoSala().getTipo() + ")  "
                            + b.getHorarioSessao() + "  -  " + b.getFilme(),
                    Estilo.dinheiro(b.getValor())
            });
        }
        for (ItemCompra item : compra.getProdutos()) {
            getModeloItens().addRow(new Object[]{
                    item.getProduto().getNome(),
                    item.getQuantidade() + " unidade(s)",
                    Estilo.dinheiro(item.getSubtotal())
            });
        }
    }

    private void cancelarCompra() {
        Compra compra = compraSelecionada();
        if (compra == null) {
            Mensagens.aviso(this, "Selecione uma compra.");
            return;
        }
        if (!Mensagens.confirmar(this, "Cancelar a compra nº " + compra.getId()
                + "? As poltronas serão liberadas.")) {
            return;
        }
        try {
            fachada.cancelarCompra(compra);
            Mensagens.sucesso(this, "Compra cancelada. O valor será estornado no seu cartão.");
            atualizar();
        } catch (CompraException e) {
            Mensagens.erro(this, e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }

    private void removerItem() {
        Compra compra = compraSelecionada();
        int linha = getTabelaItens().getSelectedRow();
        if (compra == null || linha < 0) {
            Mensagens.aviso(this, "Selecione um item da compra.");
            return;
        }
        try {
            fachada.removerItemDaCompra(compra, linha);
            atualizar();
        } catch (CompraException e) {
            Mensagens.erro(this, "Alteração não permitida", e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }

    private void adicionarProduto() {
        Compra compra = compraSelecionada();
        if (compra == null) {
            Mensagens.aviso(this, "Selecione uma compra.");
            return;
        }
        try {
            fachada.adicionarProdutoNaCompra(compra, getProduto(), getQtd());
            atualizar();
        } catch (ValidacaoException e) {
            Mensagens.aviso(this, e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }
}
