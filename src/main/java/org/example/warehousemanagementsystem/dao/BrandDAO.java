package org.example.warehousemanagementsystem.dao;

import org.example.warehousemanagementsystem.pojo.Brand;

import java.util.ArrayList;

public interface BrandDAO {
    public ArrayList<Brand> getAllBrands();
    public Brand getBrand(int id);

    public boolean addBrand(Brand brand);
}
