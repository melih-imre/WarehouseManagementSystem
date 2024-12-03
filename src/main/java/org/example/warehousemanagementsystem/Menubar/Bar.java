package org.example.warehousemanagementsystem.Menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.Main;

public class Bar extends MenuBar {
    public Bar(BorderPane root, Main mainApp) {
        this.getStyleClass().add("menu-bar");

        Menu fileMenu = new Menu("File");
        fileMenu.getStyleClass().add("menu");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.getStyleClass().add("menu-item");
        exitMenuItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exitMenuItem);

        Menu viewMenu = new Menu("View");
        viewMenu.getStyleClass().add("menu");
        MenuItem clientsMenuItem = new MenuItem("Clients");
        clientsMenuItem.getStyleClass().add("menu-item");
        MenuItem transactionsMenuItem = new MenuItem("Transactions");
        transactionsMenuItem.getStyleClass().add("menu-item");
        MenuItem creditsMenuItem = new MenuItem("Credits");
        creditsMenuItem.getStyleClass().add("menu-item");

//        clientsMenuItem.setOnAction(e -> {
//            root.setCenter(mainApp.getTabPane());
//            mainApp.showTab(0);
//        });
//
//        transactionsMenuItem.setOnAction(e -> {
//            root.setCenter(mainApp.getTabPane());
//            mainApp.showTab(1);
//        });

        creditsMenuItem.setOnAction(e -> {
            Text creditsText = new Text("Developer Information:\n\n" +
                    "Melih:\n" +
                    "Role: Lead Developer\n" +
                    "Contact: melih@example.com\n\n" +
                    "Anandhu:\n" +
                    "Role: Backend Developer\n" +
                    "Contact: melih@example.com\n\n" +
                    "Numan:\n" +
                    "Role: Frontend Developer\n" +
                    "Contact: numan@example.com\n\n" +
                    "Sai:\n" +
                    "Role: Database Administrator\n" +
                    "Contact: sai@example.com\n\n" +
                    "Version: 1.0.0\n" +
                    "Date: December 2024");
            creditsText.getStyleClass().add("credits-text");
            root.setCenter(creditsText);
        });

        viewMenu.getItems().addAll(clientsMenuItem, transactionsMenuItem, creditsMenuItem);

        this.getMenus().addAll(fileMenu, viewMenu);
    }
}
