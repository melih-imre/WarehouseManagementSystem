package org.example.warehousemanagementsystem.tables.test.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.tables.ProductsTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayProduct;

public class ProductTestTab extends Tab {

    public TableView <DisplayProduct> tableView;

    public ProductTestTab(){
        this.setText("Product Tab");
        BorderPane root = new BorderPane();

        ProductsTable productsTable = ProductsTable.getInstance();
        tableView = new TableView<>();

        TableColumn <DisplayProduct,Integer>column1 = new TableColumn<>("SKU");
        column1.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getSku()).asObject());

        TableColumn <DisplayProduct, Integer>column2 = new TableColumn<>("BrandId");
        column2.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getBrandId()).asObject());

        TableColumn <DisplayProduct, String>column3 = new TableColumn<>("Model");
        column3.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getModel()));

        TableColumn <DisplayProduct,Integer>column4 = new TableColumn<>("Price");
        column4.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getPrice()).asObject());

        tableView.getColumns().addAll(column1,column2,column3,column4);

        tableView.getItems().addAll(productsTable.getAllProducts().stream().map(product ->
                new DisplayProduct(
                        product.getSku(),
                        product.getBrandId(),
                        product.getModel(),
                        product.getPrice())).toList());
        root.setCenter(tableView);
        this.setContent(root);


    }
}
