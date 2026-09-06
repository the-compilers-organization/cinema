// talvez use em na classe compras para setar itens como pipoca, chocolate e refrigerante
public enum Produtos {
    PIPOCA("pipoca", 12.00),
    REFRIGERANTE("refrigerante", 7.00),
    CHOCOLATE("chocolate", 5.00),
    AGUA("agua", 5.00),
    BALA("bala", 4.00);

    private String nome;
    private double valor;

    Produtos(String nome, double valor){
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

}


