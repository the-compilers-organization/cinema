package model;
import enums.TipoSala;

public class Sala {
    private static final int TAM = 10;

    private int numero;
    private Sessao[] sessoes;
    private int qtdSessoes;
    private TipoSala tipoSala;

    public Sala(int numero, TipoSala tipoSala){
        this.numero = numero;
        this.sessoes = new Sessao[TAM];
        this.tipoSala = tipoSala;
        this.qtdSessoes = 0;
    }

    public boolean adicionarSessao(Sessao sessao){
        
        if(sessao != null && qtdSessoes < TAM){
            sessoes[qtdSessoes] = sessao;
            qtdSessoes += 1;
            
            return true;
        }
        return false;
    }

    public int buscarSessao(String horario){
        int i;

        if(horario != null){
            for(i = 0; i < sessoes.length; i += 1){
                if(sessoes[i] != null){
                    if(sessoes[i].getHorario().equals(horario)){
                        return i;
                    }
                }    
            }
        }    

        return -1;
    }

    public void listarSessoes(){
        int i;

        // System.out.print("=".repeat(41));
        // System.out.print(" SESSOES ");
        // System.out.print("=".repeat(41));
        // System.out.println();
        
        System.out.printf("%-10d | %-10s | %-20s | %-45s | %-15s\n","Sala", "Tipo de Sala","Horario", "Filme", "Valor R$");
        System.out.println("-".repeat(100));

        for(i = 0; i < sessoes.length; i += 1){
            if(sessoes[i] != null){
                System.out.printf("%-10d | %-10s | %-20s | %-45s | %-15s\n", numero, tipoSala, sessoes[i].getHorario(), sessoes[i].getFilme().getNome(), sessoes[i].getFilme().getValor());
            }

        }
        // System.out.printf("=".repeat(91));
        // System.out.println();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Sessao[] getSessoes() {
        return sessoes;
    }

    public void setSessoes(Sessao[] sessoes) {
        this.sessoes = sessoes;
    }

    public int getQtdSessoes() {
        return qtdSessoes;
    }

    public void setQtdSessoes(int qtdSessoes) {
        this.qtdSessoes = qtdSessoes;
    }  
    
    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }
}
