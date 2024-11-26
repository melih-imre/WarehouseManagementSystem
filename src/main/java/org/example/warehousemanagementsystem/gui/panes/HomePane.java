package org.example.warehousemanagementsystem.gui.panes;

import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.Menubar.Bar;


public class HomePane extends BorderPane {
    public HomePane(){
        Bar menu = new Bar();
        this.setTop(menu);

        TabsPane tabsPane = new TabsPane();
        this.setCenter(tabsPane);
    }
}
