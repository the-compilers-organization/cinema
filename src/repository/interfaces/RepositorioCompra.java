package repository.interfaces;


/**
 * ROTEIRO DE IMPLEMENTAÇÃO — RepositorioCompra.java
 *
 * OBJETIVO: Declarar contrato de persistência de compras, compatível com CompraDAO.
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
 * public interface RepositorioCompra {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * (Sem atributos próprios declarados neste arquivo, ou definidos por implementação.)
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. List<CompraRegistro> listar() throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 2. void salvarTodas(List<CompraRegistro> compras) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 */

public interface RepositorioCompra {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
