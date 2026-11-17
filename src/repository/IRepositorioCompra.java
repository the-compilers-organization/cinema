package repository;

import java.util.ArrayList;

import exceptions.PersistenciaException;
import model.Compra;


// Interface da camada de DADOS para compras

public interface IRepositorioCompra {

    void inserir(Compra compra) throws PersistenciaException;

    ArrayList<Compra> listar();

    ArrayList<Compra> listarPorCpf(String cpf);

    void atualizar(Compra compra) throws PersistenciaException;

    void remover(Compra compra) throws PersistenciaException;

    int gerarProximoId();
}
