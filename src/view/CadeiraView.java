package view;

import javax.swing.*;
import java.awt.*;

public class CadeiraView extends JFrame {

    private JButton[][] botoesCadeiras = new JButton[10][15];

    private JButton btnConfirmar = new JButton("Confirmar");
    private JButton btnVoltar = new JButton("Voltar");

    public CadeiraView() {

        setTitle("Escolher Cadeira");

        setSize(1000, 650);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);

        add(
                new JLabel("TELA", SwingConstants.CENTER),
                BorderLayout.NORTH
        );

        JPanel painel = new JPanel(
                new GridLayout(10, 15, 3, 3)
        );

        // Definir cor rosinha
        Color rosinha = Color.decode("#edc0df");

        int i;
        int j;

        for (i = 0; i < 10; i += 1) {

            for (j = 0; j < 15; j += 1) {

                botoesCadeiras[i][j] = new JButton(
                        String.format("%02d", j + 1)
                );

                // Configurar cor da cadeira
                botoesCadeiras[i][j].setBackground(rosinha);

                botoesCadeiras[i][j].setForeground(Color.BLACK);

                painel.add(botoesCadeiras[i][j]);
            }
        }

        add(painel, BorderLayout.CENTER);

        JPanel rodape = new JPanel();

        rodape.add(btnVoltar);

        rodape.add(btnConfirmar);

        add(rodape, BorderLayout.SOUTH);

        // Configurar cores dos botoes do rodape
        btnConfirmar.setBackground(rosinha);
        btnVoltar.setBackground(rosinha);

        btnConfirmar.setForeground(Color.BLACK);
        btnVoltar.setForeground(Color.BLACK);
    }

    public JButton[][] getBotoesCadeiras() {

        return botoesCadeiras;
    }

    public JButton getBtnConfirmar() {

        return btnConfirmar;
    }

    public JButton getBtnVoltar() {

        return btnVoltar;
    }
}



/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CadeiraView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir visualmente o mapa de cadeiras.
 *
 *
 * Criar:
 *
 * JButton[10][15]
 *
 * para representar a matriz de cadeiras.
 *
 *
 * Utilizar:
 *
 * GridLayout(10, 15, ...)
 *
 *
 * Criar tambem:
 *
 * JLabel "TELA"
 * JButton Voltar
 * JButton Confirmar
 *
 *
 * IMPORTANTE:
 *
 * A View cria os 150 botoes, mas nao decide quais cadeiras
 * estao ocupadas.
 *
 * Essa informacao sera obtida pelo CadeiraController atraves
 * da Sessao.
 *
 *
 * A View NAO deve:
 *
 * - receber Sessao;
 * - chamar cadeiraDisponivel();
 * - chamar ocuparCadeira();
 * - chamar liberarCadeira();
 * - guardar linhaSelecionada;
 * - guardar colunaSelecionada;
 * - chamar CompraView diretamente.
 *
 *
 * Disponibilizar:
 *
 * getBotoesCadeiras()
 * getBtnVoltar()
 * getBtnConfirmar()
 *
 * ============================================================
 */