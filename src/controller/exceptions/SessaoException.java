package controller.exceptions;

/**
 * ROTEIRO DE IMPLEMENTACAO - SessaoException.java
 *
 * OBJETIVO: Sessao inexistente, invalida ou sem filme associado.
 *
 * PADRAO DO PROFESSOR:
 * 1. Fazer a classe herdar de Exception (excecao verificada).
 * 2. Criar um construtor que receba String mensagem.
 * 3. Encaminhar a mensagem para a superclasse com super(mensagem).
 * 4. Nao imprimir mensagens dentro da classe de excecao.
 * 5. Nao acrescentar construtor com Throwable causa: nao e necessario
 *    para o padrao simplificado adotado neste trabalho.
 *
 * ASSINATURAS A IMPLEMENTAR (apenas referencia):
 *
 * public class SessaoException extends Exception {
 *     public SessaoException(String mensagem) {
 *         super(mensagem);
 *     }
 * }
 *
 * ONDE LANCAR: SessaoController, Facade ou outro controller que valide a sessao.
 * Exemplo de uso no metodo de negocio (NAO implementar aqui):
 *     public void validar(...) throws SessaoException {
 *         if (condicaoInvalida) {
 *             SessaoException ex = new SessaoException("Mensagem do erro");
 *             throw ex;
 *         }
 *     }
 *
 * ONDE TRATAR: no controller que recebe o evento da interface grafica.
 *     try {
 *         // chamar a operacao que declara throws
 *     } catch (SessaoException ex) {
 *         // JOptionPane.showMessageDialog(view, ex.getMessage());
 *     }
 *
 * IMPORTANTE: manter as regras de negocio fora desta classe.
 * Este arquivo e somente um roteiro: nao contem implementacao funcional.
 */
public class SessaoException {
    // TODO: implementar a classe conforme as assinaturas comentadas acima.
}
