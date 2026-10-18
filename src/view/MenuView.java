package view;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    private JButton btnCadastrarUsuario = new JButton("Cadastrar Usuario");

    private JButton btnFilmes = new JButton("Listar Filmes");

    private JButton btnSessoes = new JButton("Listar Sessoes");

    private JButton btnRealizarCompra = new JButton("Realizar Compra");

    private JButton btnAlterarCompra = new JButton("Alterar Compra");

    private JButton btnCancelarCompra = new JButton("Cancelar Compra");

    private JButton btnExibirCompra = new JButton("Exibir Compra");

    private JButton btnAvaliarFilme = new JButton("Avaliar Filme");

    private JButton btnSair = new JButton("Sair");

    public MenuView() {

        setTitle("Sistema de Cinema");

        setSize(450, 600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        add(
                new JLabel("SISTEMA DE CINEMA", SwingConstants.CENTER),
                BorderLayout.NORTH
        );

        JPanel painel = new JPanel(
                new GridLayout(9, 1, 8, 8)
        );

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
        );

        painel.add(btnCadastrarUsuario);

        painel.add(btnFilmes);

        painel.add(btnSessoes);

        painel.add(btnRealizarCompra);

        painel.add(btnAlterarCompra);

        painel.add(btnCancelarCompra);

        painel.add(btnExibirCompra);

        painel.add(btnAvaliarFilme);

        painel.add(btnSair);

        add(painel);

         // Configurar cores dos botoes
        Color rosinha = Color.decode("#edc0df");

        btnCadastrarUsuario.setBackground(rosinha);
        btnFilmes.setBackground(rosinha);
        btnSessoes.setBackground(rosinha);
        btnRealizarCompra.setBackground(rosinha);
        btnAlterarCompra.setBackground(rosinha);
        btnCancelarCompra.setBackground(rosinha);
        btnExibirCompra.setBackground(rosinha);
        btnAvaliarFilme.setBackground(rosinha);
        btnSair.setBackground(rosinha);

        btnCadastrarUsuario.setForeground(Color.BLACK);
        btnFilmes.setForeground(Color.BLACK);
        btnSessoes.setForeground(Color.BLACK);
        btnRealizarCompra.setForeground(Color.BLACK);
        btnAlterarCompra.setForeground(Color.BLACK);
        btnCancelarCompra.setForeground(Color.BLACK);
        btnExibirCompra.setForeground(Color.BLACK);
        btnAvaliarFilme.setForeground(Color.BLACK);
        btnSair.setForeground(Color.BLACK);
    }

    public JButton getBtnCadastrarUsuario() {

        return btnCadastrarUsuario;
    }

    public JButton getBtnFilmes() {

        return btnFilmes;
    }

    public JButton getBtnSessoes() {

        return btnSessoes;
    }

    public JButton getBtnRealizarCompra() {

        return btnRealizarCompra;
    }

    public JButton getBtnAlterarCompra() {

        return btnAlterarCompra;
    }

    public JButton getBtnCancelarCompra() {

        return btnCancelarCompra;
    }

    public JButton getBtnExibirCompra() {

        return btnExibirCompra;
    }

    public JButton getBtnAvaliarFilme() {

        return btnAvaliarFilme;
    }

    public JButton getBtnSair() {

        return btnSair;
    }
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