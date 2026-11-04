package controller;

import java.util.ArrayList;

import exceptions.ValidacaoException;
import model.Bilhete;
import model.Compra;
import model.Filme;
import model.Sala;
import model.Sessao;
import model.enums.TipoSala;


// Camada de NEGÓCIO (controller) - salas, sessões (programação do dia) e poltronas
// As salas e sessões são montadas em memória a partir dos filmes em cartaz
public class SessaoController {

    private Sala[] salas;

    public SessaoController(ArrayList<Filme> filmes) throws ValidacaoException {
        montarProgramacao(filmes);
    }

   
    // Monta a programação com os filmes em cartaz
    // Algumas posições da grade ficam vazias (null) de propósito, como pede o enunciado
    private void montarProgramacao(ArrayList<Filme> filmes) throws ValidacaoException {
        salas = new Sala[4];
        salas[0] = new Sala(1, TipoSala.COMUM);
        salas[1] = new Sala(2, TipoSala.SALA_3D);
        salas[2] = new Sala(3, TipoSala.XD);
        salas[3] = new Sala(4, TipoSala.XD_3D);

        ArrayList<Filme> emCartaz = new ArrayList<Filme>();
        for (Filme filme : filmes) {
            if (filme.isEmCartaz()) {
                emCartaz.add(filme);
            }
        }
        if (emCartaz.isEmpty()) {
            return;
        }

        int proximoFilme = 0;
        for (int s = 0; s < salas.length; s++) {
            for (int h = 0; h < Sala.HORARIOS.length; h++) {
                if ((s + h) % 4 == 3) {
                    continue; // horário sem sessão (fica null)
                }
                salas[s].adicionarSessao(h, emCartaz.get(proximoFilme % emCartaz.size()));
                proximoFilme++;
            }
        }
    }

    public Sala[] getSalas() {
        return salas;
    }

    public Sala buscarSala(int numero) {
        for (Sala sala : salas) {
            if (sala.getNumero() == numero) {
                return sala;
            }
        }
        return null;
    }

    // Retorna a sessão de um bilhete, ou null se ela não existir mais
    public Sessao buscarSessaoDoBilhete(Bilhete bilhete) {
        Sala sala = buscarSala(bilhete.getNumeroSala());
        if (sala == null) {
            return null;
        }
        Sessao sessao = sala.buscarSessao(bilhete.getHorarioSessao());
        if (sessao == null || !sessao.getFilme().getNome().equals(bilhete.getFilme())) {
            return null;
        }
        return sessao;
    }

    public boolean filmeEstaNaProgramacao(String nome) {
        for (Sala sala : salas) {
            for (Sessao sessao : sala.getSessoesDisponiveis()) {
                if (sessao.getFilme().getNome().equalsIgnoreCase(nome)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Marca como ocupadas as poltronas dos bilhetes das compras
    public void ocuparCadeiras(ArrayList<Compra> compras) {
        for (Compra compra : compras) {
            for (Bilhete bilhete : compra.getBilhetes()) {
                Sessao sessao = buscarSessaoDoBilhete(bilhete);
                if (sessao != null) {
                    sessao.ocuparCadeira(bilhete.getLinha(), bilhete.getColuna());
                }
            }
        }
    }

    public void liberarCadeira(Bilhete bilhete) {
        Sessao sessao = buscarSessaoDoBilhete(bilhete);
        if (sessao != null) {
            sessao.liberarCadeira(bilhete.getLinha(), bilhete.getColuna());
        }
    }

    public void liberarCadeiras(Compra compra) {
        for (Bilhete bilhete : compra.getBilhetes()) {
            liberarCadeira(bilhete);
        }
    }

    // Sugestões de cadeiras juntas, sem repetir cadeiras que já estão no carrinho
    public ArrayList<int[]> sugerirCadeirasJuntas(Compra carrinho, Sala sala, Sessao sessao, int quantidade)
            throws ValidacaoException {
        if (sala == null || sessao == null) {
            throw new ValidacaoException("Escolha a sala e a sessão.");
        }
        if (quantidade < 2) {
            throw new ValidacaoException("A sugestão de lugares juntos é para 2 ou mais ingressos. "
                    + "Para 1 ingresso, clique em uma poltrona livre.");
        }

        ArrayList<int[]> livres = new ArrayList<int[]>();
        for (int[] opcao : sessao.buscarCadeirasJuntas(quantidade)) {
            boolean conflito = false;
            for (int c = opcao[1]; c < opcao[1] + quantidade; c++) {
                if (carrinho.contemCadeira(sala.getNumero(), sessao.getHorario(), opcao[0], c)) {
                    conflito = true;
                }
            }
            if (!conflito) {
                livres.add(opcao);
            }
        }
        return livres;
    }
}
