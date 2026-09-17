public class Compra {
    private static final int TAM_BILHETES = 100;
    private static final int TAM_PRODUTOS = 150;

    private Usuario usuario;
    private Bilhete[] bilhetes;
    private int qtdBilhetes;
    private Produtos[] produtos;
    private int[] qtdCadaProduto;
    private int qtdProdutos;
    
    
    public Compra(Usuario usuario){
        this.usuario = usuario;
        this.bilhetes = new Bilhete[TAM_BILHETES];
        this.qtdBilhetes = 0;
        this.produtos = new Produtos[TAM_PRODUTOS];
        this.qtdCadaProduto = new int[TAM_PRODUTOS];
        this.qtdProdutos = 0;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Bilhete[] getBilhetes() {
        return bilhetes;
    }

    public void setBilhetes(Bilhete[] bilhetes) {
        this.bilhetes = bilhetes;
    }

    public int getQtdBilhetes() {
        return qtdBilhetes;
    }

    public void setQtdBilhetes(int qtdBilhetes) {
        this.qtdBilhetes = qtdBilhetes;
    }

    public Produtos[] getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos[] produtos) {
        this.produtos = produtos;
    }

    public int[] getQtdCadaProduto() {
        return qtdCadaProduto;
    }

    public void setQtdCadaProduto(int[] qtdCadaProduto) {
        this.qtdCadaProduto = qtdCadaProduto;
    }

    public int getQtdProdutos() {
        return qtdProdutos;
    }

    public void setQtdProdutos(int qtdProdutos) {
        this.qtdProdutos = qtdProdutos;
    }

    

    // bilhetes:
    // adiciona bilhete
    // + comprarBilhete(bilhete:Bilhete) : boolean 

    // busca um bilhete
    // + buscarBilhete(numero:int) : int 

    // remove bilhete e libera a cadeira
    // + removerBilhete(numero:int) : boolean 

    // produtos:
    // + buscarProduto(produto: Produtos) : int 

    // adiciona produto
    // + comprarProduto(produto:Produtos, quantidade:int) : boolean 

    // alterar quantidade de produto
    // + alterarQuantidadeProduto(produto:Produtos, qtdProdutos :int) : boolean 

    // remover produto
    // + removerProduto(produto:Produtos) : boolean 

    // totais:
    // total dos bilhetes
    // + calcularTotalBilhetes() : double 
    
    // total dos produtos
    // + calcularTotalProdutos() : double 

    // calcular total (bilhetes + produtos)
    // + calcularTotal() : double 

    // compra:
    // realizar compra
    // + realizarCompra() : boolean 

    // alterar compra
    // + alterarCompra() : boolean 

    // libera todas as cadeiras
    // + liberarCadeiras() : void 

    // cancelar compra
    // + cancelarCompra() : boolean 

    // exibir:
    // exibir compra
    // + exibirCompra() : void 

}