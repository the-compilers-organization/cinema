package view;

import java.awt.Component;

import javax.swing.JOptionPane;


// Caixas de diálogo padronizadas. Os controllers usam estes métodos para mostrar a mensagem das exceções

public class Mensagens {

    public static void erro(Component pai, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, "Atenção", JOptionPane.ERROR_MESSAGE);
    }

    public static void erro(Component pai, String titulo, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static void aviso(Component pai, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public static void sucesso(Component pai, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmar(Component pai, String mensagem) {
        int resposta = JOptionPane.showConfirmDialog(pai, mensagem, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return resposta == JOptionPane.YES_OPTION;
    }
}
