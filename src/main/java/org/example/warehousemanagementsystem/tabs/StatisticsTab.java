package org.example.warehousemanagementsystem.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.example.warehousemanagementsystem.pojo.Category;
import org.example.warehousemanagementsystem.pojo.Product;
import org.example.warehousemanagementsystem.tables.*;

import java.util.ArrayList;

public class StatisticsTab extends Tab {
    private static StatisticsTab instance;
    private BarChart<String, Number> barChart;
    private PieChart pieChart;
    private BarChart<String, Number> topSellingBarChart;

    private StatisticsTab() {
        this.setText("Statistics");
        this.setClosable(false);
        BorderPane root = new BorderPane();

        // Axes for charts
        CategoryAxis xAxis = new CategoryAxis();
        CategoryAxis xTopAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        NumberAxis yTopAxis = new NumberAxis();

        // Initializing charts
        barChart = new BarChart<>(xAxis, yAxis);
        pieChart = new PieChart();
        topSellingBarChart = new BarChart<>(xTopAxis, yTopAxis);

        // Styling for charts
        pieChart.setLabelsVisible(true);
        pieChart.setStyle("-fx-padding: 10px;");

        barChart.setTitle("Inventory Levels");
        barChart.setStyle("-fx-padding: 10px; -fx-background-color: #f8f9fa;");

        pieChart.setTitle("Category Distribution");
        pieChart.setStyle("-fx-background-color: #f8f9fa;");

        topSellingBarChart.setTitle("Top Selling Models");
        topSellingBarChart.setStyle("-fx-padding: 10px; -fx-background-color: #f8f9fa;");

        generateTopSellingProductsChart();
        root.setCenter(topSellingBarChart);

        // Buttons
        Button levelsButton = new Button("Levels");
        levelsButton.setPrefWidth(150);
        levelsButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5px;");
        levelsButton.setOnAction(e -> {
            generateBarChart();
            root.setCenter(barChart);
        });

        Button categoriesButton = new Button("Categories");
        categoriesButton.setPrefWidth(150);
        categoriesButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5px;");
        categoriesButton.setOnAction(e -> {
            generatePieChart();
            root.setCenter(pieChart);
        });

        Button topSelling = new Button("Top Selling Models");
        topSelling.setPrefWidth(150);
        topSelling.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5px;");
        topSelling.setOnAction(e -> {
            generateTopSellingProductsChart();
            root.setCenter(topSellingBarChart);
        });

        // VBox for buttons
        VBox buttons = new VBox();
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(15, 0, 0, 5));
        buttons.getChildren().addAll(levelsButton, categoriesButton, topSelling);
        buttons.setStyle("-fx-background-color: #ecf0f1; -fx-padding: 10px;");

        root.setLeft(buttons);
        this.setContent(root);
    }

    public void generatePieChart() {
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

    public void generateBarChart() {
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

    public void generateTopSellingProductsChart() {
        topSellingBarChart.getData().clear();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Top 10 Selling Products");

        TransactionTable transactionsTable = TransactionTable.getInstance();
        ArrayList<Object[]> topSellingProducts = transactionsTable.getTopSellingProducts();

        for (Object[] entry : topSellingProducts) {
            Product product = (Product) entry[0];
            int totalSold = (int) entry[1];

            series.getData().add(new XYChart.Data<>(product.getModel(), totalSold));
        }
        topSellingBarChart.getData().add(series);
    }

    public static StatisticsTab getInstance() {
        if (instance == null) {
            instance = new StatisticsTab();
        }
        return instance;
    }
}
