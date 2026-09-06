public enum Sexo {
    F("Feminino"),
    M("Masculino");

    private String sexo;

    Sexo(String sexo){
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}
