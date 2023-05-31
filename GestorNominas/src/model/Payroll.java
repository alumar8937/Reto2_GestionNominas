package model;

import java.util.ArrayList;

/**
 * @Author Javier Blasco Gómez
 */
public class Payroll {
    String company;
    String cif;
    String address;
    long ccc;
    String emp_name;
    long num_ss;
    String prof_group;
    int id_name;
    int id_batch;
    String nif;
    int year;
    int month;
    int day;
    double total_dev;
    double total_deduc;
    double total_net;
    double ap_company;
    ArrayList<Perception> perceptions;
    double IRPF;
    double ATEP;
    ArrayList<Contingency> contingencies_Emp;
    ArrayList<Contingency> contingencies_Com;

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
    public Payroll(String company, String cif, String address, long ccc, int id_name, int id_batch, String nif, int year, int month, int day, double total_dev, double total_deduc, double total_net, double ap_company) {
        this.company = company;
        this.cif = cif;
        this.address = address;
        this.ccc = ccc;
        this.emp_name = null;
        this.id_name = id_name;
        this.id_batch = id_batch;
        this.nif = nif;
        this.year = year;
        this.month = month;
        this.day = day;
        this.total_dev = total_dev;
        this.total_deduc = total_deduc;
        this.total_net = total_net;
        this.ap_company = ap_company;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getCif() {
        return cif;
    }

    public long getCcc() {
        return ccc;
    }

    public String getProf_group() {
        return prof_group;
    }

    public long getNum_ss() {
        return num_ss;
    }

    public double getTotal_deduc() {
        return total_deduc;
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

    public ArrayList<Perception> getPerceptions() {
        return perceptions;
    }

    public ArrayList<Retention> getRetentions() {
        return retentions;
    }

    public double getIRPF() {
        return IRPF;
    }

    public double getATEP() {
        return ATEP;
    }

    public ArrayList<Contingency> getContingencies_Com() {
        return contingencies_Com;
    }

    public ArrayList<Contingency> getContingencies_Emp() {
        return contingencies_Emp;
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

    public void setPerceptions(ArrayList<Perception> perceptions) {
      this.perceptions = perceptions;
    }

    public void setRetentions(ArrayList<Retention> retentions) {
        this.retentions = retentions;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCcc(long ccc) {
        this.ccc = ccc;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setId_batch(int id_batch) {
        this.id_batch = id_batch;
    }

    public void setNum_ss(long num_ss) {
        this.num_ss = num_ss;
    }

    public void setProf_group(String prof_group) {
        this.prof_group = prof_group;
    }

    public void setTotal_deduc(double total_deduc) {
        this.total_deduc = total_deduc;
    }

    public void setIRPF(double IRPF) {
        this.IRPF = IRPF;
    }

    public void setATEP(double ATEP) {
        this.ATEP = ATEP;
    }

    public void setContingencies_Emp(ArrayList<Contingency> contingencies_Emp) {
        this.contingencies_Emp = contingencies_Emp;
    }

    public void setContingencies_Com(ArrayList<Contingency> contingencies_Com) {
        this.contingencies_Com = contingencies_Com;
    }

    @Override
    public String toString() {
        return emp_name + " - " + day + "/" + month + "/" + year;
    }
}
