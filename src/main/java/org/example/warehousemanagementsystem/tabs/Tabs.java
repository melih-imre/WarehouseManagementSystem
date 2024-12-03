package org.example.warehousemanagementsystem.tabs;

import javafx.scene.control.TabPane;

public class Tabs extends TabPane {
    private static Tabs instance;
    public Tabs(){
        StatisticsTab statisticsTab = StatisticsTab.getInstance();
        this.getTabs().addAll(statisticsTab);
    }

    public static Tabs getInstance(){
        if(instance == null){
            instance = new Tabs();
        }
        return instance;
    }

}
