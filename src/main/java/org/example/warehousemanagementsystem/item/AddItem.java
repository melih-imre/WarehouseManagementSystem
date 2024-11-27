package org.example.warehousemanagementsystem.item;

import javafx.scene.control.*;
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
        this.setText("AddItem");
        ComboBox<Location>locationComboBox = new ComboBox<>();
        TextField brandName = new TextField();
        TextField category = new TextField();
        TextField clientName = new TextField();
        TextField clientEmail = new TextField();
        TextField clientPhone = new TextField();
        TextField clientAddress = new TextField();







    }

}
