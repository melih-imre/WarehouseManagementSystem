package org.example.warehousemanagementsystem.database;

import org.example.warehousemanagementsystem.tables.TableCreator;

import java.sql.*;
import static org.example.warehousemanagementsystem.database.Const.*;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/" + DB_NAME +
                            "?serverTimeZone = UTC",
                            DB_USER,
                            DB_PASS
                            );
            System.out.println("created connection");
            //create table
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


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();

        }
        return instance;
    }


    //table

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + DB_NAME +
                    "?serverTimezone=UTC", DB_USER, DB_PASS);
        }

        return connection;}


}
