package controller;

public class CadeiraController {
    // Implementar a classe seguindo as orientacoes.
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CadeiraController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Controlar a escolha de cadeira para uma determinada Sessao.
 *
 *
 * ATRIBUTOS:
 *
 * - CadeiraView view;
 * - Sessao sessao;
 * - CompraController compraController;
 * - int linhaSelecionada;
 * - int colunaSelecionada.
 *
 *
 * Inicialmente:
 *
 * linhaSelecionada = -1
 * colunaSelecionada = -1
 *
 *
 * CONSTRUTOR:
 *
 * Receber:
 *
 * CadeiraView
 * Sessao
 * CompraController
 *
 * Depois:
 *
 * - carregar estado das cadeiras;
 * - configurar eventos.
 *
 *
 * configurarEventos():
 *
 * Percorrer a matriz:
 *
 * JButton[][]
 *
 * existente na View.
 *
 * Cada botao deve chamar:
 *
 * selecionarCadeira(linha, coluna)
 *
 *
 * ATENCAO AO LAMBDA:
 *
 * Dentro do for, criar variaveis locais para linha e coluna
 * antes de utiliza-las no ActionListener.
 *
 * Exemplo conceitual:
 *
 * int l = linha;
 * int c = coluna;
 *
 * Isso permite utilizar l e c dentro da expressao lambda.
 *
 *
 * carregarCadeiras():
 *
 * Obter:
 *
 * Cadeira[][]
 *
 * atraves da Sessao.
 *
 * Para cada cadeira:
 *
 * ocupada:
 * - mostrar "X";
 * - desabilitar botao.
 *
 * livre:
 * - mostrar numero;
 * - habilitar botao.
 *
 *
 * selecionarCadeira():
 *
 * Verificar atraves da Sessao se a cadeira esta disponivel.
 *
 * Se estiver:
 *
 * guardar linha e coluna selecionadas.
 *
 *
 * confirmar():
 *
 * Verificar se alguma cadeira foi selecionada.
 *
 * Depois utilizar o metodo do Model:
 *
 * sessao.ocuparCadeira(...)
 *
 * Nao fazer:
 *
 * cadeira.setOcupada(true)
 *
 * diretamente.
 *
 *
 * Se a ocupacao for realizada:
 *
 * informar ao CompraController:
 *
 * setCadeiraSelecionada(...)
 *
 * mostrar novamente CompraView;
 * fechar CadeiraView.
 *
 *
 * voltar():
 *
 * Nao ocupar cadeira.
 *
 * Mostrar novamente a CompraView existente e fechar
 * CadeiraView.
 *
 * ============================================================
 */