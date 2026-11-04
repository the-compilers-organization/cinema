package model;

import exceptions.ValidacaoException;


// Métodos de validação reaproveitados pelas classes do modelo
// Se o dado for inválido, uma ValidacaoException é lançada com
// uma mensagem que pode ser mostrada diretamente para o usuário
 
public class Validador {

    // Campo de texto não pode ser nulo nem vazio
    public static void textoObrigatorio(String valor, String nomeDoCampo) throws ValidacaoException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ValidacaoException("O campo \"" + nomeDoCampo + "\" é obrigatório.");
        }
    }

    // Aceita somente letras (inclusive acentuadas, como "João") e espaços
    public static void somenteLetras(String valor, String nomeDoCampo) throws ValidacaoException {
        textoObrigatorio(valor, nomeDoCampo);

        for (int i = 0; i < valor.length(); i++) {
            char c = valor.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                throw new ValidacaoException("O campo \"" + nomeDoCampo
                        + "\" deve conter apenas letras (sem números ou símbolos).");
            }
        }
    }

    // Verifica se o texto tem exatamente "quantidade" dígitos numéricos
    public static void somenteDigitos(String valor, int quantidade, String nomeDoCampo) throws ValidacaoException {
        textoObrigatorio(valor, nomeDoCampo);

        if (valor.length() != quantidade) {
            throw new ValidacaoException("O campo \"" + nomeDoCampo + "\" deve ter " + quantidade + " dígitos.");
        }

        for (int i = 0; i < valor.length(); i++) {
            if (!Character.isDigit(valor.charAt(i))) {
                throw new ValidacaoException("O campo \"" + nomeDoCampo + "\" deve conter apenas números.");
            }
        }
    }

    public static void email(String email) throws ValidacaoException {
        textoObrigatorio(email, "E-mail");

        int arroba = email.indexOf('@');
        if (arroba < 1 || email.lastIndexOf('.') < arroba + 2 || email.endsWith(".")) {
            throw new ValidacaoException("E-mail inválido. Exemplo: nome@email.com");
        }
    }

    public static void idade(int idade) throws ValidacaoException {
        if (idade < 0 || idade > 120) {
            throw new ValidacaoException("Idade inválida. Informe um valor entre 0 e 120.");
        }
    }

    // Remove pontos, traços e espaços do CPF 
    public static String limparCpf(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.replace(".", "").replace("-", "").replace(" ", "").trim();
    }
}
