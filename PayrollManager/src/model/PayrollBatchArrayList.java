package model;

import java.util.ArrayList;

/**
 * Contains an ArrayList of PayrollBatch.
 * Extends ArrayList<PayrollBatch>.
 *
 * @author Pedro Marín Sanchis
 */
public class PayrollBatchArrayList extends ArrayList<PayrollBatch> {
    private ArrayList<PayrollBatch> batches = new ArrayList<>();

    /**
     * Constructs a PayrollBatchArrayList with the specified list of batches.
     *
     * @param batches the list of PayrollBatch to be set
     */
    public PayrollBatchArrayList(ArrayList<PayrollBatch> batches) {
        this.batches = batches;
    }

    /**
     * Sets the list of batches.
     *
     * @param batches1 the list of PayrollBatch to be set
     */
    public void setBatches(ArrayList<PayrollBatch> batches1) {
        batches = batches1;
    }

    /**
     * Returns the list of batches.
     *
     * @return the list of PayrollBatch
     */
    public ArrayList<PayrollBatch> getBatches() {
        return batches;
    }
}
