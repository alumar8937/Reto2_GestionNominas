package controller.database;

import model.Payroll;
import model.PayrollBatch;
import userconfig.UserconfigManager;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @Author Pedro Marín Sanchis
 * Manages connection to the database.
 */
public class PayrollDBController {

    private static Connection connection = null;

    public static void establishConnection() { // Author: Pedro Marín Sanchis
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://"+UserconfigManager.getINSTANCE().getIP()+"/"+UserconfigManager.getINSTANCE().getDatabase();
            Properties props = new Properties();
            props.setProperty("user", UserconfigManager.getINSTANCE().getUser());
            props.setProperty("password", UserconfigManager.getINSTANCE().getPassword());
            props.setProperty("ssl", "false");
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() { // Author: Pedro Marín Sanchis
        if (connection == null) {
            establishConnection();
        }
        return connection;
    }

    public static ArrayList<PayrollBatch> getBatch() { // Author: Javier Blasco Gómez // Return a list of batch
        ArrayList<PayrollBatch> batches = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM remesa"
            );
            while (resultSet.next()) {
                batches.add(new PayrollBatch(resultSet.getInt(1),resultSet.getBoolean(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }

    public static void setComboBatchItems(JComboBox<PayrollBatch> comboBox){
        ArrayList<PayrollBatch> batchArray = getBatch();
        comboBox.removeAllItems();
        for (PayrollBatch batch: batchArray) {
            comboBox.addItem(batch);
        }
    }

    public static Integer createBatch() { // Author: Pedro Marín Sanchis // Returns newly created batch ID.
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(
                    "INSERT INTO remesa (aceptado) " +
                            "VALUES (FALSE);"
            );
            ResultSet resultSet = statement.executeQuery(
                    "SELECT MAX(id_remesa) FROM remesa"
            );
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteBatch(int batchID) { // Author: Pedro Marín Sanchis / David Serna Mateu
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(
                    "DELETE from remesa where id_remesa="+batchID + ";"
            );
        } catch (SQLException e) {
            //TODO: Handle Exception.
        }
    }

    public static void setBatchAccepted(int batchID, boolean wasAccepted) { // Author: David Serna Mateu / Pedro Marín Sanchis
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(
                    "UPDATE remesa set aceptado=" + wasAccepted +
                            " where id_remesa=" + batchID + ";"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getPayrollsByBatchId(PayrollBatch batch) { // Author: David Serna Mateu
        ArrayList<Payroll> payrolls = new ArrayList<Payroll>();
        try{
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM nomina where id_remesa=" + batch.getID() + ";"
            );
            while(rs.next()) {
                payrolls.add(new Payroll(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(9), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8)));
            }
            batch.setPayrolls(getNameEmployeeByNif(payrolls));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Payroll> getNameEmployeeByNif(ArrayList<Payroll> payrolls) { // Author: David Serna Mateu
        try{
            Statement st = getConnection().createStatement();
            ResultSet rs;
            for(Payroll payroll : payrolls) {
                rs = st.executeQuery(
                        "SELECT nombre,apellido1,apellido2 FROM trabajador where nif='" + payroll.getNif() + "';"
                );
                if (rs.next()) {
                    payroll.setEmp_name(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payrolls;
    }

    public static void setComboPayrollItem(JComboBox combo, PayrollBatch batch) { // Author: Javier Blasco Gómez
        for (Payroll payroll : batch.getPayrolls()) {
            combo.addItem(payroll);
        }
    }

    public static DefaultListModel getListOfPayrolls(PayrollBatch batch) { // Author: David Serna Mateu
        DefaultListModel model = new DefaultListModel<>();
        for(Payroll payroll : batch.getPayrolls()){
            model.addElement(payroll);
        }
        return model;
    }
}