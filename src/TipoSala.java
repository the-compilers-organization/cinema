public enum TipoSala {

    COMUM(1.0),
    SALA3D(1.2),
    XD(1.3),
    XD_3D(1.4);


  
    private double multiplicador;


    TipoSala(double multiplicador){
        this.multiplicador = multiplicador;
    }


    public double getMultiplicador() {
        return multiplicador;
    }


}
