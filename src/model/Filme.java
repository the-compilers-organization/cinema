package model;
public class Filme {
    private static final int TAM = 100;
    private double[] notas;
    private String nome;
    private int duracao;
    private String sinopse;
    private double valor;
    private int quantidadeCriticas;
    private Critica[] criticas;

    // CONSTRUTOR
    public Filme(String nome, int duracao,String sinopse, double valor){
        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;
        this.criticas = new Critica[TAM]; 
        this.notas = new double[TAM];
        this.quantidadeCriticas = 0;
    }

    //criar metodo para calcularMedia das notas do vetor critica!!!

    // GETS E SETS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public String getSinopse() {
        return sinopse;
    }
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public Critica[] getCriticas() {
        return criticas;
    }

    public void setCriticas(Critica[] criticas) {
        this.criticas = criticas;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    public int getQuantidadeCriticas() {
        return quantidadeCriticas;
    }

    public void setQuantidadeCriticas(int quantidadeCriticas) {
        this.quantidadeCriticas = quantidadeCriticas;
    }


    
}
