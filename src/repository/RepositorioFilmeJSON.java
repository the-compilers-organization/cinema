package repository;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import model.Critica;
import model.Filme;


// Implementação de IRepositorioFilme que guarda os dados em data/filmes.json
// O vetor de críticas tem 100 posições; no JSON gravamos só as preenchidas

public class RepositorioFilmeJSON implements IRepositorioFilme {

    private String caminho;
    private ArrayList<Filme> filmes;

    public RepositorioFilmeJSON() throws PersistenciaException {
        this("data/filmes.json");
    }

    public RepositorioFilmeJSON(String caminho) throws PersistenciaException {
        this.caminho = caminho;
        this.filmes = carregar();
    }

    @Override
    public void inserir(Filme filme) throws PersistenciaException {
        filmes.add(filme);
        try {
            gravar();
        } catch (PersistenciaException e) {
            filmes.remove(filme);
            throw e;
        }
    }

    @Override
    public Filme buscar(String nome) {
        if (nome == null) {
            return null;
        }
        for (Filme filme : filmes) {
            if (filme.getNome().equalsIgnoreCase(nome.trim())) {
                return filme;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Filme> listar() {
        return filmes;
    }

    @Override
    public void atualizar(Filme filme) throws PersistenciaException {
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getNome().equalsIgnoreCase(filme.getNome())) {
                filmes.set(i, filme);
                gravar();
                return;
            }
        }
    }

    @Override
    public void remover(String nome) throws PersistenciaException {
        Filme filme = buscar(nome);
        if (filme == null) {
            return;
        }
        int posicao = filmes.indexOf(filme);
        filmes.remove(filme);
        try {
            gravar();
        } catch (PersistenciaException e) {
            filmes.add(posicao, filme);
            throw e;
        }
    }

    //  leitura e gravação do arquivo 

    private ArrayList<Filme> carregar() throws PersistenciaException {
        ArrayList<Filme> lista = new ArrayList<Filme>();
        JsonArray dados = ArquivoJson.ler(caminho);

        try {
            for (JsonElement elemento : dados) {
                JsonObject obj = elemento.getAsJsonObject();

                Filme filme = new Filme(
                        obj.get("nome").getAsString(),
                        obj.get("duracao").getAsInt(),
                        obj.get("sinopse").getAsString(),
                        obj.get("valor").getAsDouble());

                if (obj.has("emCartaz")) {
                    filme.setEmCartaz(obj.get("emCartaz").getAsBoolean());
                }
                if (obj.has("nota")) {
                    filme.setNota(obj.get("nota").getAsDouble());
                }
                if (obj.has("quantidadeCriticos")) {
                    filme.setQuantidadeCriticos(obj.get("quantidadeCriticos").getAsInt());
                }
                if (obj.has("criticas")) {
                    Critica[] criticas = ArquivoJson.GSON.fromJson(obj.get("criticas"), Critica[].class);
                    for (Critica critica : criticas) {
                        if (critica != null) {
                            filme.adicionarCritica(critica);
                        }
                    }
                }
                lista.add(filme);
            }
        } catch (ValidacaoException e) {
            throw new PersistenciaException("Filme inválido no arquivo " + caminho + ": " + e.getMessage());
        } catch (RuntimeException e) {
            throw new PersistenciaException("O arquivo " + caminho + " possui um filme com formato inválido.");
        }
        return lista;
    }

    private void gravar() throws PersistenciaException {
        JsonArray dados = new JsonArray();
        for (Filme filme : filmes) {
            JsonObject obj = new JsonObject();
            obj.addProperty("nome", filme.getNome());
            obj.addProperty("duracao", filme.getDuracao());
            obj.addProperty("sinopse", filme.getSinopse());
            obj.addProperty("valor", filme.getValor());
            obj.addProperty("emCartaz", filme.isEmCartaz());
            obj.addProperty("nota", filme.getNota());
            obj.addProperty("quantidadeCriticos", filme.getQuantidadeCriticos());
            obj.add("criticas", ArquivoJson.GSON.toJsonTree(filme.getCriticasPreenchidas()));
            dados.add(obj);
        }
        ArquivoJson.gravar(caminho, dados);
    }
}
