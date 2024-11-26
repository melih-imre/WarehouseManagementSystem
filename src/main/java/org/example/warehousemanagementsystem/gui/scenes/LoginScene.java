package org.example.warehousemanagementsystem.gui.scenes;

import javafx.scene.Scene;
import org.example.warehousemanagementsystem.loginpage.LoginPage;

public class LoginScene extends Scene {
    public LoginScene(){super(new LoginPage().getLoginPane(), 1024, 768);}
}
