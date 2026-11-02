package repository;


/**
 * ROTEIRO DE IMPLEMENTAÇÃO — FilmeDAO.java
 *
 * OBJETIVO: Implementar operações CRUD em filmes.json. Preservar campos nome, sinopse, duracao, valor, emCartaz, notas e criticas. Evitar IllegalArgumentException e definir tratamento consistente para filme duplicado.
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
 * public class FilmeDAO implements RepositorioFilme {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * private final Path arquivo;
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. public FilmeDAO();
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 2. public FilmeDAO(Path arquivo);
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 3. private List<Registro> registros() throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 4. public synchronized void salvar(Filme filme) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 5. public synchronized Filme buscarPorNome(String nome) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 6. public synchronized List<Filme> listar() throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 7. public synchronized boolean atualizar(Filme filme) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 8. public synchronized boolean excluir(String nome) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 */

public class FilmeDAO {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
