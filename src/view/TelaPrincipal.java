package view;

import javax.swing.*;

/*
 * ORIENTACOES PARA IMPLEMENTACAO
 *
 * Criar a tela principal do sistema de cinema.
 *
 * A tela deve possuir as seguintes opcoes:
 * - Cadastrar usuario;
 * - Listar filmes;
 * - Listar sessoes;
 * - Realizar compra;
 * - Alterar compra;
 * - Cancelar compra;
 * - Exibir compra;
 * - Sair.
 *
 * Utilizar:
 * - JFrame para a janela;
 * - JPanel para organizar os componentes;
 * - JButton para cada opcao.
 *
 * A tela deve armazenar o Usuario cadastrado e a Compra atual.
 *
 * Cada botao deve abrir a tela correspondente.
 *
 * Antes de realizar uma compra:
 * - Verificar se existe Usuario cadastrado.
 *
 * Antes de alterar, cancelar ou exibir:
 * - Verificar se existe uma Compra.
 *
 * Os filmes, salas e sessoes iniciais do cinema tambem devem
 * ser criados e relacionados nesta etapa.
 */


// teste
public class TelaPrincipal extends JFrame {

    public TelaPrincipal(){

        // Titulo da janela
        setTitle("Cinema");

        // Tamanho da janela
        setSize(500, 400);

        // Centraliza a janela na tela
        setLocationRelativeTo(null);

        // Encerra o programa quando fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Texto para testar
        JLabel titulo =
                new JLabel(
                        "Sistema de Cinema",
                        SwingConstants.CENTER
                );

        // Adiciona o texto na janela
        add(titulo);
    }
}