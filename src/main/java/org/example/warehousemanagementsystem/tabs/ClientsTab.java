
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
        this.setClosable(false);
        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);

        ClientTable clientTable = ClientTable.getInstance();
        Text firstNameLabel = new Text("First Name:");
        TextField firstNameTextField = new TextField();
        root.add(firstNameLabel, 0, 0);
        root.add(firstNameTextField, 1, 0);

        Text lastNameLabel = new Text("Last Name:");
        TextField lastNameTextField = new TextField();
        root.add(lastNameLabel, 0, 1);
        root.add(lastNameTextField, 1, 1);


        Text emailLabel = new Text("Email:");
        TextField emailTextField = new TextField();
        root.add(emailLabel, 0, 2);
        root.add(emailTextField, 1, 2);

        Text phoneLabel = new Text("Phone:");
        TextField phoneTextField = new TextField();
        root.add(phoneLabel, 0, 3);
        root.add(phoneTextField, 1, 3);

        Text streetNumberLabel = new Text("Street Number:");
        TextField streetNumberTextField = new TextField();
        root.add(streetNumberLabel, 0, 4);
        root.add(streetNumberTextField, 1, 4);

        Text streetNameLabel = new Text("Street Name:");
        TextField streetNameTextField = new TextField();
        root.add(streetNameLabel, 0, 5);
        root.add(streetNameTextField, 1, 5);

        Text cityLabel = new Text("City:");
        ComboBox<String> comboCity = new ComboBox<>();
        comboCity.setItems(FXCollections.observableArrayList(
                clientTable.getAllClients().stream().map(Client::getCity).distinct().toList()
        ));
        root.add(cityLabel, 0, 6);
        root.add(comboCity, 1, 6);

        Text stateLabel = new Text("State:");
        ComboBox<String> comboState = new ComboBox<>();
        comboState.setItems(FXCollections.observableArrayList(
                clientTable.getAllClients().stream().map(Client::getState).distinct().toList()
        ));
        root.add(stateLabel, 0, 7);
        root.add(comboState, 1, 7);

        Button submitButton = new Button("Add Client");
        submitButton.setOnAction(e -> {

            if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() ||
                    emailTextField.getText().isEmpty() || phoneTextField.getText().isEmpty() ||
                    streetNumberTextField.getText().isEmpty() || streetNameTextField.getText().isEmpty() ||
                    comboCity.getValue() == null || comboState.getValue() == null) {

                System.out.println("Please fill in all fields correctly.");

                firstNameTextField.clear();
                lastNameTextField.clear();
                emailTextField.clear();
                phoneTextField.clear();
                streetNumberTextField.clear();
                streetNameTextField.clear();
                comboCity.setValue(null);
                comboState.setValue(null);


                return;
            }

            try {
                Client client = new Client(
                        0, //
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        emailTextField.getText(),
                        phoneTextField.getText(),
                        streetNumberTextField.getText(),
                        streetNameTextField.getText(),
                        comboCity.getValue(),
                        comboState.getValue()
                );

                clientTable.createClient(client);
                ClientsDeleteUpdateTab clientsDeleteUpdateTab = ClientsDeleteUpdateTab.getInstance();
                clientsDeleteUpdateTab.refreshTable();

                firstNameTextField.clear();
                lastNameTextField.clear();
                emailTextField.clear();
                phoneTextField.clear();
                streetNumberTextField.clear();
                streetNameTextField.clear();
                comboCity.setValue(null);
                comboState.setValue(null);

                System.out.println("Client added successfully.");


            } catch (Exception ex) {
                System.out.println("An error occurred while adding the client.");
                ex.printStackTrace();
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

