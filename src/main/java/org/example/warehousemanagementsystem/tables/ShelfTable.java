package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ShelfDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Shelf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

/**
 * Manages database operations related to the "Shelf" table.
 * Implements the ShelfDAO interface to define standard operations for shelves.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-16
 */

public class ShelfTable implements ShelfDAO {
    private static ShelfTable instance;
    Database db = Database.getInstance();
    ArrayList<Shelf> shelves;
    private ShelfTable(){db = Database.getInstance();}

    /**
     * Retrieves all shelves from the database.
     *
     * @return An ArrayList of Shelf objects containing all shelves in the database.
     */
    @Override
    public ArrayList<Shelf> getAllShelves() {
        String query = "SELECT * FROM " + TABLE_SHELVES;
        shelves = new ArrayList<>();
        try {
            Statement getShelves = db.getConnection().createStatement();
            ResultSet data = getShelves.executeQuery(query);
            while (data.next()){
                shelves.add(
                        new Shelf(
                                data.getInt(SHELVES_COLUMN_ID),
                                data.getString(SHELVES_COLUMN_SHELF)
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shelves;    }

    /**
     * Retrieves a specific shelf from the database based on its ID.
     *
     * @param id The ID of the shelf to retrieve.
     * @return A Shelf object representing the requested shelf, or null if not found.
     */
    @Override
    public Shelf getShelf(int id) {
        String query = "SELECT * FROM " + TABLE_SHELVES + " WHERE " + SHELVES_COLUMN_ID + " = " +id;

        try {
            Statement getShelf = db.getConnection().createStatement();
            ResultSet data = getShelf.executeQuery(query);
            if (data.next()){
                Shelf shelf = new Shelf(
                        data.getInt(SHELVES_COLUMN_ID),
                        data.getString(SHELVES_COLUMN_SHELF)
                );
                return shelf;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the singleton instance of the ShelfTable class.
     *
     * @return The singleton instance of ShelfTable.
     */

    public static ShelfTable getInstance(){
        if (instance == null) {
            instance = new ShelfTable();
        }
        return instance;
    }
}
