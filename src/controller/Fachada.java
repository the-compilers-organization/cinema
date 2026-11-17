package controller;

import java.util.ArrayList;

import exceptions.CompraException;
import exceptions.FilmeException;
import exceptions.PersistenciaException;
import exceptions.UsuarioException;
import exceptions.ValidacaoException;
import exceptions.VendasException;
import model.Administrador;
import model.Base;
import model.Compra;
import model.Critico;
import model.Filme;
import model.Funcionario;
import model.Sala;
import model.Sessao;
import model.Usuario;
import model.enums.CupomPromocional;
import model.enums.Produtos;
import model.interfaces.GerenciaFilmes;
import repository.IRepositorioCompra;
import repository.IRepositorioFilme;
import repository.IRepositorioUsuario;
import repository.RepositorioCompraJSON;
import repository.RepositorioFilmeJSON;
import repository.RepositorioUsuarioJSON;


// PADRÃO DE PROJETO FACHADA (Parte 4) - classe Fachada.
// uma operação como "comprar ingresso" envolve várias classes (Usuario, Sala, Sessao, Bilhete, Compra, controladores e repositórios).
// A Fachada oferece um ÚNICO ponto de acesso para a UI e repassa cada pedido para o controlador responsável, escondendo a complexidade interna.
// UI  --(IFachada)-->  Fachada  -->  XxxController  --(IRepositorio)-->  Repositórios JSON

public class Fachada implements IFachada {

    private UsuarioController usuarioController;
    private FilmeController filmeController;
    private SessaoController sessaoController;
    private CompraController compraController;

    public Fachada() throws PersistenciaException, ValidacaoException {
        // Camada de dados: as variáveis são do tipo da INTERFACE
        IRepositorioUsuario repositorioUsuario = new RepositorioUsuarioJSON();
        IRepositorioFilme repositorioFilme = new RepositorioFilmeJSON();
        IRepositorioCompra repositorioCompra = new RepositorioCompraJSON();

        // TODO: gambiarra para funcionamento - criando Equipe do cinema (o Administrador é o responsável pela persistência)
        Administrador administrador = new Administrador(1, "Ana Administradora", 35, UsuarioController.EMAIL_ADMIN, 6500.00, repositorioUsuario, repositorioFilme);
        Funcionario funcionario = new Funcionario("Caio Funcionário", 24, UsuarioController.EMAIL_FUNCIONARIO, 2500.00, administrador);

        // Camada de negócio: controladores
        usuarioController = new UsuarioController(repositorioUsuario, administrador, funcionario);
        filmeController = new FilmeController(repositorioFilme);
        sessaoController = new SessaoController(repositorioFilme.listar());
        compraController = new CompraController(repositorioCompra, sessaoController);
    }

    //  USUÁRIOS 
    @Override
    public Usuario entrarCliente(String cpf, String senha) throws ValidacaoException, UsuarioException {
        return usuarioController.entrarCliente(cpf, senha);
    }

    @Override
    public Base entrarEquipe(String email, String senha) throws ValidacaoException, UsuarioException {
        return usuarioController.entrarEquipe(email, senha);
    }

    // Auto cadastro na tela de login: quem grava é o Administrador
    @Override
    public void cadastrarUsuario(Usuario usuario) throws UsuarioException, PersistenciaException {
        usuarioController.cadastrar(usuarioController.getAdministrador(), usuario);
    }

    @Override
    public void cadastrarUsuario(Base equipe, Usuario usuario) throws UsuarioException, PersistenciaException {
        usuarioController.cadastrar(equipe, usuario);
    }

    @Override
    public void alterarUsuario(Usuario usuario) throws UsuarioException, PersistenciaException {
        usuarioController.alterar(usuarioController.getAdministrador(), usuario);
    }

    @Override
    public void alterarUsuario(Base equipe, Usuario usuario) throws UsuarioException, PersistenciaException {
        usuarioController.alterar(equipe, usuario);
    }

