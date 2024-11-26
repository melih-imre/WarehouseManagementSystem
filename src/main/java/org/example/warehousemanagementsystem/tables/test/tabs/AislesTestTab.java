package org.example.warehousemanagementsystem.tables.test.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.tables.AisleTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayAisle;

public class AislesTestTab extends Tab {
    public TableView tableView;
    public AislesTestTab(){
        this.setText("AISLES");
        BorderPane root = new BorderPane();
        AisleTable aisleTable = new AisleTable();
        tableView = new TableView();
        TableColumn<DisplayAisle, Integer> column1 =
                new TableColumn<>("Aisle Id");
        column1.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getAisleId()).asObject());

        TableColumn<DisplayAisle, String> column2 =
                new TableColumn<>("Aisle");
        column2.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAisle()));

        tableView.getColumns().addAll(column1, column2);
        tableView.getItems().addAll(aisleTable.getItems());
        root.setCenter(tableView);
        this.setContent(root);


    }
}
