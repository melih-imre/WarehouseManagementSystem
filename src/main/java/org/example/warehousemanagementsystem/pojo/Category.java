package org.example.warehousemanagementsystem.pojo;

/**
 * Represents a category in the warehouse management system.
 * This class extends {@link DatabaseItem} and contains the category's name.
 * The category can be identified by its ID and its name.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class Category extends DatabaseItem{
    private String category;

    public Category(int id, String category) {
        super(id);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                '}';
    }
}
