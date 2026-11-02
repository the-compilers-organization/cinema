package repository;

/**
 * ROTEIRO DE IMPLEMENTACAO — repository/ArquivoJson.java
 *
 * Este arquivo e um esqueleto para a equipe.
 * As assinaturas abaixo sao ORIENTACOES EM COMENTARIOS, nao implementacoes.
 * Preencha os atributos, construtores, metodos e imports conforme a especificacao.
 * Preserve as assinaturas acordadas com as demais camadas.
 *
 * **Classe utilitária interna do pacote**, não `public`:
 *
 *
 * final class ArquivoJson {
 *     static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
 *     private ArquivoJson() {}
 *     static <T> List<T> ler(Path caminho, Type tipo) throws PersistenciaException;
 *     static void gravar(Path caminho, Object dados) throws PersistenciaException;
 * }
 *
 *
 * **Implementar:** `ler`: se arquivo não existir ou estiver vazio, devolver lista vazia; caso contrário ler UTF-8 com `GSON.fromJson`. Capturar `IOException | JsonParseException` e lançar `PersistenciaException`. `gravar`: criar diretório, escrever UTF-8 em arquivo temporário e mover para destino (preferencialmente movimento atômico com alternativa). Limpar temporário em `finally`. **Essas assinaturas com `;` são um roteiro, não um arquivo Java compilável sem os corpos.**
 */
public class ArquivoJson {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
