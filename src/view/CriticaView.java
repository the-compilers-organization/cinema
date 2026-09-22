package view;

import model.Filme;

import javax.swing.*;
import java.awt.*;

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR A CLASSE CriticaView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Criar a tela responsavel pela avaliacao dos filmes.
 *
 * A classe CriticaView deve herdar de JFrame, pois representa
 * uma janela da interface grafica.
 *
 * Nesta tela, o usuario deve conseguir:
 *
 * - escolher um filme;
 * - atribuir uma nota de 0 a 10;
 * - escrever uma critica;
 * - enviar a avaliacao;
 * - voltar para o menu principal.
 *
 *
 * 1. ATRIBUTOS PRINCIPAIS
 *
 * A classe deve possuir:
 *
 * - uma referencia para MenuView;
 * - um vetor de objetos Filme.
 *
 * Portanto, criar atributos equivalentes a:
 *
 * MenuView menu
 * Filme[] filmes
 *
 *
 * 2. POR QUE RECEBER MenuView?
 *
 * A CriticaView sera aberta a partir da MenuView.
 *
 * Quando o usuario clicar em "Voltar", queremos mostrar
 * novamente a MESMA MenuView que ja existia.
 *
 * Portanto, CriticaView deve guardar uma referencia para
 * MenuView.
 *
 * Evitar fazer:
 *
 * new MenuView(...)
 *
 * dentro do metodo voltar().
 *
 * Isso criaria uma nova tela de menu e poderia fazer com que
 * referencias aos objetos utilizados pelo programa fossem
 * perdidas.
 *
 *
 * 3. POR QUE RECEBER Filme[]?
 *
 * A tela precisa mostrar os filmes existentes no cinema.
 *
 * Esses filmes ja foram criados anteriormente e chegaram
 * ate MenuView.
 *
 * CriticaView nao deve criar novos objetos Filme apenas para
 * mostrar seus nomes.
 *
 * Ela deve receber o mesmo vetor:
 *
 * Filme[] filmes
 *
 * e utilizar os objetos que ja existem.
 *
 *
 * 4. COMPONENTES DA TELA
 *
 * Criar os seguintes componentes Swing:
 *
 * - JComboBox<String> para selecionar o filme;
 * - JSpinner para informar a nota;
 * - JTextArea para escrever a critica;
 * - JButton para enviar a avaliacao;
 * - JButton para voltar.
 *
 *
 * 5. COMBOBOX DOS FILMES
 *
 * Criar:
 *
 * JComboBox<String> cbFilmes
 *
 * Esse componente deve apresentar os nomes dos filmes
 * existentes no vetor.
 *
 * Exemplo visual:
 *
 * +-------------------------+
 * | Interestelar          v |
 * +-------------------------+
 *
 * O JComboBox apresentara apenas o nome do filme.
 *
 * O objeto Filme real continua armazenado no vetor filmes.
 *
 *
 * 6. JSpinner DA NOTA
 *
 * Criar um JSpinner para permitir que o usuario informe
 * uma nota.
 *
 * A nota deve estar no intervalo:
 *
 * 0 ate 10
 *
 * Pode ser utilizado:
 *
 * SpinnerNumberModel
 *
 * configurado com:
 *
 * valor inicial = 0.0
 * valor minimo  = 0.0
 * valor maximo  = 10.0
 * incremento    = 0.5
 *
 * Assim o usuario podera escolher valores como:
 *
 * 0.0
 * 0.5
 * 1.0
 * 1.5
 * ...
 * 9.5
 * 10.0
 *
 *
 * 7. JTextArea DA CRITICA
 *
 * Criar um JTextArea para permitir que o usuario escreva
 * sua opiniao sobre o filme.
 *
 * Pode ser utilizado um tamanho aproximado de:
 *
 * 8 linhas
 * 30 colunas
 *
 * Habilitar:
 *
 * setLineWrap(true)
 *
 * para que o texto quebre automaticamente quando chegar ao
 * final da linha.
 *
 * Utilizar tambem:
 *
 * setWrapStyleWord(true)
 *
 * para evitar que uma palavra seja quebrada no meio.
 *
 *
 * 8. JScrollPane
 *
 * Colocar o JTextArea dentro de um JScrollPane.
 *
 * Isso permite que apareca uma barra de rolagem caso o texto
 * da critica fique maior que a area disponivel.
 *
 * A ideia e:
 *
 * JScrollPane
 *      |
 *      v
 * JTextArea
 *
 *
 * 9. BOTOES
 *
 * Criar:
 *
 * JButton btnEnviar
 * JButton btnVoltar
 *
 * O primeiro deve apresentar:
 *
 * "Enviar Avaliacao"
 *
 * O segundo:
 *
 * "Voltar"
 *
 *
 * 10. CONSTRUTOR
 *
 * O construtor deve receber:
 *
 * MenuView menu
 * Filme[] filmes
 *
 * Guardar os dois parametros nos atributos da classe
 * utilizando this.
 *
 * Ideia:
 *
 * this.menu = menu;
 * this.filmes = filmes;
 *
 *
 * 11. CONFIGURAR A JANELA
 *
 * Configurar o JFrame com:
 *
 * titulo:
 * "Avaliar Filme"
 *
 * tamanho aproximado:
 * 550 x 450
 *
 * utilizar:
 *
 * JFrame.DISPOSE_ON_CLOSE
 *
 * e centralizar a janela.
 *
 * Como CriticaView e uma janela secundaria, nao utilizar
 * EXIT_ON_CLOSE, pois isso encerraria todo o programa.
 *
 *
 * 12. CARREGAR OS FILMES
 *
 * Depois de criar cbFilmes, chamar um metodo:
 *
 * carregarFilmes()
 *
 * Esse metodo sera responsavel por preencher o JComboBox com
 * os filmes existentes.
 *
 *
 * 13. METODO carregarFilmes()
 *
 * Criar:
 *
 * private void carregarFilmes()
 *
 * Esse metodo deve percorrer:
 *
 * Filme[] filmes
 *
 * utilizando um for.
 *
 * Para cada posicao:
 *
 * verificar se filmes[i] != null
 *
 * Se existir um filme naquela posicao, adicionar seu nome ao
 * JComboBox.
 *
 * Para obter o nome:
 *
 * filmes[i].getNome()
 *
 *
 * 14. POR QUE VERIFICAR null?
 *
 * O vetor pode possuir, por exemplo:
 *
 * Filme[] filmes = new Filme[10];
 *
 * mas apenas tres filmes cadastrados:
 *
 * filmes[0] = Interestelar
 * filmes[1] = Batman
 * filmes[2] = Avatar
 * filmes[3] = null
 * ...
 * filmes[9] = null
 *
 * Portanto, somente as posicoes diferentes de null devem
 * aparecer no JComboBox.
 *
 *
 * 15. PAINEL SUPERIOR
 *
 * Criar um JPanel para organizar:
 *
 * Filme:
 * JComboBox
 *
 * Nota:
 * JSpinner
 *
 * Pode ser utilizado GridLayout com:
 *
 * 2 linhas
 * 2 colunas
 *
 * A organizacao sera:
 *
 * +------------------------------------+
 * | Filme: | [ Interestelar        v ] |
 * +------------------------------------+
 * | Nota:  | [       8.5            ] |
 * +------------------------------------+
 *
 *
 * 16. PAINEL CENTRAL
 *
 * Na regiao central da janela deve ficar o JTextArea
 * utilizado para escrever a critica.
 *
 * Colocar o JTextArea dentro de JScrollPane.
 *
 *
 * 17. PAINEL INFERIOR
 *
 * Criar outro JPanel contendo:
 *
 * [ Voltar ]     [ Enviar Avaliacao ]
 *
 * Esse painel ficara na parte inferior da janela.
 *
 *
 * 18. ORGANIZACAO COM BorderLayout
 *
 * A tela pode ser organizada desta maneira:
 *
 *                   NORTH
 *      +--------------------------------+
 *      | Filme: [Interestelar       v] |
 *      | Nota:  [8.5                  ] |
 *      +--------------------------------+
 *
 *                   CENTER
 *      +--------------------------------+
 *      |                                |
 *      | Escreva aqui sua critica...    |
 *      |                                |
 *      |                                |
 *      +--------------------------------+
 *
 *                   SOUTH
 *      +--------------------------------+
 *      | [Voltar] [Enviar Avaliacao]    |
 *      +--------------------------------+
 *
 *
 * 19. EVENTO DO BOTAO ENVIAR
 *
 * O botao:
 *
 * btnEnviar
 *
 * deve possuir um ActionListener.
 *
 * Quando for clicado, deve chamar:
 *
 * enviar()
 *
 *
 * 20. EVENTO DO BOTAO VOLTAR
 *
 * O botao:
 *
 * btnVoltar
 *
 * deve possuir um ActionListener.
 *
 * Quando for clicado, deve chamar:
 *
 * voltar()
 *
 *
 * 21. METODO enviar()
 *
 * Criar:
 *
 * private void enviar()
 *
 * Esse metodo deve inicialmente descobrir qual filme foi
 * selecionado.
 *
 * Utilizar:
 *
 * cbFilmes.getSelectedIndex()
 *
 * Esse metodo retorna o indice do item selecionado no
 * JComboBox.
 *
 *
 * 22. VERIFICAR SE EXISTE FILME SELECIONADO
 *
 * Se:
 *
 * getSelectedIndex()
 *
 * retornar:
 *
 * -1
 *
 * significa que nenhum item esta selecionado.
 *
 * Nesse caso:
 *
 * - apresentar uma mensagem;
 * - informar "Selecione um filme.";
 * - utilizar return para interromper o metodo.
 *
 *
 * 23. OBTER A NOTA
 *
 * Obter o valor existente no JSpinner.
 *
 * Como getValue() retorna Object, converter o valor para
 * numero.
 *
 * Uma forma segura e:
 *
 * ((Number) spNota.getValue()).doubleValue()
 *
 * Assim teremos:
 *
 * double nota
 *
 *
 * 24. OBTER O TEXTO DA CRITICA
 *
 * Utilizar:
 *
 * txtCritica.getText()
 *
 * para obter o texto escrito pelo usuario.
 *
 * Guardar em uma String.
 *
 *
 * 25. PRIMEIRA VERSAO DO METODO enviar()
 *
 * Enquanto a integracao com o objeto Critico ainda nao
 * estiver pronta, o metodo pode apenas apresentar uma
 * mensagem mostrando:
 *
 * - filme escolhido;
 * - nota;
 * - critica.
 *
 * Essa e apenas uma etapa intermediaria para testar se os
 * componentes da interface estao funcionando.
 *
 *
 * 26. ATENCAO: JComboBox E Filme[]
 *
 * Existe um detalhe importante.
 *
 * O JComboBox apresenta somente os filmes diferentes de null.
 *
 * Portanto, tomar cuidado para nao assumir em qualquer
 * situacao que:
 *
 * indice do JComboBox
 *
 * e sempre igual a:
 *
 * indice do vetor Filme[]
 *
 * Exemplo:
 *
 * filmes[0] = Interestelar
 * filmes[1] = null
 * filmes[2] = Batman
 *
 * O JComboBox apresentaria:
 *
 * indice 0 -> Interestelar
 * indice 1 -> Batman
 *
 * mas Batman esta em:
 *
 * filmes[2]
 *
 * e nao:
 *
 * filmes[1]
 *
 * Portanto, na versao definitiva e importante localizar o
 * objeto Filme correspondente corretamente.
 *
 *
 * 27. UMA FORMA DE LOCALIZAR O FILME
 *
 * Pode ser criado posteriormente um metodo:
 *
 * private Filme buscarFilmeSelecionado()
 *
 * Esse metodo pode:
 *
 * - obter o indice selecionado no JComboBox;
 * - percorrer apenas os filmes diferentes de null;
 * - utilizar um contador;
 * - retornar o Filme correspondente.
 *
 * Essa ideia e semelhante ao que CompraView faz para descobrir
 * qual Sessao corresponde ao item selecionado.
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
 * - tornar MenuView visivel novamente;
 * - fechar CriticaView.
 *
 * Utilizar a ideia:
 *
 * menu.setVisible(true);
 * dispose();
 *
 *
 * 29. NAO CRIAR UM NOVO MENU
 *
 * No metodo voltar(), evitar:
 *
 * new MenuView(...)
 *
 * A CriticaView ja recebeu a MenuView que a abriu.
 *
 * Portanto, deve voltar para aquela mesma instancia.
 *
 *
 * 30. RESPONSABILIDADE DA CriticaView
 *
 * A View deve ser responsavel principalmente por:
 *
 * - apresentar os filmes;
 * - receber a escolha do usuario;
 * - receber a nota;
 * - receber o texto da critica;
 * - responder aos cliques dos botoes;
 * - mostrar mensagens;
 * - controlar temporariamente a navegacao.
 *
 * As regras relacionadas a critica e nota nao devem ficar
 * definitivamente dentro da View.
 *
 *
 * 31. RELACAO COM A CLASSE Critico
 *
 * No Model do projeto existe a classe:
 *
 * Critico
 *
 * Ela possui as operacoes responsaveis por atribuir uma nota
 * e uma critica a um Filme.
 *
 * Portanto, na versao completa, CriticaView devera trabalhar
 * com um objeto Critico.
 *
 * A ideia sera:
 *
 * CriticaView
 *      |
 *      | nota
 *      | filme
 *      v
 * Critico.atribuirNota(...)
 *
 *
 * e:
 *
 * CriticaView
 *      |
 *      | mensagem
 *      | filme
 *      v
 * Critico.atribuirCritica(...)
 *
 *
 * 32. NAO DUPLICAR A REGRA DO MODEL
 *
 * Evitar colocar dentro da View regras como:
 *
 * - adicionar diretamente uma nota ao vetor de notas;
 * - criar diretamente uma Critica e inserir no vetor;
 * - alterar diretamente a quantidade de criticas;
 * - calcular diretamente a media das notas.
 *
 * Essas responsabilidades pertencem ao Model.
 *
 * A View deve obter os dados digitados e solicitar a operacao
 * para os objetos apropriados.
 *
 *
 * 33. FLUXO DA NOTA NA VERSAO COMPLETA
 *
 * Usuario escolhe filme
 *          |
 *          v
 * CriticaView identifica o Filme
 *          |
 *          v
 * Usuario informa nota
 *          |
 *          v
 * Critico.atribuirNota(nota, filme)
 *          |
 *          v
 * Filme recebe a nota
 *          |
 *          v
 * media das notas pode ser atualizada
 *
 *
 * 34. FLUXO DA CRITICA NA VERSAO COMPLETA
 *
 * Usuario escreve:
 *
 * "Gostei muito do filme..."
 *
 *          |
 *          v
 * CriticaView
 *          |
 *          v
 * Critico.atribuirCritica(
 *        mensagem,
 *        filme
 * )
 *          |
 *          v
 * objeto Critica
 *          |
 *          v
 * Filme
 *          |
 *          v
 * Critica[]
 *
 *
 * 35. IMPORTANTE SOBRE QUEM PODE AVALIAR
 *
 * De acordo com a estrutura atual do Model, os metodos:
 *
 * atribuirNota(...)
 *
 * e:
 *
 * atribuirCritica(...)
 *
 * pertencem a classe Critico.
 *
 * Portanto, na integracao definitiva da interface, a tela
 * deve receber ou identificar o Critico que esta realizando
 * a avaliacao.
 *
 * Nao criar um novo Critico ficticio dentro da CriticaView
 * apenas para conseguir chamar esses metodos.
 *
 * O ideal e utilizar o mesmo objeto de usuario cadastrado
 * anteriormente no sistema.
 *
 *
 * 36. POSSIVEL CONSTRUTOR FUTURO
 *
 * Quando o usuario atual estiver sendo compartilhado entre
 * as telas, o construtor podera precisar receber tambem um
 * Critico.
 *
 * A ideia seria algo equivalente a:
 *
 * CriticaView(
 *        MenuView menu,
 *        Filme[] filmes,
 *        Critico critico)
 *
 * Isso somente deve ser feito quando essa integracao estiver
 * sendo implementada.
 *
 *
 * 37. VALIDACAO DO TEXTO
 *
 * Antes de enviar uma critica, posteriormente pode ser
 * interessante verificar se:
 *
 * txtCritica.getText()
 *
 * nao esta vazio.
 *
 * Por exemplo:
 *
 * critica.trim().isEmpty()
 *
 * pode ser utilizado para identificar um texto vazio ou
 * composto apenas por espacos.
 *
 * Essa validacao pode ser adicionada quando forem implementar
 * efetivamente o envio da critica.
 *
 *
 * 38. FLUXO GERAL DA TELA
 *
 * MenuView
 *     |
 *     | passa Filme[]
 *     v
 * CriticaView
 *     |
 *     +----> JComboBox de filmes
 *     |
 *     +----> JSpinner da nota
 *     |
 *     +----> JTextArea da critica
 *     |
 *     +----> Enviar
 *     |         |
 *     |         v
 *     |     identificar Filme
 *     |         |
 *     |         v
 *     |     obter nota
 *     |         |
 *     |         v
 *     |     obter critica
 *     |
 *     +----> Voltar
 *               |
 *               v
 *            MenuView
 *
 *
 * 39. VERSAO ATUAL X VERSAO FINAL
 *
 * VERSAO ATUAL:
 *
 * CriticaView
 *      |
 *      +-- recebe Filme[]
 *      +-- recebe nota
 *      +-- recebe critica
 *      +-- mostra mensagem
 *
 *
 * VERSAO FINAL:
 *
 * CriticaView
 *      |
 *      +-- identifica Filme
 *      +-- recebe nota
 *      +-- recebe critica
 *      |
 *      v
 * Critico
 *      |
 *      +-- atribuirNota(...)
 *      |
 *      +-- atribuirCritica(...)
 *      |
 *      v
 * Filme
 *
 *
 * 40. RESUMO DAS RESPONSABILIDADES
 *
 * CriticaView:
 *
 * - montar a interface grafica;
 * - mostrar os filmes;
 * - receber nota;
 * - receber texto;
 * - tratar os eventos dos botoes;
 * - mostrar mensagens;
 * - voltar para MenuView.
 *
 * Critico:
 *
 * - atribuir nota;
 * - atribuir critica;
 * - fornecer os dados necessarios para criar a Critica.
 *
 * Filme:
 *
 * - armazenar suas notas;
 * - armazenar suas criticas;
 * - fornecer os dados do filme.
 *
 * Critica:
 *
 * - representar uma critica feita para determinado filme.
 *
 * ============================================================
 */
public class CriticaView extends JFrame {

    // Implementar seguindo as orientacoes acima.

}