package controller;

public class UsuarioController {
    // Implementar a classe seguindo as orientacoes.
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