package org.example.warehousemanagementsystem.pojo;

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
