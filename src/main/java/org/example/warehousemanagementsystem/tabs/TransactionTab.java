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
        TextField transactionIdTextField = new TextField();
        root.add(transactionIdLabel, 0, 0);
        root.add(transactionIdTextField, 1, 0);
//

        // SKU
        Text skuLabel = new Text("SKU:");
        TextField skuTextField = new TextField();
        root.add(skuLabel, 0, 1);
        root.add(skuTextField, 1, 1);

        // Client ID
        Text clientIdLabel = new Text("Client ID:");
        TextField clientIdTextField = new TextField();
        root.add(clientIdLabel, 0, 2);
        root.add(clientIdTextField, 1, 2);


        // Date
        Text dateLabel = new Text("Date:");
        TextField dateTextField = new TextField();
        root.add(dateLabel, 0, 3);
        root.add(dateTextField, 1, 3);


        // Quantity
        Text quantityLabel = new Text("Quantity:");
        TextField quantityTextField = new TextField();
        root.add(quantityLabel, 0, 4);
        root.add(quantityTextField, 1, 4);



        // Product Location ID
        Text locationIdLabel = new Text("Product Location ID:");
        TextField locationIdTextField = new TextField();
        root.add(locationIdLabel, 0, 5);
        root.add(locationIdTextField, 1, 5);





        // Submit Button
        Button submitButton = new Button("Add Transaction");
        submitButton.setOnAction(e -> {
            try {


                int transactionId = Integer.parseInt(transactionIdTextField.getText());
                String sku = skuTextField.getText();
                int clientId = Integer.parseInt(clientIdTextField.getText());
                String date = dateTextField.getText();
                int quantity = Integer.parseInt(quantityTextField.getText());
                int locationId = Integer.parseInt(String.valueOf(locationIdTextField));
                Transaction transaction = new Transaction(
                        transactionId,sku,clientId,date,quantity,locationId
                );

                // Simulate adding transaction to the table
                //tableransactionTable.getAllTransactions().add(transaction);

                transactionTable.createTransaction(transaction);


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

