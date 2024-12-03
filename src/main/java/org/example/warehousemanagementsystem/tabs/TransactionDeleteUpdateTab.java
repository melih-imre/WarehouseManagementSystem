package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.pojo.Transaction;
import org.example.warehousemanagementsystem.tables.TransactionTable;

public class TransactionDeleteUpdateTab extends Tab {
    private static TransactionDeleteUpdateTab instance;
    private TableView<Transaction> tableView;

    public TransactionDeleteUpdateTab() {
        this.setText("Manage Transactions");
        this.setClosable(false);
        TransactionTable transactionTable = TransactionTable.getInstance();

        BorderPane root = new BorderPane();
        tableView = new TableView<>();

        // Styled columns
        TableColumn<Transaction, String> idColumn = new TableColumn<>("Transaction ID");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        idColumn.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        TableColumn<Transaction, String> skuColumn = new TableColumn<>("SKU");
        skuColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSku())));
        skuColumn.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        TableColumn<Transaction, String> clientIdColumn = new TableColumn<>("Client ID");
        clientIdColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getClientId())));
        clientIdColumn.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        TableColumn<Transaction, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        dateColumn.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        TableColumn<Transaction, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getQuantity())));
        quantityColumn.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        TableColumn<Transaction, String> locationIdColumn = new TableColumn<>("Location ID");
        locationIdColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getProductLocationId())));
        locationIdColumn.setStyle("-fx-font-weight: bold; -fx-alignment: center;");

        tableView.getColumns().addAll(idColumn, skuColumn, clientIdColumn, dateColumn, quantityColumn, locationIdColumn);
        tableView.getItems().addAll(transactionTable.getAllTransactions());

        // Row styling
        tableView.setRowFactory(tv -> {
            TableRow<Transaction> row = new TableRow<>();
            row.setStyle("-fx-background-color: #f9f9f9;");
            row.hoverProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    row.setStyle("-fx-background-color: #e0e0e0;");
                } else {
                    row.setStyle("-fx-background-color: #f9f9f9;");
                }
            });
            return row;
        });

        root.setCenter(tableView);

        // Delete Button styling
        Button deleteButton = new Button("Delete Transaction");
        deleteButton.setStyle("-fx-background-color: #ff6666; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        deleteButton.setOnAction(e -> {
            Transaction selectedTransaction = tableView.getSelectionModel().getSelectedItem();
            if (selectedTransaction != null) {
                transactionTable.deleteTransaction(selectedTransaction.getId());
                refreshTable();
            }
        });

        // Add some padding around the delete button
        deleteButton.setPadding(new Insets(10, 20, 10, 20));

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Transaction> observable, Transaction oldValue, Transaction newValue) {
                if (newValue != null) {
                    UpdateTransactionPane updatePane = new UpdateTransactionPane(newValue);
                    root.setRight(updatePane);
                }
            }
        });

        root.setBottom(deleteButton);
        this.setContent(root);
    }

    public void refreshTable() {
        TransactionTable transactionTable = TransactionTable.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(transactionTable.getAllTransactions());
    }

    public static TransactionDeleteUpdateTab getInstance() {
        if (instance == null) {
            instance = new TransactionDeleteUpdateTab();
        }
        return instance;
    }

    private class UpdateTransactionPane extends GridPane {
        public UpdateTransactionPane(Transaction transaction) {
            this.setPadding(new Insets(20, 20, 20, 20));
            this.setVgap(15);
            this.setHgap(15);

            TransactionTable transactionTable = TransactionTable.getInstance();

            // Styled Labels and TextFields
            Text skuLabel = new Text("SKU:");
            skuLabel.setStyle("-fx-font-weight: bold;");
            TextField skuField = new TextField(String.valueOf(transaction.getSku()));
            skuField.setStyle("-fx-background-color: #f0f0f0; -fx-border-radius: 5px;");

            Text clientIdLabel = new Text("Client ID:");
            clientIdLabel.setStyle("-fx-font-weight: bold;");
            TextField clientIdField = new TextField(String.valueOf(transaction.getClientId()));
            clientIdField.setStyle("-fx-background-color: #f0f0f0; -fx-border-radius: 5px;");

            Text dateLabel = new Text("Date:");
            dateLabel.setStyle("-fx-font-weight: bold;");
            TextField dateField = new TextField(transaction.getDate());
            dateField.setStyle("-fx-background-color: #f0f0f0; -fx-border-radius: 5px;");

            Text quantityLabel = new Text("Quantity:");
            quantityLabel.setStyle("-fx-font-weight: bold;");
            TextField quantityField = new TextField(String.valueOf(transaction.getQuantity()));
            quantityField.setStyle("-fx-background-color: #f0f0f0; -fx-border-radius: 5px;");

            Text locationIdLabel = new Text("Location ID:");
            locationIdLabel.setStyle("-fx-font-weight: bold;");
            TextField locationIdField = new TextField(String.valueOf(transaction.getProductLocationId()));
            locationIdField.setStyle("-fx-background-color: #f0f0f0; -fx-border-radius: 5px;");

            // Update Button styling
            Button updateButton = new Button("Update Transaction");
            updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
            updateButton.setOnAction(e -> {
                transaction.setSku(Integer.parseInt(skuField.getText()));
                transaction.setClientId(Integer.parseInt(clientIdField.getText()));
                transaction.setDate(dateField.getText());
                transaction.setQuantity(Integer.parseInt(quantityField.getText()));
                transaction.setProductLocationId(Integer.parseInt(locationIdField.getText()));

                transactionTable.updateTransaction(transaction);
                refreshTable();
            });

            // Add elements to grid with padding and spacing
            this.add(skuLabel, 0, 0);
            this.add(skuField, 1, 0);
            this.add(clientIdLabel, 0, 1);
            this.add(clientIdField, 1, 1);
            this.add(dateLabel, 0, 2);
            this.add(dateField, 1, 2);
            this.add(quantityLabel, 0, 3);
            this.add(quantityField, 1, 3);
            this.add(locationIdLabel, 0, 4);
            this.add(locationIdField, 1, 4);
            this.add(updateButton, 1, 5);
        }
    }
}
