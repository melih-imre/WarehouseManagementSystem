package org.example.warehousemanagementsystem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.Menubar.Bar;
import org.example.warehousemanagementsystem.database.Database;

import java.io.IOException;
//import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        TabPane tabPane = new TabPane();
        Bar menu = new Bar(tabPane);
        root.setTop(menu);
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1024, 768);

        Database db = Database.getInstance();
        stage.setTitle("Warehouse Database Management System");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}