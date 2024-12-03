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

/**
 * Handles database operations related to the "Aisle" table.
 * Implements the AisleDAO interface for standard data access methods.
 * This class follows the Singleton design pattern.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-16
 */
public class AisleTable implements AisleDAO {
    private static AisleTable instance;
    Database db = Database.getInstance();
    ArrayList<Aisle> aisles;
    public AisleTable(){db = Database.getInstance();}

    /**
     * Retrieves all aisles from the database.
     *
     * @return An ArrayList of Aisle objects representing all aisles in the database.
     */
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

    /**
     * Retrieves a specific aisle from the database based on its ID.
     *
     * @param id The ID of the aisle to be retrieved.
     * @return An Aisle object if found, otherwise an empty Aisle object.
     */
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

    /**
     * Retrieves aisles and their related items for display purposes.
     * This method is used to retrieve data in a format suitable for display in the UI.
     *
     * @return An ArrayList of DisplayAisle objects representing aisles and their items.
     */
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
