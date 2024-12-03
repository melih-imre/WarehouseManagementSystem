package org.example.warehousemanagementsystem.pojo;

/**
 * Represents a product-category association in the warehouse, linking products to their respective categories.
 * This class extends DatabaseItem to include an ID for database purposes.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
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
