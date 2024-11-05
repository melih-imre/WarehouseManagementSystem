package org.example.warehousemanagementsystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        //The menu bar will be developed according to the needs
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu credits = new Menu("Credits");
        menuBar.getMenus().addAll(file, credits);
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e->System.exit(0));
        file.getItems().add(exit);

        /*
        Tabs will be added according to needs
        Each tab will be handled in seperate classes
         */
        TabPane tabPane = new TabPane();
        Tab addProductTab = new Tab("Add Product");
        Tab homeTab = new Tab("Home");
        homeTab.setClosable(false);
        Tab statisticsTab = new Tab("Statistics");

        //Random pie chart image for the statistics tab
        Image pieChart = new Image("images/example.png");
        ImageView imageView = new ImageView(pieChart);
        statisticsTab.setContent(imageView);

        //Content for home tab will be decided
        TextArea homeText = new TextArea("Shortcuts for tabs and many other item may be added");
        homeTab.setContent(homeText);

        //Hypothetical menu layout for adding a product
        GridPane items = new GridPane();
        Label sku = new Label("SKU");
        Label category = new Label("Category");
        Label subCategory = new Label("Sub Category");
        Label brand = new Label("Brand");
        Label model = new Label("Model");
        Label price = new Label("Price");

        Label colon1 = new Label(":");
        Label colon2 = new Label(":");
        Label colon3 = new Label(":");
        Label colon4 = new Label(":");
        Label colon5 = new Label(":");
        Label colon6 = new Label(":");

        TextField skuText = new TextField();
        TextField categoryText = new TextField("Dropdown Menu");
        TextField subCategoryText = new TextField("Dropdown Menu");
        TextField brandText = new TextField();
        TextField modelText = new TextField();
        TextField priceText = new TextField();

        items.add(sku,0,0);
        items.add(category,0,1);
        items.add(subCategory,0,2);
        items.add(brand,0,3);
        items.add(model,0,4);
        items.add(price,0,5);

        items.add(colon1,1,0);
        items.add(colon2,1,1);
        items.add(colon3,1,2);
        items.add(colon4,1,3);
        items.add(colon5,1,4);
        items.add(colon6,1,5);

        items.add(skuText,2,0);
        items.add(categoryText,2,1);
        items.add(subCategoryText,2,2);
        items.add(brandText,2,3);
        items.add(modelText,2,4);
        items.add(priceText,2,5);

        items.setHgap(10);
        items.setVgap(10);
        items.setPadding(new Insets(5,5,5,5));

        addProductTab.setContent(items);
        tabPane.getTabs().addAll(homeTab, addProductTab, statisticsTab);

        root.setCenter(tabPane);
        root.setTop(menuBar);

        Scene scene = new Scene(root,1024,768);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Warehouse Management System");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
