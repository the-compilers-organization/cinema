package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import model.Filme;
import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import model.Critico;
import controller.IFachada;


// Tela "Avaliar filmes" (somente para Críticos)

public class CriticaView extends JPanel implements Atualizavel {

    private JComboBox<Filme> cbFilme = new JComboBox<Filme>();
    private JSpinner spNota = new JSpinner(new SpinnerNumberModel(8.0, 0.0, 10.0, 0.5));
    private JTextArea txtCritica = new JTextArea(8, 30);
    private JButton btnEnviar = Estilo.botao("Publicar avaliação", Estilo.PRIMARIA);

    private JLabel lblMedia = Estilo.subtitulo(" ");
    private JTextArea txtCriticas = new JTextArea();

    public CriticaView(IFachada fachada, Critico critico) {
        setLayout(new BorderLayout(16, 0));
        add(Estilo.cabecalho("Avaliar filmes",
                "Dê uma nota de 0 a 10 e escreva sua crítica. A média do filme é recalculada."),
                BorderLayout.NORTH);

        // Formulário
        JPanel formulario = Estilo.cartao();
        formulario.setLayout(new BorderLayout(0, 12));
        formulario.setPreferredSize(new Dimension(420, 10));

        JPanel campos = new JPanel(new BorderLayout(0, 12));
        campos.setOpaque(false);
        campos.add(Estilo.campo("Filme", cbFilme), BorderLayout.NORTH);
        spNota.setPreferredSize(new Dimension(90, 30));
        JPanel nota = new JPanel(new BorderLayout());
        nota.setOpaque(false);
        nota.add(Estilo.campo("Nota (0 a 10)", spNota), BorderLayout.WEST);
        campos.add(nota, BorderLayout.CENTER);
        formulario.add(campos, BorderLayout.NORTH);

        txtCritica.setLineWrap(true);
        txtCritica.setWrapStyleWord(true);
        formulario.add(Estilo.campo("Sua crítica", Estilo.rolagem(txtCritica)), BorderLayout.CENTER);
        formulario.add(btnEnviar, BorderLayout.SOUTH);
        add(formulario, BorderLayout.WEST);

        // Críticas já publicadas
        JPanel publicadas = Estilo.cartao();
        publicadas.setLayout(new BorderLayout(0, 10));
        publicadas.add(lblMedia, BorderLayout.NORTH);
        txtCriticas.setEditable(false);
        txtCriticas.setLineWrap(true);
        txtCriticas.setWrapStyleWord(true);
        publicadas.add(Estilo.campo("Críticas publicadas", Estilo.rolagem(txtCriticas)), BorderLayout.CENTER);
        add(publicadas, BorderLayout.CENTER);

        //  eventos da tela 
        this.fachada = fachada;
        this.critico = critico;

        getCbFilme().addActionListener(e -> mostrarCriticas());
        getBtnEnviar().addActionListener(e -> enviar());
    }

    public void mostrarCriticas(String media, String criticas) {
        lblMedia.setText(media);
        txtCriticas.setText(criticas);
        txtCriticas.setCaretPosition(0);
    }

    public void limparFormulario() {
        txtCritica.setText("");
        spNota.setValue(8.0);
    }

    public JComboBox<Filme> getCbFilme() {
        return cbFilme;
    }

    public double getNota() {
        return (Double) spNota.getValue();
    }

    public String getCritica() {
        return txtCritica.getText();
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    //  EVENTOS DA TELA - chamam a IFachada
    
    private IFachada fachada;
    private Critico critico;

    @Override
    public void atualizar() {
        Object selecionado = getCbFilme().getSelectedItem();
        getCbFilme().removeAllItems();
        for (Filme filme : fachada.listarFilmes()) {
            getCbFilme().addItem(filme);
        }
        if (selecionado != null) {
            getCbFilme().setSelectedItem(selecionado);
        }
        mostrarCriticas();
    }

    private void mostrarCriticas() {
        Filme filme = (Filme) getCbFilme().getSelectedItem();
        if (filme == null) {
            mostrarCriticas(" ", "");
            return;
        }
        String media = filme.getQuantidadeCriticos() == 0
                ? filme.getNome() + "  -  sem nota"
                : filme.getNome() + "  -  nota " + String.format("%.1f", filme.getNota())
                  + " (" + filme.getQuantidadeCriticos() + " crítico(s))";
        mostrarCriticas(media, FilmesView.textoDasCriticas(filme));
    }

    private void enviar() {
        Filme filme = (Filme) getCbFilme().getSelectedItem();
        try {
            fachada.avaliarFilme(critico, filme, getNota(), getCritica());
            Mensagens.sucesso(this, "Avaliação publicada! Nova média de \"" + filme.getNome() + "\": "
                    + String.format("%.1f", filme.getNota()));
            limparFormulario();
            mostrarCriticas();
        } catch (ValidacaoException e) {
            Mensagens.aviso(this, e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }
}
