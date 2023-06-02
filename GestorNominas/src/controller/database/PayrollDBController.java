package controller.database;

import model.Contingency;
import model.Department;
import model.Employee;
import model.Payroll;
import model.PayrollBatch;
import model.Perception;
import userconfig.UserconfigManager;

import javax.swing.JComboBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Pedro Marín Sanchis
 * Manages connection to the database.
 */
public class PayrollDBController {

    private static Connection connection = null;

    /**
     * Establishes a connection with a PostgreSQL database.
     * It uses the user configuration stored in UserconfigManager to establish the connection.
     * The method dynamically loads the database driver and creates the connection.
     */
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

    /**
     * Returns a connection to a PostgreSQL database.
     * If the connection is not already established, it calls the establishConnection() method to establish it.
     * Returns the existing connection if it has already been established.
     *
     * @return the connection to the PostgreSQL database
     */
    private static Connection getConnection() { // Author: Pedro Marín Sanchis
        if (connection == null) {
            establishConnection();
        }
        return connection;
    }

    /**
     * Retrieves a list of employees from the database.
     * Executes a SELECT query to retrieve the NIF, name, and surnames of employees from the "trabajador" table.
     * Creates an Employee object for each row in the ResultSet and adds it to the list.
     *
     * @return an ArrayList containing the retrieved employees
     */
    public static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try{
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT nif, nombre, apellido1, apellido2 from trabajador;");
            while(rs.next()){
                String name = (rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                employees.add(new Employee(name, rs.getString(1)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    /**
     * Retrieves a list of payrolls for a specific employee based on their NIF (identification number).
     * Filters the payrolls based on whether they were accepted or not (wasAccepted parameter).
     * Calls the getBatches() method to retrieve the batches based on the acceptance status.
     * Iterates through the payrolls in each batch and adds the ones matching the employee's NIF to the list.
     *
     * @param NIF           the NIF of the employee
     * @param wasAccepted   the flag indicating whether the payrolls were accepted or not
     * @return an ArrayList containing the retrieved payrolls for the employee
     */
    public static ArrayList<Payroll> getPayrollsByEmployeeNIF(String NIF, boolean wasAccepted) { // Author: Javier Blasco Gómez / David Serna Mateu / Pedro Marín Sanchis
        ArrayList<PayrollBatch> batches = getBatches(wasAccepted);
        ArrayList<Payroll> payrolls = new ArrayList<>();
        for (PayrollBatch pb: batches) {
            for(Payroll p : pb.getPayrolls()){
                if(p.getNif().equalsIgnoreCase(NIF)){payrolls.add(p);}
            }
        }
        return payrolls;
    }


    public static ArrayList<Employee> getEmployessByDepartmentID(String ID){ // Author: Javier Blasco Gómez
        ArrayList<Employee> employees = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT nif, nombre, apellido1, apellido2 from trabajador t where t.cod_dep ='" + ID + "';");
            while(rs.next()){
                String name = (rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                employees.add(new Employee(name, rs.getString(1)));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static ArrayList<Payroll> getPayrollsByDepartmentID(String ID, boolean wasAccepted) { // Author: Javier Blasco Gómez
        ArrayList<PayrollBatch> batches = getBatches(wasAccepted);
        ArrayList<Employee> employees = getEmployessByDepartmentID(ID);
        ArrayList<Payroll> payrolls = new ArrayList<>();
        for (PayrollBatch pb:batches) {
            for (Payroll p:pb.getPayrolls()) {
                for (Employee e:employees) {
                    if (p.getNif().equalsIgnoreCase(e.getNIF()) && p.getNif().equalsIgnoreCase(e.getNIF())){
                        payrolls.add(p);
                    }
                }
            }
        }
        return payrolls;
    }

    public static ArrayList<PayrollBatch> getBatches(boolean wasAccepted) { // Author: Javier Blasco Gómez // Return a list of batch
        ArrayList<PayrollBatch> batches = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM remesa where aceptado=" + wasAccepted
            );
            while (resultSet.next()) {
                PayrollBatch batch = new PayrollBatch(resultSet.getInt(1),resultSet.getBoolean(2));
                setPayrollsByBatch(batch);
                batches.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }

    /**
     * Creates a new payroll entry in the database.
     * Inserts the payroll details into the "nomina" table.
     *
     * @param payroll   the Payroll object representing the payroll to be created
     */
    public static void createPayroll(Payroll payroll) { // Author: Javier Blasco Gómez
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate("INSERT INTO nomina ()");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyPayroll(Payroll payroll) { // Author: David Serna Mateu
        try {
            Statement statementUpdatePayrollData = getConnection().createStatement();
            statementUpdatePayrollData.executeUpdate(
                    "UPDATE nomina set anyo=" + payroll.getYear() + ", mes=" + payroll.getMonth() + ", total_dev="
                            + payroll.getTotal_dev() + ", total_neto=" + payroll.getTotal_net() + ", ap_empresa="
                            + payroll.getAp_company() + ", dia=" + payroll.getDay() + ", total_deduc=" + payroll.getTotal_deduc()
                            + " where id_nom=" + payroll.getId_name() + ";"
            );

            Statement statementUpdateWorkerData = getConnection().createStatement();
            String[] nombres = payroll.getEmp_name().split(" ");
            if(nombres.length == 3){
                statementUpdateWorkerData.executeUpdate(
                        "UPDATE trabajador set cod_gr='" + payroll.getProf_group() + "', nombre='" + nombres[0] +
                                "', apellido1='" + nombres[1] + "', apellido2='" + nombres[2] + "' where nif='" + payroll.getNif() + "';"
                );
            }else if(nombres.length == 4 ){
                statementUpdateWorkerData.executeUpdate(
                        "UPDATE trabajador set cod_gr='" + payroll.getProf_group() + "', nombre='" + nombres[0] + " " + nombres[1] +
                                "', apellido1='" + nombres[2] + "', apellido2='" + nombres[3] + "' where nif='" + payroll.getNif() + "';"
                );
            }else if(nombres.length == 2){
                statementUpdateWorkerData.executeUpdate(
                        "UPDATE trabajador set cod_gr='" + payroll.getProf_group() + "', nombre='" + nombres[0] +
                                "', apellido1='" + nombres[1] + "', apellido2='' where nif='" + payroll.getNif() + "';"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createNewBatch() { // Author: Pedro Marín Sanchis // Returns newly created batch ID.
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(
                    "INSERT INTO remesa (aceptado) " +
                            "VALUES (FALSE);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void setPayrollsByBatch(PayrollBatch batch) { // Author: David Serna Mateu
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
                Payroll payroll = new Payroll(name_company, cif_company, address_company, ccc_company, rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getInt(9), rs.getDouble(6), rs.getDouble(10), rs.getDouble(8));
                payroll.setTotal_net(payroll.getTotal_dev() - payroll.getTotal_deduc());
                payrolls.add(payroll);
            }
            setContingenciesToEachPayroll(payrolls);
            setRetentionsToEachPayroll(payrolls);
            setPerceptionsToEachPayroll(payrolls);
            setNameEmployeeToEachPayroll(payrolls);
            batch.setPayrolls(payrolls);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Payroll> setNameEmployeeToEachPayroll(ArrayList<Payroll> payrolls) { // Author: David Serna Mateu
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

    private static ArrayList<Payroll> setPerceptionsToEachPayroll(ArrayList<Payroll> payrolls) { // Author: David Serna Mateu
        try{
            Statement st = getConnection().createStatement();
            Statement st2 = getConnection().createStatement();
            ResultSet rs;
            ResultSet rs2;
            for(Payroll payroll : payrolls) {
                ArrayList<Perception> perceptions = new ArrayList<>();
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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payrolls;
    }

    private static ArrayList<Payroll> setRetentionsToEachPayroll(ArrayList<Payroll> payrolls) { // Author: David Serna Mateu
        try{
            Statement st = getConnection().createStatement();
            ResultSet rs;
            for(Payroll payroll : payrolls) {
                rs = st.executeQuery(
                        "SELECT cod_r, quant FROM retencion_ind where nif='" + payroll.getNif() + "';"
                );
                while(rs.next()) {
                    if(rs.getString("cod_r").equalsIgnoreCase("IRPF")){payroll.setIRPF(rs.getDouble("quant"));}
                    else if(rs.getString("cod_r").equalsIgnoreCase("ATEP")){payroll.setATEP(rs.getDouble("quant"));}
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payrolls;
    }

    private static ArrayList<Payroll> setContingenciesToEachPayroll(ArrayList<Payroll> payrolls) { // Author: David Serna Mateu / Javier Blasco Gómez
        try{
            Statement st = getConnection().createStatement();
            Statement st2 = getConnection().createStatement();
            Statement st3 = getConnection().createStatement();
            ResultSet rs;
            ResultSet rs_aux;
            ResultSet rs2;
            ResultSet rs_aux2;
            for(Payroll payroll : payrolls) {
                ArrayList<Contingency> contingencies_Emp = new ArrayList<>();
                rs_aux2 = st3.executeQuery("SELECT indefinido from trabajador where nif='" + payroll.getNif() + "';");
                rs_aux2.next();
                if(rs_aux2.getBoolean(1)) {
                    rs2 = st2.executeQuery(
                            "SELECT * FROM contingencia_t ct where not cod_c='Desempleo2'"
                    );
                    while(rs2.next()) {
                        contingencies_Emp.add(new Contingency(rs2.getString(1), rs2.getFloat(2)));
                    }
                }else{
                    rs2 = st2.executeQuery(
                            "SELECT * FROM contingencia_t ct where not cod_c='Desempleo'"
                    );
                    while(rs2.next()) {
                        contingencies_Emp.add(new Contingency(rs2.getString(1), rs2.getFloat(2)));
                    }
                }
                payroll.setContingencies_Emp(contingencies_Emp);
            }
            for(Payroll payroll : payrolls) {
                ArrayList<Contingency> contingencies_Com = new ArrayList<>();
                rs_aux = st3.executeQuery("SELECT indefinido from trabajador where nif='" + payroll.getNif() + "';");
                rs_aux.next();
                if(rs_aux.getBoolean(1)) {
                    rs = st.executeQuery(
                            "SELECT * FROM contingencia_e ce where not cod_c='Desempleo2'"
                    );
                    while(rs.next()) {
                        contingencies_Com.add(new Contingency(rs.getString(1), rs.getFloat(2)));
                    }
                }else{
                    rs = st.executeQuery(
                            "SELECT * FROM contingencia_e ce where not cod_c='Desempleo'"
                    );
                    while(rs.next()) {
                        contingencies_Com.add(new Contingency(rs.getString(1), rs.getFloat(2)));
                    }
                }
                payroll.setContingencies_Com(contingencies_Com);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payrolls;
    }

    public static ArrayList<String> getProfesionalGroup() {
        ArrayList<String> profesionalGroups = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT cod_gr FROM grupo_profesional"
            );
            while (resultSet.next()) {
                profesionalGroups.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesionalGroups;
    }

    public static void setComboProfesionalGroup(JComboBox comboBox) {
        ArrayList<String> profesionalGroups = getProfesionalGroup();
        comboBox.removeAllItems();
        for (String profesionalGroup: profesionalGroups) {
            comboBox.addItem(profesionalGroup);
        }
    }

    public static ArrayList<Department> getDepartments() {
        ArrayList<Department> departments = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from departamento");
            while (resultSet.next()) {
                departments.add(new Department(resultSet.getString(2),resultSet.getString(1)));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public static boolean calculateAllPayrolls() {
        try{
            Statement st = getConnection().createStatement();
            st.executeUpdate("update nomina set total_neto = (total_dev - total_deduc);");

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}