package view;

import javax.swing.*;
import java.awt.*;
import enums.Sexo;

public class UsuarioView extends JFrame {

    private JComboBox<String> tipoUsuario;
    private JTextField user;
    private JTextField cpf;
    private JPasswordField senha;
    private JTextField idade;
    private JComboBox<Sexo> sexo;
    private JTextField email;
    private JTextField nomeCartao;
    private JTextField numeroCartao;
    private JTextField codigoVerificador;

    private JLabel labelOrigem;
    private JTextField txtOrigem;

    private JButton cadastrar;
    private JButton voltar;

    //GETTERS
    public JComboBox<String> getTipoUsuario() {
        return tipoUsuario;
    }

    public JTextField getUser() {
        return user;
    }

    public JTextField getCpf() {
        return cpf;
    }

    public JPasswordField getSenha() {
        return senha;
    }

    public JTextField getIdade() {
        return idade;
    }

    public JComboBox<Sexo> getSexo() {
        return sexo;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getNomeCartao() {
        return nomeCartao;
    }

    public JTextField getNumeroCartao() {
        return numeroCartao;
    }

    public JTextField getCodigoVerificador() {
        return codigoVerificador;
    }

    public JLabel getLabelOrigem() {
        return labelOrigem;
    }

    public JTextField getTxtOrigem() {
        return txtOrigem;
    }

    public JButton getCadastrar() {
        return cadastrar;
    }

    public JButton getVoltar() {
        return voltar;
    }



    public UsuarioView(){
        setTitle("Cadastro de usuario");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tipoUsuario = new JComboBox<String>(new String[]{"Usuario", "Estudante", "Critico"});

        user = new JTextField();
        cpf = new JTextField();
        senha = new JPasswordField();
        idade = new JTextField();
        sexo = new JComboBox<>(Sexo.values());
        email = new JTextField();
        nomeCartao = new JTextField();
        numeroCartao = new JTextField();
        codigoVerificador = new JTextField();

        labelOrigem = new JLabel("Origem");
        txtOrigem = new JTextField();

        labelOrigem.setVisible(false);
        txtOrigem.setVisible(false);

        cadastrar = new JButton("Cadastrar");
        voltar = new JButton("Voltar");

        Color rosinha = Color.decode("#edc0df");
        cadastrar.setBackground(rosinha);
        voltar.setBackground(rosinha);
        cadastrar.setForeground(Color.BLACK);
        voltar.setForeground(Color.BLACK);

        JPanel painelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        painelFormulario.add(new JLabel("Tipo de usuario: "));
        painelFormulario.add(tipoUsuario);

        painelFormulario.add(new JLabel("User: "));
        painelFormulario.add(user);

        painelFormulario.add(new JLabel("CPF: "));
        painelFormulario.add(cpf);

        painelFormulario.add(new JLabel("Senha: "));
        painelFormulario.add(senha);

        painelFormulario.add(new JLabel("Idade: "));
        painelFormulario.add(idade);

        painelFormulario.add(new JLabel("Sexo: "));
        painelFormulario.add(sexo);

        painelFormulario.add(new JLabel("Email: "));
        painelFormulario.add(email);

        painelFormulario.add(new JLabel("Nome do cartão: "));
        painelFormulario.add(nomeCartao);

        painelFormulario.add(new JLabel("Numero do cartão: "));
        painelFormulario.add(numeroCartao);

        painelFormulario.add(new JLabel("Codigo verificador: "));
        painelFormulario.add(codigoVerificador);

        painelFormulario.add(labelOrigem);
        painelFormulario.add(txtOrigem);


        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painelBotoes.add(cadastrar);
        painelBotoes.add(voltar);

        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

    }
}

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR UsuarioView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir o formulario visual de cadastro de usuario.
 *
 *
 * Criar componentes para:
 *
 * - tipo de usuario;
 * - user;
 * - CPF;
 * - senha;
 * - idade;
 * - Sexo;
 * - email;
 * - nome no cartao;
 * - numero do cartao;
 * - codigo verificador;
 * - origem;
 * - Cadastrar;
 * - Voltar.
 *
 *
 * Tipo de usuario:
 *
 * JComboBox<String>
 *
 * com:
 *
 * Usuario
 * Estudante
 * Critico
 *
 *
 * Sexo:
 *
 * JComboBox<Sexo>
 *
 * utilizando:
 *
 * Sexo.values()
 *
 *
 * Origem:
 *
 * Criar JLabel e JTextField, inicialmente invisiveis.
 *
 * Quem decide quando mostrar esses componentes sera o
 * UsuarioController.
 *
 *
 * Disponibilizar getters dos componentes que o Controller
 * precisa consultar ou alterar.
 *
 *
 * NAO deve existir nesta View:
 *
 * cadastrarUsuario()
 * alterarTipoUsuario()
 * voltar()
 *
 * Esses metodos pertencem ao UsuarioController.
 *
 *
 * UsuarioView tambem nao precisa mais possuir:
 *
 * Usuario usuario
 *
 * O usuario criado sera administrado pelo Controller.
 *
 * ============================================================
 */