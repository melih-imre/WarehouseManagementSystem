package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.warehousemanagementsystem.pojo.Category;
import org.example.warehousemanagementsystem.tables.CategoryTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayCategory;
import org.example.warehousemanagementsystem.tasks.CategoryInsert;
import org.example.warehousemanagementsystem.tasks.DeleteTask;

public class CategoryTab extends Tab {
    public TableView<DisplayCategory> tableView;

    public CategoryTab() {
        this.setText("Categories");
        this.setClosable(false);

        // Root container
        BorderPane root = new BorderPane();

        // Initialize TextFields and Buttons
        TextField deleteId = new TextField();
        deleteId.setPromptText("Enter the record ID to delete");
        TextField insert = new TextField();
        insert.setPromptText("Enter a new category name");

        Button deleteButton = new Button("Delete");
        Button insertButton = new Button("Insert");

        // Add Tooltips
        deleteButton.setTooltip(new Tooltip("Delete a category by entering its ID"));
        insertButton.setTooltip(new Tooltip("Insert a new category by entering its name"));

        // Style the buttons
        deleteButton.setStyle("-fx-background-color: #ff4c4c; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        insertButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");

        // Button hover effects
        deleteButton.setOnMouseEntered(e -> deleteButton.setStyle("-fx-background-color: #e60000; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;"));
        deleteButton.setOnMouseExited(e -> deleteButton.setStyle("-fx-background-color: #ff4c4c; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;"));

        insertButton.setOnMouseEntered(e -> insertButton.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;"));
        insertButton.setOnMouseExited(e -> insertButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;"));

        // Category Table setup
        CategoryTable categoryTable = CategoryTable.getInstance();
        tableView = new TableView<>();
        TableColumn<DisplayCategory, Integer> column1 = new TableColumn<>("Category ID");
        column1.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getId()).asObject());
        TableColumn<DisplayCategory, String> column2 = new TableColumn<>("Category");
        column2.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory()));
        tableView.getColumns().addAll(column1, column2);

        // Populate TableView
        tableView.getItems().addAll(
                categoryTable.getAllCategories().stream()
                        .map(category -> new DisplayCategory(category.getId(), category.getCategory()))
                        .toList()
        );

        // Set Placeholder for TableView
        tableView.setPlaceholder(new Label("No categories available"));

        // Delete button action
        deleteButton.setOnAction(e -> {
            try {
                String id = deleteId.getText();
                if (!id.isEmpty()) {
                    int categoryId = Integer.parseInt(id);
                    Category categoryToDelete = categoryTable.getCategory(categoryId);
                    if (categoryToDelete != null) {
                        DeleteTask deleteTask = new DeleteTask(categoryId);
                        if (deleteTask.execute()) {
                            tableView.getItems().removeIf(category -> category.getId() == categoryId);
                            deleteId.clear();

                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setContentText("Category deleted successfully!");
                            successAlert.show();
                        }
                    } else {
                        showErrorAlert("Category not found");
                    }
                } else {
                    showErrorAlert("Please enter a valid category ID");
                }
            } catch (NumberFormatException ex) {
                showErrorAlert("Invalid ID format");
            }
        });

        // Insert button action
        insertButton.setOnAction(e -> {
            String categoryName = insert.getText();
            if (!categoryName.isEmpty()) {
                CategoryInsert insertTask = new CategoryInsert(categoryName);
                insertTask.run();
                try {
                    tableView.getItems().clear();
                    tableView.getItems().addAll(
                            categoryTable.getAllCategories().stream()
                                    .map(category -> new DisplayCategory(category.getId(), category.getCategory()))
                                    .toList()
                    );
                    insert.clear();

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setContentText("Category inserted successfully!");
                    successAlert.show();
                } catch (Exception ex) {
                    showErrorAlert("Failed to insert category. Please try again.");
                }
            } else {
                showErrorAlert("Category name cannot be empty");
            }
        });

        // Layout for Delete Section
        HBox deleteSection = new HBox(10, deleteId, deleteButton);
        deleteSection.setPadding(new Insets(10));
        deleteSection.setStyle("-fx-background-color: #f8f8f8;");

        // Layout for Insert Section
        HBox insertSection = new HBox(10, insert, insertButton);
        insertSection.setPadding(new Insets(10));
        insertSection.setStyle("-fx-background-color: #f8f8f8;");

        // Main Layout
        VBox controlPanel = new VBox(10, deleteSection, insertSection);
        controlPanel.setPadding(new Insets(10));

        root.setTop(controlPanel);
        root.setCenter(tableView);

        this.setContent(root);
    }

    private void showErrorAlert(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setContentText(message);
        errorAlert.show();
    }
}
