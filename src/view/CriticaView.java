package view;

import javax.swing.*;
import java.awt.*;

public class CriticaView extends JFrame {

    private JComboBox<String> cbFilmes = new JComboBox<>();

    private JSpinner spNota = new JSpinner(
            new SpinnerNumberModel(0.0, 0.0, 10.0, 0.5)
    );

    private JTextArea txtCritica = new JTextArea(8, 30);

    private JButton btnEnviar = new JButton("Enviar Avaliacao");

    private JButton btnVoltar = new JButton("Voltar");

    public CriticaView() {

        setTitle("Avaliar Filme");

        setSize(550, 450);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);

        txtCritica.setLineWrap(true);

        txtCritica.setWrapStyleWord(true);

        JPanel superior = new JPanel(
                new GridLayout(2, 2, 10, 10)
        );

        superior.add(new JLabel("Filme:"));
        superior.add(cbFilmes);

        superior.add(new JLabel("Nota:"));
        superior.add(spNota);

        JPanel inferior = new JPanel();

        inferior.add(btnVoltar);
        inferior.add(btnEnviar);

        add(superior, BorderLayout.NORTH);

        add(
                new JScrollPane(txtCritica),
                BorderLayout.CENTER
        );

        add(inferior, BorderLayout.SOUTH);


        // Configurar cores dos botoes
        Color rosinha = Color.decode("#edc0df");

        btnEnviar.setBackground(rosinha);
        btnVoltar.setBackground(rosinha);

        btnEnviar.setForeground(Color.BLACK);
        btnVoltar.setForeground(Color.BLACK);
    }

    public JComboBox<String> getCbFilmes() {

        return cbFilmes;
    }

    public JSpinner getSpNota() {

        return spNota;
    }

    public JTextArea getTxtCritica() {

        return txtCritica;
    }

    public JButton getBtnEnviar() {

        return btnEnviar;
    }

    public JButton getBtnVoltar() {

        return btnVoltar;
    }
}



/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CriticaView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir a interface visual utilizada para avaliar um
 * Filme.
 *
 *
 * Criar:
 *
 * JComboBox<String> para os filmes;
 *
 * JSpinner para nota;
 *
 * JTextArea para critica;
 *
 * JButton Enviar;
 *
 * JButton Voltar.
 *
 *
 * NOTA:
 *
 * Utilizar SpinnerNumberModel permitindo valores:
 *
 * minimo: 0.0
 * maximo: 10.0
 * passo: 0.5
 *
 *
 * JTextArea:
 *
 * habilitar quebra de linha e quebra por palavras.
 *
 * Colocar dentro de JScrollPane.
 *
 *
 * A View NAO deve:
 *
 * - receber Filme[];
 * - receber Critico;
 * - carregar os filmes;
 * - procurar Filme selecionado;
 * - atribuir nota;
 * - criar Critica;
 * - chamar atribuirCritica();
 *
 * Tudo isso sera coordenado pelo CriticaController.
 *
 *
 * Disponibilizar getters para:
 *
 * getCbFilmes()
 * getSpNota()
 * getTxtCritica()
 * getBtnEnviar()
 * getBtnVoltar()
 *
 * ============================================================
 */