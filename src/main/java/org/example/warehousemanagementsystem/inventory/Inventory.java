package org.example.warehousemanagementsystem.inventory;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.Main;

public class Inventory extends BorderPane {
    public Inventory() {



        TabPane tabPane = new TabPane();
        Tab order = new Tab("Order");
        Tab inventory = new Tab("Inventory");
        Tab returns = new Tab("Returns");
        Tab locations = new Tab("Location");
        tabPane.getTabs().addAll(order, inventory, returns, locations);
        tabPane.setSide(Side.LEFT);
        Label brand = new Label("Brand");
        Label sku = new Label("SKU");
        Label description = new Label("Description");
        Label location = new Label("Location");
        Label customer = new Label("Customer");
        TextField customerfield = new TextField();
        TextField brandfield = new TextField();
        TextField skuField = new TextField();
        TextField locationfield = new TextField();
        TextField descriptionField = new TextField();
        customerfield.setMaxWidth(200);
        brandfield.setMaxWidth(200);
        skuField.setMaxWidth(200);
        locationfield.setMaxWidth(200);
        descriptionField.setMaxWidth(250);
        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(customer, customerfield, brand, brandfield, sku, skuField, location, locationfield,
                description, descriptionField);
        tabPane.setRotateGraphic(false);
        this.setLeft(tabPane);
        this.setCenter(vBox);



    }

}
