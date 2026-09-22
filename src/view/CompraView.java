package view;

import enums.CupomPromocional;
import enums.Produtos;

import model.Sala;
import model.Sessao;

import javax.swing.*;
import java.awt.*;

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR A CLASSE CompraView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Criar a tela responsavel pela realizacao de uma compra
 * no sistema de cinema.
 *
 * A classe CompraView deve herdar de JFrame, pois representa
 * uma janela da interface grafica.
 *
 * Essa tela deve permitir que o usuario:
 *
 * - escolha uma sessao;
 * - informe a quantidade de bilhetes;
 * - escolha a cadeira;
 * - escolha produtos;
 * - informe a quantidade do produto;
 * - adicione produtos;
 * - escolha um cupom promocional;
 * - visualize os valores da compra;
 * - finalize a compra;
 * - volte para o menu.
 *
 *
 * 1. ATRIBUTOS PRINCIPAIS
 *
 * A classe deve possuir uma referencia para:
 *
 * - MenuView;
 * - vetor de Sala.
 *
 * A referencia para MenuView sera utilizada para voltar para
 * a mesma tela principal que abriu CompraView.
 *
 * O vetor de Sala sera utilizado para encontrar as sessoes
 * disponiveis no cinema.
 *
 * Portanto, criar atributos equivalentes a:
 *
 * MenuView menu
 * Sala[] salas
 *
 *
 * 2. COMPONENTES PARA ESCOLHER A SESSAO
 *
 * Criar um JComboBox para apresentar as sessoes disponiveis.
 *
 * O JComboBox pode apresentar informacoes como:
 *
 * Sala 1 | 18:00 | Interestelar
 * Sala 1 | 20:00 | Batman
 * Sala 2 | 21:30 | Avatar
 *
 * O componente pode ser:
 *
 * JComboBox<String>
 *
 *
 * 3. QUANTIDADE DE BILHETES
 *
 * Criar um JSpinner para o usuario informar a quantidade
 * de bilhetes que deseja comprar.
 *
 * O valor inicial pode ser:
 *
 * 1
 *
 * O menor valor permitido:
 *
 * 1
 *
 * O maior valor pode ser:
 *
 * 15
 *
 * pois uma fileira possui 15 cadeiras.
 *
 * Utilizar SpinnerNumberModel para controlar esses valores.
 *
 *
 * 4. BOTAO ESCOLHER CADEIRAS
 *
 * Criar um JButton:
 *
 * "Escolher Cadeira"
 *
 * Esse botao deve abrir CadeiraView.
 *
 * A CadeiraView precisa receber:
 *
 * - a CompraView atual;
 * - a Sessao escolhida.
 *
 * Assim, CadeiraView consegue mostrar as cadeiras daquela
 * sessao e posteriormente devolver a cadeira escolhida para
 * esta CompraView.
 *
 *
 * 5. COMPONENTES PARA PRODUTOS
 *
 * Criar um JComboBox utilizando o enum Produtos.
 *
 * Nao e necessario escrever manualmente:
 *
 * PIPOCA
 * REFRIGERANTE
 * CHOCOLATE
 * AGUA
 * BALA
 *
 * Utilizar:
 *
 * Produtos.values()
 *
 * Dessa forma, o JComboBox sera preenchido com os valores
 * existentes no enum.
 *
 *
 * 6. QUANTIDADE DO PRODUTO
 *
 * Criar outro JSpinner para informar a quantidade do produto.
 *
 * Por exemplo:
 *
 * valor inicial = 1
 * minimo = 1
 * maximo = 20
 * incremento = 1
 *
 *
 * 7. BOTAO ADICIONAR PRODUTO
 *
 * Criar um JButton:
 *
 * "Adicionar Produto"
 *
 * Quando clicado, ele deve chamar um metodo privado
 * responsavel por adicionar o valor daquele produto ao
 * total de produtos.
 *
 *
 * 8. CUPOM PROMOCIONAL
 *
 * Criar um JComboBox utilizando:
 *
 * CupomPromocional
 *
 * Para carregar os valores do enum, utilizar:
 *
 * CupomPromocional.values()
 *
 * Assim, se os valores do enum forem:
 *
 * CRITICO
 * ESTUDANTE
 * MAIS_60
 * PROFESSOR
 * MEMBROS_VIP
 *
 * essas opcoes aparecerao automaticamente no JComboBox.
 *
 * IMPORTANTE:
 *
 * Nesta etapa inicial da View, o JComboBox apenas permite
 * selecionar o cupom.
 *
 * A regra para calcular corretamente o desconto deve respeitar
 * a regra existente no Model.
 *
 *
 * 9. LABEL DA CADEIRA
 *
 * Criar um JLabel para mostrar qual cadeira foi selecionada.
 *
 * Inicialmente:
 *
 * "Cadeira: nenhuma"
 *
 * Depois que CadeiraView retornar uma cadeira, atualizar para
 * algo como:
 *
 * "Linha 3 | Cadeira 7"
 *
 *
 * 10. LABEL DOS PRODUTOS
 *
 * Criar um JLabel para apresentar o valor acumulado dos
 * produtos.
 *
 * Inicialmente:
 *
 * "Produtos: R$ 0,00"
 *
 *
 * 11. LABEL DO TOTAL
 *
 * Criar outro JLabel para apresentar o valor total.
 *
 * Inicialmente:
 *
 * "TOTAL: R$ 0,00"
 *
 * Esse valor deve ser atualizado conforme forem selecionados
 * bilhetes e adicionados produtos.
 *
 *
 * 12. BOTOES INFERIORES
 *
 * Criar:
 *
 * JButton btnFinalizar
 * JButton btnVoltar
 *
 * O botao Finalizar deve concluir a operacao.
 *
 * O botao Voltar deve retornar para a mesma MenuView.
 *
 *
 * 13. ATRIBUTOS PARA GUARDAR A SESSAO ESCOLHIDA
 *
 * Alem dos componentes visuais, criar:
 *
 * Sala salaSelecionada
 * Sessao sessaoSelecionada
 *
 * Esses atributos representam os objetos reais correspondentes
 * ao item escolhido no JComboBox.
 *
 * Isso e importante porque o JComboBox apresenta uma String,
 * mas posteriormente precisamos trabalhar com objetos Sala
 * e Sessao.
 *
 *
 * 14. ATRIBUTOS PARA GUARDAR A CADEIRA
 *
 * Criar:
 *
 * int linhaSelecionada
 * int colunaSelecionada
 *
 * Inicializar os dois com:
 *
 * -1
 *
 * O valor -1 significa:
 *
 * "nenhuma cadeira foi escolhida"
 *
 *
 * 15. TOTAL DOS PRODUTOS
 *
 * Criar:
 *
 * double totalProdutos
 *
 * Inicializar com:
 *
 * 0
 *
 * Esse atributo sera utilizado para acumular o valor dos
 * produtos adicionados.
 *
 *
 * 16. CONSTRUTOR
 *
 * O construtor deve receber:
 *
 * MenuView menu
 * Sala[] salas
 *
 * Guardar utilizando this:
 *
 * this.menu = menu;
 * this.salas = salas;
 *
 * Inicializar tambem:
 *
 * linhaSelecionada = -1;
 * colunaSelecionada = -1;
 * totalProdutos = 0;
 *
 *
 * 17. CONFIGURAR O JFrame
 *
 * Configurar:
 *
 * titulo:
 * "Realizar Compra"
 *
 * tamanho aproximado:
 * 650 x 650
 *
 * utilizar:
 *
 * JFrame.DISPOSE_ON_CLOSE
 *
 * e centralizar a janela.
 *
 *
 * 18. CRIAR O PAINEL PRINCIPAL
 *
 * Criar um JPanel para organizar os componentes.
 *
 * Pode ser utilizado GridLayout com duas colunas.
 *
 * A ideia visual sera aproximadamente:
 *
 * +------------------------------------------------+
 * | Sessao:              [ Sala 1 | 18:00 | ... ] |
 * |                                                |
 * | Quantidade bilhetes: [          1           ] |
 * |                                                |
 * | Cadeira: nenhuma     [ Escolher Cadeira     ] |
 * |                                                |
 * | Produto:             [ PIPOCA               ] |
 * |                                                |
 * | Quantidade:          [          1           ] |
 * |                                                |
 * |                      [ Adicionar Produto     ] |
 * |                                                |
 * | Cupom:               [ ESTUDANTE            ] |
 * |                                                |
 * | Produtos: R$ 0,00                             |
 * | TOTAL: R$ 0,00                                |
 * |                                                |
 * | [ Voltar ]                  [ Finalizar ]      |
 * +------------------------------------------------+
 *
 *
 * 19. METODO carregarSessoes()
 *
 * Criar:
 *
 * private void carregarSessoes()
 *
 * Esse metodo deve percorrer:
 *
 * Sala[]
 *
 * e, para cada Sala existente, percorrer:
 *
 * Sessao[]
 *
 * obtido atraves de:
 *
 * sala.getSessoes()
 *
 * Para cada sessao diferente de null, adicionar uma opcao
 * no JComboBox.
 *
 * Exemplo:
 *
 * "Sala "
 * + numero da sala
 * + " | "
 * + horario
 * + " | "
 * + nome do filme
 *
 *
 * 20. POR QUE PRECISAMOS DE DOIS FOR?
 *
 * Porque a estrutura dos objetos e:
 *
 * Sala[]
 *   |
 *   +-- Sala 1
 *   |      |
 *   |      +-- Sessao[]
 *   |
 *   +-- Sala 2
 *          |
 *          +-- Sessao[]
 *
 * Portanto:
 *
 * primeiro for  -> percorre as salas
 * segundo for   -> percorre as sessoes daquela sala
 *
 *
 * 21. METODO buscarSessaoSelecionada()
 *
 * Criar:
 *
 * private void buscarSessaoSelecionada()
 *
 * O JComboBox guarda a posicao visual da opcao escolhida.
 *
 * Obter essa posicao utilizando:
 *
 * cbSessao.getSelectedIndex()
 *
 * Depois percorrer novamente as salas e sessoes na mesma
 * ordem utilizada em carregarSessoes().
 *
 * Utilizar um contador para descobrir qual Sala e qual Sessao
 * correspondem ao indice selecionado.
 *
 * Quando encontrar:
 *
 * salaSelecionada = salas[i];
 * sessaoSelecionada = sessoes[j];
 *
 * Depois interromper o metodo.
 *
 *
 * 22. POR QUE NAO USAR APENAS O TEXTO DO JComboBox?
 *
 * Porque:
 *
 * "Sala 1 | 18:00 | Interestelar"
 *
 * e apenas uma String para exibicao.
 *
 * Para abrir as cadeiras precisamos do objeto real:
 *
 * Sessao
 *
 * E para calcular o valor precisamos tambem do objeto:
 *
 * Sala
 *
 *
 * 23. METODO abrirCadeiras()
 *
 * Criar:
 *
 * private void abrirCadeiras()
 *
 * Primeiro chamar:
 *
 * buscarSessaoSelecionada()
 *
 * Depois verificar se sessaoSelecionada existe.
 *
 * Se for null:
 *
 * mostrar uma mensagem solicitando que o usuario escolha
 * uma sessao.
 *
 * Caso exista, criar:
 *
 * CadeiraView
 *
 * passando:
 *
 * this
 * sessaoSelecionada
 *
 * O this representa a CompraView atual.
 *
 * Depois:
 *
 * - mostrar CadeiraView;
 * - esconder temporariamente CompraView.
 *
 *
 * 24. METODO setCadeiraSelecionada()
 *
 * Criar um metodo PUBLICO:
 *
 * public void setCadeiraSelecionada(
 *         int linha,
 *         int coluna)
 *
 * Esse metodo precisa ser publico porque sera chamado pela
 * CadeiraView.
 *
 * Ele deve:
 *
 * - guardar a linha;
 * - guardar a coluna;
 * - atualizar lblCadeira;
 * - atualizar o total.
 *
 *
 * A relacao sera:
 *
 * CadeiraView
 *      |
 *      | confirmou cadeira
 *      v
 * compraView.setCadeiraSelecionada(...)
 *      |
 *      v
 * CompraView
 *
 *
 * 25. METODO adicionarProduto()
 *
 * Criar:
 *
 * private void adicionarProduto()
 *
 * Obter o produto selecionado no JComboBox.
 *
 * Obter a quantidade informada no JSpinner.
 *
 * Calcular:
 *
 * valorProduto =
 * produto.getValor() * quantidade
 *
 * Somar esse valor em:
 *
 * totalProdutos
 *
 * Depois atualizar:
 *
 * lblTotalProdutos
 *
 * e chamar:
 *
 * atualizarTotal()
 *
 *
 * 26. EXEMPLO DO CALCULO DOS PRODUTOS
 *
 * Suponha:
 *
 * PIPOCA = R$ 12,00
 * quantidade = 2
 *
 * Entao:
 *
 * 12.00 * 2 = 24.00
 *
 * Se totalProdutos era:
 *
 * 0
 *
 * passa a ser:
 *
 * 24.00
 *
 * Se depois adicionar:
 *
 * REFRIGERANTE = R$ 7,00
 * quantidade = 1
 *
 * totalProdutos:
 *
 * 24.00 + 7.00 = 31.00
 *
 *
 * 27. METODO calcularValorBilhete()
 *
 * Criar:
 *
 * private double calcularValorBilhete()
 *
 * Primeiro garantir que temos:
 *
 * salaSelecionada
 * sessaoSelecionada
 *
 * O valor base do ingresso vem do Filme:
 *
 * sessaoSelecionada
 *     .getFilme()
 *     .getValor()
 *
 * Depois considerar o multiplicador do TipoSala:
 *
 * valor =
 * valorFilme * multiplicadorSala
 *
 * O multiplicador pode ser obtido atraves de:
 *
 * salaSelecionada
 *     .getTipoSala()
 *     .getMultiplicador()
 *
 *
 * 28. QUANTIDADE DE BILHETES
 *
 * Obter a quantidade utilizando o JSpinner.
 *
 * Depois:
 *
 * totalBilhetes =
 * valorDeUmBilhete * quantidade
 *
 * Retornar esse valor.
 *
 *
 * 29. EXEMPLO DO CALCULO
 *
 * Filme:
 *
 * R$ 30,00
 *
 * TipoSala:
 *
 * SALA3D
 *
 * multiplicador:
 *
 * 1.2
 *
 * Portanto:
 *
 * 30.00 * 1.2 = 36.00
 *
 * Se forem 2 bilhetes:
 *
 * 36.00 * 2 = 72.00
 *
 *
 * 30. METODO atualizarTotal()
 *
 * Criar:
 *
 * private void atualizarTotal()
 *
 * Obter:
 *
 * totalBilhetes
 *
 * atraves de:
 *
 * calcularValorBilhete()
 *
 * Depois calcular:
 *
 * total =
 * totalBilhetes + totalProdutos
 *
 * Atualizar lblTotal utilizando String.format().
 *
 *
 * 31. EVENTOS DOS BOTOES
 *
 * Cada botao deve possuir um ActionListener.
 *
 * Escolher Cadeira:
 *
 * chamar abrirCadeiras()
 *
 * Adicionar Produto:
 *
 * chamar adicionarProduto()
 *
 * Finalizar:
 *
 * chamar finalizar()
 *
 * Voltar:
 *
 * chamar voltar()
 *
 *
 * 32. METODO finalizar()
 *
 * Criar:
 *
 * private void finalizar()
 *
 * Antes de finalizar, verificar se alguma cadeira foi
 * selecionada.
 *
 * Como -1 significa "nenhuma cadeira", verificar:
 *
 * linhaSelecionada == -1
 * ou
 * colunaSelecionada == -1
 *
 * Se nenhuma cadeira foi escolhida:
 *
 * mostrar:
 *
 * "Escolha uma cadeira."
 *
 * e interromper utilizando return.
 *
 *
 * 33. FINALIZACAO DA COMPRA
 *
 * Nesta primeira versao da View, depois da validacao:
 *
 * - calcular o total;
 * - mostrar uma mensagem com o valor;
 * - tornar MenuView visivel;
 * - fechar CompraView.
 *
 * IMPORTANTE:
 *
 * Posteriormente, quando a integracao completa com o Model
 * Compra estiver pronta, este metodo deve criar/atualizar
 * o objeto Compra e utilizar os metodos existentes no Model.
 *
 * A View nao deve substituir definitivamente as regras da
 * classe Compra.
 *
 *
 * 34. METODO voltar()
 *
 * Criar:
 *
 * private void voltar()
 *
 * Existe uma situacao importante:
 *
 * O usuario pode:
 *
 * 1. escolher uma cadeira;
 * 2. confirmar a cadeira;
 * 3. voltar para CompraView;
 * 4. desistir da compra;
 * 5. clicar em Voltar.
 *
 * Nesse caso, a cadeira nao pode continuar ocupada.
 *
 * Portanto, antes de sair, verificar se existe:
 *
 * - sessaoSelecionada;
 * - linhaSelecionada;
 * - colunaSelecionada.
 *
 * Se existir uma cadeira selecionada, solicitar para a Sessao
 * liberar essa cadeira.
 *
 * Utilizar o metodo:
 *
 * sessaoSelecionada.liberarCadeira(...)
 *
 * Depois:
 *
 * - tornar MenuView visivel;
 * - fechar CompraView.
 *
 *
 * 35. NAO ALTERAR A CADEIRA DIRETAMENTE
 *
 * Evitar fazer:
 *
 * cadeira.setOcupada(false);
 *
 * A CompraView deve solicitar para Sessao:
 *
 * sessaoSelecionada.liberarCadeira(...)
 *
 * Assim a regra continua dentro do Model.
 *
 *
 * 36. RESPONSABILIDADE DA CompraView
 *
 * Como o projeto ainda nao possui Controller, CompraView
 * temporariamente sera responsavel por:
 *
 * - receber os eventos dos botoes;
 * - apresentar as sessoes;
 * - abrir CadeiraView;
 * - receber a cadeira selecionada;
 * - apresentar produtos;
 * - receber quantidade;
 * - atualizar os valores exibidos;
 * - controlar a navegacao para MenuView.
 *
 * As regras principais do cinema devem continuar nas classes
 * do pacote model.
 *
 *
 * 37. RELACAO ENTRE AS CLASSES
 *
 * O fluxo esperado e:
 *
 * Main
 *   |
 *   v
 * MenuView
 *   |
 *   | passa Sala[]
 *   v
 * CompraView
 *   |
 *   +------> Sala
 *   |          |
 *   |          v
 *   |       Sessao
 *   |          |
 *   |          v
 *   |       Filme
 *   |
 *   +------> CadeiraView
 *                |
 *                | linha e coluna
 *                v
 *            CompraView
 *
 *
 * 38. FLUXO PARA ESCOLHER A SESSAO
 *
 * Usuario escolhe:
 *
 * Sala 2 | 21:30 | Avatar
 *             |
 *             v
 * cbSessao.getSelectedIndex()
 *             |
 *             v
 * buscarSessaoSelecionada()
 *             |
 *             +--> salaSelecionada
 *             |
 *             +--> sessaoSelecionada
 *
 *
 * 39. FLUXO PARA ESCOLHER CADEIRA
 *
 * CompraView
 *      |
 *      | Escolher Cadeira
 *      v
 * CadeiraView
 *      |
 *      | usuario seleciona
 *      v
 * Sessao.ocuparCadeira(...)
 *      |
 *      v
 * CompraView.setCadeiraSelecionada(...)
 *      |
 *      v
 * lblCadeira atualizado
 *
 *
 * 40. FLUXO DO VALOR
 *
 * Filme.getValor()
 *        |
 *        v
 * multiplicador do TipoSala
 *        |
 *        v
 * valor de um bilhete
 *        |
 *        v
 * quantidade de bilhetes
 *        |
 *        v
 * total dos bilhetes
 *        |
 *        +------------------+
 *                           |
 * Produtos.getValor()       |
 *        |                  |
 *        v                  |
 * quantidade                |
 *        |                  |
 *        v                  |
 * totalProdutos             |
 *        |                  |
 *        +------------------+
 *                           |
 *                           v
 *                     TOTAL DA COMPRA
 *
 *
 * 41. ATENCAO COM CUPOM PROMOCIONAL
 *
 * O JComboBox do cupom pode ser criado nesta tela utilizando
 * CupomPromocional.values().
 *
 * Porem, nao colocar uma regra de desconto diferente daquela
 * definida pelo projeto/model.
 *
 * O enum possui os valores e percentuais, mas a aplicacao
 * definitiva do desconto deve seguir a regra utilizada pela
 * classe Compra/Usuario.
 *
 *
 * 42. ATENCAO COM VARIOS BILHETES
 *
 * A tela possui um JSpinner para quantidade de bilhetes.
 *
 * Porem, selecionar apenas uma cadeira nao e suficiente quando
 * quantidade de bilhetes for maior que 1.
 *
 * Exemplo:
 *
 * quantidade = 3
 *
 * deverao existir 3 cadeiras correspondentes aos 3 bilhetes.
 *
 * O Model Sessao ja possui metodos relacionados a busca de
 * cadeiras juntas.
 *
 * Portanto, em uma etapa posterior, CompraView deve enviar a
 * quantidade de bilhetes para CadeiraView para permitir a
 * escolha/sugestao da quantidade correta de cadeiras.
 *
 * Nao considerar uma unica cadeira suficiente para varios
 * bilhetes na versao definitiva.
 *
 *
 * 43. IMPORTANTE SOBRE O OBJETO Compra
 *
 * Esta primeira implementacao da interface esta trabalhando
 * principalmente com os componentes Swing e valores
 * temporarios.
 *
 * Na versao completa, a operacao deve utilizar o objeto
 * model.Compra para guardar:
 *
 * - bilhetes;
 * - produtos;
 * - quantidades;
 * - valores;
 * - usuario da compra;
 * - demais informacoes existentes no Model.
 *
 * Ou seja:
 *
 * CompraView = interface
 * Compra     = dados e regras da compra
 *
 * Evitar transferir definitivamente toda a logica da classe
 * Compra para CompraView.
 *
 *
 * 44. RESUMO DAS RESPONSABILIDADES
 *
 * CompraView:
 *
 * - mostrar componentes;
 * - receber cliques;
 * - receber escolhas;
 * - mostrar resultados;
 * - abrir CadeiraView;
 * - voltar para MenuView.
 *
 * Sala:
 *
 * - guardar suas sessoes.
 *
 * Sessao:
 *
 * - guardar filme e cadeiras;
 * - verificar disponibilidade;
 * - ocupar/liberar cadeiras;
 * - buscar cadeiras juntas.
 *
 * Filme:
 *
 * - fornecer dados do filme e seu valor.
 *
 * Produtos:
 *
 * - fornecer os produtos e seus valores.
 *
 * CupomPromocional:
 *
 * - fornecer os tipos/percentuais definidos pelo projeto.
 *
 * Compra:
 *
 * - deve concentrar as regras e os dados definitivos da compra.
 *
 * ============================================================
 */
public class CompraView extends JFrame {

    // Implementar seguindo as orientacoes acima.

}