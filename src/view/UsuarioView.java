package view;

import javax.swing.*;
import java.awt.*;


public class UsuarioView extends JFrame {

    // Implementar seguindo as orientacoes.

}

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR UsuarioView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Construir o formulario visual de cadastro de usuario.
 *
 *
 * Criar componentes para:
 *
 * - tipo de usuario;
 * - user;
 * - CPF;
 * - senha;
 * - idade;
 * - Sexo;
 * - email;
 * - nome no cartao;
 * - numero do cartao;
 * - codigo verificador;
 * - origem;
 * - Cadastrar;
 * - Voltar.
 *
 *
 * Tipo de usuario:
 *
 * JComboBox<String>
 *
 * com:
 *
 * Usuario
 * Estudante
 * Critico
 *
 *
 * Sexo:
 *
 * JComboBox<Sexo>
 *
 * utilizando:
 *
 * Sexo.values()
 *
 *
 * Origem:
 *
 * Criar JLabel e JTextField, inicialmente invisiveis.
 *
 * Quem decide quando mostrar esses componentes sera o
 * UsuarioController.
 *
 *
 * Disponibilizar getters dos componentes que o Controller
 * precisa consultar ou alterar.
 *
 *
 * NAO deve existir nesta View:
 *
 * cadastrarUsuario()
 * alterarTipoUsuario()
 * voltar()
 *
 * Esses metodos pertencem ao UsuarioController.
 *
 *
 * UsuarioView tambem nao precisa mais possuir:
 *
 * Usuario usuario
 *
 * O usuario criado sera administrado pelo Controller.
 *
 * ============================================================
 */