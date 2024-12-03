package org.example.warehousemanagementsystem.loginpage;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.Main;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.gui.scenes.HomeScene;

import java.io.IOException;

public class LoginPage extends GridPane {
    private TextField usernameField;
    private Button cancelButton;
    private PasswordField passwordField;
    private Button loginButton;
    private TextField dbNameField;

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

    public GridPane getLoginPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(40));
        gridPane.setHgap(20);
        gridPane.setVgap(15);
        gridPane.setStyle("-fx-background-color: #add8e6;");  // Light blue background

        Label dbNameLabel = new Label("Database Name:");
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        dbNameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
        usernameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");

        dbNameField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #333333; -fx-border-radius: 25px; -fx-padding: 10;");
        usernameField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #333333; -fx-border-radius: 25px; -fx-padding: 10;");
        passwordField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #333333; -fx-border-radius: 25px; -fx-padding: 10;");
        loginButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 25px;");
        cancelButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 25px;");

        // Buttons hover effects
        loginButton.setOnMouseEntered(event -> loginButton.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 25px;"));
        loginButton.setOnMouseExited(event -> loginButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 25px;"));
        cancelButton.setOnMouseEntered(event -> cancelButton.setStyle("-fx-background-color: #e53935; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 25px;"));
        cancelButton.setOnMouseExited(event -> cancelButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-border-radius: 25px;"));

        // column and row positions
        gridPane.add(dbNameLabel, 0, 0);
        gridPane.add(dbNameField, 1, 0);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(usernameField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(loginButton, 1, 3);
        gridPane.add(cancelButton, 1, 4);

        // Centering the entire GridPane
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);

        return gridPane;
    }

    private void login() {
        String dbName = dbNameField.getText();
        String serverLocation = "";
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Database.getInstance().connect(serverLocation, dbName, username, password);
            System.out.println("Login Successful. Connected to Database!");
            Manager.info(serverLocation, dbName, username, password);

            Main.mainStage.setScene(new HomeScene());
        } catch (Exception e) {
            System.out.println("Failed to connect: " + e.getMessage());
        }
    }

    private void cancel() {
        dbNameField.clear();
        usernameField.clear();
        passwordField.clear();
    }
}
