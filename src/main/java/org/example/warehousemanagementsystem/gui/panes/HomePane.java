package org.example.warehousemanagementsystem.gui.panes;

import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.Menubar.Bar;


public class HomePane extends BorderPane {
    private TabsPane tabsPane;
    public HomePane(){
        Bar menu = new Bar(this,this);
        this.setTop(menu);

        tabsPane = new TabsPane();
        this.setCenter(tabsPane);
    }

}
