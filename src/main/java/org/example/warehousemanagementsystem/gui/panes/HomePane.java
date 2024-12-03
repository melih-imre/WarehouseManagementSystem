package org.example.warehousemanagementsystem.gui.panes;

import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.Menubar.Bar;

/**
 * Represents the home pane of the warehouse management system, including the menu bar and tabs.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class HomePane extends BorderPane {
    private TabsPane tabsPane;
    public HomePane(){
        Bar menu = new Bar(this,this);
        this.setTop(menu);

        tabsPane = new TabsPane();
        this.setCenter(tabsPane);
    }

}
