package model;


// Parte 2 - Crítica feita por um crítico: nome, origem e mensagem

public class Critica {

    private String nome;     // nome do crítico
    private String origem;   // veículo ao qual o crítico está ligado
    private String mensagem; // texto do comentário

    public Critica(String nome, String origem, String mensagem) {
        this.nome = nome;
        this.origem = origem;
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
