package exceptions;

// Exceção exigida na Parte 3 do projeto
// Trata os 3 erros possíveis na compra de um bilhete:
// 1) compra de bilhete de uma sessão cujo horário já passou
// 2) compra de bilhete de uma poltrona que já foi selecionada
// 3) compra de bilhete de um filme que não está mais em cartaz
// Assim como no exemplo SaldoInsuficienteException da aula, a exceção guarda informações extras sobre o erro (o filme e o motivo)
 
public class VendasException extends Exception {

    public static final String SESSAO_INICIADA = "Sessão já iniciada";
    public static final String POLTRONA_OCUPADA = "Poltrona já selecionada";
    public static final String FORA_DE_CARTAZ = "Filme fora de cartaz";

    private String motivo;
    private String filme;

    public VendasException(String motivo, String filme, String mensagem) {
        super(mensagem);
        this.motivo = motivo;
        this.filme = filme;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getFilme() {
        return filme;
    }
}
