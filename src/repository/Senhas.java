package repository;

/**
 * ROTEIRO DE IMPLEMENTAÇÃO — Senhas.java
 *
 * OBJETIVO: Implementar geração de salt e hash PBKDF2, e verificação segura. Não armazenar senhas em texto puro. Preservar o formato já utilizado por UsuarioDAO.
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
 * final class Senhas {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * (Sem atributos próprios declarados neste arquivo, ou definidos por implementação.)
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. private Senhas();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 2. static String gerarSalt();
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 3. static String gerarHash(String senha,String salt);
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 4. catch(Exception e);
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 5. static boolean verificar(String senha,String salt,String esperado);
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 6. catch(RuntimeException e);
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 */

public class Senhas {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
