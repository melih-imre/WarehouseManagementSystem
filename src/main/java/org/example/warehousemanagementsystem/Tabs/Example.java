package org.example.warehousemanagementsystem.Tabs;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class Example extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();


        Tab homeTab = new Tab();
        homeTab.setText("Home");
        Label homeLabel = new Label("Welcome to the Home Screen!");
        homeTab.setContent(homeLabel);


        Tab profileTab = new Tab();
        profileTab.setText("Profile");
        VBox profileLayout = new VBox(10);
        profileLayout.setAlignment(Pos.CENTER);
        Label profileLabel = new Label("User Profile");
        Button updateButton = new Button("Update Profile");
        profileLayout.getChildren().addAll(profileLabel, updateButton);
        profileTab.setContent(profileLayout);



        Tab exitTab = new Tab();
        exitTab.setText("Exit");
        exitTab.setClosable(false);
        Button exitButton = new Button("Exit Application");
        exitButton.setOnAction(e -> confirmExit(primaryStage));
        StackPane exitLayout = new StackPane();
        exitLayout.getChildren().add(exitButton);
        exitTab.setContent(exitLayout);



        tabPane.getTabs().addAll(homeTab, profileTab, exitTab);



        Scene scene = new Scene(tabPane, 400, 300);
        primaryStage.setTitle("JavaFX Tab Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void confirmExit(Stage stage) {
        Alert exitAlert = new Alert(AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit Confirmation");
        exitAlert.setHeaderText("Are you sure you want to exit?");
        exitAlert.setContentText("Click OK to exit or Cancel to stay.");

        exitAlert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                stage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
