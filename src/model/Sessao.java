package model;

public class Sessao {

    private static final int TAM_LINHA = 10;
    private static final int TAM_COLUNA = 15;

    private String horario;
    private Filme filme;
    private Cadeira[][] cadeiras;

    public Sessao(String horario, Filme filme){
        this.horario = horario;
        this.filme = filme;
        this.cadeiras = new Cadeira[TAM_LINHA][TAM_COLUNA];
        
        int l, c;

        for(l = 0; l < TAM_LINHA; l += 1){
            for(c = 0; c < TAM_COLUNA; c += 1){
                cadeiras[l][c] = new Cadeira(l, c);
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
        if(cadeiraValida(linha, coluna) && cadeiraDisponivel(linha, coluna)){
            cadeiras[linha][coluna].setOcupada(true);
            return true;
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

    // buscar cadeira
    public Cadeira buscarCadeira(int linha, int coluna){
        if(cadeiraValida(linha, coluna)){
            return cadeiras[linha][coluna];
        }
        return null;
    }

    

    

    public boolean fazParteDeGrupo(int linha, int coluna, int qtd){
        int inicio, i;
        boolean grupoLivre;

        if(qtd <= 1 || qtd > TAM_COLUNA || !cadeiraValida(linha, coluna)){
            return false;
        }

        for(inicio = 0; inicio < TAM_COLUNA - qtd; inicio += 1){
            grupoLivre = true;

            for(i = inicio; i < qtd; i += 1){
                if(!cadeiraDisponivel(linha, i)){
                    grupoLivre = false;
                }

            }


            if(grupoLivre && coluna >= inicio && coluna < inicio + qtd){
                return true;
            }

        }
       
        
        return false;
    }

    

    // buscar cadeiras livres juntas na mesma fileira
    public int[][] buscarCadeirasJuntas(int qtd){
        int linha, coluna, i, maxOpcoes, qtdOpcoes = 0;
        boolean livre;

        if(qtd <= 1 || qtd > TAM_COLUNA){
            return null;
        }

        maxOpcoes = TAM_LINHA * (TAM_COLUNA - qtd + 1);
        int[][] opcoesTemp = new int[maxOpcoes][2];


        for(linha = 0; linha < TAM_LINHA; linha += 1){
            for(coluna = 0; coluna < TAM_COLUNA - qtd; coluna += 1){
                livre = true;

                for(i = coluna; i < qtd; i += 1){
                    if(!cadeiraDisponivel(linha, i)){
                        livre = false;
                    }
                }

                if(livre){
                    opcoesTemp[qtdOpcoes][0] = linha;
                    opcoesTemp[qtdOpcoes][1] = coluna;
                    qtdOpcoes += 1;
                }
            }
        }

        int[][] opcoes = new int[qtdOpcoes][2];

        for(i = 0; i < qtdOpcoes; i += 1){
            opcoes[i][0] = opcoesTemp[i][0];
            opcoes[i][1] = opcoesTemp[i][1];

        }

       return opcoes;
    }

    
    // ficará no view public void exibirCadeiras() - exibir todas as cadeiras livres no caso da compra de apenas um bilhete
    // public void exibirCadeiras(){
    //    int l, c;

    //     System.out.println(" ".repeat(28) + "TELA" + " ".repeat(28));

    //     System.out.printf("  ");
    //     for(c = 0; c < TAM_COLUNA; c += 1){
    //         System.out.printf("%02d  ", c);
    //     }

    //     System.out.println();

    //     for(l = 0; l < TAM_LINHA; l += 1){
    //         for(c = 0; c < TAM_COLUNA; c += 1){
    //             System.out.printf("%02d", l);
    //             if(cadeiraDisponivel(l, c)){
    //                 System.out.printf("%02d  ", "O");
    //             }else{
    //                 System.out.printf("%02d  ", "X");
    //             }
    //         }
    //         System.out.println();

    //     }
    //     System.out.println();

    //     System.out.println("X = Cadeira Ocupada");
    //     System.out.println("O = Cadeira Disponivel");
        
    // }

    // ficará no view public void exibirCadeirasJuntas(int quantidade) - exibir cadeiras livres juntas na mesma fileira quando a compra for de mais de um bilhete
    // public void exibirCadeirasJuntas(int quantidade){
    //     int linha, coluna;

    //     System.out.println(" ".repeat(28) + "TELA" + " ".repeat(28));

    //     System.out.printf("  ");
    //     for(coluna = 0; coluna < TAM_COLUNA; coluna += 1){
    //         System.out.printf("%02d  ", coluna);
    //     }

    //     for(linha = 0; linha < TAM_LINHA; linha += 1){
    //         System.out.printf("%02d  ", linha);

    //         for(coluna = 0; coluna < TAM_COLUNA; coluna += 1){
    //             if(!cadeiraDisponivel(linha, coluna)){
    //                 System.out.printf("X  ");
    //             }else{
    //                 if(fazParteDeGrupo(linha, coluna, quantidade)){
    //                     System.out.printf("O  ");
    //                 }else{
    //                     System.out.printf("-  ");
    //                 }
    //             }
    //         }

    //         System.out.println();
    //     }
    //     System.out.println();

    //     System.out.println("LEGENDA:");
    //     System.out.println("O = cadeira disponivel no grupo");
    //     System.out.println("X = cadeira ocupada");
    //     System.out.println("- = cadeira livre fora de grupo");

    // }

    // ficará no view public void exibirOpcoesCadeirasJuntas(int quantidade)
    // public void exibirOpcoesCadeirasJuntas(int quantidade){
    //     int[][] opcoes = buscarCadeirasJuntas(quantidade);

    //     if(opcoes == null){
    //         System.out.println("Nao existem cadeiras juntas disponiveis.");
    //         return;
    //     }

    //     int i, j;

    //     for(i = 0; i < opcoes.length; i += 1){
    //         int linha = opcoes[i][0];
    //         int coluna = opcoes[i][1];

    //         System.out.printf("%d. Linha %d | Cadeiras: ", i + 1, linha);

    //         for(j = 0; j < quantidade; j += 1){
    //             System.out.printf("%d", coluna + j);

    //             if(j < quantidade - 1){
    //                 System.out.printf(", ");
    //             }
    //         }
    //         System.out.println();
    //     }
    // }


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
