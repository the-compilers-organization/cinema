package model;

import enums.Sexo;

public class Estudante extends Usuario {

    public Estudante(String user, String cpf, String senha, int idade, Sexo sexo, String email, String nomeCartao, String numeroCartao, String codigoVerificador){
        super(user, cpf, senha, idade, sexo, email, nomeCartao, numeroCartao, codigoVerificador);

    }

    // sobrecrita do metodo public double calcularValorDoBilhete(Compra compra) da classe Usuario
    @Override
    public double calcularValorDoBilhete(Compra compra){
        return compra.calcularTotalBilhetes() / 2;

    }

}
