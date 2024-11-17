package org.example.warehousemanagementsystem.pojo;

public class Shelf extends DatabaseItem{
    private String shelf;

    public Shelf(int id, String shelf) {
        super(id);
        this.shelf = shelf;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "shelf='" + shelf + '\'' +
                '}';
    }
}
