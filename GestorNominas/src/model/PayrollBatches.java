package model;

import java.util.ArrayList;

/**
 * @Author Pedro Marín Sanchis
 * Contains an ArrayList of PayrollBatch
 */
public class PayrollBatches {
    private static ArrayList<PayrollBatch> batches = new ArrayList<PayrollBatch>();

    public ArrayList<PayrollBatch> getPayrollBatches() {
        return batches;
    }
}
