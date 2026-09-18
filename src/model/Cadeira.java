package model;

public class Cadeira {
    private int linha;
    private int coluna;
    private boolean ocupada;


    public Cadeira(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        this.ocupada = false;
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


	public boolean isOcupada() {
		return ocupada;
	}


	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}


    
}
