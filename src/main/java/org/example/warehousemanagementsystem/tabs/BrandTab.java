package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.warehousemanagementsystem.pojo.Brand;
import org.example.warehousemanagementsystem.tables.BrandTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayBrand;
import org.example.warehousemanagementsystem.tasks.DeleteBrandTask;

public class BrandTab extends Tab {
    public TableView<DisplayBrand> tableView;

    public BrandTab() {
        this.setText("Brands");
        this.setClosable(false);

        // Main layout
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));

        // TableView
        tableView = new TableView<>();
        TableColumn<DisplayBrand, Integer> column1 = new TableColumn<>("Brand ID");
        column1.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getBrandId()).asObject());
        TableColumn<DisplayBrand, String> column2 = new TableColumn<>("Brand Name");
        column2.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getBrand()));
        tableView.getColumns().addAll(column1, column2);
        tableView.getItems().addAll(BrandTable.getInstance().getAllBrands()
                .stream()
                .map(brand -> new DisplayBrand(brand.getId(), brand.getBrand()))
                .toList());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPlaceholder(new Label("No brands available"));

        // Delete section
        TextField deleteId = new TextField();
        deleteId.setPromptText("Enter Brand ID to delete");
        deleteId.setTooltip(new Tooltip("Enter the ID of the brand you wish to delete"));

        Button deleteButton = new Button("Delete");
        deleteButton.setTooltip(new Tooltip("Click to delete the selected brand"));
        // Add color to the delete button
        deleteButton.setStyle("-fx-background-color: #ff6347; -fx-text-fill: white; -fx-font-weight: bold;");
        deleteButton.setOnAction(e -> handleDelete(deleteId));

        HBox deleteBox = new HBox(10, deleteId, deleteButton);
        deleteBox.setPadding(new Insets(10));
        deleteBox.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #ddd; -fx-border-radius: 5px;");
        deleteBox.setSpacing(10);

        // Top section layout
        VBox topSection = new VBox(10, new Label("Brand Management"), deleteBox);
        topSection.setPadding(new Insets(10));
        topSection.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ccc; -fx-border-width: 0 0 1px 0;");

        // Set layout
        pane.setTop(topSection);
        pane.setCenter(tableView);

        this.setContent(pane);
    }

    private void handleDelete(TextField deleteId) {
        try {
            String id = deleteId.getText();
            if (!id.isEmpty()) {
                int brandId = Integer.parseInt(id);
                Brand brandToDelete = BrandTable.getInstance().getBrand(brandId);
                if (brandToDelete != null) {
                    DeleteBrandTask deleteBrandTask = new DeleteBrandTask(brandId);
                    if (deleteBrandTask.execute()) {
                        tableView.getItems().removeIf(brand -> brand.getBrandId() == brandId);
                        deleteId.clear();

                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Brand deleted successfully");
                        successAlert.show();
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Brand not found");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid Brand ID");
            }
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Error", "Brand ID must be a number");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
