package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import exceptions.UsuarioException;
import exceptions.ValidacaoException;
import model.Base;
import model.Usuario;
import controller.IFachada;


// Tela inicial: login de clientes e da equipe do cinema
// A tela trata os próprios cliques e chama a camada de negócio pela IFachada

public class LoginView extends JFrame {

    public static final String CLIENTE = "Cliente";
    public static final String EQUIPE = "Equipe do cinema";

    private JComboBox<String> cbPerfil = new JComboBox<String>(new String[]{CLIENTE, EQUIPE});
    private JLabel lblLogin = new JLabel("CPF");
    private JTextField txtLogin = new JTextField();
    private JPasswordField txtSenha = new JPasswordField();
    private JButton btnEntrar = Estilo.botao("Entrar", Estilo.PRIMARIA);
    private JButton btnCadastrar = Estilo.botaoSecundario("Criar minha conta");
    private JLabel lblDica = new JLabel();

    public LoginView(IFachada fachada) {
        setTitle("Cine JHEY - Entrar");
        setSize(900, 600);
        setMinimumSize(new Dimension(820, 560));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(montarPainelMarca(), BorderLayout.WEST);
        add(montarPainelFormulario(), BorderLayout.CENTER);

        //  eventos da tela 
        this.fachada = fachada;

        mostrarModoCliente(true);
        setDica("<html><b>Contas de teste</b><br>"
                + "Cliente: 11111111111 / 1234 &nbsp; Estudante: 22222222222 / 1234<br>"
                + "Crítico: 33333333333 / 1234<br>"
                + "Equipe: admin@cinema.com / admin123 &nbsp; ou &nbsp; func@cinema.com / func123</html>");

        // "e -> ..." é uma expressão lambda: o código executado no clique
        getCbPerfil().addActionListener(e -> mostrarModoCliente(isPerfilCliente()));
        getBtnEntrar().addActionListener(e -> entrar());
        getTxtSenha().addActionListener(e -> entrar()); // tecla Enter no campo senha
        getBtnCadastrar().addActionListener(e -> abrirCadastro());
    }

    // Lado esquerdo: identidade visual
    private JPanel montarPainelMarca() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(Estilo.ESCURO);
        painel.setPreferredSize(new Dimension(360, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(60, 40, 40, 40));

        JLabel faixa = new JLabel("SESSÃO DE HOJE");
        faixa.setFont(Estilo.FONTE_NEGRITO);
        faixa.setForeground(Estilo.DOURADO);

        JLabel nome = new JLabel("CINE JHEY");
        nome.setFont(new Font("SansSerif", Font.BOLD, 44));
        nome.setForeground(Color.WHITE);

        JLabel descricao = new JLabel("<html>Ingressos, poltronas e pipoca<br>em um só lugar.</html>");
        descricao.setFont(new Font("SansSerif", Font.PLAIN, 16));
        descricao.setForeground(new Color(0xC9C5D6));

        painel.add(faixa);
        painel.add(Box.createVerticalStrut(8));
        painel.add(nome);
        painel.add(Box.createVerticalStrut(16));
        painel.add(descricao);
        painel.add(Box.createVerticalStrut(40));
        painel.add(itemMarca("Escolha a sala, a sessão e a poltrona"));
        painel.add(Box.createVerticalStrut(10));
        painel.add(itemMarca("Sugestão de lugares juntos para grupos"));
        painel.add(Box.createVerticalStrut(10));
        painel.add(itemMarca("Meia para estudantes"));
        painel.add(Box.createVerticalStrut(10));
        painel.add(itemMarca("Cortesia para críticos de cinema"));
        painel.add(Box.createVerticalGlue());

        JLabel rodape = new JLabel("UNICAP  -  Programação Orientada a Objetos");
        rodape.setFont(Estilo.FONTE_PEQUENA);
        rodape.setForeground(new Color(0x8D88A0));
        painel.add(rodape);
        return painel;
    }

    private JLabel itemMarca(String texto) {
        JLabel label = new JLabel("▸  " + texto);
        label.setFont(Estilo.FONTE_NORMAL);
        label.setForeground(Color.WHITE);
        return label;
    }

