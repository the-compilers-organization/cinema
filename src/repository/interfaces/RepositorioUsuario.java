package repository.interfaces;

/**
 * ROTEIRO DE IMPLEMENTACAO — repository/interfaces/RepositorioUsuario.java
 *
 * Este arquivo e um esqueleto para a equipe.
 * As assinaturas abaixo sao ORIENTACOES EM COMENTARIOS, nao implementacoes.
 * Preencha os atributos, construtores, metodos e imports conforme a especificacao.
 * Preserve as assinaturas acordadas com as demais camadas.
 *
 *
 * package repository.interfaces;
 * import java.util.List;
 * import model.Usuario;
 * import repository.exceptions.PersistenciaException;
 * public interface RepositorioUsuario {
 *     void salvar(Usuario usuario) throws PersistenciaException;
 *     Usuario buscarPorCpf(String cpf) throws PersistenciaException;
 *     List<Usuario> listar() throws PersistenciaException;
 *     boolean atualizar(Usuario usuario) throws PersistenciaException;
 *     boolean excluir(String cpf) throws PersistenciaException;
 * }
 *
 *
 * **Observação de integração:** a versão atual possui `autenticar(String cpf, String senha)` apenas em `UsuarioDAO`, e a `Facade` faz `instanceof UsuarioDAO`. **Melhoria recomendada, a combinar antes de codificar:** adicionar `Usuario autenticar(String cpf, String senha) throws PersistenciaException;` à interface e chamar `usuarios.autenticar(...)` na `Facade`. Isso elimina dependência da implementação concreta.
 */
public interface RepositorioUsuario {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
