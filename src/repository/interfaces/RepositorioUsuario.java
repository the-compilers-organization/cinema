package repository.interfaces;

/**
 * ROTEIRO DE IMPLEMENTAÇÃO — RepositorioUsuario.java
 *
 * OBJETIVO: Declarar contrato do DAO de usuários; declarar throws PersistenciaException em operações de arquivo. A implementação concreta ficará em UsuarioDAO.
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
 * public interface RepositorioUsuario {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * (Sem atributos próprios declarados neste arquivo, ou definidos por implementação.)
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. void salvar(Usuario usuario) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 2. Usuario buscarPorCpf(String cpf) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 3. List<Usuario> listar() throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 4. boolean atualizar(Usuario usuario) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 5. boolean excluir(String cpf) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 */

public interface RepositorioUsuario {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
