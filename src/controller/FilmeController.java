package controller;

public class FilmeController {
    // Implementar a classe seguindo as orientacoes.
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR FilmeController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Controlar a apresentacao dos filmes existentes.
 *
 *
 * ATRIBUTOS:
 *
 * - FilmeView view;
 * - Filme[] filmes;
 * - MenuController menuController.
 *
 *
 * CONSTRUTOR:
 *
 * Receber os tres objetos.
 *
 * Depois:
 *
 * - carregar os filmes;
 * - configurar o evento do botao Voltar.
 *
 *
 * carregarFilmes():
 *
 * Limpar o DefaultTableModel antes de preencher.
 *
 * Percorrer Filme[] utilizando for.
 *
 * Para cada:
 *
 * filmes[i] != null
 *
 * adicionar uma linha contendo:
 *
 * - nome;
 * - duracao;
 * - sinopse;
 * - valor;
 * - nota/media.
 *
 *
 * O fluxo sera:
 *
 * Filme[]
 *    |
 *    v
 * FilmeController
 *    |
 *    v
 * DefaultTableModel da View
 *    |
 *    v
 * JTable
 *
 *
 * O Controller pode consultar os getters do Filme.
 *
 * Nao deve alterar os atributos internos do Filme
 * diretamente.
 *
 *
 * voltar():
 *
 * Mostrar MenuView novamente atraves do MenuController e
 * fechar FilmeView.
 *
 * ============================================================
 */
