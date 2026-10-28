package model;

// A classe base é uma classe abstrata que deve possuir as seguintes
// características:
// ○ Atributos: nome, idade, email.
// ○ Métodos: getters e setters para os atributos;
// ○ Métodos abstratos: Adicionar usuário e alterar usuário;

public abstract class Base {
    // Atributos: nome, idade, email.
    private String nome;
    private int idade;
    private String email;

    public Base(String nome, int idade, String email){
        this.nome = nome;
        this.idade = idade;
        this.email = email;

    }


    public String getNome() {
        return nome;
    }




    public void setNome(String nome) {
        this.nome = nome;
    }




    public int getIdade() {
        return idade;
    }




    public void setIdade(int idade) {
        this.idade = idade;
    }




    public String getEmail() {
        return email;
    }




    public void setEmail(String email) {
        this.email = email;
    }




    // ○ Métodos abstratos: Adicionar usuário e alterar usuário;
    public abstract int buscarUsuario(String cpf);

    public abstract boolean adicionarUsuario(Usuario usuario);

    public abstract boolean alterarUsuario(String cpf, Usuario usuarioAtualizado);
        
}
