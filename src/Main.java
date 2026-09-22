// public class Main {

//     // === FILMES ===
//     // O auto da compadecida
//     // Senhor dos aneis, o retorno do rei
//     // O  Fabuloso Destino de Amélie Poulain
//     // Bacurau
//     // A Sociedade dos poetas mortos
//     // Ainda Estou Aqui
    
//     public static void main(String[] args){
//         Filme filme1 = new Filme("O Auto da Compadcida", 104, "Dois amigos nordestinos enfrentam situações de pobreza, esperteza e confusão enquanto tentam sobreviver e conseguir dinheiro.", 20.00);
//         Filme filme2 = new Filme("O Senhor dos Anéis: O Retorno do Rei", 201, "Frodo e Sam seguem em direção à Montanha da Perdição enquanto seus aliados enfrentam a batalha final contra as forças de Sauron.", 20.00);
//         Filme filme3 = new Filme("O Fabuloso Destino de Amélie Poulain", 122, "Amélie é uma jovem parisiense que decide ajudar secretamente as pessoas ao seu redor enquanto busca transformar sua própria vida.", 20.00);
//         Filme filme4 = new Filme("Bacurau", 131, "Os moradores de uma pequena cidade do sertão percebem que sua comunidade desapareceu dos mapas e precisam se unir diante de uma ameaça misteriosa.", 20.00);
//         Filme filme5 = new Filme("A Sociedade dos Poetas Mortos", 128, "Um professor de literatura inspira seus alunos a enxergar a vida de uma maneira diferente e a buscar seus próprios caminhos.", 20.00);
//         Filme filme6 = new Filme("Ainda Estou Aqui", 135, "Uma família enfrenta as consequências da ditadura militar brasileira enquanto busca preservar sua memória e sua história.", 20.00);
    
//         //agrupando filmes para um vetor (array de objtos FILMe)
//         Filme[] filmes = {filme1, filme2, filme3, filme4, filme5, filme6};
        
//         //exibir os dados dos filmes
//         // para cada 'Filme f (f é a variavel)' em filmes faça... + obj (for-each)
//         for(Filme f : filmes){
//             System.out.println("Filme: " + f.getNome());     System.out.println();
//             System.out.println("Duracao: " + f.getDuracao() + " minutos");
//             System.out.println("Sinopse: " + f.getSinopse());
//             System.out.println("Valor: R$ " + f.getValor());
//             System.out.println("=============================================================");
//         }
        
    
//     }

// }






// teste exemplo
import enums.TipoSala;

import model.Filme;
import model.Sala;
import model.Sessao;

import view.MenuView;

