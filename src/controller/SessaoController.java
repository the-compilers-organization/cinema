package controller;

import model.Sala;
import model.Sessao;
import view.SessaoView;

public class SessaoController {

    private SessaoView view;
    private Sala[] salas;
    private MenuController menu;

    public SessaoController(
            SessaoView view,
            Sala[] salas,
            MenuController menu) {

        this.view = view;
        this.salas = salas;
        this.menu = menu;

        carregarSessoes();

        view.getBtnVoltar().addActionListener(e -> voltar());

        view.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                voltar();
            }
        });
    }

    private void carregarSessoes() {

        view.getModelo().setRowCount(0);

        int i;
        int j;

        for (i = 0; i < salas.length; i += 1) {

            if (salas[i] != null) {

                Sessao[] sessoes = salas[i].getSessoes();

                for (j = 0; j < sessoes.length; j += 1) {

                    if (sessoes[j] != null) {

                        Sessao s = sessoes[j];

                        double valor =
                                s.getFilme().getValor()
                                * salas[i].getTipoSala().getMultiplicador();

                        view.getModelo().addRow(
                                new Object[]{
                                        salas[i].getNumero(),
                                        salas[i].getTipoSala(),
                                        s.getHorario(),
                                        s.getFilme().getNome(),
                                        String.format("R$ %.2f", valor)
                                }
                        );
                    }
                }
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
 * ORIENTACOES PARA IMPLEMENTAR SessaoController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Localizar as sessoes existentes nas salas e enviar suas
 * informacoes para a tabela de SessaoView.
 *
 *
 * ATRIBUTOS:
 *
 * - SessaoView view;
 * - Sala[] salas;
 * - MenuController menuController.
 *
 *
 * IMPORTANTE:
 *
 * As sessoes estao dentro das Salas.
 *
 * Portanto:
 *
 * Sala[]
 *   |
 *   v
 * Sala
 *   |
 *   v
 * Sessao[]
 *   |
 *   v
 * Sessao
 *
 *
 * carregarSessoes():
 *
 * Utilizar dois lacos:
 *
 * primeiro for:
 * percorrer Sala[]
 *
 * segundo for:
 * percorrer Sessao[] da Sala encontrada.
 *
 * Sempre verificar null.
 *
 *
 * Para cada sessao, adicionar na tabela:
 *
 * - numero da sala;
 * - TipoSala;
 * - horario;
 * - nome do filme;
 * - valor.
 *
 *
 * Para o valor apresentado nesta versao:
 *
 * valor do filme
 *       *
 * multiplicador do TipoSala
 *
 *
 * voltar():
 *
 * Mostrar MenuView novamente e fechar SessaoView.
 *
 *
 * NAO FAZER:
 *
 * - criar novas sessoes;
 * - alterar as sessoes apenas para exibi-las;
 * - manipular diretamente o vetor interno da Sala.
 *
 * ============================================================
 */