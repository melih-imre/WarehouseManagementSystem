package org.example.warehousemanagementsystem.pojo;

/**
 * Represents a base class for items stored in the database.
 * This class is intended to be extended by other classes that represent specific items with an ID.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class DatabaseItem {
    private int id;

    public DatabaseItem(int id) {this.id = id;}

    public DatabaseItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(){
        this.id = id;
    }
}
