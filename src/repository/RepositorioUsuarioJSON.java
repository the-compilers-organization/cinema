package repository;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import exceptions.PersistenciaException;
import model.Critico;
import model.Estudante;
import model.Usuario;


// Implementação de IRepositorioUsuario que guarda os dados em data/usuarios.json
// Os usuários ficam numa lista em memória. a cada alteração o arquivo é regravado
// O campo "tipo" é gravado para saber se o objeto é Usuario, Estudante ou Critico
 
public class RepositorioUsuarioJSON implements IRepositorioUsuario {

    private String caminho;
    private ArrayList<Usuario> usuarios;

    public RepositorioUsuarioJSON() throws PersistenciaException {
        this("data/usuarios.json");
    }

    public RepositorioUsuarioJSON(String caminho) throws PersistenciaException {
        this.caminho = caminho;
        this.usuarios = carregar();
    }

    @Override
    public void inserir(Usuario usuario) throws PersistenciaException {
        usuarios.add(usuario);
        try {
            gravar();
        } catch (PersistenciaException e) {
            usuarios.remove(usuario); // desfaz se não conseguiu gravar
            throw e;
        }
    }

    @Override
    public Usuario buscar(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> listar() {
        return usuarios;
    }

    @Override
    public void atualizar(Usuario usuario) throws PersistenciaException {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCpf().equals(usuario.getCpf())) {
                usuarios.set(i, usuario);
                gravar();
                return;
            }
        }
    }

    @Override
    public void remover(String cpf) throws PersistenciaException {
        Usuario usuario = buscar(cpf);
        if (usuario == null) {
            return;
        }
        int posicao = usuarios.indexOf(usuario);
        usuarios.remove(usuario);
        try {
            gravar();
        } catch (PersistenciaException e) {
            usuarios.add(posicao, usuario);
            throw e;
        }
    }

    //  leitura e gravação do arquivo 

    private ArrayList<Usuario> carregar() throws PersistenciaException {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        JsonArray dados = ArquivoJson.ler(caminho);

        try {
            for (JsonElement elemento : dados) {
                JsonObject obj = elemento.getAsJsonObject();
                String tipo = obj.get("tipo").getAsString();

                if (tipo.equals("Estudante")) {
                    lista.add(ArquivoJson.GSON.fromJson(obj, Estudante.class));
                } else if (tipo.equals("Crítico")) {
                    lista.add(ArquivoJson.GSON.fromJson(obj, Critico.class));
                } else {
                    lista.add(ArquivoJson.GSON.fromJson(obj, Usuario.class));
                }
            }
        } catch (JsonParseException | IllegalStateException | NullPointerException e) {
            throw new PersistenciaException("O arquivo " + caminho + " possui um usuário com formato inválido.");
        }
        return lista;
    }

    private void gravar() throws PersistenciaException {
        JsonArray dados = new JsonArray();
        for (Usuario usuario : usuarios) {
            JsonObject obj = ArquivoJson.GSON.toJsonTree(usuario).getAsJsonObject();
            obj.addProperty("tipo", usuario.getTipo());
            dados.add(obj);
        }
        ArquivoJson.gravar(caminho, dados);
    }
}
