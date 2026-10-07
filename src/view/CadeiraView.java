package view;

import javax.swing.*;
import java.awt.*;

public class CadeiraView extends JFrame {

    // Implementar seguindo as orientacoes.

}

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CadeiraView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir visualmente o mapa de cadeiras.
 *
 *
 * Criar:
 *
 * JButton[10][15]
 *
 * para representar a matriz de cadeiras.
 *
 *
 * Utilizar:
 *
 * GridLayout(10, 15, ...)
 *
 *
 * Criar tambem:
 *
 * JLabel "TELA"
 * JButton Voltar
 * JButton Confirmar
 *
 *
 * IMPORTANTE:
 *
 * A View cria os 150 botoes, mas nao decide quais cadeiras
 * estao ocupadas.
 *
 * Essa informacao sera obtida pelo CadeiraController atraves
 * da Sessao.
 *
 *
 * A View NAO deve:
 *
 * - receber Sessao;
 * - chamar cadeiraDisponivel();
 * - chamar ocuparCadeira();
 * - chamar liberarCadeira();
 * - guardar linhaSelecionada;
 * - guardar colunaSelecionada;
 * - chamar CompraView diretamente.
 *
 *
 * Disponibilizar:
 *
 * getBotoesCadeiras()
 * getBtnVoltar()
 * getBtnConfirmar()
 *
 * ============================================================
 */