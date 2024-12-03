//package org.example.warehousemanagementsystem;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
//import org.example.warehousemanagementsystem.database.Database;
//import org.example.warehousemanagementsystem.loginpage.LoginPage;
//
//import java.io.IOException;
//
//
//public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        BorderPane root = new BorderPane();
//        LoginPage loginPage = new LoginPage();
//        root.setCenter(loginPage.getLoginPane());
//        Scene scene = new Scene(root, 1024, 768);
////        String cssPath = getClass().getResource("/styles.css").toExternalForm();
////        scene.getStylesheets().add(cssPath);
//        Database db = Database.getInstance();
//        stage.setTitle("Warehouse Database Management System");
//        stage.setScene(scene);
//        stage.show();
//
//    }
//    public static void main(String[] args) {
//        launch();
//    }
//}


package org.example.warehousemanagementsystem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.loginpage.LoginPage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        LoginPage loginPage = new LoginPage();
        root.setCenter(loginPage.getLoginPane());

        String cssPath = getClass().getResource("/styles.css").toExternalForm();
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(cssPath);

        Database db = Database.getInstance();
        stage.setTitle("Warehouse Database Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
