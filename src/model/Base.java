package model;



public abstract class Base {
    // Atributos: nome, idade, email.


    // ○ Métodos: getters e setters para os atributos;


    // ○ Métodos abstratos: Adicionar usuário e alterar usuário;
    public abstract boolean adicionarUsuario(Usuario usuario);

    public abstract boolean alterarUsuario(Usuario usuarioAtualizado);

    public abstract int buscarUsuario(String cpf);
        
}
