package model;

import exceptions.FilmeException;
import exceptions.PersistenciaException;
import exceptions.UsuarioException;
import exceptions.ValidacaoException;
import model.interfaces.GerenciaFilmes;


// Parte 3 - Classe concreta Funcionario
// Atributo: salário
// Implementa os métodos abstratos de Base e a interface GerenciaFilmes
// Como o Administrador é o responsável pela persistência dos dados, o Funcionário repassa (delega) as operações para ele
// Diferença importante: o Funcionário NÃO pode excluir usuários

public class Funcionario extends Base implements GerenciaFilmes {

    private double salario;
    private Administrador administrador;

    public Funcionario(String nome, int idade, String email, double salario, Administrador administrador) throws ValidacaoException {
        super(nome, idade, email);
        setSalario(salario);
        this.administrador = administrador;
    }

    @Override
    public String getCargo() {
        return "Funcionário";
    }

    //  métodos abstratos de Base 
    @Override
    public void adicionarUsuario(Usuario usuario) throws UsuarioException, PersistenciaException {
        administrador.adicionarUsuario(usuario);
    }

    @Override
    public void alterarUsuario(Usuario usuarioAtualizado) throws UsuarioException, PersistenciaException {
        administrador.alterarUsuario(usuarioAtualizado);
    }

    //  interface GerenciaFilmes 
    @Override
    public void incluirFilme(Filme filme) throws FilmeException, PersistenciaException {
        administrador.incluirFilme(filme);
    }

    @Override
    public void excluirFilme(String nome) throws FilmeException, PersistenciaException {
        administrador.excluirFilme(nome);
    }

    @Override
    public void alterarFilme(Filme filmeAtualizado) throws FilmeException, PersistenciaException {
        administrador.alterarFilme(filmeAtualizado);
    }

    //  getters e setters 
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) throws ValidacaoException {
        if (salario < 0) {
            throw new ValidacaoException("O salário não pode ser negativo.");
        }
        this.salario = salario;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
