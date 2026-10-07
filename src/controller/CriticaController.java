package controller;

public class CriticaController {
    // Implementar a classe seguindo as orientacoes.
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