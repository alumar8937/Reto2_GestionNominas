package model;

import java.text.DecimalFormat;

/**
 * Represents a contingency with a code and quantity.
 *
 * @author Pedro Marín Sanchis
 */
public class Contingency {
    private String cod_c = "";
    private double quant = 0;

    /**
     * Constructs a Contingency object with the specified code and quantity.
     *
     * @param cod_c the code of the contingency
     * @param quant the quantity of the contingency
     */
    public Contingency(String cod_c, double quant) {
        this.cod_c = cod_c;
        this.quant = quant;
    }

    /**
     * Returns a string representation of the contingency.
     *
     * @return a string representation of the contingency
     */
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return cod_c + ": " + decimalFormat.format(quant) + "%";
    }

    /**
     * Returns the code of the contingency.
     *
     * @return the code of the contingency
     */
    public String getCod_c() {
        return cod_c;
    }

    /**
     * Returns the quantity of the contingency.
     *
     * @return the quantity of the contingency
     */
    public double getQuant() {
        return quant;
    }

    /**
     * Sets the code of the contingency.
     *
     * @param cod_c the code of the contingency to set
     */
    public void setCod_c(String cod_c) {
        this.cod_c = cod_c;
    }

    /**
     * Sets the quantity of the contingency.
     *
     * @param quant the quantity of the contingency to set
     */
    public void setQuant(double quant) {
        this.quant = quant;
    }
}
