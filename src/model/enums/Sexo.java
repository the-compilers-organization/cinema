package model.enums;

public enum Sexo {

    FEMININO("Feminino"),
    MASCULINO("Masculino"),
    NAO_BINARIO("Não binário"),
    NAO_INFORMADO("Prefiro não informar");

    private String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
