package org.example.exercice1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/exercice1_jdbc";
    private static String USERNAME = "root";
    private static String PASSWORD = "test";

    public static Connection getSQLConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}
