package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.enums.Sexo;
import exceptions.PersistenciaException;
import exceptions.UsuarioException;
import exceptions.ValidacaoException;
import model.Base;
import model.Critico;
import model.Estudante;
import model.Usuario;
import controller.IFachada;


// Formulário de cadastro/edição de usuário (janela modal) 

public class CadastroUsuarioView extends JDialog {

    public static final String TIPO_CLIENTE = "Cliente";
    public static final String TIPO_ESTUDANTE = "Estudante";
    public static final String TIPO_CRITICO = "Crítico";

    private JComboBox<String> cbTipo = new JComboBox<String>(
            new String[]{TIPO_CLIENTE, TIPO_ESTUDANTE, TIPO_CRITICO});
    private JTextField txtUser = new JTextField();
    private JTextField txtCpf = new JTextField();
    private JPasswordField txtSenha = new JPasswordField();
    private JTextField txtIdade = new JTextField();
    private JComboBox<Sexo> cbSexo = new JComboBox<Sexo>(Sexo.values());
    private JTextField txtEmail = new JTextField();
    private JTextField txtNomeCartao = new JTextField();
    private JTextField txtNumeroCartao = new JTextField();
    private JTextField txtCvv = new JTextField();
    private JTextField txtOrigem = new JTextField();
    private JLabel lblTitulo = Estilo.titulo("Criar conta");
    private JLabel lblSenhaDica = Estilo.textoSuave(" ");

    private JButton btnSalvar = Estilo.botao("Salvar", Estilo.VERDE);
    private JButton btnCancelar = Estilo.botaoSecundario("Cancelar");

