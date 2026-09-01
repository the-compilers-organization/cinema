public class Main {     //Testando FILME
    
    public static void main(String[] args){
        Filme filme1 = new Filme("\nFrozen", 100, "Gosto de abracos quentihos <3", 20.00);

        System.out.println("Nome: " + filme1.getNome());
        System.out.println("Duracao: " + filme1.getDuracao());
        System.out.println("Sinopse: " + filme1.getSinopse());
        System.out.println("Valor: R$" + filme1.getValor());

        filme1.setValor(25.00);
        System.out.println("Novo valor: "+filme1.getValor());
    }
}
