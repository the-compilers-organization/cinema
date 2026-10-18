package controller;

import model.*;
import view.*;
import javax.swing.*;
import java.awt.*;

public class CadeiraController {
    private CadeiraView view;
    private Sessao sessao;
    private CompraController compra;
    private int quantidade;
    private int ingressoEditado = -1;
    private int linha = -1;
    private int coluna = -1;

    public CadeiraController(CadeiraView view, Sessao sessao, CompraController compra, int quantidade) {
        this.view = view;
        this.sessao = sessao;
        this.compra = compra;
        this.quantidade = quantidade;
        JButton[][] botoes = view.getBotoesCadeiras();
        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes[i].length; j++) {
                int l = i;
                int c = j;
                botoes[i][j].addActionListener(e -> selecionar(l, c));
            }
        }
        view.getBtnConfirmar().addActionListener(e -> confirmar());
        view.getBtnVoltar().addActionListener(e -> voltar());
        view.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) { voltar(); }
        });
        atualizar();
    }

    public CadeiraController(CadeiraView view, Sessao sessao, CompraController compra,
            int quantidade, int ingressoEditado) {
        this(view, sessao, compra, quantidade);
        this.ingressoEditado = ingressoEditado;
        atualizar();
    }

    // Ao reabrir a selecao, destacar o grupo escolhido anteriormente.
    // Nenhuma cadeira e ocupada definitivamente nesta etapa.
    public void preSelecionarCadeiras(int[] linhas, int[] colunas, int qtd) {
        if (qtd != quantidade || qtd <= 0) return;
        int l = linhas[0];
        int c = colunas[0];
        for (int i = 0; i < qtd; i++) {
            if (linhas[i] != l || colunas[i] != c + i) return;
        }
        if (grupoLivre(l, c)) {
            linha = l;
            coluna = c;
            atualizar();
        }
    }

    private boolean grupoLivre(int l, int c) {
        if (c < 0 || c + quantidade > 15) return false;
        for (int j = c; j < c + quantidade; j++) {
            if ((!sessao.cadeiraDisponivel(l, j) && !compra.cadeiraDaCompraEmEdicao(sessao, l, j)) ||
                    (ingressoEditado >= 0 && compra.cadeiraJaNoRascunho(l, j, ingressoEditado))) return false;
        }
        return true;
    }

    private void selecionar(int l, int c) {
        if (!grupoLivre(l, c)) {
            JOptionPane.showMessageDialog(view, "Nao ha " + quantidade + " cadeiras juntas disponiveis.");
            return;
        }
        linha = l;
        coluna = c;
        atualizar();
    }

    private void atualizar() {
        JButton[][] botoes = view.getBotoesCadeiras();
        Color rosinha = Color.decode("#edc0df");
        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes[i].length; j++) {
                boolean livre = (sessao.cadeiraDisponivel(i, j) || compra.cadeiraDaCompraEmEdicao(sessao, i, j)) &&
                        (ingressoEditado < 0 || !compra.cadeiraJaNoRascunho(i, j, ingressoEditado));
                botoes[i][j].setEnabled(livre);
                botoes[i][j].setText(livre ? String.format("%02d", j + 1) : "X");
                botoes[i][j].setBackground(livre ? rosinha : Color.LIGHT_GRAY);
                if (i == linha && j >= coluna && j < coluna + quantidade) {
                    botoes[i][j].setBackground(Color.GREEN);
                }
            }
        }
        view.setTitle("Escolher " + quantidade + " cadeira(s) juntas");
    }

    private void confirmar() {
        if (linha < 0 || !grupoLivre(linha, coluna)) {
            JOptionPane.showMessageDialog(view, "Selecione cadeiras disponiveis.");
            return;
        }
        if (ingressoEditado >= 0) compra.trocarCadeiraIngresso(ingressoEditado, linha, coluna);
        else compra.setCadeirasSelecionadas(linha, coluna, quantidade);
        compra.mostrarView();
        view.dispose();
    }

    private void voltar() {
        compra.mostrarView();
        view.dispose();
    }
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