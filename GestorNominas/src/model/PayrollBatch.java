package model;

import java.util.ArrayList;

/**
 * @Author Pedro Marín Sanchis
 * Batch of Payrolls.
 */
public class PayrollBatch {
    private static ArrayList<Payroll> payrolls = new ArrayList<Payroll>();
    private static int ID = 0;
    private static boolean wasAccepted = false;

    public static ArrayList<Payroll> getPayrolls() {
        return payrolls;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        PayrollBatch.ID = ID;
    }

    public static boolean wasAccepted() {
        return wasAccepted;
    }
}
