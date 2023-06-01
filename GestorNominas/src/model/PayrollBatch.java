package model;

import controller.database.PayrollDBController;

import java.util.ArrayList;

/**
 * Represents a batch of payrolls.
 *
 * @author Pedro Marín Sanchis
 */
public class PayrollBatch {
    private ArrayList<Payroll> payrolls = new ArrayList<Payroll>();
    private int ID = 0;
    private boolean wasAccepted = false;

    /**
     * Returns the list of payrolls in the batch.
     *
     * @return the list of payrolls
     */
    public ArrayList<Payroll> getPayrolls() {
        return payrolls;
    }

    /**
     * Returns the ID of the payroll batch.
     *
     * @return the ID of the payroll batch
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the payroll batch.
     *
     * @param ID the ID of the payroll batch
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Checks if the payroll batch was accepted.
     *
     * @return true if the payroll batch was accepted, false otherwise
     */
    public boolean wasAccepted() {
        return wasAccepted;
    }

    /**
     * Sets the list of payrolls in the batch.
     *
     * @param payrolls the list of payrolls
     */
    public void setPayrolls(ArrayList<Payroll> payrolls) {
        this.payrolls = payrolls;
    }

    /**
     * Creates a new instance of PayrollBatch.
     *
     * @param ID          the ID of the payroll batch
     * @param wasAccepted true if the payroll batch was accepted, false otherwise
     */
    public PayrollBatch(int ID, boolean wasAccepted) {
        this.ID = ID;
        this.wasAccepted = wasAccepted;
    }

    /**
     * Returns a string representation of the payroll batch object.
     *
     * @return a string representation of the payroll batch
     */
    @Override
    public String toString() {
        return "ID: " + ID + "    [" + payrolls.size() + "]";
    }
}
