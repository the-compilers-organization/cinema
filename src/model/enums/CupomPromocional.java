package model.enums;


//  Parte 2 - Três cupons promocionais com seus descontos
//  O percentual é guardado como fração: 0.10 = 10%

public enum CupomPromocional {

    BEMVINDO10(0.10),
    CINEPOO20(0.20),
    QUARTA30(0.30);

    private double percentualDesconto;

    CupomPromocional(double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    // Aplica o desconto do cupom em um valor
    public double aplicarDesconto(double valor) {
        return valor - (valor * percentualDesconto);
    }

    @Override
    public String toString() {
        return name() + " (" + (int) (percentualDesconto * 100) + "% de desconto)";
    }
}
