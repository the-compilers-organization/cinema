package controller;

import model.Filme;
import view.FilmeView;

public class FilmeController {

    private FilmeView view;
    private Filme[] filmes;
    private MenuController menu;

    public FilmeController(
            FilmeView view,
            Filme[] filmes,
            MenuController menu) {

        this.view = view;
        this.filmes = filmes;
        this.menu = menu;

        carregarFilmes();

        view.getBtnVoltar().addActionListener(e -> voltar());

        view.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                voltar();
            }
        });
    }

    private void carregarFilmes() {

        view.getModelo().setRowCount(0);

        int i;

        for (i = 0; i < filmes.length; i += 1) {

            if (filmes[i] != null) {

                Filme f = filmes[i];

                view.getModelo().addRow(
                        new Object[]{
                                f.getNome(),
                                f.getDuracao(),
                                f.getSinopse(),
                                String.format("R$ %.2f", f.getValor()),
                                f.mediaDasNotas()
                        }
                );
            }
        }
    }

    private void voltar() {

        menu.voltarMenu();

        view.dispose();
    }
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
