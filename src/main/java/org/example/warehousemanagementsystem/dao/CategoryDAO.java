package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.Category;

import java.util.ArrayList;

public interface CategoryDAO {
    public ArrayList<Category> getAllCategories();
    public Category getCategory(int id);
}
