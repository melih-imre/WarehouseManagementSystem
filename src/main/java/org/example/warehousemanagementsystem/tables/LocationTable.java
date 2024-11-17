package org.example.warehousemanagementsystem.tables;

import org.example.warehousemanagementsystem.dao.LocationDAO;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.pojo.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.warehousemanagementsystem.database.DBConst.*;

public class LocationTable implements LocationDAO {
    private static LocationTable instance;
    Database db = Database.getInstance();
    ArrayList<Location> locations;
    private LocationTable(){db = Database.getInstance();}

    @Override
    public ArrayList<Location> getAllLocations() {
        String query = "SELECT * FROM " + TABLE_LOCATIONS;

        locations = new ArrayList<>();
        try {
            Statement getLocations = db.getConnection().createStatement();
            ResultSet resultSet = getLocations.executeQuery(query);

            while (resultSet.next()){
                locations.add(new Location(
                        resultSet.getInt(LOCATIONS_COLUMN_ID),
                        resultSet.getInt(LOCATIONS_COLUMN_AISLE_ID),
                        resultSet.getInt(LOCATIONS_COLUMN_SHELF_ID),
                        resultSet.getInt(LOCATIONS_COLUMN_CAPACITY)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public Location getLocation(int id) {
        String query = "SELECT * FROM " + TABLE_LOCATIONS + " WHERE " + LOCATIONS_COLUMN_ID + " = " +id;

        try {
            Statement getLocation = db.getConnection().createStatement();
            ResultSet data = getLocation.executeQuery(query);
            if (data.next()){
                Location location = new Location(
                        data.getInt(LOCATIONS_COLUMN_ID),
                        data.getInt(LOCATIONS_COLUMN_AISLE_ID),
                        data.getInt(LOCATIONS_COLUMN_SHELF_ID),
                        data.getInt(LOCATIONS_COLUMN_CAPACITY)
                );
                return location;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocationTable getInstance(){
        if (instance == null) {
            instance = new LocationTable();
        }
        return instance;
    }
}
