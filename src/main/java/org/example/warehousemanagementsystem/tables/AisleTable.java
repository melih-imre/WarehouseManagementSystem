package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.AisleDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Aisle;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayAisle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;


public class AisleTable implements AisleDAO {
    private static AisleTable instance;
    Database db = Database.getInstance();
    ArrayList<Aisle> aisles;
    public AisleTable(){db = Database.getInstance();}
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
        Aisle aisle = new Aisle();

        try {
            Statement getAisle = db.getConnection().createStatement();
            ResultSet data = getAisle.executeQuery(query);
            if (data.next()){
                aisle = new Aisle(
                        data.getInt(AISLES_COLUMN_ID),
                        data.getString(AISLES_COLUMN_AISLE)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aisle;    }

    public static AisleTable getInstance(){
        if (instance == null) {
            instance = new AisleTable();
        }
        return instance;
    }

    public ArrayList<DisplayAisle> getItems(){
        ArrayList<DisplayAisle> aisles = new ArrayList<DisplayAisle>();
        String query = "SELECT aisle_id, aisle FROM aisles ORDER BY aisle_id ASC";
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                aisles.add(new DisplayAisle(
                        resultSet.getInt("aisle_id"),
                        resultSet.getString("aisle")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aisles;

    }
}
