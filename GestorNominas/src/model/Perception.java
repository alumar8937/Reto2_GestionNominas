package model;

import java.text.DecimalFormat;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public class Perception {
    private String cod_p = "";
    private double quant = 0;

    public Perception(String cod_p, double quant) {
        this.cod_p = cod_p;
        this.quant = quant;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return cod_p+": "+decimalFormat.format(quant)+"€";
    }

    public String getCod_p() {
        return cod_p;
    }

    public double getQuant() {
        return quant;
    }

    public void setCod_p(String cod_p) {
        this.cod_p = cod_p;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }
}
