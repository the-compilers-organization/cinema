package repository;

/**
 * ROTEIRO DE IMPLEMENTACAO — repository/Senhas.java
 *
 * Este arquivo e um esqueleto para a equipe.
 * As assinaturas abaixo sao ORIENTACOES EM COMENTARIOS, nao implementacoes.
 * Preencha os atributos, construtores, metodos e imports conforme a especificacao.
 * Preserve as assinaturas acordadas com as demais camadas.
 *
 *
 * final class Senhas {
 *     private Senhas() {}
 *     static String gerarSalt();
 *     static String gerarHash(String senha, String salt);
 *     static boolean verificar(String senha, String salt, String esperado);
 * }
 *
 *
 * **Implementar:** salt aleatório (`SecureRandom`), derivação PBKDF2, Base64 e comparação do hash; não armazenar senha original. Manter parâmetros de PBKDF2 consistentes entre cadastro e login.
 */
public class Senhas {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
