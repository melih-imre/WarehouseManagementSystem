package org.example.warehousemanagementsystem;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.warehousemanagementsystem.database.Database;
import org.example.warehousemanagementsystem.gui.scenes.IntroScene;

public class Main extends Application {
    public static Stage mainStage;
    @Override
    public void start(Stage stage) {

        mainStage = stage;
        mainStage.setScene(new IntroScene());

        mainStage.setTitle("Warehouse Database Management System");
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
