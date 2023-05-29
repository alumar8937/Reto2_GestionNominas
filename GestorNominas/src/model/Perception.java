package model;

/**
 * @Author Pedro Marín Sanchis
 *
 */
public class Perception {
    private String cod_p = "";
    private String desc_p = " ";
    private String type_p = " ";
    private double quant = 0;

    public Perception(String cod_p, String desc_p, String type_p, double quant) {
        this.cod_p = cod_p;
        this.desc_p = desc_p;
        this.type_p = type_p;
        this.quant = quant;
    }

    public String getCod_p() {
        return cod_p;
    }

    public String getDesc_p() {
        return desc_p;
    }

    public String getType_p() { return type_p; }

    public double getQuant() {
        return quant;
    }

    public void setCod_p(String cod_p) {
        this.cod_p = cod_p;
    }

    public void setDesc_p(String desc_p) {
        this.desc_p = desc_p;
    }

    public void setType_p(String type_p) { this.type_p = type_p; }

    public void setQuant(double quant) {
        this.quant = quant;
    }
}
