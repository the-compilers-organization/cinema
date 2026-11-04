package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Sessao;


// Mapa de poltronas da sessão (10 fileiras x 15 poltronas)
// Clique em uma poltrona livre para selecioná-la

public class MapaCadeirasPanel extends JPanel {

    private static final int LIVRE = 0;
    private static final int OCUPADA = 1;
    private static final int NO_CARRINHO = 2;
    private static final int SELECIONADA = 3;

    private static final Color COR_LIVRE = new Color(0xDCE9DF);
    private static final Color COR_OCUPADA = new Color(0xC9C4CF);
    private static final Color COR_CARRINHO = new Color(0x8FB1E3);
    private static final Color COR_SELECIONADA = Estilo.DOURADO;

    private JButton[][] botoes = new JButton[Sessao.LINHAS][Sessao.COLUNAS];
    private int[][] estado = new int[Sessao.LINHAS][Sessao.COLUNAS];
    private ArrayList<ActionListener> ouvintes = new ArrayList<ActionListener>();

    public MapaCadeirasPanel() {
        setLayout(new BorderLayout(0, 10));
        setOpaque(false);

        // Tela do cinema
        JLabel tela = new JLabel("T E L A", SwingConstants.CENTER);
        tela.setOpaque(true);
        tela.setBackground(Estilo.ESCURO);
        tela.setForeground(Color.WHITE);
        tela.setFont(Estilo.FONTE_NEGRITO);
        tela.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        add(tela, BorderLayout.NORTH);

        // Grade: 1 linha extra (números) e 1 coluna extra (letras)
        JPanel grade = new JPanel(new GridLayout(Sessao.LINHAS + 1, Sessao.COLUNAS + 1, 4, 4));
        grade.setOpaque(false);
        grade.add(new JLabel(""));
        for (int c = 0; c < Sessao.COLUNAS; c++) {
            grade.add(rotulo(String.valueOf(c + 1)));
        }

        for (int l = 0; l < Sessao.LINHAS; l++) {
            grade.add(rotulo(String.valueOf((char) ('A' + l))));
            for (int c = 0; c < Sessao.COLUNAS; c++) {
                botoes[l][c] = criarBotao(l, c);
                grade.add(botoes[l][c]);
            }
        }
        add(grade, BorderLayout.CENTER);
        add(montarLegenda(), BorderLayout.SOUTH);

        desabilitar();
    }

    private JLabel rotulo(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(Estilo.FONTE_PEQUENA);
        label.setForeground(Estilo.TEXTO_SUAVE);
        return label;
    }

    private JButton criarBotao(final int linha, final int coluna) {
        JButton botao = new JButton();
        botao.setPreferredSize(new Dimension(30, 24));
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setOpaque(true);
        botao.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        botao.setFont(new Font("SansSerif", Font.PLAIN, 9));
        botao.setToolTipText("Poltrona " + Sessao.nomeDaCadeira(linha, coluna));
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alternarSelecao(linha, coluna);
            }
        });
        return botao;
    }

    private JPanel montarLegenda() {
        JPanel legenda = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));
        legenda.setOpaque(false);
        legenda.add(itemLegenda(COR_LIVRE, "Livre"));
        legenda.add(itemLegenda(COR_SELECIONADA, "Selecionada"));
        legenda.add(itemLegenda(COR_CARRINHO, "No carrinho"));
        legenda.add(itemLegenda(COR_OCUPADA, "Ocupada"));
        return legenda;
    }

    private JLabel itemLegenda(Color cor, String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(Estilo.FONTE_PEQUENA);
        label.setIcon(new javax.swing.Icon() {
            public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
                g.setColor(cor);
                g.fillRect(x, y, 14, 12);
            }

            public int getIconWidth() {
                return 14;
            }

            public int getIconHeight() {
                return 12;
            }
        });
        return label;
    }

    // Clique do usuário: livre <-> selecionada
    private void alternarSelecao(int linha, int coluna) {
        if (estado[linha][coluna] == LIVRE) {
            estado[linha][coluna] = SELECIONADA;
        } else if (estado[linha][coluna] == SELECIONADA) {
            estado[linha][coluna] = LIVRE;
        } else {
            return;
        }
        pintar(linha, coluna);
        avisarOuvintes();
    }

    private void pintar(int l, int c) {
        JButton botao = botoes[l][c];
        botao.setText("");
        switch (estado[l][c]) {
            case OCUPADA:
                botao.setBackground(COR_OCUPADA);
                botao.setCursor(Cursor.getDefaultCursor());
                break;
            case NO_CARRINHO:
                botao.setBackground(COR_CARRINHO);
                botao.setCursor(Cursor.getDefaultCursor());
                break;
            case SELECIONADA:
                botao.setBackground(COR_SELECIONADA);
                botao.setText(String.valueOf(c + 1));
                botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
                break;
            default:
                botao.setBackground(COR_LIVRE);
                botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    
    // Carrega a situação das poltronas de uma sessão
    // ocupadas[l][c] = vendida; noCarrinho[l][c] = já está no carrinho do usuário
    public void carregar(boolean[][] ocupadas, boolean[][] noCarrinho) {
        for (int l = 0; l < Sessao.LINHAS; l++) {
            for (int c = 0; c < Sessao.COLUNAS; c++) {
                if (ocupadas[l][c]) {
                    estado[l][c] = OCUPADA;
                } else if (noCarrinho[l][c]) {
                    estado[l][c] = NO_CARRINHO;
                } else {
                    estado[l][c] = LIVRE;
                }
                botoes[l][c].setEnabled(true);
                pintar(l, c);
            }
        }
        avisarOuvintes();
    }

    // Sem sessão escolhida: mapa todo cinza
    public void desabilitar() {
        for (int l = 0; l < Sessao.LINHAS; l++) {
            for (int c = 0; c < Sessao.COLUNAS; c++) {
                estado[l][c] = OCUPADA;
                pintar(l, c);
                botoes[l][c].setEnabled(false);
            }
        }
        avisarOuvintes();
    }

    public void limparSelecao() {
        for (int l = 0; l < Sessao.LINHAS; l++) {
            for (int c = 0; c < Sessao.COLUNAS; c++) {
                if (estado[l][c] == SELECIONADA) {
                    estado[l][c] = LIVRE;
                    pintar(l, c);
                }
            }
        }
        avisarOuvintes();
    }

    // Seleciona "quantidade" poltronas lado a lado (usado na sugestão)
    public void selecionarGrupo(int linha, int colunaInicial, int quantidade) {
        limparSelecao();
        for (int c = colunaInicial; c < colunaInicial + quantidade; c++) {
            estado[linha][c] = SELECIONADA;
            pintar(linha, c);
        }
        avisarOuvintes();
    }

    // Lista de {linha, coluna} das poltronas selecionadas
    public ArrayList<int[]> getSelecionadas() {
        ArrayList<int[]> lista = new ArrayList<int[]>();
        for (int l = 0; l < Sessao.LINHAS; l++) {
            for (int c = 0; c < Sessao.COLUNAS; c++) {
                if (estado[l][c] == SELECIONADA) {
                    lista.add(new int[]{l, c});
                }
            }
        }
        return lista;
    }

    // O controller pode ser avisado quando a seleção mudar
    public void adicionarOuvinte(ActionListener ouvinte) {
        ouvintes.add(ouvinte);
    }

    private void avisarOuvintes() {
        for (ActionListener ouvinte : ouvintes) {
            ouvinte.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "selecao"));
        }
    }
}
