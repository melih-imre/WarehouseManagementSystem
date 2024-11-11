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
            TableCreator.createTable(DBConst.TABLE_PRODUCT, DBConst.CREATE_TABLE_PRODUCTS, connection);


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

    public Connection getConnection() {return connection;}
}
