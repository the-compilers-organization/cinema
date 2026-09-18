package model;
import enums.Produtos;

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


    // bilhetes:
    // adiciona bilhete
    public boolean comprarBilhete(Bilhete bilhete){
        if(bilhete != null){
            if(qtdBilhetes < TAM_BILHETES){
                bilhetes[qtdBilhetes] = bilhete;
                qtdBilhetes += 1;
                return true;
            }
        }
        return false;
    }


    // busca um bilhete
    public int buscarBilhete(int num){
        if(qtdBilhetes > 0){
            int indice = num - 1;

            if(indice >= 0 && indice < qtdBilhetes){
                return indice;
            }
        }

       return -1;
    }

    // remove bilhete e libera a cadeira
    public boolean removerBilhete(int numero){
        if(qtdBilhetes > 0){
            int indice = buscarBilhete(numero);

            if(indice != -1){
                Bilhete bilhete = bilhetes[indice];
                bilhete.getSessao().liberarCadeira(bilhete.getCadeira().getLinha(), bilhete.getCadeira().getColuna());
            }

            int i;
            for(i = indice; i < qtdBilhetes - 1; i += 1){
                bilhetes[i] = bilhetes[i + 1];
            }

            bilhetes[qtdBilhetes - 1] = null;
            qtdBilhetes -= 1;
            return true;
        }

        return false;
    }

    // produtos:
    public int buscarProduto(Produtos produto){
        if(produto != null){
            if(qtdProdutos > 0){
                int i;
                for(i = 0; i < qtdProdutos; i += 1){
                    if(produtos[i] == produto){
                        return i;
                    }
                }
            }
        }

        return -1;
    }


    

    // adiciona produto
    public boolean comprarProduto(Produtos produto, int quantidade){
        if(produto != null && quantidade > 0 && qtdProdutos < TAM_PRODUTOS){
            int indice = buscarProduto(produto);

            if(indice != -1){
                qtdCadaProduto[indice] += quantidade;
                return true;
            }else{
                produtos[qtdProdutos] = produto;
                qtdCadaProduto[qtdProdutos] = quantidade;
                qtdProdutos += 1;
                return true;

            }
        }

        return false;
    } 


    // alterar quantidade de produto
    public boolean alterarQuantidadeProduto(Produtos produto, int novaQuantidade){
        if(produto != null && novaQuantidade > 0){
            int indice = buscarProduto(produto);

            if(indice != -1){
                qtdCadaProduto[indice] = novaQuantidade;
                return true;
            }
        }

        return false;
    }


    // remover produto
    public boolean removerProduto(Produtos produto){
        if(produtos != null && qtdProdutos > 0){
            int indice = buscarProduto(produto);

            if(indice != -1){
                int i;
                for(i = indice; i < qtdProdutos - 1; i += 1){
                    produtos[i] = produtos[i + 1];
                    qtdCadaProduto[i] = qtdCadaProduto[i + 1];
                }

                produtos[qtdProdutos - 1] = null;
                qtdCadaProduto[qtdProdutos - 1] = 0;
                qtdProdutos -= 1;
                return true;
            }
        }
        return false;
    } 

    // totais:
    // total dos bilhetes
    public double calcularTotalBilhetes(){
        if(qtdBilhetes > 0){
            double valorTotal = 0.00;
            int i = 0;

            for(i = 0; i < qtdBilhetes; i += 1){
                valorTotal += bilhetes[i].getValor();
            }
            return valorTotal;
        }

        return 0.00;
    } 

    
    // total dos produtos
    public double calcularTotalProdutos(){
        if(qtdProdutos > 0){
            double valorTotal = 0.00;
            int i;

            for(i = 0; i < qtdProdutos; i += 1){
                valorTotal += (produtos[i].getValor() * qtdCadaProduto[i]);
            }

            return valorTotal;

        }

        return 0.00;
    }

    // calcular total (bilhetes + produtos)
    public double calcularTotal(){

        return calcularTotalBilhetes() + calcularTotalProdutos();
    } 


    // compra:
    // realizar compra
    public boolean realizarCompra(){
        if(usuario != null && qtdBilhetes > 0){
            return true;
        }
        return false;
    }

    // alterar compra
    public boolean alterarCompra(){
        if(usuario != null && qtdBilhetes > 0){
            return true;
        }
        return false;
    } 

    

    // libera todas as cadeiras
    public void liberarCadeiras(){
        if(qtdBilhetes > 0){
            int i;

            for(i = 0; i < qtdBilhetes; i += 1){
                int linha = bilhetes[i].getCadeira().getLinha();
                int coluna = bilhetes[i].getCadeira().getColuna();
                bilhetes[i].getSessao().liberarCadeira(linha, coluna);
            }

        }
    }

    // private Bilhete[] bilhetes;
    // private int qtdBilhetes;
    // private Produtos[] produtos;
    // private int[] qtdCadaProduto;
    // private int qtdProdutos

    // cancelar compra
    public boolean cancelarCompra(){

        return false;
    } 

    // exibir:
    // exibir compra
    // + exibirCompra() : void 


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

}