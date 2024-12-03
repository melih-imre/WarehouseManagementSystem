package org.example.warehousemanagementsystem.gui.panes;

import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.example.warehousemanagementsystem.tables.test.tabs.AislesTestTab;
import org.example.warehousemanagementsystem.tables.test.tabs.ClientTestTab;
import org.example.warehousemanagementsystem.tabs.Tabs;

/**
 * Represents the pane that holds the tabs for the warehouse management system.
 * It provides the central layout where different tabs (e.g., Aisles, Clients) are displayed.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class TabsPane extends BorderPane {
    public TabsPane(){
        Tabs tabs = Tabs.getInstance();

        this.setCenter(tabs);
    }

}
