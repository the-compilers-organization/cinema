package exceptions;


// Erros de regra de negócio envolvendo filmes
// uso: filme já cadastrado, filme não encontrado, filme em uso na programação

public class FilmeException extends Exception {

    public FilmeException(String mensagem) {
        super(mensagem);
    }
}
