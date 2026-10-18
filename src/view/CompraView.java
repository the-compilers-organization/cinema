package view;

import enums.Produtos;
import enums.CupomPromocional;

import javax.swing.*;
import java.awt.*;

public class CompraView extends JFrame {

    private JComboBox<String> cbSessao = new JComboBox<>();

    private JSpinner spQtdBilhetes = new JSpinner(
            new SpinnerNumberModel(1, 1, 15, 1)
    );

    private JButton btnEscolherCadeiras = new JButton("Escolher cadeiras");

    private JComboBox<Produtos> cbProduto = new JComboBox<>(
            Produtos.values()
    );

    private JSpinner spQtdProduto = new JSpinner(
            new SpinnerNumberModel(1, 1, 20, 1)
    );

    private JButton btnAdicionarProduto = new JButton("Adicionar produto");

    private JComboBox<String> cbCupom = new JComboBox<>();

    private JLabel lblCadeira = new JLabel("Cadeira: nenhuma");

    private JLabel lblProdutos = new JLabel("Produtos: R$ 0,00");

    private JLabel lblBilhetes = new JLabel("Bilhetes: R$ 0,00");

    private JLabel lblTotal = new JLabel("TOTAL: R$ 0,00");

    private JButton btnFinalizar = new JButton("Finalizar");

    private JButton btnVoltar = new JButton("Voltar");

    public CompraView() {

        setTitle("Realizar Compra");

        setSize(650, 650);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);

        cbCupom.addItem("Desconto do perfil (se houver)");

        cbCupom.addItem("Sem desconto");

        for (CupomPromocional cupom : CupomPromocional.values()) {

            cbCupom.addItem(cupom.name());
        }

        JPanel painel = new JPanel(
                new GridLayout(11, 2, 10, 10)
        );

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        painel.add(new JLabel("Sessao:"));
        painel.add(cbSessao);

        painel.add(new JLabel("Quantidade de bilhetes:"));
        painel.add(spQtdBilhetes);

        painel.add(new JLabel("Cadeira:"));
        painel.add(btnEscolherCadeiras);

        painel.add(new JLabel("Produto:"));
        painel.add(cbProduto);

        painel.add(new JLabel("Quantidade do produto:"));
        painel.add(spQtdProduto);

        painel.add(new JLabel("Adicionar:"));
        painel.add(btnAdicionarProduto);

        painel.add(new JLabel("Escolha UM desconto:"));
        painel.add(cbCupom);

        painel.add(new JLabel("Selecao:"));
        painel.add(lblCadeira);

        painel.add(new JLabel("Subtotal:"));
        painel.add(lblBilhetes);

        painel.add(new JLabel("Produtos:"));
        painel.add(lblProdutos);

        painel.add(new JLabel("Total:"));
        painel.add(lblTotal);

        painel.add(btnVoltar);
        painel.add(btnFinalizar);

        add(painel);

        // Configurar cores dos botoes
        Color rosinha = Color.decode("#edc0df");

        btnEscolherCadeiras.setBackground(rosinha);
        btnAdicionarProduto.setBackground(rosinha);
        btnFinalizar.setBackground(rosinha);
        btnVoltar.setBackground(rosinha);

        btnEscolherCadeiras.setForeground(Color.BLACK);
        btnAdicionarProduto.setForeground(Color.BLACK);
        btnFinalizar.setForeground(Color.BLACK);
        btnVoltar.setForeground(Color.BLACK);
    }

    public JComboBox<String> getCbSessao() {

        return cbSessao;
    }

    public JSpinner getSpQtdBilhetes() {

        return spQtdBilhetes;
    }

    public JButton getBtnEscolherCadeiras() {

        return btnEscolherCadeiras;
    }

    public JComboBox<Produtos> getCbProduto() {

        return cbProduto;
    }

    public JSpinner getSpQtdProduto() {

        return spQtdProduto;
    }

    public JButton getBtnAdicionarProduto() {

        return btnAdicionarProduto;
    }

    public JComboBox<String> getCbCupom() {

        return cbCupom;
    }

    public JLabel getLblCadeira() {

        return lblCadeira;
    }

    public JLabel getLblProdutos() {

        return lblProdutos;
    }

    public JLabel getLblBilhetes() {

        return lblBilhetes;
    }

    public JLabel getLblTotal() {

        return lblTotal;
    }

    public JButton getBtnFinalizar() {

        return btnFinalizar;
    }

    public JButton getBtnVoltar() {

        return btnVoltar;
    }
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