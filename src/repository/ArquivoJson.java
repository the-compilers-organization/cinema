package repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import exceptions.PersistenciaException;


// Classe auxiliar para ler e grava arquivos JSON usando a biblioteca Gson
// Todos os erros de arquivo viram PersistenciaException

public class ArquivoJson {

    
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // ler o arquivo e devolve um vetor JSON (vazio se o arquivo nAO existir)
    public static JsonArray ler(String caminho) throws PersistenciaException {
        File arquivo = new File(caminho);
        if (!arquivo.exists() || arquivo.length() == 0) {
            return new JsonArray();
        }

        FileReader leitor = null;
        try {
            leitor = new FileReader(arquivo, StandardCharsets.UTF_8);
            JsonElement conteudo = JsonParser.parseReader(leitor);
            if (!conteudo.isJsonArray()) {
                throw new PersistenciaException("O arquivo " + caminho + " não contém uma lista JSON.");
            }
            return conteudo.getAsJsonArray();
        } catch (IOException e) {
            throw new PersistenciaException("Erro ao ler o arquivo " + caminho + ": " + e.getMessage());
        } catch (JsonParseException e) {
            throw new PersistenciaException("O arquivo " + caminho + " está com o JSON inválido.");
        } finally {
            fechar(leitor); // o finally sempre executa, com ou sem erro
        }
    }

    // Grava o conteúdo no arquivo (ou cria pasta data quando nao existir
    public static void gravar(String caminho, JsonElement conteudo) throws PersistenciaException {
        File arquivo = new File(caminho);
        File pasta = arquivo.getAbsoluteFile().getParentFile();
        if (pasta != null && !pasta.exists()) {
            pasta.mkdirs();
        }

        FileWriter escritor = null;
        try {
            escritor = new FileWriter(arquivo, StandardCharsets.UTF_8);
            GSON.toJson(conteudo, escritor);
        } catch (IOException e) {
            throw new PersistenciaException("Erro ao gravar o arquivo " + caminho + ": " + e.getMessage());
        } finally {
            fechar(escritor);
        }
    }

    private static void fechar(java.io.Closeable arquivo) {
        if (arquivo != null) {
            try {
                arquivo.close();
            } catch (IOException e) {
                // nao tem o que fazer se falhar ao fechar
            }
        }
    }
}
