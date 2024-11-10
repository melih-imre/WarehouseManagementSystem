
package org.example.warehousemanagementsystem.Menubar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.Main;
import org.example.warehousemanagementsystem.database.Database;

import java.io.IOException;

public class Form extends Main {
    @Override
    public void start(Stage stage) throws IOException{
        BorderPane root = new BorderPane();
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu creditsMenu = new Menu("Credits");

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));

        fileMenu.getItems().addAll(exit);
        menuBar.getMenus().addAll(fileMenu, creditsMenu);

        root.setTop(menuBar);
        Scene scene = new Scene(root, 1024, 768);
        // Initialize Database instance (ensure the Database class handles getInstance() properly)
        Database db = Database.getInstance();
        stage.setTitle("Warehouse Database Management System");
        stage.setScene(scene);
        stage.show();
    }
}
