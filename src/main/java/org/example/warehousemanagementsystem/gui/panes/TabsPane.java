package org.example.warehousemanagementsystem.gui.panes;

import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.tables.test.tabs.AislesTestTab;
import org.example.warehousemanagementsystem.tables.test.tabs.ClientTestTab;
import org.example.warehousemanagementsystem.tabs.Tabs;

public class TabsPane extends BorderPane {
    public TabsPane(){
        Tabs tabs = Tabs.getInstance();

        this.setCenter(tabs);
    }

}
