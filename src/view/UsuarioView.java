package view;

import enums.Sexo;

import javax.swing.*;
import java.awt.*;

public class UsuarioView extends JFrame {

    private JComboBox<String> cbTipoUsuario = new JComboBox<>(
            new String[]{"Usuario", "Estudante", "Critico"}
    );

    private JTextField txtUser = new JTextField();

    private JTextField txtCpf = new JTextField();

    private JTextField txtIdade = new JTextField();

    private JTextField txtEmail = new JTextField();

    private JTextField txtNomeCartao = new JTextField();

    private JTextField txtNumeroCartao = new JTextField();

    private JTextField txtCodigoVerificador = new JTextField();

    private JTextField txtOrigem = new JTextField();

    private JPasswordField txtSenha = new JPasswordField();

    private JComboBox<Sexo> cbSexo = new JComboBox<>(
            Sexo.values()
    );

    private JLabel lblOrigem = new JLabel("Origem:");

    private JButton btnCadastrar = new JButton("Cadastrar");

    private JButton btnVoltar = new JButton("Voltar");

    public UsuarioView() {

        setTitle("Cadastrar Usuario");

        setSize(550, 600);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel painel = new JPanel(
                new GridLayout(12, 2, 8, 8)
        );

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        painel.add(new JLabel("Tipo:"));
        painel.add(cbTipoUsuario);

        painel.add(new JLabel("Usuario:"));
        painel.add(txtUser);

        painel.add(new JLabel("CPF:"));
        painel.add(txtCpf);

        painel.add(new JLabel("Senha:"));
        painel.add(txtSenha);

        painel.add(new JLabel("Idade:"));
        painel.add(txtIdade);

        painel.add(new JLabel("Sexo:"));
        painel.add(cbSexo);

        painel.add(new JLabel("Email:"));
        painel.add(txtEmail);

        painel.add(new JLabel("Nome no cartao:"));
        painel.add(txtNomeCartao);

        painel.add(new JLabel("Numero do cartao:"));
        painel.add(txtNumeroCartao);

        painel.add(new JLabel("Codigo verificador:"));
        painel.add(txtCodigoVerificador);

        painel.add(lblOrigem);
        painel.add(txtOrigem);

        painel.add(btnVoltar);
        painel.add(btnCadastrar);

        lblOrigem.setVisible(false);

        txtOrigem.setVisible(false);

        add(painel);

        // Configurar cores dos botoes
        Color rosinha = Color.decode("#edc0df");

        btnCadastrar.setBackground(rosinha);
        btnVoltar.setBackground(rosinha);

        btnCadastrar.setForeground(Color.BLACK);
        btnVoltar.setForeground(Color.BLACK);
    }

    public JComboBox<String> getCbTipoUsuario() {

        return cbTipoUsuario;
    }

    public JTextField getTxtUser() {

        return txtUser;
    }

    public JTextField getTxtCpf() {

        return txtCpf;
    }

    public JPasswordField getTxtSenha() {

        return txtSenha;
    }

    public JTextField getTxtIdade() {

        return txtIdade;
    }

    public JComboBox<Sexo> getCbSexo() {

        return cbSexo;
    }

    public JTextField getTxtEmail() {

        return txtEmail;
    }

    public JTextField getTxtNomeCartao() {

        return txtNomeCartao;
    }

    public JTextField getTxtNumeroCartao() {

        return txtNumeroCartao;
    }

    public JTextField getTxtCodigoVerificador() {

        return txtCodigoVerificador;
    }

    public JLabel getLblOrigem() {

        return lblOrigem;
    }

    public JTextField getTxtOrigem() {

        return txtOrigem;
    }

    public JButton getBtnCadastrar() {

        return btnCadastrar;
    }

    public JButton getBtnVoltar() {

        return btnVoltar;
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