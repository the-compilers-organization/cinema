package model;
public class Filme {
    private static final int TAM_CRITICAS = 100;
    
    private String nome;
    private int duracao;
    private String sinopse;
    private double valor;
    private double[] notas;
    private double nota;
    private int qtdCriticas;
    private int qtdComentarios;
    private Critica[] criticas;

    // CONSTRUTOR
    public Filme(String nome, int duracao, String sinopse, double valor) {

        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;

        this.criticas = new Critica[TAM_CRITICAS];
        this.notas = new double[TAM_CRITICAS];

        this.qtdCriticas = 0;
        this.qtdComentarios = 0;

        this.nota = mediaDasNotas();
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

    public int getQtdCriticas() {
        return qtdCriticas;
    }

    public void setQtdCriticas(int qtdCriticas) {
        this.qtdCriticas = qtdCriticas;
    }

    public double getNota() {
        return nota;
    }


    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getQtdComentarios() {
        return qtdComentarios;
    }

    public void setQtdComentarios(int qtdComentarios) {
        if(qtdComentarios >= 0 && qtdComentarios <= TAM_CRITICAS) {
            this.qtdComentarios = qtdComentarios;
        }
    }

    public boolean adicionarNota(double nota) {

        if(nota >= 0 && nota <= 10 && qtdCriticas < TAM_CRITICAS) {
            notas[qtdCriticas] = nota;
            qtdCriticas += 1;
            this.nota = mediaDasNotas();
            return true;
        }

        return false;
    }

    public boolean adicionarCritica(Critica critica) {

        if(critica != null && qtdComentarios < TAM_CRITICAS) {
            criticas[qtdComentarios] = critica;
            qtdComentarios += 1;
            return true;
        }

        return false;
    }

    public double mediaDasNotas() {
        if(qtdCriticas > 0){
            int i;
            double soma = 0.00;
            for(i = 0; i < qtdCriticas; i += 1){
                soma += notas[i];
            }

            return soma / qtdCriticas;

        }
        
        return 0.00;
    }
  
}
