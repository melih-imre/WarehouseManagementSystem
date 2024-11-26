package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.Location;

import java.util.ArrayList;

public interface LocationDAO {
    public ArrayList<Location> getAllLocations();
    public Location getLocation(int id);
}
