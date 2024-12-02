package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
        TransactionTable transactionTable = TransactionTable.getInstance();

        BorderPane root = new BorderPane();
        tableView = new TableView<>();

        TableColumn<Transaction, String> idColumn = new TableColumn<>("Transaction ID");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));

        TableColumn<Transaction, String> skuColumn = new TableColumn<>("SKU");
        skuColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSku()));

        TableColumn<Transaction, String> clientIdColumn = new TableColumn<>("Client ID");
        clientIdColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getClientId())));

        TableColumn<Transaction, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));

        TableColumn<Transaction, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getQuantity())));

        TableColumn<Transaction, String> locationIdColumn = new TableColumn<>("Location ID");
        locationIdColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getProductLocationId())));

        tableView.getColumns().addAll(idColumn, skuColumn, clientIdColumn, dateColumn, quantityColumn, locationIdColumn);
        tableView.getItems().addAll(transactionTable.getAllTransactions());

        root.setCenter(tableView);

        Button deleteButton = new Button("Delete Transaction");
        deleteButton.setOnAction(e -> {
            Transaction selectedTransaction = tableView.getSelectionModel().getSelectedItem();
            if (selectedTransaction != null) {
                transactionTable.deleteTransaction(selectedTransaction.getId());
                refreshTable();
            }
        });

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
            this.setPadding(new Insets(10, 10, 10, 10));
            this.setVgap(10);
            this.setHgap(10);

            TransactionTable transactionTable = TransactionTable.getInstance();

            Text skuLabel = new Text("SKU:");
            TextField skuField = new TextField(transaction.getSku());
            this.add(skuLabel, 0, 0);
            this.add(skuField, 1, 0);

            Text clientIdLabel = new Text("Client ID:");
            TextField clientIdField = new TextField(String.valueOf(transaction.getClientId()));
            this.add(clientIdLabel, 0, 1);
            this.add(clientIdField, 1, 1);

            Text dateLabel = new Text("Date:");
            TextField dateField = new TextField(transaction.getDate());
            this.add(dateLabel, 0, 2);
            this.add(dateField, 1, 2);

            Text quantityLabel = new Text("Quantity:");
            TextField quantityField = new TextField(String.valueOf(transaction.getQuantity()));
            this.add(quantityLabel, 0, 3);
            this.add(quantityField, 1, 3);

            Text locationIdLabel = new Text("Location ID:");
            TextField locationIdField = new TextField(String.valueOf(transaction.getProductLocationId()));
            this.add(locationIdLabel, 0, 4);
            this.add(locationIdField, 1, 4);

            Button updateButton = new Button("Update Transaction");
            updateButton.setOnAction(e -> {
                transaction.setSku(skuField.getText());
                transaction.setClientId(Integer.parseInt(clientIdField.getText()));
                transaction.setDate(dateField.getText());
                transaction.setQuantity(Integer.parseInt(quantityField.getText()));
                transaction.setProductLocationId(Integer.parseInt(locationIdField.getText()));

                transactionTable.updateTransaction(transaction);
                refreshTable();
            });

            this.add(updateButton, 1, 5);
        }
    }
}
