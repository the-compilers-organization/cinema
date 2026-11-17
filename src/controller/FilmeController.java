package controller;

import java.util.ArrayList;

import exceptions.FilmeException;
import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import model.Critico;
import model.Filme;
import model.Validador;
import model.interfaces.GerenciaFilmes;
import repository.IRepositorioFilme;


// Camada de NEGÓCIO (controller) - regras envolvendo filmes e críticas
// Acessa os dados somente pela interface IRepositorioFilme

public class FilmeController {

    private IRepositorioFilme repositorio;

    public FilmeController(IRepositorioFilme repositorio) {
        this.repositorio = repositorio;
    }

    public ArrayList<Filme> listar() {
        return repositorio.listar();
    }

    // "gerente" pode ser o Administrador ou o Funcionário (interface GerenciaFilmes)
    public void incluir(GerenciaFilmes gerente, Filme filme) throws FilmeException, PersistenciaException {
        gerente.incluirFilme(filme);
    }

    public void alterar(GerenciaFilmes gerente, Filme filme) throws FilmeException, PersistenciaException {
        gerente.alterarFilme(filme);
    }

    public void excluir(GerenciaFilmes gerente, String nome) throws FilmeException, PersistenciaException {
        gerente.excluirFilme(nome);
    }

    public void avaliar(Critico critico, Filme filme, double nota, String texto) throws ValidacaoException, PersistenciaException {
        Validador.textoObrigatorio(texto, "Crítica");
        critico.atribuirNota(nota, filme);
        critico.atribuirCritica(texto, filme);
        repositorio.atualizar(filme);
    }
}
