package org.example.warehousemanagementsystem.pojo;

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
