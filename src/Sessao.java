public class Sessao {

    private static final int TAM_LINHA = 10;
    private static final int TAM_COLUNA = 15;

    private String horario;
    private Filme filme;
    private Cadeira[][] cadeiras;

    public Sessao(String horario, Filme filme){
        this.horario = horario;
        this.filme = filme;
        this.cadeiras = new Cadeira[TAM_LINHA][TAM_COLUNA]; // inicializar td em false. quando false, a cadeira esta livre. quando true, a cadeira esta ocupada

        int linha, coluna;

        for(linha = 0; linha < TAM_LINHA; linha += 1){
            for(coluna = 0; coluna < TAM_COLUNA; coluna += 1){
                cadeiras[linha][coluna] = new Cadeira(linha, coluna);

            }

        }
    }

    // false = cadeira disponível
    // true  = cadeira ocupada

    // se é valido
    public boolean cadeiraValida(int linha, int coluna){

        if((linha >= 0 && linha < TAM_LINHA) && (coluna >= 0 && coluna < TAM_COLUNA)){
            return true;

        }
       
        return false;
    }

    // na matriz cadeiras ==> false = cadeira disponível | true  = cadeira ocupada
    // entao, se matriz retorna false, cadeira disponivel (cadeiraDisponivel retorna true) 
    //  se matriz retorna true, cadeira ocupada (cadeiraDisponivel retorna false) 
    public boolean cadeiraDisponivel(int linha, int coluna){

        if(cadeiraValida(linha, coluna)){
            if(!cadeiras[linha][coluna].isOcupada()){
                return true;
            }

        }
        
        return false;
    }


    // se false, não ocupou cadeira. se true, ocupou a cadeira.
    public boolean ocuparCadeira(int linha, int coluna){
        if(cadeiraValida(linha, coluna)){
            if(cadeiraDisponivel(linha, coluna)){
                cadeiras[linha][coluna].setOcupada(true);
                return true;
            }
        }

        return false;
    }

    // se false, nao liberou a cadeira. se true, liberou a cadeira.
    public boolean liberarCadeira(int linha, int coluna){
        if(cadeiraValida(linha, coluna)){
            if(!cadeiraDisponivel(linha, coluna)){
                cadeiras[linha][coluna].setOcupada(false);
                return true;

            }
        }
        return false;
    }

    // exibir todas as cadeiras livres no caso da compra de apenas um bilhete
    public void exibirCadeiras(){

    }

    // exibir cadeiras livres juntas na mesma fileira quando a compra for de mais de um bilhete
    public void exibirCadeirasJuntas(){

    }

    public boolean fazParteDeGrupo(){

        return false;
    }

    // buscar cadeiras livres juntas na mesma fileira
    // public int[][] buscarCadeirasJuntas(){

        
    // }

    public void exibirOpcoesCadeirasJuntas(){

    }

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

    // gets e sets
    
    

    
}
