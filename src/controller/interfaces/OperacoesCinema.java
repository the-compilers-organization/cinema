package controller.interfaces;

/**
 * ROTEIRO DE IMPLEMENTAÇÃO — OperacoesCinema.java
 *
 * OBJETIVO: Contrato entre Controllers e Facade. Manter todas as assinaturas e exceções checked; nenhuma implementação aqui.
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
 * public interface OperacoesCinema {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * (Sem atributos próprios declarados neste arquivo, ou definidos por implementação.)
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. void cadastrarUsuario(Usuario usuario) throws UsuarioException, ValidacaoException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 2. Usuario buscarUsuario(String cpf) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 3. Usuario autenticarUsuario(String cpf, String senha) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 4. List<Usuario> listarUsuarios() throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 5. boolean atualizarUsuario(Usuario usuario) throws UsuarioException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 6. boolean excluirUsuario(String cpf) throws UsuarioException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 7. void cadastrarFilme(Filme filme) throws FilmeException, ValidacaoException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 8. Filme buscarFilme(String nome) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 9. List<Filme> listarFilmes() throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 10. boolean atualizarFilme(Filme filme) throws FilmeException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 11. boolean excluirFilme(String nome) throws FilmeException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 12. List<Filme> filmesEmExibicao(Filme[] catalogo);;
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 13. List<Sessao> sessoesDisponiveis(Sala[] salas);;
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 14. void avaliarFilme(Critico critico, Filme filme, double nota, String comentario) throws ValidacaoException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 15. List<CompraRegistro> listarComprasPersistidas() throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 16. void salvarCompras(List<CompraRegistro> compras) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 17. List<CompraRegistro> converterCompras(List<Compra> compras, List<Boolean> perfis, List<CupomPromocional> cupons, Sala[] salas) throws CompraException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 18. void validarVenda(Sessao sessao, int linha, int coluna) throws VendasException, ValidacaoException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 19. void validarVendaEmEdicao(Sessao sessao, int linha, int coluna, boolean cadeiraDaPropriaCompra) throws VendasException, ValidacaoException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 20. void inicializarCompras(Sala[] salas) throws PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 21. boolean podeRegistrarCompra();;
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 22. boolean registrarCompra(Compra compra, boolean perfil, CupomPromocional cupom) throws CompraException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 23. int getQtdCompras();;
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 24. Compra getCompra(int indice);;
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 25. boolean getPerfilCompra(int indice);;
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 26. CupomPromocional getCupomCompra(int indice);;
 *    ORIENTAÇÃO: Implementar conforme o contrato e as regras existentes.
 *
 * 27. boolean atualizarCompra(int indice, Compra nova, boolean perfil, CupomPromocional cupom) throws CompraException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 *
 * 28. boolean excluirCompra(int indice) throws CompraException, PersistenciaException;;
 *    ORIENTAÇÃO: Declarar e propagar as exceções indicadas; tratar no ponto de interação quando aplicável.
 */
public interface OperacoesCinema {
    // TODO: implementar conforme as assinaturas e orientacoes acima.
}
