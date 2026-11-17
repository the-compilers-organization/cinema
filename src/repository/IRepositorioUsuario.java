package repository;

import java.util.ArrayList;

import exceptions.PersistenciaException;
import model.Usuario;


// Interface da camada de DADOS para usuario
// A camada de negocio so conhece esta interface, e nao a classe que grava o arquivo

public interface IRepositorioUsuario {

    void inserir(Usuario usuario) throws PersistenciaException;

    Usuario buscar(String cpf);

    ArrayList<Usuario> listar();

    void atualizar(Usuario usuario) throws PersistenciaException;

    void remover(String cpf) throws PersistenciaException;
}
