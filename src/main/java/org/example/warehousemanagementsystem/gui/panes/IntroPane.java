package org.example.warehousemanagementsystem.gui.panes;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.warehousemanagementsystem.Main;
import org.example.warehousemanagementsystem.gui.scenes.LoginScene;

import java.io.IOException;
import java.util.Objects;

public class IntroPane extends StackPane {
    public IntroPane() {
        // Load the logo image
        ImageView imageView = new ImageView(new Image(
                Objects.requireNonNull(getClass().getResource("/images/logo.png")).toExternalForm()));
        imageView.setFitWidth(300);
        imageView.setFitHeight(300);

        // Text Label with additional styling
        Label textLabel = new Label("WAREHOUSE DATABASE MANAGEMENT SYSTEM");
        textLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        textLabel.setStyle("-fx-text-fill: #003366; -fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.7), 10, 0.5, 1, 1);");
        textLabel.setOpacity(0);

        // Background gradient
        this.setStyle("-fx-background-color: linear-gradient(to bottom, #cbe5ea, #6ab7d3);");

        // Add elements to the pane
        this.getChildren().addAll(imageView, textLabel);

        StackPane.setAlignment(textLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(textLabel, new javafx.geometry.Insets(20, 0, 50, 0));

        FadeTransition fadeImage = new FadeTransition(Duration.seconds(2), imageView);
        fadeImage.setFromValue(0);
        fadeImage.setToValue(1);

        ScaleTransition scaleImage = new ScaleTransition(Duration.seconds(2), imageView);
        scaleImage.setFromX(0.5);
        scaleImage.setFromY(0.5);
        scaleImage.setToX(1);
        scaleImage.setToY(1);

        FadeTransition fadeText = new FadeTransition(Duration.seconds(2), textLabel);
        fadeText.setFromValue(0);
        fadeText.setToValue(1);

        fadeText.setOnFinished(event -> {
            try {
                Main.mainStage.setScene(new LoginScene());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fadeImage.setOnFinished(event -> {
            scaleImage.play();
            fadeText.play();
        });

        fadeImage.play();
    }
}
