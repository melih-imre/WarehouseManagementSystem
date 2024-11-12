package org.example.warehousemanagementsystem.loginpage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginPage extends GridPane {
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button cancelButton;



    public LoginPage() {
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        cancelButton = new Button("Cancel");

        // Attach event handlers for buttons
        loginButton.setOnAction(event -> login());
        cancelButton.setOnAction(event -> cancel());
    }

    public GridPane getLoginPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 1, 2);
        gridPane.add(cancelButton, 1, 3);

        return gridPane;
    }

    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("password")) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Invalid Credentials");
        }
    }

    private void cancel() {
        usernameField.clear();
        passwordField.clear();
    }
}
