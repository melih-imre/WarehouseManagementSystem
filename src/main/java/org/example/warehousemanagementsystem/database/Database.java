package org.example.warehousemanagementsystem.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void connect(String serverLocation, String dbName, String username, String password) throws Exception {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + serverLocation + "/" + dbName + "?serverTimeZone=UTC";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established.");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
