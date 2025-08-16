package az.company.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "");
            System.out.println("Connection to database has been successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection has been closed!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
