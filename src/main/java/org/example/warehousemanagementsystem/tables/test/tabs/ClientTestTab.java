package org.example.warehousemanagementsystem.tables.test.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.tables.AisleTable;
import org.example.warehousemanagementsystem.tables.ClientTable;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayAisle;
import org.example.warehousemanagementsystem.tables.test.displayItems.DisplayClient;


public class ClientTestTab extends Tab {
    public TableView tableView;
    public ClientTestTab(){
        this.setText("CLIENTS");
        BorderPane root = new BorderPane();
        ClientTable clientTable = new ClientTable();
        tableView = new TableView();


        TableColumn<DisplayClient, Integer> column1 =
                new TableColumn<>("Client Id");
        column1.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getClientId()).asObject());

        TableColumn<DisplayClient, String> column2 =
                new TableColumn<>("Name");
        column2.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getFirstName()));

        TableColumn<DisplayClient, String> column3 =
                new TableColumn<>("Surname");
        column3.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getLastName()));

        TableColumn<DisplayClient, String> column4 =
                new TableColumn<>("Email");
        column4.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getEmail()));

        TableColumn<DisplayClient, String> column5 =
                new TableColumn<>("Phone");
        column5.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getPhone()));

        TableColumn<DisplayClient, String> column6 =
                new TableColumn<>("Street No");
        column6.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getStreetNumber()));

        TableColumn<DisplayClient, String> column7 =
                new TableColumn<>("Street Name");
        column7.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getStreetName()));

        TableColumn<DisplayClient, String> column8 =
                new TableColumn<>("City");
        column8.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCity()));

        TableColumn<DisplayClient, String> column9 =
                new TableColumn<>("State");
        column9.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getState()));

        tableView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9);
        tableView.getItems().addAll(clientTable.getAllClients());

        root.setCenter(tableView);
        this.setContent(root);
    }
}
