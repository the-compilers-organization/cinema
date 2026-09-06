// talvez use em na classe compras para setar itens como pipoca, chocolate e refrigerante
public enum Produtos {
    PIPOCA("Pipoca", 12.00),
    REFRIGERANTE("Refrigerante", 7.00),
    CHOCOLATE("Chocolate", 5.00),
    AGUA("Agua", 5.00),
    BALA("Bala", 4.00);

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


