package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.pojo.Client;
import org.example.warehousemanagementsystem.pojo.Transaction;
import org.example.warehousemanagementsystem.tables.ClientTable;
import org.example.warehousemanagementsystem.tables.ProductLocationTable;
import org.example.warehousemanagementsystem.tables.ProductsTable;
import org.example.warehousemanagementsystem.tables.TransactionTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayClient;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayProduct;

public class TransactionTab extends Tab {
    private static TransactionTab instance;
    public TableView<DisplayProduct> tableView;
    public TableView<Client> clientTableView;

    public TransactionTab() {
        this.setText("Transactions");
        this.setClosable(false);
        BorderPane root = new BorderPane();
        GridPane insertField = new GridPane();
        insertField.setVgap(10);
        insertField.setHgap(10);
        insertField.setPadding(new javafx.geometry.Insets(20));
        insertField.getStyleClass().add("transaction-tab-root");

        TransactionTable transactionTable = TransactionTable.getInstance();
        ProductLocationTable productLocationTable = ProductLocationTable.getInstance();

        ProductsTable productsTable = ProductsTable.getInstance();
        tableView = new TableView<>();
        setupTableView(productsTable);

        ClientTable clientTable = ClientTable.getInstance();
        clientTableView = new TableView<>();

        clientTableView.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5px;");

        TableColumn<Client, Number> clientIdColumn = new TableColumn<>("Client ID");
        clientIdColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        clientIdColumn.setPrefWidth(150);

        TableColumn<Client, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        firstNameColumn.setPrefWidth(150);

        TableColumn<Client, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        lastNameColumn.setPrefWidth(150);

        TableColumn<Client, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        emailColumn.setPrefWidth(200);

        clientTableView.getColumns().addAll(clientIdColumn, firstNameColumn,lastNameColumn, emailColumn);
        clientTableView.getItems().addAll(clientTable.getAllClients());


        // SKU
        Text skuLabel = new Text("SKU:");
        skuLabel.getStyleClass().add("label");
        TextField skuTextField = new TextField();
//        skuTextField.setPromptText("Enter SKU");
//        skuTextField.setTooltip(new Tooltip("Stock Keeping Unit of the product"));
        skuTextField.setPromptText("Select the product from list");
        skuTextField.setEditable(false);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedSku = newValue.getSku();
                skuTextField.setText(String.valueOf(selectedSku));
            }
        });
        skuTextField.setOnMouseClicked(e->{
            root.setCenter(tableView);
        });
        insertField.add(skuLabel, 0, 1);
        insertField.add(skuTextField, 1, 1);

        // Client ID
        Text clientIdLabel = new Text("Client ID:");
        clientIdLabel.getStyleClass().add("label");
        TextField clientIdTextField = new TextField();
        clientIdTextField.setPromptText("Select Client ID");
        clientIdTextField.setEditable(false);
        clientTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedId = newValue.getId();
                clientIdTextField.setText(String.valueOf(selectedId));
            }
        });
        clientIdTextField.setOnMouseClicked(e->{
            root.setCenter(clientTableView);
        });
        insertField.add(clientIdLabel, 0, 2);
        insertField.add(clientIdTextField, 1, 2);

        // Date
        Text dateLabel = new Text("Date:");
        dateLabel.getStyleClass().add("label");
//        TextField dateTextField = new TextField();
        DatePicker dateTextField = new DatePicker();
        dateTextField.setPromptText("Select Date");
        insertField.add(dateLabel, 0, 3);
        insertField.add(dateTextField, 1, 3);

        // Quantity
        Text quantityLabel = new Text("Quantity:");
        quantityLabel.getStyleClass().add("label");
        TextField quantityTextField = new TextField();
        quantityTextField.setPromptText("Enter Quantity");
        insertField.add(quantityLabel, 0, 4);
        insertField.add(quantityTextField, 1, 4);

        // Product Location ID
        Text locationIdLabel = new Text("Product Location ID:");
        locationIdLabel.getStyleClass().add("label");
        TextField locationIdTextField = new TextField();
        locationIdTextField.setEditable(false);
        locationIdTextField.setPromptText("Enter Location ID");
//        insertField.add(locationIdLabel, 0, 5);
//        insertField.add(locationIdTextField, 1, 5);

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
                String date = dateTextField.getValue().toString();
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

                skuTextField.clear();
                clientIdTextField.clear();
                dateTextField.setValue(null);
                quantityTextField.clear();
                locationIdTextField.clear();

            } catch (Exception ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Failed to Add Transaction");
                errorAlert.setContentText("Ensure all fields are filled correctly.");
                errorAlert.showAndWait();
                ex.printStackTrace();
            }
        });
        insertField.add(submitButton, 0, 6, 2, 1);

        root.setLeft(insertField);
//        root.setCenter(tableView);

        this.setContent(root);
    }

    public static TransactionTab getInstance() {
        if (instance == null) {
            instance = new TransactionTab();
        }
        return instance;
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