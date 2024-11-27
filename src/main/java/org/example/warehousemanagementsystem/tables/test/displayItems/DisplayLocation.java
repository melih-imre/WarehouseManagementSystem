package org.example.warehousemanagementsystem.tables.test.displayItems;

public class DisplayLocation {
    private int aisleId;
    private int shelfId;
    private int capacity;

    public DisplayLocation(int aisleId, int shelfId, int capacity) {
        this.aisleId = aisleId;
        this.shelfId = shelfId;
        this.capacity = capacity;
    }

    public int getAisleId() {
        return aisleId;
    }

    public void setAisleId(int aisleId) {
        this.aisleId = aisleId;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
