package org.example.warehousemanagementsystem.tables.test.displayItems;

public class DisplayProduct {
    private int sku;
    private int brandId;
    private String model;
    private int price;

    public DisplayProduct(int sku, int brandId, String model, int price) {
        this.sku = sku;
        this.brandId = brandId;
        this.model = model;
        this.price = price;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
