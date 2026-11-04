package exceptions;


// Erros de regra de negócio envolvendo compras
// uso: compra sem nenhum bilhete, item inexistente na compra

public class CompraException extends Exception {

    public CompraException(String mensagem) {
        super(mensagem);
    }
}
