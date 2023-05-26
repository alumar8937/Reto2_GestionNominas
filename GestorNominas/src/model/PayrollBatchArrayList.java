package model;

import java.util.ArrayList;

/**
 * @Author Pedro Marín Sanchis
 * Contains an ArrayList of PayrollBatch
 */
public class PayrollBatchArrayList extends ArrayList<PayrollBatch> {
    public ArrayList<PayrollBatch> batches = new ArrayList<>();

    public PayrollBatchArrayList(ArrayList<PayrollBatch> batches){
        this.batches = batches;
    }
    public void setBatches(ArrayList<PayrollBatch> batches1){
        batches = batches1;
    }
    public ArrayList<PayrollBatch> getBatches() {
        return batches;
    }
}
