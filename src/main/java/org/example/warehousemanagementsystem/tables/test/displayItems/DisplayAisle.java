package org.example.warehousemanagementsystem.tables.test.displayItems;

public class DisplayAisle {
    private int aisleId;
    private String aisle;

    public DisplayAisle(int aisleId, String aisle) {
        this.aisleId = aisleId;
        this.aisle = aisle;
    }

    public int getAisleId() {
        return aisleId;
    }

    public void setAisleId(int aisleId) {
        this.aisleId = aisleId;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
}
