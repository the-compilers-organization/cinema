package controller;

import model.*;
import enums.*;
import view.*;
import javax.swing.*;

public class MenuController {
    private MenuView view;
    private Filme[] filmes;
    private Sala[] salas;
    private Usuario usuario;
    private Compra[] compras = new Compra[100];
    private boolean[] perfilPorCompra = new boolean[100];
    private CupomPromocional[] cupomPorCompra = new CupomPromocional[100];
    private int qtdCompras = 0;

    public MenuController(MenuView view, Filme[] filmes, Sala[] salas) {
        this.view = view;
        this.filmes = filmes;
        this.salas = salas;
        view.getBtnCadastrarUsuario().addActionListener(e -> abrirUsuario());
        view.getBtnFilmes().addActionListener(e -> abrirFilmes());
        view.getBtnSessoes().addActionListener(e -> abrirSessoes());
        view.getBtnRealizarCompra().addActionListener(e -> abrirCompra());
        view.getBtnAvaliarFilme().addActionListener(e -> abrirCritica());
        view.getBtnSair().addActionListener(e -> System.exit(0));
    }

    private void abrirUsuario() {
        UsuarioView tela = new UsuarioView();
        new UsuarioController(tela, this);
        tela.setVisible(true);
        view.setVisible(false);
    }

    private void abrirFilmes() {
        FilmeView tela = new FilmeView();
        new FilmeController(tela, filmes, this);
        tela.setVisible(true);
        view.setVisible(false);
    }

    private void abrirSessoes() {
        SessaoView tela = new SessaoView();
        new SessaoController(tela, salas, this);
        tela.setVisible(true);
        view.setVisible(false);
    }

    private void abrirCompra() {
        if (usuario == null) {
            JOptionPane.showMessageDialog(view, "Cadastre um usuario antes de comprar.");
            return;
        }
        CompraView tela = new CompraView();
        new CompraController(tela, salas, usuario, this);
        tela.setVisible(true);
        view.setVisible(false);
    }

    private void abrirCritica() {
        CriticaView tela = new CriticaView();
        new CriticaController(tela, filmes, usuario, this);
        tela.setVisible(true);
        view.setVisible(false);
    }

    public boolean podeRegistrarCompra() { return qtdCompras < compras.length; }

    public boolean registrarCompra(Compra compra, boolean perfil, CupomPromocional cupom) {
        if (compra == null || !podeRegistrarCompra()) return false;
        compras[qtdCompras] = compra;
        perfilPorCompra[qtdCompras] = perfil;
        cupomPorCompra[qtdCompras] = cupom;
        qtdCompras++;
        return true;
    }

    public int getQtdCompras() { return qtdCompras; }
    public Compra getCompra(int indice) {
        if (indice < 0 || indice >= qtdCompras) return null;
        return compras[indice];
    }
    public boolean getPerfilCompra(int indice) { return perfilPorCompra[indice]; }
    public CupomPromocional getCupomCompra(int indice) { return cupomPorCompra[indice]; }

    public boolean atualizarCompra(int indice, Compra nova, boolean perfil, CupomPromocional cupom) {
        if (getCompra(indice) == null || nova == null) return false;
        compras[indice] = nova;
        perfilPorCompra[indice] = perfil;
        cupomPorCompra[indice] = cupom;
        return true;
    }

    // Remove somente o registro. As cadeiras devem ser liberadas pelo Model Compra.
    public boolean excluirCompra(int indice) {
        if (getCompra(indice) == null) return false;
        for (int i = indice; i < qtdCompras - 1; i++) {
            compras[i] = compras[i + 1];
            perfilPorCompra[i] = perfilPorCompra[i + 1];
            cupomPorCompra[i] = cupomPorCompra[i + 1];
        }
        qtdCompras--;
        compras[qtdCompras] = null;
        perfilPorCompra[qtdCompras] = false;
        cupomPorCompra[qtdCompras] = null;
        return true;
    }

    public void voltarMenu() { view.setVisible(true); }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Usuario getUsuario() { return usuario; }
}



/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR MenuController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Controlar a tela principal do sistema e a navegacao para
 * as demais funcionalidades.
 *
 * O MenuController deve fazer a ligacao entre:
 *
 * MenuView
 *    |
 *    +---- UsuarioView
 *    +---- FilmeView
 *    +---- SessaoView
 *    +---- CompraView
 *    +---- CriticaView
 *
 *
 * ATRIBUTOS:
 *
 * Deve possuir referencias para:
 *
 * - MenuView;
 * - Filme[];
 * - Sala[];
 * - Usuario atual.
 *
 * O Usuario atual inicialmente pode ser null, pois sera
 * definido depois do cadastro realizado em UsuarioView.
 *
 *
 * CONSTRUTOR:
 *
 * Deve receber:
 *
 * MenuView
 * Filme[]
 * Sala[]
 *
 * Guardar essas referencias e chamar um metodo responsavel
 * pela configuracao dos eventos.
 *
 *
 * EVENTOS:
 *
 * Associar os botoes da MenuView aos metodos correspondentes:
 *
 * Cadastrar Usuario -> abrirUsuario()
 * Listar Filmes     -> abrirFilmes()
 * Listar Sessoes    -> abrirSessoes()
 * Realizar Compra   -> abrirCompra()
 * Avaliar Filme     -> abrirCritica()
 * Sair              -> encerrar aplicacao
 *
 *
 * NAVEGACAO:
 *
 * Quando uma nova tela for aberta:
 *
 * 1. criar a View;
 * 2. criar o Controller correspondente;
 * 3. passar os objetos necessarios;
 * 4. tornar a nova View visivel;
 * 5. esconder MenuView.
 *
 *
 * Exemplo conceitual:
 *
 * MenuController
 *      |
 *      | abrir filmes
 *      v
 * cria FilmeView
 *      |
 *      v
 * cria FilmeController
 *      |
 *      v
 * FilmeView visivel
 *
 *
 * USUARIO ATUAL:
 *
 * Criar:
 *
 * setUsuario(...)
 * getUsuario()
 *
 * O UsuarioController deve informar ao MenuController qual
 * usuario foi cadastrado.
 *
 * Isso permite posteriormente passar o mesmo Usuario para:
 *
 * CompraController
 * CriticaController
 *
 *
 * VOLTAR AO MENU:
 *
 * Criar um metodo publico como:
 *
 * voltarMenu()
 *
 * Esse metodo deve tornar MenuView novamente visivel.
 *
 *
 * NAO FAZER:
 *
 * - construir componentes Swing;
 * - implementar regras de Filme;
 * - ocupar cadeiras diretamente sem utilizar o Model;
 * - calcular regras de desconto que ja pertencem ao Model;
 * - criar usuarios ficticios para outras telas.
 *
 * ============================================================
 */