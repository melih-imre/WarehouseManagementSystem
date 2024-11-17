package org.example.warehousemanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.Home.Homepa;
import org.example.warehousemanagementsystem.database.Database;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Homepa homepa = new Homepa();
        BorderPane homeLayout = homepa.getHomePageLayout();

        Scene scene = new Scene(homeLayout, 1024, 768);
        stage.setTitle("Warehouse Database Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
