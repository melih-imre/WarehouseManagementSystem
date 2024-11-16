
package org.example.warehousemanagementsystem.Menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class FileMenu extends Menu {
    public FileMenu() {
        this.setText("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(event -> System.exit(0));
        this.getItems().add(exit);


    }
}
