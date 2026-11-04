package exceptions;


// Erros de regra de negócio envolvendo usuários
// uso: CPF já cadastrado, usuário não encontrado, login incorreto

public class UsuarioException extends Exception {

    public UsuarioException(String mensagem) {
        super(mensagem);
    }
}