    public CadastroUsuarioView(Frame pai, IFachada fachada, Usuario usuarioEmEdicao, Base equipe) {
        super(pai, "Cadastro de usuário", true);
        setSize(620, 540);
        setLocationRelativeTo(pai);
        setLayout(new BorderLayout());

        JPanel conteudo = new JPanel(new BorderLayout(0, 12));
        conteudo.setBorder(BorderFactory.createEmptyBorder(20, 24, 16, 24));

        JPanel topo = new JPanel(new BorderLayout());
        topo.setOpaque(false);
        topo.add(lblTitulo, BorderLayout.NORTH);
        topo.add(Estilo.textoSuave("Todos os campos são obrigatórios."), BorderLayout.SOUTH);

        JTextField[] campos = {txtUser, txtCpf, txtIdade, txtEmail, txtNomeCartao, txtNumeroCartao, txtCvv, txtOrigem};
        for (JTextField campo : campos) {
            Estilo.estilizarCampo(campo);
        }
        Estilo.estilizarCampo(txtSenha);

        JPanel formulario = new JPanel(new GridLayout(0, 2, 14, 10));
        formulario.setOpaque(false);
        formulario.add(Estilo.campo("Tipo de conta", cbTipo));
        formulario.add(Estilo.campo("Nome de usuário (só letras)", txtUser));
        formulario.add(Estilo.campo("CPF (11 números)", txtCpf));
        formulario.add(Estilo.campo("Senha (mín. 4 caracteres)", txtSenha));
        formulario.add(Estilo.campo("Idade", txtIdade));
        formulario.add(Estilo.campo("Sexo", cbSexo));
        formulario.add(Estilo.campo("E-mail", txtEmail));
        formulario.add(Estilo.campo("Origem (só para crítico)", txtOrigem));

        JPanel cartao = new JPanel(new GridLayout(0, 2, 14, 10));
        cartao.setOpaque(false);
        cartao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Estilo.BORDA),
                BorderFactory.createEmptyBorder(12, 0, 0, 0)));
        cartao.add(Estilo.campo("Nome no cartão (só letras)", txtNomeCartao));
        cartao.add(Estilo.campo("Número do cartão (16 números)", txtNumeroCartao));
        cartao.add(Estilo.campo("Código verificador (CVV)", txtCvv));
        cartao.add(lblSenhaDica);

        // O formulário fica no topo para os campos não esticarem
        JPanel centro = new JPanel(new BorderLayout(0, 14));
        centro.setOpaque(false);
        centro.add(formulario, BorderLayout.NORTH);
        centro.add(cartao, BorderLayout.CENTER);

        JPanel norte = new JPanel(new BorderLayout(0, 14));
        norte.setOpaque(false);
        norte.add(topo, BorderLayout.NORTH);
        norte.add(centro, BorderLayout.CENTER);
        conteudo.add(norte, BorderLayout.NORTH);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botoes.setOpaque(false);
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);
        conteudo.add(botoes, BorderLayout.SOUTH);

        add(conteudo, BorderLayout.CENTER);
        atualizarCampoOrigem();

        //  eventos da tela 
        this.fachada = fachada;
        this.usuarioEmEdicao = usuarioEmEdicao;
        this.equipe = equipe;

        if (usuarioEmEdicao != null) {
            String origem = null;
            if (usuarioEmEdicao instanceof Critico) {
                origem = ((Critico) usuarioEmEdicao).getOrigem();
            }
            modoEdicao(usuarioEmEdicao.getTipo(), usuarioEmEdicao.getUser(), usuarioEmEdicao.getCpf(),
                    usuarioEmEdicao.getIdade(), usuarioEmEdicao.getSexo(), usuarioEmEdicao.getEmail(),
                    usuarioEmEdicao.getNomeCartao(), usuarioEmEdicao.getNumeroCartao(),
                    usuarioEmEdicao.getCodigoVerificador(), origem);
        }

        getCbTipo().addActionListener(e -> atualizarCampoOrigem());
        getBtnSalvar().addActionListener(e -> salvar());
        getBtnCancelar().addActionListener(e -> dispose());
    }

    // Origem só pode ser preenchida quando o tipo é Crítico
    public void atualizarCampoOrigem() {
        boolean critico = TIPO_CRITICO.equals(cbTipo.getSelectedItem());
        txtOrigem.setEnabled(critico);
        if (!critico) {
            txtOrigem.setText("");
        }
    }

    // Prepara a tela para edição: CPF e tipo não podem mudar
    public void modoEdicao(String tipo, String user, String cpf, int idade, Sexo sexo, String email,
                           String nomeCartao, String numeroCartao, String cvv, String origem) {
        lblTitulo.setText("Editar dados");
        setTitle("Editar usuário");
        cbTipo.setSelectedItem(tipo);
        cbTipo.setEnabled(false);
        txtUser.setText(user);
        txtCpf.setText(cpf);
        txtCpf.setEnabled(false);
        txtIdade.setText(String.valueOf(idade));
        cbSexo.setSelectedItem(sexo);
        txtEmail.setText(email);
        txtNomeCartao.setText(nomeCartao);
        txtNumeroCartao.setText(numeroCartao);
        txtCvv.setText(cvv);
        atualizarCampoOrigem();
        txtOrigem.setText(origem == null ? "" : origem);
        lblSenhaDica.setText("<html>Deixe a senha em branco<br>para manter a atual.</html>");
    }

    public String getTipo() {
        return (String) cbTipo.getSelectedItem();
    }

    public String getUser() {
        return txtUser.getText();
    }

    public String getCpf() {
        return txtCpf.getText();
    }

    public String getSenha() {
        return new String(txtSenha.getPassword());
    }

    public String getIdade() {
        return txtIdade.getText().trim();
    }

    public Sexo getSexo() {
        return (Sexo) cbSexo.getSelectedItem();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getNomeCartao() {
        return txtNomeCartao.getText();
    }

    public String getNumeroCartao() {
        return txtNumeroCartao.getText();
    }

    public String getCvv() {
        return txtCvv.getText().trim();
    }

    public String getOrigem() {
        return txtOrigem.getText();
    }

    public JComboBox<String> getCbTipo() {
        return cbTipo;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }


    //  EVENTOS DA TELA - chamam a IFachada
    private IFachada fachada;
    private Usuario usuarioEmEdicao;
    private Base equipe;
    private boolean salvou = false;

    // Mostra a janela (modal) e informa se o usuário foi salvo
    public boolean abrir() {
        setVisible(true);
        return salvou;
    }

    private void salvar() {
        try {
            // NumberFormatException se a idade não for um número
            int idade = Integer.parseInt(getIdade());

            String senha = getSenha();
            if (usuarioEmEdicao != null && senha.isEmpty()) {
                senha = usuarioEmEdicao.getSenha(); // mantém a senha atual
            }

            // O construtor valida todos os campos (ValidacaoException)
            Usuario usuario = criarUsuario(senha, idade);

            if (usuarioEmEdicao == null) {
                if (equipe == null) {
                    fachada.cadastrarUsuario(usuario);
                } else {
                    fachada.cadastrarUsuario(equipe, usuario);
                }
                Mensagens.sucesso(this, "Usuário cadastrado com sucesso!");
            } else {
                if (equipe == null) {
                    fachada.alterarUsuario(usuario);
                } else {
                    fachada.alterarUsuario(equipe, usuario);
                }
                Mensagens.sucesso(this, "Dados atualizados com sucesso!");
            }
            salvou = true;
            dispose();

        } catch (NumberFormatException e) {
            Mensagens.erro(this, "A idade deve ser um número inteiro. Ex.: 25");
        } catch (ValidacaoException e) {
            Mensagens.erro(this, "Dados inválidos", e.getMessage());
        } catch (UsuarioException e) {
            Mensagens.erro(this, "Não foi possível salvar", e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }

    // Cria o objeto do tipo escolhido (Usuario, Estudante ou Critico)
    private Usuario criarUsuario(String senha, int idade) throws ValidacaoException {
        String tipo = getTipo();

        if (tipo.equals(CadastroUsuarioView.TIPO_ESTUDANTE)) {
            return new Estudante(getUser(), getCpf(), senha, idade, getSexo(), getEmail(),
                    getNomeCartao(), getNumeroCartao(), getCvv());
        }
        if (tipo.equals(CadastroUsuarioView.TIPO_CRITICO)) {
            return new Critico(getUser(), getCpf(), senha, idade, getSexo(), getEmail(),
                    getNomeCartao(), getNumeroCartao(), getCvv(), getOrigem());
        }
        return new Usuario(getUser(), getCpf(), senha, idade, getSexo(), getEmail(),
                getNomeCartao(), getNumeroCartao(), getCvv());
    }
}
