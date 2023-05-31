package model;

import java.text.DecimalFormat;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public class Retention {
    private String cod_r = "";
    private double quant = 0;
    public Retention(String cod_r, double quant) {
        this.cod_r = cod_r;
        this.quant = quant;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return cod_r+": "+decimalFormat.format(quant);
    }

    public String getCod_r() {
        return cod_r;
    }

    public void setCod_r(String cod_r) {
        this.cod_r = cod_r;
    }

    public double getQuant() {
        return quant;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }
}