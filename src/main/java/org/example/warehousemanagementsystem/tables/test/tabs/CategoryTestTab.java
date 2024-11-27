package org.example.warehousemanagementsystem.tables.test.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.tables.CategoryTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayCategory;

public class CategoryTestTab extends Tab {
    public TableView <DisplayCategory> tableView;

    public CategoryTestTab(){
        this.setText("Categories");
        BorderPane root = new BorderPane();
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



        root.setCenter(tableView);
        this.setContent(root);

    }

}
