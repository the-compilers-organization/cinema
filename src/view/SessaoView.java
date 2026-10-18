package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SessaoView extends JFrame {

    private DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Sala", "Tipo", "Horario", "Filme", "Valor"}, 0
    ) {

        @Override
        public boolean isCellEditable(int linha, int coluna) {

            return false;
        }
    };

    private JTable tabelaSessaos = new JTable(modelo);

    private JButton btnVoltar = new JButton("Voltar");

    public SessaoView() {

        setTitle("Sessaos");

        setSize(850, 450);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);

        add(
                new JScrollPane(tabelaSessaos),
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

    public JTable getTabelaSessaos() {

        return tabelaSessaos;
    }

    public JButton getBtnVoltar() {

        return btnVoltar;
    }
}



/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR SessaoView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir a tela que apresenta as sessoes existentes.
 *
 *
 * Criar JTable com as colunas:
 *
 * Sala
 * Tipo
 * Horario
 * Filme
 * Valor
 *
 *
 * Criar tambem:
 *
 * JButton Voltar
 *
 *
 * A View NAO deve:
 *
 * - receber Sala[];
 * - percorrer Sala[];
 * - percorrer Sessao[];
 * - calcular valor;
 * - possuir carregarSessoes();
 *
 * Essas responsabilidades passam para SessaoController.
 *
 *
 * Disponibilizar getters para:
 *
 * DefaultTableModel
 * JTable
 * JButton Voltar
 *
 * ============================================================
 */