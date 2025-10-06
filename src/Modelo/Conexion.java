
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/gp3_universidad";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    public static Connection getConexion() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexion exitosa a la BD.");
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Error de conexion: " + ex.getMessage());
            }
        }
        return connection;
    }
}
