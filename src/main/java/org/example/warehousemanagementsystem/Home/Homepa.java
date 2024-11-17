package org.example.warehousemanagementsystem.Home;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Homepa {

    public BorderPane getHomePageLayout() {
        Text title = new Text("Warehouse Management System");
        title.setFont(Font.font("Verdana", 48));
        title.setFill(Color.WHITE);
        title.setEffect(new DropShadow(10, Color.BLACK));


        TranslateTransition titleAnimation = new TranslateTransition(Duration.seconds(1), title);
        titleAnimation.setFromY(-100);
        titleAnimation.setToY(0);
        titleAnimation.play();

        Button inventoryBtn = createStyledButton("Manage Inventory");
        Button reportsBtn = createStyledButton("View Reports");
        Button settingsBtn = createStyledButton("Settings");
        Button logoutBtn = createStyledButton("Logout");


        VBox buttonLayout = new VBox(20, inventoryBtn, reportsBtn, settingsBtn, logoutBtn);
        buttonLayout.setAlignment(Pos.CENTER);

        FadeTransition buttonFade = new FadeTransition(Duration.seconds(1), buttonLayout);
        buttonFade.setFromValue(0);
        buttonFade.setToValue(1);
        buttonFade.play();

        Text footer = new Text("Warehouse Management App Done By Numan,Melih,Sai,Anandhu");
        footer.setFont(Font.font("Arial", 16));
        footer.setFill(Color.WHITE);

        Background gradientBackground = new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                                new Stop(0, Color.DARKBLUE),
                                new Stop(1, Color.DARKCYAN)),
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        );

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(title);
        mainLayout.setCenter(buttonLayout);
        mainLayout.setBottom(footer);

        BorderPane.setAlignment(title, Pos.CENTER);

        BorderPane.setAlignment(footer, Pos.CENTER);

        BorderPane.setMargin(footer, new javafx.geometry.Insets(10, 0, 10, 0));
        mainLayout.setBackground(gradientBackground);

        return mainLayout;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 18; -fx-background-color: #7fd38f; -fx-text-fill: white; -fx-padding: 10 20; -fx-cursor: hand; -fx-border-radius: 5; -fx-background-radius: 5;");
        button.setEffect(new DropShadow(5, Color.GRAY));

        button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 18; -fx-background-color: #44e9ff; -fx-text-fill: white; -fx-padding: 10 20; -fx-cursor: hand; -fx-border-radius: 5; -fx-background-radius: 5;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 18; -fx-background-color: #4d81b8; -fx-text-fill: white; -fx-padding: 10 20; -fx-cursor: hand; -fx-border-radius: 5; -fx-background-radius: 5;"));

        return button;
    }
}
