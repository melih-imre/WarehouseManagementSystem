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
import org.example.warehousemanagementsystem.pojo.Client;
import org.example.warehousemanagementsystem.tables.ClientTable;

public class ClientsDeleteUpdateTab extends Tab {
    private static ClientsDeleteUpdateTab instance;
    private TableView<Client> tableView;

    public ClientsDeleteUpdateTab() {
        this.setText("Manage Clients");
        ClientTable clientTable = ClientTable.getInstance();

        BorderPane root = new BorderPane();
        tableView = new TableView<>();

        TableColumn<Client, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));

        TableColumn<Client, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));

        TableColumn<Client, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn);
        tableView.getItems().addAll(clientTable.getAllClients());

        root.setCenter(tableView);

        Button deleteButton = new Button("Delete Client");
        deleteButton.setOnAction(e -> {
            Client selectedClient = tableView.getSelectionModel().getSelectedItem();
            if (selectedClient != null) {
                clientTable.deleteClient(selectedClient.getId());
                refreshTable();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
                if (newValue != null) {
                    UpdateClientPane updatePane = new UpdateClientPane(newValue);
                    root.setRight(updatePane);
                }
            }
        });

        root.setBottom(deleteButton);
        this.setContent(root);
    }

    public void refreshTable() {
        ClientTable clientTable = ClientTable.getInstance();
        tableView.getItems().clear();
        tableView.getItems().addAll(clientTable.getAllClients());
    }

    public static ClientsDeleteUpdateTab getInstance() {
        if (instance == null) {
            instance = new ClientsDeleteUpdateTab();
        }
        return instance;
    }

    private class UpdateClientPane extends GridPane {
        public UpdateClientPane(Client client) {
            this.setPadding(new Insets(10, 10, 10, 10));
            this.setVgap(10);
            this.setHgap(10);

            ClientTable clientTable = ClientTable.getInstance();

            Text firstNameLabel = new Text("First Name:");
            TextField firstNameField = new TextField(client.getFirstName());
            this.add(firstNameLabel, 0, 0);
            this.add(firstNameField, 1, 0);

            Text lastNameLabel = new Text("Last Name:");
            TextField lastNameField = new TextField(client.getLastName());
            this.add(lastNameLabel, 0, 1);
            this.add(lastNameField, 1, 1);

            Text emailLabel = new Text("Email:");
            TextField emailField = new TextField(client.getEmail());
            this.add(emailLabel, 0, 2);
            this.add(emailField, 1, 2);

            Button updateButton = new Button("Update Client");
            updateButton.setOnAction(e -> {
                client.setFirstName(firstNameField.getText());
                client.setLastName(lastNameField.getText());
                client.setEmail(emailField.getText());
                clientTable.updateClient(client);
                refreshTable();
            });

            this.add(updateButton, 1, 3);
        }
    }
}
