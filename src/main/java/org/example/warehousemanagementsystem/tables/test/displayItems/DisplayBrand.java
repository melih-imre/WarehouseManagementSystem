package org.example.warehousemanagementsystem.tables.test.displayItems;

public class DisplayBrand {
    private int brandId;
    private String brand;

    public DisplayBrand(int brandId, String brand) {
        this.brandId = brandId;
        this.brand = brand;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
