package org.example.warehousemanagementsystem.tables.test.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.tables.TransactionTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayTransaction;

public class TranscationTestTab extends Tab {
    public TableView tableView;
    public TranscationTestTab() {
        this.setText("Transcations");
        BorderPane root = new BorderPane();
        TransactionTable transactionTable = new TransactionTable() ;
        tableView = new TableView() ;


        // Transaction ID column
        TableColumn<DisplayTransaction, Integer> column1 =
                new TableColumn<>("Transaction ID");
        column1.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getTransactionId()).asObject());

        // SKU column
        TableColumn<DisplayTransaction, String> column2 =
                new TableColumn<>("SKU");
        column2.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getSku()));

        // Client ID column
        TableColumn<DisplayTransaction, Integer> column3 =
                new TableColumn<>("Client ID");
        column3.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getClient()).asObject());

        // Date column
        TableColumn<DisplayTransaction, String> column4 =
                new TableColumn<>("Date");
        column4.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getDate()));

        // Quantity column
        TableColumn<DisplayTransaction, Integer> column5 =
                new TableColumn<>("Quantity");
        column5.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getQuantity()).asObject());

        // Product Location ID column
        TableColumn<DisplayTransaction, Integer> column6 =
                new TableColumn<>("Product Location ID");
        column6.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getProductLocationId()).asObject());

        // Add columns to the table view
        tableView.getColumns().addAll(column1, column2, column3, column4, column5, column6);

        // Add data to the table view
        tableView.getItems().addAll(transactionTable.getAllTransactions().stream()
                .map(transaction -> new DisplayTransaction(
                        transaction.getId(),
                        transaction.getSku(),
                        transaction.getClientId(),
                        transaction.getDate(),
                        transaction.getQuantity(),
                        transaction.getProductLocationId()
                )).toList());

        // Set the table view as the center of the layout
        root.setCenter(tableView);
        this.setContent(root);
    }
}

