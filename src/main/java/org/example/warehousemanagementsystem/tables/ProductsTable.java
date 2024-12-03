package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ProductDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class ProductsTable implements ProductDAO {
    private static ProductsTable instance;

    Database db = Database.getInstance();
    ArrayList<Product> products;
    private ProductsTable() {
        db = Database.getInstance();
    }


    @Override
    public ArrayList<Product> getAllProducts() {
        String query = "SELECT * FROM " + TABLE_PRODUCT;

        products = new ArrayList<>();
        try {
            Statement getProducts = db.getConnection().createStatement();
            ResultSet resultSet = getProducts.executeQuery(query);

            while (resultSet.next()){
                products.add(new Product(
                        resultSet.getInt(COLUMN_SKU),
                        resultSet.getInt(COLUMN_BRAND_ID),
                        resultSet.getString(COLUMN_MODEL),
                        resultSet.getInt(COLUMN_PRICE)
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }



    @Override
    public Product getProduct(int id) {
        String query = "SELECT * FROM " + TABLE_PRODUCT + " WHERE " + COLUMN_SKU + " = " +id;

        try {
            Statement getProduct = db.getConnection().createStatement();
            ResultSet data = getProduct.executeQuery(query);
            if (data.next()){
                Product product = new Product(
                            data.getInt(COLUMN_SKU),
                            data.getInt(COLUMN_BRAND_ID),
                            data.getString(COLUMN_MODEL),
                            data.getInt(COLUMN_PRICE)
                         );
                        return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addProduct(Product product) {
        String query = "INSERT INTO " + TABLE_PRODUCT + " (" +
                COLUMN_SKU+","+
                COLUMN_BRAND_ID + ", " +
                COLUMN_MODEL + ", " +
                COLUMN_PRICE + ") VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1,product.getSku());
            preparedStatement.setInt(1, product.getBrandId());
            preparedStatement.setString(2, product.getModel());
            preparedStatement.setInt(3, product.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ProductsTable getInstance(){
        if (instance == null) {
            instance = new ProductsTable();
        }
        return instance;
    }
}
