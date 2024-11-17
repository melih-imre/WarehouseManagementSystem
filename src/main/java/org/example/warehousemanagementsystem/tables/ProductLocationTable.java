package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ProductLocationDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.ProductLocation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class ProductLocationTable implements ProductLocationDAO {
    private static ProductLocationTable instance;
    Database db = Database.getInstance();
    ArrayList<ProductLocation> productLocations;
    private ProductLocationTable(){db = Database.getInstance();}
    @Override
    public ArrayList<ProductLocation> getAllProductLocations() {
        String query = "SELECT * FROM " + TABLE_PRODUCT_LOCATIONS;

        productLocations = new ArrayList<>();
        try {
            Statement getProductLocations = db.getConnection().createStatement();
            ResultSet resultSet = getProductLocations.executeQuery(query);

            while (resultSet.next()){
                productLocations.add(new ProductLocation(
                        resultSet.getInt(PRODUCT_LOCATION_ID),
                        resultSet.getString(PRODUCT_LOCATION_COLUMN_SKU),
                        resultSet.getInt(PRODUCT_LOCATION_COLUMN_AISLE_ID),
                        resultSet.getInt(PRODUCT_LOCATION_COLUMN_SHELF_ID),
                        resultSet.getInt(PRODUCT_LOCATION_COLUMN_QUANTITY)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productLocations;
    }

    @Override
    public ProductLocation getProductLocation(int id) {
        String query = "SELECT * FROM " + TABLE_PRODUCT_LOCATIONS + " WHERE " + PRODUCT_LOCATION_ID + " = " +id;

        try {
            Statement getProductLocation = db.getConnection().createStatement();
            ResultSet data = getProductLocation.executeQuery(query);
            if (data.next()){
                ProductLocation productLocation = new ProductLocation(
                        data.getInt(PRODUCT_LOCATION_ID),
                        data.getString(PRODUCT_LOCATION_COLUMN_SKU),
                        data.getInt(PRODUCT_LOCATION_COLUMN_AISLE_ID),
                        data.getInt(PRODUCT_LOCATION_COLUMN_SHELF_ID),
                        data.getInt(PRODUCT_LOCATION_COLUMN_QUANTITY)
                );
                return productLocation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;    }

    public static ProductLocationTable getInstance(){
        if (instance == null) {
            instance = new ProductLocationTable();
        }
        return instance;
    }
}
