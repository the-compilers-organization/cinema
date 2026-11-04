package repository;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import exceptions.PersistenciaException;
import model.Compra;


// Implementação de IRepositorioCompra que guarda os dados em data/compras.json.

public class RepositorioCompraJSON implements IRepositorioCompra {

    private String caminho;
    private ArrayList<Compra> compras;

    public RepositorioCompraJSON() throws PersistenciaException {
        this("data/compras.json");
    }

    public RepositorioCompraJSON(String caminho) throws PersistenciaException {
        this.caminho = caminho;
        this.compras = carregar();
    }

    @Override
    public void inserir(Compra compra) throws PersistenciaException {
        compras.add(compra);
        try {
            gravar();
        } catch (PersistenciaException e) {
            compras.remove(compra);
            throw e;
        }
    }

    @Override
    public ArrayList<Compra> listar() {
        return compras;
    }

    @Override
    public ArrayList<Compra> listarPorCpf(String cpf) {
        ArrayList<Compra> lista = new ArrayList<Compra>();
        for (Compra compra : compras) {
            if (compra.getCpfUsuario().equals(cpf)) {
                lista.add(compra);
            }
        }
        return lista;
    }

    @Override
    public void atualizar(Compra compra) throws PersistenciaException {
        for (int i = 0; i < compras.size(); i++) {
            if (compras.get(i).getId() == compra.getId()) {
                compras.set(i, compra);
                gravar();
                return;
            }
        }
    }

    @Override
    public void remover(Compra compra) throws PersistenciaException {
        int posicao = compras.indexOf(compra);
        if (posicao < 0) {
            return;
        }
        compras.remove(posicao);
        try {
            gravar();
        } catch (PersistenciaException e) {
            compras.add(posicao, compra);
            throw e;
        }
    }

    @Override
    public int gerarProximoId() {
        int maior = 0;
        for (Compra compra : compras) {
            if (compra.getId() > maior) {
                maior = compra.getId();
            }
        }
        return maior + 1;
    }

    //  leitura e gravação do arquivo 

    private ArrayList<Compra> carregar() throws PersistenciaException {
        ArrayList<Compra> lista = new ArrayList<Compra>();
        JsonArray dados = ArquivoJson.ler(caminho);
        try {
            for (JsonElement elemento : dados) {
                lista.add(ArquivoJson.GSON.fromJson(elemento, Compra.class));
            }
        } catch (JsonParseException e) {
            throw new PersistenciaException("O arquivo " + caminho + " possui uma compra com formato inválido.");
        }
        return lista;
    }

    private void gravar() throws PersistenciaException {
        ArquivoJson.gravar(caminho, ArquivoJson.GSON.toJsonTree(compras));
    }
}
