package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ProductLocationDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.ProductLocation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;
/**
 * Handles database operations related to the "ProductLocation" table.
 * Implements the ProductLocationDAO interface for standard data access methods.
 * This class follows the Singleton design pattern.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-03
 */

public class ProductLocationTable implements ProductLocationDAO {
    private static ProductLocationTable instance;
    Database db = Database.getInstance();
    ArrayList<ProductLocation> productLocations;
    private ProductLocationTable(){db = Database.getInstance();}

    /**
     * Retrieves all product locations from the database.
     *
     * @return An ArrayList of ProductLocation objects representing all locations in the database.
     */

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
                        resultSet.getInt(PRODUCT_LOCATION_COLUMN_SKU),
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

    /**
     * Retrieves a specific product location by its ID.
     *
     * @param id The ID of the product location to retrieve.
     * @return A ProductLocation object, or null if not found.
     */
    @Override
    public ProductLocation getProductLocation(int id) {
        String query = "SELECT * FROM " + TABLE_PRODUCT_LOCATIONS + " WHERE " + PRODUCT_LOCATION_ID + " = " +id;

        try {
            Statement getProductLocation = db.getConnection().createStatement();
            ResultSet data = getProductLocation.executeQuery(query);
            if (data.next()){
                ProductLocation productLocation = new ProductLocation(
                        data.getInt(PRODUCT_LOCATION_ID),
                        data.getInt(PRODUCT_LOCATION_COLUMN_SKU),
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

    /**
     * Retrieves the total quantity of a product at a specific location.
     *
     * @param id The ID of the product location.
     * @return The total quantity at the specified location.
     */
    public int getQuantity(int id){
        String query = "SELECT SUM(" + PRODUCT_LOCATION_COLUMN_QUANTITY + ") AS total_quantity " +
                "FROM " + TABLE_PRODUCT_LOCATIONS +
                " WHERE " + PRODUCT_LOCATION_ID + " = '" + id + "'";
        try {
            Statement getQuantity = db.getConnection().createStatement();
            ResultSet resultSet = getQuantity.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getInt("total_quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * Updates the quantity of a product at a specific location.
     *
     * @param id The ID of the product location.
     * @param quantity The amount to decrease the current quantity by.
     */
    public void updateQuantity(int id, int quantity){
        int updatedQuantity = getQuantity(id) - quantity;
        String query = "UPDATE " + TABLE_PRODUCT_LOCATIONS +
                " SET " + PRODUCT_LOCATION_COLUMN_QUANTITY + " = " + updatedQuantity +
                " WHERE " + PRODUCT_LOCATION_ID + " = " + id;

        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Quantity updated successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the location ID of a product by its SKU.
     *
     * @param sku The SKU of the product.
     * @return The location ID, or -42 if not found.
     */
    public int getLocationIdBySku(int sku) {
        String query = "SELECT " + PRODUCT_LOCATION_ID + " FROM " + TABLE_PRODUCT_LOCATIONS + " WHERE " + PRODUCT_LOCATION_COLUMN_SKU + " = " +sku;

        try {
            Statement getProductLocation = db.getConnection().createStatement();
            ResultSet data = getProductLocation.executeQuery(query);
            if (data.next()){
                return data.getInt(PRODUCT_LOCATION_ID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -42;
    }
}
