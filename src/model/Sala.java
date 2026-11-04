package model;

import java.util.ArrayList;

import exceptions.ValidacaoException;
import model.enums.TipoSala;


// Parte 1 - Sala: possui um vetor de sessões de TAMANHO FIXO, organizado por uma grade de horários
// Se não houver sessão em um horário, a posição fica null e não aparece para o usuário

public class Sala {

    // Grade de horários (cada posição do vetor de sessões corresponde a um horário)
    public static final String[] HORARIOS = {"10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00"};

    private int numero;
    private TipoSala tipoSala;
    private Sessao[] sessoes;

    public Sala(int numero, TipoSala tipoSala) {
        this.numero = numero;
        this.tipoSala = tipoSala;
        this.sessoes = new Sessao[HORARIOS.length];
    }

    // Cria uma sessão do filme no horário da grade indicado
    public void adicionarSessao(int indiceHorario, Filme filme) throws ValidacaoException {
        if (indiceHorario < 0 || indiceHorario >= HORARIOS.length) {
            throw new ValidacaoException("Horário inválido.");
        }
        if (filme == null) {
            throw new ValidacaoException("Filme inválido.");
        }
        if (sessoes[indiceHorario] != null) {
            throw new ValidacaoException("Já existe sessão às " + HORARIOS[indiceHorario] + " na sala " + numero + ".");
        }
        sessoes[indiceHorario] = new Sessao(HORARIOS[indiceHorario], filme);
    }

    // Retorna só as sessões que existem (ignora as posições null)
    public ArrayList<Sessao> getSessoesDisponiveis() {
        ArrayList<Sessao> lista = new ArrayList<Sessao>();
        for (Sessao sessao : sessoes) {
            if (sessao != null) {
                lista.add(sessao);
            }
        }
        return lista;
    }

    public Sessao buscarSessao(String horario) {
        for (Sessao sessao : sessoes) {
            if (sessao != null && sessao.getHorario().equals(horario)) {
                return sessao;
            }
        }
        return null;
    }

    // Valor do ingresso nesta sala = valor do filme x multiplicador
    public double calcularValorIngresso(Filme filme) {
        return filme.getValor() * tipoSala.getMultiplicador();
    }

    @Override
    public String toString() {
        return "Sala " + numero + "  (" + tipoSala.getTipo() + ")";
    }

    //  GETTERS E SETTERS 
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    public Sessao[] getSessoes() {
        return sessoes;
    }

    public void setSessoes(Sessao[] sessoes) {
        this.sessoes = sessoes;
    }
}
