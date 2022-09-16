package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DbConnector {
    private static Connection connection;
    public static final String URL = "jdbc:mysql://localhost:3306/Shop?serverTimezone=Europe/Kiev";
    public static final String USER = "root";
    public static final String PASSWORD = "musiiko";

    public static Connection getConnection() {
        try {
            if (Objects.isNull(connection)) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
