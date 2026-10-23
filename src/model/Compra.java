package model;
import enums.Produtos;
import enums.CupomPromocional;
import java.util.ArrayList;

public class Compra {

    private Usuario usuario;
    private ArrayList<Bilhete> bilhetes;
    private ArrayList<Produtos> produtos;
    private ArrayList<Integer> qtdCadaProduto;
    private ArrayList<Double> subtotaisProdutos;


    public Compra(Usuario usuario){
        this.usuario = usuario;
        this.bilhetes = new ArrayList<Bilhete>();
        this.produtos = new ArrayList<Produtos>();
        this.qtdCadaProduto = new ArrayList<Integer>();
        this.subtotaisProdutos = new ArrayList<Double>();
    }


    // bilhetes:
    // adiciona bilhete
    public boolean comprarBilhete(Bilhete bilhete){
        if(bilhete != null){
            bilhetes.add(bilhete);
            return true;
        }
        return false;
    }

    // sobrecarga do metodo comprarBilhete
    public boolean comprarBilhete(Bilhete bilhete, CupomPromocional cupom){
        if(bilhete != null && cupom != null){
            
            double valorDesconto = bilhete.getValor() * cupom.getPercentualDesconto();
            bilhete.setValor(bilhete.getValor() - valorDesconto);
            bilhetes.add(bilhete);

            return true;
            
        }
        return false;
    }


    // busca um bilhete
    public int buscarBilhete(int num){
       
        int indice = num - 1;

        if(indice >= 0 && indice < bilhetes.size()){
            return indice;
        }
        

       return -1;
    }

    // remove bilhete e libera a cadeira
    public boolean removerBilhete(int numero){
        
        int indice = buscarBilhete(numero);

        if(indice == -1){
            return false;
        }

        
        Bilhete bilhete = bilhetes.get(indice);
        bilhete.getSessao().liberarCadeira(bilhete.getCadeira().getLinha(), bilhete.getCadeira().getColuna());
    
        bilhetes.remove(indice);

        return true;
           
    }

    // produtos:
    public int buscarProduto(Produtos produto){
        if(produto != null){
            int i;

            for(i = 0; i < produtos.size(); i += 1){
                if(produtos.get(i) == produto){
                    return i;
                }
            }
            
        }

        return -1;
    }



    // Adiciona ou soma a quantidade de um produto sem cupom.
    public boolean comprarProduto(Produtos produto, int quantidade) {
        if (produto == null || quantidade <= 0){
            return false;
        }

        int indice = buscarProduto(produto);

        if (indice == -1) {
            produtos.add(produto);
            qtdCadaProduto.add(quantidade);
            subtotaisProdutos.add(produto.getValor() * quantidade);
            
        }else{
            int novaQtd = qtdCadaProduto.get(indice) + quantidade;
            qtdCadaProduto.set(indice, novaQtd);

            subtotaisProdutos.set(indice, produto.getValor() * novaQtd);

        }
        
        return true;
    }

    // sobrecarga do metodo adiciona comprarProduto
    public boolean comprarProduto(Produtos produto, int quantidade, CupomPromocional cupom){
        if(produto == null || quantidade <= 0 || cupom == null){
            return false;
        }

        int indice = buscarProduto(produto);

        if(indice == -1){
            produtos.add(produto);
            qtdCadaProduto.add(quantidade);
            subtotaisProdutos.add(produto.getValor() *
                (1 - cupom.getPercentualDesconto()) * quantidade);
            
        }else{
            int novaQtd = qtdCadaProduto.get(indice) + quantidade;
            qtdCadaProduto.set(indice, novaQtd);
            
            subtotaisProdutos.set(indice, produto.getValor() *
                (1 - cupom.getPercentualDesconto()) * novaQtd);
        }
        return true;
    }



    // alterar quantidade de produto
    public boolean alterarQuantidadeProduto(Produtos produto, int novaQuantidade) {

        if (produto == null || novaQuantidade <= 0) {
            return false;
        }

        int indice = buscarProduto(produto);

        if (indice == -1) {
            return false;
        }

        qtdCadaProduto.set(indice, novaQuantidade);
        double subtotal = produto.getValor() * novaQuantidade;
        subtotaisProdutos.set(indice, subtotal);

        return true;
    }

    

