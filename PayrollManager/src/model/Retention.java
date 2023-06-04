package model;

import java.text.DecimalFormat;

/**
 * Represents a retention with a code and quantity.
 *
 * @author Pedro Marín Sanchis
 */
public class Retention {
    private String cod_r = "";
    private double quant = 0;

    /**
     * Constructs a Retention object with the specified code and quantity.
     *
     * @param cod_r  the code of the retention
     * @param quant  the quantity of the retention
     */
    public Retention(String cod_r, double quant) {
        this.cod_r = cod_r;
        this.quant = quant;
    }

    /**
     * Returns a string representation of the Retention object.
     *
     * @return a string representation of the retention in the format "code: quantity"
     */
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return cod_r + ": " + decimalFormat.format(quant);
    }

    /**
     * Returns the code of the retention.
     *
     * @return the code of the retention
     */
    public String getCod_r() {
        return cod_r;
    }

    /**
     * Sets the code of the retention.
     *
     * @param cod_r the code of the retention to be set
     */
    public void setCod_r(String cod_r) {
        this.cod_r = cod_r;
    }

    /**
     * Returns the quantity of the retention.
     *
     * @return the quantity of the retention
     */
    public double getQuant() {
        return quant;
    }

    /**
     * Sets the quantity of the retention.
     *
     * @param quant the quantity of the retention to be set
     */
    public void setQuant(double quant) {
        this.quant = quant;
    }
}
