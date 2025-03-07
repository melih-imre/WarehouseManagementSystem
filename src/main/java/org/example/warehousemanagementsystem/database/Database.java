package org.example.warehousemanagementsystem.database;

import org.example.warehousemanagementsystem.tables.TableCreator;

import java.sql.*;

public class Database {

    private static Database instance;
    private Connection connection;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();

        }
        return instance;
    }


    //table

    public Connection getConnection() {return connection;}

    public void connect(String serverLocation, String dbName, String username, String password) throws Exception {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + serverLocation + "/" + dbName + "?serverTimeZone=UTC";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established.");

            TableCreator.createTable(DBConst.TABLE_BRAND, DBConst.CREATE_TABLE_BRAND_ID, connection);
            TableCreator.createTable(DBConst.TABLE_CATEGORIES, DBConst.CREATE_TABLE_CATEGORIES, connection);
            TableCreator.createTable(DBConst.TABLE_PRODUCT, DBConst.CREATE_TABLE_PRODUCTS, connection);
            TableCreator.createTable(DBConst.TABLE_PRODUCT_CATEGORIES, DBConst.CREATE_TABLE_PRODUCT_CATEGORY, connection);
            TableCreator.createTable(DBConst.TABLE_AISLES, DBConst.CREATE_TABLE_AISLES, connection);
            TableCreator.createTable(DBConst.TABLE_SHELVES, DBConst.CREATE_TABLE_SHELVES, connection);
            TableCreator.createTable(DBConst.TABLE_LOCATIONS, DBConst.CREATE_TABLE_LOCATIONS, connection);
            TableCreator.createTable(DBConst.TABLE_CLIENTS, DBConst.CREATE_TABLE_CLIENTS, connection);
            TableCreator.createTable(DBConst.TABLE_PRODUCT_LOCATIONS, DBConst.CREATE_TABLE_PRODUCT_LOCATIONS, connection);
            TableCreator.createTable(DBConst.TABLE_TRANSACTIONS, DBConst.CREATE_TABLE_TRANSACTIONS, connection);

        }
    }
}
