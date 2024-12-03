package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.pojo.Client;
import org.example.warehousemanagementsystem.tables.ClientTable;

public class ClientsDeleteUpdateTab extends Tab {
    private static ClientsDeleteUpdateTab instance;
    private TableView<Client> tableView;

    public ClientsDeleteUpdateTab() {
        this.setText("Manage Clients");
        this.setClosable(false);

        // Root container with padding and spacing
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f8f8f8;");

        ClientTable clientTable = ClientTable.getInstance();
        tableView = new TableView<>();
        tableView.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5px;");

        TableColumn<Client, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));
        firstNameColumn.setPrefWidth(150);

        TableColumn<Client, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));
        lastNameColumn.setPrefWidth(150);

        TableColumn<Client, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        emailColumn.setPrefWidth(200);

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn);
        tableView.getItems().addAll(clientTable.getAllClients());

        // Set prompt for no selection
        tableView.setPlaceholder(new Label("No clients to display"));

        root.setCenter(tableView);

        // Bottom pane for delete button with spacing
        HBox bottomPane = new HBox();
        bottomPane.setSpacing(10);
        bottomPane.setPadding(new Insets(10, 0, 0, 0));
        bottomPane.setStyle("-fx-background-color: #e9ecef;");

        Button deleteButton = new Button("Delete Client");
        deleteButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold;");
        deleteButton.setTooltip(new Tooltip("Select a client and click to delete"));
        deleteButton.setOnAction(e -> {
            Client selectedClient = tableView.getSelectionModel().getSelectedItem();
            if (selectedClient != null) {
                clientTable.deleteClient(selectedClient.getId());
                refreshTable();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Client deleted successfully.");
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "No client selected for deletion.");
            }
        });

        bottomPane.getChildren().add(deleteButton);
        root.setBottom(bottomPane);

        // Right pane for updates
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
                if (newValue != null) {
                    UpdateClientPane updatePane = new UpdateClientPane(newValue);
                    root.setRight(updatePane);
                }
            }
        });

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
            this.setPadding(new Insets(15));
            this.setHgap(10);
            this.setVgap(10);
            this.setStyle("-fx-border-color: #17a2b8; -fx-border-radius: 5px; -fx-background-color: #f1f8ff;");

            ClientTable clientTable = ClientTable.getInstance();

            // Styling labels and inputs
            Text firstNameLabel = new Text("First Name:");
            firstNameLabel.setFont(Font.font("Arial", 14));
            TextField firstNameField = new TextField(client.getFirstName());
            firstNameField.setPromptText("Enter first name");

            Text lastNameLabel = new Text("Last Name:");
            lastNameLabel.setFont(Font.font("Arial", 14));
            TextField lastNameField = new TextField(client.getLastName());
            lastNameField.setPromptText("Enter last name");

            Text emailLabel = new Text("Email:");
            emailLabel.setFont(Font.font("Arial", 14));
            TextField emailField = new TextField(client.getEmail());
            emailField.setPromptText("Enter email");

            Button updateButton = new Button("Update Client");
            updateButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold;");
            updateButton.setTooltip(new Tooltip("Click to update the client's details"));
            updateButton.setOnAction(e -> {
                client.setFirstName(firstNameField.getText());
                client.setLastName(lastNameField.getText());
                client.setEmail(emailField.getText());
                clientTable.updateClient(client);
                refreshTable();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Client updated successfully.");
            });

            this.add(firstNameLabel, 0, 0);
            this.add(firstNameField, 1, 0);
            this.add(lastNameLabel, 0, 1);
            this.add(lastNameField, 1, 1);
            this.add(emailLabel, 0, 2);
            this.add(emailField, 1, 2);
            this.add(updateButton, 1, 3);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
