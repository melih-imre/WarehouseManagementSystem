package org.example.warehousemanagementsystem.tasks;

import org.example.warehousemanagementsystem.database.DBConst;
import org.example.warehousemanagementsystem.database.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteProductTask {
    private final int id;

    public DeleteProductTask(int id) {
        this.id = id;
    }
    public boolean execute(){
        boolean isDeleted =false;
        try {
            Connection connection= Database.getInstance().getConnection();
            String deleteQuery ="DELETE FROM "+ DBConst.TABLE_PRODUCT+" WHERE "+ DBConst.PRODUCT_LOCATION_COLUMN_SKU + " = " + id;
            try (Statement statement = connection.createStatement()) {
                int rowsDeleted = statement.executeUpdate(deleteQuery);
                if (rowsDeleted > 0) {
                    System.out.println("Deleted Successfully");
                    isDeleted = true;
                } else {
                    System.out.println("No records found with ID:" + id);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database Error" + e.getMessage());
        }

        return isDeleted;
    }

}
