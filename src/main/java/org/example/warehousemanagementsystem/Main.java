package org.example.warehousemanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.Menubar.Bar;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.tables.test.tabs.AislesTestTab;
import org.example.warehousemanagementsystem.tables.test.tabs.ClientTestTab;
import org.example.warehousemanagementsystem.tabs.ClientsTab;
import org.example.warehousemanagementsystem.tabs.ClientsDeleteUpdateTab; // New Tab
import org.example.warehousemanagementsystem.tabs.TransactionDeleteUpdateTab;
import org.example.warehousemanagementsystem.tabs.TransactionTab;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Root layout
        BorderPane root = new BorderPane();

        // Add Menu Bar
        Bar menu = new Bar();
        root.setTop(menu);

        // Initialize TabPane
        TabPane tabPane = new TabPane();

        // Existing Tabs
        ClientsTab clientsTab = new ClientsTab();
        TransactionTab transactionTab = new TransactionTab();

        // New Tabs
        ClientsDeleteUpdateTab clientsDeleteUpdateTab = new ClientsDeleteUpdateTab();
        TransactionDeleteUpdateTab transactionDeleteUpdateTab = new TransactionDeleteUpdateTab();

        // Add all tabs to TabPane
        tabPane.getTabs().addAll(clientsTab, transactionTab, clientsDeleteUpdateTab, transactionDeleteUpdateTab);

        // Set TabPane to the center of the root layout
        root.setCenter(tabPane);

        // Create scene and set dimensions
        Scene scene = new Scene(root, 1024, 768);

        // Initialize database
        Database db = Database.getInstance();

        // Configure stage
        stage.setTitle("Warehouse Database Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
