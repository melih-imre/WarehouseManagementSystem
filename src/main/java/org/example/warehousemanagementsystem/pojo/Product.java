package org.example.warehousemanagementsystem.pojo;

/**
 * Represents a product in the warehouse with details such as SKU, brand ID, model, and price.
 * This class extends DatabaseItem to include an ID for database purposes.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class Product extends DatabaseItem{
    private int brandId;
    private String model;
    private int sku;
    private int price;

    public Product(int sku, int brandId, String model, int price) {

        this.brandId = brandId;
        this.model = model;
        this.price = price;
        this.sku = sku;
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

    @Override
    public String toString() {
        return "Product{" +
                "Sku= "+sku+
                "brandId=" + brandId +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
