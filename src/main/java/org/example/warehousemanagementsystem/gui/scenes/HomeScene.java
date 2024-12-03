package org.example.warehousemanagementsystem.gui.scenes;

import javafx.scene.Scene;
import org.example.warehousemanagementsystem.gui.panes.HomePane;
import org.example.warehousemanagementsystem.gui.panes.TabsPane;

/**
 * Represents the home scene in the warehouse management system GUI.
 * This scene displays the home page of the application.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class HomeScene extends Scene {
    public HomeScene(){super(new HomePane(),1024, 768);}
}
