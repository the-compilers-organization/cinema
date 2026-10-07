package view;

import javax.swing.*;
import java.awt.*;

public class CriticaView extends JFrame {

    // Implementar seguindo as orientacoes.

}

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CriticaView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir a interface visual utilizada para avaliar um
 * Filme.
 *
 *
 * Criar:
 *
 * JComboBox<String> para os filmes;
 *
 * JSpinner para nota;
 *
 * JTextArea para critica;
 *
 * JButton Enviar;
 *
 * JButton Voltar.
 *
 *
 * NOTA:
 *
 * Utilizar SpinnerNumberModel permitindo valores:
 *
 * minimo: 0.0
 * maximo: 10.0
 * passo: 0.5
 *
 *
 * JTextArea:
 *
 * habilitar quebra de linha e quebra por palavras.
 *
 * Colocar dentro de JScrollPane.
 *
 *
 * A View NAO deve:
 *
 * - receber Filme[];
 * - receber Critico;
 * - carregar os filmes;
 * - procurar Filme selecionado;
 * - atribuir nota;
 * - criar Critica;
 * - chamar atribuirCritica();
 *
 * Tudo isso sera coordenado pelo CriticaController.
 *
 *
 * Disponibilizar getters para:
 *
 * getCbFilmes()
 * getSpNota()
 * getTxtCritica()
 * getBtnEnviar()
 * getBtnVoltar()
 *
 * ============================================================
 */