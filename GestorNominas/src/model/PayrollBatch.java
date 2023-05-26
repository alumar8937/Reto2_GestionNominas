package model;

import java.util.ArrayList;

/**
 * @Author Pedro Marín Sanchis
 * Batch of Payrolls.
 */
public class PayrollBatch {
    private ArrayList<Payroll> payrolls = new ArrayList<Payroll>();
    private int ID = 0;
    private boolean wasAccepted = false;

    public static ArrayList<Payroll> getPayrolls() {
        return payrolls;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean wasAccepted() {
        return wasAccepted;
    }
}
