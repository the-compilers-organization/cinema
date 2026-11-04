package controller;

import java.util.ArrayList;

import exceptions.CompraException;
import exceptions.FilmeException;
import exceptions.PersistenciaException;
import exceptions.UsuarioException;
import exceptions.ValidacaoException;
import exceptions.VendasException;
import model.Base;
import model.Compra;
import model.Critico;
import model.Filme;
import model.Sala;
import model.Sessao;
import model.Usuario;
import model.enums.CupomPromocional;
import model.enums.Produtos;
import model.interfaces.GerenciaFilmes;


// PADRÃO DE PROJETO FACHADA - interface IFachada.
// É o "contrato" entre a camada de UI (telas) e a camada de NEGÓCIO.
// As telas só conhecem esta interface: não sabem que existem UsuarioController, FilmeController, repositórios, arquivos JSON etc.

public interface IFachada {

    //  Usuários 
    Usuario entrarCliente(String cpf, String senha) throws ValidacaoException, UsuarioException;

    Base entrarEquipe(String email, String senha) throws ValidacaoException, UsuarioException;

    void cadastrarUsuario(Usuario usuario) throws UsuarioException, PersistenciaException;

    void cadastrarUsuario(Base equipe, Usuario usuario) throws UsuarioException, PersistenciaException;

    void alterarUsuario(Usuario usuario) throws UsuarioException, PersistenciaException;

    void alterarUsuario(Base equipe, Usuario usuario) throws UsuarioException, PersistenciaException;

    void excluirUsuario(Base equipe, String cpf) throws UsuarioException, PersistenciaException;

    ArrayList<Usuario> listarUsuarios();

    //  Filmes 
    ArrayList<Filme> listarFilmes();

    void incluirFilme(GerenciaFilmes gerente, Filme filme) throws FilmeException, PersistenciaException;

    void alterarFilme(GerenciaFilmes gerente, Filme filme) throws FilmeException, PersistenciaException;

    void excluirFilme(GerenciaFilmes gerente, String nome) throws FilmeException, PersistenciaException;

    void avaliarFilme(Critico critico, Filme filme, double nota, String texto)
            throws ValidacaoException, PersistenciaException;

    //  Salas e sessões 
    Sala[] getSalas();

    ArrayList<int[]> sugerirCadeirasJuntas(Compra carrinho, Sala sala, Sessao sessao, int quantidade)
            throws ValidacaoException;

    //  Compras 
    Compra novaCompra(Usuario usuario);

    void adicionarIngressos(Compra carrinho, Usuario usuario, Sala sala, Sessao sessao,
                            ArrayList<int[]> cadeiras, int quantidadePessoas, CupomPromocional cupom)
            throws VendasException, ValidacaoException;

    void adicionarProduto(Compra carrinho, Produtos produto, int quantidade, CupomPromocional cupom)
            throws ValidacaoException;

    void removerItemDoCarrinho(Compra carrinho, int indice) throws CompraException;

    void finalizarCompra(Compra compra) throws CompraException, VendasException, PersistenciaException;

    ArrayList<Compra> comprasDoUsuario(String cpf);

    void cancelarCompra(Compra compra) throws CompraException, PersistenciaException;

    void removerItemDaCompra(Compra compra, int indice) throws CompraException, PersistenciaException;

    void adicionarProdutoNaCompra(Compra compra, Produtos produto, int quantidade)
            throws ValidacaoException, PersistenciaException;
}
