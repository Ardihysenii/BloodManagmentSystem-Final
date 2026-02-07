package com.bloodmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static final String SERVER_NAME = "DESKTOP-PCV687J";
    private static final String DB_NAME = "BloodManagementDB";
    private static final String USER = "BloodManagmentSystem";
    private static final String PASSWORD = "Project123..";


    private static final String URL = "jdbc:sqlserver://" + SERVER_NAME + ":1433;"
            + "databaseName=" + DB_NAME + ";"
            + "encrypt=false;"
            + "trustServerCertificate=true;";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ [Database Error]: Nuk u realizua lidhja!");
            System.out.println("Arsyeja: " + e.getMessage());
        }
        return connection;
    }
}