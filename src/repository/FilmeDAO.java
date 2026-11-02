package repository;

/**
 * ROTEIRO DE IMPLEMENTACAO — repository/FilmeDAO.java
 *
 * Este arquivo e um esqueleto para a equipe.
 * As assinaturas abaixo sao ORIENTACOES EM COMENTARIOS, nao implementacoes.
 * Preencha os atributos, construtores, metodos e imports conforme a especificacao.
 * Preserve as assinaturas acordadas com as demais camadas.
 *
 *
 * public class FilmeDAO implements RepositorioFilme {
 *     private final Path arquivo;
 *     public FilmeDAO();
 *     public FilmeDAO(Path arquivo);
 *     private static class Registro {
 *         // nome, sinopse, duracao, valor, emCartaz, notas, criticas
 *         Registro();
 *         Registro(Filme filme);
 *         Filme filme();
 *     }
 *     private List<Registro> registros() throws PersistenciaException;
 *     public synchronized void salvar(Filme filme) throws PersistenciaException;
 *     public synchronized Filme buscarPorNome(String nome) throws PersistenciaException;
 *     public synchronized List<Filme> listar() throws PersistenciaException;
 *     public synchronized boolean atualizar(Filme filme) throws PersistenciaException;
 *     public synchronized boolean excluir(String nome) throws PersistenciaException;
 * }
 *
 *
 * **Implementar:** caminho `data/filmes.json`; converter `Filme ↔ Registro`, preservando notas e críticas; salvar, buscar por nome, listar, atualizar e excluir; devolver `false` se registro não encontrado. O JSON acordado contém `nome`, `sinopse`, `duracao`, `valor`, `emCartaz`, `notas`, `criticas`. Não colocar filmes iniciais fixos dentro do DAO; o arquivo JSON é a fonte de dados.
 */
public class FilmeDAO {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
