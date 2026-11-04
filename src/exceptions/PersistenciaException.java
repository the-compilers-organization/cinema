package exceptions;


// Lançada quando não é possível ler ou gravar os arquivos JSON

public class PersistenciaException extends Exception {

    public PersistenciaException(String mensagem) {
        super(mensagem);
    }
}
