package model;

import model.enums.TipoSala;


// Parte 1 - Bilhete: user, CPF, sala, sessão, filme, valor e cadeira comprada
// Para salvar no JSON de forma simples, o bilhete guarda os DADOS que identificam sala, sessão, filme e cadeira (número da sala,
// horário da sessão, nome do filme, linha e coluna) em vez dos objetos
 
public class Bilhete {

    private String user;
    private String cpf;
    private int numeroSala;
    private TipoSala tipoSala;
    private String horarioSessao;
    private String filme;
    private double valor;
    private int linha;
    private int coluna;

    // O valor é calculado com base no filme e na sala escolhidos
    public Bilhete(Usuario usuario, Sala sala, Sessao sessao, int linha, int coluna) {
        this.user = usuario.getUser();
        this.cpf = usuario.getCpf();
        this.numeroSala = sala.getNumero();
        this.tipoSala = sala.getTipoSala();
        this.horarioSessao = sessao.getHorario();
        this.filme = sessao.getFilme().getNome();
        this.valor = sala.calcularValorIngresso(sessao.getFilme());
        this.linha = linha;
        this.coluna = coluna;
    }

    // Verifica se este bilhete é da mesma cadeira/sessão/sala informada
    public boolean mesmaCadeira(int numeroSala, String horario, int linha, int coluna) {
        return this.numeroSala == numeroSala
                && this.horarioSessao.equals(horario)
                && this.linha == linha
                && this.coluna == coluna;
    }

    public String getNomeCadeira() {
        return Sessao.nomeDaCadeira(linha, coluna);
    }

    //  GETTERS E SETTERS 
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

    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    public String getHorarioSessao() {
        return horarioSessao;
    }

    public void setHorarioSessao(String horarioSessao) {
        this.horarioSessao = horarioSessao;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}
