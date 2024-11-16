package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ProductCategoryDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Category;
import org.example.warehousemanagementsystem.pojo.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class ProductCategoryTable implements ProductCategoryDAO {
    private static ProductCategoryTable instance;
    Database db = Database.getInstance();
    ArrayList<ProductCategory> productCategories;
    private ProductCategoryTable(){db = Database.getInstance();}
    @Override
    public ArrayList<ProductCategory> getAllProductCategories() {
        String query = "SELECT * FROM " + TABLE_PRODUCT_CATEGORIES;

        productCategories = new ArrayList<>();
        try {
            Statement getProductCategories = db.getConnection().createStatement();
            ResultSet resultSet = getProductCategories.executeQuery(query);

            while (resultSet.next()){
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

    @Override
    public ProductCategory getProductCategory(int id) {
        String query = "SELECT * FROM " + TABLE_PRODUCT_CATEGORIES + " WHERE " + PRODUCT_CATEGORY_COLUMN_SKU + " = " +id;

        try {
            Statement getProductCategory = db.getConnection().createStatement();
            ResultSet data = getProductCategory.executeQuery(query);
            if (data.next()){
                ProductCategory productCategory = new ProductCategory(
                        data.getInt(PRODUCT_CATEGORY_COLUMN_SKU),
                        data.getInt(PRODUCT_CATEGORY_COLUMN_ID)
                );
                return productCategory;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static ProductCategoryTable getInstance(){
        if (instance == null) {
            instance = new ProductCategoryTable();
        }
        return instance;
    }
}
