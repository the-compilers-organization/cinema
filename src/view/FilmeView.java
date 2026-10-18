package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FilmeView extends JFrame {

    private DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Filme", "Duracao", "Sinopse", "Valor", "Nota"}, 0
    ) {

        @Override
        public boolean isCellEditable(int linha, int coluna) {

            return false;
        }
    };

    private JTable tabelaFilmes = new JTable(modelo);

    private JButton btnVoltar = new JButton("Voltar");

    public FilmeView() {

        setTitle("Filmes");

        setSize(850, 450);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);

        add(
                new JScrollPane(tabelaFilmes),
                BorderLayout.CENTER
        );

        add(btnVoltar, BorderLayout.SOUTH);

        // Configurar cor do botao Voltar
        Color rosinha = Color.decode("#edc0df");

        btnVoltar.setBackground(rosinha);
        btnVoltar.setForeground(Color.BLACK);
    }

    public DefaultTableModel getModelo() {

        return modelo;
    }

    public JTable getTabelaFilmes() {

        return tabelaFilmes;
    }

    public JButton getBtnVoltar() {

        return btnVoltar;
    }
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR FilmeView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir a tela que apresenta os filmes.
 *
 *
 * Criar:
 *
 * - DefaultTableModel;
 * - JTable;
 * - JScrollPane;
 * - JButton Voltar.
 *
 *
 * Colunas:
 *
 * Filme
 * Duracao
 * Sinopse
 * Valor
 * Nota
 *
 *
 * A View deve apenas construir a tabela.
 *
 * Nao deve receber Filme[].
 *
 * Nao deve percorrer Filme[].
 *
 * Nao deve possuir carregarFilmes().
 *
 * Quem faz isso agora e:
 *
 * FilmeController.
 *
 *
 * Disponibilizar:
 *
 * getModelo()
 * getTabelaFilmes()
 * getBtnVoltar()
 *
 * ============================================================
 */