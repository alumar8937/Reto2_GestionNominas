package testing;

import controller.database.PayrollDBController;
import model.Employee;
import model.Payroll;
import model.PayrollBatch;
import org.junit.jupiter.api.Test;
import userconfig.UserconfigManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {

    private static Connection connection;

    @Test
    public void testEstablishConnection() {
        try {
            // Call the establishConnection() method
            establishConnection();

            // Check if the connection is not null
            assertNotNull(connection);

            // Check if the connection is valid
            assertTrue(connection.isValid(5));

        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

    public static void establishConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://89.36.214.106/";
            Properties props = new Properties();
            props.setProperty("user", "geo_1cfsy_3561y");
            props.setProperty("password", "geo_1cfsy_3561y");
            props.setProperty("ssl", "false");
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT nif, nombre, apellido1, apellido2 from trabajador;");
            while(rs.next()){
                String name = (rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                employees.add(new Employee(name, rs.getString(1)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        // Verificar el resultado esperado
        assertEquals(4, employees.size());

    }

    @Test
    public void testGetEmployeesByDepartmentID() {
        ArrayList<Employee> employees = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nif, nombre, apellido1, apellido2 from trabajador t where t.cod_dep ='1';");
            while(rs.next()){
                String name = (rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                employees.add(new Employee(name, rs.getString(1)));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        // Verificar el resultado esperado
        assertEquals(2, employees.size());

        }

    @Test
    public void testGetBatches() {
        ArrayList<PayrollBatch> batches = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM remesa where aceptado=false;"
            );
            while (resultSet.next()) {
                PayrollBatch batch = new PayrollBatch(resultSet.getInt(1),resultSet.getBoolean(2));
                batches.add(batch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Verificar el resultado esperado
        assertEquals(3, batches.size());

        }

}





