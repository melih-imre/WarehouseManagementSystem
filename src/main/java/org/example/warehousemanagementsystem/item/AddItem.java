package org.example.warehousemanagementsystem.item;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.warehousemanagementsystem.pojo.Brand;
import org.example.warehousemanagementsystem.pojo.Category;
import org.example.warehousemanagementsystem.pojo.Client;
import org.example.warehousemanagementsystem.pojo.Location;
import org.example.warehousemanagementsystem.tables.BrandTable;
import org.example.warehousemanagementsystem.tables.CategoryTable;
import org.example.warehousemanagementsystem.tables.ClientTable;
import org.example.warehousemanagementsystem.tables.LocationTable;

public class AddItem extends Tab {
    public AddItem(){
        this.setText("Add Item");
        GridPane pane = new GridPane();
        BrandTable brandTable = new BrandTable();
        CategoryTable categoryTable = new CategoryTable();
        ClientTable clientTable = new ClientTable();
        LocationTable locationTable = new LocationTable();
        Label brand = new Label("Brand");
        ComboBox<Brand> comboBrand = new ComboBox<>();
        Label category = new Label("Category");
        ComboBox<Category> categoryComboBox=new ComboBox<>();
        Label client = new Label("Client");
        ComboBox<Client> clientComboBox= new ComboBox<>();
        Label location = new Label("Location");
        ComboBox<Location> locationComboBox= new ComboBox<>();

        comboBrand.getItems().addAll(brandTable.getAllBrands());
        categoryComboBox.getItems().addAll(categoryTable.getAllCategories());
        clientComboBox.getItems().addAll(clientTable.getAllClients());
        locationComboBox.getItems().addAll(locationTable.getAllLocations());

        pane.add(brand,0,0);
        pane.add(comboBrand,1,0);
        pane.add(category,0,1);
        pane.add(categoryComboBox,1,1);
        pane.add(client,0,2);
        pane.add(clientComboBox,1,2);
        pane.add(location,0,3);
        pane.add(locationComboBox,1,3);

        this.setContent(pane);








    }

}
