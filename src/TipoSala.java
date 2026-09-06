public enum TipoSala {

    COMUM("comum", 1.0),
    _3D("3D", 1.2),
    XD("XD", 1.3),
    XD_3D("XD/3D", 1.4);


    private String tipo;
    private double multiplicador;


    TipoSala(String tipo, double multiplicador){
        this.tipo = tipo;
        this.multiplicador = multiplicador;
    }


    public String getTipo() {
        return tipo;
    }


    public double getMultiplicador() {
        return multiplicador;
    }

    



}
