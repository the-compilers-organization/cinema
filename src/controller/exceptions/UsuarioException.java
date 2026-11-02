package controller.exceptions;

/**
 * ROTEIRO DE IMPLEMENTACAO - UsuarioException.java
 *
 * OBJETIVO: Falhas de cadastro, autenticacao, alteracao ou exclusao de usuarios.
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
 * public class UsuarioException extends Exception {
 *     public UsuarioException(String mensagem) {
 *         super(mensagem);
 *     }
 * }
 *
 * ONDE LANCAR: UsuarioController, MenuController ou Facade, conforme a regra.
 * Exemplo de uso no metodo de negocio (NAO implementar aqui):
 *     public void validar(...) throws UsuarioException {
 *         if (condicaoInvalida) {
 *             UsuarioException ex = new UsuarioException("Mensagem do erro");
 *             throw ex;
 *         }
 *     }
 *
 * ONDE TRATAR: no controller que recebe o evento da interface grafica.
 *     try {
 *         // chamar a operacao que declara throws
 *     } catch (UsuarioException ex) {
 *         // JOptionPane.showMessageDialog(view, ex.getMessage());
 *     }
 *
 * IMPORTANTE: manter as regras de negocio fora desta classe.
 * Este arquivo e somente um roteiro: nao contem implementacao funcional.
 */
public class UsuarioException {
    // TODO: implementar a classe conforme as assinaturas comentadas acima.
}
