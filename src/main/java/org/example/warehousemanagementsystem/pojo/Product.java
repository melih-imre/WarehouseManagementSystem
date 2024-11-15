package org.example.warehousemanagementsystem.pojo;

public class Product extends DatabaseItem{
    private int brandId;
    private String model;
    private int price;

    public Product(int id, int brandId, String model, int price) {
        super(id);
        this.brandId = brandId;
        this.model = model;
        this.price = price;
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

    @Override
    public String toString() {
        return "Product{" +
                "brandId=" + brandId +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
