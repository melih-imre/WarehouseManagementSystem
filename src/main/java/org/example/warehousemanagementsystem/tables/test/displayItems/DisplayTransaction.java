package org.example.warehousemanagementsystem.tables.test.displayItems;

public class DisplayTransaction {
    private int transactionId;
    private int sku;
    private int client;
    private String date;
    private int quantity;
    private int productLocationId;

    public DisplayTransaction(int transactionId, int sku,
                              int client, String date, int quantity, int productLocationId)

    {
        this.transactionId = transactionId;
        this.sku = sku;
        this.client = client;
        this.date = date;
        this.quantity = quantity;
        this.productLocationId = productLocationId;

    }

    public int getTransactionId() {return transactionId;}
    public void setTransactionId(int transactionId) {this.transactionId = transactionId;}
    public int getSku() {return sku;}
    public void setSku(int sku) {this.sku = sku;}
    public int getClient() {return client;}
    public void setClient(int client) {this.client = client;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public int getProductLocationId() {return productLocationId;}
    public void setProductLocationId(int productLocationId) {this.productLocationId = productLocationId;}


}
