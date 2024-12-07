package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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

        // Title Label
        Label titleLabel = new Label("Manage Products");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setStyle("-fx-text-fill: #2c3e50; -fx-padding: 10px;");
        root.setTop(titleLabel);

        // Product Insert Form
        TextField sku = new TextField();
        sku.setPromptText("0");
        sku.setEditable(false);
        sku.setDisable(true);
        sku.setFocusTraversable(false);
        TextField brandfield = new TextField();
        brandfield.setPromptText("Enter the brandId");
        TextField model = new TextField();
        model.setPromptText("Enter the model");
        TextField price = new TextField();
        price.setPromptText("Enter the price");

//        ComboBox<String> brandComboBox = new ComboBox<>();
//        brandComboBox.setPromptText("Select Brand");
//        ArrayList<Brand> brands = BrandTable.getInstance().getAllBrands();
//        ArrayList<String> brandNames = new ArrayList<>();
//        for (Brand brand : brands) {
//            brandNames.add(brand.getBrand());
//        }
//        brandComboBox.setItems(FXCollections.observableArrayList(brandNames));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("SKU:"), 0, 0);
        grid.add(sku, 1, 0);
        grid.add(new Label("Brand ID:"), 0, 1);
        grid.add(brandfield, 1, 1);
        grid.add(new Label("Model:"), 0, 2);
        grid.add(model, 1, 2);
        grid.add(new Label("Price:"), 0, 3);
        grid.add(price, 1, 3);
//        grid.add(new Label("Brand:"), 0, 4);
//        grid.add(brandComboBox, 1, 4);

        // Table View to display products
        ProductsTable productsTable = ProductsTable.getInstance();
        tableView = new TableView<>();
        setupTableView(productsTable);

        // Insert Button
        Button insertButton = new Button("Insert");
        insertButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5px;");
        insertButton.setOnAction(e -> {
            try {
                String skuName = "0";
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

        // Delete Product Section
//        TextField deleteId = new TextField();
//        deleteId.setPromptText("Enter the SKU to delete");
//        deleteId.setStyle("-fx-border-color: #e74c3c; -fx-border-radius: 5px; -fx-padding: 5px;");

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5px;");
        deleteButton.setOnAction(e -> {
            DisplayProduct selectedProduct = tableView.getSelectionModel().getSelectedItem();

            if (selectedProduct != null) {
                int skuToDelete = selectedProduct.getSku();

                try {
                    // Check if the product exists in the database
                    Product productToDelete = ProductsTable.getInstance().getProduct(skuToDelete);
                    if (productToDelete != null) {
                        // Execute the delete task
                        DeleteProductTask deleteProductTask = new DeleteProductTask(skuToDelete);
                        if (deleteProductTask.execute()) {
                            // Remove the product from the TableView
                            tableView.getItems().remove(selectedProduct);

                            // Show success message
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setContentText("Product deleted successfully!");
                            successAlert.show();
                        } else {
                            // Show failure message
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setContentText("Failed to delete the product.");
                            errorAlert.show();
                        }
                    } else {
                        // Show product not found message
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setContentText("Product not found!");
                        errorAlert.show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setContentText("An error occurred while deleting the product.");
                    errorAlert.show();
                }
            } else {
                // Show warning if no product is selected
                Alert errorAlert = new Alert(Alert.AlertType.WARNING);
                errorAlert.setTitle("No Selection");
                errorAlert.setContentText("Please select a product to delete.");
                errorAlert.show();
            }
        });

        HBox buttonBar = new HBox(10, insertButton, deleteButton);
        buttonBar.setStyle("-fx-spacing: 10px; -fx-padding: 10px; -fx-alignment: center;");

        VBox rightPane = new VBox(10, grid, buttonBar);
        rightPane.setStyle("-fx-padding: 15px;");
        rightPane.setPrefWidth(350);

        root.setLeft(rightPane);
        root.setCenter(tableView);

        this.setContent(root);
    }

    private void setupTableView(ProductsTable productsTable) {
        TableColumn<DisplayProduct, Integer> column1 = new TableColumn<>("SKU");
        column1.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getSku()).asObject());

        TableColumn<DisplayProduct, Integer> column2 = new TableColumn<>("Brand ID");
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
    }


}
