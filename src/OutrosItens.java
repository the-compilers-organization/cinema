// talvez use em na classe compras para setar itens como pipoca, chocolate e refrigerante
public enum OutrosItens {
    PIPOCA("pipoca", 12.00),
    REFRIGERANTE("refrigerante", 7.00),
    CHOCOLATE("chocolate", 5.00);

    private String nome;
    private double valor;

    OutrosItens(String nome, double valor){
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


