package org.example.warehousemanagementsystem.pojo;

public class Brand extends DatabaseItem {
    private String brand;

    public Brand(int id, String brand) {
        super(id);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
