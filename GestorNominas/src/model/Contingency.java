package model;

import java.text.DecimalFormat;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public class Contingency {
    private String cod_c = "";
    private double quant = 0;

    public Contingency(String cod_c, double quant) {
        this.cod_c = cod_c;
        this.quant = quant;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return cod_c+": "+decimalFormat.format(quant)+"%";
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
