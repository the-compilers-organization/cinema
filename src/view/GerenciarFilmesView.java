package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import exceptions.FilmeException;
import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import model.Filme;
import model.interfaces.GerenciaFilmes;
import controller.IFachada;


// Tela da equipe: incluir, alterar e excluir filmes (interface GerenciaFilmes)

public class GerenciarFilmesView extends JPanel implements Atualizavel {

    private DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Filme", "Duração", "Valor", "Em cartaz"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabela = new JTable(modelo);

    private JLabel lblModo = Estilo.subtitulo("Novo filme");
    private JTextField txtNome = new JTextField();
    private JTextField txtDuracao = new JTextField();
    private JTextField txtValor = new JTextField();
    private JTextArea txtSinopse = new JTextArea(5, 20);
    private JCheckBox chkEmCartaz = new JCheckBox("Em cartaz (disponível para venda)", true);

    private JButton btnNovo = Estilo.botaoSecundario("Novo");
    private JButton btnSalvar = Estilo.botao("Salvar", Estilo.VERDE);
    private JButton btnExcluir = Estilo.botao("Excluir", Estilo.PRIMARIA);

    public GerenciarFilmesView(IFachada fachada, GerenciaFilmes gerente) {
        setLayout(new BorderLayout(16, 0));
        add(Estilo.cabecalho("Gerenciar filmes",
                "Selecione um filme na tabela para alterar ou excluir, ou clique em Novo."), BorderLayout.NORTH);

        Estilo.estilizarTabela(tabela);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(280);
        add(Estilo.rolagem(tabela), BorderLayout.CENTER);

        JPanel formulario = Estilo.cartao();
        formulario.setLayout(new BorderLayout(0, 12));
        formulario.setPreferredSize(new Dimension(360, 10));
        formulario.add(lblModo, BorderLayout.NORTH);

        Estilo.estilizarCampo(txtNome);
        Estilo.estilizarCampo(txtDuracao);
        Estilo.estilizarCampo(txtValor);
        txtSinopse.setLineWrap(true);
        txtSinopse.setWrapStyleWord(true);
        chkEmCartaz.setOpaque(false);

        JPanel campos = new JPanel(new BorderLayout(0, 10));
        campos.setOpaque(false);
        JPanel linhas = new JPanel(new GridLayout(0, 1, 0, 10));
        linhas.setOpaque(false);
        linhas.add(Estilo.campo("Nome", txtNome));
        JPanel numeros = new JPanel(new GridLayout(1, 2, 10, 0));
        numeros.setOpaque(false);
        numeros.add(Estilo.campo("Duração (min)", txtDuracao));
        numeros.add(Estilo.campo("Valor base (R$)", txtValor));
        linhas.add(numeros);
        campos.add(linhas, BorderLayout.NORTH);
        campos.add(Estilo.campo("Sinopse", Estilo.rolagem(txtSinopse)), BorderLayout.CENTER);
        campos.add(chkEmCartaz, BorderLayout.SOUTH);
        formulario.add(campos, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new GridLayout(1, 3, 8, 0));
        botoes.setOpaque(false);
        botoes.add(btnNovo);
        botoes.add(btnExcluir);
        botoes.add(btnSalvar);
        formulario.add(botoes, BorderLayout.SOUTH);
        add(formulario, BorderLayout.EAST);

        //  eventos da tela 
        this.fachada = fachada;
        this.gerente = gerente;

        getTabela().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherFormulario();
            }
        });
        getBtnNovo().addActionListener(e -> limpar());
        getBtnSalvar().addActionListener(e -> salvar());
        getBtnExcluir().addActionListener(e -> excluir());
        limpar();
    }

    public void preencher(String nome, int duracao, double valor, String sinopse, boolean emCartaz) {
        lblModo.setText("Alterar filme");
        txtNome.setText(nome);
        txtNome.setEnabled(false); // o nome identifica o filme
        txtDuracao.setText(String.valueOf(duracao));
        txtValor.setText(String.format("%.2f", valor));
        txtSinopse.setText(sinopse);
        txtSinopse.setCaretPosition(0);
        chkEmCartaz.setSelected(emCartaz);
        btnExcluir.setEnabled(true);
    }

    public void limpar() {
        lblModo.setText("Novo filme");
        txtNome.setText("");
        txtNome.setEnabled(true);
        txtDuracao.setText("");
        txtValor.setText("");
        txtSinopse.setText("");
        chkEmCartaz.setSelected(true);
        btnExcluir.setEnabled(false);
        tabela.clearSelection();
    }

    public boolean isModoEdicao() {
        return !txtNome.isEnabled();
    }

    public String getNome() {
        return txtNome.getText();
    }

    public String getDuracao() {
        return txtDuracao.getText().trim();
    }

    public String getValor() {
        return txtValor.getText().trim();
    }

    public String getSinopse() {
        return txtSinopse.getText();
    }

    public boolean isEmCartaz() {
        return chkEmCartaz.isSelected();
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTable getTabela() {
        return tabela;
    }

    public JButton getBtnNovo() {
        return btnNovo;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }


    //  EVENTOS DA TELA - chamam a IFachada
    private IFachada fachada;
    private GerenciaFilmes gerente;
    private ArrayList<Filme> filmes = new ArrayList<Filme>();

    @Override
    public void atualizar() {
        filmes = fachada.listarFilmes();
        getModelo().setRowCount(0);
        for (Filme filme : filmes) {
            getModelo().addRow(new Object[]{
                    filme.getNome(),
                    filme.getDuracao() + " min",
                    Estilo.dinheiro(filme.getValor()),
                    filme.isEmCartaz() ? "Sim" : "Não"
            });
        }
        limpar();
    }

    private void preencherFormulario() {
        int linha = getTabela().getSelectedRow();
        if (linha < 0 || linha >= filmes.size()) {
            return;
        }
        Filme f = filmes.get(linha);
        preencher(f.getNome(), f.getDuracao(), f.getValor(), f.getSinopse(), f.isEmCartaz());
    }

    private void salvar() {
        try {
            int duracao = Integer.parseInt(getDuracao());
            double valor = Double.parseDouble(getValor().replace(",", "."));

            // O construtor de Filme valida os dados (ValidacaoException)
            Filme filme = new Filme(getNome(), duracao, getSinopse(), valor);
            filme.setEmCartaz(isEmCartaz());

            if (isModoEdicao()) {
                fachada.alterarFilme(gerente, filme);
                Mensagens.sucesso(this, "Filme alterado com sucesso!");
            } else {
                fachada.incluirFilme(gerente, filme);
                Mensagens.sucesso(this, "Filme incluído! Ele entrará na programação "
                        + "quando o sistema for iniciado novamente.");
            }
            atualizar();

        } catch (NumberFormatException e) {
            Mensagens.erro(this, "Duração e valor devem ser números. Ex.: 120 e 25,00");
        } catch (ValidacaoException e) {
            Mensagens.erro(this, "Dados inválidos", e.getMessage());
        } catch (FilmeException e) {
            Mensagens.erro(this, "Operação não permitida", e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }

    private void excluir() {
        String nome = getNome();
        if (!isModoEdicao()) {
            Mensagens.aviso(this, "Selecione um filme na tabela.");
            return;
        }
        if (!Mensagens.confirmar(this, "Excluir o filme \"" + nome + "\"?")) {
            return;
        }
        try {
            fachada.excluirFilme(gerente, nome);
            Mensagens.sucesso(this, "Filme excluído.");
            atualizar();
        } catch (FilmeException e) {
            Mensagens.erro(this, "Operação não permitida", e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }
}
