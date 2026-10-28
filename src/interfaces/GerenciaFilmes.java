package interfaces;
import model.Filme;

public interface GerenciaFilmes {

// Crie uma interface com os seguintes métodos:
// Incluir filme;
// Excluir filme;
// Alterar filme;

    int buscarFilme(String nome);

    boolean incluirFilme(Filme filme);

    boolean excluirFilme(Filme filmeExcluido);

    boolean alterarFilme(String nome, Filme filmeAtualizado);

    
}
