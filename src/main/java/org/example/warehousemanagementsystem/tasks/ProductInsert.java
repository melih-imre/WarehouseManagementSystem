package org.example.warehousemanagementsystem.tasks;

import org.example.warehousemanagementsystem.database.DBConst;
import org.example.warehousemanagementsystem.database.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductInsert implements Runnable{
    private int sku;
    private String model;
    private int brandId;
    private int price;

    public ProductInsert(int sku,int brandId, String model, int price) {
        this.sku = sku;
        this.brandId=brandId;
        this.model = model;
        this.price = price;
    }
    @Override
    public void run() {
        try {
            Connection connection= Database.getInstance().getConnection();
            String insertQuery ="INSERT INTO "+ DBConst.TABLE_PRODUCT+" ("+
                    DBConst.COLUMN_SKU+", "+
                    DBConst.COLUMN_BRAND_ID+", "+
                    DBConst.COLUMN_MODEL+", "+
                    DBConst.COLUMN_PRICE+") VALUES ("+
                    sku+", "+brandId+", '"+model+"', "+price+")";
            Statement statement =connection.createStatement();
            int rowsInsert = statement.executeUpdate(insertQuery);
            if(rowsInsert>0){
                System.out.println("Product Inserted Successfully");
            }else {
                System.out.println("Failed to insert Product");
            }


        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());


        }
    }
}
