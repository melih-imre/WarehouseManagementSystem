package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.pojo.Brand;
import org.example.warehousemanagementsystem.tables.BrandTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayBrand;
import org.example.warehousemanagementsystem.tasks.DeleteBrandTask;

public class BrandTab extends Tab {
    public TableView<DisplayBrand> tableView;

    public BrandTab(){
        this.setText("Brands");
        this.setClosable(false);
        BorderPane pane = new BorderPane();
        BrandTable brandTable = BrandTable.getInstance();
        TextField deleteId = new TextField();
        deleteId.setPromptText("Enter the record to delete");


        tableView = new TableView<>();
        TableColumn<DisplayBrand,Integer> column1 = new TableColumn<>("Brand Id");
        column1.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getBrandId()).asObject());
        TableColumn<DisplayBrand,String>column2= new TableColumn<>("Brand");
        column2.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getBrand()));
        tableView.getColumns().addAll(column1,column2);
         tableView.getItems().addAll(brandTable.getAllBrands().stream().map(brand -> new DisplayBrand(brand.getId(), brand.getBrand())).toList());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e->{
            try {
                String id = deleteId.getText();
                if(!id.isEmpty()){
                    int brandId = Integer.parseInt(id);
                    Brand brandToDelete = BrandTable.getInstance().getBrand(brandId);
                    if(brandToDelete!=null){
                        DeleteBrandTask deleteBrandTask = new DeleteBrandTask(brandId);
                        if(deleteBrandTask.execute()){

                            tableView.getItems().removeIf(brand -> brand.getBrandId() == brandId);
                            deleteId.clear();

                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.show();
                        }

                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setContentText("Brand not found");
                        errorAlert.show();
                    }
                }else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setContentText("Please enter a valid Brand Id");
                    errorAlert.show();
                }

            }catch (NumberFormatException ex){
                throw new RuntimeException(ex.getMessage());
            }

        });

    BorderPane top = new BorderPane();
        top.setLeft(deleteId);
        top.setRight(deleteButton);
        pane.setRight(top);

         pane.setCenter(tableView);
         this.setContent(pane);



    }

}
