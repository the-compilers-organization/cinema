public class Sessao {

    private static final int TAM_LINHA = 10;
    private static final int TAM_COLUNA = 15;

    private String horario;
    private Filme filme;
    private boolean[][] cadeiras;

    public Sessao(String horario, Filme filme){
        this.horario = horario;
        this.filme = filme;
        this.cadeiras = new boolean[TAM_LINHA][TAM_COLUNA]; // inicializar td em false. quando false, a cadeira esta livre. quando true, a cadeira esta ocupada
    }

    // se false, não tem cadeira disponivel. se true, tem cadeira disponivel
    public boolean cadeiraDisponivel(int linha, int coluna){

        return false;
    }


    // se false, não ocupou cadeira. se true, ocupou a cadeira.
    public boolean ocuparCadeira(int linha, int coluna){


        return false;
    }

    // se false, nao liberou a cadeira. se true, liberou a cadeira.
    public boolean liberarCadeira(int linha, int coluna){

        return false;
    }

    // exibir todas as cadeiras livres no caso da compra de apenas um bilhete
    public void exibirCadeiras(){

    }

    // exibir cadeiras livres juntas na mesma fileira quando a compra for de mais de um bilhete
    public void exibirCadeirasJuntas(){

    }

    // buscar cadeiras livres juntas na mesma fileira
    // public int[][] buscarCadeirasJuntas(){

        
    // }

    public void exibirOpcoesCadeirasJuntas(){

    }

    // gets e sets
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

    public boolean[][] getCadeiras() {
        return cadeiras;
    }

    public void setCadeiras(boolean[][] cadeiras) {
        this.cadeiras = cadeiras;
    }

    

    
}
