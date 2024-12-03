package org.example.warehousemanagementsystem.Menubar;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.warehousemanagementsystem.gui.panes.HomePane;
import org.example.warehousemanagementsystem.tabs.Tabs;

public class Bar extends MenuBar {
    public Bar(BorderPane root, HomePane mainApp) {
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
        MenuItem productsMenuItem = new MenuItem("Products");
        productsMenuItem.getStyleClass().add("menu-item");
        MenuItem categoriesMenuItem = new MenuItem("Categories");
        categoriesMenuItem.getStyleClass().add("menu-item");
        MenuItem statisticsMenuItem = new MenuItem("Statistics");
        statisticsMenuItem.getStyleClass().add("menu-item");

        Tabs tabs = Tabs.getInstance();

        // View Menu Actions (Same as before)
        clientsMenuItem.setOnAction(e -> {
            root.setCenter(tabs);
            tabs.getSelectionModel().select(1);
        });

        transactionsMenuItem.setOnAction(e -> {
            root.setCenter(tabs);
            tabs.getSelectionModel().select(3);
        });

        productsMenuItem.setOnAction(e -> {
            root.setCenter(tabs);
            tabs.getSelectionModel().select(0);
        });

        categoriesMenuItem.setOnAction(e -> {
            root.setCenter(tabs);
            tabs.getSelectionModel().select(5);
        });

        statisticsMenuItem.setOnAction(e -> {
            root.setCenter(tabs);
            tabs.getSelectionModel().select(7);
        });

        // Credits Menu Action with Enhanced Effects
        creditsMenuItem.setOnAction(e -> {
            // Create a Text object for credits
            Text creditsText = new Text("Developer Information:\n\n" +
                    "Melih:\n" +
                    "Role: Backend Developer\n" +
                    "Contact: melih@gmail.com\n\n" +
                    "Anandhu:\n" +
                    "Role: Backend Developer\n" +
                    "Contact: Anandhu@gmail.com\n\n" +
                    "Numan:\n" +
                    "Role: Frontend Developer\n" +
                    "Contact: numan@gmail.com\n\n" +
                    "Sai:\n" +
                    "Role: Database Administrator\n" +
                    "Contact: sai@gmail.com\n\n" +
                    "Version: 1.0.0\n" +
                    "Date: December 2024");

            // Style the credits text
            creditsText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            creditsText.setFill(Color.WHITE);
            creditsText.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.7), 8, 0.7, 2, 2);");
            creditsText.setOpacity(0);

            // Added a cool gradient background to the center of the screen
            root.setStyle("-fx-background-color: linear-gradient(to top, #5baafd, #bdc3c7);");

            root.setCenter(creditsText);

            // Fade In Animation for the credits text
            FadeTransition fadeText = new FadeTransition(Duration.seconds(3), creditsText);
            fadeText.setFromValue(0);
            fadeText.setToValue(1);
            fadeText.play();

            // Create TranslateTransition for moving creditsText
            TranslateTransition translateText = new TranslateTransition(Duration.seconds(10), creditsText);
            translateText.setFromY(0);
            translateText.setToY(700);

            // Play the translation effect after fade-in completes
            fadeText.setOnFinished(event -> translateText.play());
        });

        viewMenu.getItems().addAll(productsMenuItem, clientsMenuItem, transactionsMenuItem, categoriesMenuItem, statisticsMenuItem, creditsMenuItem);

        this.getMenus().addAll(fileMenu, viewMenu);
    }
}
