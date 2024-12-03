package org.example.warehousemanagementsystem.tables;

import java.sql.*;

/**
 * Utility class for creating database tables.
 * This class provides a method to create a table if it does not already exist in the database.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-11
 */

public class TableCreator {

    /**
     * Creates a table in the database if it does not already exist.
     *
     * @param tableName The name of the table to create.
     * @param tableQuery The SQL query to define the table's structure.
     * @param connection The database connection to use for the operation.
     * @throws SQLException If a database access error occurs.
     */
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
