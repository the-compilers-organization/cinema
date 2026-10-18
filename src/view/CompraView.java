package view;

import enums.Produtos;
import enums.CupomPromocional;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;
import java.util.function.IntConsumer;
import java.awt.*;

public class CompraView extends JFrame {
    private final JTabbedPane abas = new JTabbedPane();
    private final JComboBox<String> cbCompras = new JComboBox<>();
    private final JComboBox<String> cbSessao = new JComboBox<>();
    private final JSpinner spQtdBilhetes = new JSpinner(new SpinnerNumberModel(0, 0, 15, 1));
    private final JButton btnEscolherCadeiras = new JButton("Escolher cadeiras");
    private final JButton btnExcluirCadeiras = new JButton("Excluir cadeiras");
    private final JComboBox<Produtos> cbProduto = new JComboBox<>(Produtos.values());
    private final JSpinner spQtdProduto = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
    private final JButton btnAdicionarProduto = new JButton("Adicionar produto");
    private final JComboBox<String> cbCupom = new JComboBox<>();
    private final JLabel lblCadeira = new JLabel("Nenhuma cadeira selecionada");
    private final JLabel lblBilhetes = new JLabel("Ingressos: R$ 0,00");
    private final JLabel lblProdutos = new JLabel("Produtos: R$ 0,00");
    private final JLabel lblTotal = new JLabel("TOTAL: R$ 0,00");
    private final JLabel lblEstado = new JLabel("NOVA COMPRA");
    private final DefaultTableModel modeloProdutos = new DefaultTableModel(new Object[]{"Produto", "Quantidade", "Subtotal", "Acoes"}, 0) {
        @Override public boolean isCellEditable(int linha, int coluna) { return coluna == 3; }
    };
    private final JTable tabelaProdutos = new JTable(modeloProdutos);
    private final DefaultTableModel modeloIngressos = new DefaultTableModel(
            new Object[]{"Ingresso", "Cadeira", "Valor", "Acoes"}, 0) {
        @Override public boolean isCellEditable(int linha, int coluna) { return coluna == 3; }
    };
    private final JTable tabelaIngressos = new JTable(modeloIngressos);
    private final JTextArea txtResumo = new JTextArea(4, 24);
    private final JTextArea txtDetalhesCompra = new JTextArea(11, 35);
    private final JButton btnNovaCompra = new JButton("Limpar formulario");
    private final JButton btnCarregarCompra = new JButton("Editar compra selecionada");
    private final JButton btnFinalizar = new JButton("Confirmar compra");
    private final JButton btnCancelar = new JButton("Cancelar compra selecionada");
    private final JButton btnVoltar = new JButton("Voltar ao menu");

