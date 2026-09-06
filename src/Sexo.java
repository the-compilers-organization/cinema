public enum Sexo {
    F(1),
    M(2);

    private int sexo;

    Sexo(int sexo){
        this.sexo = sexo;
    }

    public int getSexo() {
        return sexo;
    }
}
