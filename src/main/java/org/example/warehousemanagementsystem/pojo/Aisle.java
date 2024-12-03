package org.example.warehousemanagementsystem.pojo;

/**
 * Represents an aisle in the warehouse.
 * This class extends {@link DatabaseItem} and contains the aisle information.
 * The aisle can be identified by its ID and its name.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class Aisle extends DatabaseItem{
    private String aisle;

    public Aisle(int id, String aisle) {
        super(id);
        this.aisle = aisle;
    }
    public Aisle(){

    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    @Override
    public String toString() {
        return "Aisle{" +
                "aisle='" + aisle + '\'' +
                '}';
    }
}
