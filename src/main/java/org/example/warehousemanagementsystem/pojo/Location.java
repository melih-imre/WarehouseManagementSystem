package org.example.warehousemanagementsystem.pojo;

import org.example.warehousemanagementsystem.database.Database;

public class Location extends DatabaseItem {
    private int aisleId;
    private int shelfId;
    private int capacity;

    public Location(int id, int aisleId, int shelfId, int capacity) {
        super(id);
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

    @Override
    public String toString() {
        return "Location{" +
                "aisleId=" + aisleId +
                ", shelfId=" + shelfId +
                ", capacity=" + capacity +
                '}';
    }
}
