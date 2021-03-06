package client.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
    Box which appears to inform players of an event.
 */

public class AlertBox {

    public static void display(String title, String message, Stage mainStage){

        // Setting up Pop up Window
        Stage window = new Stage();
        window.initStyle(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(250);
        window.setX(mainStage.getScene().getWidth()/2 - window.getMinWidth()/2);
        window.setY(mainStage.getScene().getHeight()/2);

        // Creating Nodes
        Label label = new Label();
        label.setText(message);
        label.setTextFill(Color.rgb(232, 142, 39));
        Button confirmButton = new Button("OK");
        confirmButton.setOnAction(e -> window.close());

        // Applying Layout
        VBox yLayout = new VBox(20);
        yLayout.setPadding(new Insets(20, 20, 20, 20));
        HBox xLayout = new HBox(20);
        yLayout.setAlignment(Pos.CENTER);
        xLayout.setAlignment(Pos.CENTER);
        xLayout.getChildren().addAll(confirmButton);
        yLayout.getChildren().addAll(label, xLayout);

        // Applying Style and Showing window
        Scene scene = new Scene(yLayout);
        scene.getStylesheets().add("/client/resources/css/alertBox.css");
        window.setScene(scene);
        window.showAndWait();
    }
}
