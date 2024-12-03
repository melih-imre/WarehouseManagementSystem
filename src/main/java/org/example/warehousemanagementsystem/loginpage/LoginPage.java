package org.example.warehousemanagementsystem.loginpage;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.example.warehousemanagementsystem.Main;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.gui.scenes.HomeScene;

import java.io.IOException;

/**
 * Login page for the Warehouse Management System.
 * Allows users to enter credentials and connect to the database.
 *
 * @author Numan
 * @date Dec 3, 2024
 * @version 1.0
 */
public class LoginPage extends GridPane {
    private TextField usernameField;
    private Button cancelButton;
    private PasswordField passwordField;
    private Button loginButton;
    private TextField dbNameField;

    /**
     * Sets up the login page with input fields and buttons.
     *
     * @throws IOException If loading fails.
     */
    public LoginPage() throws IOException {
        dbNameField = new TextField();
        dbNameField.setText(Manager.getDbName());
        usernameField = new TextField();
        usernameField.setText(Manager.getUsername());
        passwordField = new PasswordField();
        passwordField.setText(Manager.getPassword());
        loginButton = new Button("Login");
        cancelButton = new Button("Cancel");

        loginButton.setOnAction(event -> login());
        cancelButton.setOnAction(event -> cancel());
    }

    /**
     * Builds and returns the layout of the login page.
     *
     * @return The GridPane layout for the login page.
     */
    public GridPane getLoginPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(40));
        gridPane.setHgap(20);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: #add8e6;");

        Label dbNameLabel = new Label("Database Name:");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        dbNameField.setStyle("-fx-background-color: #ffffff;");
        usernameField.setStyle("-fx-background-color: #ffffff;");
        passwordField.setStyle("-fx-background-color: #ffffff;");
        loginButton.setStyle("-fx-background-color: #4caf50;");
        cancelButton.setStyle("-fx-background-color: #f44336;");

        loginButton.setOnMouseEntered(event -> loginButton.setStyle("-fx-background-color: #45a049;"));
        loginButton.setOnMouseExited(event -> loginButton.setStyle("-fx-background-color: #4caf50;"));
        cancelButton.setOnMouseEntered(event -> cancelButton.setStyle("-fx-background-color: #e53935;"));
        cancelButton.setOnMouseExited(event -> cancelButton.setStyle("-fx-background-color: #f44336;"));

        gridPane.add(dbNameLabel, 0, 0);
        gridPane.add(dbNameField, 1, 0);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(usernameField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(loginButton, 1, 3);
        gridPane.add(cancelButton, 1, 4);

        gridPane.setAlignment(javafx.geometry.Pos.CENTER);

        return gridPane;
    }

    /**
     * Attempts to log in using the provided credentials.
     * If successful, transitions to the home scene.
     */
    private void login() {
        String dbName = dbNameField.getText();
        String serverLocation = "";
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Database.getInstance().connect(serverLocation, dbName, username, password);
            System.out.println("Login Successful.");
            Manager.info(serverLocation, dbName, username, password);
            Main.mainStage.setScene(new HomeScene());
        } catch (Exception e) {
            System.out.println("Failed to connect: " + e.getMessage());
        }
    }

    /**
     * Clears all input fields when the cancel button is pressed.
     */
    private void cancel() {
        dbNameField.clear();
        usernameField.clear();
        passwordField.clear();
    }
}
