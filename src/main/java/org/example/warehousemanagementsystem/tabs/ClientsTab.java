package org.example.warehousemanagementsystem.tabs;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.example.warehousemanagementsystem.pojo.Client;
import org.example.warehousemanagementsystem.tables.ClientTable;

import java.util.ArrayList;

public class ClientsTab extends Tab {
    private static ClientsTab instance;

    public ClientsTab() {
        this.setText("Clients");
        this.setClosable(false);
        GridPane root = new GridPane();
        root.setVgap(15);
        root.setHgap(10);
        root.setPadding(new Insets(20));

        ClientTable clientTable = ClientTable.getInstance();

        // Styling the labels
        Text firstNameLabel = new Text("First Name:");
        firstNameLabel.setFont(Font.font("Arial", 14));
        firstNameLabel.setFill(Color.BLACK);
        TextField firstNameTextField = new TextField();
        styleTextField(firstNameTextField);
        root.add(firstNameLabel, 0, 0);
        root.add(firstNameTextField, 1, 0);

        Text lastNameLabel = new Text("Last Name:");
        lastNameLabel.setFont(Font.font("Arial", 14));
        lastNameLabel.setFill(Color.BLACK);
        TextField lastNameTextField = new TextField();
        styleTextField(lastNameTextField);
        root.add(lastNameLabel, 0, 1);
        root.add(lastNameTextField, 1, 1);

        Text emailLabel = new Text("Email:");
        emailLabel.setFont(Font.font("Arial", 14));
        emailLabel.setFill(Color.BLACK);
        TextField emailTextField = new TextField();
        styleTextField(emailTextField);
        root.add(emailLabel, 0, 2);
        root.add(emailTextField, 1, 2);

        Text phoneLabel = new Text("Phone:");
        phoneLabel.setFont(Font.font("Arial", 14));
        phoneLabel.setFill(Color.BLACK);
        TextField phoneTextField = new TextField();
        styleTextField(phoneTextField);
        root.add(phoneLabel, 0, 3);
        root.add(phoneTextField, 1, 3);

        Text streetNumberLabel = new Text("Street Number:");
        streetNumberLabel.setFont(Font.font("Arial", 14));
        streetNumberLabel.setFill(Color.BLACK);
        TextField streetNumberTextField = new TextField();
        styleTextField(streetNumberTextField);
        root.add(streetNumberLabel, 0, 4);
        root.add(streetNumberTextField, 1, 4);

        Text streetNameLabel = new Text("Street Name:");
        streetNameLabel.setFont(Font.font("Arial", 14));
        streetNameLabel.setFill(Color.BLACK);
        TextField streetNameTextField = new TextField();
        styleTextField(streetNameTextField);
        root.add(streetNameLabel, 0, 5);
        root.add(streetNameTextField, 1, 5);

        // Styling ComboBox for City
        Text cityLabel = new Text("City:");
        cityLabel.setFont(Font.font("Arial", 14));
        cityLabel.setFill(Color.BLACK);
        ComboBox<String> comboCity = new ComboBox<>();
        comboCity.setItems(FXCollections.observableArrayList(
                clientTable.getAllClients().stream().map(Client::getCity).distinct().toList()
        ));
        styleComboBox(comboCity);
        root.add(cityLabel, 0, 6);
        root.add(comboCity, 1, 6);

        // Styling ComboBox for State
        Text stateLabel = new Text("State:");
        stateLabel.setFont(Font.font("Arial", 14));
        stateLabel.setFill(Color.BLACK);
        ComboBox<String> comboState = new ComboBox<>();
        comboState.setPromptText("Select State");
        ArrayList<String> states = new ArrayList<>();
        states.add("AB");
        states.add("BC");
        states.add("MB");
        states.add("NB");
        states.add("NL");
        states.add("NT");
        states.add("NS");
        states.add("NU");
        states.add("ON");
        states.add("PE");
        states.add("QC");
        states.add("SK");
        states.add("YT");

        comboState.setItems(FXCollections.observableArrayList(states));

        styleComboBox(comboState);
        root.add(stateLabel, 0, 7);
        root.add(comboState, 1, 7);

        // Submit button styling
        Button submitButton = new Button("Add Client");
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
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
                        0,
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

                // Clear fields after submit
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

    private void styleTextField(TextField textField) {
        textField.setStyle("-fx-padding: 5px; -fx-border-color: #ccc; -fx-background-color: #f9f9f9;");
    }

    private void styleComboBox(ComboBox<String> comboBox) {
        comboBox.setStyle("-fx-padding: 5px; -fx-border-color: #ccc; -fx-background-color: #f9f9f9;");
    }

    public static ClientsTab getInstance() {
        if (instance == null) {
            instance = new ClientsTab();
        }
        return instance;
    }
}
