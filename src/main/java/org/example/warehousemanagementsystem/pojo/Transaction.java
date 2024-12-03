package org.example.warehousemanagementsystem.pojo;

/**
 * Represents a transaction in the warehouse management system.
 * This class extends DatabaseItem to include an ID for database purposes.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class Transaction extends DatabaseItem{
    private int sku;
    private int clientId;
    private String date;
    private int quantity;
    private int productLocationId;

    public Transaction(int id, int sku, int clientId, String date, int quantity, int productLocationId) {
        super(id);
        this.sku = sku;
        this.clientId = clientId;
        this.date = date;
        this.quantity = quantity;
        this.productLocationId = productLocationId;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductLocationId() {
        return productLocationId;
    }

    public void setProductLocationId(int productLocationId) {
        this.productLocationId = productLocationId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sku='" + sku + '\'' +
                ", clientId=" + clientId +
                ", date='" + date + '\'' +
                ", quantity=" + quantity +
                ", productLocationId=" + productLocationId +
                '}';
    }
}
