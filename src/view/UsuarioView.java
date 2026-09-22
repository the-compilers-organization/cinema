package view;

import enums.Sexo;

import model.Usuario;
import model.Estudante;
import model.Critico;

import javax.swing.*;
import java.awt.*;

/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR A CLASSE UsuarioView
 * ============================================================
 *
 * OBJETIVO:
 *
 * Criar a tela responsavel pelo cadastro de usuarios do
 * sistema de cinema.
 *
 * A classe UsuarioView deve herdar de JFrame.
 *
 * A tela deve permitir cadastrar tres tipos de usuario:
 *
 * - Usuario;
 * - Estudante;
 * - Critico.
 *
 * Dependendo do tipo selecionado, deve ser criado um objeto
 * da classe correspondente.
 *
 *
 * 1. ATRIBUTOS PRINCIPAIS
 *
 * A classe deve possuir:
 *
 * - uma referencia para MenuView;
 * - uma referencia do tipo Usuario.
 *
 * Pensar em atributos equivalentes a:
 *
 * MenuView menu
 * Usuario usuario
 *
 * A referencia para MenuView sera utilizada para voltar para
 * a mesma tela principal.
 *
 * O atributo usuario guardara o objeto cadastrado.
 *
 *
 * 2. HERANCA UTILIZADA NESTA TELA
 *
 * No Model existe uma estrutura de heranca:
 *
 *                  Usuario
 *                 /       \
 *                /         \
 *         Estudante       Critico
 *
 * Portanto:
 *
 * Estudante E UM Usuario.
 * Critico E UM Usuario.
 *
 * Isso permite declarar:
 *
 * Usuario usuario;
 *
 * e posteriormente armazenar nesse atributo:
 *
 * new Usuario(...)
 *
 * ou:
 *
 * new Estudante(...)
 *
 * ou:
 *
 * new Critico(...)
 *
 * Isso e uma aplicacao de heranca e polimorfismo.
 *
 *
 * 3. COMPONENTE PARA ESCOLHER O TIPO
 *
 * Criar um:
 *
 * JComboBox<String>
 *
 * contendo:
 *
 * "Usuario"
 * "Estudante"
 * "Critico"
 *
 * Esse componente sera utilizado para decidir qual classe
 * devera ser instanciada no momento do cadastro.
 *
 *
 * 4. CAMPOS COMUNS AOS USUARIOS
 *
 * Criar componentes para receber:
 *
 * - usuario;
 * - CPF;
 * - senha;
 * - idade;
 * - sexo;
 * - email;
 * - nome no cartao;
 * - numero do cartao;
 * - codigo verificador.
 *
 * Esses dados sao utilizados pelo construtor de Usuario e
 * tambem pelos construtores das subclasses.
 *
 *
 * 5. CAMPO USUARIO
 *
 * Criar:
 *
 * JTextField txtUser
 *
 * Esse campo recebera o nome de usuario utilizado pelo
 * sistema.
 *
 *
 * 6. CAMPO CPF
 *
 * Criar:
 *
 * JTextField txtCpf
 *
 * O CPF deve ser tratado como String.
 *
 * Nao utilizar int para CPF.
 *
 * Alem de nao ser utilizado para operacoes matematicas, um
 * CPF pode possuir zeros no inicio.
 *
 *
 * 7. CAMPO SENHA
 *
 * Para a senha, utilizar:
 *
 * JPasswordField
 *
 * em vez de JTextField.
 *
 * Isso permite que os caracteres digitados nao aparecam
 * normalmente na tela.
 *
 *
 * 8. CAMPO IDADE
 *
 * Criar:
 *
 * JTextField txtIdade
 *
 * O usuario digitara a idade como texto.
 *
 * Entretanto, o construtor do Model espera:
 *
 * int idade
 *
 * Portanto, posteriormente sera necessario converter a
 * String para int.
 *
 *
 * 9. COMPONENTE SEXO
 *
 * O projeto possui:
 *
 * enum Sexo
 *
 * Portanto, criar:
 *
 * JComboBox<Sexo>
 *
 * Para preencher automaticamente com todos os valores do
 * enum, utilizar:
 *
 * Sexo.values()
 *
 * Assim, nao sera necessario escrever manualmente:
 *
 * F
 * M
 * NB
 * NI
 *
 *
 * 10. DEMAIS CAMPOS
 *
 * Criar JTextField para:
 *
 * - email;
 * - nome no cartao;
 * - numero do cartao;
 * - codigo verificador.
 *
 * Cada campo deve possuir um JLabel correspondente para que
 * o usuario saiba qual informacao deve preencher.
 *
 *
 * 11. CAMPO ESPECIAL DO CRITICO
 *
 * A classe Critico possui um atributo adicional:
 *
 * origem
 *
 * Esse atributo nao pertence ao Usuario comum nem ao
 * Estudante.
 *
 * Portanto, criar:
 *
 * JLabel lblOrigem
 *
 * e:
 *
 * JTextField txtOrigem
 *
 *
 * 12. ORIGEM DEVE COMECAR ESCONDIDA
 *
 * Quando UsuarioView for aberta, a opcao inicial normalmente
 * sera:
 *
 * "Usuario"
 *
 * Portanto, o campo origem nao deve aparecer inicialmente.
 *
 * Deixar:
 *
 * lblOrigem
 *
 * e:
 *
 * txtOrigem
 *
 * invisiveis.
 *
 *
 * 13. QUANDO MOSTRAR ORIGEM?
 *
 * O campo deve aparecer somente quando:
 *
 * "Critico"
 *
 * estiver selecionado.
 *
 * Exemplos:
 *
 * Usuario selecionado:
 *
 * Origem -> escondida
 *
 *
 * Estudante selecionado:
 *
 * Origem -> escondida
 *
 *
 * Critico selecionado:
 *
 * Origem: [________________________]
 *
 *
 * 14. BOTOES
 *
 * Criar:
 *
 * JButton btnCadastrar
 * JButton btnVoltar
 *
 * O primeiro sera responsavel por realizar o cadastro.
 *
 * O segundo retornara para MenuView.
 *
 *
 * 15. CONSTRUTOR
 *
 * O construtor deve receber:
 *
 * MenuView menu
 *
 * e guardar essa referencia utilizando this.
 *
 * A ideia:
 *
 * this.menu = menu;
 *
 *
 * 16. CONFIGURAR A JANELA
 *
 * Configurar o JFrame com:
 *
 * titulo:
 *
 * "Cadastrar Usuario"
 *
 * tamanho aproximado:
 *
 * 550 x 600
 *
 * utilizar:
 *
 * JFrame.DISPOSE_ON_CLOSE
 *
 * e centralizar a janela.
 *
 *
 * 17. POR QUE DISPOSE_ON_CLOSE?
 *
 * UsuarioView e uma janela secundaria.
 *
 * Portanto, nao utilizar:
 *
 * JFrame.EXIT_ON_CLOSE
 *
 * pois isso encerraria toda a aplicacao.
 *
 *
 * 18. ORGANIZAR OS COMPONENTES
 *
 * Criar um JPanel utilizando GridLayout.
 *
 * A ideia e possuir duas colunas:
 *
 * primeira coluna:
 *
 * JLabel
 *
 * segunda coluna:
 *
 * componente utilizado para digitar/selecionar o dado.
 *
 * Exemplo:
 *
 * +------------------------------------------------+
 * | Tipo:               | [ Usuario          v ] |
 * | Usuario:            | [___________________] |
 * | CPF:                | [___________________] |
 * | Senha:              | [___________________] |
 * | Idade:              | [___________________] |
 * | Sexo:               | [ F              v ] |
 * | Email:              | [___________________] |
 * | Nome no cartao:     | [___________________] |
 * | Numero do cartao:   | [___________________] |
 * | Codigo verificador: | [___________________] |
 * | Origem:             | [___________________] |
 * | [Voltar]            | [Cadastrar]          |
 * +------------------------------------------------+
 *
 * A linha Origem somente deve ficar visivel quando o tipo
 * selecionado for Critico.
 *
 *
 * 19. BORDA DO PAINEL
 *
 * Pode ser utilizada:
 *
 * BorderFactory.createEmptyBorder(...)
 *
 * para criar um espacamento entre os componentes e as
 * extremidades da janela.
 *
 *
 * 20. EVENTO DO TIPO DE USUARIO
 *
 * Adicionar um ActionListener em:
 *
 * cbTipoUsuario
 *
 * Quando o usuario alterar a opcao, chamar:
 *
 * alterarTipoUsuario()
 *
 *
 * 21. METODO alterarTipoUsuario()
 *
 * Criar:
 *
 * private void alterarTipoUsuario()
 *
 * Esse metodo deve descobrir qual String esta selecionada no
 * JComboBox.
 *
 * Utilizar:
 *
 * cbTipoUsuario.getSelectedItem()
 *
 * Como o JComboBox armazena String, realizar a conversao
 * correspondente.
 *
 *
 * 22. VERIFICAR SE E CRITICO
 *
 * Depois de obter o tipo selecionado, verificar se:
 *
 * tipo.equals("Critico")
 *
 * O resultado sera boolean.
 *
 * Se for true:
 *
 * mostrar:
 *
 * lblOrigem
 * txtOrigem
 *
 * Se for false:
 *
 * esconder os dois.
 *
 * Uma forma simples e utilizar o mesmo boolean em:
 *
 * setVisible(...)
 *
 *
 * 23. EVENTO DO BOTAO CADASTRAR
 *
 * Adicionar um ActionListener em:
 *
 * btnCadastrar
 *
 * Quando clicado, chamar:
 *
 * cadastrarUsuario()
 *
 *
 * 24. METODO cadastrarUsuario()
 *
 * Criar:
 *
 * private void cadastrarUsuario()
 *
 * Esse metodo deve:
 *
 * - descobrir o tipo selecionado;
 * - obter os valores dos campos;
 * - converter a idade;
 * - obter o Sexo;
 * - decidir qual classe sera instanciada;
 * - guardar o objeto no atributo usuario;
 * - informar que o cadastro foi realizado.
 *
 *
 * 25. OBTER O TIPO
 *
 * Obter o item selecionado em:
 *
 * cbTipoUsuario
 *
 * e guardar em uma String.
 *
 * Esse valor sera utilizado posteriormente para decidir entre:
 *
 * Usuario
 * Estudante
 * Critico
 *
 *
 * 26. OBTER OS JTextField
 *
 * Para os campos JTextField, utilizar:
 *
 * getText()
 *
 * Por exemplo:
 *
 * txtUser.getText()
 * txtCpf.getText()
 * txtEmail.getText()
 *
 * Guardar os valores em variaveis locais antes de criar o
 * objeto.
 *
 *
 * 27. OBTER A SENHA
 *
 * JPasswordField possui:
 *
 * getPassword()
 *
 * que retorna um vetor de char.
 *
 * Para utilizar o construtor atual do Model, converter esse
 * valor para String.
 *
 * A ideia utilizada nesta versao e:
 *
 * new String(
 *        txtSenha.getPassword()
 * )
 *
 *
 * 28. CONVERTER A IDADE
 *
 * txtIdade.getText()
 *
 * retorna String.
 *
 * Entretanto, idade deve ser int.
 *
 * Portanto, utilizar:
 *
 * Integer.parseInt(...)
 *
 * Exemplo:
 *
 * texto:
 *
 * "20"
 *
 * depois da conversao:
 *
 * int idade = 20
 *
 *
 * 29. POSSIVEL ERRO NA IDADE
 *
 * O usuario pode digitar:
 *
 * "vinte"
 *
 * Nesse caso:
 *
 * Integer.parseInt("vinte")
 *
 * nao consegue realizar a conversao e gera:
 *
 * NumberFormatException
 *
 * Por isso o cadastro deve tratar esse erro.
 *
 *
 * 30. UTILIZAR try/catch
 *
 * Colocar a parte do cadastro que realiza a conversao dentro
 * de:
 *
 * try
 *
 * e tratar:
 *
 * NumberFormatException
 *
 * no catch.
 *
 * Se ocorrer o erro, apresentar:
 *
 * "A idade deve ser um numero inteiro."
 *
 * Assim o programa nao encerra por causa de uma entrada
 * incorreta.
 *
 *
 * 31. OBTER O SEXO
 *
 * Como cbSexo e:
 *
 * JComboBox<Sexo>
 *
 * o item selecionado sera um valor do enum Sexo.
 *
 * Obter:
 *
 * cbSexo.getSelectedItem()
 *
 * e converter para:
 *
 * Sexo
 *
 *
 * 32. DECIDIR QUAL OBJETO CRIAR
 *
 * Depois de obter todos os dados, verificar o tipo de usuario.
 *
 * Existem tres possibilidades:
 *
 * "Estudante"
 *
 * criar:
 *
 * Estudante
 *
 *
 * "Critico"
 *
 * criar:
 *
 * Critico
 *
 *
 * qualquer outro caso:
 *
 * criar:
 *
 * Usuario
 *
 *
 * 33. CADASTRO DE ESTUDANTE
 *
 * Se o tipo selecionado for:
 *
 * "Estudante"
 *
 * criar um objeto Estudante utilizando os dados informados
 * nos campos.
 *
 * Como Estudante herda de Usuario, o objeto pode ser guardado
 * no atributo:
 *
 * Usuario usuario
 *
 * Portanto, conceitualmente:
 *
 * Usuario usuario
 *        ^
 *        |
 * Estudante
 *
 *
 * 34. CADASTRO DE CRITICO
 *
 * Se o tipo selecionado for:
 *
 * "Critico"
 *
 * criar um objeto Critico.
 *
 * Alem dos dados comuns de Usuario, o construtor de Critico
 * precisa receber:
 *
 * origem
 *
 * Obter esse valor atraves de:
 *
 * txtOrigem.getText()
 *
 *
 * 35. CADASTRO DE USUARIO COMUM
 *
 * Se o tipo selecionado nao for Estudante nem Critico,
 * criar um objeto:
 *
 * Usuario
 *
 * utilizando os dados informados.
 *
 *
 * 36. POLIMORFISMO NESTA TELA
 *
 * Observe:
 *
 * O atributo e:
 *
 * Usuario usuario
 *
 * mas podemos fazer conceitualmente:
 *
 * usuario = new Usuario(...)
 *
 * ou:
 *
 * usuario = new Estudante(...)
 *
 * ou:
 *
 * usuario = new Critico(...)
 *
 * Isso e possivel porque:
 *
 * Estudante extends Usuario
 *
 * e:
 *
 * Critico extends Usuario
 *
 *
 * Visualmente:
 *
 *                  Usuario
 *                     ^
 *                    / \
 *                   /   \
 *                  /     \
 *          Estudante     Critico
 *
 *
 * Portanto, uma referencia da classe mae pode apontar para
 * objetos das classes filhas.
 *
 *
 * 37. MENSAGEM DE SUCESSO
 *
 * Depois que o objeto for criado corretamente, apresentar
 * uma mensagem utilizando:
 *
 * JOptionPane.showMessageDialog(...)
 *
 * informando:
 *
 * "Usuario cadastrado com sucesso."
 *
 *
 * 38. IMPORTANTE: O OBJETO NAO DEVE SER APENAS LOCAL
 *
 * Evitar criar:
 *
 * Usuario usuario
 *
 * apenas como variavel local dentro de cadastrarUsuario().
 *
 * A classe ja deve possuir:
 *
 * private Usuario usuario;
 *
 * e o objeto criado deve ser armazenado nesse atributo.
 *
 * Assim ele pode ser recuperado posteriormente.
 *
 *
 * 39. METODO getUsuario()
 *
 * Criar um getter:
 *
 * public Usuario getUsuario()
 *
 * que retorna o atributo:
 *
 * usuario
 *
 * Esse metodo permite que outra parte do sistema obtenha o
 * usuario cadastrado.
 *
 *
 * 40. EVENTO DO BOTAO VOLTAR
 *
 * Adicionar um ActionListener em:
 *
 * btnVoltar
 *
 * Quando clicado, chamar:
 *
 * voltar()
 *
 *
 * 41. METODO voltar()
 *
 * Criar:
 *
 * private void voltar()
 *
 * Esse metodo deve:
 *
 * - tornar a mesma MenuView visivel novamente;
 * - fechar UsuarioView.
 *
 * Utilizar a ideia:
 *
 * menu.setVisible(true);
 *
 * depois:
 *
 * dispose();
 *
 *
 * 42. NAO CRIAR NOVA MenuView
 *
 * Evitar:
 *
 * new MenuView(...)
 *
 * dentro de voltar().
 *
 * UsuarioView ja recebeu a instancia da MenuView que abriu
 * essa tela.
 *
 * Portanto, reutilizar essa mesma instancia.
 *
 *
 * 43. FLUXO DO TIPO DE USUARIO
 *
 *              JComboBox
 *                  |
 *                  v
 *        +-------------------+
 *        | Tipo selecionado? |
 *        +-------------------+
 *          /       |       \
 *         /        |        \
 *        v         v         v
 *    Usuario   Estudante   Critico
 *                              |
 *                              v
 *                      mostrar Origem
 *
 *
 * 44. FLUXO DO CADASTRO
 *
 * Campos da tela
 *      |
 *      +---- user
 *      +---- cpf
 *      +---- senha
 *      +---- idade
 *      +---- sexo
 *      +---- email
 *      +---- nomeCartao
 *      +---- numeroCartao
 *      +---- codigo
 *      +---- origem, se Critico
 *      |
 *      v
 * cadastrarUsuario()
 *      |
 *      v
 * verificar tipo
 *      |
 *      +----------+-----------+
 *      |          |           |
 *      v          v           v
 *   Usuario   Estudante    Critico
 *      |          |           |
 *      +----------+-----------+
 *                 |
 *                 v
 *         atributo usuario
 *
 *
 * 45. RELACAO COM O ENUM Sexo
 *
 * A View nao precisa criar manualmente as opcoes de sexo.
 *
 * O enum ja possui os valores.
 *
 * Portanto:
 *
 * Sexo.values()
 *      |
 *      v
 * JComboBox<Sexo>
 *
 * Se futuramente um novo valor for adicionado ao enum, o
 * JComboBox tambem podera apresenta-lo automaticamente.
 *
 *
 * 46. RESPONSABILIDADE DA UsuarioView
 *
 * UsuarioView deve ser responsavel principalmente por:
 *
 * - montar o formulario;
 * - receber os dados digitados;
 * - permitir escolher o tipo de usuario;
 * - mostrar/esconder o campo Origem;
 * - converter os dados necessarios;
 * - criar o objeto correspondente nesta versao sem Controller;
 * - apresentar mensagens;
 * - voltar para MenuView.
 *
 *
 * 47. RESPONSABILIDADE DO MODEL
 *
 * As classes:
 *
 * Usuario
 * Estudante
 * Critico
 *
 * continuam sendo responsaveis pelos dados e pelas regras
 * relacionadas aos usuarios.
 *
 * Evitar colocar na View regras que pertencem ao Model.
 *
 *
 * 48. ATENCAO COM A INTEGRACAO ENTRE AS TELAS
 *
 * Nesta primeira versao, UsuarioView possui:
 *
 * Usuario usuario
 *
 * e:
 *
 * getUsuario()
 *
 * Entretanto, existe uma questao importante:
 *
 * quando UsuarioView for fechada com dispose(), as outras
 * telas precisam ter alguma forma de continuar acessando o
 * usuario cadastrado.
 *
 * Portanto, posteriormente sera necessario compartilhar o
 * usuario atual com o restante do sistema.
 *
 * Como o projeto ainda nao possui Controller, uma possibilidade
 * simples sera MenuView guardar uma referencia para o usuario
 * cadastrado.
 *
 *
 * 49. POR QUE ISSO SERA IMPORTANTE?
 *
 * CompraView precisara saber qual Usuario esta realizando
 * uma compra.
 *
 * CriticaView precisara saber se o Usuario atual e um Critico.
 *
 * Alem disso, as regras de Estudante e Critico dependem do
 * tipo real do objeto cadastrado.
 *
 * Portanto, futuramente o fluxo pode ser:
 *
 * UsuarioView
 *      |
 *      | cadastra
 *      v
 * Usuario / Estudante / Critico
 *      |
 *      v
 * MenuView
 *      |
 *      +----> CompraView
 *      |
 *      +----> CriticaView
 *
 *
 * 50. NAO CRIAR NOVO USUARIO EM CADA TELA
 *
 * Depois que o usuario estiver cadastrado, CompraView ou
 * CriticaView nao devem criar outro Usuario apenas para
 * executar suas funcionalidades.
 *
 * O ideal e compartilhar o mesmo objeto cadastrado.
 *
 * Exemplo conceitual:
 *
 * UsuarioView
 *       |
 *       | cria
 *       v
 *    usuario
 *       |
 *       v
 *   MenuView
 *      /   \
 *     /     \
 *    v       v
 * Compra   Critica
 *
 *
 * 51. RELACAO COM CriticaView
 *
 * Esse compartilhamento sera especialmente importante para
 * Critico.
 *
 * Critico possui operacoes especificas para:
 *
 * - atribuir nota;
 * - atribuir critica.
 *
 * Portanto, CriticaView deve posteriormente utilizar o mesmo
 * objeto Critico criado nesta tela.
 *
 * Evitar criar um Critico ficticio dentro de CriticaView.
 *
 *
 * 52. RELACAO COM CompraView
 *
 * CompraView tambem precisara posteriormente do Usuario que
 * esta realizando a compra.
 *
 * Isso permitira utilizar corretamente as regras existentes
 * para:
 *
 * Usuario
 * Estudante
 * Critico
 *
 * e seus respectivos calculos.
 *
 *
 * 53. VALIDACOES FUTURAS
 *
 * Nesta primeira implementacao, o tratamento principal mostrado
 * e para uma idade que nao seja um numero inteiro.
 *
 * Posteriormente podem ser adicionadas validacoes como:
 *
 * - campos obrigatorios vazios;
 * - idade negativa;
 * - email vazio ou invalido;
 * - CPF vazio;
 * - senha vazia;
 * - origem vazia quando o usuario for Critico.
 *
 * Essas validacoes devem ser adicionadas de acordo com as
 * regras definidas para o projeto.
 *
 *
 * 54. NAO INVENTAR REGRAS NA VIEW
 *
 * Se o enunciado ou o Model nao estabelecer, por exemplo:
 *
 * - quantidade minima de caracteres da senha;
 * - formato obrigatorio do CPF;
 * - formato especifico do email;
 *
 * nao criar essas regras arbitrariamente dentro da View.
 *
 * A interface deve respeitar as regras definidas pelo projeto.
 *
 *
 * 55. RESUMO DAS RESPONSABILIDADES
 *
 * MenuView:
 *
 * - abrir UsuarioView;
 * - posteriormente guardar/compartilhar o usuario atual.
 *
 *
 * UsuarioView:
 *
 * - apresentar o formulario;
 * - receber os dados;
 * - escolher o tipo;
 * - criar Usuario, Estudante ou Critico;
 * - disponibilizar o usuario cadastrado;
 * - voltar para MenuView.
 *
 *
 * Usuario:
 *
 * - representar o usuario comum;
 * - possuir os dados e comportamentos definidos no Model.
 *
 *
 * Estudante:
 *
 * - herdar de Usuario;
 * - possuir as regras especificas de Estudante.
 *
 *
 * Critico:
 *
 * - herdar de Usuario;
 * - possuir o atributo Origem;
 * - possuir as operacoes especificas de Critico.
 *
 *
 * Sexo:
 *
 * - representar as opcoes de sexo atraves de enum.
 *
 *
 * 56. FLUXO GERAL
 *
 *                       MenuView
 *                          |
 *                          | Cadastrar Usuario
 *                          v
 *                     UsuarioView
 *                          |
 *                          v
 *                  escolher tipo
 *                          |
 *              +-----------+-----------+
 *              |           |           |
 *              v           v           v
 *           Usuario    Estudante    Critico
 *                                      |
 *                                      v
 *                               campo Origem
 *
 *              +-----------+-----------+
 *              |           |           |
 *              +-----------+-----------+
 *                          |
 *                          v
 *                 cadastrarUsuario()
 *                          |
 *                          v
 *                  Usuario usuario
 *                          |
 *                          v
 *                    getUsuario()
 *
 * ============================================================
 */
public class UsuarioView extends JFrame {

    // Implementar seguindo as orientacoes acima.

}