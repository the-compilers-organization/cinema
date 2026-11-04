package model;

import java.util.ArrayList;

import exceptions.CompraException;
import exceptions.ValidacaoException;
import exceptions.VendasException;
import model.enums.CupomPromocional;
import model.enums.Produtos;


// Parte 1 - Compra: um ou mais bilhetes + produtos da lanchonete
// Parte 2 - O método de compra de produtos é SOBRECARREGADO com CupomPromocional

public class Compra {

    private int id;
    private String cpfUsuario;
    private String nomeUsuario;
    private String dataHora;
    private CupomPromocional cupom; // pode ser null (sem cupom)
    private ArrayList<Bilhete> bilhetes;
    private ArrayList<ItemCompra> produtos;

    public Compra(Usuario usuario) {
        this.cpfUsuario = usuario.getCpf();
        this.nomeUsuario = usuario.getUser();
        this.bilhetes = new ArrayList<Bilhete>();
        this.produtos = new ArrayList<ItemCompra>();
    }

    //  BILHETES 
    // Adiciona um bilhete. Não deixa a mesma poltrona entrar duas vezes na compra.
    public void adicionarBilhete(Bilhete bilhete) throws VendasException {
        if (contemCadeira(bilhete.getNumeroSala(), bilhete.getHorarioSessao(),
                bilhete.getLinha(), bilhete.getColuna())) {
            throw new VendasException(VendasException.POLTRONA_OCUPADA, bilhete.getFilme(),
                    "A poltrona " + bilhete.getNomeCadeira() + " já foi selecionada nesta compra.");
        }
        bilhetes.add(bilhete);
    }

    public boolean contemCadeira(int numeroSala, String horario, int linha, int coluna) {
        for (Bilhete b : bilhetes) {
            if (b.mesmaCadeira(numeroSala, horario, linha, coluna)) {
                return true;
            }
        }
        return false;
    }

    public Bilhete removerBilhete(int indice) throws CompraException {
        if (indice < 0 || indice >= bilhetes.size()) {
            throw new CompraException("Ingresso não encontrado na compra.");
        }
        return bilhetes.remove(indice);
    }

    //  PRODUTOS 
    // Compra de produto SEM cupom
    public void comprarProduto(Produtos produto, int quantidade) throws ValidacaoException {
        comprarProduto(produto, quantidade, null);
    }

    // SOBRECARGA: compra de produto COM cupom promocional
    public void comprarProduto(Produtos produto, int quantidade, CupomPromocional cupom)
            throws ValidacaoException {
        if (produto == null) {
            throw new ValidacaoException("Selecione um produto.");
        }
        if (quantidade <= 0) {
            throw new ValidacaoException("A quantidade deve ser maior que zero.");
        }

        double valor = produto.getValor() * quantidade;
        if (cupom != null) {
            valor = cupom.aplicarDesconto(valor);
        }

        // Se o produto já está na compra, soma a quantidade
        for (ItemCompra item : produtos) {
            if (item.getProduto() == produto) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                item.setSubtotal(item.getSubtotal() + valor);
                return;
            }
        }
        produtos.add(new ItemCompra(produto, quantidade, valor));
    }

    public ItemCompra removerProduto(int indice) throws CompraException {
        if (indice < 0 || indice >= produtos.size()) {
            throw new CompraException("Produto não encontrado na compra.");
        }
        return produtos.remove(indice);
    }

    //  TOTAIS 
    public double calcularTotalBilhetes() {
        double total = 0;
        for (Bilhete b : bilhetes) {
            total += b.getValor();
        }
        return total;
    }

    public double calcularTotalProdutos() {
        double total = 0;
        for (ItemCompra item : produtos) {
            total += item.getSubtotal();
        }
        return total;
    }

    public double calcularTotal() {
        return calcularTotalBilhetes() + calcularTotalProdutos();
    }

    public boolean estaVazia() {
        return bilhetes.isEmpty() && produtos.isEmpty();
    }

    //  GETTERS E SETTERS 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public CupomPromocional getCupom() {
        return cupom;
    }

    public void setCupom(CupomPromocional cupom) {
        this.cupom = cupom;
    }

    public ArrayList<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public void setBilhetes(ArrayList<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }

    public ArrayList<ItemCompra> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<ItemCompra> produtos) {
        this.produtos = produtos;
    }
}
