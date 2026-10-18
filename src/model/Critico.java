package model;

import enums.Sexo;
import enums.CupomPromocional;

public class Critico extends Usuario{
    private String origem;


    public Critico(String user, String cpf, String senha, int idade, Sexo sexo, String email, String nomeCartao, String numeroCartao, String codigoVerificador, String origem){
        super(user, cpf, senha, idade, sexo, email, nomeCartao, numeroCartao, codigoVerificador);
        this.origem = origem;

    }

    // sobrescrita 
    @Override
    public double calcularValorDoBilhete(Compra compra){

        return compra.calcularTotalBilhetes() - (compra.calcularTotalBilhetes() * CupomPromocional.CRITICO.getPercentualDesconto());
    }


    public boolean atribuirNota(double nota, Filme filme) {

        if(nota >= 0 && nota <= 10 && filme != null) {
            return filme.adicionarNota(nota);
        }

        return false;
    }


    public boolean atribuirCritica(String mensagem, Filme filme) {

        if(mensagem != null && filme != null) {
            Critica critica = new Critica(
                origem,
                super.getUser(),
                mensagem
            );

            return filme.adicionarCritica(critica);
        }

        return false;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

       
}