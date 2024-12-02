package org.example.warehousemanagementsystem.Menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.example.warehousemanagementsystem.Main;

public class Bar extends MenuBar {
    public Bar(BorderPane root, Main mainApp) {
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exitMenuItem);

        Menu viewMenu = new Menu("View");
        MenuItem clientsMenuItem = new MenuItem("Clients");
        MenuItem transactionsMenuItem = new MenuItem("Transactions");
        MenuItem creditsMenuItem = new MenuItem("Credits");

        // Handle "Clients" menu item click
        clientsMenuItem.setOnAction(e -> {
            root.setCenter(mainApp.getTabPane()); // Restore TabPane
            mainApp.showTab(0); // Show the Clients Tab (index 0)
        });

        // Handle "Transactions" menu item click
        transactionsMenuItem.setOnAction(e -> {
            root.setCenter(mainApp.getTabPane()); // Restore TabPane
            mainApp.showTab(1); // Show the Transactions Tab (index 1)
        });

        // Handle "Credits" menu item click
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
            root.setCenter(creditsText);
        });

        viewMenu.getItems().addAll(clientsMenuItem, transactionsMenuItem, creditsMenuItem);

        this.getMenus().addAll(fileMenu, viewMenu);
    }
}
