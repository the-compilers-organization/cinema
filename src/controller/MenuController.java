package controller;

public class MenuController {
    // Implementar a classe seguindo as orientacoes.
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