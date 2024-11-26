package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.ProductLocation;

import java.util.ArrayList;

public interface ProductLocationDAO {
    public ArrayList<ProductLocation> getAllProductLocations();
    public ProductLocation getProductLocation(int id);
}
