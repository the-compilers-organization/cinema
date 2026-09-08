public enum Sexo {
    F("Feminino"),
    M("Masculino"),
    NB("Não Binário"),
    NF("Não Informado");

    private String sexo;

    Sexo(String sexo){
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}
