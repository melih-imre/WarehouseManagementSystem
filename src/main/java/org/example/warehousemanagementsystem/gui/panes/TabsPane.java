package org.example.warehousemanagementsystem.gui.panes;

import javafx.scene.control.TabPane;
import org.example.warehousemanagementsystem.tables.test.tabs.AislesTestTab;
import org.example.warehousemanagementsystem.tables.test.tabs.ClientTestTab;

public class TabsPane extends TabPane {
    public TabsPane(){
        ClientTestTab clientTestTab = new ClientTestTab();
        AislesTestTab aislesTestTab = new AislesTestTab();

        this.getTabs().addAll(clientTestTab, aislesTestTab);
    }

}
