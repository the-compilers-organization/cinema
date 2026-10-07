package controller;

public class CompraController {
    // Implementar a classe seguindo as orientacoes.
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CompraController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Coordenar todo o processo de realizacao da compra.
 *
 *
 * ATRIBUTOS:
 *
 * Deve manter referencias para:
 *
 * - CompraView;
 * - Sala[];
 * - Usuario atual;
 * - MenuController;
 * - Sala selecionada;
 * - Sessao selecionada;
 * - linha da cadeira selecionada;
 * - coluna da cadeira selecionada;
 * - total dos produtos.
 *
 *
 * ESTADO INICIAL:
 *
 * linhaSelecionada = -1
 * colunaSelecionada = -1
 * totalProdutos = 0.00
 *
 *
 * CONSTRUTOR:
 *
 * Receber:
 *
 * CompraView
 * Sala[]
 * Usuario
 * MenuController
 *
 * Depois:
 *
 * - carregar as sessoes;
 * - configurar eventos;
 * - atualizar o total inicial.
 *
 *
 * EVENTOS:
 *
 * Escolher cadeira -> abrirCadeiras()
 * Adicionar produto -> adicionarProduto()
 * Finalizar         -> finalizar()
 * Voltar            -> voltar()
 *
 *
 * carregarSessoes():
 *
 * Percorrer:
 *
 * Sala[]
 *   |
 *   v
 * Sessao[]
 *
 * Adicionar cada sessao no JComboBox da CompraView.
 *
 * Pode apresentar:
 *
 * Sala 1 | 18:00 | Interestelar
 *
 *
 * buscarSessaoSelecionada():
 *
 * O indice do JComboBox nao deve ser usado diretamente como
 * indice de Sala[] ou Sessao[], pois existem vetores com
 * posicoes null.
 *
 * Percorrer novamente as sessoes validas utilizando um
 * contador.
 *
 * Quando o contador corresponder ao indice selecionado,
 * guardar:
 *
 * salaSelecionada
 * sessaoSelecionada
 *
 *
 * abrirCadeiras():
 *
 * Primeiro localizar a Sessao selecionada.
 *
 * Depois:
 *
 * criar CadeiraView;
 * criar CadeiraController;
 * passar a Sessao selecionada;
 * passar o proprio CompraController;
 * esconder CompraView;
 * mostrar CadeiraView.
 *
 *
 * COMUNICACAO ENTRE CONTROLLERS:
 *
 * CadeiraController
 *        |
 *        | cadeira escolhida
 *        v
 * CompraController
 *        |
 *        v
 * CompraView
 *
 *
 * Criar um metodo publico como:
 *
 * setCadeiraSelecionada(int linha, int coluna)
 *
 * Ele deve guardar a cadeira selecionada e atualizar a
 * informacao apresentada na CompraView.
 *
 *
 * adicionarProduto():
 *
 * Obter:
 *
 * Produtos produto
 * quantidade
 *
 * Calcular:
 *
 * produto.getValor() * quantidade
 *
 * Atualizar o total de produtos.
 *
 *
 * calcularValorBilhete():
 *
 * Localizar a Sessao e Sala selecionadas.
 *
 * Nesta versao inicial, calcular:
 *
 * valor do filme
 *       *
 * multiplicador da sala
 *       *
 * quantidade de bilhetes
 *
 *
 * IMPORTANTE:
 *
 * Conforme a integracao com model.Compra e model.Bilhete
 * avancar, evitar duplicar no Controller regras que ja
 * estiverem implementadas nessas classes.
 *
 *
 * atualizarTotal():
 *
 * Atualizar o JLabel correspondente ao total.
 *
 *
 * finalizar():
 *
 * Verificar se existe cadeira selecionada.
 *
 * Posteriormente deve integrar efetivamente:
 *
 * Usuario
 * Compra
 * Bilhete
 * Produtos
 * CupomPromocional
 *
 * utilizando os metodos existentes no Model.
 *
 *
 * voltar():
 *
 * Caso a cadeira ja tenha sido temporariamente ocupada e a
 * compra seja abandonada, liberar a cadeira.
 *
 * Depois retornar ao Menu.
 *
 *
 * mostrarView():
 *
 * Criar metodo publico para CadeiraController conseguir
 * mostrar novamente a mesma CompraView.
 *
 * ============================================================
 */