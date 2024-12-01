package org.example.warehousemanagementsystem.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.warehousemanagementsystem.pojo.Category;
import org.example.warehousemanagementsystem.pojo.Product;
import org.example.warehousemanagementsystem.tables.CategoryTable;
import org.example.warehousemanagementsystem.tables.ProductCategoryTable;
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
        pieChart = new PieChart();

        pieChart.setLabelsVisible(true);

        generateBarChart();
        root.setCenter(barChart);

        barChart.setTitle("Inventory Levels");
        xAxis.setLabel("Product Model");
        yAxis.setLabel("Quantity");

        Button levelsButton =  new Button("Levels");
        levelsButton.setOnAction(e->{
            generateBarChart();
            root.setCenter(barChart);
        });


        Button categoriesButton = new Button("Categories");
        categoriesButton.setOnAction(e->{
            generatePieChart();
            root.setCenter(pieChart);
        });
        VBox buttons = new VBox();
        buttons.getChildren().addAll(levelsButton, categoriesButton);

        root.setLeft(buttons);
        this.setContent(root);

    }

    public void generatePieChart(){
//        ProductsTable productsTable = ProductsTable.getInstance();
//        ProductLocationTable productLocationTable = ProductLocationTable.getInstance();
        CategoryTable categoryTable = CategoryTable.getInstance();
        ProductCategoryTable productCategoryTable = ProductCategoryTable.getInstance();

        ArrayList<Category> categories = categoryTable.getAllCategories();
        ArrayList<PieChart.Data> data = new ArrayList<>();

        for (Category category : categories) {
            int count = productCategoryTable.getProductCountByCategoryId(category.getId());
            if (count > 0) {
                data.add(new PieChart.Data(category.getCategory(), count));
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
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


