package org.example.warehousemanagementsystem.tasks;

import org.example.warehousemanagementsystem.database.DBConst;
import org.example.warehousemanagementsystem.database.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryInsert implements Runnable {

    private String category;

    public CategoryInsert(String category){
        this.category=category;
    }
    @Override
    public void run(){
        try (Connection connection = Database.getInstance().getConnection()){
        String insertquery = "INSERT INTO "+ DBConst.TABLE_CATEGORIES+" ("+
                DBConst.CATEGORY_COLUMN_CATEGORY + ")VALUES ('" + category + "')";
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(insertquery);
            System.out.println("Category Inserted Successfully");

        }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());

        }
    }
}
