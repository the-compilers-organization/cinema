package controller;

import java.util.ArrayList;

import exceptions.PersistenciaException;
import exceptions.UsuarioException;
import exceptions.ValidacaoException;
import model.Administrador;
import model.Base;
import model.Funcionario;
import model.Usuario;
import model.Validador;
import repository.IRepositorioUsuario;


// Camada de NEGÓCIO (controller) - regras envolvendo usuários (login, cadastro, alteração, exclusão)
// Acessa os dados somente pela interface IRepositorioUsuario

public class UsuarioController {

    // Contas fixas da equipe do cinema
    public static final String EMAIL_ADMIN = "admin@cinema.com";
    public static final String SENHA_ADMIN = "admin123";
    public static final String EMAIL_FUNCIONARIO = "func@cinema.com";
    public static final String SENHA_FUNCIONARIO = "func123";

    private IRepositorioUsuario repositorio;
    private Administrador administrador;
    private Funcionario funcionario;

    public UsuarioController(IRepositorioUsuario repositorio, Administrador administrador, Funcionario funcionario) {
        this.repositorio = repositorio;
        this.administrador = administrador;
        this.funcionario = funcionario;
    }

    public Usuario entrarCliente(String cpf, String senha) throws ValidacaoException, UsuarioException {
        Validador.textoObrigatorio(cpf, "CPF");
        Validador.textoObrigatorio(senha, "Senha");

        Usuario usuario = repositorio.buscar(Validador.limparCpf(cpf));
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            throw new UsuarioException("CPF ou senha incorretos.");
        }
        return usuario;
    }

    public Base entrarEquipe(String email, String senha) throws ValidacaoException, UsuarioException {
        Validador.textoObrigatorio(email, "E-mail");
        Validador.textoObrigatorio(senha, "Senha");

        if (email.trim().equalsIgnoreCase(EMAIL_ADMIN) && senha.equals(SENHA_ADMIN)) {
            return administrador;
        }
        if (email.trim().equalsIgnoreCase(EMAIL_FUNCIONARIO) && senha.equals(SENHA_FUNCIONARIO)) {
            return funcionario;
        }
        throw new UsuarioException("E-mail ou senha da equipe incorretos.");
    }

    // "responsavel" é o Administrador ou o Funcionário (polimorfismo com a classe abstrata Base)
    public void cadastrar(Base responsavel, Usuario usuario) throws UsuarioException, PersistenciaException {
        responsavel.adicionarUsuario(usuario);
    }

    public void alterar(Base responsavel, Usuario usuario) throws UsuarioException, PersistenciaException {
        responsavel.alterarUsuario(usuario);
    }

    public void excluir(Base equipe, String cpf, boolean possuiCompras) throws UsuarioException, PersistenciaException {
        // Somente o Administrador possui o método excluirUsuario
        if (!(equipe instanceof Administrador)) {
            throw new UsuarioException("Somente o administrador pode excluir usuários.");
        }
        if (possuiCompras) {
            throw new UsuarioException("Este usuário possui compras. Cancele as compras antes de excluí-lo.");
        }
        ((Administrador) equipe).excluirUsuario(cpf);
    }

    public ArrayList<Usuario> listar() {
        return repositorio.listar();
    }

    public Administrador getAdministrador() {
        return administrador;
    }
}
