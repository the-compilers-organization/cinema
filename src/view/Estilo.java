package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


// Cores, fontes e "fábricas" de componentes usados em todas as telas
// Centralizar o visual aqui deixa as telas com a mesma aparência

public class Estilo {

    //  Paleta de cores 
    public static final Color FUNDO = new Color(0xF5F2EC);       // creme claro
    public static final Color CARTAO = Color.WHITE;
    public static final Color ESCURO = new Color(0x1C1A27);      // barra superior / menu
    public static final Color ESCURO_2 = new Color(0x2A2738);    // menu lateral
    public static final Color PRIMARIA = new Color(0xB3261E);    // vermelho "cortina de cinema"
    public static final Color DOURADO = new Color(0xE2A93B);
    public static final Color VERDE = new Color(0x2E7D4F);
    public static final Color AZUL = new Color(0x2F5FA7);
    public static final Color TEXTO = new Color(0x22212B);
    public static final Color TEXTO_SUAVE = new Color(0x6E6A78);
    public static final Color BORDA = new Color(0xE2DDD3);
    public static final Color SELECAO = new Color(0xF6E3B4);

    //  Fontes 
    public static final Font FONTE_TITULO = new Font("SansSerif", Font.BOLD, 22);
    public static final Font FONTE_SUBTITULO = new Font("SansSerif", Font.BOLD, 15);
    public static final Font FONTE_NORMAL = new Font("SansSerif", Font.PLAIN, 13);
    public static final Font FONTE_NEGRITO = new Font("SansSerif", Font.BOLD, 13);
    public static final Font FONTE_PEQUENA = new Font("SansSerif", Font.PLAIN, 11);

    // Aplica o tema em todo o sistema. Chamado uma vez no Main
    public static void aplicarTema() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            // se falhar, o Java usa o tema padrão
        }
        UIManager.put("Panel.background", FUNDO);
        UIManager.put("OptionPane.background", FUNDO);
        UIManager.put("Label.font", FONTE_NORMAL);
        UIManager.put("Label.foreground", TEXTO);
        UIManager.put("Button.font", FONTE_NEGRITO);
        UIManager.put("TextField.font", FONTE_NORMAL);
        UIManager.put("PasswordField.font", FONTE_NORMAL);
        UIManager.put("TextArea.font", FONTE_NORMAL);
        UIManager.put("ComboBox.font", FONTE_NORMAL);
        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", SELECAO);
        UIManager.put("ComboBox.selectionForeground", TEXTO);
        UIManager.put("TextField.inactiveForeground", TEXTO_SUAVE);
        UIManager.put("Spinner.font", FONTE_NORMAL);
        UIManager.put("CheckBox.font", FONTE_NORMAL);
        UIManager.put("Table.font", FONTE_NORMAL);
        UIManager.put("TableHeader.font", FONTE_NEGRITO);
        UIManager.put("ToolTip.font", FONTE_PEQUENA);
    }

    // Botão colorido "chapado", com efeito ao passar o mouse
    public static JButton botao(String texto, final Color cor) {
        final JButton botao = new JButton(texto);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(FONTE_NEGRITO);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setOpaque(true);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBorder(BorderFactory.createEmptyBorder(9, 16, 9, 16));
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (botao.isEnabled()) {
                    botao.setBackground(cor.darker());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(cor);
            }
        });
        return botao;
    }

    // Botão branco com borda (ações secundárias)
    public static JButton botaoSecundario(String texto) {
        final JButton botao = botao(texto, Color.WHITE);
        botao.setForeground(TEXTO);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDA),
                BorderFactory.createEmptyBorder(8, 14, 8, 14)));
        return botao;
    }

    public static JLabel titulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_TITULO);
        label.setForeground(TEXTO);
        return label;
    }

    public static JLabel subtitulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_SUBTITULO);
        label.setForeground(TEXTO);
        return label;
    }

    public static JLabel textoSuave(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_NORMAL);
        label.setForeground(TEXTO_SUAVE);
        return label;
    }

    // Cabeçalho padrão das telas: título grande + descrição
    public static JPanel cabecalho(String titulo, String descricao) {
        JPanel painel = new JPanel(new BorderLayout(0, 4));
        painel.setOpaque(false);
        painel.add(titulo(titulo), BorderLayout.NORTH);
        painel.add(textoSuave(descricao), BorderLayout.CENTER);
        painel.setBorder(BorderFactory.createEmptyBorder(0, 0, 14, 0));
        return painel;
    }

    // Painel branco com borda arredondada visualmente simples ("cartão")
    public static JPanel cartao() {
        JPanel painel = new JPanel();
        painel.setBackground(CARTAO);
        painel.setBorder(bordaCartao());
        return painel;
    }

    public static Border bordaCartao() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDA),
                BorderFactory.createEmptyBorder(14, 16, 14, 16));
    }

    // Aplica o visual padrão em uma tabela
    public static void estilizarTabela(JTable tabela) {
        tabela.setRowHeight(28);
        tabela.setShowVerticalLines(false);
        tabela.setGridColor(BORDA);
        tabela.setSelectionBackground(SELECAO);
        tabela.setSelectionForeground(TEXTO);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setFillsViewportHeight(true);
        tabela.getTableHeader().setReorderingAllowed(false);

        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setBackground(ESCURO_2);
        cabecalho.setForeground(Color.WHITE);
        cabecalho.setPreferredSize(new Dimension(10, 30));

        DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer();
        renderizador.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        tabela.setDefaultRenderer(Object.class, renderizador);
    }

    public static JScrollPane rolagem(Component componente) {
        JScrollPane rolagem = new JScrollPane(componente);
        rolagem.setBorder(BorderFactory.createLineBorder(BORDA));
        rolagem.getViewport().setBackground(Color.WHITE);
        return rolagem;
    }

    // Rótulo em cima + campo embaixo
    public static JPanel campo(String rotulo, JComponent componente) {
        JPanel painel = new JPanel(new BorderLayout(0, 4));
        painel.setOpaque(false);
        JLabel label = new JLabel(rotulo);
        label.setFont(FONTE_NEGRITO);
        label.setForeground(TEXTO_SUAVE);
        painel.add(label, BorderLayout.NORTH);
        painel.add(componente, BorderLayout.CENTER);
        return painel;
    }

    // Borda interna para campos de texto
    public static void estilizarCampo(JComponent campo) {
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDA),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
    }

    public static String dinheiro(double valor) {
        return String.format("R$ %.2f", valor);
    }
}