    // A Fachada coordena dois controladores: usuário e compra
    @Override
    public void excluirUsuario(Base equipe, String cpf) throws UsuarioException, PersistenciaException {
        boolean possuiCompras = !compraController.comprasDoUsuario(cpf).isEmpty();
        usuarioController.excluir(equipe, cpf, possuiCompras);
    }

    @Override
    public ArrayList<Usuario> listarUsuarios() {
        return usuarioController.listar();
    }

    //  FILMES 
    @Override
    public ArrayList<Filme> listarFilmes() {
        return filmeController.listar();
    }

    @Override
    public void incluirFilme(GerenciaFilmes gerente, Filme filme) throws FilmeException, PersistenciaException {
        filmeController.incluir(gerente, filme);
    }

    @Override
    public void alterarFilme(GerenciaFilmes gerente, Filme filme) throws FilmeException, PersistenciaException {
        filmeController.alterar(gerente, filme);
    }

    // A Fachada coordena dois controladores: filme e sessão
    @Override
    public void excluirFilme(GerenciaFilmes gerente, String nome) throws FilmeException, PersistenciaException {
        if (sessaoController.filmeEstaNaProgramacao(nome)) {
            throw new FilmeException("\"" + nome + "\" está na programação de hoje e não pode ser excluído. " + "Desmarque a opção \"Em cartaz\" para retirá-lo das vendas.");
        }
        filmeController.excluir(gerente, nome);
    }

    @Override
    public void avaliarFilme(Critico critico, Filme filme, double nota, String texto) throws ValidacaoException, PersistenciaException {
        filmeController.avaliar(critico, filme, nota, texto);
    }

    //  SALAS E SESSÕES 
    @Override
    public Sala[] getSalas() {
        return sessaoController.getSalas();
    }

    @Override
    public ArrayList<int[]> sugerirCadeirasJuntas(Compra carrinho, Sala sala, Sessao sessao, int quantidade) throws ValidacaoException {
        return sessaoController.sugerirCadeirasJuntas(carrinho, sala, sessao, quantidade);
    }

    //  COMPRAS 
    @Override
    public Compra novaCompra(Usuario usuario) {
        return compraController.novaCompra(usuario);
    }

    @Override
    public void adicionarIngressos(Compra carrinho, Usuario usuario, Sala sala, Sessao sessao, ArrayList<int[]> cadeiras, int quantidadePessoas, CupomPromocional cupom) throws VendasException, ValidacaoException {
        compraController.adicionarIngressos(carrinho, usuario, sala, sessao, cadeiras, quantidadePessoas, cupom);
    }

    @Override
    public void adicionarProduto(Compra carrinho, Produtos produto, int quantidade, CupomPromocional cupom) throws ValidacaoException {
        compraController.adicionarProduto(carrinho, produto, quantidade, cupom);
    }

    @Override
    public void removerItemDoCarrinho(Compra carrinho, int indice) throws CompraException {
        compraController.removerItemDoCarrinho(carrinho, indice);
    }

    @Override
    public void finalizarCompra(Compra compra) throws CompraException, VendasException, PersistenciaException {
        compraController.finalizar(compra);
    }

    @Override
    public ArrayList<Compra> comprasDoUsuario(String cpf) {
        return compraController.comprasDoUsuario(cpf);
    }

    @Override
    public void cancelarCompra(Compra compra) throws CompraException, PersistenciaException {
        compraController.cancelar(compra);
    }

    @Override
    public void removerItemDaCompra(Compra compra, int indice) throws CompraException, PersistenciaException {
        compraController.removerItemDaCompra(compra, indice);
    }

    @Override
    public void adicionarProdutoNaCompra(Compra compra, Produtos produto, int quantidade) throws ValidacaoException, PersistenciaException {
        compraController.adicionarProdutoNaCompra(compra, produto, quantidade);
    }
}
