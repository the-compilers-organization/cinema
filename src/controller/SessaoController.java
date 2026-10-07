package controller;

public class SessaoController {
    // Implementar a classe seguindo as orientacoes.
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