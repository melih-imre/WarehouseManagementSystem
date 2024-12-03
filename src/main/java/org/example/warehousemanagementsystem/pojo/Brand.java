package org.example.warehousemanagementsystem.pojo;

/**
 * Represents a brand in the warehouse management system.
 * This class extends {@link DatabaseItem} and contains the brand's name.
 * The brand can be identified by its ID and its name.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
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
