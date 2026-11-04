package model.interfaces;

import exceptions.FilmeException;
import exceptions.PersistenciaException;
import model.Filme;

/**
 * Parte 3 - Interface de gerência de filmes.
 * Implementada por Funcionario e Administrador.
 */
public interface GerenciaFilmes {

    void incluirFilme(Filme filme) throws FilmeException, PersistenciaException;

<<<<<<< Updated upstream
    int buscarFilme(String nome);
    boolean incluirFilme(Filme filme);
    boolean excluirFilme(Filme filmeExcluido);
    boolean alterarFilme(Filme filmeAtualizado);

}
=======
    void excluirFilme(String nome) throws FilmeException, PersistenciaException;

    void alterarFilme(Filme filmeAtualizado) throws FilmeException, PersistenciaException;
}
>>>>>>> Stashed changes
