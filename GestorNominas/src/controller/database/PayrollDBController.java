package controller.database;

import org.postgresql.core.Query;
import userconfig.UserconfigManager;

import javax.swing.*;
import java.sql.*;
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
}