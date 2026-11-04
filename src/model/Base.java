package model;

import exceptions.PersistenciaException;
import exceptions.UsuarioException;
import exceptions.ValidacaoException;


// Parte 3 - Classe abstrata Base.
// Atributos: nome, idade e email.
// Métodos abstratos: adicionar usuário e alterar usuário.
// Funcionario e Administrador herdam desta classe.

public abstract class Base {

    private String nome;
    private int idade;
    private String email;

    public Base(String nome, int idade, String email) throws ValidacaoException {
        setNome(nome);
        setIdade(idade);
        setEmail(email);
    }

    //  métodos abstratos 
    public abstract void adicionarUsuario(Usuario usuario)
            throws UsuarioException, PersistenciaException;

    public abstract void alterarUsuario(Usuario usuarioAtualizado)
            throws UsuarioException, PersistenciaException;

    // Usado na tela para mostrar o cargo de quem está logado
    public abstract String getCargo();

    //  getters e setters (com validação) 
    public String getNome() {
        return nome;
    }

<<<<<<< Updated upstream
    public void setNome(String nome) {
        this.nome = nome;
=======
    public void setNome(String nome) throws ValidacaoException {
        Validador.textoObrigatorio(nome, "Nome");
        this.nome = nome.trim();
>>>>>>> Stashed changes
    }

    public int getIdade() {
        return idade;
    }

<<<<<<< Updated upstream
    public void setIdade(int idade) {
=======
    public void setIdade(int idade) throws ValidacaoException {
        Validador.idade(idade);
>>>>>>> Stashed changes
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

<<<<<<< Updated upstream
    public void setEmail(String email) {
        this.email = email;
=======
    public void setEmail(String email) throws ValidacaoException {
        Validador.email(email);
        this.email = email.trim();
>>>>>>> Stashed changes
    }
}
