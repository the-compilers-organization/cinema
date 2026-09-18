package enums;

public enum CupomPromocional{
    CRITICO(1.0),
    ESTUDANTE(0.50),
    MAIS_60(0.60),
    PROFESSOR(0.50),
    MEMBROS_VIP(0.70);

    private double percentualDesconto;

    CupomPromocional(double percentualDesconto){
        this.percentualDesconto = percentualDesconto;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }


}