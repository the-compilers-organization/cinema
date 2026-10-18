package controller;

import model.*;
import view.CriticaView;

import javax.swing.*;

public class CriticaController {

    private CriticaView view;
    private Filme[] filmes;
    private Usuario usuario;
    private MenuController menu;

    public CriticaController(
            CriticaView view,
            Filme[] filmes,
            Usuario usuario,
            MenuController menu) {

        this.view = view;
        this.filmes = filmes;
        this.usuario = usuario;
        this.menu = menu;

        carregarFilmes();

        view.getBtnEnviar().addActionListener(e -> enviar());

        view.getBtnVoltar().addActionListener(e -> voltar());

        view.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                voltar();
            }
        });
    }

    private void carregarFilmes() {

        view.getCbFilmes().removeAllItems();

        int i;

        for (i = 0; i < filmes.length; i += 1) {

            if (filmes[i] != null) {

                view.getCbFilmes().addItem(filmes[i].getNome());
            }
        }
    }

    private Filme buscarFilmeSelecionado() {

        int indice = view.getCbFilmes().getSelectedIndex();

        if (indice < 0) {
            return null;
        }

        int contador = 0;
        int i;

        for (i = 0; i < filmes.length; i += 1) {

            if (filmes[i] != null) {

                if (contador == indice) {
                    return filmes[i];
                }

                contador += 1;
            }
        }

        return null;
    }

    private void enviar() {

        if (!(usuario instanceof Critico)) {

            JOptionPane.showMessageDialog(
                    view,
                    "Somente um Critico cadastrado pode avaliar."
            );

            return;
        }

        Filme filme = buscarFilmeSelecionado();

        if (filme == null) {

            JOptionPane.showMessageDialog(
                    view,
                    "Selecione um filme."
            );

            return;
        }

        String mensagem = view.getTxtCritica().getText().trim();

        if (mensagem.isEmpty()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Digite uma critica."
            );

            return;
        }

        double nota = ((Number) view.getSpNota().getValue()).doubleValue();

        Critico critico = (Critico) usuario;

        // Notas e comentarios possuem contadores independentes no Model.
        // Conferir a capacidade antes de registrar para evitar uma
        // avaliacao parcial.

        if (filme.getQtdCriticas() >= filme.getNotas().length ||
                filme.getQtdComentarios() >= filme.getCriticas().length) {

            JOptionPane.showMessageDialog(
                    view,
                    "Limite de avaliacoes ou comentarios atingido."
            );

            return;
        }

        boolean notaOk = critico.atribuirNota(nota, filme);

        boolean criticaOk = false;

        if (notaOk) {

            criticaOk = critico.atribuirCritica(mensagem, filme);
        }

        if (notaOk && criticaOk) {

            JOptionPane.showMessageDialog(
                    view,
                    "Avaliacao enviada."
            );

            view.getTxtCritica().setText("");

        } else {

            JOptionPane.showMessageDialog(
                    view,
                    "Nao foi possivel concluir a avaliacao."
            );
        }
    }

    private void voltar() {

        menu.voltarMenu();

        view.dispose();
    }
}



/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CriticaController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Controlar a avaliacao de filmes realizada por um Critico.
 *
 *
 * ATRIBUTOS:
 *
 * - CriticaView view;
 * - Filme[] filmes;
 * - Usuario usuario;
 * - MenuController menuController.
 *
 *
 * CONSTRUTOR:
 *
 * Receber essas referencias.
 *
 * Depois:
 *
 * - carregar os filmes;
 * - configurar eventos.
 *
 *
 * carregarFilmes():
 *
 * Percorrer Filme[].
 *
 * Para cada filme diferente de null, adicionar seu nome ao
 * JComboBox da View.
 *
 *
 * buscarFilmeSelecionado():
 *
 * Nao utilizar diretamente:
 *
 * filmes[cbFilmes.getSelectedIndex()]
 *
 * porque Filme[] pode possuir posicoes null.
 *
 * Percorrer apenas os filmes existentes utilizando um
 * contador.
 *
 *
 * enviar():
 *
 * Primeiro verificar se:
 *
 * usuario instanceof Critico
 *
 * Se nao for Critico, informar que a avaliacao nao pode ser
 * realizada.
 *
 *
 * Depois:
 *
 * - localizar Filme;
 * - obter nota;
 * - obter mensagem;
 * - converter Usuario para Critico;
 * - chamar os metodos existentes no Model.
 *
 * Utilizar:
 *
 * critico.atribuirNota(...)
 *
 * e:
 *
 * critico.atribuirCritica(...)
 *
 *
 * NAO FAZER:
 *
 * - adicionar diretamente no vetor de notas;
 * - adicionar diretamente no vetor Critica[];
 * - alterar qtdCriticas diretamente;
 * - calcular manualmente regras que pertencem a Filme ou
 *   Critico.
 *
 *
 * voltar():
 *
 * Mostrar MenuView novamente e fechar CriticaView.
 *
 * ============================================================
 */