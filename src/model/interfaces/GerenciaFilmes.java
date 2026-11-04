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

    void excluirFilme(String nome) throws FilmeException, PersistenciaException;

    void alterarFilme(Filme filmeAtualizado) throws FilmeException, PersistenciaException;
}
