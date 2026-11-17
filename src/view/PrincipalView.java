package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Base;
import model.Critico;
import model.Usuario;
import model.interfaces.GerenciaFilmes;
import controller.IFachada;


// Janela principal: barra superior + menu lateral + área de conteúdo. Cada opção do menu mostra uma tela (JPanel) usando CardLayout

public class PrincipalView extends JFrame {

    private JLabel lblUsuario = new JLabel();
    private JLabel lblPerfil = new JLabel();
    private JButton btnMeusDados = Estilo.botaoSecundario("Meus dados");
    private JButton btnSair = Estilo.botao("Sair", Estilo.PRIMARIA);

    private JPanel menu = new JPanel();
    private CardLayout cartoes = new CardLayout();
    private JPanel conteudo = new JPanel(cartoes);
    private ArrayList<JButton> botoesMenu = new ArrayList<JButton>();

    public PrincipalView(IFachada fachada, Usuario cliente, Base equipe, LoginView login) {
        setTitle("Cine JHEY");
        setSize(1240, 780);
        setMinimumSize(new Dimension(1100, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(montarBarraSuperior(), BorderLayout.NORTH);

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(Estilo.ESCURO_2);
        menu.setPreferredSize(new Dimension(210, 10));
        menu.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));
        add(menu, BorderLayout.WEST);

        conteudo.setBorder(BorderFactory.createEmptyBorder(22, 26, 22, 26));
        add(conteudo, BorderLayout.CENTER);

        //  dados da sessão e eventos 
        this.fachada = fachada;
        this.cliente = cliente;
        this.equipe = equipe;
        this.login = login;

        if (cliente != null) {
            montarTelasDoCliente();
        } else {
            montarTelasDaEquipe();
        }

        getBtnSair().addActionListener(e -> sair());
        getBtnMeusDados().addActionListener(e -> editarMeusDados());
        mostrarTela(primeiraTela);
    }

    private JPanel montarBarraSuperior() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(Estilo.ESCURO);
        barra.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 18));

        JLabel logo = new JLabel("CINE JHEY");
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));
        logo.setForeground(Color.WHITE);
        barra.add(logo, BorderLayout.WEST);

        JPanel direita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        direita.setOpaque(false);

        JPanel identificacao = new JPanel();
        identificacao.setLayout(new BoxLayout(identificacao, BoxLayout.Y_AXIS));
        identificacao.setOpaque(false);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(Estilo.FONTE_NEGRITO);
        lblUsuario.setAlignmentX(RIGHT_ALIGNMENT);
        lblPerfil.setForeground(Estilo.DOURADO);
        lblPerfil.setFont(Estilo.FONTE_PEQUENA);
        lblPerfil.setAlignmentX(RIGHT_ALIGNMENT);
        identificacao.add(lblUsuario);
        identificacao.add(lblPerfil);

        direita.add(identificacao);
        direita.add(Box.createHorizontalStrut(6));
        direita.add(btnMeusDados);
        direita.add(btnSair);
        barra.add(direita, BorderLayout.EAST);
        return barra;
    }

    public void setUsuario(String nome, String perfil) {
        lblUsuario.setText(nome);
        lblPerfil.setText(perfil.toUpperCase());
    }

    // Adiciona uma tela, cria o botão no menu lateral e carrega os dados dela
    // Ao clicar no botão, a tela aparece e seus dados são atualizados
    private void adicionarTela(final String nome, final Atualizavel tela) {
        conteudo.add((JPanel) tela, nome);

        JButton botao = new JButton(nome);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setFont(Estilo.FONTE_NEGRITO);
        botao.setForeground(new Color(0xD9D6E3));
        botao.setBackground(Estilo.ESCURO_2);
        botao.setContentAreaFilled(false);
        botao.setOpaque(true);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 12));
        botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        botao.setAlignmentX(LEFT_ALIGNMENT);
        botao.addActionListener(e -> {
            mostrarTela(nome);
            tela.atualizar();
        });

        menu.add(botao);
        botoesMenu.add(botao);
        tela.atualizar();
        if (primeiraTela == null) {
            primeiraTela = nome;
        }
    }

    // Mostra a tela escolhida e destaca o botão do menu
    public void mostrarTela(String nome) {
        cartoes.show(conteudo, nome);
        for (JButton botao : botoesMenu) {
            boolean ativo = botao.getText().equals(nome);
            botao.setBackground(ativo ? Estilo.PRIMARIA : Estilo.ESCURO_2);
            botao.setForeground(ativo ? Color.WHITE : new Color(0xD9D6E3));
        }
    }

    public JButton getBtnMeusDados() {
        return btnMeusDados;
    }

    public JButton getBtnSair() {
        return btnSair;
    }


    //  EVENTOS DA TELA - chamam a IFachada

    private IFachada fachada;
    private Usuario cliente;     // null se quem entrou foi a equipe
    private Base equipe;         // null se quem entrou foi um cliente
    private LoginView login;
    private String primeiraTela = null;

    private void montarTelasDoCliente() {
        setUsuario(cliente.getUser(), cliente.getTipo());

        adicionarTela("Comprar ingressos", new CompraView(fachada, cliente));
        adicionarTela("Minhas compras", new MinhasComprasView(fachada, cliente));
        adicionarTela("Programação", new ProgramacaoView(fachada));
        adicionarTela("Filmes", new FilmesView(fachada));

        // Só o Crítico pode avaliar filmes
        if (cliente instanceof Critico) {
            adicionarTela("Avaliar filmes", new CriticaView(fachada, (Critico) cliente));
        }
    }

    private void montarTelasDaEquipe() {
        setUsuario(equipe.getNome(), equipe.getCargo());
        getBtnMeusDados().setVisible(false);

        // Administrador e Funcionário implementam a interface GerenciaFilmes
        adicionarTela("Gerenciar filmes", new GerenciarFilmesView(fachada, (GerenciaFilmes) equipe));
        adicionarTela("Usuários", new GerenciarUsuariosView(fachada, equipe));
        adicionarTela("Programação", new ProgramacaoView(fachada));
        adicionarTela("Filmes", new FilmesView(fachada));
    }

    private void editarMeusDados() {
        CadastroUsuarioView cadastro = new CadastroUsuarioView(this, fachada, cliente, null);
        if (cadastro.abrir()) {
            setUsuario(cliente.getUser(), cliente.getTipo());
        }
    }

    private void sair() {
        if (Mensagens.confirmar(this, "Deseja sair da sua conta?")) {
            dispose();
            login.iniciar();
        }
    }
}
