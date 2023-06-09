package controller.database;

import model.Contingency;
import model.Department;
import model.Employee;
import model.Payroll;
import model.PayrollBatch;
import model.Perception;
import userconfig.UserConfigManager;

import java.sql.*;
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
            String url = "jdbc:postgresql://"+ UserConfigManager.getINSTANCE().getIP()+"/"+ UserConfigManager.getINSTANCE().getDatabase();
            Properties props = new Properties();
            props.setProperty("user", UserConfigManager.getINSTANCE().getUser());
            props.setProperty("password", UserConfigManager.getINSTANCE().getPassword());
            props.setProperty("ssl", "false");
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException | ClassNotFoundException e) {
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
    public static Connection getConnection() { // Author: Pedro Marín Sanchis
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
    public static ArrayList<Employee> getEmployees() { // Author: David Serna Mateu
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

    /**
     * Retrieves a list of employees belonging to a specific department based on its ID.
     * Executes a SELECT query to retrieve the NIF, name, and surnames of employees from the "trabajador" table
     * who belong to the department with the specified ID.
     * Creates an Employee object for each row in the ResultSet and adds it to the list.
     *
     * @param ID the ID of the department
     * @return an ArrayList containing the retrieved employees belonging to the department
     */
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

    /**
     * Retrieves a list of payrolls for employees belonging to a specific department based on its ID.
     * Filters the payrolls based on whether they were accepted or not (wasAccepted parameter).
     * Calls the getBatches() method to retrieve the payroll batches based on the acceptance status.
     * Calls the getEmployeesByDepartmentID() method to retrieve the employees belonging to the department.
     * Iterates through the payrolls in each batch and checks if they belong to any of the retrieved employees.
     * Adds the matching payrolls to the list.
     *
     * @param ID            the ID of the department
     * @param wasAccepted   the flag indicating whether the payrolls were accepted or not
     * @return an ArrayList containing the retrieved payrolls for employees belonging to the department
     */
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

    /**
     * Retrieves a list of payroll batches based on the acceptance status.
     * Filters the batches based on whether they were accepted or not (wasAccepted parameter).
     * Executes a SELECT query to retrieve the batches from the "remesa" table with the specified acceptance status.
     * Creates a PayrollBatch object for each row in the ResultSet and adds it to the list.
     * Calls the setPayrollsByBatch() method to set the payrolls for each batch.
     *
     * @param wasAccepted   the flag indicating whether the batches were accepted or not
     * @return an ArrayList containing the retrieved payroll batches
     */
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
            String query = "INSERT INTO nomina (id_remesa, nif, anyo, mes, total_dev, total_neto, ap_empresa, dia, total_deduc) " +
                    "VALUES (?, ?, ?, ?, ?, null, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, payroll.getId_batch());
            statement.setString(2, payroll.getNif());
            statement.setInt(3, payroll.getYear());
            statement.setInt(4, payroll.getMonth());
            statement.setDouble(5, payroll.getTotal_dev());
            statement.setDouble(6, payroll.getAp_company());
            statement.setInt(7, payroll.getDay());
            statement.setDouble(8, payroll.getTotal_deduc());

            statement.executeUpdate();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifies an existing payroll entry in the database.
     * Updates the payroll details in the "nomina" table and the associated worker details in the "trabajador" table.
     * Checks the length of the name to see if it has the two surnames or if it has a compound name
     *
     * @param payroll the Payroll object representing the modified payroll data
     */
    public static void modifyPayroll(Payroll payroll) { // Author: David Serna Mateu
        try {
            Statement statementUpdatePayrollData = getConnection().createStatement();
            statementUpdatePayrollData.executeUpdate(
                    "UPDATE nomina set anyo=" + payroll.getYear() + ", mes=" + payroll.getMonth() + ", total_dev="
                            + payroll.getTotal_dev() + ", total_neto=null" + ", ap_empresa="
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

    /**
     * Creates a new batch entry in the database with a default acceptance status of "FALSE".
     * Inserts the batch details into the "remesa" table.
     * Returns the ID of the newly created batch.
     *
     */
    public static void createNewBatch() { // Author: Pedro Marín Sanchis
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

    /**
     * Deletes a batch entry from the database based on the provided batch ID.
     * Removes the batch record from the "remesa" table.
     *
     * @param batchID   the ID of the batch to be deleted
     */
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

    /**
     * Deletes a payroll entry from the database based on the provided payroll ID.
     * Removes the payroll record from the "nomina" table.
     *
     * @param payrollID   the ID of the payroll to be deleted
     */
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

    /**
     * Deletes all payroll entries associated with a specific batch from the database.
     * Removes the payroll records from the "nomina" table based on the provided batch ID.
     *
     * @param batchID   the ID of the batch whose payrolls are to be deleted
     */
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

    /**
     * Updates the acceptance status of a batch in the database based on the provided batch ID.
     * Sets the acceptance status of the batch in the "remesa" table.
     *
     * @param batchID      the ID of the batch to update
     * @param wasAccepted  the new acceptance status of the batch
     */
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

    /**
     * Sets the list of payrolls for a specific batch by retrieving payroll data from the database.
     * Populates the payrolls list of the provided PayrollBatch object with Payroll objects.
     *
     * @param batch   the PayrollBatch object for which to set the payrolls
     */
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

    /**
     * Sets the name, professional group, and social security number for each payroll in the provided list of payrolls.
     * Retrieves employee data from the database based on the NIF (tax identification number) of each payroll.
     *
     * @param payrolls   the list of payrolls for which to set the employee data
     * @return the updated list of payrolls with employee data set
     */
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

    /**
     * Sets the perceptions (individual and group) for each payroll in the provided list of payrolls.
     * Retrieves perception data from the database based on the NIF (tax identification number) of each payroll.
     *
     * @param payrolls   the list of payrolls for which to set the perceptions
     * @return the updated list of payrolls with perceptions set
     */
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

    /**
     * Sets the retentions for each payroll in the provided list of payrolls.
     * Retrieves retention data from the database based on the NIF (tax identification number) of each payroll.
     *
     * @param payrolls   the list of payrolls for which to set the retentions
     * @return the updated list of payrolls with retentions set
     */
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

    /**
     * Sets the contingencies for each payroll in the provided list of payrolls.
     * Retrieves contingency data from the database based on the NIF (tax identification number) of each payroll.
     *
     * @param payrolls   the list of payrolls for which to set the contingencies
     * @return the updated list of payrolls with contingencies set
     */
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
                            "SELECT * FROM contingencia_t ct where not cod_c='Desempleo2';"
                    );
                    while(rs2.next()) {
                        contingencies_Emp.add(new Contingency(rs2.getString(1), rs2.getFloat(2)));
                    }
                }else{
                    rs2 = st2.executeQuery(
                            "SELECT * FROM contingencia_t ct where not cod_c='Desempleo';"
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
                            "SELECT * FROM contingencia_e ce where not cod_c='Desempleo2';"
                    );
                    while(rs.next()) {
                        contingencies_Com.add(new Contingency(rs.getString(1), rs.getFloat(2)));
                    }
                }else{
                    rs = st.executeQuery(
                            "SELECT * FROM contingencia_e ce where not cod_c='Desempleo';"
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

    /**
     * Retrieves the professional groups from the database.
     *
     * @return the list of professional groups
     */
    public static ArrayList<String> getProfesionalGroup() { // Author : Javier Blasco Gómez
        ArrayList<String> profesionalGroups = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT cod_gr FROM grupo_profesional;"
            );
            while (resultSet.next()) {
                profesionalGroups.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesionalGroups;
    }

    /**
     * Retrieves the departments from the database.
     *
     * @return the list of departments
     */
    public static ArrayList<Department> getDepartments() { // Author: Javier Blasco Gómez
        ArrayList<Department> departments = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from departamento;");
            while (resultSet.next()) {
                departments.add(new Department(resultSet.getString(2),resultSet.getString(1)));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    /**
     * Retrieves company data.
     *
     * @return an ArrayList containing the company's CIF, name, address, and CCC (bank account number)
     */
    public static ArrayList<String> getCompanyData() { // Author : Javier Blasco Gómez
        ArrayList<String> companyData = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from datos_empresa;");
            resultSet.next();
            companyData.add(resultSet.getString(1));
            companyData.add(resultSet.getString(2));
            companyData.add(resultSet.getString(3));
            companyData.add(String.valueOf(resultSet.getLong(4)));
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return companyData;
    }

    /**
     * Retrieves employee data based on the provided NIF.
     *
     * @param Nif the NIF (identification number) of the employee
     * @return an ArrayList containing the employee's name, professional group, and social security number
     */
    public static ArrayList<String> getEmployeeData(String Nif) { // Author : Javier Blasco Gómez
        ArrayList<String> employeeData = new ArrayList<>();
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from trabajador where nif='" + Nif + "';");
            resultSet.next();
            String name = resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4);
            employeeData.add(name);
            employeeData.add(resultSet.getString(10));
            employeeData.add(String.valueOf(resultSet.getLong(6)));
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return employeeData;
    }

    /**
     * Calculates various totals for all payroll batches.
     *
     * @return true if the calculations are successful, false otherwise
     */
    public static boolean calculateAllPayrolls() { // Author : David Serna Mateu
        ArrayList<PayrollBatch> batchesHistory = getBatches(true);
        ArrayList<PayrollBatch> batches = getBatches(false);
        batches.addAll(batchesHistory);
        calculateEarningsTotal(batches);
        calculateDeductionTotal(batches);
        calculateNetIncome(batches);
        calculateCompanyTotal(batches);
        return true;
    }

    /**
     * Calculates the total earnings for each payroll in the list of batches.
     *
     * @param batches the list of payroll batches
     */
    private static void calculateEarningsTotal(ArrayList<PayrollBatch> batches) { // Author : David Serna Mateu
        for (PayrollBatch batch: batches) {
            for (Payroll payroll: batch.getPayrolls()) {
                double earningsTotal = 0;
                for (Perception perception: payroll.getPerceptions()) {
                    earningsTotal += perception.getQuant();
                }
                updateEarningsTotal(payroll, earningsTotal);
            }
        }
    }

    /**
     * Updates the earnings total for a payroll.
     *
     * @param payroll       the payroll object
     * @param earningsTotal the total earnings to be updated
     */
    private static void updateEarningsTotal(Payroll payroll, double earningsTotal) { // Author : David Serna Mateu
        try{
            Statement st = getConnection().createStatement();
            st.executeUpdate("update nomina set total_dev=" + earningsTotal + " where id_nom=" + payroll.getId_name() + ";");
            payroll.setTotal_dev(earningsTotal);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the total deductions for each payroll in the batches.
     *
     * @param batches the list of batches
     */
    private static void calculateDeductionTotal(ArrayList<PayrollBatch> batches) { // Author : David Serna Mateu
        for (PayrollBatch batch: batches) {
            for (Payroll payroll: batch.getPayrolls()) {
                double deductionTotal = 0;

                deductionTotal = getTotalCC(payroll) + getTotalDesempleo(payroll) + getTotalFP(payroll)
                        + getTotalExtraHours(payroll) + calculateIRPFDeduction(payroll);

                updateDeductionTotal(payroll, deductionTotal);
            }
        }
    }

    /**
     * Calculates the total contribution for common contingencies for the given payroll.
     *
     * @param payroll the payroll
     * @return the total contribution for common contingencies
     */
    private static double getTotalCC(Payroll payroll) { // Author: Pedro Marín Sanchis
        double paso1 = getBaseCC(payroll);
        double totalCC = 0;
        for (Contingency contingency: payroll.getContingencies_Emp()) { // Step 4
            if (contingency.getCod_c().equalsIgnoreCase("Comunes")) {
                totalCC = paso1*(contingency.getQuant()/100);
            }
        }
        return totalCC;
    }

    /**
     * Calculates the base amount for common contingencies for the given payroll.
     *
     * @param payroll the payroll
     * @return the base amount for common contingencies
     */
    private static double getBaseCC(Payroll payroll) { // Author: David Serna Mateu
        for (Perception perception: payroll.getPerceptions()) {
            if (perception.getCod_p().equalsIgnoreCase("Horas_Ext")) {
                return payroll.getTotal_dev() - perception.getQuant();
            }
        }
        return payroll.getTotal_dev();
    }

    /**
     * Calculates the total amount for unemployment contingencies for the given payroll.
     *
     * @param payroll the payroll
     * @return the total amount for unemployment contingencies
     */
    private static double getTotalDesempleo(Payroll payroll) { // Author: Pedro Marín Sanchis
        double totalDesempleo = 0;
        for (Contingency contingency: payroll.getContingencies_Emp()) { // Step 5
            if (contingency.getCod_c().equalsIgnoreCase("Desempleo") || contingency.getCod_c().equalsIgnoreCase("Desempleo2")) {
                totalDesempleo = payroll.getTotal_dev()*(contingency.getQuant()/100);
            }
        }
        return totalDesempleo;
    }

    /**
     * Calculates the total amount for professional training contingencies for the given payroll.
     *
     * @param payroll the payroll
     * @return the total amount for professional training contingencies
     */
    private static double getTotalFP(Payroll payroll) { // Author: Pedro Marín Sanchis
        double totalFP = 0;
        for (Contingency contingency: payroll.getContingencies_Emp()) { // Step 6
            if (contingency.getCod_c().equalsIgnoreCase("Formación Profesional")) {
                totalFP = payroll.getTotal_dev()*(contingency.getQuant()/100);
            }
        }
        return totalFP;
    }

    /**
     * Calculates the total amount for extra hours worked based on the corresponding contingency code and perception code for the given payroll.
     *
     * @param payroll the payroll
     * @return the total amount for extra hours worked
     */
    private static double getTotalExtraHours(Payroll payroll) { // Author: Pedro Marín Sanchis
        double totalExtraHours = 0;
        for (Contingency contingency: payroll.getContingencies_Emp()) { // Step 7
            if (contingency.getCod_c().equalsIgnoreCase("Horas Extras Normales")) {
                for (Perception perception: payroll.getPerceptions()) {
                    if (perception.getCod_p().equalsIgnoreCase("Horas_Ext")) {
                        totalExtraHours = perception.getQuant()*(contingency.getQuant()/100);
                    }
                }
            }
        }
        return totalExtraHours;
    }

    /**
     * Updates the deduction total for a given payroll in the database and sets the deduction total value in the corresponding Payroll object.
     *
     * @param payroll         The Payroll object for which to update the deduction total.
     * @param deductionTotal  The new deduction total value.
     */
    private static void updateDeductionTotal(Payroll payroll, double deductionTotal) { // Author : David Serna Mateu
        try{
            Statement st = getConnection().createStatement();
            st.executeUpdate("update nomina set total_deduc=" + deductionTotal + " where id_nom=" + payroll.getId_name() + ";");
            payroll.setTotal_deduc(deductionTotal);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the IRPF (Impuesto sobre la Renta de las Personas Físicas) deduction for a given payroll based on the IRPF rate and the base contribution for common contingencies.
     *
     * @param payroll  The Payroll object for which to calculate the IRPF deduction.
     * @return The calculated IRPF deduction.
     */
    private static double calculateIRPFDeduction(Payroll payroll) { // Author: Pedro Marín Sanchis
        return payroll.getIRPF()*(getBaseCC(payroll)/100);
    }

    /**
     * Calculates the net income for each payroll in the given list of batches. The net income is calculated as the difference between the total earnings and total deductions.
     *
     * @param batches The list of PayrollBatch objects containing the payrolls for which to calculate the net income.
     */
    private static void calculateNetIncome(ArrayList<PayrollBatch> batches) { // Author: David Serna Mateu / Pedro Marín Sanchis
        for (PayrollBatch batch: batches) {
            for (Payroll payroll: batch.getPayrolls()) {
                try{
                    Statement st = getConnection().createStatement();
                    st.executeUpdate("update nomina set total_neto = (total_dev - total_deduc);");
                    payroll.setTotal_net(payroll.getTotal_dev() - payroll.getTotal_deduc());
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Calculates the total company deductions for each payroll in the given list of batches. The company deductions include contributions to social security, ATEP, unemployment, FP, FOGASA, and extra hours.
     *
     * @param batches The list of PayrollBatch objects containing the payrolls for which to calculate the company deductions.
     */
    private static void calculateCompanyTotal(ArrayList<PayrollBatch> batches) { // Author: Pedro Marín Sanchis
        for (PayrollBatch batch: batches) {
            for (Payroll payroll: batch.getPayrolls()) {
                double deductionTotal = 0;

                deductionTotal = calculateCompanyCC(payroll) + getCompanyATEP(payroll) + getCompanyUnemployment(payroll)
                        + getCompanyFP(payroll) + getCompanyFOGASA(payroll) + getCompanyExtraHours(payroll);

                updateCompanyTotal(payroll, deductionTotal);
            }
        }
    }

    /**
     * Calculates the company contribution to social security for the given payroll.
     *
     * @param payroll The Payroll object for which to calculate the company contribution to social security.
     * @return The total company contribution to social security.
     */
    private static double calculateCompanyCC(Payroll payroll) { // Author: Pedro Marín Sanchis
        double totalCompanyCC = 0;
        for (Contingency contingency: payroll.getContingencies_Com()) {
            if (contingency.getCod_c().equalsIgnoreCase("Comunes")) {
                return getBaseCC(payroll)*(contingency.getQuant()/100);
            }
        }
        return totalCompanyCC;
    }

    /**
     * Calculates the company contribution for ATEP (Accidents at Work and Occupational Diseases)
     * for the given payroll.
     *
     * @param payroll The Payroll object for which to calculate the company contribution for ATEP.
     * @return The total company contribution for ATEP.
     */
    private static double getCompanyATEP(Payroll payroll) {  // Author: David Serna Mateu
        return payroll.getATEP()*(payroll.getTotal_dev()/100);
    }

    /**
     * Calculates the company contribution for unemployment for the given payroll.
     *
     * @param payroll The Payroll object for which to calculate the company contribution for unemployment.
     * @return The total company contribution for unemployment.
     */
    private static double getCompanyUnemployment(Payroll payroll) { // Author: Pedro Marín Sanchis
        double totalCompanyUnemployment = 0;
        for (Contingency contingency: payroll.getContingencies_Com()) {
            if (contingency.getCod_c().equalsIgnoreCase("Desempleo") || contingency.getCod_c().equalsIgnoreCase("Desempleo2")) {
                return payroll.getTotal_dev()*(contingency.getQuant()/100);
            }
        }
        return totalCompanyUnemployment;
    }

    /**
     * Calculates the company contribution for Formación Profesional (Professional Training)
     * for the given payroll.
     *
     * @param payroll The Payroll object for which to calculate the company contribution for Formación Profesional.
     * @return The total company contribution for Formación Profesional.
     */
    private static double getCompanyFP(Payroll payroll) { // Author: Pedro Marín Sanchis
        double totalFP = 0;
        for (Contingency contingency: payroll.getContingencies_Com()) {
            if (contingency.getCod_c().equalsIgnoreCase("Formación Profesional")) {
                totalFP = payroll.getTotal_dev()*(contingency.getQuant()/100);
            }
        }
        return totalFP;
    }

    /**
     * Calculates the company contribution for FOGASA (Guarantee Fund for Wage Payments)
     * for the given payroll.
     *
     * @param payroll The Payroll object for which to calculate the company contribution for FOGASA.
     * @return The total company contribution for FOGASA.
     */
    private static double getCompanyFOGASA(Payroll payroll){ // Author: David Serna Mateu
        double totalFOGASA = 0;
        for (Contingency contingency: payroll.getContingencies_Com()) {
            if (contingency.getCod_c().equalsIgnoreCase("FOGASA")) {
                totalFOGASA = payroll.getTotal_dev()*(contingency.getQuant()/100);
            }
        }
        return totalFOGASA;
    }

    /**
     * Calculates the company contribution for extra hours for the given payroll.
     *
     * @param payroll The Payroll object for which to calculate the company contribution for extra hours.
     * @return The total company contribution for extra hours.
     */
    private static double getCompanyExtraHours(Payroll payroll) { // Author: Pedro Marín Sanchis
        double totalExtraHours = 0;
        for (Contingency contingency: payroll.getContingencies_Com()) {
            if (contingency.getCod_c().equalsIgnoreCase("Horas Extras Normales")) {
                for (Perception perception: payroll.getPerceptions()) {
                    if (perception.getCod_p().equalsIgnoreCase("Horas_Ext")) {
                        totalExtraHours = perception.getQuant()*(contingency.getQuant()/100);
                    }
                }
            }
        }
        return totalExtraHours;
    }

    /**
     * Updates the company total contribution for the given payroll and updates the payroll object accordingly.
     *
     * @param payroll         The Payroll object for which to update the company total contribution.
     * @param deductionTotal  The total company contribution to be updated.
     */
    private static void updateCompanyTotal(Payroll payroll, double deductionTotal) { // Author : David Serna Mateu
        try{
            Statement st = getConnection().createStatement();
            st.executeUpdate("update nomina set ap_empresa=" + deductionTotal + " where id_nom=" + payroll.getId_name() + ";");
            payroll.setAp_company(deductionTotal);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}