package exceptions;


// Lançada quando um dado digitado é inválido
// uso: campo vazio, CPF com quantidade errada de dígitos, idade negativa

public class ValidacaoException extends Exception {

    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
