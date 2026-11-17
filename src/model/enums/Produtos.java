package model.enums;


// Parte 2 - Produtos de balcão do cinema
// Cada produto tem um nome (String) e um valor (double)
// O estoque é considerado infinito

public enum Produtos {

    PIPOCA("Pipoca grande", 18.00),
    REFRIGERANTE("Refrigerante 500ml", 9.00),
    CHOCOLATE("Chocolate", 7.50),
    AGUA("Água mineral", 5.00),
    NACHOS("Nachos com cheddar", 16.00),
    BALA("Bala de goma", 4.00);

    private String nome;
    private double valor;

    Produtos(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    // Texto exibido nas caixas de seleção da tela
    @Override
    public String toString() {
        return nome + "  -  R$ " + String.format("%.2f", valor);
    }
}
