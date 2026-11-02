
/**
 * ROTEIRO DE IMPLEMENTAÇÃO — MenuController.java
 *
 * OBJETIVO: Controller do menu e login. Receber OperacoesCinema e passar a MESMA instância ao CompraController; usar this::voltarMenu. Não criar métodos intermediários de compra nem manter listas de compras. Controlar apenas eventos, sessão de usuário e navegação.
 *
 * ORIENTAÇÕES GERAIS:
 * - Este arquivo é um roteiro, não uma implementação funcional.
 * - As assinaturas abaixo são comentários; o colega deve escrever o código.
 * - Não usar IllegalArgumentException; seguir as exceções personalizadas do projeto.
 * - Seguir o exemplo do professor: extends Exception, super(mensagem),
 *   throws na assinatura, throw na origem e try/catch no local apropriado.
 * - Não substituir a versão funcional antes de concluir e testar a implementação.
 */

/* INTEGRAÇÃO MVC:
 * Main cria a Facade -> MenuController recebe OperacoesCinema ->
 * CompraController recebe a mesma OperacoesCinema diretamente.
 * Repository faz JSON; Facade aplica regras; Controller trata eventos;
 * View apresenta as mensagens. Ao voltar: Runnable aoVoltar.
 */

/* DECLARAÇÃO DA CLASSE/INTERFACE:
 * public class MenuController {
 *     // Implementar os itens listados abaixo.
 * }
 */

/* ATRIBUTOS / ESTADO:
 * private MenuView view;
 * private Filme[] filmes;
 * private Sala[] salas;
 * private Usuario usuario;
 * private final OperacoesCinema operacoes;
 */

/* CONSTRUTORES E MÉTODOS — ASSINATURAS:
 *
 * 1. public MenuController(MenuView view, Filme[] filmes, Sala[] salas);
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 2. public MenuController(MenuView view, Filme[] filmes, Sala[] salas, OperacoesCinema operacoes);
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 3. private void loginUsuario();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 4. private void sairConta();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 5. public boolean cadastrarUsuario(Usuario novo);
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 6. private void abrirUsuario();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 7. private void abrirFilmes();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 8. private void abrirSessoes();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 9. private void abrirCompra();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 10. private void abrirCritica();
 *    ORIENTAÇÃO: Método auxiliar interno; implementar apenas a responsabilidade indicada pelo nome.
 *
 * 11. public void voltarMenu();
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 12. public void setUsuario(Usuario usuario);
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 *
 * 13. public Usuario getUsuario();
 *    ORIENTAÇÃO: Método da API da classe; preservar parâmetros e retorno.
 */

/* ATENÇÃO: ao abrir CompraView, criar CompraController(tela, salas, usuario, operacoes, this::voltarMenu).
 * Não delegar compras por métodos intermediários deste Controller.
 */


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