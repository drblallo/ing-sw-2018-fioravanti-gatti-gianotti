package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertTurnBoxPaneController {

    private static Stage alertStage;

    public static synchronized void setup(){
        FXMLLoader fxmlLoader = new FXMLLoader(AlertExitBoxPaneController.class
                .getResource("AlertTurnBoxPane.fxml"));
        Pane pane;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            pane = null;
        }
        alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setScene(new Scene(pane));
        alertStage.setTitle("Avviso turno");

        Image image = new Image(GamePaneController.class.getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        pane.setBackground(background);

    }

    public static void display(){
        alertStage.showAndWait();
    }

    @FXML
    private void onOkButtonClicked(){
        alertStage.close();
    }

}
