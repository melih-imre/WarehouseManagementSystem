package org.example.warehousemanagementsystem.tables.test.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.pojo.Location;
import org.example.warehousemanagementsystem.tables.LocationTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayCategory;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayLocation;

public class LocationTestTab extends Tab {
    public TableView <DisplayLocation> tableView;

    public LocationTestTab(){
        this.setText("Location");
        BorderPane pane = new BorderPane();
        LocationTable locationTable = new LocationTable();
        tableView =new TableView<>();
        TableColumn<DisplayLocation,Integer>column1=new TableColumn<>("Aisle Id");
        column1.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getAisleId()).asObject());
        TableColumn<DisplayLocation,Integer>column2 = new TableColumn<>("Shelf Id");
        column2.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getAisleId()).asObject());
        TableColumn<DisplayLocation,Integer>column3 = new TableColumn<>("Capacity");
        column3.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getAisleId()).asObject());
        tableView.getColumns().addAll(column1,column2,column3);
        tableView.getItems().addAll(locationTable.getAllLocations().stream().map(
                location -> new DisplayLocation(location.getAisleId(),location.getShelfId(),location.getCapacity()))
                        .toList());
    }



}
