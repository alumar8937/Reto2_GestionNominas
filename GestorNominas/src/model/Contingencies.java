package model;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public class Contingencies {
    private String cod_c = "";
    private double quant = 0;

    public Contingencies(String cod_c, double quant) {
        this.cod_c = cod_c;
        this.quant = quant;
    }

    public String getCod_c() {
        return cod_c;
    }

    public double getQuant() {
        return quant;
    }

    public void setCod_c(String cod_c) {
        this.cod_c = cod_c;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }
}
