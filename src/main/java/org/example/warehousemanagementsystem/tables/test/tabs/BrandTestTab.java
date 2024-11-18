package org.example.warehousemanagementsystem.tables.test.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.tables.BrandTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayBrand;

public class BrandTestTab extends Tab {
    public TableView<DisplayBrand> tableView;

    public BrandTestTab(){
        this.setText("Brands");
        BorderPane pane = new BorderPane();
        BrandTable brandTable = new BrandTable();
        tableView = new TableView<>();
        TableColumn<DisplayBrand,Integer> column1 = new TableColumn<>("Brand Id");
        column1.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getBrandId()).asObject());
        TableColumn<DisplayBrand,String>column2= new TableColumn<>("Brand");
        column2.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getBrand()));
        tableView.getColumns().addAll(column1,column2);
         tableView.getItems().addAll(brandTable.getAllBrands().stream().map(brand -> new DisplayBrand(brand.getId(), brand.getBrand())).toList());

         pane.setCenter(tableView);
         this.setContent(pane);



    }

}
