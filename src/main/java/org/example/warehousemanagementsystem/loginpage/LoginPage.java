package org.example.warehousemanagementsystem.loginpage;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.example.warehousemanagementsystem.database.Database;

// Fields
public class LoginPage extends GridPane {
    private TextField serverLocationField;
    private TextField usernameField;
    private Button cancelButton;
    private PasswordField passwordField;
    private Button loginButton;
    private TextField dbNameField;


//Buttons
    public LoginPage() {
        dbNameField = new TextField();
        serverLocationField = new TextField();
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        cancelButton = new Button("Cancel");

        loginButton.setOnAction(event -> login());
        cancelButton.setOnAction(event -> cancel());
    }
    public GridPane getLoginPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label dbNameLabel = new Label("Database Name:");
        Label serverLocationLabel = new Label("Server Location:");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");


        // column and row positions
        gridPane.add(dbNameLabel, 0, 0);
        gridPane.add(dbNameField, 1, 0);
        gridPane.add(serverLocationLabel, 0, 1);
        gridPane.add(serverLocationField, 1, 1);
        gridPane.add(usernameLabel, 0, 2);
        gridPane.add(usernameField, 1, 2);
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(passwordField, 1, 3);
        gridPane.add(loginButton, 1, 4);
        gridPane.add(cancelButton, 1, 5);

        return gridPane;
    }

    // Method that handles the login stuff
    private void login() {
        String dbName = dbNameField.getText();
        String serverLocation = serverLocationField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();


        try {
            Database.getInstance().connect(serverLocation, dbName, username, password);
            System.out.println("Login Successful. Connected to Database!");
        } catch (Exception e) {
            System.out.println("Failed to connect: " + e.getMessage());
        }
    }

    // Method to clears all
    private void cancel() {
        dbNameField.clear();
        serverLocationField.clear();
        usernameField.clear();
        passwordField.clear();
    }
}
