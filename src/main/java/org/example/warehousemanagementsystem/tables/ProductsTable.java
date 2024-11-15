package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ProductDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Product;

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
    public ArrayList<Product> getAllCoins() {
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
            throw new RuntimeException(e);
        }
        return null;
    }

    public static ProductsTable getInstance(){
        if (instance == null) {
            instance = new ProductsTable();
        }
        return instance;
    }
}
