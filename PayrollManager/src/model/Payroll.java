package model;

import java.util.ArrayList;

/**
 * Represents a payroll with various attributes and related information.
 *
 * @author Javier Blasco Gómez
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
     * Constructs a Payroll object with the specified attributes.
     *
     * @param company      the company name
     * @param cif          the CIF (tax identification code) of the company
     * @param address      the address of the company
     * @param ccc          the CCC (bank account number) of the company
     * @param id_name      the ID of the name
     * @param id_batch     the ID of the batch
     * @param nif          the NIF (identification number) of the employee
     * @param year         the year of the payroll
     * @param month        the month of the payroll
     * @param day          the day of the payroll
     * @param total_dev    the total amount of development
     * @param total_deduc  the total amount of deduction
     * @param ap_company   the company's social security contribution
     */
    public Payroll(String company, String cif, String address, long ccc, int id_name, int id_batch, String nif, int year, int month, int day, double total_dev, double total_deduc, double ap_company) {
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
        this.ap_company = ap_company;
    }

    /**
     * Returns the company name.
     *
     * @return the company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Returns the address of the company.
     *
     * @return the address of the company
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the CIF (tax identification code) of the company.
     *
     * @return the CIF of the company
     */
    public String getCif() {
        return cif;
    }

    /**
     * Returns the CCC (bank account number) of the company.
     *
     * @return the CCC of the company
     */
    public long getCcc() {
        return ccc;
    }

    /**
     * Returns the professional group of the employee.
     *
     * @return the professional group of the employee
     */
    public String getProf_group() {
        return prof_group;
    }

    /**
     * Returns the social security number of the employee.
     *
     * @return the social security number of the employee
     */
    public long getNum_ss() {
        return num_ss;
    }

    /**
     * Returns the total deduction amount of the payroll.
     *
     * @return the total deduction amount
     */
    public double getTotal_deduc() {
        return total_deduc;
    }

    /**
     * Returns the employee name associated with the payroll.
     *
     * @return the employee name
     */
    public String getEmp_name() {
        return emp_name;
    }

    /**
     * Returns the ID of the name.
     *
     * @return the ID of the name
     */
    public int getId_name() {
        return id_name;
    }

    /**
     * Returns the ID of the batch.
     *
     * @return the ID of the batch
     */
    public int getId_batch() {
        return id_batch;
    }

    /**
     * Returns the NIF (identification number) of the employee.
     *
     * @return the NIF of the employee
     */
    public String getNif() {
        return nif;
    }

    /**
     * Returns the year of the payroll.
     *
     * @return the year of the payroll
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the month of the payroll.
     *
     * @return the month of the payroll
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the day of the payroll.
     *
     * @return the day of the payroll
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the total development amount of the payroll.
     *
     * @return the total development amount
     */
    public double getTotal_dev() {
        return total_dev;
    }

    /**
     * Returns the company's social security contribution.
     *
     * @return the company's social security contribution
     */
    public double getAp_company() {
        return ap_company;
    }

    /**
     * Returns the total net amount of the payroll.
     *
     * @return the total net amount
     */
    public double getTotal_net() {
        return total_net;
    }

    /**
     * Returns the list of perceptions associated with the payroll.
     *
     * @return the list of perceptions
     */
    public ArrayList<Perception> getPerceptions() {
        return perceptions;
    }

    /**
     * Returns the IRPF (income tax) of the payroll.
     *
     * @return the IRPF of the payroll
     */
    public double getIRPF() {
        return IRPF;
    }

    /**
     * Returns the ATEP (accidents at work) of the payroll.
     *
     * @return the ATEP of the payroll
     */
    public double getATEP() {
        return ATEP;
    }

    /**
     * Returns the list of contingencies related to the company.
     *
     * @return the list of company contingencies
     */
    public ArrayList<Contingency> getContingencies_Com() {
        return contingencies_Com;
    }

    /**
     * Returns the list of contingencies related to the employee.
     *
     * @return the list of employee contingencies
     */
    public ArrayList<Contingency> getContingencies_Emp() {
        return contingencies_Emp;
    }

    /**
     * Sets the employee name.
     *
     * @param emp_name the employee name to be set
     */
    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    /**
     * Sets the ID name.
     *
     * @param id_name the ID name to be set
     */
    public void setId_name(int id_name) {
        this.id_name = id_name;
    }

    /**
     * Sets the remesa ID.
     *
     * @param id_remesa the remesa ID to be set
     */
    public void setId_remesa(int id_remesa) {
        this.id_batch = id_remesa;
    }

    /**
     * Sets the NIF (Tax Identification Number).
     *
     * @param nif the NIF to be set
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Sets the year.
     *
     * @param year the year to be set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Sets the month.
     *
     * @param month the month to be set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Sets the day.
     *
     * @param day the day to be set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Sets the total net amount.
     *
     * @param total_net the total net amount to be set
     */
    public void setTotal_net(double total_net) {
        this.total_net = total_net;
    }

    /**
     * Sets the total dev amount.
     *
     * @param total_dev the total dev amount to be set
     */
    public void setTotal_dev(double total_dev) {
        this.total_dev = total_dev;
    }

    /**
     * Sets the ap company.
     *
     * @param ap_company the ap company to be set
     */
    public void setAp_company(double ap_company) {
        this.ap_company = ap_company;
    }

    /**
     * Sets the list of perceptions.
     *
     * @param perceptions the list of perceptions to be set
     */
    public void setPerceptions(ArrayList<Perception> perceptions) {
        this.perceptions = perceptions;
    }

    /**
     * Sets the address.
     *
     * @param address the address to be set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the CCC (Bank Account Code).
     *
     * @param ccc the CCC to be set
     */
    public void setCcc(long ccc) {
        this.ccc = ccc;
    }

    /**
     * Sets the CIF (Tax Identification Code).
     *
     * @param cif the CIF to be set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Sets the company.
     *
     * @param company the company to be set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Sets the batch ID.
     *
     * @param id_batch the batch ID to be set
     */
    public void setId_batch(int id_batch) {
        this.id_batch = id_batch;
    }

    /**
     * Sets the Social Security number.
     *
     * @param num_ss the Social Security number to be set
     */
    public void setNum_ss(long num_ss) {
        this.num_ss = num_ss;
    }

    /**
     * Sets the professional group.
     *
     * @param prof_group the professional group to be set
     */
    public void setProf_group(String prof_group) {
        this.prof_group = prof_group;
    }

    /**
     * Sets the total deductions amount.
     *
     * @param total_deduc the total deductions amount to be set
     */
    public void setTotal_deduc(double total_deduc) {
        this.total_deduc = total_deduc;
    }

    /**
     * Sets the IRPF (Personal Income Tax) amount.
     *
     * @param IRPF the IRPF amount to be set
     */
    public void setIRPF(double IRPF) {
        this.IRPF = IRPF;
    }

    /**
     * Sets the ATEP (Accidents at Work and Occupational Diseases) amount.
     *
     * @param ATEP the ATEP amount to be set
     */
    public void setATEP(double ATEP) {
        this.ATEP = ATEP;
    }

    /**
     * Sets the list of employee contingencies.
     *
     * @param contingencies_Emp the list of employee contingencies to be set
     */
    public void setContingencies_Emp(ArrayList<Contingency> contingencies_Emp) {
        this.contingencies_Emp = contingencies_Emp;
    }

    /**
     * Sets the list of company contingencies.
     *
     * @param contingencies_Com the list of company contingencies to be set
     */
    public void setContingencies_Com(ArrayList<Contingency> contingencies_Com) {
        this.contingencies_Com = contingencies_Com;
    }


    /**
     * Returns a string representation of the payroll object.
     *
     * @return a string representation of the payroll
     */
    @Override
    public String toString() {
        return emp_name + " - " + day + "/" + month + "/" + year;
    }

}
