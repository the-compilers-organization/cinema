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



/*
 * ORIENTACOES PARA IMPLEMENTACAO
 *
 * A Main sera responsavel apenas por iniciar a aplicacao.
 *
 * Implementar:
 * - Importar a TelaPrincipal;
 * - Inicializar a interface grafica utilizando Swing;
 * - Criar uma TelaPrincipal;
 * - Tornar a TelaPrincipal visivel.
 *
 * Nao implementar regras de compra, cadastro ou manipulacao
 * de cadeiras na Main. Essas responsabilidades devem ficar
 * nas respectivas classes e telas.
 */



// teste exemplo
import view.TelaPrincipal;

public class Main {

    public static void main(String[] args){

        TelaPrincipal tela =
                new TelaPrincipal();

        tela.setVisible(true);
    }
}