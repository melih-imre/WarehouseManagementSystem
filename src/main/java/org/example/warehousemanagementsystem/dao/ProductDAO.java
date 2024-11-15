package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.Product;

import java.util.ArrayList;

public interface ProductDAO {
    public ArrayList<Product> getAllCoins();
    public Product getProduct(int id);
}
