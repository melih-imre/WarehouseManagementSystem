package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.BrandDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Brand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

/**
 * Handles database operations related to the "Brand" table.
 * Implements the BrandDAO interface for standard data access methods.
 * This class follows the Singleton design pattern.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-16
 */

public class BrandTable implements BrandDAO {
    private static BrandTable instance;
    Database db = Database.getInstance();
    ArrayList<Brand> brands;
    public BrandTable() {
        db = Database.getInstance();
    }

    /**
     * Retrieves all brands from the database.
     *
     * @return An ArrayList of Brand objects representing all brands in the database.
     */
    @Override
    public ArrayList<Brand> getAllBrands() {
        String query = "SELECT * FROM " + TABLE_BRAND;

        brands = new ArrayList<>();
        try {
            Statement getBrands = db.getConnection().createStatement();
            ResultSet resultSet = getBrands.executeQuery(query);

            while (resultSet.next()){
                brands.add(new Brand(
                        resultSet.getInt(BRAND_COLUMN_BRAND_ID),
                        resultSet.getString(BRAND_COLUMN_BRAND)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brands;
    }

    /**
     * Retrieves a brand from the database based on its ID.
     *
     * @param id The ID of the brand to be retrieved.
     * @return A Brand object if found, otherwise null.
     */
    @Override
    public Brand getBrand(int id) {
        String query = "SELECT * FROM " + TABLE_BRAND + " WHERE " + BRAND_COLUMN_BRAND_ID + " = " +id;

        try {
            Statement getBrand = db.getConnection().createStatement();
            ResultSet data = getBrand.executeQuery(query);
            if (data.next()){
                Brand brand = new Brand(
                        data.getInt(BRAND_COLUMN_BRAND_ID),
                        data.getString(BRAND_COLUMN_BRAND)
                );
                return brand;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean addBrand(Brand brand) {
        String query = "INSERT INTO " + TABLE_BRAND + " (" + BRAND_COLUMN_BRAND + ") VALUES (?)";
        try {
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, brand.getBrand());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static BrandTable getInstance(){
        if (instance == null) {
            instance = new BrandTable();
        }
        return instance;
    }
}
