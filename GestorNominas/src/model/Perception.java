package model;

import java.text.DecimalFormat;

/**
 * Represents a perception with a code and quantity.
 *
 * @author Pedro Marín Sanchis
 */
public class Perception {
    private String cod_p = "";
    private double quant = 0;

    /**
     * Constructs a Perception object with the specified code and quantity.
     *
     * @param cod_p  the code of the perception
     * @param quant  the quantity of the perception
     */
    public Perception(String cod_p, double quant) {
        this.cod_p = cod_p;
        this.quant = quant;
    }

    /**
     * Returns a string representation of the Perception object.
     *
     * @return a string representation of the perception in the format "code: quantity€"
     */
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return cod_p + ": " + decimalFormat.format(quant) + "€";
    }

    /**
     * Returns the code of the perception.
     *
     * @return the code of the perception
     */
    public String getCod_p() {
        return cod_p;
    }

    /**
     * Returns the quantity of the perception.
     *
     * @return the quantity of the perception
     */
    public double getQuant() {
        return quant;
    }

    /**
     * Sets the code of the perception.
     *
     * @param cod_p the code of the perception to be set
     */
    public void setCod_p(String cod_p) {
        this.cod_p = cod_p;
    }

    /**
     * Sets the quantity of the perception.
     *
     * @param quant the quantity of the perception to be set
     */
    public void setQuant(double quant) {
        this.quant = quant;
    }
}