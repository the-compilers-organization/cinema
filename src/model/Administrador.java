package model;

import exceptions.FilmeException;
import exceptions.PersistenciaException;
import exceptions.UsuarioException;
import exceptions.ValidacaoException;
import model.interfaces.GerenciaFilmes;
import repository.IRepositorioFilme;
import repository.IRepositorioUsuario;


// Parte 3 - Classe concreta Administrador
// Atributos: salário e ID
// Implementa os métodos abstratos de Base, exclui usuários e implementa a interface GerenciaFilmes
// O Administrador é o responsável pela PERSISTÊNCIA de usuários e filmes. Ele conversa com a camada de dados apenas pelas INTERFACES
// IRepositorioUsuario e IRepositorioFilme (não sabe que é JSON)

public class Administrador extends Base implements GerenciaFilmes {

    private int id;
    private double salario;
    private IRepositorioUsuario repositorioUsuarios;
    private IRepositorioFilme repositorioFilmes;

    public Administrador(int id, String nome, int idade, String email, double salario, IRepositorioUsuario repositorioUsuarios, IRepositorioFilme repositorioFilmes) throws ValidacaoException {
        super(nome, idade, email);
        setId(id);
        setSalario(salario);
        this.repositorioUsuarios = repositorioUsuarios;
        this.repositorioFilmes = repositorioFilmes;
    }

    @Override
    public String getCargo() {
        return "Administrador";
    }

    //  USUÁRIOS 
    // Retorna o usuário com o CPF informado ou null se não existir
    public Usuario buscarUsuario(String cpf) {
        return repositorioUsuarios.buscar(Validador.limparCpf(cpf));
    }

    @Override
    public void adicionarUsuario(Usuario usuario) throws UsuarioException, PersistenciaException {
        if (usuario == null) {
            throw new UsuarioException("Usuário inválido.");
        }
        if (buscarUsuario(usuario.getCpf()) != null) {
            throw new UsuarioException("Já existe um usuário cadastrado com o CPF " + usuario.getCpf() + ".");
        }
        repositorioUsuarios.inserir(usuario);
    }

    // Regra de negócio: o CPF não pode ser alterado (ele identifica o usuário)
    @Override
    public void alterarUsuario(Usuario usuarioAtualizado) throws UsuarioException, PersistenciaException {
        if (usuarioAtualizado == null) {
            throw new UsuarioException("Usuário inválido.");
        }
        Usuario atual = buscarUsuario(usuarioAtualizado.getCpf());
        if (atual == null) {
            throw new UsuarioException("Usuário com CPF " + usuarioAtualizado.getCpf() + " não encontrado.");
        }
        // Atualiza o próprio objeto: quem já tem a referência (ex.: usuário logado) vê os dados novos
        atual.copiarDados(usuarioAtualizado);
        repositorioUsuarios.atualizar(atual);
    }

    // Método exclusivo do Administrador
    public void excluirUsuario(String cpf) throws UsuarioException, PersistenciaException {
        Usuario usuario = buscarUsuario(cpf);
        if (usuario == null) {
            throw new UsuarioException("Usuário com CPF " + cpf + " não encontrado.");
        }
        repositorioUsuarios.remover(usuario.getCpf());
    }

    // FILMES (interface GerenciaFilmes) 

    public Filme buscarFilme(String nome) {
        return repositorioFilmes.buscar(nome);
    }

    @Override
    public void incluirFilme(Filme filme) throws FilmeException, PersistenciaException {
        if (filme == null) {
            throw new FilmeException("Filme inválido.");
        }
        if (buscarFilme(filme.getNome()) != null) {
            throw new FilmeException("O filme \"" + filme.getNome() + "\" já está cadastrado.");
        }
        repositorioFilmes.inserir(filme);
    }

    // Regra de negócio: o nome do filme não pode ser alterado
    @Override
    public void alterarFilme(Filme filmeAtualizado) throws FilmeException, PersistenciaException {
        if (filmeAtualizado == null) {
            throw new FilmeException("Filme inválido.");
        }
        Filme atual = buscarFilme(filmeAtualizado.getNome());
        if (atual == null) {
            throw new FilmeException("Filme \"" + filmeAtualizado.getNome() + "\" não encontrado.");
        }
        // Atualiza o próprio objeto: as sessões que exibem o filme veem os novos dados
        atual.copiarDados(filmeAtualizado);
        repositorioFilmes.atualizar(atual);
    }

    @Override
    public void excluirFilme(String nome) throws FilmeException, PersistenciaException {
        Filme filme = buscarFilme(nome);
        if (filme == null) {
            throw new FilmeException("Filme \"" + nome + "\" não encontrado.");
        }
        repositorioFilmes.remover(filme.getNome());
    }

    //  GETTERS E SETTERS 
    public int getId() {
        return id;
    }

    public void setId(int id) throws ValidacaoException {
        if (id <= 0) {
            throw new ValidacaoException("O ID do administrador deve ser positivo.");
        }
        this.id = id;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) throws ValidacaoException {
        if (salario < 0) {
            throw new ValidacaoException("O salário não pode ser negativo.");
        }
        this.salario = salario;
    }
}
