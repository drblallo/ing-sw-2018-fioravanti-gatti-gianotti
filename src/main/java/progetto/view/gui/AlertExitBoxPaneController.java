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

/**
 * Controller of the stage shown when the user want to leave the game
 */
public class AlertExitBoxPaneController {

    private GUIView view;
    private static Stage alertStage;

    /**
     * load the AlertExitBoxPane and prepare it to be shown
     * @param guiView
     */
    public static synchronized void setup(GUIView guiView){
        FXMLLoader fxmlLoader = new FXMLLoader(AlertExitBoxPaneController.class
                .getResource("AlertExitBoxPane.fxml"));
        Pane pane;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            pane = null;
        }

        AlertExitBoxPaneController alertExitBoxPaneController = fxmlLoader.getController();
        alertExitBoxPaneController.setView(guiView);
        alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setScene(new Scene(pane));
        alertStage.setTitle("Uscita dal gioco");

        Image image = new Image(GamePaneController.class.getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        pane.setBackground(background);

    }

    /**
     * set the current gui view
     * @param view the current gui view
     */
    public void setView(GUIView view){
        this.view = view;
    }

    /**
     * show the AlertExitBoxPane
     */
    public static void display(){
        alertStage.showAndWait();
    }

    /**
     * called when noButton is clicked
     * close the AlertExitBox stage
     */
    @FXML
    private void onNoButtonClicked(){
        alertStage.close();
    }

    /**
     * called when de yesButton is clicked
     * close the game
     */
    @FXML
    private void onYesButtonClicked(){
        view.getController().shutDown();
    }

}
