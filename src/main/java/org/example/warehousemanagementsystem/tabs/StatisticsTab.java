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


    private StatisticsTab(){
        this.setText("Statistics");
        this.setClosable(false);
        BorderPane root = new BorderPane();
        CategoryAxis xAxis = new CategoryAxis();
        CategoryAxis xTopAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        NumberAxis yTopAxis = new NumberAxis();


        barChart = new BarChart<>(xAxis, yAxis);
        pieChart = new PieChart();
        topSellingBarChart = new BarChart<>(xTopAxis, yTopAxis);

        pieChart.setLabelsVisible(true);

        barChart.setTitle("Inventory Levels");
        pieChart.setTitle("Category Distribution");
        topSellingBarChart.setTitle("Top Selling Models");

        generateTopSellingProductsChart();
        root.setCenter(topSellingBarChart);

        Button levelsButton =  new Button("Levels");
        levelsButton.setPrefWidth(150);
        levelsButton.setOnAction(e->{
            generateBarChart();
            root.setCenter(barChart);
        });


        Button categoriesButton = new Button("Categories");
        categoriesButton.setPrefWidth(150);
        categoriesButton.setOnAction(e->{
            generatePieChart();
            root.setCenter(pieChart);
        });

        Button topSelling = new Button("Top Selling Models");
        topSelling.setPrefWidth(150);
        topSelling.setOnAction(e->{
            generateTopSellingProductsChart();
            root.setCenter(topSellingBarChart);
        });
        VBox buttons = new VBox();
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(15,0,0,5));
        buttons.getChildren().addAll(levelsButton, categoriesButton, topSelling);

        root.setLeft(buttons);
        this.setContent(root);

    }

    public void generatePieChart(){
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


        ProductLocationTable productLocationTable = ProductLocationTable.getInstance();

        ArrayList<Product> products = ProductsTable.getInstance().getAllProducts();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Inventory Levels");

        for (Product product : products) {
            System.out.println(product.getSku());
            System.out.println(productLocationTable.getLocationIdBySku(product.getSku()));
            int quantity = productLocationTable.getQuantity(productLocationTable.getLocationIdBySku(product.getSku()));
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
        if (instance == null){
            instance = new StatisticsTab();
        }
        return instance;
    }
}


