package model;

public class Critica{

    private double nota;
    private String origem;
    private String nome; //nome do critico
    private String mensagem;

    public Critica(double nota, String origem, String nome, String mensagem) {
        this.origem = origem;
        this.nome = nome;
        this.mensagem = mensagem;
        this.nota = nota;
    }
    
    public boolean atribuirNota(double nota, Filme filme, Critico critico){
        if(nota >= 0 && nota <= 10 && filme != null && critico != null){
            int indice = filme.getQuantidadeCriticas();
            double[] notasFilme = filme.getNotas();
            notasFilme[indice] = nota;

            Critica critica = filme.getCriticas()[indice];
            
        }
        return false;
    }

    public void atribuirCritica(String critica, Filme filme){}

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

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
}   