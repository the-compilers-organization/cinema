package controller.exceptions;

public class VendasException extends Exception{

    public enum Tipo {
        sessao_ja_iniciada, cadeira_ocupada, filme_fora_de_cartaz
    }

    private final Tipo tipo;

    public Tipo getTipo() {
        return tipo;
    }

    public VendasException(Tipo tipo, String mensagem){
        super(mensagem);
        this.tipo = tipo;
    }

    //================================================================================

    public static VendasException sessaoJaIniciada(String filme, String horario) {
        return new VendasException(Tipo.sessao_ja_iniciada,"A sessao de \"" + filme + "\" as " + horario + " ja comecou.");
    }

    public static VendasException poltronaOcupada(int linha, int coluna) {
        return new VendasException(Tipo.cadeira_ocupada, "A cadeira da linha " + linha + ", coluna " + coluna + " ja foi selecionada.");
    }

    public static VendasException filmeForaDeCartaz(String filme) {
        return new VendasException(Tipo.filme_fora_de_cartaz, "O filme \"" + filme + "\" nao esta mais em cartaz.");
    }
    
    // Compra de um bilhete que já tenha passado a hora do filme;
    // ○ Compra de um bilhete associado a uma poltrona que já foi selecionada;
    // ○ Compra de um bilhete de um filme que não esteja mais em cartaz

}