    // sobrecarga do metodo alterarQuantidadeProduto
    public boolean alterarQuantidadeProduto(Produtos produto, int novaQuantidade, CupomPromocional cupom) {

        if (produto == null || novaQuantidade <= 0 || cupom == null) {
            return false;
        }

        int indice = buscarProduto(produto);

        if (indice == -1) {
            return false;
        }

        qtdCadaProduto.set(indice, novaQuantidade);

        double valorDesconto = produto.getValor() * cupom.getPercentualDesconto();
        double subtotal = (produto.getValor() - valorDesconto) * novaQuantidade;
        subtotaisProdutos.set(indice, subtotal);

        return true;
    }



    // remover produto
    public boolean removerProduto(Produtos produto) {

        int indice = buscarProduto(produto);

        if (indice == -1) {
            return false;
        }

        produtos.remove(indice);
        qtdCadaProduto.remove(indice);
        subtotaisProdutos.remove(indice);

        return true;
    }


    // totais:
    // total dos bilhetes
    public double calcularTotalBilhetes() {

        double valorTotal = 0.00;

        for (int i = 0; i < bilhetes.size(); i++) {

            valorTotal += bilhetes.get(i).getValor();
        }

        return valorTotal;
    }

    
    // total dos produtos
    public double calcularTotalProdutos() {

        double valorTotal = 0.00;

        for (int i = 0; i < subtotaisProdutos.size(); i++) {

            valorTotal += subtotaisProdutos.get(i);
        }

        return valorTotal;
    }

    // calcular total (bilhetes + produtos)
    public double calcularTotal(){

        return calcularTotalBilhetes() + calcularTotalProdutos();
    } 


    // compra:
    // realizar compra
    public boolean realizarCompra(){
        if(usuario != null && !bilhetes.isEmpty()){
            return true;
        }
        return false;
    }

    // alterar compra
    public boolean alterarCompra(){
        if(usuario != null && !bilhetes.isEmpty()){
            return true;
        }
        return false;
    } 

    

    // libera todas as cadeiras
    public void liberarCadeiras() {

        for (int i = 0; i < bilhetes.size(); i++) {

            Bilhete bilhete = bilhetes.get(i);

            int linha = bilhete.getCadeira().getLinha();
            int coluna = bilhete.getCadeira().getColuna();

            bilhete.getSessao().liberarCadeira(
                    linha, coluna
            );
        }
    }


    // cancelar compra
    public boolean cancelarCompra() {

        if (!bilhetes.isEmpty()) {

            liberarCadeiras();

            bilhetes.clear();

            produtos.clear();
            qtdCadaProduto.clear();
            subtotaisProdutos.clear();

            return true;
        }

        return false;
    } 

    // Limpa itens de um rascunho, sem alterar as cadeiras da sessao.
    public void limparRascunho() {

        bilhetes.clear();

        produtos.clear();
        qtdCadaProduto.clear();
        subtotaisProdutos.clear();
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public ArrayList<Bilhete> getBilhetes() {
        return bilhetes;
    }


    public void setBilhetes(ArrayList<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }


    public ArrayList<Produtos> getProdutos() {
        return produtos;
    }


    public void setProdutos(ArrayList<Produtos> produtos) {
        this.produtos = produtos;
    }


    public ArrayList<Integer> getQtdCadaProduto() {
        return qtdCadaProduto;
    }


    public void setQtdCadaProduto(ArrayList<Integer> qtdCadaProduto) {
        this.qtdCadaProduto = qtdCadaProduto;
    }


    public ArrayList<Double> getSubtotaisProdutos() {
        return subtotaisProdutos;
    }


    public void setSubtotaisProdutos(ArrayList<Double> subtotaisProdutos) {
        this.subtotaisProdutos = subtotaisProdutos;
    }


    

    // BILHETE:
    // private String user;
    // private String cpf;
    // private Sala sala;
    // private Sessao sessao;
    // private Filme filme;
    // private double valor;
    // private Cadeira cadeira;

    // COMPRA:
    // private Usuario usuario;
    // private Bilhete[] bilhetes;
    // private int qtdBilhetes;
    // private Produtos[] produtos;
    // private int[] qtdCadaProduto;
    // private double[] subtotaisProdutos;
    // private int qtdProdutos;

    // Ficara no view public void exibirCompra()
    // public void exibirCompra(){

    // }


    

    

}