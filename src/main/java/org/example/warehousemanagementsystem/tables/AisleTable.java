package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.AisleDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Aisle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;


public class AisleTable implements AisleDAO {
    private static AisleTable instance;
    Database db = Database.getInstance();
    ArrayList<Aisle> aisles;
    private AisleTable(){db = Database.getInstance();}
    @Override
    public ArrayList<Aisle> getAllAisles() {
        String query = "SELECT * FROM " + TABLE_AISLES;
        aisles = new ArrayList<>();
        try {
            Statement getAisles = db.getConnection().createStatement();
            ResultSet data = getAisles.executeQuery(query);
            while (data.next()){
                aisles.add(
                        new Aisle(
                                data.getInt(AISLES_COLUMN_ID),
                                data.getString(AISLES_COLUMN_AISLE)
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aisles;
    }

    @Override
    public Aisle getAisle(int id) {
        String query = "SELECT * FROM " + TABLE_AISLES + " WHERE " + AISLES_COLUMN_ID + " = " +id;

        try {
            Statement getAisle = db.getConnection().createStatement();
            ResultSet data = getAisle.executeQuery(query);
            if (data.next()){
                Aisle aisle = new Aisle(
                        data.getInt(AISLES_COLUMN_ID),
                        data.getString(AISLES_COLUMN_AISLE)
                );
                return aisle;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;    }

    public static AisleTable getInstance(){
        if (instance == null) {
            instance = new AisleTable();
        }
        return instance;
    }
}
