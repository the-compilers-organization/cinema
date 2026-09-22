package view;

import model.Sala;
import model.Sessao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR A CLASSE SessaoView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Criar a tela responsavel por listar todas as sessoes
 * disponiveis no cinema.
 *
 * A classe SessaoView deve herdar de JFrame, pois representa
 * uma janela da interface grafica.
 *
 * Essa tela sera utilizada apenas para visualizacao das
 * sessoes existentes.
 *
 * Para cada sessao, devem ser apresentados:
 *
 * - numero da sala;
 * - tipo da sala;
 * - horario da sessao;
 * - nome do filme;
 * - valor do ingresso naquela sala.
 *
 *
 * 1. ATRIBUTOS PRINCIPAIS
 *
 * A classe deve possuir:
 *
 * - uma referencia para MenuView;
 * - um vetor de objetos Sala;
 * - uma JTable;
 * - um DefaultTableModel;
 * - um JButton para voltar.
 *
 * Pensar em atributos equivalentes a:
 *
 * MenuView menu
 * Sala[] salas
 * JTable tabelaSessoes
 * DefaultTableModel modelo
 * JButton btnVoltar
 *
 *
 * 2. POR QUE RECEBER MenuView?
 *
 * SessaoView sera aberta a partir da MenuView.
 *
 * Quando o usuario clicar em "Voltar", queremos retornar
 * para a MESMA MenuView que abriu esta tela.
 *
 * Portanto, guardar uma referencia para MenuView.
 *
 * Evitar criar:
 *
 * new MenuView(...)
 *
 * dentro de SessaoView.
 *
 * A tela principal ja existe e deve apenas voltar a ficar
 * visivel.
 *
 *
 * 3. POR QUE RECEBER Sala[]?
 *
 * As sessoes do sistema estao armazenadas dentro das salas.
 *
 * A estrutura do Model e:
 *
 * Sala[]
 *    |
 *    +---- Sala
 *    |       |
 *    |       +---- Sessao[]
 *    |
 *    +---- Sala
 *            |
 *            +---- Sessao[]
 *
 * Portanto, SessaoView precisa receber:
 *
 * Sala[] salas
 *
 * para conseguir acessar todas as sessoes existentes.
 *
 *
 * 4. NAO CRIAR NOVAS SALAS OU SESSOES
 *
 * SessaoView nao deve criar novos objetos Sala ou Sessao
 * apenas para apresenta-los.
 *
 * Os objetos ja existem no sistema.
 *
 * A View deve apenas consultar esses objetos atraves dos
 * getters e apresentar suas informacoes.
 *
 *
 * 5. CONSTRUTOR
 *
 * O construtor deve receber:
 *
 * MenuView menu
 * Sala[] salas
 *
 * Guardar os parametros utilizando this.
 *
 * Ideia:
 *
 * this.menu = menu;
 * this.salas = salas;
 *
 *
 * 6. CONFIGURAR A JANELA
 *
 * Configurar o JFrame com:
 *
 * titulo:
 *
 * "Sessoes"
 *
 * tamanho aproximado:
 *
 * 850 x 450
 *
 * utilizar:
 *
 * JFrame.DISPOSE_ON_CLOSE
 *
 * e centralizar a janela.
 *
 *
 * 7. POR QUE DISPOSE_ON_CLOSE?
 *
 * SessaoView e uma janela secundaria.
 *
 * Fechar essa janela nao deve encerrar toda a aplicacao.
 *
 * Portanto, nao utilizar:
 *
 * JFrame.EXIT_ON_CLOSE
 *
 * pois essa opcao encerraria o programa inteiro.
 *
 *
 * 8. CRIAR A TABELA
 *
 * Utilizar:
 *
 * JTable
 *
 * para apresentar as sessoes.
 *
 * Os dados da JTable devem ser controlados atraves de:
 *
 * DefaultTableModel
 *
 *
 * 9. RELACAO ENTRE DefaultTableModel E JTable
 *
 * Podemos pensar da seguinte maneira:
 *
 * objetos Sala e Sessao
 *          |
 *          | getters
 *          v
 * DefaultTableModel
 *          |
 *          | linhas e colunas
 *          v
 *        JTable
 *          |
 *          v
 *        usuario
 *
 *
 * 10. COLUNAS DA TABELA
 *
 * Criar um DefaultTableModel com cinco colunas:
 *
 * "Sala"
 * "Tipo"
 * "Horario"
 * "Filme"
 * "Valor"
 *
 * Inicialmente:
 *
 * 0 linhas
 *
 * pois as linhas serao adicionadas posteriormente atraves
 * do metodo carregarSessoes().
 *
 *
 * 11. O QUE CADA COLUNA REPRESENTA
 *
 * "Sala"
 *
 * Deve apresentar o numero da sala.
 *
 *
 * "Tipo"
 *
 * Deve apresentar o TipoSala:
 *
 * COMUM
 * SALA3D
 * XD
 * XD_3D
 *
 *
 * "Horario"
 *
 * Deve apresentar o horario da Sessao.
 *
 *
 * "Filme"
 *
 * Deve apresentar o nome do Filme daquela Sessao.
 *
 *
 * "Valor"
 *
 * Deve apresentar o valor do ingresso considerando:
 *
 * valor do Filme
 *
 * multiplicado pelo:
 *
 * multiplicador do TipoSala.
 *
 *
 * 12. CRIAR JTable
 *
 * Depois de criar o DefaultTableModel, criar:
 *
 * JTable
 *
 * utilizando o modelo.
 *
 * Conceitualmente:
 *
 * DefaultTableModel
 *         |
 *         v
 *      JTable
 *
 * Quando uma linha for adicionada ao modelo, ela aparecera
 * automaticamente na tabela.
 *
 *
 * 13. JScrollPane
 *
 * Colocar a JTable dentro de:
 *
 * JScrollPane
 *
 * Isso permite utilizar barra de rolagem caso existam muitas
 * sessoes.
 *
 * A estrutura sera:
 *
 * JScrollPane
 *      |
 *      v
 *    JTable
 *
 *
 * 14. BOTAO VOLTAR
 *
 * Criar:
 *
 * JButton btnVoltar
 *
 * com o texto:
 *
 * "Voltar"
 *
 * Esse botao deve retornar para MenuView.
 *
 *
 * 15. ORGANIZACAO DA TELA
 *
 * A JTable pode ocupar a regiao central da janela.
 *
 * O botao Voltar pode ficar na parte inferior.
 *
 * Exemplo:
 *
 *                         CENTER
 *
 * +----------------------------------------------------------+
 * | Sala | Tipo   | Horario | Filme        | Valor          |
 * +----------------------------------------------------------+
 * |  1   | COMUM  | 18:00   | Interestelar | R$ 30,00       |
 * |  1   | COMUM  | 20:00   | Batman       | R$ 25,00       |
 * |  2   | SALA3D | 21:30   | Avatar       | R$ 42,00       |
 * +----------------------------------------------------------+
 *
 *                         SOUTH
 *
 * +----------------------------------------------------------+
 * |                       [ Voltar ]                         |
 * +----------------------------------------------------------+
 *
 *
 * 16. METODO carregarSessoes()
 *
 * Criar:
 *
 * private void carregarSessoes()
 *
 * Esse metodo sera responsavel por percorrer todas as salas
 * e todas as sessoes existentes e adicionar as informacoes
 * na tabela.
 *
 *
 * 17. LIMPAR A TABELA PRIMEIRO
 *
 * Antes de adicionar as sessoes, limpar as linhas existentes.
 *
 * Utilizar:
 *
 * modelo.setRowCount(0)
 *
 * Isso evita duplicar as sessoes caso carregarSessoes() seja
 * executado novamente.
 *
 *
 * 18. PRIMEIRO FOR: PERCORRER AS SALAS
 *
 * Declarar uma variavel inteira para controlar o primeiro
 * laco.
 *
 * Percorrer:
 *
 * Sala[] salas
 *
 * da primeira ate a ultima posicao.
 *
 * Para cada posicao, verificar:
 *
 * salas[i] != null
 *
 * Somente continuar se existir uma Sala naquela posicao.
 *
 *
 * 19. POR QUE VERIFICAR null NA SALA?
 *
 * O vetor pode ter tamanho 10:
 *
 * Sala[] salas = new Sala[10];
 *
 * mas possuir apenas tres salas cadastradas:
 *
 * [0] -> Sala 1
 * [1] -> Sala 2
 * [2] -> Sala 3
 * [3] -> null
 * ...
 * [9] -> null
 *
 * Portanto, nao podemos executar:
 *
 * salas[i].getSessoes()
 *
 * se salas[i] for null.
 *
 * Isso poderia gerar:
 *
 * NullPointerException
 *
 *
 * 20. OBTER O VETOR DE SESSOES
 *
 * Para cada Sala existente, obter seu vetor de sessoes.
 *
 * Utilizar o getter existente em Sala.
 *
 * A ideia sera:
 *
 * Sala
 *   |
 *   | getSessoes()
 *   v
 * Sessao[]
 *
 * Guardar esse vetor em uma variavel local.
 *
 *
 * 21. SEGUNDO FOR: PERCORRER AS SESSOES
 *
 * Depois de obter:
 *
 * Sessao[] sessoes
 *
 * utilizar outro for para percorrer esse vetor.
 *
 * Portanto, teremos:
 *
 * for das salas
 *     |
 *     +---- for das sessoes
 *
 *
 * Estrutura:
 *
 * Sala 1
 *   |
 *   +-- Sessao 18:00
 *   +-- Sessao 20:00
 *
 * Sala 2
 *   |
 *   +-- Sessao 21:30
 *
 * Sala 3
 *   |
 *   +-- Sessao 22:00
 *
 *
 * 22. VERIFICAR null NA SESSAO
 *
 * O vetor Sessao[] tambem possui tamanho fixo e pode conter
 * posicoes vazias.
 *
 * Portanto, para cada posicao verificar:
 *
 * sessoes[j] != null
 *
 * Somente sessoes existentes devem ser adicionadas na tabela.
 *
 *
 * 23. POR QUE SAO NECESSARIOS DOIS FOR?
 *
 * Porque nao recebemos diretamente:
 *
 * Sessao[]
 *
 * Recebemos:
 *
 * Sala[]
 *
 * e cada Sala possui:
 *
 * Sessao[]
 *
 * Portanto:
 *
 * primeiro for:
 *
 * encontra cada Sala.
 *
 * segundo for:
 *
 * encontra cada Sessao daquela Sala.
 *
 * Visualmente:
 *
 * salas[i]
 *    |
 *    v
 * Sala
 *    |
 *    v
 * getSessoes()
 *    |
 *    v
 * sessoes[j]
 *
 *
 * 24. CALCULAR O VALOR DA SESSAO
 *
 * O valor apresentado nao deve considerar apenas o valor
 * base do Filme.
 *
 * Tambem deve considerar o tipo da Sala.
 *
 * O calculo utilizado nesta versao e:
 *
 * valor =
 * valorDoFilme * multiplicadorDaSala
 *
 *
 * 25. OBTER O VALOR DO FILME
 *
 * A Sessao possui um Filme.
 *
 * Portanto:
 *
 * Sessao
 *    |
 *    | getFilme()
 *    v
 * Filme
 *    |
 *    | getValor()
 *    v
 * valor base
 *
 *
 * 26. OBTER O MULTIPLICADOR DA SALA
 *
 * A Sala possui um TipoSala.
 *
 * Portanto:
 *
 * Sala
 *   |
 *   | getTipoSala()
 *   v
 * TipoSala
 *   |
 *   | getMultiplicador()
 *   v
 * multiplicador
 *
 *
 * 27. CALCULO COMPLETO
 *
 * O calculo conceitual sera:
 *
 * sessao
 *   |
 *   +-- filme
 *   |     |
 *   |     +-- valor
 *   |
 * sala
 *   |
 *   +-- tipoSala
 *         |
 *         +-- multiplicador
 *
 *
 * valor =
 *
 * filme.getValor()
 *       *
 * tipoSala.getMultiplicador()
 *
 *
 * 28. EXEMPLO COM SALA COMUM
 *
 * Suponha:
 *
 * Filme:
 * Interestelar
 *
 * valor:
 * R$ 30,00
 *
 * Sala:
 * COMUM
 *
 * multiplicador:
 * 1.0
 *
 * Entao:
 *
 * 30.00 * 1.0
 *
 * =
 *
 * R$ 30,00
 *
 *
 * 29. EXEMPLO COM SALA 3D
 *
 * Suponha:
 *
 * Filme:
 * Avatar
 *
 * valor:
 * R$ 35,00
 *
 * TipoSala:
 * SALA3D
 *
 * multiplicador:
 * 1.2
 *
 * Entao:
 *
 * 35.00 * 1.2
 *
 * =
 *
 * R$ 42,00
 *
 * Portanto, a tabela deve apresentar:
 *
 * Sala | Tipo   | Horario | Filme  | Valor
 * ------------------------------------------------
 * 2    | SALA3D | 21:30   | Avatar | R$ 42,00
 *
 *
 * 30. ADICIONAR A LINHA AO MODELO
 *
 * Para cada Sessao existente, utilizar:
 *
 * modelo.addRow(...)
 *
 * A nova linha deve possuir cinco informacoes na mesma ordem
 * das colunas:
 *
 * 1 - numero da sala;
 * 2 - tipo da sala;
 * 3 - horario;
 * 4 - nome do filme;
 * 5 - valor calculado.
 *
 *
 * 31. NUMERO DA SALA
 *
 * Obter atraves do objeto Sala utilizando:
 *
 * getNumero()
 *
 * Esse valor sera colocado na coluna:
 *
 * "Sala"
 *
 *
 * 32. TIPO DA SALA
 *
 * Obter atraves de:
 *
 * getTipoSala()
 *
 * Esse valor sera colocado na coluna:
 *
 * "Tipo"
 *
 *
 * 33. HORARIO DA SESSAO
 *
 * Obter atraves do objeto Sessao:
 *
 * getHorario()
 *
 * Esse valor sera colocado na coluna:
 *
 * "Horario"
 *
 *
 * 34. NOME DO FILME
 *
 * Primeiro obter o Filme da Sessao.
 *
 * Depois obter seu nome.
 *
 * A navegacao sera:
 *
 * Sessao
 *    |
 *    | getFilme()
 *    v
 * Filme
 *    |
 *    | getNome()
 *    v
 * nome
 *
 *
 * 35. FORMATAR O VALOR
 *
 * Para apresentar o valor como dinheiro, utilizar
 * String.format().
 *
 * A ideia:
 *
 * String.format(
 *        "R$ %.2f",
 *        valor
 * )
 *
 * Assim um valor como:
 *
 * 42.0
 *
 * sera apresentado com duas casas decimais.
 *
 *
 * 36. CHAMAR carregarSessoes()
 *
 * Depois que:
 *
 * - DefaultTableModel estiver criado;
 * - JTable estiver criada;
 * - componentes estiverem adicionados;
 *
 * chamar:
 *
 * carregarSessoes()
 *
 * Assim, quando a janela aparecer, a tabela ja estara
 * preenchida.
 *
 *
 * 37. EVENTO DO BOTAO VOLTAR
 *
 * Adicionar um ActionListener ao:
 *
 * btnVoltar
 *
 * Quando o usuario clicar, chamar:
 *
 * voltar()
 *
 *
 * 38. METODO voltar()
 *
 * Criar:
 *
 * private void voltar()
 *
 * Esse metodo deve:
 *
 * - tornar MenuView visivel novamente;
 * - fechar SessaoView.
 *
 * A ideia:
 *
 * menu.setVisible(true);
 *
 * depois:
 *
 * dispose();
 *
 *
 * 39. NAO CRIAR UMA NOVA MenuView
 *
 * Evitar fazer:
 *
 * new MenuView(...)
 *
 * dentro de voltar().
 *
 * A SessaoView ja possui a referencia para a MenuView que
 * abriu essa tela.
 *
 * Portanto, reutilizar:
 *
 * menu
 *
 *
 * 40. RESPONSABILIDADE DA SessaoView
 *
 * A responsabilidade desta View deve ser principalmente:
 *
 * - montar a interface grafica;
 * - receber Sala[];
 * - localizar as sessoes dentro das salas;
 * - consultar os dados atraves dos getters;
 * - apresentar as sessoes na JTable;
 * - responder ao botao Voltar.
 *
 *
 * 41. O QUE NAO DEVE FICAR NA SessaoView
 *
 * Evitar colocar nesta classe regras como:
 *
 * - criar novas sessoes;
 * - adicionar sessoes diretamente nas salas;
 * - ocupar cadeiras;
 * - liberar cadeiras;
 * - alterar filmes;
 * - modificar o TipoSala.
 *
 * Essas responsabilidades pertencem ao Model ou a outras
 * funcionalidades do sistema.
 *
 *
 * 42. RELACAO COM Sala.listarSessoes()
 *
 * A classe Sala pode possuir um metodo:
 *
 * listarSessoes()
 *
 * que utiliza System.out.println().
 *
 * Esse metodo foi criado para a versao de terminal.
 *
 * Na interface grafica nao precisamos utilizar:
 *
 * System.out.println()
 *
 * para mostrar as sessoes.
 *
 * SessaoView substitui essa apresentacao visual utilizando:
 *
 * JTable
 *
 * Portanto:
 *
 * versao terminal:
 *
 * Sala.listarSessoes()
 *          |
 *          v
 * System.out
 *
 *
 * versao Swing:
 *
 * Sala[]
 *    |
 *    v
 * SessaoView
 *    |
 *    v
 * JTable
 *
 *
 * 43. TABELA APENAS PARA VISUALIZACAO
 *
 * Como essa tela tem o objetivo de LISTAR sessoes, o ideal
 * e que o usuario nao consiga editar diretamente as celulas.
 *
 * DefaultTableModel normalmente permite edicao das celulas.
 *
 * Posteriormente pode ser criado um modelo que sobrescreva:
 *
 * isCellEditable(...)
 *
 * e retorne false.
 *
 * Isso deixa a tabela somente para leitura.
 *
 *
 * 44. POR QUE NAO DEIXAR EDITAR A JTable?
 *
 * Imagine que o usuario altere visualmente:
 *
 * 18:00
 *
 * para:
 *
 * 23:00
 *
 * diretamente na tabela.
 *
 * Isso nao significa que:
 *
 * sessao.setHorario("23:00")
 *
 * foi executado.
 *
 * O usuario teria alterado apenas a representacao visual.
 *
 * Como essa tela serve apenas para listar, o ideal e impedir
 * esse tipo de edicao.
 *
 *
 * 45. FLUXO DOS OBJETOS
 *
 * Main
 *   |
 *   | cria as salas
 *   v
 * Sala[]
 *   |
 *   v
 * MenuView
 *   |
 *   | usuario clica em
 *   | "Listar Sessoes"
 *   v
 * SessaoView
 *   |
 *   | recebe Sala[]
 *   v
 * carregarSessoes()
 *
 *
 * 46. FLUXO DENTRO DE carregarSessoes()
 *
 * Sala[]
 *    |
 *    | primeiro for
 *    v
 * Sala
 *    |
 *    | getSessoes()
 *    v
 * Sessao[]
 *    |
 *    | segundo for
 *    v
 * Sessao
 *    |
 *    +---- getHorario()
 *    |
 *    +---- getFilme()
 *             |
 *             +---- getNome()
 *             |
 *             +---- getValor()
 *
 *
 * Ao mesmo tempo:
 *
 * Sala
 *    |
 *    +---- getNumero()
 *    |
 *    +---- getTipoSala()
 *             |
 *             +---- getMultiplicador()
 *
 * Todas essas informacoes formam uma linha da JTable.
 *
 *
 * 47. FLUXO DO CALCULO DO VALOR
 *
 * Sessao
 *    |
 *    v
 * Filme
 *    |
 *    v
 * getValor()
 *    |
 *    |                Sala
 *    |                  |
 *    |                  v
 *    |              TipoSala
 *    |                  |
 *    |                  v
 *    |          getMultiplicador()
 *    |                  |
 *    +--------- x ------+
 *              |
 *              v
 *       valor da sessao
 *              |
 *              v
 *       String.format()
 *              |
 *              v
 *       coluna "Valor"
 *
 *
 * 48. EXEMPLO COMPLETO
 *
 * Considere:
 *
 * Sala 1
 * Tipo: COMUM
 *
 * Sessao:
 * 18:00
 *
 * Filme:
 * Interestelar
 *
 * Valor do filme:
 * R$ 30,00
 *
 * Multiplicador:
 * 1.0
 *
 * Linha apresentada:
 *
 * 1 | COMUM | 18:00 | Interestelar | R$ 30,00
 *
 *
 * Agora:
 *
 * Sala 2
 * Tipo: SALA3D
 *
 * Sessao:
 * 21:30
 *
 * Filme:
 * Avatar
 *
 * Valor:
 * R$ 35,00
 *
 * Multiplicador:
 * 1.2
 *
 * Calculo:
 *
 * 35.00 * 1.2 = 42.00
 *
 * Linha apresentada:
 *
 * 2 | SALA3D | 21:30 | Avatar | R$ 42,00
 *
 *
 * 49. RESUMO DAS RESPONSABILIDADES
 *
 * MenuView:
 *
 * - abrir SessaoView;
 * - passar Sala[].
 *
 *
 * SessaoView:
 *
 * - receber Sala[];
 * - percorrer as salas;
 * - percorrer as sessoes;
 * - montar as linhas da JTable;
 * - apresentar os dados;
 * - voltar para MenuView.
 *
 *
 * Sala:
 *
 * - armazenar suas sessoes;
 * - fornecer numero;
 * - fornecer TipoSala;
 * - fornecer Sessao[].
 *
 *
 * Sessao:
 *
 * - armazenar horario;
 * - armazenar Filme;
 * - armazenar as cadeiras.
 *
 *
 * Filme:
 *
 * - fornecer nome;
 * - fornecer valor.
 *
 *
 * TipoSala:
 *
 * - fornecer o multiplicador correspondente ao tipo da sala.
 *
 *
 * 50. FLUXO GERAL
 *
 *                       Sala[]
 *                          |
 *                          v
 *                    SessaoView
 *                          |
 *                          v
 *                  carregarSessoes()
 *                          |
 *                    primeiro for
 *                          |
 *                          v
 *                        Sala
 *                          |
 *                     getSessoes()
 *                          |
 *                          v
 *                     Sessao[]
 *                          |
 *                     segundo for
 *                          |
 *                          v
 *                       Sessao
 *                      /       \
 *                     /         \
 *                    v           v
 *                 Filme       Horario
 *                  /  \
 *                 v    v
 *               Nome  Valor
 *                       \
 *                        \        TipoSala
 *                         \          |
 *                          \         v
 *                           +-- multiplicador
 *                                   |
 *                                   v
 *                           valor calculado
 *
 * Todas essas informacoes sao adicionadas como uma linha
 * no DefaultTableModel e apresentadas pela JTable.
 *
 * ============================================================
 */
public class SessaoView extends JFrame {

    // Implementar seguindo as orientacoes acima.

}