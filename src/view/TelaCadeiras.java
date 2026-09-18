
package view;
import javax.swing.*;


/*
 * ORIENTACOES PARA IMPLEMENTACAO
 *
 * Criar a tela para escolha das cadeiras da Sessao.
 *
 * Cada Sessao possui uma matriz Cadeira[10][15].
 * Representar essa matriz graficamente utilizando botoes.
 *
 * Criar:
 * - Uma matriz JButton[10][15];
 * - Uma estrutura para controlar as cadeiras selecionadas;
 * - Um contador da quantidade de cadeiras selecionadas;
 * - Um botao para confirmar a selecao.
 *
 * Para cada posicao da matriz:
 * - Buscar a Cadeira correspondente na Sessao;
 * - Criar um JButton que represente essa Cadeira;
 * - Se a Cadeira estiver ocupada, impedir sua selecao;
 * - Se estiver livre, permitir sua selecao.
 *
 * O usuario somente pode selecionar a quantidade de cadeiras
 * correspondente a quantidade de ingressos solicitada.
 *
 * Ao confirmar:
 * 1. Verificar se a quantidade correta foi selecionada;
 * 2. Ocupar cada Cadeira selecionada na Sessao;
 * 3. Buscar o objeto Cadeira correspondente;
 * 4. Criar um Bilhete para cada Cadeira;
 * 5. Adicionar cada Bilhete na Compra.
 *
 * IMPORTANTE:
 * Para mais de um ingresso, as cadeiras devem ser contiguas
 * e pertencer a mesma fileira.
 *
 * Utilizar os metodos existentes em Sessao para verificar
 * e localizar grupos de cadeiras disponiveis.
 *
 * O JButton representa apenas a cadeira na interface.
 * O estado real da cadeira continua sendo controlado
 * pelo objeto Cadeira existente na Sessao.
 */


public class TelaCadeiras extends JFrame{
    
}