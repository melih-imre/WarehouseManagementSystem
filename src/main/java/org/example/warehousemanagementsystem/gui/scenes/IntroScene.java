package org.example.warehousemanagementsystem.gui.scenes;

import javafx.scene.Scene;
import org.example.warehousemanagementsystem.gui.panes.IntroPane;

/**
 * Represents the intro scene in the warehouse management system GUI.
 * This scene displays the introductory page when the application starts.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class IntroScene extends Scene {
    public IntroScene(){super(new IntroPane(),1024,768);}
}
