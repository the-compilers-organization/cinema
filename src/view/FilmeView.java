package view;

import model.Filme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR A CLASSE FilmeView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Criar a tela responsavel por listar os filmes existentes
 * no sistema de cinema.
 *
 * A classe FilmeView deve herdar de JFrame, pois representa
 * uma janela da interface grafica.
 *
 * Essa tela nao sera utilizada para cadastrar ou alterar
 * filmes.
 *
 * Sua principal responsabilidade sera apresentar os dados
 * dos filmes em uma tabela.
 *
 *
 * 1. DADOS QUE DEVEM SER APRESENTADOS
 *
 * Para cada filme existente, mostrar:
 *
 * - nome;
 * - duracao;
 * - sinopse;
 * - valor;
 * - nota.
 *
 * Exemplo:
 *
 * +-----------------------------------------------------------+
 * | Filme        | Duracao | Sinopse      | Valor    | Nota  |
 * +-----------------------------------------------------------+
 * | Interestelar | 169     | Viagem...    | R$ 30,00 | 8.5   |
 * | Batman       | 176     | Filme...     | R$ 25,00 | 9.0   |
 * | Avatar       | 192     | Pandora...   | R$ 35,00 | 7.5   |
 * +-----------------------------------------------------------+
 *
 *
 * 2. ATRIBUTOS PRINCIPAIS
 *
 * A classe deve possuir:
 *
 * - uma referencia para MenuView;
 * - um vetor de objetos Filme;
 * - uma JTable;
 * - um DefaultTableModel;
 * - um JButton para voltar.
 *
 * Portanto, pensar em atributos equivalentes a:
 *
 * MenuView menu
 * Filme[] filmes
 * JTable tabelaFilmes
 * DefaultTableModel modelo
 * JButton btnVoltar
 *
 *
 * 3. POR QUE RECEBER MenuView?
 *
 * FilmeView sera aberta a partir da tela principal.
 *
 * Quando o usuario clicar em "Voltar", queremos retornar para
 * a MESMA MenuView que ja existia.
 *
 * Portanto, FilmeView deve receber e guardar uma referencia
 * para MenuView.
 *
 * Evitar fazer:
 *
 * new MenuView(...)
 *
 * dentro desta classe.
 *
 * Isso criaria uma nova tela principal desnecessariamente.
 *
 *
 * 4. POR QUE RECEBER Filme[]?
 *
 * Os objetos Filme ja foram criados anteriormente pela
 * aplicacao.
 *
 * FilmeView nao deve criar novos filmes apenas para
 * apresenta-los.
 *
 * Ela deve receber o mesmo:
 *
 * Filme[] filmes
 *
 * utilizado pelo restante do sistema.
 *
 * A View apenas consulta os dados desses objetos para
 * apresenta-los na tabela.
 *
 *
 * 5. CONSTRUTOR
 *
 * O construtor deve receber:
 *
 * MenuView menu
 * Filme[] filmes
 *
 * Guardar os dois parametros utilizando this.
 *
 * Ideia:
 *
 * this.menu = menu;
 * this.filmes = filmes;
 *
 *
 * 6. CONFIGURAR A JANELA
 *
 * Configurar o JFrame com:
 *
 * titulo:
 *
 * "Filmes"
 *
 * tamanho aproximado:
 *
 * 850 x 450
 *
 * utilizar:
 *
 * JFrame.DISPOSE_ON_CLOSE
 *
 * e centralizar a janela utilizando:
 *
 * setLocationRelativeTo(null)
 *
 *
 * 7. POR QUE DISPOSE_ON_CLOSE?
 *
 * FilmeView e uma tela secundaria.
 *
 * Portanto, fechar essa janela nao deve necessariamente
 * encerrar todo o programa.
 *
 * Evitar utilizar:
 *
 * JFrame.EXIT_ON_CLOSE
 *
 * pois essa opcao encerraria toda a aplicacao.
 *
 *
 * 8. TABELA DOS FILMES
 *
 * Utilizar:
 *
 * JTable
 *
 * para apresentar os filmes.
 *
 * A JTable sera responsavel pela parte visual da tabela.
 *
 * Entretanto, os dados e as colunas da JTable serao
 * controlados atraves de:
 *
 * DefaultTableModel
 *
 *
 * 9. O QUE E DefaultTableModel?
 *
 * Podemos pensar na relacao desta forma:
 *
 * DefaultTableModel
 *         |
 *         | guarda colunas e linhas
 *         v
 *      JTable
 *         |
 *         | apresenta
 *         v
 *       usuario
 *
 * Portanto:
 *
 * DefaultTableModel = estrutura dos dados da tabela
 *
 * JTable = componente visual que apresenta esses dados
 *
 *
 * 10. CRIAR AS COLUNAS
 *
 * Criar o DefaultTableModel com cinco colunas:
 *
 * "Filme"
 * "Duracao"
 * "Sinopse"
 * "Valor"
 * "Nota"
 *
 * Inicialmente a quantidade de linhas deve ser:
 *
 * 0
 *
 * pois os filmes serao adicionados posteriormente pelo
 * metodo carregarFilmes().
 *
 *
 * 11. CRIAR A JTable
 *
 * Depois de criar o DefaultTableModel, criar a JTable
 * utilizando esse modelo.
 *
 * A ideia e:
 *
 * DefaultTableModel modelo
 *            |
 *            v
 *     new JTable(modelo)
 *
 * Assim, quando uma linha for adicionada ao modelo,
 * automaticamente ela aparecera na JTable.
 *
 *
 * 12. JScrollPane
 *
 * Colocar a JTable dentro de um:
 *
 * JScrollPane
 *
 * Isso e importante porque, se existirem muitos filmes,
 * o usuario podera utilizar a barra de rolagem.
 *
 * A estrutura sera:
 *
 * JScrollPane
 *      |
 *      v
 *    JTable
 *
 *
 * 13. ORGANIZACAO DA TELA
 *
 * A tabela pode ocupar a regiao central da janela.
 *
 * O botao Voltar pode ficar na parte inferior.
 *
 * A organizacao pode ser:
 *
 *                  CENTER
 * +-------------------------------------------------------+
 * | Filme    | Duracao | Sinopse | Valor      | Nota     |
 * +-------------------------------------------------------+
 * | Filme 1  | ...     | ...     | ...        | ...      |
 * | Filme 2  | ...     | ...     | ...        | ...      |
 * | Filme 3  | ...     | ...     | ...        | ...      |
 * +-------------------------------------------------------+
 *
 *                  SOUTH
 * +-------------------------------------------------------+
 * |                     [ Voltar ]                        |
 * +-------------------------------------------------------+
 *
 *
 * 14. BOTAO VOLTAR
 *
 * Criar um JButton com o texto:
 *
 * "Voltar"
 *
 * Esse botao deve possuir um ActionListener que chama:
 *
 * voltar()
 *
 *
 * 15. METODO carregarFilmes()
 *
 * Criar:
 *
 * private void carregarFilmes()
 *
 * Esse metodo sera responsavel por transferir os dados dos
 * objetos Filme para o DefaultTableModel.
 *
 * A ideia e:
 *
 * Filme[]
 *    |
 *    | percorrer
 *    v
 * Filme
 *    |
 *    | getters
 *    v
 * DefaultTableModel
 *    |
 *    v
 * JTable
 *
 *
 * 16. LIMPAR A TABELA ANTES DE CARREGAR
 *
 * Antes de adicionar os filmes, limpar as linhas existentes
 * no modelo.
 *
 * Utilizar:
 *
 * modelo.setRowCount(0)
 *
 * Isso evita que os mesmos filmes sejam duplicados caso o
 * metodo carregarFilmes() seja executado novamente.
 *
 *
 * 17. PERCORRER O VETOR DE FILMES
 *
 * Utilizar um for para percorrer:
 *
 * Filme[] filmes
 *
 * O laco deve ir da primeira ate a ultima posicao do vetor.
 *
 * Para cada posicao, verificar:
 *
 * filmes[i] != null
 *
 * Somente os objetos diferentes de null devem ser adicionados
 * a tabela.
 *
 *
 * 18. POR QUE VERIFICAR null?
 *
 * Imagine:
 *
 * Filme[] filmes = new Filme[10];
 *
 * mas somente tres filmes foram cadastrados.
 *
 * O vetor poderia estar assim:
 *
 * [0] -> Interestelar
 * [1] -> Batman
 * [2] -> Avatar
 * [3] -> null
 * [4] -> null
 * ...
 * [9] -> null
 *
 * Portanto, nao podemos tentar executar:
 *
 * filmes[i].getNome()
 *
 * quando filmes[i] for null.
 *
 * Isso poderia gerar:
 *
 * NullPointerException
 *
 *
 * 19. ADICIONAR UMA LINHA NA TABELA
 *
 * Para cada Filme existente, adicionar uma nova linha ao
 * DefaultTableModel.
 *
 * Utilizar:
 *
 * modelo.addRow(...)
 *
 * Cada linha deve possuir cinco valores, seguindo exatamente
 * a mesma ordem das colunas:
 *
 * 1 - nome;
 * 2 - duracao;
 * 3 - sinopse;
 * 4 - valor;
 * 5 - nota.
 *
 *
 * 20. OBTER O NOME
 *
 * Utilizar o getter da classe Filme:
 *
 * getNome()
 *
 * Esse valor sera colocado na coluna:
 *
 * "Filme"
 *
 *
 * 21. OBTER A DURACAO
 *
 * Utilizar:
 *
 * getDuracao()
 *
 * Esse valor sera colocado na coluna:
 *
 * "Duracao"
 *
 *
 * 22. OBTER A SINOPSE
 *
 * Utilizar:
 *
 * getSinopse()
 *
 * Esse valor sera colocado na coluna:
 *
 * "Sinopse"
 *
 *
 * 23. OBTER O VALOR
 *
 * Utilizar:
 *
 * getValor()
 *
 * Para apresentar o valor como dinheiro, pode ser utilizado:
 *
 * String.format(
 *        "R$ %.2f",
 *        valor
 * )
 *
 * Por exemplo:
 *
 * valor = 30
 *
 * sera apresentado aproximadamente como:
 *
 * R$ 30,00
 *
 * dependendo da configuracao regional utilizada pelo Java.
 *
 *
 * 24. OBTER A NOTA
 *
 * A ultima coluna deve apresentar a nota do filme.
 *
 * Utilizar o metodo existente na classe Filme responsavel por
 * fornecer essa informacao.
 *
 * Na estrutura atual pode existir:
 *
 * getNota()
 *
 * ou o calculo atraves de:
 *
 * mediaDasNotas()
 *
 * Utilizar o metodo que estiver sendo adotado pela versao
 * atual do Model.
 *
 * A View nao deve calcular manualmente a media percorrendo o
 * vetor de notas.
 *
 *
 * 25. EXEMPLO CONCEITUAL DE UMA LINHA
 *
 * Para um Filme:
 *
 * nome:
 * Interestelar
 *
 * duracao:
 * 169
 *
 * sinopse:
 * Viagem espacial.
 *
 * valor:
 * 30.00
 *
 * nota:
 * 8.5
 *
 * a linha inserida seria conceitualmente:
 *
 * ---------------------------------------------------------
 * Interestelar | 169 | Viagem espacial. | R$ 30,00 | 8.5
 * ---------------------------------------------------------
 *
 *
 * 26. CHAMAR carregarFilmes()
 *
 * Depois de construir:
 *
 * - DefaultTableModel;
 * - JTable;
 * - componentes da janela;
 *
 * chamar:
 *
 * carregarFilmes()
 *
 * Assim a tabela ja sera preenchida quando FilmeView for
 * apresentada.
 *
 *
 * 27. EVENTO DO BOTAO VOLTAR
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
 * 28. METODO voltar()
 *
 * Criar:
 *
 * private void voltar()
 *
 * Esse metodo deve:
 *
 * - tornar a MenuView novamente visivel;
 * - fechar a FilmeView atual.
 *
 * A ideia e:
 *
 * menu.setVisible(true);
 *
 * e depois:
 *
 * dispose();
 *
 *
 * 29. POR QUE USAR dispose()?
 *
 * A FilmeView nao sera mais necessaria depois que o usuario
 * voltar ao menu.
 *
 * Portanto, pode fechar essa janela utilizando:
 *
 * dispose()
 *
 * Isso nao encerra todo o programa.
 *
 * Apenas encerra aquela instancia de FilmeView.
 *
 *
 * 30. NAO CRIAR UMA NOVA MenuView
 *
 * Evitar:
 *
 * MenuView menu =
 *        new MenuView(...);
 *
 * dentro do metodo voltar().
 *
 * A FilmeView ja recebeu a MenuView que a abriu.
 *
 * Portanto, deve reutilizar:
 *
 * menu
 *
 *
 * 31. RESPONSABILIDADE DA FilmeView
 *
 * FilmeView deve ser responsavel principalmente por:
 *
 * - construir a interface;
 * - receber o vetor Filme[];
 * - percorrer os filmes existentes;
 * - apresentar os dados em uma JTable;
 * - responder ao botao Voltar.
 *
 *
 * 32. O QUE NAO DEVE FICAR NA FilmeView
 *
 * Evitar colocar nesta View regras como:
 *
 * - calcular a media manualmente;
 * - adicionar nota diretamente ao vetor de notas;
 * - adicionar critica diretamente;
 * - alterar o valor do filme;
 * - cadastrar filmes;
 * - modificar regras do cinema.
 *
 * Essas responsabilidades pertencem ao Model ou a outras
 * funcionalidades do sistema.
 *
 *
 * 33. RELACAO ENTRE VIEW E MODEL
 *
 * A relacao principal sera:
 *
 * FilmeView
 *      |
 *      | possui referencia
 *      v
 * Filme[]
 *      |
 *      +---- Filme
 *      |
 *      +---- Filme
 *      |
 *      +---- Filme
 *
 *
 * A View consulta cada objeto atraves dos getters:
 *
 * Filme
 *   |
 *   +-- getNome()
 *   |
 *   +-- getDuracao()
 *   |
 *   +-- getSinopse()
 *   |
 *   +-- getValor()
 *   |
 *   +-- getNota()
 *        ou metodo equivalente
 *
 *              |
 *              v
 *       DefaultTableModel
 *              |
 *              v
 *            JTable
 *
 *
 * 34. FLUXO PARA ABRIR A TELA
 *
 * Main
 *   |
 *   | cria Filme[]
 *   v
 * MenuView
 *   |
 *   | usuario clica
 *   | "Listar Filmes"
 *   v
 * FilmeView
 *   |
 *   | recebe o mesmo Filme[]
 *   v
 * carregarFilmes()
 *   |
 *   v
 * JTable
 *
 *
 * 35. FLUXO PARA VOLTAR
 *
 * FilmeView
 *     |
 *     | usuario clica em Voltar
 *     v
 * voltar()
 *     |
 *     +----> menu.setVisible(true)
 *     |
 *     +----> dispose()
 *                  |
 *                  v
 *          FilmeView e fechada
 *
 *
 * 36. ATUALIZACAO DAS NOTAS
 *
 * Existe uma relacao importante com CriticaView.
 *
 * Se posteriormente um Critico atribuir uma nova nota a um
 * Filme, o objeto Filme sera alterado.
 *
 * Como FilmeView recebe os mesmos objetos Filme utilizados
 * pelo sistema, ao abrir novamente a tela e executar:
 *
 * carregarFilmes()
 *
 * a tabela deve consultar novamente os dados desses objetos.
 *
 * Portanto, nao criar copias desnecessarias dos filmes.
 *
 *
 * 37. TABELA APENAS PARA VISUALIZACAO
 *
 * Como esta tela possui a finalidade de LISTAR filmes, pode
 * ser interessante impedir que o usuario edite diretamente
 * as celulas da JTable.
 *
 * Uma JTable criada com DefaultTableModel normalmente permite
 * editar as celulas.
 *
 * Como melhoria, posteriormente pode ser utilizado um
 * DefaultTableModel que sobrescreva:
 *
 * isCellEditable(...)
 *
 * retornando false.
 *
 * Isso nao e obrigatorio para montar a primeira versao, mas
 * evita que o usuario altere visualmente os dados da tabela
 * sem alterar os objetos Filme reais.
 *
 *
 * 38. DIFERENCA ENTRE ALTERAR A TABELA E ALTERAR O MODEL
 *
 * Se o usuario conseguir escrever diretamente:
 *
 * R$ 100,00
 *
 * em uma celula da JTable, isso nao significa necessariamente
 * que:
 *
 * filme.setValor(100)
 *
 * foi executado.
 *
 * Por isso, como esta tela serve apenas para listagem, o ideal
 * e deixar a tabela somente para leitura.
 *
 *
 * 39. RESUMO DAS RESPONSABILIDADES
 *
 * MenuView:
 *
 * - abrir FilmeView;
 * - passar Filme[].
 *
 *
 * FilmeView:
 *
 * - receber Filme[];
 * - criar JTable;
 * - carregar os filmes;
 * - mostrar seus dados;
 * - voltar para MenuView.
 *
 *
 * Filme:
 *
 * - armazenar os dados do filme;
 * - fornecer seus dados atraves dos getters;
 * - manter as regras relacionadas a notas e criticas.
 *
 *
 * 40. FLUXO GERAL
 *
 *                 Filme[]
 *                    |
 *                    v
 *               FilmeView
 *                    |
 *                    v
 *           carregarFilmes()
 *                    |
 *          +---------+---------+
 *          |         |         |
 *          v         v         v
 *       Filme 1   Filme 2   Filme 3
 *          |         |         |
 *          +---------+---------+
 *                    |
 *                  getters
 *                    |
 *                    v
 *          DefaultTableModel
 *                    |
 *                    v
 *                 JTable
 *                    |
 *                    v
 *                 usuario
 *
 * ============================================================
 */
public class FilmeView extends JFrame {

    // Implementar seguindo as orientacoes acima.

}