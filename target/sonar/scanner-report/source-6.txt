package fr.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL="jdbc:mysql//localhost:8889/salle_de_sport";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySql Driver non trouvé", e);
        }
        
    } 
}
