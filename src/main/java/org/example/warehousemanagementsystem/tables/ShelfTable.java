package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.ShelfDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Shelf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class ShelfTable implements ShelfDAO {
    private static ShelfTable instance;
    Database db = Database.getInstance();
    ArrayList<Shelf> shelves;
    private ShelfTable(){db = Database.getInstance();}
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

    public static ShelfTable getInstance(){
        if (instance == null) {
            instance = new ShelfTable();
        }
        return instance;
    }
}
