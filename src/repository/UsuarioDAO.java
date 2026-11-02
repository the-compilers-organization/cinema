package repository;

/**
 * ROTEIRO DE IMPLEMENTACAO — repository/UsuarioDAO.java
 *
 * Este arquivo e um esqueleto para a equipe.
 * As assinaturas abaixo sao ORIENTACOES EM COMENTARIOS, nao implementacoes.
 * Preencha os atributos, construtores, metodos e imports conforme a especificacao.
 * Preserve as assinaturas acordadas com as demais camadas.
 *
 *
 * public class UsuarioDAO implements RepositorioUsuario {
 *     private final Path arquivo;
 *     public UsuarioDAO();
 *     public UsuarioDAO(Path arquivo);
 *     private static class Registro {
 *         String tipo, user, cpf, email, origem, senhaHash, senhaSalt;
 *         int idade;
 *         Sexo sexo;
 *         Registro();
 *         Registro(Usuario usuario);
 *         Usuario usuario();
 *     }
 *     public synchronized Usuario autenticar(String cpf, String senha) throws PersistenciaException;
 *     private List<Registro> registros() throws PersistenciaException;
 *     private void gravar(List<Registro> dados) throws PersistenciaException;
 *     public synchronized void salvar(Usuario usuario) throws PersistenciaException;
 *     public synchronized Usuario buscarPorCpf(String cpf) throws PersistenciaException;
 *     public synchronized List<Usuario> listar() throws PersistenciaException;
 *     public synchronized boolean atualizar(Usuario usuario) throws PersistenciaException;
 *     public synchronized boolean excluir(String cpf) throws PersistenciaException;
 * }
 *
 *
 * **Implementar:** construtor padrão aponta para `data/usuarios.json`; `Registro` é DTO JSON; reconstruir `Usuario`, `Estudante`, `Critico` conforme `tipo`; autenticar por CPF e hash/salt, sem salvar senha em texto puro; preservar hash/salt quando editar usuário sem informar senha nova; devolver `null` para busca/autenticação sem resultado e `false` para alteração/exclusão não encontrada. Usar `ArquivoJson` para I/O. `Senhas` auxilia hashes.
 */
public class UsuarioDAO {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
