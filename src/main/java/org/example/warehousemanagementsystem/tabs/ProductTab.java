package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.warehousemanagementsystem.pojo.Brand;
import org.example.warehousemanagementsystem.pojo.Product;
import org.example.warehousemanagementsystem.tables.BrandTable;
import org.example.warehousemanagementsystem.tables.ProductsTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayProduct;
import org.example.warehousemanagementsystem.tasks.DeleteProductTask;
import org.example.warehousemanagementsystem.tasks.ProductInsert;

import java.util.ArrayList;

public class ProductTab extends Tab {

    public TableView<DisplayProduct> tableView;

    public ProductTab() {
        this.setText("Product Tab");
        this.setClosable(false);
        BorderPane root = new BorderPane();

        Label titleLabel = new Label("Manage Products");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #2c3e50; -fx-padding: 10px;");
        root.setTop(titleLabel);

        TextField sku = new TextField();
        sku.setPromptText("Enter the SKU");
        sku.setStyle("-fx-border-color: #3498db; -fx-border-radius: 5px; -fx-padding: 5px;");

        TextField brandfield = new TextField();
        brandfield.setPromptText("Enter the brandId");
        brandfield.setStyle("-fx-border-color: #3498db; -fx-border-radius: 5px; -fx-padding: 5px;");

        TextField model = new TextField();
        model.setPromptText("Enter the model");
        model.setStyle("-fx-border-color: #3498db; -fx-border-radius: 5px; -fx-padding: 5px;");

        TextField price = new TextField();
        price.setPromptText("Enter the price");
        price.setStyle("-fx-border-color: #3498db; -fx-border-radius: 5px; -fx-padding: 5px;");

        ComboBox<String> brandComboBox = new ComboBox<>();
        brandComboBox.setPromptText("Select Brand");
        brandComboBox.setStyle("-fx-border-color: #3498db; -fx-border-radius: 5px;");
        ArrayList<Brand> brands = BrandTable.getInstance().getAllBrands();
        ArrayList<String> brandNames = new ArrayList<>();
        for (Brand brand : brands) {
            brandNames.add(brand.getBrand());
        }
        brandComboBox.setItems(FXCollections.observableArrayList(brandNames));

        TextField deleteId = new TextField();
        deleteId.setPromptText("Enter the item to delete");
        deleteId.setStyle("-fx-border-color: #e74c3c; -fx-border-radius: 5px; -fx-padding: 5px;");

        ProductsTable productsTable = ProductsTable.getInstance();
        tableView = new TableView<>();

        TableColumn<DisplayProduct, Integer> column1 = new TableColumn<>("SKU");
        column1.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getSku()).asObject());

        TableColumn<DisplayProduct, Integer> column2 = new TableColumn<>("BrandId");
        column2.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getBrandId()).asObject());

        TableColumn<DisplayProduct, String> column3 = new TableColumn<>("Model");
        column3.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getModel()));

        TableColumn<DisplayProduct, Integer> column4 = new TableColumn<>("Price");
        column4.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getPrice()).asObject());

        tableView.getColumns().addAll(column1, column2, column3, column4);
        tableView.getItems().addAll(productsTable.getAllProducts().stream().map(product ->
                new DisplayProduct(
                        product.getSku(),
                        product.getBrandId(),
                        product.getModel(),
                        product.getPrice())).toList());
        tableView.setStyle("-fx-padding: 10px; -fx-background-color: #ecf0f1;");

        Button insertButton = new Button("Insert");
        insertButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5px;");
        insertButton.setOnAction(e -> {
            try {
                String skuName = sku.getText();
                String brandName = brandfield.getText();
                String modelName = model.getText();
                String priceValue = price.getText();
                ProductInsert productInsert = new ProductInsert(Integer.parseInt(skuName), Integer.parseInt(brandName), modelName, Integer.parseInt(priceValue));
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
                successAlert.setContentText("Product inserted successfully.");
                successAlert.show();
            } catch (NumberFormatException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setContentText("SKU and Price must be valid integers.");
                errorAlert.show();
            } catch (Exception ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setContentText("Failed to insert product. Please try again.");
                errorAlert.show();
                ex.printStackTrace();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5px;");
        deleteButton.setOnAction(e -> {
            try {
                String delete = deleteId.getText();
                if (!delete.isEmpty()) {
                    int brandId = Integer.parseInt(delete);
                    Product brandToDelete = ProductsTable.getInstance().getProduct(brandId);
                    if (brandToDelete != null) {
                        DeleteProductTask deleteProductTask = new DeleteProductTask(brandId);
                        if (deleteProductTask.execute()) {
                            tableView.getItems().removeIf(product -> product.getSku() == brandId);
                            deleteId.clear();

                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setContentText("Successfully deleted.");
                            successAlert.show();
                        }
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setContentText("Product not found.");
                        errorAlert.show();
                    }
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setContentText("Please enter a valid SKU.");
                    errorAlert.show();
                }
            } catch (NumberFormatException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        });

        ToolBar toolBar = new ToolBar(sku, brandfield, model, price, brandComboBox, insertButton);
        toolBar.setStyle("-fx-background-color: #bdc3c7; -fx-padding: 10px;");
        BorderPane pane = new BorderPane();
        pane.setRight(deleteId);
        pane.setTop(deleteButton);

        root.setBottom(pane);
        root.setRight(toolBar);
        root.setCenter(tableView);
        this.setContent(root);
    }
}
