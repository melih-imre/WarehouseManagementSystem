
package org.example.warehousemanagementsystem.Menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.Main;
import org.example.warehousemanagementsystem.item.AddItem;

import java.io.IOException;

public class FileMenu extends Menu {
    public FileMenu(TabPane tabPane) {
        this.setText("File");
        MenuItem add = new MenuItem("Add Item");
        MenuItem exit = new MenuItem("Exit");
        add.setOnAction(e-> {
            AddItem addItem =new AddItem();
            tabPane.getTabs().add(addItem);
        });
        exit.setOnAction(event -> System.exit(0));
        this.getItems().addAll(add,exit);


    }
}
