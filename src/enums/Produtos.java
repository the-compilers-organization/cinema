// talvez use em na classe compras para setar itens como pipoca, chocolate e refrigerante
package enums;

public enum Produtos {
    PIPOCA(12.00),
    REFRIGERANTE(7.00),
    CHOCOLATE(5.00),
    AGUA(5.00),
    BALA(4.00);

    private double valor;

    Produtos(double valor){
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

}


