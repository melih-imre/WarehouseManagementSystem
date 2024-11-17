package org.example.warehousemanagementsystem.Menubar;

import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;

import javax.swing.*;

public class Bar extends MenuBar {
    public Bar(TabPane tabPane) {
        FileMenu fileMenu = new FileMenu(tabPane);
        CreditsMenu creditsMenu = new CreditsMenu();

        this.getMenus().addAll(fileMenu, creditsMenu);
    }
}