    // Lado direito: formulário
    private JPanel montarPainelFormulario() {
        JPanel fundo = new JPanel(new GridBagLayout());
        fundo.setBackground(Estilo.FUNDO);

        JPanel cartao = Estilo.cartao();
        cartao.setLayout(new BoxLayout(cartao, BoxLayout.Y_AXIS));
        cartao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Estilo.BORDA),
                BorderFactory.createEmptyBorder(28, 32, 28, 32)));
        cartao.setPreferredSize(new Dimension(400, 470));

        Estilo.estilizarCampo(txtLogin);
        Estilo.estilizarCampo(txtSenha);

        JPanel painelLogin = new JPanel(new BorderLayout(0, 4));
        painelLogin.setOpaque(false);
        lblLogin.setFont(Estilo.FONTE_NEGRITO);
        lblLogin.setForeground(Estilo.TEXTO_SUAVE);
        painelLogin.add(lblLogin, BorderLayout.NORTH);
        painelLogin.add(txtLogin, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new GridLayout(2, 1, 0, 8));
        botoes.setOpaque(false);
        botoes.add(btnEntrar);
        botoes.add(btnCadastrar);

        lblDica.setFont(Estilo.FONTE_PEQUENA);
        lblDica.setForeground(Estilo.TEXTO_SUAVE);

        adicionarLinha(cartao, Estilo.titulo("Bem-vindo(a)!"));
        adicionarLinha(cartao, Estilo.textoSuave("Entre para comprar seus ingressos."));
        cartao.add(Box.createVerticalStrut(18));
        adicionarLinha(cartao, Estilo.campo("Entrar como", cbPerfil));
        cartao.add(Box.createVerticalStrut(10));
        adicionarLinha(cartao, painelLogin);
        cartao.add(Box.createVerticalStrut(10));
        adicionarLinha(cartao, Estilo.campo("Senha", txtSenha));
        cartao.add(Box.createVerticalStrut(18));
        adicionarLinha(cartao, botoes);
        cartao.add(Box.createVerticalStrut(14));
        adicionarLinha(cartao, lblDica);

        fundo.add(cartao);
        return fundo;
    }

    private void adicionarLinha(JPanel painel, java.awt.Component componente) {
        JPanel linha = new JPanel(new BorderLayout());
        linha.setOpaque(false);
        linha.add(componente, BorderLayout.CENTER);
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, componente.getPreferredSize().height + 4));
        painel.add(linha);
    }

    // Muda o rótulo do campo de login conforme o perfil escolhido
    public void mostrarModoCliente(boolean cliente) {
        lblLogin.setText(cliente ? "CPF (somente números)" : "E-mail da equipe");
        btnCadastrar.setVisible(cliente);
    }

    public void setDica(String textoHtml) {
        lblDica.setText(textoHtml);
    }

    public void limparCampos() {
        txtLogin.setText("");
        txtSenha.setText("");
    }

    public boolean isPerfilCliente() {
        return CLIENTE.equals(cbPerfil.getSelectedItem());
    }

    public String getLogin() {
        return txtLogin.getText().trim();
    }

    public String getSenha() {
        return new String(txtSenha.getPassword());
    }

    public JComboBox<String> getCbPerfil() {
        return cbPerfil;
    }

    public JPasswordField getTxtSenha() {
        return txtSenha;
    }

    public JButton getBtnEntrar() {
        return btnEntrar;
    }

    public JButton getBtnCadastrar() {
        return btnCadastrar;
    }


    //  EVENTOS DA TELA - chamam a IFachada
    private IFachada fachada;

    public void iniciar() {
        limparCampos();
        setVisible(true);
    }

    private void entrar() {
        try {
            PrincipalView principal;
            if (isPerfilCliente()) {
                Usuario usuario = fachada.entrarCliente(getLogin(), getSenha());
                principal = new PrincipalView(fachada, usuario, null, this);
            } else {
                Base equipe = fachada.entrarEquipe(getLogin(), getSenha());
                principal = new PrincipalView(fachada, null, equipe, this);
            }
            setVisible(false);
            principal.setVisible(true);

        } catch (ValidacaoException e) {
            Mensagens.erro(this, e.getMessage());
        } catch (UsuarioException e) {
            Mensagens.erro(this, "Login não realizado", e.getMessage());
        }
    }

    private void abrirCadastro() {
        CadastroUsuarioView cadastro = new CadastroUsuarioView(this, fachada, null, null);
        cadastro.abrir();
    }
}
