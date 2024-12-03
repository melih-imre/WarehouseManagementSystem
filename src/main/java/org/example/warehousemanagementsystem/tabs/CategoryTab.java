package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.pojo.Category;
import org.example.warehousemanagementsystem.tables.CategoryTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayCategory;
import org.example.warehousemanagementsystem.tasks.CategoryInsert;
import org.example.warehousemanagementsystem.tasks.DeleteTask;

public class CategoryTab extends Tab {
    public TableView <DisplayCategory> tableView;

    public CategoryTab(){
        this.setText("Categories");
        this.setClosable(false);
        BorderPane root = new BorderPane();
        TextField deleteId = new TextField();
        TextField insert = new TextField();
        insert.setPromptText("Enter the Category");
        deleteId.setPromptText("Enter the record to delete");
        CategoryTable categoryTable = CategoryTable.getInstance();
        tableView=new TableView<>();

        TableColumn <DisplayCategory,Integer>column1= new TableColumn<>("Category Id");
        column1.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getId()).asObject());
        TableColumn<DisplayCategory,String>column2=new TableColumn<>("Category");
        column2.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getCategory()));
        tableView.getColumns().addAll(column1,column2);
        tableView.getItems().addAll(
                categoryTable.getAllCategories().stream()
                        .map(category -> new DisplayCategory(category.getId(), category.getCategory()))
                        .toList()
        );
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e->{
            try {
                String id = deleteId.getText();
                if(!id.isEmpty()){
                    int categoryId = Integer.parseInt(id);
                    Category categoryToDelete = categoryTable.getCategory(categoryId);
                    if(categoryToDelete!=null){
                        DeleteTask deleteTask = new DeleteTask(categoryId);
                        if(deleteTask.execute()){

                            tableView.getItems().removeIf(category -> category.getId() == categoryId);
                            deleteId.clear();

                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.show();
                        }

                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setContentText("Category not found");
                        errorAlert.show();
                    }
                }else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setContentText("Please enter a valid category Id");
                    errorAlert.show();
                }

            }catch (NumberFormatException ex){
                throw new RuntimeException(ex.getMessage());
            }

        });
        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e-> {
                    String categoryname = insert.getText();
                    if (!categoryname.isEmpty()) {
                        CategoryInsert insertTask = new CategoryInsert(categoryname);
                        insertTask.run();
                        try {


                            tableView.getItems().clear();
                            tableView.getItems().addAll(categoryTable.getAllCategories().stream()
                                    .map(category -> new DisplayCategory(category.getId(), category.getCategory())).toList());
                            insert.clear();

                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setContentText("Category Insert Successfully");
                            successAlert.show();

                        } catch (Exception ex) {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Error");
                            errorAlert.setContentText("Failed to insert category. Please try again.");
                            errorAlert.show();
                        }
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setContentText("Category name cannot be empty.");
                        errorAlert.show();
                    }
                });


        BorderPane top = new BorderPane();
        top.setLeft(deleteId);
        top.setRight(deleteButton);


        BorderPane bottom = new BorderPane();
        bottom.setTop(insert);
        bottom.setRight(insertButton);

        root.setRight(top);
        root.setBottom(bottom);



        root.setCenter(tableView);
        this.setContent(root);

    }

}
