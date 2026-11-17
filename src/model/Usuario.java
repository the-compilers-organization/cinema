package model;

import exceptions.ValidacaoException;
import exceptions.VendasException;
import model.enums.CupomPromocional;
import model.enums.Sexo;


// Parte 1 - Usuário que compra bilhetes online
// Parte 2 - É a superclasse de Estudante e Critico
// Os setters validam os dados e lançam ValidacaoException quando algo está errado

public class Usuario {

    private String user;
    private String cpf;
    private String senha;
    private int idade;
    private Sexo sexo;
    private String email;
    private String nomeCartao;
    private String numeroCartao;
    private String codigoVerificador;

    public Usuario(String user, String cpf, String senha, int idade, Sexo sexo, String email,
                   String nomeCartao, String numeroCartao, String codigoVerificador)
            throws ValidacaoException {
        setUser(user);
        setCpf(cpf);
        setSenha(senha);
        setIdade(idade);
        setSexo(sexo);
        setEmail(email);
        setNomeCartao(nomeCartao);
        setNumeroCartao(numeroCartao);
        setCodigoVerificador(codigoVerificador);
    }

    // Tipo do usuário (sobrescrito nas subclasses). Também é usado no JSON
    public String getTipo() {
        return "Cliente";
    }

    //  COMPRA DE BILHETE 

    // Compra (gera) um bilhete para a cadeira escolhida
    // Valor = valor do filme x multiplicador da sala
    // Estudante e Critico SOBRESCREVEM este método
    // Lança VendasException nos 3 casos pedidos na Parte 3
    
    public Bilhete comprarBilhete(Sala sala, Sessao sessao, int linha, int coluna)
            throws VendasException, ValidacaoException {

        if (sala == null || sessao == null) {
            throw new ValidacaoException("Escolha a sala e a sessão antes de comprar.");
        }

        Filme filme = sessao.getFilme();

        // Erro 3: filme que não está mais em cartaz
        if (!filme.isEmCartaz()) {
            throw new VendasException(VendasException.FORA_DE_CARTAZ, filme.getNome(),
                    "O filme \"" + filme.getNome() + "\" não está mais em cartaz.");
        }

        // Erro 1: a sessão já começou (horário já passou)
        if (sessao.jaComecou()) {
            throw new VendasException(VendasException.SESSAO_INICIADA, filme.getNome(),
                    "A sessão de \"" + filme.getNome() + "\" às " + sessao.getHorario()
                    + " já começou. Escolha outro horário.");
        }

        if (!sessao.cadeiraValida(linha, coluna)) {
            throw new ValidacaoException("Cadeira inválida.");
        }

        // Erro 2: poltrona já selecionada por outra compra
        if (!sessao.cadeiraDisponivel(linha, coluna)) {
            throw new VendasException(VendasException.POLTRONA_OCUPADA, filme.getNome(),
                    "A poltrona " + Sessao.nomeDaCadeira(linha, coluna) + " já foi vendida.");
        }

        return new Bilhete(this, sala, sessao, linha, coluna);
    }

   
    // sobrecarga (Parte 2): mesma compra, com um parâmetro a mais (CupomPromocional)
    // Chama a versão sem cupom (que pode estar sobrescrita) e aplica o desconto
    public Bilhete comprarBilhete(Sala sala, Sessao sessao, int linha, int coluna, CupomPromocional cupom)
            throws VendasException, ValidacaoException {

        Bilhete bilhete = comprarBilhete(sala, sessao, linha, coluna);

        if (cupom != null) {
            bilhete.setValor(cupom.aplicarDesconto(bilhete.getValor()));
        }
        return bilhete;
    }

    
    // Copia os dados de outro usuário (usado ao alterar o cadastro)
    // O CPF não é copiado, pois identifica o usuário
    
    public void copiarDados(Usuario outro) {
        this.user = outro.user;
        this.senha = outro.senha;
        this.idade = outro.idade;
        this.sexo = outro.sexo;
        this.email = outro.email;
        this.nomeCartao = outro.nomeCartao;
        this.numeroCartao = outro.numeroCartao;
        this.codigoVerificador = outro.codigoVerificador;
    }

    //  GETTERS E SETTERS 
    public String getUser() {
        return user;
    }

    public void setUser(String user) throws ValidacaoException {
        Validador.somenteLetras(user, "Nome de usuário");
        this.user = user.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws ValidacaoException {
        String cpfLimpo = Validador.limparCpf(cpf);
        Validador.somenteDigitos(cpfLimpo, 11, "CPF");
        this.cpf = cpfLimpo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws ValidacaoException {
        if (senha == null || senha.length() < 4) {
            throw new ValidacaoException("A senha deve ter pelo menos 4 caracteres.");
        }
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) throws ValidacaoException {
        Validador.idade(idade);
        this.idade = idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) throws ValidacaoException {
        if (sexo == null) {
            throw new ValidacaoException("Selecione o sexo.");
        }
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidacaoException {
        Validador.email(email);
        this.email = email.trim();
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) throws ValidacaoException {
        Validador.somenteLetras(nomeCartao, "Nome no cartão");
        this.nomeCartao = nomeCartao.trim();
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) throws ValidacaoException {
        String numero = (numeroCartao == null) ? "" : numeroCartao.replace(" ", "");
        Validador.somenteDigitos(numero, 16, "Número do cartão");
        this.numeroCartao = numero;
    }

    public String getCodigoVerificador() {
        return codigoVerificador;
    }

    public void setCodigoVerificador(String codigoVerificador) throws ValidacaoException {
        Validador.somenteDigitos(codigoVerificador, 3, "Código verificador (CVV)");
        this.codigoVerificador = codigoVerificador;
    }
}
