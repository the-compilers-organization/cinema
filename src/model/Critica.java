package model;

public class Critica{

    private String origem;
    private String nome; //nome do critico
    private String mensagem;

    public Critica(String origem, String nome, String mensagem) {
        this.origem = origem;
        this.nome = nome;
        this.mensagem = mensagem;
    }
    

    //GETS E SETS
    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}   