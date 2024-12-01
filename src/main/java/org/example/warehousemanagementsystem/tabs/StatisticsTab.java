package org.example.warehousemanagementsystem.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.warehousemanagementsystem.pojo.Product;
import org.example.warehousemanagementsystem.tables.ProductLocationTable;
import org.example.warehousemanagementsystem.tables.ProductsTable;

import java.util.ArrayList;

public class StatisticsTab extends Tab {
    private static StatisticsTab instance;
    private BarChart<String, Number> barChart;
    private PieChart pieChart;


    private StatisticsTab(){
        this.setText("Statistics");
        BorderPane root = new BorderPane();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        barChart = new BarChart<String, Number>(xAxis, yAxis);

        barChart.setTitle("Inventory Levels");
        xAxis.setLabel("Product Model");
        yAxis.setLabel("Quantity");

        Button levelsButton =  new Button("Levels");
        levelsButton.setOnAction(e->{
            generateBarChart();
        });
        generateBarChart();
        VBox buttons = new VBox();
        buttons.getChildren().add(levelsButton);
        root.setCenter(barChart);
        root.setLeft(buttons);
        this.setContent(root);

    }

    public void generatePieChart(){
        ProductsTable productsTable = ProductsTable.getInstance();
        ProductLocationTable productLocationTable = ProductLocationTable.getInstance();

        ArrayList<Product> products = productsTable.getAllProducts();
        ArrayList<PieChart.Data> data = new ArrayList<>();

        System.out.println(productsTable.getAllProducts());
        System.out.println(productLocationTable.getQuantity(1001));
        for (Product product : products) {
            int quantity = productLocationTable.getQuantity(product.getId());
            if (quantity > 0){
                data.add(new PieChart.Data(product.getModel(), quantity));
            }
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(data);
        pieChart.setData(pieChartData);
    }

    public void generateBarChart(){
        barChart.getData().clear();
        ProductsTable productsTable = ProductsTable.getInstance();
        ProductLocationTable productLocationTable = ProductLocationTable.getInstance();

        ArrayList<Product> products = productsTable.getAllProducts();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Inventory Levels");

        for (Product product : products) {
            int quantity = productLocationTable.getQuantity(product.getId());
            if (quantity > 0) {
                series.getData().add(new XYChart.Data<>(product.getModel(), quantity));
            }
        }

        barChart.getData().add(series);

    }

    public static StatisticsTab getInstance() {
        if (instance == null){
            instance = new StatisticsTab();
        }
        return instance;
    }
}
