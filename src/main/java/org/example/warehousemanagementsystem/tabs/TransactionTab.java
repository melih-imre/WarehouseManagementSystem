package org.example.warehousemanagementsystem.tabs;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.pojo.Transaction;
import org.example.warehousemanagementsystem.tables.TransactionTable;

public class TransactionTab extends Tab {
    private static TransactionTab instance;

    public TransactionTab() {
        this.setText("Transactions");
        this.setClosable(false);

        // Root GridPane
        GridPane root = new GridPane();
        root.setVgap(15);
        root.setHgap(15);
        root.setPadding(new javafx.geometry.Insets(25));
        root.getStyleClass().add("transaction-tab-root");

        TransactionTable transactionTable = TransactionTable.getInstance();

        // SKU
        Text skuLabel = new Text("SKU:");
        skuLabel.getStyleClass().add("label");
        TextField skuTextField = new TextField();
        skuTextField.setPromptText("Enter SKU");
        skuTextField.setTooltip(new Tooltip("Enter the Stock Keeping Unit of the product"));
        root.add(skuLabel, 0, 1);
        root.add(skuTextField, 1, 1);

        // Client ID
        Text clientIdLabel = new Text("Client ID:");
        clientIdLabel.getStyleClass().add("label");
        TextField clientIdTextField = new TextField();
        clientIdTextField.setPromptText("Enter Client ID");
        clientIdTextField.setTooltip(new Tooltip("Enter the Client ID associated with the transaction"));
        root.add(clientIdLabel, 0, 2);
        root.add(clientIdTextField, 1, 2);

        // Date
        Text dateLabel = new Text("Date:");
        dateLabel.getStyleClass().add("label");
        TextField dateTextField = new TextField();
        dateTextField.setPromptText("Enter Date (YYYY-MM-DD)");
        dateTextField.setTooltip(new Tooltip("Provide the date of the transaction in YYYY-MM-DD format"));
        root.add(dateLabel, 0, 3);
        root.add(dateTextField, 1, 3);

        // Quantity
        Text quantityLabel = new Text("Quantity:");
        quantityLabel.getStyleClass().add("label");
        TextField quantityTextField = new TextField();
        quantityTextField.setPromptText("Enter Quantity");
        quantityTextField.setTooltip(new Tooltip("Specify the number of products involved in the transaction"));
        root.add(quantityLabel, 0, 4);
        root.add(quantityTextField, 1, 4);

        // Product Location ID
        Text locationIdLabel = new Text("Product Location ID:");
        locationIdLabel.getStyleClass().add("label");
        TextField locationIdTextField = new TextField();
        locationIdTextField.setPromptText("Enter Location ID");
        locationIdTextField.setTooltip(new Tooltip("Enter the location ID where the product is stored"));
        root.add(locationIdLabel, 0, 5);
        root.add(locationIdTextField, 1, 5);

        // Submit Button
        Button submitButton = new Button("Add Transaction");
        submitButton.getStyleClass().add("submit-button");
        submitButton.setPrefWidth(200);
        submitButton.setTooltip(new Tooltip("Click to add the transaction to the database"));
        submitButton.setOnMouseEntered(e -> submitButton.setStyle("-fx-background-color: #5cb85c; -fx-text-fill: white;"));
        submitButton.setOnMouseExited(e -> submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"));
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
                TransactionDeleteUpdateTab.getInstance().refreshTable();

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Transaction added successfully!");
                successAlert.showAndWait();
                StatisticsTab.getInstance().generateTopSellingProductsChart();
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
