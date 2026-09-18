package model;

public class Bilhete {
    private String user;
    private String cpf;
    private Sala sala;
    private Sessao sessao;
    private Filme filme;
    private double valor;
    private Cadeira cadeira;

    // Construtor
    public Bilhete(Usuario usuario, Sala sala, Sessao sessao, Cadeira cadeira){
        this.user = usuario.getUser();
        this.cpf = usuario.getCpf();
        this.sala = sala;
        this.sessao = sessao;
        this.filme = sessao.getFilme();
        this.valor = filme.getValor() * sala.getTipoSala().getMultiplicador();
        this.cadeira = cadeira;
    }

    // Gets e Sets
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cadeira getCadeira() {
        return cadeira;
    }

    public void setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
    }

}