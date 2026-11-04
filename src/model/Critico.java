package model;

import exceptions.ValidacaoException;
import exceptions.VendasException;
import model.enums.Sexo;


// Parte 2 - Crítico herda tudo de Usuario e tem o atributo "origem" (órgão ao qual o crítico está ligado)
// sobrecrita: o bilhete do crítico é sempre gratuito (valor zero)

public class Critico extends Usuario {

    private String origem;

    public Critico(String user, String cpf, String senha, int idade, Sexo sexo, String email,
                   String nomeCartao, String numeroCartao, String codigoVerificador, String origem)
            throws ValidacaoException {
        super(user, cpf, senha, idade, sexo, email, nomeCartao, numeroCartao, codigoVerificador);
        setOrigem(origem);
    }

    @Override
    public String getTipo() {
        return "Crítico";
    }

    @Override
    public Bilhete comprarBilhete(Sala sala, Sessao sessao, int linha, int coluna)
            throws VendasException, ValidacaoException {

        Bilhete bilhete = super.comprarBilhete(sala, sessao, linha, coluna);
        bilhete.setValor(0.0); // crítico não paga
        return bilhete;
    }

   
    // Recebe uma nota de 0 a 10 e um filme
    // O filme incrementa a quantidade de críticos e recalcula a média
    public void atribuirNota(double nota, Filme filme) throws ValidacaoException {
        if (filme == null) {
            throw new ValidacaoException("Selecione um filme.");
        }
        filme.adicionarNota(nota);
    }

    
    // Recebe o texto da crítica e um filme
    // A crítica é colocada no final do vetor de críticas do filme
    public void atribuirCritica(String critica, Filme filme) throws ValidacaoException {
        if (filme == null) {
            throw new ValidacaoException("Selecione um filme.");
        }
        Validador.textoObrigatorio(critica, "Crítica");
        filme.adicionarCritica(new Critica(getUser(), origem, critica.trim()));
    }

    // Sobrescreve para copiar também a origem
    @Override
    public void copiarDados(Usuario outro) {
        super.copiarDados(outro);
        if (outro instanceof Critico) {
            this.origem = ((Critico) outro).getOrigem();
        }
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) throws ValidacaoException {
        Validador.textoObrigatorio(origem, "Origem (veículo do crítico)");
        this.origem = origem.trim();
    }
}
