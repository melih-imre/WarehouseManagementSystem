//package org.example.warehousemanagementsystem;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.TabPane;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//import org.example.warehousemanagementsystem.Menubar.Bar;
//import org.example.warehousemanagementsystem.database.Database;
//import org.example.warehousemanagementsystem.tables.test.tabs.AislesTestTab;
//import org.example.warehousemanagementsystem.tables.test.tabs.ClientTestTab;
//import org.example.warehousemanagementsystem.tabs.ClientsTab;
//import org.example.warehousemanagementsystem.tabs.ClientsDeleteUpdateTab; // New Tab
//import org.example.warehousemanagementsystem.tabs.TransactionDeleteUpdateTab;
//import org.example.warehousemanagementsystem.tabs.TransactionTab;
//
//import java.io.IOException;
//
//public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        BorderPane root = new BorderPane();
//
//
//        Bar menu = new Bar();
//        root.setTop(menu);
//
//        TabPane tabPane = new TabPane();
//
//        ClientsTab clientsTab = new ClientsTab();
//        TransactionTab transactionTab = new TransactionTab();
//
//        ClientsDeleteUpdateTab clientsDeleteUpdateTab = new ClientsDeleteUpdateTab();
//        TransactionDeleteUpdateTab transactionDeleteUpdateTab = new TransactionDeleteUpdateTab();
//
//        tabPane.getTabs().addAll(clientsTab, transactionTab, clientsDeleteUpdateTab, transactionDeleteUpdateTab);
//
//        root.setCenter(tabPane);
//
//        Scene scene = new Scene(root, 1024, 768);
//
//        Database db = Database.getInstance();
//
//        stage.setTitle("Warehouse Database Management System");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}


//package org.example.warehousemanagementsystem;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.TabPane;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//import org.example.warehousemanagementsystem.Menubar.Bar;
//import org.example.warehousemanagementsystem.Menubar.CreditsMenu;
//import org.example.warehousemanagementsystem.database.Database;
//import org.example.warehousemanagementsystem.tabs.ClientsTab;
//import org.example.warehousemanagementsystem.tabs.TransactionTab;
//import org.example.warehousemanagementsystem.tabs.ClientsDeleteUpdateTab;
//import org.example.warehousemanagementsystem.tabs.TransactionDeleteUpdateTab;
//
//import java.io.IOException;
//
//public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        BorderPane root = new BorderPane();
//
//        Bar menu = new Bar(root);
//        root.setTop(menu);
//
//        // Set up the tab pane with your tabs
//        TabPane tabPane = new TabPane();
//
//        ClientsTab clientsTab = new ClientsTab();
//        TransactionTab transactionTab = new TransactionTab();
//        ClientsDeleteUpdateTab clientsDeleteUpdateTab = new ClientsDeleteUpdateTab();
//        TransactionDeleteUpdateTab transactionDeleteUpdateTab = new TransactionDeleteUpdateTab();
//
//        tabPane.getTabs().addAll(clientsTab, transactionTab, clientsDeleteUpdateTab, transactionDeleteUpdateTab);
//
//        root.setCenter(tabPane);
//
//
//        Scene scene = new Scene(root, 1024, 768);
//
//        Database db = Database.getInstance();
//
//        stage.setTitle("Warehouse Database Management System");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }




//}



package org.example.warehousemanagementsystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.Menubar.Bar;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.tabs.ClientsTab;
import org.example.warehousemanagementsystem.tabs.TransactionTab;
import org.example.warehousemanagementsystem.tabs.ClientsDeleteUpdateTab;
import org.example.warehousemanagementsystem.tabs.TransactionDeleteUpdateTab;

import java.io.IOException;

public class Main extends Application {
    private TabPane tabPane;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();

        Bar menu = new Bar(root, this);
        root.setTop(menu);
        tabPane = new TabPane();

        ClientsTab clientsTab = new ClientsTab();
        clientsTab.setClosable(false);
        TransactionTab transactionTab = new TransactionTab();
        transactionTab.setClosable(false);
        ClientsDeleteUpdateTab clientsDeleteUpdateTab = new ClientsDeleteUpdateTab();
        clientsDeleteUpdateTab.setClosable(false);
        TransactionDeleteUpdateTab transactionDeleteUpdateTab = new TransactionDeleteUpdateTab();
        transactionDeleteUpdateTab.setClosable(false);

        tabPane.getTabs().addAll(clientsTab, transactionTab, clientsDeleteUpdateTab, transactionDeleteUpdateTab);

        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1024, 768);

        Database db = Database.getInstance();

        stage.setTitle("Warehouse Database Management System");
        stage.setScene(scene);
        stage.show();
    }

    public void showTab(int tabIndex) {
        if (tabIndex >= 0 && tabIndex < tabPane.getTabs().size()) {
            tabPane.getSelectionModel().select(tabIndex);
        }
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public static void main(String[] args) {
        launch();
    }
}
