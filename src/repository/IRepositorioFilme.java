package repository;

import java.util.ArrayList;

import exceptions.PersistenciaException;
import model.Filme;


// Interface da camada de DADOS para filmes

public interface IRepositorioFilme {

    void inserir(Filme filme) throws PersistenciaException;

    Filme buscar(String nome);

    ArrayList<Filme> listar();

    void atualizar(Filme filme) throws PersistenciaException;

    void remover(String nome) throws PersistenciaException;
}
