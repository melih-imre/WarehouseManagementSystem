package org.example.warehousemanagementsystem.gui.scenes;

import javafx.scene.Scene;
import org.example.warehousemanagementsystem.loginpage.LoginPage;

import java.io.IOException;

/**
 * Represents the login scene in the warehouse management system GUI.
 * This scene displays the login page for the user to enter credentials.
 *
 * @author 0845830 Melih Imre
 * @version 1.0
 * @date 2024-11-15
 */
public class LoginScene extends Scene {
    public LoginScene() throws IOException {super(new LoginPage().getLoginPane(), 1024, 768);}
}
