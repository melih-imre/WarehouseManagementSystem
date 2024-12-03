package org.example.warehousemanagementsystem.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.pojo.Brand;
import org.example.warehousemanagementsystem.pojo.Product;
import org.example.warehousemanagementsystem.tables.BrandTable;
import org.example.warehousemanagementsystem.tables.ProductsTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayProduct;
import org.example.warehousemanagementsystem.tasks.DeleteProductTask;
import org.example.warehousemanagementsystem.tasks.ProductInsert;

import java.util.ArrayList;

public class ProductTab extends Tab {

    public TableView <DisplayProduct> tableView;

    public ProductTab(){
        this.setText("Product Tab");
        this.setClosable(false);
        BorderPane root = new BorderPane();
        TextField sku = new TextField();
        sku.setPromptText("Enter the SKU");
        TextField brandfield = new TextField();
        brandfield.setPromptText("Enter the brandId");
        TextField model = new TextField();
        model.setPromptText("Enter the model");
        TextField price = new TextField();
        price.setPromptText("Enter the price");
        ComboBox<String> brandComboBox = new ComboBox<>();
        brandComboBox.setPromptText("Select Brand");
        ArrayList<Brand> brands = BrandTable.getInstance().getAllBrands();
        ArrayList<String> brandNames = new ArrayList<>();
        for(Brand brand:brands){
            brandNames.add(brand.getBrand());
        }
        brandComboBox.setItems(FXCollections.observableArrayList(brandNames));
        TextField deleteId = new TextField();
        deleteId.setPromptText("Enter the item to delete");

        ProductsTable productsTable = ProductsTable.getInstance();
        tableView = new TableView<>();

        TableColumn <DisplayProduct,Integer>column1 = new TableColumn<>("SKU");
        column1.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getSku()).asObject());

        TableColumn <DisplayProduct, Integer>column2 = new TableColumn<>("BrandId");
        column2.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getBrandId()).asObject());

        TableColumn <DisplayProduct, String>column3 = new TableColumn<>("Model");
        column3.setCellValueFactory(e->new SimpleStringProperty(e.getValue().getModel()));

        TableColumn <DisplayProduct,Integer>column4 = new TableColumn<>("Price");
        column4.setCellValueFactory(e->new SimpleIntegerProperty(e.getValue().getPrice()).asObject());

        tableView.getColumns().addAll(column1,column2,column3,column4);

        tableView.getItems().addAll(productsTable.getAllProducts().stream().map(product ->
                new DisplayProduct(
                        product.getSku(),
                        product.getBrandId(),
                        product.getModel(),
                        product.getPrice())).toList());

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e->{
            try{
                String skuName = sku.getText();
                String brandName = brandfield.getText();
                String modelName = model.getText();
                String priceValue = price.getText();
                ProductInsert productInsert = new ProductInsert(Integer.parseInt(skuName),Integer.parseInt(brandName),modelName,Integer.parseInt(priceValue));
                productInsert.run();

                sku.clear();
                model.clear();
                price.clear();

                tableView.getItems().clear();
                tableView.getItems().addAll(productsTable.getAllProducts().stream().map(product ->
                        new DisplayProduct(
                                product.getSku(),
                                product.getBrandId(),
                                product.getModel(),
                                product.getPrice())).toList());
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setContentText("Category Insert Successfully");
                successAlert.show();
                StatisticsTab.getInstance().generateBarChart();


            } catch (NumberFormatException ex) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Invalid Input");
                errorAlert.setContentText("SKU and Price must be valid integers.");
                errorAlert.show();
            } catch (Exception ex) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setContentText("Failed to insert product. Please try again.");
                errorAlert.show();
                ex.printStackTrace();
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e->{
            try {
                String delete = deleteId.getText();
                if(!delete.isEmpty()){
                    int brandId = Integer.parseInt(delete);
                    Product brandToDelete = ProductsTable.getInstance().getProduct(brandId);
                    if(brandToDelete!=null){
                        DeleteProductTask deleteProductTask = new DeleteProductTask(brandId);
                        if(deleteProductTask.execute()){

                            tableView.getItems().removeIf(product -> product.getSku() == brandId);
                            deleteId.clear();

                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setContentText("Sucessfully Deleted");
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




        ToolBar toolBar = new ToolBar(sku,brandfield, model,price,brandComboBox,insertButton);
        BorderPane pane = new BorderPane();
        pane.setRight(deleteId);
        pane.setTop(deleteButton);

        root.setBottom(pane);
        root.setRight(toolBar);
        root.setCenter(tableView);
        this.setContent(root);


    }
}