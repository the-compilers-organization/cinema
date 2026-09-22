package view;

import model.Filme;
import model.Sala;

import javax.swing.*;
import java.awt.*;

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR A CLASSE MenuView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Criar a tela principal do sistema de cinema utilizando Swing.
 *
 * A classe MenuView deve herdar de JFrame, pois ela representa
 * a janela principal da aplicacao.
 *
 *
 * 1. ATRIBUTOS
 *
 * A tela deve receber e guardar:
 *
 * - um vetor de objetos Filme;
 * - um vetor de objetos Sala.
 *
 * Esses vetores serao criados na Main e passados para o
 * construtor da MenuView.
 *
 * Eles devem ser guardados como atributos porque outras telas
 * precisarao acessar os mesmos filmes, salas e sessoes.
 *
 *
 * Criar tambem um JButton para cada opcao do menu:
 *
 * - Cadastrar Usuario;
 * - Listar Filmes;
 * - Listar Sessoes;
 * - Realizar Compra;
 * - Alterar Compra;
 * - Cancelar Compra;
 * - Exibir Compra;
 * - Avaliar Filme;
 * - Sair.
 *
 *
 * 2. CONSTRUTOR
 *
 * O construtor deve receber:
 *
 * Filme[] filmes
 * Sala[] salas
 *
 * Guardar os parametros nos atributos da classe utilizando
 * this.
 *
 * Exemplo da ideia:
 *
 * this.filmes = filmes;
 * this.salas = salas;
 *
 *
 * 3. CONFIGURAR A JANELA
 *
 * No construtor, configurar o JFrame.
 *
 * Definir:
 *
 * - titulo da janela;
 * - largura e altura;
 * - operacao para fechar o programa;
 * - janela centralizada na tela.
 *
 * Pesquisar/utilizar os metodos:
 *
 * setTitle(...)
 * setSize(...)
 * setDefaultCloseOperation(...)
 * setLocationRelativeTo(...)
 *
 *
 * 4. TITULO DA TELA
 *
 * Criar um JLabel para apresentar:
 *
 * "SISTEMA DE CINEMA"
 *
 * O titulo deve ficar centralizado.
 *
 * Pode ser utilizada uma fonte maior e em negrito.
 *
 *
 * 5. CRIAR OS BOTOES
 *
 * Instanciar todos os JButton declarados nos atributos.
 *
 * O texto apresentado em cada botao deve representar a
 * funcionalidade correspondente.
 *
 * Exemplo:
 *
 * btnFilmes = new JButton("Listar Filmes");
 *
 *
 * 6. CRIAR O PAINEL
 *
 * Criar um JPanel para organizar o titulo e os botoes.
 *
 * Utilizar GridLayout para deixar os componentes organizados
 * verticalmente.
 *
 * A tela possui:
 *
 * - 1 titulo;
 * - 9 botoes.
 *
 * Portanto, serao 10 componentes organizados em uma coluna.
 *
 * Pode ser utilizado espacamento entre os componentes e uma
 * borda vazia para que os botoes nao fiquem encostados nas
 * extremidades da janela.
 *
 *
 * 7. ADICIONAR OS COMPONENTES
 *
 * Adicionar ao JPanel nesta ordem:
 *
 * 1 - titulo;
 * 2 - Cadastrar Usuario;
 * 3 - Listar Filmes;
 * 4 - Listar Sessoes;
 * 5 - Realizar Compra;
 * 6 - Alterar Compra;
 * 7 - Cancelar Compra;
 * 8 - Exibir Compra;
 * 9 - Avaliar Filme;
 * 10 - Sair.
 *
 * Depois adicionar o JPanel ao JFrame.
 *
 *
 * 8. EVENTOS DOS BOTOES
 *
 * Cada botao deve possuir um ActionListener.
 *
 * O ActionListener define o que acontece quando o usuario
 * clica no botao.
 *
 *
 * CADASTRAR USUARIO:
 *
 * Deve chamar um metodo privado chamado abrirUsuario().
 *
 *
 * LISTAR FILMES:
 *
 * Deve chamar abrirFilmes().
 *
 *
 * LISTAR SESSOES:
 *
 * Deve chamar abrirSessoes().
 *
 *
 * REALIZAR COMPRA:
 *
 * Deve chamar abrirCompra().
 *
 *
 * AVALIAR FILME:
 *
 * Deve chamar abrirCritica().
 *
 *
 * SAIR:
 *
 * Deve encerrar o programa.
 *
 *
 * ALTERAR, CANCELAR E EXIBIR COMPRA:
 *
 * Enquanto essas funcionalidades ainda nao estiverem
 * implementadas, pode ser utilizado JOptionPane para informar
 * ao usuario que ainda nao existe uma compra ou que a
 * funcionalidade ainda sera implementada.
 *
 *
 * 9. METODO abrirUsuario()
 *
 * Criar um metodo privado:
 *
 * private void abrirUsuario()
 *
 * Esse metodo deve:
 *
 * - criar um objeto UsuarioView;
 * - passar a MenuView atual para UsuarioView;
 * - tornar UsuarioView visivel;
 * - esconder temporariamente a MenuView.
 *
 * A MenuView atual pode ser passada utilizando:
 *
 * this
 *
 * Isso permite que UsuarioView volte para a mesma tela de menu.
 *
 *
 * 10. METODO abrirFilmes()
 *
 * Criar:
 *
 * private void abrirFilmes()
 *
 * Esse metodo deve:
 *
 * - criar FilmeView;
 * - passar a MenuView atual;
 * - passar o vetor filmes;
 * - tornar FilmeView visivel;
 * - esconder MenuView.
 *
 * IMPORTANTE:
 *
 * Nao criar um novo vetor de filmes.
 *
 * Deve ser passado o mesmo vetor recebido da Main.
 *
 *
 * 11. METODO abrirSessoes()
 *
 * Criar:
 *
 * private void abrirSessoes()
 *
 * Esse metodo deve:
 *
 * - criar SessaoView;
 * - passar a MenuView atual;
 * - passar o vetor salas;
 * - tornar SessaoView visivel;
 * - esconder MenuView.
 *
 * As sessoes podem ser obtidas atraves das salas.
 *
 *
 * 12. METODO abrirCompra()
 *
 * Criar:
 *
 * private void abrirCompra()
 *
 * Esse metodo deve:
 *
 * - criar CompraView;
 * - passar a MenuView atual;
 * - passar o vetor salas;
 * - tornar CompraView visivel;
 * - esconder MenuView.
 *
 * CompraView precisara das salas para permitir que o usuario
 * escolha uma sessao e posteriormente as cadeiras.
 *
 *
 * 13. METODO abrirCritica()
 *
 * Criar:
 *
 * private void abrirCritica()
 *
 * Esse metodo deve:
 *
 * - criar CriticaView;
 * - passar a MenuView atual;
 * - passar o vetor filmes;
 * - tornar CriticaView visivel;
 * - esconder MenuView.
 *
 *
 * 14. NAVEGACAO ENTRE AS TELAS
 *
 * Ao abrir uma nova tela, a MenuView nao deve ser destruida.
 *
 * Ela deve apenas ficar invisivel:
 *
 * this.setVisible(false);
 *
 * Quando o usuario clicar em "Voltar" na outra tela, aquela
 * tela deve ser fechada e a mesma MenuView deve aparecer
 * novamente.
 *
 * Portanto, evitar fazer:
 *
 * new MenuView(...)
 *
 * toda vez que o usuario clicar em "Voltar".
 *
 * O objetivo e continuar utilizando os mesmos objetos e os
 * mesmos dados durante a execucao do programa.
 *
 *
 * 15. RESPONSABILIDADE DA MenuView
 *
 * Por enquanto o projeto nao possui Controller.
 *
 * Portanto, a MenuView sera responsavel temporariamente por:
 *
 * - apresentar o menu;
 * - detectar os cliques nos botoes;
 * - abrir as outras Views;
 * - passar os objetos necessarios para cada View;
 * - controlar a navegacao entre as telas.
 *
 * As regras do cinema devem continuar nas classes do pacote
 * model.
 *
 *
 * FLUXO ESPERADO:
 *
 * Main
 *   |
 *   | Filme[] e Sala[]
 *   v
 * MenuView
 *   |
 *   +---- Cadastrar Usuario ----> UsuarioView
 *   |
 *   +---- Listar Filmes --------> FilmeView
 *   |                              recebe Filme[]
 *   |
 *   +---- Listar Sessoes -------> SessaoView
 *   |                              recebe Sala[]
 *   |
 *   +---- Realizar Compra ------> CompraView
 *   |                              recebe Sala[]
 *   |
 *   +---- Avaliar Filme --------> CriticaView
 *   |                              recebe Filme[]
 *   |
 *   +---- Sair -----------------> encerra o programa
 *
 * ============================================================
 */
public class MenuView extends JFrame {

    // Implementar a classe seguindo as orientacoes acima.

}