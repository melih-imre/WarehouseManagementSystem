package org.example.warehousemanagementsystem.gui.scenes;

import javafx.scene.Scene;
import org.example.warehousemanagementsystem.loginpage.LoginPage;

import java.io.IOException;

public class LoginScene extends Scene {
    public LoginScene() throws IOException {super(new LoginPage().getLoginPane(), 1024, 768);}
}
