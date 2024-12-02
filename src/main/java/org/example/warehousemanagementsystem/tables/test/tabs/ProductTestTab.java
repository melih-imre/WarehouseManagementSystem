package org.example.warehousemanagementsystem.tables.test.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.warehousemanagementsystem.tables.ProductsTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayProduct;
import org.example.warehousemanagementsystem.tasks.ProductInsert;

public class ProductTestTab extends Tab {

    public TableView <DisplayProduct> tableView;

    public ProductTestTab(){
        this.setText("Product Tab");
        BorderPane root = new BorderPane();
        TextField sku = new TextField();
        sku.setPromptText("Enter the SKU");
        TextField brandfield = new TextField();
        brandfield.setPromptText("Enter the brandId");
        TextField model = new TextField();
        model.setPromptText("Enter the model");
        TextField price = new TextField();
        price.setPromptText("Enter the price");

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

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e->{
            try{
            String skuName = sku.getText();
            String brandName = brandfield.getText();
            String modelName = model.getText();
            String priceValue = price.getText();
            ProductInsert productInsert = new ProductInsert(Integer.parseInt(skuName),Integer.parseInt(brandName),modelName,Integer.parseInt(priceValue));
            productInsert.run();

            sku.clear();
            model.clear();
            price.clear();

            tableView.getItems().clear();
            tableView.getItems().addAll(productsTable.getAllProducts().stream().map(product ->
                    new DisplayProduct(
                            product.getSku(),
                            product.getBrandId(),
                            product.getModel(),
                            product.getPrice())).toList());
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setContentText("Category Insert Successfully");
                successAlert.show();


            } catch (NumberFormatException ex) {
                // Handle invalid number input
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setContentText("SKU and Price must be valid integers.");
                errorAlert.show();
            } catch (Exception ex) {
                // Handle other exceptions
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setContentText("Failed to insert product. Please try again.");
                errorAlert.show();
                ex.printStackTrace();
            }
        });
        ToolBar toolBar = new ToolBar(sku,brandfield, model,price,insertButton);
        root.setRight(toolBar);
        root.setCenter(tableView);
        this.setContent(root);


    }
}
