package controller;

import enums.Sexo;
import model.*;
import view.UsuarioView;

import javax.swing.*;

public class UsuarioController {

    private UsuarioView view;
    private MenuController menu;

    public UsuarioController(
            UsuarioView view,
            MenuController menu) {

        this.view = view;
        this.menu = menu;

        view.getCbTipoUsuario().addActionListener(e -> alterarTipoUsuario());

        view.getBtnCadastrar().addActionListener(e -> cadastrarUsuario());

        view.getBtnVoltar().addActionListener(e -> voltar());

        view.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                voltar();
            }
        });
    }

    private void alterarTipoUsuario() {

        boolean critico = "Critico".equals(
                view.getCbTipoUsuario().getSelectedItem()
        );

        view.getLblOrigem().setVisible(critico);

        view.getTxtOrigem().setVisible(critico);
    }

    private void cadastrarUsuario() {

        try {

            String tipo = (String) view.getCbTipoUsuario().getSelectedItem();

            String user = view.getTxtUser().getText().trim();

            String cpf = view.getTxtCpf().getText().trim();

            String senha = new String(view.getTxtSenha().getPassword());

            int idade = Integer.parseInt(
                    view.getTxtIdade().getText().trim()
            );

            Sexo sexo = (Sexo) view.getCbSexo().getSelectedItem();

            String email = view.getTxtEmail().getText().trim();

            String nomeCartao = view.getTxtNomeCartao().getText().trim();

            String numeroCartao = view.getTxtNumeroCartao().getText().trim();

            String codigo = view.getTxtCodigoVerificador().getText().trim();

            if (user.isEmpty() ||
                    cpf.isEmpty() ||
                    senha.isEmpty() ||
                    idade < 0 ||
                    email.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "Preencha os dados obrigatorios e informe idade valida."
                );

                return;
            }

            Usuario usuario;

            if ("Estudante".equals(tipo)) {

                usuario = new Estudante(
                        user,
                        cpf,
                        senha,
                        idade,
                        sexo,
                        email,
                        nomeCartao,
                        numeroCartao,
                        codigo
                );

            } else if ("Critico".equals(tipo)) {

                String origem = view.getTxtOrigem().getText().trim();

                if (origem.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            view,
                            "Informe a origem do critico."
                    );

                    return;
                }

                usuario = new Critico(
                        user,
                        cpf,
                        senha,
                        idade,
                        sexo,
                        email,
                        nomeCartao,
                        numeroCartao,
                        codigo,
                        origem
                );

            } else {

                usuario = new Usuario(
                        user,
                        cpf,
                        senha,
                        idade,
                        sexo,
                        email,
                        nomeCartao,
                        numeroCartao,
                        codigo
                );
            }

            menu.setUsuario(usuario);

            JOptionPane.showMessageDialog(
                    view,
                    "Usuario cadastrado com sucesso."
            );

            voltar();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    view,
                    "A idade deve ser um numero inteiro."
            );
        }
    }

    private void voltar() {

        menu.voltarMenu();

        view.dispose();
    }
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR UsuarioController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Controlar o formulario de cadastro apresentado por
 * UsuarioView.
 *
 *
 * ATRIBUTOS:
 *
 * - UsuarioView view;
 * - MenuController menuController.
 *
 *
 * CONSTRUTOR:
 *
 * Receber UsuarioView e MenuController.
 *
 * Guardar as referencias e configurar os eventos.
 *
 *
 * EVENTOS:
 *
 * Tipo de usuario alterado -> alterarTipoUsuario()
 * Cadastrar                -> cadastrarUsuario()
 * Voltar                   -> voltar()
 *
 *
 * alterarTipoUsuario():
 *
 * Consultar o tipo selecionado na View.
 *
 * Se:
 *
 * "Critico"
 *
 * mostrar:
 *
 * - JLabel Origem;
 * - JTextField Origem.
 *
 * Caso contrario, esconder os dois.
 *
 *
 * cadastrarUsuario():
 *
 * Obter da View:
 *
 * - tipo;
 * - user;
 * - CPF;
 * - senha;
 * - idade;
 * - sexo;
 * - email;
 * - nome do cartao;
 * - numero do cartao;
 * - codigo verificador;
 * - origem, quando for Critico.
 *
 * Converter idade de String para int.
 *
 * Tratar NumberFormatException.
 *
 *
 * HERANCA:
 *
 * Dependendo do tipo selecionado:
 *
 * "Usuario"
 *       |
 *       v
 * new Usuario(...)
 *
 * "Estudante"
 *       |
 *       v
 * new Estudante(...)
 *
 * "Critico"
 *       |
 *       v
 * new Critico(...)
 *
 *
 * Todos podem ser armazenados em:
 *
 * Usuario usuario
 *
 * pois Estudante e Critico herdam de Usuario.
 *
 *
 * Depois do cadastro:
 *
 * enviar o objeto para:
 *
 * menuController.setUsuario(usuario)
 *
 * Dessa maneira o mesmo objeto podera ser utilizado nas
 * outras funcionalidades.
 *
 *
 * voltar():
 *
 * Solicitar ao MenuController que mostre novamente o menu e
 * fechar UsuarioView.
 *
 *
 * NAO FAZER:
 *
 * - construir JPanel;
 * - criar JTextField;
 * - criar JButton;
 * - colocar regras especificas de Usuario dentro da View.
 *
 * ============================================================
 */