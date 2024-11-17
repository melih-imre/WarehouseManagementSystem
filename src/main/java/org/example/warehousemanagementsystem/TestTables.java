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

public class TestTables extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Bar menu = new Bar();
        root.setTop(menu);
        TabPane tabPane = new TabPane();
        AislesTestTab aislesTab = new AislesTestTab();
        ClientTestTab clientTestTab = new ClientTestTab();
        tabPane.getTabs().addAll(aislesTab,clientTestTab);
        root.setCenter(tabPane);

        Database db = Database.getInstance();
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
