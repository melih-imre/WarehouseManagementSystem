package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.BrandDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class BrandTable implements BrandDAO {
    private static BrandTable instance;
    Database db = Database.getInstance();
    ArrayList<Brand> brands;
    private BrandTable() {
        db = Database.getInstance();
    }

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

    public static BrandTable getInstance(){
        if (instance == null) {
            instance = new BrandTable();
        }
        return instance;
    }
}
