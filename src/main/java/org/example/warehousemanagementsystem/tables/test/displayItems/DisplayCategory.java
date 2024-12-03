package org.example.warehousemanagementsystem.tables.test.displayItems;

public class DisplayCategory {
    private int id;
    private String category;

    public DisplayCategory(int id,String category){
        this.category = category;
        this.id=id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
