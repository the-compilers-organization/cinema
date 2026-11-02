package repository;

/**
 * ROTEIRO DE IMPLEMENTAÇÃO — UsuarioDAO.java
 *
 * OBJETIVO: Implementar cadastro, busca, autenticação, listagem, atualização e exclusão de usuários em usuarios.json. Preservar hash/salt ao atualizar dados sem nova senha. Duplicidade de CPF deve usar exceção personalizada, não IllegalArgumentException.
 *
 * ORIENTAÇÕES GERAIS:
 * - Este arquivo é um roteiro, não uma implementação funcional.
 * - As assinaturas abaixo são comentários; o colega deve escrever o código.
 * - Não usar IllegalArgumentException; seguir as exceções personalizadas do projeto.
 * - Seguir o exemplo do professor: extends Exception, super(mensagem),
 *   throws na assinatura, throw na origem e try/catch no local apropriado.
 * - Não substituir a versão funcional antes de concluir e testar a implementação.
 */

/* DECLARAÇÃO DA CLASSE/INTERFACE:
 * public class UsuarioDAO implements RepositorioUsuario {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * private final Path arquivo;
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. public UsuarioDAO();
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 2. public UsuarioDAO(Path arquivo);
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 3. public synchronized Usuario autenticar(String cpf, String senha) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 4. private List<Registro> registros() throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 5. private void gravar(List<Registro> dados) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 6. public synchronized void salvar(Usuario usuario) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 7. public synchronized Usuario buscarPorCpf(String cpf) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 8. public synchronized List<Usuario> listar() throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 9. public synchronized boolean atualizar(Usuario usuario) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 10. private String datosCpf(Registro r);
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 11. public synchronized boolean excluir(String cpf) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 */

public class UsuarioDAO {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
