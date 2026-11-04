package repository;

import java.util.ArrayList;

import exceptions.PersistenciaException;
import model.Usuario;


// Interface da camada de DADOS para usuários
// A camada de negócio só conhece esta interface, e não a classe que grava o arquivo. Assim, trocar JSON por banco de dados
// exigiria apenas uma nova classe que implemente esta interface

public interface IRepositorioUsuario {

    void inserir(Usuario usuario) throws PersistenciaException;

    Usuario buscar(String cpf);

    ArrayList<Usuario> listar();

    void atualizar(Usuario usuario) throws PersistenciaException;

    void remover(String cpf) throws PersistenciaException;
}
