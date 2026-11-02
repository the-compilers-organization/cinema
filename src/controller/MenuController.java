package controller;

import model.*;
import model.enums.*;
import view.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuController {
    private MenuView view;
    private Filme[] filmes;
    private Sala[] salas;
    private Usuario usuario;
    private final Map<String, Usuario> usuariosCadastrados = new HashMap<>();
    // As tres listas usam o mesmo indice para cada compra registrada.
    private final ArrayList<Compra> compras = new ArrayList<>();
    private final ArrayList<Boolean> perfilPorCompra = new ArrayList<>();
    private final ArrayList<CupomPromocional> cupomPorCompra = new ArrayList<>();

    public MenuController(MenuView view, Filme[] filmes, Sala[] salas) {
        this.view = view;
        this.filmes = filmes;
        this.salas = salas;
        view.getBtnCadastrarUsuario().addActionListener(e -> abrirUsuario());
        view.getBtnLoginUsuario().addActionListener(e -> loginUsuario());
        view.getBtnSairConta().addActionListener(e -> sairConta());
        view.getBtnFilmes().addActionListener(e -> abrirFilmes());
        view.getBtnSessoes().addActionListener(e -> abrirSessoes());
        view.getBtnRealizarCompra().addActionListener(e -> abrirCompra());
        view.getBtnAvaliarFilme().addActionListener(e -> abrirCritica());
        view.getBtnSair().addActionListener(e -> System.exit(0));
    }

    private void loginUsuario() {
        JTextField campoCpf = new JTextField();
        JPasswordField campoSenha = new JPasswordField();
        Object[] campos = {"CPF:", campoCpf, "Senha:", campoSenha};
        int opcao = JOptionPane.showConfirmDialog(view, campos, "Login Usuario",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (opcao != JOptionPane.OK_OPTION) return;

        Usuario encontrado = usuariosCadastrados.get(campoCpf.getText().trim());
        String senha = new String(campoSenha.getPassword());
        if (encontrado == null || !encontrado.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(view, "CPF ou senha invalidos.");
            return;
        }
        usuario = encontrado;
        view.mostrarCliente(usuario.getUser());
    }

    private void sairConta() {
        usuario = null;
        view.mostrarPrincipal();
    }

    public boolean cadastrarUsuario(Usuario novo) {
        if (novo == null || usuariosCadastrados.containsKey(novo.getCpf())) return false;
        usuariosCadastrados.put(novo.getCpf(), novo);
        return true;
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
            JOptionPane.showMessageDialog(view, "Faca login antes de comprar.");
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

    // Mantido para compatibilidade com CompraController.
    // ArrayList nao estabelece limite fixo de 100 compras.
    public boolean podeRegistrarCompra() {
        return true;
    }

    public boolean registrarCompra(Compra compra, boolean perfil, CupomPromocional cupom) {
        if (compra == null || (perfil && cupom != null)) return false;

        compras.add(compra);
        perfilPorCompra.add(perfil);
        cupomPorCompra.add(cupom);
        return true;
    }

    public int getQtdCompras() {
        return compras.size();
    }

    public Compra getCompra(int indice) {
        if (indice < 0 || indice >= compras.size()) return null;
        return compras.get(indice);
    }

    public boolean getPerfilCompra(int indice) {
        if (getCompra(indice) == null) return false;
        return perfilPorCompra.get(indice);
    }

    public CupomPromocional getCupomCompra(int indice) {
        if (getCompra(indice) == null) return null;
        return cupomPorCompra.get(indice);
    }

    public boolean atualizarCompra(int indice, Compra nova, boolean perfil, CupomPromocional cupom) {
        if (getCompra(indice) == null || nova == null || (perfil && cupom != null)) return false;

        compras.set(indice, nova);
        perfilPorCompra.set(indice, perfil);
        cupomPorCompra.set(indice, cupom);
        return true;
    }

    // Remove apenas o registro. Liberar cadeiras e responsabilidade de Compra.
    public boolean excluirCompra(int indice) {
        if (getCompra(indice) == null) return false;

        compras.remove(indice);
        perfilPorCompra.remove(indice);
        cupomPorCompra.remove(indice);
        return true;
    }

    public void voltarMenu() { view.setVisible(true); }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Usuario getUsuario() { return usuario; }
}
