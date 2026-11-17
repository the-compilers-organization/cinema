package model;

import exceptions.ValidacaoException;


// Parte 1 - Filme: nome, duração, sinopse e valor
// Parte 2 - nota (média), quantidadeCriticos e vetor de críticas (tamanho 100)
// Parte 3 - emCartaz (usado pela VendasException)

public class Filme {

    public static final int MAX_CRITICAS = 100;

    private String nome;
    private int duracao;        // em minutos
    private String sinopse;
    private double valor;
    private boolean emCartaz;

    private double nota;                 // média das notas dos críticos
    private int quantidadeCriticos;      // quantos críticos deram nota
    private Critica[] criticas;          // vetor de comentários
    private int quantidadeComentarios;   // posições ocupadas do vetor

    public Filme(String nome, int duracao, String sinopse, double valor) throws ValidacaoException {
        setNome(nome);
        setDuracao(duracao);
        setSinopse(sinopse);
        setValor(valor);
        this.emCartaz = true;
        this.nota = 0.0;
        this.quantidadeCriticos = 0;
        this.criticas = new Critica[MAX_CRITICAS];
        this.quantidadeComentarios = 0;
    }

    
    // Adiciona uma nota (0 a 10), incrementa a quantidade de críticos e calcula a nova média
     
    public void adicionarNota(double novaNota) throws ValidacaoException {
        if (novaNota < 0 || novaNota > 10) {
            throw new ValidacaoException("A nota deve estar entre 0 e 10.");
        }
        double soma = nota * quantidadeCriticos;  // soma das notas anteriores
        quantidadeCriticos++;
        nota = (soma + novaNota) / quantidadeCriticos;
    }

    // Coloca a crítica no final do vetor
    public void adicionarCritica(Critica critica) throws ValidacaoException {
        if (critica == null) {
            throw new ValidacaoException("Crítica inválida.");
        }
        if (quantidadeComentarios >= MAX_CRITICAS) {
            throw new ValidacaoException("O filme já atingiu o limite de " + MAX_CRITICAS + " críticas.");
        }
        criticas[quantidadeComentarios] = critica;
        quantidadeComentarios++;
    }

    // Retorna apenas as posições preenchidas do vetor (sem os null)
    public Critica[] getCriticasPreenchidas() {
        Critica[] preenchidas = new Critica[quantidadeComentarios];
        for (int i = 0; i < quantidadeComentarios; i++) {
            preenchidas[i] = criticas[i];
        }
        return preenchidas;
    }

    // Copia os dados editáveis de outro filme (o nome não muda)
    public void copiarDados(Filme outro) {
        this.duracao = outro.duracao;
        this.sinopse = outro.sinopse;
        this.valor = outro.valor;
        this.emCartaz = outro.emCartaz;
    }

    @Override
    public String toString() {
        return nome;
    }

    //  GETTERS E SETTERS 
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ValidacaoException {
        Validador.textoObrigatorio(nome, "Nome do filme");
        this.nome = nome.trim();
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) throws ValidacaoException {
        if (duracao <= 0 || duracao > 600) {
            throw new ValidacaoException("A duração deve ser entre 1 e 600 minutos.");
        }
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) throws ValidacaoException {
        Validador.textoObrigatorio(sinopse, "Sinopse");
        this.sinopse = sinopse.trim();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) throws ValidacaoException {
        if (valor <= 0) {
            throw new ValidacaoException("O valor do filme deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public boolean isEmCartaz() {
        return emCartaz;
    }

    public void setEmCartaz(boolean emCartaz) {
        this.emCartaz = emCartaz;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getQuantidadeCriticos() {
        return quantidadeCriticos;
    }

    public void setQuantidadeCriticos(int quantidadeCriticos) {
        this.quantidadeCriticos = quantidadeCriticos;
    }

    public Critica[] getCriticas() {
        return criticas;
    }

    public void setCriticas(Critica[] criticas) {
        this.criticas = criticas;
    }

    public int getQuantidadeComentarios() {
        return quantidadeComentarios;
    }
}
