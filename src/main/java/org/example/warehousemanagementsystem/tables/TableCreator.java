package org.example.warehousemanagementsystem.tables;

import java.sql.*;

public class TableCreator {
    public static void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTable;
        DatabaseMetaData md = connection.getMetaData();
        ResultSet resultSet = md.getTables("apulikkalmd", null, tableName, null);
        if (resultSet.next()){
            System.out.println(tableName + " table already exists");
        } else {
            createTable = connection.createStatement();
            createTable.execute(tableQuery);
            System.out.println("The " + tableName + " table has been created");
        }
    }
}
