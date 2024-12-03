package org.example.warehousemanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.tables.test.tabs.*;
import org.example.warehousemanagementsystem.tabs.BrandTab;
import org.example.warehousemanagementsystem.tabs.CategoryTab;
import org.example.warehousemanagementsystem.tabs.ProductTab;

public class TestTables extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
//        Bar menu = new Bar(new TabPane());
//        root.setTop(menu);
        TabPane tabPane = new TabPane();

        AislesTestTab aislesTab = new AislesTestTab();
        ClientTestTab clientTestTab = new ClientTestTab();
        BrandTab brandTab = new BrandTab();
        CategoryTab categoryTab =new CategoryTab();
        ProductTab productTab = new ProductTab();
        tabPane.getTabs().addAll(aislesTab,clientTestTab, brandTab, categoryTab, productTab);
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
