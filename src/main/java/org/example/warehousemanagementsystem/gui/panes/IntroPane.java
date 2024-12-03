package org.example.warehousemanagementsystem.gui.panes;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import org.example.warehousemanagementsystem.Main;
import org.example.warehousemanagementsystem.gui.scenes.LoginScene;

import java.util.Objects;

public class IntroPane extends StackPane {
    public IntroPane(){
        ImageView imageView = new ImageView(new Image(
                Objects.requireNonNull(getClass().getResource("/images/logo.png")).toExternalForm()));
        imageView.setFitWidth(300);
        imageView.setFitHeight(300); // Adjust height of the image

        Label textLabel = new Label("WAREHOUSE DATABASE MANAGEMENT SYSTEM");
        textLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        textLabel.setStyle("-fx-text-fill: #003366;");

        this.setStyle("-fx-background-color: linear-gradient(to bottom, #cbe5ea, #6ab7d3);");
        this.getChildren().addAll(imageView, textLabel);

        StackPane.setAlignment(textLabel, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(textLabel, new javafx.geometry.Insets(20, 0, 50, 0));

        FadeTransition fadeImage = new FadeTransition(Duration.seconds(5), imageView);
        fadeImage.setFromValue(0);
        fadeImage.setToValue(1);

        FadeTransition fadeText = new FadeTransition(Duration.seconds(5), textLabel);
        fadeText.setFromValue(0);
        fadeText.setToValue(1);

        fadeText.setOnFinished(event -> {
            Main.mainStage.setScene(new LoginScene());
        });

        fadeImage.setOnFinished(event -> fadeText.play());

        fadeImage.play();

    }

}
