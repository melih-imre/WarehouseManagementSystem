package org.example.warehousemanagementsystem.tabs;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.pojo.Transaction;
import org.example.warehousemanagementsystem.tables.TransactionTable;

public class TransactionTab extends Tab {
    private static TransactionTab instance;

    public TransactionTab() {
        this.setText("Transactions");
        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);

        TransactionTable transactionTable = TransactionTable.getInstance();

        // Transaction ID
        Text transactionIdLabel = new Text("Transaction ID:");
        ComboBox<Integer> comboTransactionId = new ComboBox<>();
        comboTransactionId.setItems(FXCollections.observableArrayList(
                transactionTable.getAllTransactions().stream().map(Transaction::getId).toList()
        ));
        root.add(transactionIdLabel, 0, 0);
        root.add(comboTransactionId, 1, 0);

        // SKU
        Text skuLabel = new Text("SKU:");
        ComboBox<String> comboSku = new ComboBox<>();
        comboSku.setItems(FXCollections.observableArrayList(
                transactionTable.getAllTransactions().stream().map(Transaction::getSku).toList()
        ));
        root.add(skuLabel, 0, 1);
        root.add(comboSku, 1, 1);

        // Client ID
        Text clientIdLabel = new Text("Client ID:");
        ComboBox<Integer> comboClientId = new ComboBox<>();
        comboClientId.setItems(FXCollections.observableArrayList(
                transactionTable.getAllTransactions().stream().map(Transaction::getClientId).toList()
        ));
        root.add(clientIdLabel, 0, 2);
        root.add(comboClientId, 1, 2);

        // Date
        Text dateLabel = new Text("Date:");
        ComboBox<String> comboDate = new ComboBox<>();
        comboDate.setItems(FXCollections.observableArrayList(
                transactionTable.getAllTransactions().stream().map(Transaction::getDate).toList()
        ));
        root.add(dateLabel, 0, 3);
        root.add(comboDate, 1, 3);

        // Quantity
        Text quantityLabel = new Text("Quantity:");
        ComboBox<Integer> comboQuantity = new ComboBox<>();
        comboQuantity.setItems(FXCollections.observableArrayList(
                transactionTable.getAllTransactions().stream().map(Transaction::getQuantity).toList()
        ));
        root.add(quantityLabel, 0, 4);
        root.add(comboQuantity, 1, 4);

        // Product Location ID
        Text locationIdLabel = new Text("Product Location ID:");
        ComboBox<Integer> comboLocationId = new ComboBox<>();
        comboLocationId.setItems(FXCollections.observableArrayList(
                transactionTable.getAllTransactions().stream().map(Transaction::getProductLocationId).toList()
        ));
        root.add(locationIdLabel, 0, 5);
        root.add(comboLocationId, 1, 5);

        // Submit Button
        Button submitButton = new Button("Add Transaction");
        submitButton.setOnAction(e -> {
            try {
                // Create a new transaction based on selected values
                Transaction transaction = new Transaction(
                        comboTransactionId.getValue(),
                        comboSku.getValue(),
                        comboClientId.getValue(),
                        comboDate.getValue(),
                        comboQuantity.getValue(),
                        comboLocationId.getValue()
                );

                // Simulate adding transaction to the table
                transactionTable.getAllTransactions().add(transaction);

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Transaction added successfully!");
                successAlert.showAndWait();
            } catch (Exception ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Failed to Add Transaction");
                errorAlert.setContentText("Ensure all fields are filled correctly.");
                errorAlert.showAndWait();
            }
        });
        root.add(submitButton, 0, 6);

        this.setContent(root);
    }

    public static TransactionTab getInstance() {
        if (instance == null) {
            instance = new TransactionTab();
        }
        return instance;
    }
}
