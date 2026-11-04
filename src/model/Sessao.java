package model;

import java.time.LocalTime;
import java.util.ArrayList;


// Parte 1 - Sessão: filme exibido + matriz de cadeiras (10 linhas x 15 colunas)
 
public class Sessao {

    public static final int LINHAS = 10;
    public static final int COLUNAS = 15;

    private String horario; // formato HH:mm
    private Filme filme;
    private Cadeira[][] cadeiras;

    public Sessao(String horario, Filme filme) {
        this.horario = horario;
        this.filme = filme;
        this.cadeiras = new Cadeira[LINHAS][COLUNAS];

        for (int l = 0; l < LINHAS; l++) {
            for (int c = 0; c < COLUNAS; c++) {
                cadeiras[l][c] = new Cadeira(l, c);
            }
        }
    }

    public boolean cadeiraValida(int linha, int coluna) {
        return linha >= 0 && linha < LINHAS && coluna >= 0 && coluna < COLUNAS;
    }

    public boolean cadeiraDisponivel(int linha, int coluna) {
        return cadeiraValida(linha, coluna) && !cadeiras[linha][coluna].isOcupada();
    }

    public void ocuparCadeira(int linha, int coluna) {
        if (cadeiraValida(linha, coluna)) {
            cadeiras[linha][coluna].setOcupada(true);
        }
    }

    public void liberarCadeira(int linha, int coluna) {
        if (cadeiraValida(linha, coluna)) {
            cadeiras[linha][coluna].setOcupada(false);
        }
    }

    // Parte 3: verifica se o horário da sessão já passou
    public boolean jaComecou() {
        LocalTime inicio = LocalTime.parse(horario);
        return LocalTime.now().isAfter(inicio);
    }

    
    // Procura grupos de "quantidade" cadeiras livres lado a lado na mesma fileira
    // Cada item da lista é um vetor {linha, colunaInicial}
    // Usado para sugerir lugares juntos quando o usuário compra vários bilhetes
     
    public ArrayList<int[]> buscarCadeirasJuntas(int quantidade) {
        ArrayList<int[]> opcoes = new ArrayList<int[]>();

        if (quantidade < 1 || quantidade > COLUNAS) {
            return opcoes;
        }

        // Começa pelas fileiras do meio da sala (melhor visão da tela)
        int[] ordemFileiras = {4, 5, 3, 6, 2, 7, 1, 8, 0, 9};

        for (int linha : ordemFileiras) {
            for (int inicio = 0; inicio <= COLUNAS - quantidade; inicio++) {
                boolean todasLivres = true;
                for (int c = inicio; c < inicio + quantidade; c++) {
                    if (!cadeiraDisponivel(linha, c)) {
                        todasLivres = false;
                        break;
                    }
                }
                if (todasLivres) {
                    opcoes.add(new int[]{linha, inicio});
                }
            }
        }
        return opcoes;
    }

    // Nome da cadeira para o usuário
    public static String nomeDaCadeira(int linha, int coluna) {
        char letra = (char) ('A' + linha);
        return "" + letra + (coluna + 1);
    }

    @Override
    public String toString() {
        return horario + "  -  " + filme.getNome();
    }

    //  GETTERS E SETTERS 

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Cadeira[][] getCadeiras() {
        return cadeiras;
    }

    public void setCadeiras(Cadeira[][] cadeiras) {
        this.cadeiras = cadeiras;
    }
}
