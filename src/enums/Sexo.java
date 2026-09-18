package enums;

public enum Sexo {
    F(1),
    M(2),
    NB(3),
    NI(4);

    private int sexo;

    Sexo(int sexo){
        this.sexo = sexo;
    }

    public int getSexo() {
        return sexo;
    }
}