import javax.swing.SwingUtilities;

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR A CLASSE Main
 * ============================================================
 *
 * OBJETIVO:
 *
 * Criar o ponto inicial da aplicacao do cinema.
 *
 * A classe Main sera responsavel por iniciar o programa,
 * criar os dados iniciais necessarios para testar o sistema
 * e abrir a tela principal.
 *
 * Nesta versao do projeto ainda nao existe Controller.
 *
 * Portanto, Main sera responsavel inicialmente por:
 *
 * - iniciar o Swing;
 * - criar alguns objetos Filme;
 * - armazenar os filmes em um vetor;
 * - criar as Salas;
 * - definir o TipoSala de cada sala;
 * - criar as Sessoes;
 * - relacionar cada Sessao com um Filme;
 * - adicionar as Sessoes nas Salas;
 * - armazenar as salas em um vetor;
 * - criar MenuView;
 * - passar Filme[] e Sala[] para MenuView;
 * - tornar MenuView visivel.
 *
 *
 * 1. IMPORTACOES
 *
 * Main precisara utilizar classes dos pacotes:
 *
 * enums
 * model
 * view
 *
 * Portanto, importar:
 *
 * TipoSala
 * Filme
 * Sala
 * Sessao
 * MenuView
 *
 * Tambem sera necessario:
 *
 * SwingUtilities
 *
 * para iniciar corretamente a interface Swing.
 *
 *
 * 2. CLASSE Main
 *
 * Criar:
 *
 * public class Main
 *
 * Essa classe nao precisa herdar de JFrame.
 *
 * Main nao representa uma janela.
 *
 * Ela representa o ponto inicial da aplicacao.
 *
 *
 * 3. METODO main()
 *
 * Criar:
 *
 * public static void main(String[] args)
 *
 * Esse e o metodo que sera executado quando o programa
 * iniciar.
 *
 * O Java procura esse metodo para saber por onde iniciar
 * a execucao.
 *
 *
 * 4. INICIAR O SWING
 *
 * Utilizar:
 *
 * SwingUtilities.invokeLater(...)
 *
 * para executar a criacao da interface grafica na thread
 * apropriada do Swing.
 *
 * A estrutura sera aproximadamente:
 *
 * main()
 *   |
 *   v
 * SwingUtilities.invokeLater(...)
 *   |
 *   v
 * criar os objetos
 *   |
 *   v
 * criar MenuView
 *   |
 *   v
 * mostrar MenuView
 *
 *
 * 5. POR QUE USAR SwingUtilities.invokeLater()?
 *
 * Aplicacoes Swing possuem uma thread responsavel por
 * trabalhar com os componentes graficos.
 *
 * Essa thread e conhecida como:
 *
 * Event Dispatch Thread
 *
 * ou:
 *
 * EDT
 *
 * Utilizar invokeLater() garante que a criacao da interface
 * grafica seja colocada nessa thread.
 *
 *
 * 6. PRIMEIRA ETAPA: CRIAR OS FILMES
 *
 * Criar alguns objetos Filme para que o sistema possua dados
 * iniciais para teste.
 *
 * Cada Filme deve receber os dados exigidos pelo construtor
 * atual da classe Filme.
 *
 * Nesta versao:
 *
 * - nome;
 * - duracao;
 * - sinopse;
 * - valor.
 *
 *
 * 7. PRIMEIRO FILME
 *
 * Criar um objeto Filme representando:
 *
 * Interestelar
 *
 * com:
 *
 * nome:
 * "Interestelar"
 *
 * duracao:
 * 169
 *
 * sinopse:
 * "Viagem espacial."
 *
 * valor:
 * 30.00
 *
 *
 * 8. SEGUNDO FILME
 *
 * Criar outro objeto Filme representando:
 *
 * Batman
 *
 * com:
 *
 * nome:
 * "Batman"
 *
 * duracao:
 * 176
 *
 * sinopse:
 * "Filme do Batman."
 *
 * valor:
 * 25.00
 *
 *
 * 9. TERCEIRO FILME
 *
 * Criar outro objeto Filme representando:
 *
 * Avatar
 *
 * com:
 *
 * nome:
 * "Avatar"
 *
 * duracao:
 * 192
 *
 * sinopse:
 * "Pandora."
 *
 * valor:
 * 35.00
 *
 *
 * 10. POR QUE CRIAR ESSES FILMES NO Main?
 *
 * Neste momento ainda nao existe uma tela de cadastro de
 * filmes nem carregamento de banco de dados.
 *
 * Portanto, precisamos criar alguns objetos manualmente para
 * conseguir testar:
 *
 * - FilmeView;
 * - SessaoView;
 * - CompraView;
 * - CriticaView.
 *
 * Esses filmes funcionam como dados iniciais de teste.
 *
 *
 * 11. CRIAR O VETOR DE FILMES
 *
 * Depois de criar os objetos individualmente, criar:
 *
 * Filme[] filmes
 *
 * com tamanho:
 *
 * 10
 *
 * Depois colocar os objetos nas primeiras posicoes.
 *
 * A estrutura sera:
 *
 * Filme[] filmes
 *
 * [0] -> Interestelar
 * [1] -> Batman
 * [2] -> Avatar
 * [3] -> null
 * [4] -> null
 * ...
 * [9] -> null
 *
 *
 * 12. POR QUE EXISTEM POSICOES null?
 *
 * O vetor possui capacidade para 10 filmes.
 *
 * Entretanto, inicialmente existem apenas 3.
 *
 * Portanto, as outras posicoes permanecem:
 *
 * null
 *
 * Isso explica por que as Views precisam verificar:
 *
 * filmes[i] != null
 *
 * antes de acessar os dados de um Filme.
 *
 *
 * 13. SEGUNDA ETAPA: CRIAR AS SALAS
 *
 * Depois dos filmes, criar as salas do cinema.
 *
 * Cada Sala deve receber:
 *
 * - numero;
 * - TipoSala.
 *
 *
 * 14. PRIMEIRA SALA
 *
 * Criar:
 *
 * Sala 1
 *
 * utilizando:
 *
 * TipoSala.COMUM
 *
 * Portanto, essa sala tera multiplicador:
 *
 * 1.0
 *
 *
 * 15. SEGUNDA SALA
 *
 * Criar:
 *
 * Sala 2
 *
 * utilizando:
 *
 * TipoSala.SALA3D
 *
 * O multiplicador definido no enum para esse tipo sera
 * utilizado posteriormente no calculo do ingresso.
 *
 *
 * 16. TERCEIRA SALA
 *
 * Criar:
 *
 * Sala 3
 *
 * utilizando:
 *
 * TipoSala.XD
 *
 *
 * 17. RELACAO ENTRE Sala E TipoSala
 *
 * A estrutura sera:
 *
 * Sala
 *   |
 *   +---- numero
 *   |
 *   +---- TipoSala
 *            |
 *            +---- multiplicador
 *
 * Exemplo:
 *
 * Sala 1
 *   |
 *   +---- COMUM
 *           |
 *           +---- 1.0
 *
 *
 * 18. POR QUE UTILIZAR O ENUM?
 *
 * Nao criar Strings como:
 *
 * "comum"
 * "3D"
 * "XD"
 *
 * diretamente no Main para representar o tipo.
 *
 * O projeto ja possui:
 *
 * enum TipoSala
 *
 * Portanto, utilizar os valores existentes nesse enum.
 *
 *
 * 19. TERCEIRA ETAPA: CRIAR AS SESSOES
 *
 * Depois de criar Filme e Sala, criar os objetos Sessao.
 *
 * Cada Sessao deve receber:
 *
 * - horario;
 * - Filme.
 *
 * Isso significa que cada sessao esta associada a um filme.
 *
 *
 * 20. PRIMEIRA SESSAO
 *
 * Criar uma sessao:
 *
 * horario:
 * 18:00
 *
 * filme:
 * Interestelar
 *
 *
 * 21. SEGUNDA SESSAO
 *
 * Criar uma sessao:
 *
 * horario:
 * 20:00
 *
 * filme:
 * Batman
 *
 *
 * 22. TERCEIRA SESSAO
 *
 * Criar uma sessao:
 *
 * horario:
 * 21:30
 *
 * filme:
 * Avatar
 *
 *
 * 23. QUARTA SESSAO
 *
 * Criar uma sessao:
 *
 * horario:
 * 22:00
 *
 * filme:
 * Interestelar
 *
 *
 * 24. IMPORTANTE: REUTILIZAR OS OBJETOS Filme
 *
 * Ao criar uma Sessao, nao criar novamente o Filme.
 *
 * Evitar:
 *
 * new Sessao(
 *        "18:00",
 *        new Filme(...)
 * )
 *
 * se Interestelar ja foi criado anteriormente.
 *
 * Utilizar o mesmo objeto:
 *
 * filme1
 *
 * A ideia e:
 *
 * Filme filme1
 *      |
 *      +---------> Sessao 18:00
 *      |
 *      +---------> Sessao 22:00
 *
 * As duas sessoes podem apontar para o mesmo objeto Filme.
 *
 *
 * 25. POR QUE REUTILIZAR O MESMO Filme?
 *
 * Imagine que Interestelar receba posteriormente uma nova
 * nota.
 *
 * Se todas as sessoes utilizam o mesmo objeto Filme, todas
 * continuam relacionadas ao filme atualizado.
 *
 * Se fossem criadas copias diferentes de Interestelar,
 * poderiamos ter informacoes diferentes representando o
 * mesmo filme.
 *
 *
 * 26. RELACAO Sessao E Filme
 *
 * Depois da criacao:
 *
 * Sessao 1
 *    |
 *    +---- horario = 18:00
 *    |
 *    +---- filme ---------> Interestelar
 *
 *
 * Sessao 2
 *    |
 *    +---- horario = 20:00
 *    |
 *    +---- filme ---------> Batman
 *
 *
 * Sessao 3
 *    |
 *    +---- horario = 21:30
 *    |
 *    +---- filme ---------> Avatar
 *
 *
 * Sessao 4
 *    |
 *    +---- horario = 22:00
 *    |
 *    +---- filme ---------> Interestelar
 *
 *
 * 27. AS CADEIRAS SAO CRIADAS ONDE?
 *
 * Main nao precisa criar manualmente as 150 cadeiras de
 * cada sessao.
 *
 * A propria classe Sessao ja possui essa responsabilidade
 * em seu construtor.
 *
 * Quando fazemos conceitualmente:
 *
 * new Sessao(
 *        horario,
 *        filme
 * )
 *
 * o construtor da Sessao cria:
 *
 * Cadeira[10][15]
 *
 * e inicializa os objetos Cadeira.
 *
 *
 * Portanto, evitar criar no Main:
 *
 * Cadeira cadeira1...
 * Cadeira cadeira2...
 *
 * Isso pertence a Sessao.
 *
 *
 * 28. QUARTA ETAPA: ADICIONAR SESSOES NAS SALAS
 *
 * Criar a Sessao nao significa automaticamente que ela
 * pertence a uma Sala.
 *
 * Depois de criar as sessoes, adicionar cada uma na sala
 * correspondente.
 *
 * Utilizar o metodo existente:
 *
 * adicionarSessao(...)
 *
 *
 * 29. DISTRIBUICAO DAS SESSOES
 *
 * Utilizar a seguinte organizacao:
 *
 * Sala 1 - COMUM
 *
 * - 18:00 -> Interestelar
 * - 20:00 -> Batman
 *
 *
 * Sala 2 - SALA3D
 *
 * - 21:30 -> Avatar
 *
 *
 * Sala 3 - XD
 *
 * - 22:00 -> Interestelar
 *
 *
 * 30. RELACAO COMPLETA ATE AQUI
 *
 *                  Filme
 *                    ^
 *                    |
 *                  Sessao
 *                    ^
 *                    |
 *                   Sala
 *
 * Mais detalhadamente:
 *
 * Sala 1
 *   |
 *   +---- Sessao 18:00
 *   |         |
 *   |         +---- Interestelar
 *   |
 *   +---- Sessao 20:00
 *             |
 *             +---- Batman
 *
 *
 * Sala 2
 *   |
 *   +---- Sessao 21:30
 *             |
 *             +---- Avatar
 *
 *
 * Sala 3
 *   |
 *   +---- Sessao 22:00
 *             |
 *             +---- Interestelar
 *
 *
 * 31. UTILIZAR adicionarSessao()
 *
 * Nao acessar diretamente o vetor interno de sessoes da
 * Sala para inserir os objetos.
 *
 * Evitar algo como:
 *
 * sala.getSessoes()[0] = sessao;
 *
 * Utilizar:
 *
 * sala.adicionarSessao(sessao)
 *
 * Isso preserva o encapsulamento e utiliza a operacao que
 * a propria classe Sala oferece para inserir uma Sessao.
 *
 *
 * 32. QUINTA ETAPA: CRIAR O VETOR DE SALAS
 *
 * Depois de configurar as salas, criar:
 *
 * Sala[] salas
 *
 * com capacidade:
 *
 * 10
 *
 * Adicionar:
 *
 * sala1
 * sala2
 * sala3
 *
 * nas primeiras posicoes.
 *
 *
 * 33. ESTRUTURA DO VETOR DE SALAS
 *
 * Sala[] salas
 *
 * [0] -> Sala 1
 * [1] -> Sala 2
 * [2] -> Sala 3
 * [3] -> null
 * ...
 * [9] -> null
 *
 *
 * 34. POR QUE AS VIEWS RECEBEM Sala[]?
 *
 * Porque atraves desse vetor e possivel chegar ate as
 * sessoes e cadeiras.
 *
 * A estrutura e:
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
 *   |
 *   +---- Filme
 *   |
 *   +---- Cadeira[10][15]
 *
 *
 * Portanto, Sala[] sera importante para:
 *
 * - SessaoView;
 * - CompraView;
 * - CadeiraView indiretamente.
 *
 *
 * 35. SEXTA ETAPA: CRIAR MenuView
 *
 * Depois que os dados iniciais estiverem prontos, criar:
 *
 * MenuView
 *
 * O construtor atual deve receber:
 *
 * Filme[] filmes
 * Sala[] salas
 *
 * Portanto, passar os mesmos vetores criados anteriormente.
 *
 *
 * 36. NAO CRIAR NOVOS VETORES AO ABRIR O MENU
 *
 * Evitar:
 *
 * new MenuView(
 *        new Filme[10],
 *        new Sala[10]
 * )
 *
 * Isso faria MenuView receber vetores vazios.
 *
 * Utilizar:
 *
 * filmes
 * salas
 *
 * que ja possuem os objetos criados.
 *
 *
 * 37. MOSTRAR MenuView
 *
 * Depois de criar MenuView, tornar a janela visivel.
 *
 * Utilizar:
 *
 * menu.setVisible(true)
 *
 * Essa sera a primeira janela apresentada ao usuario.
 *
 *
 * 38. FLUXO DE INICIALIZACAO
 *
 * O fluxo geral sera:
 *
 * main()
 *   |
 *   v
 * SwingUtilities.invokeLater()
 *   |
 *   v
 * criar Filme
 *   |
 *   v
 * criar Filme[]
 *   |
 *   v
 * criar Sala
 *   |
 *   v
 * criar Sessao
 *   |
 *   v
 * relacionar Sessao com Filme
 *   |
 *   v
 * adicionar Sessao na Sala
 *   |
 *   v
 * criar Sala[]
 *   |
 *   v
 * criar MenuView
 *   |
 *   v
 * setVisible(true)
 *
 *
 * 39. RELACAO DOS OBJETOS CRIADOS
 *
 * Depois da inicializacao, teremos aproximadamente:
 *
 * Filme[]
 *   |
 *   +---- Interestelar <----------------+
 *   |         ^                         |
 *   |         |                         |
 *   +---- Batman                        |
 *   |         ^                         |
 *   |         |                         |
 *   +---- Avatar                        |
 *             ^                         |
 *             |                         |
 *             |                         |
 * Sala[]      |                         |
 *   |         |                         |
 *   +-- Sala 1                          |
 *   |      |                            |
 *   |      +-- Sessao 18:00 ------------+
 *   |      |
 *   |      +-- Sessao 20:00 -> Batman
 *   |
 *   +-- Sala 2
 *   |      |
 *   |      +-- Sessao 21:30 -> Avatar
 *   |
 *   +-- Sala 3
 *          |
 *          +-- Sessao 22:00 -> Interestelar
 *
 *
 * 40. IMPORTANTE SOBRE REFERENCIAS
 *
 * Os objetos nao sao copiados quando sao passados para
 * MenuView.
 *
 * MenuView recebe referencias para os mesmos vetores.
 *
 * Portanto:
 *
 * Main
 *   |
 *   +---- filmes --------+
 *   |                    |
 *   +---- salas -----+   |
 *                    |   |
 *                    v   v
 *                  MenuView
 *
 * As outras Views receberao essas mesmas referencias a partir
 * de MenuView.
 *
 *
 * 41. CONSEQUENCIA DISSO
 *
 * Se uma cadeira de determinada Sessao for ocupada durante
 * uma compra, o objeto Sessao existente dentro da Sala sera
 * alterado.
 *
 * Quando outra tela consultar aquela mesma Sessao, ela podera
 * encontrar a cadeira ocupada.
 *
 * Isso acontece porque estamos trabalhando com os mesmos
 * objetos e nao criando copias para cada tela.
 *
 *
 * 42. RESPONSABILIDADE DO Main
 *
 * Main deve ser responsavel principalmente por:
 *
 * - iniciar a aplicacao;
 * - criar dados iniciais para teste;
 * - estabelecer as relacoes iniciais entre os objetos;
 * - criar a primeira View;
 * - iniciar a navegacao do programa.
 *
 *
 * 43. O QUE NAO DEVE FICAR NO Main
 *
 * Evitar colocar no Main:
 *
 * - codigo para escolher cadeira;
 * - codigo para realizar compra;
 * - calculo de desconto;
 * - calculo de nota;
 * - codigo para cadastrar usuario;
 * - eventos de botoes das telas;
 * - System.out para substituir as Views;
 * - regras que ja pertencem ao Model.
 *
 * Main deve iniciar os objetos e abrir o sistema.
 *
 *
 * 44. MAIN NAO E UMA VIEW
 *
 * Main nao deve:
 *
 * extends JFrame
 *
 * porque Main nao representa uma janela.
 *
 * Quem representa a janela principal e:
 *
 * MenuView
 *
 * Portanto:
 *
 * Main
 *   |
 *   | inicia
 *   v
 * MenuView extends JFrame
 *
 *
 * 45. MAIN NAO E MODEL
 *
 * Main tambem nao deve substituir:
 *
 * Filme
 * Sala
 * Sessao
 * Compra
 * Usuario
 *
 * Ele apenas cria objetos dessas classes para iniciar a
 * aplicacao.
 *
 *
 * 46. MAIN NAO E CONTROLLER
 *
 * Nesta etapa ainda nao existe uma camada Controller.
 *
 * Isso nao significa que todas as regras devam ser colocadas
 * no Main.
 *
 * Os eventos temporariamente ficam nas Views e as regras
 * continuam no Model.
 *
 * A organizacao atual e:
 *
 * Main
 *   |
 *   v
 * View
 *   |
 *   v
 * Model
 *
 *
 * 47. DADOS DE TESTE
 *
 * Os filmes, salas e sessoes criados diretamente no Main
 * servem principalmente para permitir testar a aplicacao.
 *
 * Posteriormente, se o projeto possuir:
 *
 * - cadastro;
 * - arquivo;
 * - banco de dados;
 * - persistencia;
 *
 * esses dados poderao vir de outra fonte.
 *
 * Por enquanto, criar manualmente no Main e uma forma simples
 * de iniciar o sistema.
 *
 *
 * 48. NAO DUPLICAR FILMES
 *
 * Um detalhe muito importante:
 *
 * se Interestelar ja foi criado como:
 *
 * filme1
 *
 * e existem duas sessoes desse filme, ambas devem receber:
 *
 * filme1
 *
 * Nao criar:
 *
 * filme1
 * filme1Copia
 *
 * para representar o mesmo filme.
 *
 * Assim, se a nota de Interestelar for alterada, todas as
 * sessoes continuam apontando para o mesmo Filme.
 *
 *
 * 49. NAO DUPLICAR SALAS
 *
 * Da mesma forma, depois que:
 *
 * sala1
 *
 * estiver configurada com suas sessoes, colocar esse mesmo
 * objeto dentro de:
 *
 * Sala[] salas
 *
 * Nao criar outra Sala 1 apenas para colocar no vetor.
 *
 *
 * 50. ENCAPSULAMENTO
 *
 * Ao relacionar os objetos, utilizar os metodos publicos
 * disponibilizados pelas classes.
 *
 * Por exemplo:
 *
 * para adicionar uma Sessao na Sala:
 *
 * adicionarSessao(...)
 *
 * em vez de manipular diretamente os atributos internos.
 *
 *
 * 51. VISAO GERAL DA ARQUITETURA ATUAL
 *
 * O projeto esta organizado aproximadamente assim:
 *
 *                  Main
 *                    |
 *                    v
 *                MenuView
 *              /     |      \
 *             /      |       \
 *            v       v        v
 *      FilmeView SessaoView CompraView
 *                              |
 *                              v
 *                         CadeiraView
 *
 *                MenuView
 *                /      \
 *               v        v
 *        UsuarioView   CriticaView
 *
 *
 * Todas essas Views utilizam objetos existentes no Model.
 *
 *
 * 52. RELACAO COM FilmeView
 *
 * MenuView recebe:
 *
 * Filme[]
 *
 * e pode passar esse vetor para:
 *
 * FilmeView
 *
 * Portanto:
 *
 * Main
 *   |
 *   v
 * Filme[]
 *   |
 *   v
 * MenuView
 *   |
 *   v
 * FilmeView
 *
 *
 * 53. RELACAO COM SessaoView
 *
 * MenuView recebe:
 *
 * Sala[]
 *
 * e pode passar para:
 *
 * SessaoView
 *
 * Portanto:
 *
 * Main
 *   |
 *   v
 * Sala[]
 *   |
 *   v
 * MenuView
 *   |
 *   v
 * SessaoView
 *
 *
 * 54. RELACAO COM CompraView
 *
 * CompraView tambem precisa das Salas para localizar as
 * sessoes disponiveis.
 *
 * Portanto:
 *
 * Main
 *   |
 *   v
 * Sala[]
 *   |
 *   v
 * MenuView
 *   |
 *   v
 * CompraView
 *   |
 *   v
 * Sessao
 *   |
 *   v
 * Cadeira[10][15]
 *
 *
 * 55. RELACAO COM CriticaView
 *
 * CriticaView precisa dos Filmes para permitir que um filme
 * seja escolhido para avaliacao.
 *
 * Portanto:
 *
 * Main
 *   |
 *   v
 * Filme[]
 *   |
 *   v
 * MenuView
 *   |
 *   v
 * CriticaView
 *
 *
 * 56. RELACAO COM UsuarioView
 *
 * UsuarioView nao precisa inicialmente receber Filme[] nem
 * Sala[].
 *
 * Ela recebe a MenuView para poder retornar.
 *
 * Entretanto, quando o compartilhamento do usuario atual for
 * implementado, MenuView podera tambem guardar esse Usuario.
 *
 *
 * 57. PROXIMA INTEGRACAO: USUARIO ATUAL
 *
 * Nesta versao, Main cria:
 *
 * Filme[]
 * Sala[]
 *
 * mas ainda nao cria nem compartilha um Usuario atual.
 *
 * O Usuario sera criado posteriormente em UsuarioView.
 *
 * Quando essa integracao for feita, o fluxo pode ser:
 *
 * Main
 *   |
 *   v
 * MenuView
 *   |
 *   v
 * UsuarioView
 *   |
 *   | cria
 *   v
 * Usuario / Estudante / Critico
 *   |
 *   v
 * MenuView
 *   |
 *   +----> CompraView
 *   |
 *   +----> CriticaView
 *
 *
 * 58. NAO CRIAR USUARIO FICTICIO NO Main
 *
 * Se a proposta da interface e permitir que o usuario seja
 * cadastrado atraves de UsuarioView, nao criar um Critico ou
 * Estudante ficticio no Main apenas para fazer outras telas
 * funcionarem.
 *
 * O ideal e posteriormente compartilhar o objeto realmente
 * cadastrado.
 *
 *
 * 59. ORDEM IMPORTA
 *
 * Os objetos precisam ser criados em uma ordem coerente.
 *
 * Nao podemos criar uma Sessao com:
 *
 * filme1
 *
 * antes de filme1 existir.
 *
 * Da mesma forma, antes de adicionar:
 *
 * sessao1
 *
 * em sala1, ambos precisam existir.
 *
 * Portanto, uma ordem simples e:
 *
 * 1 - Filme
 * 2 - Filme[]
 * 3 - Sala
 * 4 - Sessao
 * 5 - adicionar Sessao na Sala
 * 6 - Sala[]
 * 7 - MenuView
 *
 *
 * 60. RESUMO DO FLUXO COMPLETO
 *
 *                        main()
 *                           |
 *                           v
 *             SwingUtilities.invokeLater()
 *                           |
 *                           v
 *                    criar Filmes
 *                           |
 *                           v
 *                    criar Filme[]
 *                           |
 *                           v
 *                     criar Salas
 *                           |
 *                           v
 *                    criar Sessoes
 *                           |
 *                           v
 *             associar Filme a Sessao
 *                           |
 *                           v
 *             adicionar Sessao na Sala
 *                           |
 *                           v
 *                     criar Sala[]
 *                           |
 *                           v
 *                    criar MenuView
 *                      /          \
 *                     /            \
 *                    v              v
 *                Filme[]          Sala[]
 *                     \            /
 *                      \          /
 *                       v        v
 *                        MenuView
 *                           |
 *                           v
 *                   setVisible(true)
 *
 * ============================================================
 */
public class Main {

    // Implementar seguindo as orientacoes acima.

}