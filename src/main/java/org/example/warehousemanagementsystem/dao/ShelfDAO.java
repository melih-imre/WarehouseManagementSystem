package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.Shelf;

import java.util.ArrayList;

public interface ShelfDAO {
    public ArrayList<Shelf> getAllShelves();
    public Shelf getShelf(int id);
}