    public CompraView() {
        setTitle("Central de compras - Cinema");
        setSize(950, 750);
        setMinimumSize(new Dimension(760, 600));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        cbCupom.addItem("Desconto do perfil (se houver)");
        cbCupom.addItem("Sem desconto");
        for (CupomPromocional cupom : CupomPromocional.values()) cbCupom.addItem(cupom.name());
        cbProduto.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> lista, Object valor, int indice, boolean selecionado, boolean foco) {
                super.getListCellRendererComponent(lista, valor, indice, selecionado, foco);
                if (valor instanceof Produtos) {
                    Produtos p = (Produtos) valor;
                    setText(String.format("%s - R$ %.2f", p.name(), p.getValor()));
                }
                return this;
            }
        });

        JPanel nova = new JPanel();
        nova.setLayout(new BoxLayout(nova, BoxLayout.Y_AXIS));
        nova.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        nova.add(lblEstado);
        nova.add(Box.createVerticalStrut(10));

        JPanel ingressos = secao("1. Ingressos");
        ingressos.add(campo("Filme / sessao", cbSessao));
        JPanel linhaIngressos = new JPanel(new GridLayout(1, 2, 12, 0));
        linhaIngressos.add(campo("Quantidade de ingressos", spQtdBilhetes));
        JPanel painelSelecao = new JPanel(new GridLayout(1, 2, 8, 0));
        painelSelecao.add(btnEscolherCadeiras);
        painelSelecao.add(btnExcluirCadeiras);
        linhaIngressos.add(campo("Selecao", painelSelecao));
        ingressos.add(linhaIngressos);
        ingressos.add(lblCadeira);
        nova.add(ingressos);
        nova.add(Box.createVerticalStrut(12));

        JPanel bomboniere = secao("2. Bomboniere");
        JPanel linhaProduto = new JPanel(new GridLayout(1, 3, 10, 0));
        linhaProduto.add(campo("Produto", cbProduto));
        linhaProduto.add(campo("Quantidade", spQtdProduto));
        linhaProduto.add(campo("Adicionar", btnAdicionarProduto));
        bomboniere.add(linhaProduto);
        tabelaProdutos.setRowHeight(25);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaProdutos.setFillsViewportHeight(true);
        JScrollPane rolagemTabela = new JScrollPane(tabelaProdutos);
        rolagemTabela.setPreferredSize(new Dimension(550, 110));
        bomboniere.add(rolagemTabela);
        nova.add(bomboniere);
        nova.add(Box.createVerticalStrut(12));

        JPanel resumo = secao("3. Resumo e pagamento");
        resumo.add(campo("Escolha somente um desconto", cbCupom));
        txtResumo.setEditable(false);
        txtResumo.setLineWrap(true);
        txtResumo.setWrapStyleWord(true);
        txtResumo.setBackground(UIManager.getColor("Panel.background"));
        JScrollPane rolagemResumo = new JScrollPane(txtResumo);
        rolagemResumo.setPreferredSize(new Dimension(550, 80));
        resumo.add(rolagemResumo);
        JPanel valores = new JPanel(new GridLayout(3, 1, 3, 3));
        valores.add(lblBilhetes);
        valores.add(lblProdutos);
        lblTotal.setFont(lblTotal.getFont().deriveFont(Font.BOLD, 17f));
        valores.add(lblTotal);
        resumo.add(valores);
        nova.add(resumo);
        nova.add(Box.createVerticalStrut(12));

        JPanel acoesNova = new JPanel(new GridLayout(1, 2, 12, 0));
        acoesNova.add(btnNovaCompra);
        acoesNova.add(btnFinalizar);
        nova.add(acoesNova);
        nova.add(Box.createVerticalGlue());

        JPanel minhas = new JPanel(new BorderLayout(12, 12));
        minhas.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        JPanel topoMinhas = new JPanel(new BorderLayout(5, 5));
        topoMinhas.add(new JLabel("Compra registrada:"), BorderLayout.NORTH);
        topoMinhas.add(cbCompras, BorderLayout.CENTER);
        minhas.add(topoMinhas, BorderLayout.NORTH);
        txtDetalhesCompra.setEditable(false);
        txtDetalhesCompra.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        txtDetalhesCompra.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        minhas.add(new JScrollPane(txtDetalhesCompra), BorderLayout.CENTER);
        JPanel acoesMinhas = new JPanel(new GridLayout(1, 2, 12, 0));
        acoesMinhas.add(btnCarregarCompra);
        acoesMinhas.add(btnCancelar);
        minhas.add(acoesMinhas, BorderLayout.SOUTH);

        abas.addTab("Nova compra", new JScrollPane(nova));
        abas.addTab("Minhas compras", minhas);
        add(abas, BorderLayout.CENTER);
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.add(btnVoltar);
        add(rodape, BorderLayout.SOUTH);

        Color rosinha = Color.decode("#edc0df");
        for (JButton b : new JButton[]{btnNovaCompra, btnCarregarCompra, btnEscolherCadeiras, btnExcluirCadeiras,
                btnAdicionarProduto, btnFinalizar, btnCancelar, btnVoltar}) {
            b.setBackground(rosinha);
            b.setForeground(Color.BLACK);
            b.setFocusPainted(false);
        }
    }

    private JPanel secao(String titulo) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(titulo),
                BorderFactory.createEmptyBorder(8, 10, 10, 10)));
        painel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return painel;
    }

    private JPanel campo(String rotulo, JComponent componente) {
        JPanel painel = new JPanel(new BorderLayout(3, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(3, 0, 6, 0));
        painel.add(new JLabel(rotulo), BorderLayout.NORTH);
        painel.add(componente, BorderLayout.CENTER);
        return painel;
    }


    // A View apenas informa qual linha e qual acao foram acionadas.
    // As regras de alteracao e exclusao continuam no Controller.
    public void configurarAcoesProdutos(IntConsumer editar, IntConsumer excluir) {
        tabelaProdutos.getColumnModel().getColumn(3).setCellRenderer(new RenderizadorAcoes());
        tabelaProdutos.getColumnModel().getColumn(3).setCellEditor(new EditorAcoes(editar, excluir));
        tabelaProdutos.getColumnModel().getColumn(3).setPreferredWidth(210);
        tabelaProdutos.setRowHeight(36);
    }

    private JPanel painelAcoes(JButton editar, JButton excluir) {
        JPanel painel = new JPanel(new GridLayout(1, 2, 6, 0));
        painel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        for (JButton botao : new JButton[]{editar, excluir}) {
            botao.setBackground(Color.decode("#edc0df"));
            botao.setForeground(Color.BLACK);
            botao.setFocusPainted(false);
            botao.setMargin(new Insets(2, 4, 2, 4));
        }
        painel.add(editar);
        painel.add(excluir);
        return painel;
    }

    private class RenderizadorAcoes implements TableCellRenderer {
        private final JPanel painel = painelAcoes(new JButton("Editar"), new JButton("Excluir"));
        @Override
        public Component getTableCellRendererComponent(JTable tabela, Object valor,
                boolean selecionada, boolean foco, int linha, int coluna) {
            painel.setBackground(selecionada ? tabela.getSelectionBackground() : tabela.getBackground());
            return painel;
        }
    }

    private class EditorAcoes extends AbstractCellEditor implements TableCellEditor {
        private final JPanel painel;
        private int linhaAtual = -1;

        EditorAcoes(IntConsumer editar, IntConsumer excluir) {
            JButton btnEditar = new JButton("Editar");
            JButton btnExcluir = new JButton("Excluir");
            painel = painelAcoes(btnEditar, btnExcluir);
            btnEditar.addActionListener(e -> executar(editar));
            btnExcluir.addActionListener(e -> executar(excluir));
        }

        private void executar(IntConsumer acao) {
            int linha = linhaAtual;
            fireEditingStopped();
            if (linha >= 0) SwingUtilities.invokeLater(() -> acao.accept(linha));
        }

        @Override
        public Component getTableCellEditorComponent(JTable tabela, Object valor,
                boolean selecionada, int linha, int coluna) {
            linhaAtual = tabela.convertRowIndexToModel(linha);
            return painel;
        }

        @Override
        public Object getCellEditorValue() { return ""; }
    }

    public void configurarAcoesIngressos(IntConsumer editar, IntConsumer excluir) {
        tabelaIngressos.getColumnModel().getColumn(3).setCellRenderer(new RenderizadorAcoes());
        tabelaIngressos.getColumnModel().getColumn(3).setCellEditor(new EditorAcoes(editar, excluir));
        tabelaIngressos.getColumnModel().getColumn(3).setPreferredWidth(210);
    }
    public JTable getTabelaIngressos() { return tabelaIngressos; }
    public DefaultTableModel getModeloIngressos() { return modeloIngressos; }
    public JTabbedPane getAbas() { return abas; }
    public JComboBox<String> getCbCompras() { return cbCompras; }
    public JComboBox<String> getCbSessao() { return cbSessao; }
    public JSpinner getSpQtdBilhetes() { return spQtdBilhetes; }
    public JButton getBtnEscolherCadeiras() { return btnEscolherCadeiras; }
    public JButton getBtnExcluirCadeiras() { return btnExcluirCadeiras; }
    public JComboBox<Produtos> getCbProduto() { return cbProduto; }
    public JSpinner getSpQtdProduto() { return spQtdProduto; }
    public JButton getBtnAdicionarProduto() { return btnAdicionarProduto; }
    public JComboBox<String> getCbCupom() { return cbCupom; }
    public JLabel getLblCadeira() { return lblCadeira; }
    public JLabel getLblProdutos() { return lblProdutos; }
    public JLabel getLblBilhetes() { return lblBilhetes; }
    public JLabel getLblTotal() { return lblTotal; }
    public JLabel getLblEstado() { return lblEstado; }
    public DefaultTableModel getModeloProdutos() { return modeloProdutos; }
    public JTable getTabelaProdutos() { return tabelaProdutos; }
    public JTextArea getTxtResumo() { return txtResumo; }
    public JTextArea getTxtDetalhesCompra() { return txtDetalhesCompra; }
    public JButton getBtnNovaCompra() { return btnNovaCompra; }
    public JButton getBtnCarregarCompra() { return btnCarregarCompra; }
    public JButton getBtnFinalizar() { return btnFinalizar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnVoltar() { return btnVoltar; }
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CompraView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir a interface visual utilizada durante a compra.
 *
 *
 * COMPONENTES:
 *
 * Criar componentes para:
 *
 * - escolher Sessao;
 * - informar quantidade de bilhetes;
 * - escolher cadeiras;
 * - escolher Produto;
 * - quantidade do Produto;
 * - adicionar Produto;
 * - escolher CupomPromocional;
 * - apresentar cadeira selecionada;
 * - apresentar total dos produtos;
 * - apresentar total da compra;
 * - finalizar;
 * - voltar.
 *
 *
 * A View deve construir os componentes e disponibiliza-los
 * atraves de getters.
 *
 *
 * Exemplos:
 *
 * getCbSessao()
 * getSpQtdBilhetes()
 * getBtnEscolherCadeiras()
 * getCbProduto()
 * getSpQtdProduto()
 * getBtnAdicionarProduto()
 * getCbCupom()
 * getLblCadeira()
 * getLblProdutos()
 * getLblTotal()
 * getBtnFinalizar()
 * getBtnVoltar()
 *
 *
 * NAO devem permanecer na View:
 *
 * carregarSessoes()
 * buscarSessaoSelecionada()
 * abrirCadeiras()
 * adicionarProduto()
 * calcularValorBilhete()
 * atualizarTotal()
 * finalizar()
 * voltar()
 *
 * Essas operacoes passam para CompraController.
 *
 *
 * CompraView tambem nao deve armazenar:
 *
 * Sala selecionada
 * Sessao selecionada
 * totalProdutos
 * linhaSelecionada
 * colunaSelecionada
 *
 * Esse estado pertence ao CompraController nesta arquitetura.
 *
 * ============================================================
 */