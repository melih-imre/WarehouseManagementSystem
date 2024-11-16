package org.example.warehousemanagementsystem.pojo;

public class ProductCategory extends DatabaseItem{
    private int categoryId;

    public ProductCategory(int id, int categoryId) {
        super(id);
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                '}';
    }
}
