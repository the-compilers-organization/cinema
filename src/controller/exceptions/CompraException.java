package controller.exceptions;

/**
 * ROTEIRO DE IMPLEMENTACAO - CompraException.java
 *
 * OBJETIVO: Falhas em realizar, alterar, cancelar ou registrar uma compra.
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
 * public class CompraException extends Exception {
 *     public CompraException(String mensagem) {
 *         super(mensagem);
 *     }
 * }
 *
 * ONDE LANCAR: CompraController e Facade, nos pontos que identificam falhas de compra.
 * Exemplo de uso no metodo de negocio (NAO implementar aqui):
 *     public void validar(...) throws CompraException {
 *         if (condicaoInvalida) {
 *             CompraException ex = new CompraException("Mensagem do erro");
 *             throw ex;
 *         }
 *     }
 *
 * ONDE TRATAR: no controller que recebe o evento da interface grafica.
 *     try {
 *         // chamar a operacao que declara throws
 *     } catch (CompraException ex) {
 *         // JOptionPane.showMessageDialog(view, ex.getMessage());
 *     }
 *
 * IMPORTANTE: manter as regras de negocio fora desta classe.
 * Este arquivo e somente um roteiro: nao contem implementacao funcional.
 */
public class CompraException {
    // TODO: implementar a classe conforme as assinaturas comentadas acima.
}
