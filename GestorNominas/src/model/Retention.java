package model;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public class Retention {
    private int cod_r = 0;
    private String desc_r = " ";
    private double quant = 0;
    public Retention(int cod_r, String desc_r, double quant) {
        this.cod_r = cod_r;
        this.desc_r = desc_r;
        this.quant = quant;
    }

    public int getCod_r() {
        return cod_r;
    }

    public void setCod_r(int cod_r) {
        this.cod_r = cod_r;
    }

    public String getDesc_r() {
        return desc_r;
    }

    public void setDesc_r(String desc_r) {
        this.desc_r = desc_r;
    }

    public double getQuant() {
        return quant;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }
}