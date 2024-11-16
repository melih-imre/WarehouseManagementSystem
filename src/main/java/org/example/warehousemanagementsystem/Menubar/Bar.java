package org.example.warehousemanagementsystem.Menubar;

import javafx.scene.control.MenuBar;

import javax.swing.*;

public class Bar extends MenuBar {
    public Bar() {
        FileMenu fileMenu = new FileMenu();
        CreditsMenu creditsMenu = new CreditsMenu();

        this.getMenus().addAll(fileMenu, creditsMenu);
    }
}
