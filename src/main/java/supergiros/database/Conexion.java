package supergiros.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:postgresql://localhost:5432/crud";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";


    //Conexion a la BD
    public Connection Conexion() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //Ejecutar consulta SQL
    public  ResultSet EjecutarSelect(Connection connection, String sqlQuery) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //Ejecutar insertar SQL
    public  boolean ejecutarInsert(Connection connection, String sqlQuery) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return true;
    }

    //Ejecutar Actualizar SQL
    public  boolean ejecutarUpdate(Connection connection, String sqlQuery) {
        boolean cumplido = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            cumplido = true;
        } catch (SQLException e) {
            cumplido = false;
            e.printStackTrace();
        }
        return cumplido;
    }

    //Ejecutar eliminar SQL
    public  boolean ejecutarDelete(Connection connection, String sqlQuery) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
