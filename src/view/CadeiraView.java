package view;

import model.Cadeira;
import model.Sessao;

import javax.swing.*;
import java.awt.*;

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR A CLASSE CadeiraView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Criar a tela responsavel por exibir as cadeiras de uma
 * determinada Sessao e permitir que o usuario escolha uma
 * cadeira disponivel.
 *
 * A classe CadeiraView deve herdar de JFrame, pois representa
 * uma nova janela da interface grafica.
 *
 * A tela deve representar graficamente a matriz de cadeiras
 * existente dentro da classe Sessao.
 *
 *
 * 1. ATRIBUTOS
 *
 * A classe deve possuir:
 *
 * - uma referencia para CompraView;
 * - uma referencia para Sessao;
 * - uma matriz de JButton para representar as cadeiras;
 * - uma variavel para guardar a linha selecionada;
 * - uma variavel para guardar a coluna selecionada;
 * - um botao Confirmar;
 * - um botao Voltar.
 *
 *
 * A matriz de botoes deve possuir as mesmas dimensoes da
 * matriz de cadeiras da Sessao:
 *
 * 10 linhas
 * 15 colunas
 *
 * Portanto:
 *
 * JButton[][] botoesCadeiras
 *
 * sera uma matriz 10 x 15.
 *
 *
 * 2. POR QUE RECEBER CompraView?
 *
 * O construtor deve receber a CompraView que abriu esta tela.
 *
 * Isso sera necessario porque, depois que o usuario confirmar
 * uma cadeira, CadeiraView precisa informar para CompraView
 * qual linha e qual coluna foram escolhidas.
 *
 * Tambem permite que o botao "Voltar" retorne para a mesma
 * CompraView, sem criar uma nova tela de compra.
 *
 *
 * 3. POR QUE RECEBER Sessao?
 *
 * O construtor tambem deve receber um objeto Sessao.
 *
 * A Sessao possui a matriz de objetos Cadeira correspondente
 * aquela sessao do filme.
 *
 * Portanto, CadeiraView nao deve criar uma nova Sessao nem
 * uma nova matriz de objetos Cadeira.
 *
 * Ela deve trabalhar com a Sessao que foi escolhida
 * anteriormente na CompraView.
 *
 *
 * 4. CONSTRUTOR
 *
 * O construtor deve receber:
 *
 * CompraView compraView
 * Sessao sessao
 *
 * Guardar os parametros nos atributos utilizando this.
 *
 * Ideia:
 *
 * this.compraView = compraView;
 * this.sessao = sessao;
 *
 *
 * 5. INICIALIZAR A CADEIRA SELECIONADA
 *
 * Quando a tela abrir, nenhuma cadeira foi escolhida.
 *
 * Por isso:
 *
 * linhaSelecionada = -1;
 * colunaSelecionada = -1;
 *
 * O valor -1 sera utilizado para representar:
 *
 * "nenhuma cadeira selecionada"
 *
 * Isso sera verificado posteriormente no metodo confirmar().
 *
 *
 * 6. CONFIGURAR A JANELA
 *
 * Configurar o JFrame definindo:
 *
 * - titulo "Escolher Cadeira";
 * - tamanho da janela;
 * - operacao de fechamento;
 * - centralizacao da janela.
 *
 * Como CadeiraView e uma tela secundaria, utilizar
 * DISPOSE_ON_CLOSE em vez de EXIT_ON_CLOSE.
 *
 * EXIT_ON_CLOSE encerraria todo o programa.
 *
 *
 * 7. LAYOUT PRINCIPAL
 *
 * Utilizar BorderLayout para dividir a janela em regioes.
 *
 * A organizacao pode ser:
 *
 *                  NORTH
 *              +-----------+
 *              |   TELA    |
 *              +-----------+
 *
 *                  CENTER
 *       +---------------------------+
 *       | [01][02][03] ... [15]     |
 *       | [01][02][03] ... [15]     |
 *       |          ...              |
 *       | [01][02][03] ... [15]     |
 *       +---------------------------+
 *
 *                  SOUTH
 *              +-----------+
 *              | Voltar    |
 *              | Confirmar |
 *              +-----------+
 *
 *
 * 8. INDICACAO DA TELA DO CINEMA
 *
 * Criar um JLabel com o texto:
 *
 * "TELA"
 *
 * Centralizar o texto e colocá-lo na regiao NORTH do
 * BorderLayout.
 *
 * Pode ser utilizada uma fonte maior e em negrito.
 *
 * Essa indicacao ajuda o usuario a entender a orientacao
 * das cadeiras em relacao a tela do cinema.
 *
 *
 * 9. MATRIZ DE BOTOES
 *
 * Criar:
 *
 * JButton[10][15]
 *
 * Cada JButton da matriz visual deve representar uma
 * Cadeira da matriz existente dentro da Sessao.
 *
 * A relacao sera:
 *
 * botoesCadeiras[0][0]
 *         representa
 * sessao.getCadeiras()[0][0]
 *
 * botoesCadeiras[0][1]
 *         representa
 * sessao.getCadeiras()[0][1]
 *
 * e assim sucessivamente.
 *
 *
 * 10. PAINEL DAS CADEIRAS
 *
 * Criar um JPanel utilizando:
 *
 * GridLayout
 *
 * com:
 *
 * 10 linhas
 * 15 colunas
 *
 * Isso permite que os botoes aparecam na mesma organizacao
 * da matriz de Cadeira da Sessao.
 *
 *
 * 11. CRIAR OS 150 BOTOES
 *
 * Utilizar dois lacos for:
 *
 * for das linhas
 *     for das colunas
 *
 * Dentro deles:
 *
 * - criar o JButton;
 * - colocar o numero da cadeira;
 * - adicionar um ActionListener;
 * - adicionar o botao ao painel.
 *
 * Como existem:
 *
 * 10 linhas x 15 colunas
 *
 * serao criados 150 botoes.
 *
 *
 * 12. NUMERACAO VISUAL DAS CADEIRAS
 *
 * Internamente os indices da matriz comecam em:
 *
 * 0
 *
 * Mas para o usuario e melhor apresentar as cadeiras
 * comecando em:
 *
 * 1
 *
 * Portanto:
 *
 * coluna 0 -> cadeira 01
 * coluna 1 -> cadeira 02
 * ...
 * coluna 14 -> cadeira 15
 *
 * Pode ser utilizado String.format("%02d", coluna + 1)
 * para apresentar:
 *
 * 01
 * 02
 * 03
 * ...
 * 15
 *
 *
 * 13. CUIDADO COM linha E coluna NO ActionListener
 *
 * Dentro dos lacos, criar variaveis auxiliares para guardar
 * a linha e a coluna correspondentes ao botao.
 *
 * Exemplo da ideia:
 *
 * int l = linha;
 * int c = coluna;
 *
 * O ActionListener daquele botao deve chamar:
 *
 * selecionarCadeira(l, c)
 *
 * Assim cada botao sabe qual posicao da matriz representa.
 *
 *
 * 14. METODO selecionarCadeira()
 *
 * Criar:
 *
 * private void selecionarCadeira(
 *         int linha,
 *         int coluna)
 *
 * Esse metodo sera executado quando o usuario clicar em uma
 * cadeira.
 *
 * Ele deve guardar:
 *
 * linhaSelecionada = linha;
 * colunaSelecionada = coluna;
 *
 * Depois pode apresentar uma mensagem informando a cadeira
 * escolhida.
 *
 * Lembrar novamente:
 *
 * internamente:
 * linha 0, coluna 0
 *
 * visualmente:
 * linha 1, cadeira 1
 *
 * Portanto, para mostrar ao usuario, utilizar + 1.
 *
 *
 * 15. METODO carregarCadeiras()
 *
 * Criar:
 *
 * private void carregarCadeiras()
 *
 * Esse metodo deve obter a matriz real de cadeiras atraves
 * da Sessao:
 *
 * Cadeira[][] cadeiras = sessao.getCadeiras();
 *
 * Depois percorrer toda a matriz utilizando dois lacos for.
 *
 * Para cada cadeira, verificar:
 *
 * cadeiras[linha][coluna].isOcupada()
 *
 *
 * 16. CADEIRA OCUPADA
 *
 * Se isOcupada() retornar true:
 *
 * - alterar o texto do botao para "X";
 * - desabilitar o JButton.
 *
 * Exemplo visual:
 *
 * [ X ]
 *
 * Como o botao fica desabilitado, o usuario nao consegue
 * selecionar uma cadeira que ja esta ocupada.
 *
 *
 * 17. CADEIRA LIVRE
 *
 * Se isOcupada() retornar false:
 *
 * - mostrar o numero da cadeira;
 * - deixar o JButton habilitado.
 *
 * Exemplo:
 *
 * [01] [02] [03] [ X ] [05]
 *
 * Nesse exemplo, a cadeira 04 esta ocupada.
 *
 *
 * 18. BOTOES INFERIORES
 *
 * Criar:
 *
 * JButton btnVoltar
 * JButton btnConfirmar
 *
 * Criar um JPanel inferior e adicionar os dois botoes.
 *
 * Depois colocar esse painel na regiao SOUTH do
 * BorderLayout.
 *
 *
 * 19. CARREGAR O ESTADO DAS CADEIRAS
 *
 * Depois de construir todos os botoes da matriz, chamar:
 *
 * carregarCadeiras()
 *
 * Isso e importante porque os JButton precisam representar
 * o estado atual das Cadeiras da Sessao.
 *
 * Nao assumir que todas as cadeiras estao livres.
 *
 *
 * 20. EVENTO DO BOTAO CONFIRMAR
 *
 * O botao Confirmar deve possuir um ActionListener que chama:
 *
 * confirmar()
 *
 *
 * 21. METODO confirmar()
 *
 * Criar:
 *
 * private void confirmar()
 *
 * Primeiro verificar se alguma cadeira foi selecionada.
 *
 * Como -1 representa "nenhuma cadeira", verificar:
 *
 * linhaSelecionada == -1
 * ou
 * colunaSelecionada == -1
 *
 * Se isso acontecer:
 *
 * - mostrar "Selecione uma cadeira.";
 * - interromper o metodo utilizando return.
 *
 *
 * 22. OCUPAR A CADEIRA
 *
 * Se existe uma cadeira selecionada, utilizar o metodo da
 * Sessao responsavel por ocupar a cadeira.
 *
 * Passar:
 *
 * linhaSelecionada
 * colunaSelecionada
 *
 * IMPORTANTE:
 *
 * A CadeiraView nao deve alterar diretamente:
 *
 * cadeira.setOcupada(true)
 *
 * A alteracao deve ser feita atraves do metodo existente na
 * classe Sessao.
 *
 * Assim, a regra continua no Model.
 *
 *
 * 23. SE A CADEIRA FOR OCUPADA COM SUCESSO
 *
 * Se o metodo da Sessao informar que foi possivel ocupar a
 * cadeira:
 *
 * - apresentar uma mensagem de sucesso;
 *
 * - informar para CompraView qual cadeira foi escolhida;
 *
 * - tornar CompraView visivel novamente;
 *
 * - fechar CadeiraView utilizando dispose().
 *
 * A comunicacao com CompraView pode ser feita atraves de um
 * metodo como:
 *
 * setCadeiraSelecionada(
 *         linhaSelecionada,
 *         colunaSelecionada)
 *
 *
 * 24. SE NAO FOR POSSIVEL OCUPAR
 *
 * Se a Sessao informar que nao foi possivel ocupar a cadeira,
 * apresentar uma mensagem de erro.
 *
 * Exemplo:
 *
 * "Nao foi possivel selecionar a cadeira."
 *
 *
 * 25. EVENTO DO BOTAO VOLTAR
 *
 * O botao Voltar deve chamar:
 *
 * voltar()
 *
 *
 * 26. METODO voltar()
 *
 * Criar:
 *
 * private void voltar()
 *
 * Esse metodo deve:
 *
 * - tornar a mesma CompraView visivel novamente;
 * - fechar a CadeiraView atual.
 *
 * Utilizar:
 *
 * compraView.setVisible(true);
 * dispose();
 *
 * NAO criar:
 *
 * new CompraView(...)
 *
 * porque isso criaria uma nova compra e poderia perder
 * informacoes que ja foram selecionadas.
 *
 *
 * 27. RESPONSABILIDADE DA CadeiraView
 *
 * A CadeiraView deve cuidar apenas da parte visual da escolha:
 *
 * - mostrar as cadeiras;
 * - mostrar quais estao ocupadas;
 * - permitir clicar em uma cadeira livre;
 * - guardar temporariamente linha e coluna escolhidas;
 * - solicitar ao Model que ocupe a cadeira;
 * - informar a escolha para CompraView;
 * - permitir voltar.
 *
 * A regra sobre uma cadeira poder ou nao ser ocupada deve
 * continuar na classe Sessao.
 *
 *
 * 28. RELACAO ENTRE VIEW E MODEL
 *
 * O fluxo esperado e:
 *
 * CompraView
 *     |
 *     | escolheu uma Sessao
 *     v
 * CadeiraView
 *     |
 *     | possui referencia para Sessao
 *     v
 * Sessao
 *     |
 *     | possui
 *     v
 * Cadeira[10][15]
 *
 *
 * Cada botao representa uma cadeira:
 *
 * JButton[linha][coluna]
 *          |
 *          v
 * Cadeira[linha][coluna]
 *
 *
 * 29. FLUXO DE SELECAO
 *
 * Usuario clica em [05]
 *          |
 *          v
 * selecionarCadeira(linha, coluna)
 *          |
 *          v
 * linhaSelecionada = linha
 * colunaSelecionada = coluna
 *          |
 *          v
 * Usuario clica em Confirmar
 *          |
 *          v
 * confirmar()
 *          |
 *          v
 * sessao.ocuparCadeira(...)
 *          |
 *          v
 * CompraView recebe linha e coluna
 *          |
 *          v
 * CadeiraView fecha
 *          |
 *          v
 * CompraView aparece novamente
 *
 *
 * 30. IMPORTANTE PARA A PROXIMA ETAPA
 *
 * Nesta primeira versao, a tela trabalha com uma cadeira
 * selecionada.
 *
 * O projeto tambem possui a regra para compra de varios
 * bilhetes e busca de cadeiras juntas.
 *
 * Depois, CadeiraView podera ser adaptada para receber a
 * quantidade de bilhetes e utilizar os metodos da Sessao
 * responsaveis por localizar grupos de cadeiras disponiveis.
 *
 * Nao e necessario misturar essa funcionalidade nesta
 * primeira implementacao se ela ainda nao estiver sendo
 * desenvolvida.
 *
 * ============================================================
 */
public class CadeiraView extends JFrame {

    // Implementar seguindo as orientacoes acima.

}