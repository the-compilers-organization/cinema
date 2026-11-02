package repository;

/**
 * ROTEIRO DE IMPLEMENTAÇÃO — ArquivoJson.java
 *
 * OBJETIVO: Centralizar leitura e gravação JSON com Gson. Ler arquivo ausente/vazio como lista vazia; capturar IOException/JsonParseException e lançar PersistenciaException; gravar em arquivo temporário e substituir o original; limpar temporário no finally.
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
 * final class ArquivoJson {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * static final Gson GSON;
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. private ArquivoJson();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 2. static <T> List<T> ler(Path caminho, Type tipo) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 3. static void gravar(Path caminho, Object dados) throws PersistenciaException;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 */

public class ArquivoJson {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
