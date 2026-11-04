package model.enums;

/**
 * Parte 2 - Tipos de sala com seu multiplicador de preço.
 */
public enum TipoSala {

    COMUM("Comum", 1.0),
    SALA_3D("3D", 1.2),
    XD("XD", 1.3),
    XD_3D("XD/3D", 1.4);

    private String tipo;
    private double multiplicador;

    TipoSala(String tipo, double multiplicador) {
        this.tipo = tipo;
        this.multiplicador = multiplicador;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
