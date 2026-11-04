package model;

import exceptions.ValidacaoException;
import exceptions.VendasException;
import model.enums.Sexo;


// Parte 2 - Estudante herda tudo de Usuario
// sobrescrita: o bilhete do estudante custa metade do valor

public class Estudante extends Usuario {

    public Estudante(String user, String cpf, String senha, int idade, Sexo sexo, String email,
                     String nomeCartao, String numeroCartao, String codigoVerificador)
            throws ValidacaoException {
        super(user, cpf, senha, idade, sexo, email, nomeCartao, numeroCartao, codigoVerificador);
    }

    @Override
    public String getTipo() {
        return "Estudante";
    }

    @Override
    public Bilhete comprarBilhete(Sala sala, Sessao sessao, int linha, int coluna)
            throws VendasException, ValidacaoException {

        Bilhete bilhete = super.comprarBilhete(sala, sessao, linha, coluna);
        bilhete.setValor(bilhete.getValor() / 2); // meia-entrada
        return bilhete;
    }
}
