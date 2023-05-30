package controller.database;

import model.*;
import userconfig.UserconfigManager;
import view.payroll.EditPayrollWindow;

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

    public static ArrayList<PayrollBatch> getBatchAceptOrNot(boolean acept) { // Author: Javier Blasco Gómez // Return a list of batch
        ArrayList<PayrollBatch> batches = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM remesa where aceptado=" + acept
            );
            while (resultSet.next()) {
                batches.add(new PayrollBatch(resultSet.getInt(1),resultSet.getBoolean(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }

    public static void setComboBatchItems(JComboBox<PayrollBatch> comboBox, boolean acept){ // Author: Javier Blasco Gómez
        ArrayList<PayrollBatch> batchArray = getBatchAceptOrNot(acept);
        comboBox.removeAllItems();
        for (PayrollBatch batch: batchArray) {
            comboBox.addItem(batch);
        }
    }

    public static Integer createPayroll() { // Author: Javier Blasco Gómez
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("INSERT INTO nomina ()");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public static void deletePayroll(int payrollID) { // Author: David Serna Mateu
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(
                    "DELETE from nomina where id_nom="+payrollID + ";"
            );
        } catch (SQLException e) {
            //TODO: Handle Exception.
        }
    }

    public static void deletePayrollsOfBatch(int batchID) { // Author: David Serna Mateu
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(
                    "DELETE from nomina where id_remesa="+batchID + ";"
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
            Statement st2 = getConnection().createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM nomina where id_remesa=" + batch.getID() + ";"
            );
            ResultSet rs_company = st2.executeQuery("SELECT * FROM datos_empresa;");
            rs_company.next();
            String name_company = rs_company.getString(2);
            String cif_company = rs_company.getString(1);
            String address_company = rs_company.getString(3);
            long ccc_company = rs_company.getLong(4);
            rs_company.close();
            while(rs.next()) {
                payrolls.add(new Payroll(name_company, cif_company, address_company, ccc_company, rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(9), rs.getDouble(6), rs.getDouble(10), rs.getDouble(7), rs.getDouble(8)));
            }
            batch.setPayrolls(getContingenciesByNif(getRetentionsByNIF(getPerceptionsByNIF(getNameEmployeeByNif(payrolls)))));

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
                        "SELECT nombre,apellido1,apellido2,cod_gr,numero_ss FROM trabajador where nif='" + payroll.getNif() + "';"
                );
                if (rs.next()) {
                    payroll.setEmp_name(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    payroll.setProf_group(rs.getString(4));
                    payroll.setNum_ss(rs.getLong(5));
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

    private static ArrayList<Payroll> getPerceptionsByNIF(ArrayList<Payroll> payrolls) { // Author: David Serna Mateu
        ArrayList<Perception> perceptions = new ArrayList<>();
        try{
            Statement st = getConnection().createStatement();
            Statement st2 = getConnection().createStatement();
            ResultSet rs;
            ResultSet rs2;
            for(Payroll payroll : payrolls) {
                rs = st.executeQuery(
                        "SELECT cod_p, quant FROM percepcion_ind where nif='" + payroll.getNif() + "';"
                );
                while(rs.next()) {
                    perceptions.add(new Perception(rs.getString(1), rs.getFloat(2)));
                }
                rs2 = st2.executeQuery(
                        "select * from (select pg.cod_p, pg.quant from percepcion_gr pg inner join trabajador t using (cod_gr) where t.nif='" +
                                 payroll.getNif() + "') as t;"
                );
                while(rs2.next()) {
                    perceptions.add(new Perception(rs2.getString(1), rs2.getFloat(2)));
                }
                payroll.setPerceptions(perceptions);
                perceptions.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payrolls;
    }

    private static ArrayList<Payroll> getRetentionsByNIF(ArrayList<Payroll> payrolls) { // Author: David Serna Mateu
        ArrayList<Retention> retentions = new ArrayList<>();
        try{
            Statement st = getConnection().createStatement();
            ResultSet rs;
            for(Payroll payroll : payrolls) {
                rs = st.executeQuery(
                        "SELECT cod_r, quant FROM retencion_ind where nif='" + payroll.getNif() + "';"
                );
                while(rs.next()) {
                    retentions.add(new Retention(rs.getString(1), rs.getFloat(2)));
                }
                payroll.setRetentions(retentions);
                retentions.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payrolls;
    }

    private static ArrayList<Payroll> getContingenciesByNif(ArrayList<Payroll> payrolls) { // Author: David Serna Mateu / Javier Blasco Gómez
        ArrayList<Contingencies> contingencies_Emp = new ArrayList<>();
        ArrayList<Contingencies> contingencies_Com = new ArrayList<>();
        try{
            Statement st = getConnection().createStatement();
            Statement st2 = getConnection().createStatement();
            Statement st3 = getConnection().createStatement();
            ResultSet rs;
            ResultSet rs_aux;
            ResultSet rs2;
            ResultSet rs_aux2;
            for(Payroll payroll : payrolls) {
                rs_aux2 = st3.executeQuery("SELECT indefinido from trabajador where nif='" + payroll.getNif() + "';");
                rs_aux2.next();
                if(rs_aux2.getBoolean(1)) {
                    rs2 = st2.executeQuery(
                            "SELECT * FROM contingencia_t ct where not cod_c='Desempleo2'"
                    );
                    while(rs2.next()) {
                        contingencies_Emp.add(new Contingencies(rs2.getString(1), rs2.getFloat(2)));
                    }
                }else{
                    rs2 = st2.executeQuery(
                            "SELECT * FROM contingencia_t ct where not cod_c='Desempleo'"
                    );
                    while(rs2.next()) {
                        contingencies_Emp.add(new Contingencies(rs2.getString(1), rs2.getFloat(2)));
                    }
                }
                payroll.setContingencies_Emp(contingencies_Emp);
                contingencies_Emp.clear();
            }
            for(Payroll payroll : payrolls) {
                rs_aux = st3.executeQuery("SELECT indefinido from trabajador where nif='" + payroll.getNif() + "';");
                rs_aux.next();
                if(rs_aux.getBoolean(1)) {
                    rs = st.executeQuery(
                            "SELECT * FROM contingencia_e ce where not cod_c='Desempleo2'"
                    );
                    while(rs.next()) {
                        contingencies_Com.add(new Contingencies(rs.getString(1), rs.getFloat(2)));
                    }
                }else{
                    rs = st.executeQuery(
                            "SELECT * FROM contingencia_e ce where not cod_c='Desempleo'"
                    );
                    while(rs.next()) {
                        contingencies_Com.add(new Contingencies(rs.getString(1), rs.getFloat(2)));
                    }
                }
                payroll.setContingencies_Com(contingencies_Com);
                contingencies_Com.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payrolls;
    }
}