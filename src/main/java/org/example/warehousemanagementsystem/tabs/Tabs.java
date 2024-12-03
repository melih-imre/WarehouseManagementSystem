package org.example.warehousemanagementsystem.tabs;

import javafx.scene.control.TabPane;

/**
 * Manages the various tabs in the Warehouse Management System.
 * This class follows the Singleton design pattern and is responsible
 * for initializing and displaying all the tabs in the application.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-30
 */

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
