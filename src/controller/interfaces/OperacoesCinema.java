package controller.interfaces;

/**
 * ROTEIRO DE IMPLEMENTACAO — controller/interfaces/OperacoesCinema.java
 *
 * Este arquivo e um esqueleto para a equipe.
 * As assinaturas abaixo sao ORIENTACOES EM COMENTARIOS, nao implementacoes.
 * Preencha os atributos, construtores, metodos e imports conforme a especificacao.
 * Preserve as assinaturas acordadas com as demais camadas.
 *
 *
 * public interface OperacoesCinema {
 *     void cadastrarUsuario(Usuario usuario) throws UsuarioException, ValidacaoException, PersistenciaException;
 *     Usuario buscarUsuario(String cpf) throws PersistenciaException;
 *     Usuario autenticarUsuario(String cpf, String senha) throws PersistenciaException;
 *     List<Usuario> listarUsuarios() throws PersistenciaException;
 *     boolean atualizarUsuario(Usuario usuario) throws UsuarioException, PersistenciaException;
 *     boolean excluirUsuario(String cpf) throws UsuarioException, PersistenciaException;
 *
 *     void cadastrarFilme(Filme filme) throws FilmeException, ValidacaoException, PersistenciaException;
 *     Filme buscarFilme(String nome) throws PersistenciaException;
 *     List<Filme> listarFilmes() throws PersistenciaException;
 *     boolean atualizarFilme(Filme filme) throws FilmeException, PersistenciaException;
 *     boolean excluirFilme(String nome) throws FilmeException, PersistenciaException;
 *     List<Filme> filmesEmExibicao(Filme[] catalogo);
 *     List<Sessao> sessoesDisponiveis(Sala[] salas);
 *     void avaliarFilme(Critico critico, Filme filme, double nota, String comentario)
 *         throws ValidacaoException, PersistenciaException;
 *
 *     List<CompraRegistro> listarComprasPersistidas() throws PersistenciaException;
 *     void salvarCompras(List<CompraRegistro> compras) throws PersistenciaException;
 *     List<CompraRegistro> converterCompras(List<Compra> compras, List<Boolean> perfis,
 *         List<CupomPromocional> cupons, Sala[] salas);
 *     void validarVenda(Sessao sessao, int linha, int coluna)
 *         throws VendasException, ValidacaoException;
 * }
 *
 *
 * **Imports necessários:** `java.util.List`, `model.*`, `model.enums.CupomPromocional`, `repository.CompraRegistro`, `repository.exceptions.PersistenciaException`, `controller.exceptions.*`.
 *
 * **Regra:** a interface declara o contrato; não instancia DAO, não acessa JSON, não abre Swing. Alterar uma assinatura aqui exige ajustar `Facade` e chamadores.
 */
public interface OperacoesCinema {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
