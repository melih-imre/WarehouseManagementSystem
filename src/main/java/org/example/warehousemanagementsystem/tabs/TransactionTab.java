package org.example.warehousemanagementsystem.tabs;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.pojo.Transaction;
import org.example.warehousemanagementsystem.tables.ProductLocationTable;
import org.example.warehousemanagementsystem.tables.TransactionTable;

public class TransactionTab extends Tab {
    private static TransactionTab instance;

    public TransactionTab() {
        this.setText("Transactions");
        this.setClosable(false);
        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new javafx.geometry.Insets(20));
        root.getStyleClass().add("transaction-tab-root");

        TransactionTable transactionTable = TransactionTable.getInstance();
        ProductLocationTable productLocationTable = ProductLocationTable.getInstance();

        // SKU
        Text skuLabel = new Text("SKU:");
        skuLabel.getStyleClass().add("label");
        TextField skuTextField = new TextField();
        skuTextField.setPromptText("Enter SKU");
        skuTextField.setTooltip(new Tooltip("Stock Keeping Unit of the product"));
        root.add(skuLabel, 0, 1);
        root.add(skuTextField, 1, 1);

        // Client ID
        Text clientIdLabel = new Text("Client ID:");
        clientIdLabel.getStyleClass().add("label");
        TextField clientIdTextField = new TextField();
        clientIdTextField.setPromptText("Enter Client ID");
        root.add(clientIdLabel, 0, 2);
        root.add(clientIdTextField, 1, 2);

        // Date
        Text dateLabel = new Text("Date:");
        dateLabel.getStyleClass().add("label");
        TextField dateTextField = new TextField();
        dateTextField.setPromptText("Enter Date (YYYY-MM-DD)");
        root.add(dateLabel, 0, 3);
        root.add(dateTextField, 1, 3);

        // Quantity
        Text quantityLabel = new Text("Quantity:");
        quantityLabel.getStyleClass().add("label");
        TextField quantityTextField = new TextField();
        quantityTextField.setPromptText("Enter Quantity");
        root.add(quantityLabel, 0, 4);
        root.add(quantityTextField, 1, 4);

        // Product Location ID
        Text locationIdLabel = new Text("Product Location ID:");
        locationIdLabel.getStyleClass().add("label");
        TextField locationIdTextField = new TextField();
        locationIdTextField.setEditable(false);
        locationIdTextField.setPromptText("Enter Location ID");
        root.add(locationIdLabel, 0, 5);
        root.add(locationIdTextField, 1, 5);

        skuTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        int sku = Integer.parseInt(newValue);
                        int locationId = productLocationTable.getLocationIdBySku(sku);
                        locationIdTextField.setText(String.valueOf(locationId));
                    }
                }));

        // Submit Button
        Button submitButton = new Button("Add Transaction");
        submitButton.getStyleClass().add("submit-button");
        submitButton.setOnAction(e -> {
            try {
                int transactionId = 0;
                int sku = Integer.parseInt(skuTextField.getText());
                int clientId = Integer.parseInt(clientIdTextField.getText());
                String date = dateTextField.getText();
                int quantity = Integer.parseInt(quantityTextField.getText());
                int locationId = Integer.parseInt(locationIdTextField.getText());

                Transaction transaction = new Transaction(transactionId, sku, clientId, date, quantity, locationId);
                transactionTable.createTransaction(transaction);
                productLocationTable.updateQuantity(locationId, quantity);
                TransactionDeleteUpdateTab.getInstance().refreshTable();

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Transaction added successfully!");
                successAlert.showAndWait();
                StatisticsTab.getInstance().generateTopSellingProductsChart();
                StatisticsTab.getInstance().generateBarChart();

            } catch (Exception ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Failed to Add Transaction");
                errorAlert.setContentText("Ensure all fields are filled correctly.");
                errorAlert.showAndWait();
                ex.printStackTrace();
            }
        });
        root.add(submitButton, 0, 6, 2, 1);

        this.setContent(root);
    }

    public static TransactionTab getInstance() {
        if (instance == null) {
            instance = new TransactionTab();
        }
        return instance;
    }
}
