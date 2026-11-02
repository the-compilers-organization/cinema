package repository.interfaces;

/**
 * ROTEIRO DE IMPLEMENTAÇÃO — RepositorioFilme.java
 *
 * OBJETIVO: Declarar contrato do DAO de filmes, com os tipos e throws exatamente compatíveis com FilmeDAO.
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
 * public interface RepositorioFilme {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * (Sem atributos próprios declarados neste arquivo, ou definidos por implementação.)
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. void salvar(Filme filme) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 2. Filme buscarPorNome(String nome) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 3. List<Filme> listar() throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 4. boolean atualizar(Filme filme) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 5. boolean excluir(String nome) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 */

public interface RepositorioFilme {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
