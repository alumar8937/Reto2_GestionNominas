package model;

/**
 * @Author Javier Blasco Gómez
 */
public class Payroll {
    String emp_name;
    int id_name;
    int id_batch;
    String nif;
    int year;
    int month;
    int day;
    double total_dev;
    double total_net;
    double ap_company;

    /**
     * @param id_name
     * @param id_batch
     * @param nif
     * @param year
     * @param month
     * @param total_dev
     * @param total_net
     * @param ap_company
     */
    public Payroll(int id_name, int id_batch, String nif, int year, int month, int day, double total_dev, double total_net, double ap_company) {
        this.emp_name = null;
        this.id_name = id_name;
        this.id_batch = id_batch;
        this.nif = nif;
        this.year = year;
        this.month = month;
        this.day = day;
        this.total_dev = total_dev;
        this.total_net = total_net;
        this.ap_company = ap_company;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public int getId_name() {
        return id_name;
    }

    public int getId_batch() {
        return id_batch;
    }

    public String getNif() {
        return nif;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() { return day; }

    public double getTotal_dev() {
        return total_dev;
    }

    public double getAp_company() {
        return ap_company;
    }

    public double getTotal_net() {
        return total_net;
    }

    public void setEmp_name(String emp_name){ this.emp_name = emp_name; }

    public void setId_name(int id_name) {
        this.id_name = id_name;
    }

    public void setId_remesa(int id_remesa) {
        this.id_batch = id_remesa;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) { this.day = day; }

    public void setTotal_net(double total_net) {
        this.total_net = total_net;
    }

    public void setTotal_dev(double total_dev) {
        this.total_dev = total_dev;
    }

    public void setAp_company(double ap_company) {
        this.ap_company = ap_company;
    }

    @Override
    public String toString() {
        return emp_name + " - " + day + "/" + month + "/" + year;
    }
}
