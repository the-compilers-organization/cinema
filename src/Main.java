// public class Main {

//     // === FILMES ===
//     // O auto da compadecida
//     // Senhor dos aneis, o retorno do rei
//     // O  Fabuloso Destino de Amélie Poulain
//     // Bacurau
//     // A Sociedade dos poetas mortos
//     // Ainda Estou Aqui
    
//     public static void main(String[] args){
//         Filme filme1 = new Filme("O Auto da Compadcida", 104, "Dois amigos nordestinos enfrentam situações de pobreza, esperteza e confusão enquanto tentam sobreviver e conseguir dinheiro.", 20.00);
//         Filme filme2 = new Filme("O Senhor dos Anéis: O Retorno do Rei", 201, "Frodo e Sam seguem em direção à Montanha da Perdição enquanto seus aliados enfrentam a batalha final contra as forças de Sauron.", 20.00);
//         Filme filme3 = new Filme("O Fabuloso Destino de Amélie Poulain", 122, "Amélie é uma jovem parisiense que decide ajudar secretamente as pessoas ao seu redor enquanto busca transformar sua própria vida.", 20.00);
//         Filme filme4 = new Filme("Bacurau", 131, "Os moradores de uma pequena cidade do sertão percebem que sua comunidade desapareceu dos mapas e precisam se unir diante de uma ameaça misteriosa.", 20.00);
//         Filme filme5 = new Filme("A Sociedade dos Poetas Mortos", 128, "Um professor de literatura inspira seus alunos a enxergar a vida de uma maneira diferente e a buscar seus próprios caminhos.", 20.00);
//         Filme filme6 = new Filme("Ainda Estou Aqui", 135, "Uma família enfrenta as consequências da ditadura militar brasileira enquanto busca preservar sua memória e sua história.", 20.00);
    
//         //agrupando filmes para um vetor (array de objtos FILMe)
//         Filme[] filmes = {filme1, filme2, filme3, filme4, filme5, filme6};
        
//         //exibir os dados dos filmes
//         // para cada 'Filme f (f é a variavel)' em filmes faça... + obj (for-each)
//         for(Filme f : filmes){
//             System.out.println("Filme: " + f.getNome());     System.out.println();
//             System.out.println("Duracao: " + f.getDuracao() + " minutos");
//             System.out.println("Sinopse: " + f.getSinopse());
//             System.out.println("Valor: R$ " + f.getValor());
//             System.out.println("=============================================================");
//         }
        
    
//     }

// }





import enums.TipoSala;

import model.Filme;
import model.Sala;
import model.Sessao;

import view.MenuView;

import controller.MenuController;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Filme filme1 = new Filme(
                    "Interestelar",
                    169,
                    "Viagem espacial.",
                    30.00
            );

            Filme filme2 = new Filme(
                    "Batman",
                    176,
                    "Filme do Batman.",
                    25.00
            );

            Filme filme3 = new Filme(
                    "Avatar",
                    192,
                    "Pandora.",
                    35.00
            );

            Filme[] filmes = new Filme[10];

            filmes[0] = filme1;
            filmes[1] = filme2;
            filmes[2] = filme3;

            Sala sala1 = new Sala(
                    1,
                    TipoSala.COMUM
            );

            Sala sala2 = new Sala(
                    2,
                    TipoSala.SALA3D
            );

            Sala sala3 = new Sala(
                    3,
                    TipoSala.XD
            );

            Sessao sessao1 = new Sessao(
                    "18:00",
                    filme1
            );

            Sessao sessao2 = new Sessao(
                    "20:00",
                    filme2
            );

            Sessao sessao3 = new Sessao(
                    "21:30",
                    filme3
            );

            Sessao sessao4 = new Sessao(
                    "22:00",
                    filme1
            );

            sala1.adicionarSessao(sessao1);
            sala1.adicionarSessao(sessao2);

            sala2.adicionarSessao(sessao3);

            sala3.adicionarSessao(sessao4);

            Sala[] salas = new Sala[10];

            salas[0] = sala1;
            salas[1] = sala2;
            salas[2] = sala3;

            MenuView menu = new MenuView();

            new MenuController(
                    menu,
                    filmes,
                    salas
            );

            menu.setVisible(true);
        });
    }
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR Main
 * ============================================================
 *
 * OBJETIVO:
 *
 * Ser o ponto inicial da aplicacao.
 *
 *
 * Utilizar:
 *
 * SwingUtilities.invokeLater(...)
 *
 * para iniciar a interface Swing.
 *
 *
 * ETAPA 1:
 *
 * Criar os Filmes iniciais.
 *
 * Exemplo:
 *
 * Interestelar
 * Batman
 * Avatar
 *
 *
 * ETAPA 2:
 *
 * Criar Filme[] e colocar os objetos no vetor.
 *
 *
 * ETAPA 3:
 *
 * Criar as Salas utilizando TipoSala.
 *
 *
 * ETAPA 4:
 *
 * Criar as Sessoes.
 *
 * Cada Sessao deve receber o Filme correspondente.
 *
 *
 * ETAPA 5:
 *
 * Adicionar as Sessoes nas Salas utilizando:
 *
 * sala.adicionarSessao(...)
 *
 *
 * ETAPA 6:
 *
 * Criar Sala[] e adicionar as salas.
 *
 *
 * ETAPA 7:
 *
 * Criar:
 *
 * MenuView
 *
 *
 * ETAPA 8:
 *
 * Criar:
 *
 * MenuController
 *
 * passando:
 *
 * MenuView
 * Filme[]
 * Sala[]
 *
 *
 * ETAPA 9:
 *
 * Tornar MenuView visivel.
 *
 *
 * O fluxo inicial sera:
 *
 * Main
 *   |
 *   +---- cria Filme[]
 *   |
 *   +---- cria Sala[]
 *   |
 *   +---- cria MenuView
 *   |
 *   +---- cria MenuController
 *   |
 *   v
 * MenuView.setVisible(true)
 *
 *
 * IMPORTANTE:
 *
 * Main nao deve configurar os ActionListeners dos botoes.
 *
 * Main nao deve implementar regras de compra.
 *
 * Main nao deve controlar a navegacao.
 *
 * Isso agora pertence aos Controllers.
 *
 * ============================================================
 */