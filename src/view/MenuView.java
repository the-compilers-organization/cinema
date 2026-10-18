package view;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {
    private JButton btnCadastrarUsuario = new JButton("Cadastrar Usuario");
    private JButton btnFilmes = new JButton("Listar Filmes");
    private JButton btnSessoes = new JButton("Listar Sessoes");
    private JButton btnRealizarCompra = new JButton("Central de Compras");
    private JButton btnAvaliarFilme = new JButton("Avaliar Filme");
    private JButton btnSair = new JButton("Sair");

    public MenuView() {
        setTitle("Sistema de Cinema");
        setSize(450, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("SISTEMA DE CINEMA", SwingConstants.CENTER), BorderLayout.NORTH);
        JPanel painel = new JPanel(new GridLayout(6, 1, 8, 8));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        JButton[] botoes = {btnCadastrarUsuario, btnFilmes, btnSessoes,
                btnRealizarCompra, btnAvaliarFilme, btnSair};
        Color rosinha = Color.decode("#edc0df");
        for (JButton botao : botoes) {
            botao.setBackground(rosinha);
            botao.setForeground(Color.BLACK);
            painel.add(botao);
        }
        add(painel);
    }

    public JButton getBtnCadastrarUsuario() { return btnCadastrarUsuario; }
    public JButton getBtnFilmes() { return btnFilmes; }
    public JButton getBtnSessoes() { return btnSessoes; }
    public JButton getBtnRealizarCompra() { return btnRealizarCompra; }
    public JButton getBtnAvaliarFilme() { return btnAvaliarFilme; }
    public JButton getBtnSair() { return btnSair; }
}




/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR MenuView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir a janela principal do sistema.
 *
 *
 * Deve possuir botoes para:
 *
 * - Cadastrar Usuario;
 * - Listar Filmes;
 * - Listar Sessoes;
 * - Realizar Compra;
 * - Alterar Compra;
 * - Cancelar Compra;
 * - Exibir Compra;
 * - Avaliar Filme;
 * - Sair.
 *
 *
 * A View deve:
 *
 * - criar JFrame;
 * - criar titulo;
 * - criar botoes;
 * - organizar os componentes;
 * - disponibilizar getters dos botoes.
 *
 *
 * A View NAO deve:
 *
 * - abrir UsuarioView;
 * - abrir FilmeView;
 * - abrir SessaoView;
 * - abrir CompraView;
 * - abrir CriticaView;
 * - encerrar o programa pelo evento do botao.
 *
 * Essas operacoes pertencem ao MenuController.
 *
 *
 * Portanto, nao adicionar ActionListener nesta View.
 *
 * O Controller obtera os botoes atraves dos getters.
 *
 * ============================================================
 */