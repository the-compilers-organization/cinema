package model;

import model.enums.Produtos;

// Um produto da lanchonete dentro de uma compra (produto + quantidade + subtotal).

public class ItemCompra {

    private Produtos produto;
    private int quantidade;
    private double subtotal;

    public ItemCompra(Produtos produto, int quantidade, double subtotal) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
