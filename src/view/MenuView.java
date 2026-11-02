package view;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {
    private final JButton btnCadastrarUsuario = new JButton("Cadastrar Usuario");
    private final JButton btnLoginUsuario = new JButton("Login Usuario");
    private final JButton btnFilmes = new JButton("Listar Filmes");
    private final JButton btnSessoes = new JButton("Listar Sessoes");
    private final JButton btnRealizarCompra = new JButton("Central de Compras");
    private final JButton btnAvaliarFilme = new JButton("Avaliar Filme");
    private final JButton btnSair = new JButton("Sair");
    private final JButton btnSairConta = new JButton("Sair da Conta");
    private final CardLayout cartoes = new CardLayout();
    private final JPanel conteudo = new JPanel(cartoes);
    private final JLabel titulo = new JLabel("SISTEMA DE CINEMA", SwingConstants.CENTER);

    public MenuView() {
        setTitle("Sistema de Cinema");
        setSize(450, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        conteudo.add(criarPainel(btnCadastrarUsuario, btnLoginUsuario, btnSair), "principal");
        conteudo.add(criarPainel(btnFilmes, btnSessoes, btnRealizarCompra,
                btnAvaliarFilme, btnSairConta), "cliente");
        add(conteudo, BorderLayout.CENTER);
        mostrarPrincipal();
    }

    private JPanel criarPainel(JButton... botoes) {
        JPanel painel = new JPanel(new GridLayout(botoes.length, 1, 8, 8));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        Color rosinha = Color.decode("#edc0df");
        for (JButton botao : botoes) {
            botao.setBackground(rosinha);
            botao.setForeground(Color.BLACK);
            painel.add(botao);
        }
        return painel;
    }

    public void mostrarPrincipal() {
        titulo.setText("SISTEMA DE CINEMA");
        cartoes.show(conteudo, "principal");
    }

    public void mostrarCliente(String nome) {
        titulo.setText("AREA DO USUARIO - " + nome);
        cartoes.show(conteudo, "cliente");
    }

    public JButton getBtnCadastrarUsuario() { return btnCadastrarUsuario; }
    public JButton getBtnLoginUsuario() { return btnLoginUsuario; }
    public JButton getBtnFilmes() { return btnFilmes; }
    public JButton getBtnSessoes() { return btnSessoes; }
    public JButton getBtnRealizarCompra() { return btnRealizarCompra; }
    public JButton getBtnAvaliarFilme() { return btnAvaliarFilme; }
    public JButton getBtnSairConta() { return btnSairConta; }
    public JButton getBtnSair() { return btnSair; }
}
