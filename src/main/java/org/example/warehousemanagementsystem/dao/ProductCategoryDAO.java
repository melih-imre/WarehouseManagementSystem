package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.ProductCategory;

import java.util.ArrayList;

public interface ProductCategoryDAO {
    public ArrayList<ProductCategory> getAllProductCategories();
    public ProductCategory getProductCategory(int id);
}
