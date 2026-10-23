package interfaces;
import model.Filme;

public interface GerenciaFilmes {

// Crie uma interface com os seguintes métodos:
// Incluir filme;
// Excluir filme;
// Alterar filme;


    boolean incluirFilme(Filme filme);

    boolean excluirFilme(Filme filme);

    boolean alterarFilme(Filme filme);

    int buscarFilme(String nome);
 
}
