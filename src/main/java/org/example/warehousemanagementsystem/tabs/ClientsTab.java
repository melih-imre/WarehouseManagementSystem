package org.example.warehousemanagementsystem.tabs;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.pojo.Client;
import org.example.warehousemanagementsystem.tables.ClientTable;

public class ClientsTab extends Tab {
    private static ClientsTab instance;

    public ClientsTab() {
        this.setText("Clients");
        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);

        ClientTable clientTable = ClientTable.getInstance();

        // First Name
        Text firstNameLabel = new Text("First Name:");
        TextField firstNameTextField = new TextField();
        root.add(firstNameLabel, 0, 0);
        root.add(firstNameTextField, 1, 0);
//

        // Last Name
        Text lastNameLabel = new Text("Last Name:");
        TextField lastNameTextField = new TextField();
        root.add(lastNameLabel, 0, 1);
        root.add(lastNameTextField, 1, 1);


        // Email
        Text emailLabel = new Text("Email:");
        TextField emailTextField = new TextField();
        root.add(emailLabel, 0, 2);
        root.add(emailTextField, 1, 2);

//


        // Phone
        Text phoneLabel = new Text("Phone:");
        TextField phoneTextField = new TextField();
        root.add(phoneLabel, 0, 3);
        root.add(phoneTextField, 1, 3);

//
        // Street Number
        Text streetNumberLabel = new Text("Street Number:");
        TextField streetNumberTextField = new TextField();
        root.add(streetNumberLabel, 0, 4);
        root.add(streetNumberTextField, 1, 4);

//

        // Street Name
        Text streetNameLabel = new Text("Street Name:");
        TextField streetNameTextField = new TextField();
        root.add(streetNameLabel, 0, 5);
        root.add(streetNameTextField, 1, 5);


//

        // City
        Text cityLabel = new Text("City:");
        ComboBox<String> comboCity = new ComboBox<>();
        comboCity.setItems(FXCollections.observableArrayList(
                clientTable.getAllClients().stream().map(Client::getCity).toList()
        ));
        root.add(cityLabel, 0, 6);
        root.add(comboCity, 1, 6);

        // State
        Text stateLabel = new Text("State:");
        ComboBox<String> comboState = new ComboBox<>();
        comboState.setItems(FXCollections.observableArrayList(
                clientTable.getAllClients().stream().map(Client::getState).toList()
        ));
        root.add(stateLabel, 0, 7);
        root.add(comboState, 1, 7);

        // Submit Button
        Button submitButton = new Button("Add Client");
        submitButton.setOnAction(e -> {
            // Create a new client based on selected values
            try {
                Client client = new Client(
                        0, // ID is auto-generated
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        emailTextField.getText(),
                        phoneTextField.getText(),
                        streetNumberTextField.getText(),
                        streetNameTextField.getText(),
                        comboCity.getValue(),
                        comboState.getValue()
                );
                clientTable.getAllClients().add(client); // Simulate addition
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Client added successfully!");
                successAlert.showAndWait();

                firstNameTextField.clear();
                lastNameTextField.clear();
                emailTextField.clear();
                phoneTextField.clear();
                streetNumberTextField.clear();
                streetNameTextField.clear();

            } catch (Exception ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Failed to Add Client");
                errorAlert.setContentText("Ensure all fields are filled correctly.");
                errorAlert.showAndWait();
            }
        });
        root.add(submitButton, 0, 8);


            this.setContent(root);
        }

        public static ClientsTab getInstance() {
            if (instance == null) {
                instance = new ClientsTab();
            }
            return instance;
        }
    }






