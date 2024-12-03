package org.example.warehousemanagementsystem.pojo;

/**
 * Represents a shelf in the warehouse.
 * This class extends DatabaseItem to include an ID for database purposes.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
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
