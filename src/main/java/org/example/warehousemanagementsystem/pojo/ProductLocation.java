package org.example.warehousemanagementsystem.pojo;

/**
 * Represents a product's location in the warehouse, including its SKU, aisle, shelf, and quantity.
 * This class extends DatabaseItem to include an ID for database purposes.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class ProductLocation extends DatabaseItem{
    private int sku;
    private int aisleId;
    private int shelfId;
    private int quantity;

    public ProductLocation(int id, int sku, int aisleId, int shelfId, int quantity) {
        super(id);
        this.sku = sku;
        this.aisleId = aisleId;
        this.shelfId = shelfId;
        this.quantity = quantity;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getAisleId() {
        return aisleId;
    }

    public void setAisleId(int aisleId) {
        this.aisleId = aisleId;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductLocation{" +
                "sku='" + sku + '\'' +
                ", aisleId=" + aisleId +
                ", shelfId=" + shelfId +
                ", quantity=" + quantity +
                '}';
    }
}
