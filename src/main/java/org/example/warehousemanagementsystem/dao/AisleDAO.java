package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.Aisle;

import java.util.ArrayList;

public interface AisleDAO {
    public ArrayList<Aisle> getAllAisles();
    public Aisle getAisle(int id);

}
