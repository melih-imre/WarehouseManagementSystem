package org.example.warehousemanagementsystem.tasks;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.example.warehousemanagementsystem.database.DBConst;
import org.example.warehousemanagementsystem.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTask {
    private final int recordID;


    public DeleteTask(int recordID){
        this.recordID=recordID;

    }

    public boolean execute(){
        boolean isDeleted = false;
        try(Connection connection= Database.getInstance().getConnection()){
            String deleteQuery = "DELETE FROM "+ DBConst.TABLE_CATEGORIES+" WHERE "+DBConst.CATEGORY_COLUMN_CATEGORY_ID+" = "+recordID;
            try(Statement statement = connection.createStatement()){
                int rowsDeleted = statement.executeUpdate(deleteQuery);
                if(rowsDeleted>0){


                    System.out.println("Deleted Successfully");
                    isDeleted=true;


                }else {
                    System.out.println("No records Found with ID:"+recordID);
                }

            }

        }catch (SQLException e){
            System.out.println("Database Error"+e.getMessage());
        }
        return isDeleted;
    }



}
