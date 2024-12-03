package org.example.warehousemanagementsystem.tabs;

import javafx.scene.control.TabPane;

public class Tabs extends TabPane {
    private static Tabs instance;
    public Tabs(){
        ProductTab productTab = new ProductTab();
        StatisticsTab statisticsTab = StatisticsTab.getInstance();
        ClientsTab clientsTab = ClientsTab.getInstance();
        ClientsDeleteUpdateTab clientsDeleteUpdateTab = ClientsDeleteUpdateTab.getInstance();
        TransactionTab transactionTab = TransactionTab.getInstance();
        TransactionDeleteUpdateTab transactionDeleteUpdateTab = TransactionDeleteUpdateTab.getInstance();
        CategoryTab categoryTab = new CategoryTab();
        BrandTab brandTab = new BrandTab();

        this.getTabs().addAll(productTab, clientsTab, clientsDeleteUpdateTab, transactionTab, transactionDeleteUpdateTab, categoryTab, brandTab, statisticsTab);
    }

    public static Tabs getInstance(){
        if(instance == null){
            instance = new Tabs();
        }
        return instance;
    }

}
