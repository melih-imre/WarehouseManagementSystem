package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ProductCategoryDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

/**
 * Handles database operations related to the "ProductCategory" table.
 * Implements the ProductCategoryDAO interface for standard data access methods.
 * This class follows the Singleton design pattern.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-16
 */

public class ProductCategoryTable implements ProductCategoryDAO {
    private static ProductCategoryTable instance;
    Database db = Database.getInstance();
    ArrayList<ProductCategory> productCategories;

    private ProductCategoryTable() {
        db = Database.getInstance();
    }

    /**
     * Retrieves all product categories from the database.
     *
     * @return An ArrayList of ProductCategory objects representing all categories in the database.
     */
    @Override
    public ArrayList<ProductCategory> getAllProductCategories() {
        String query = "SELECT * FROM " + TABLE_PRODUCT_CATEGORIES;

        productCategories = new ArrayList<>();
        try {
            Statement getProductCategories = db.getConnection().createStatement();
            ResultSet resultSet = getProductCategories.executeQuery(query);

            while (resultSet.next()) {
                productCategories.add(new ProductCategory(
                        resultSet.getInt(PRODUCT_CATEGORY_COLUMN_SKU),
                        resultSet.getInt(PRODUCT_CATEGORY_COLUMN_ID)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productCategories;
    }

    /**
     * Retrieves a specific product category by SKU.
     *
     * @param id The SKU of the product to retrieve.
     * @return A ProductCategory object, or null if not found.
     */
    @Override
    public ProductCategory getProductCategory(int id) {
        String query = "SELECT * FROM " + TABLE_PRODUCT_CATEGORIES + " WHERE " + PRODUCT_CATEGORY_COLUMN_SKU + " = " + id;

        try {
            Statement getProductCategory = db.getConnection().createStatement();
            ResultSet data = getProductCategory.executeQuery(query);
            if (data.next()) {
                ProductCategory productCategory = new ProductCategory(
                        data.getInt(PRODUCT_CATEGORY_COLUMN_SKU),
                        data.getInt(PRODUCT_CATEGORY_COLUMN_ID)
                );
                return productCategory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ProductCategoryTable getInstance() {
        if (instance == null) {
            instance = new ProductCategoryTable();
        }
        return instance;
    }
    /**
     * Retrieves the total number of products in a specific category.
     *
     * @param id The ID of the category.
     * @return The count of products in the specified category.
     */
    public int getProductCountByCategoryId(int id) {
        int count = 0;
        String query = "SELECT COUNT(*) AS product_count FROM " + TABLE_PRODUCT_CATEGORIES + " WHERE " + PRODUCT_CATEGORY_COLUMN_ID + " = " + id;

        try {
            Statement getProductCategory = db.getConnection().createStatement();
            ResultSet data = getProductCategory.executeQuery(query);

            if (data.next()) {
                count = data.getInt("product_count");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
