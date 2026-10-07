package view;

import javax.swing.*;
import java.awt.*;

public class CompraView extends JFrame {

    // Implementar seguindo as orientacoes.

}

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CompraView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir a interface visual utilizada durante a compra.
 *
 *
 * COMPONENTES:
 *
 * Criar componentes para:
 *
 * - escolher Sessao;
 * - informar quantidade de bilhetes;
 * - escolher cadeiras;
 * - escolher Produto;
 * - quantidade do Produto;
 * - adicionar Produto;
 * - escolher CupomPromocional;
 * - apresentar cadeira selecionada;
 * - apresentar total dos produtos;
 * - apresentar total da compra;
 * - finalizar;
 * - voltar.
 *
 *
 * A View deve construir os componentes e disponibiliza-los
 * atraves de getters.
 *
 *
 * Exemplos:
 *
 * getCbSessao()
 * getSpQtdBilhetes()
 * getBtnEscolherCadeiras()
 * getCbProduto()
 * getSpQtdProduto()
 * getBtnAdicionarProduto()
 * getCbCupom()
 * getLblCadeira()
 * getLblProdutos()
 * getLblTotal()
 * getBtnFinalizar()
 * getBtnVoltar()
 *
 *
 * NAO devem permanecer na View:
 *
 * carregarSessoes()
 * buscarSessaoSelecionada()
 * abrirCadeiras()
 * adicionarProduto()
 * calcularValorBilhete()
 * atualizarTotal()
 * finalizar()
 * voltar()
 *
 * Essas operacoes passam para CompraController.
 *
 *
 * CompraView tambem nao deve armazenar:
 *
 * Sala selecionada
 * Sessao selecionada
 * totalProdutos
 * linhaSelecionada
 * colunaSelecionada
 *
 * Esse estado pertence ao CompraController nesta arquitetura.
 *
 * ============================================================
 */