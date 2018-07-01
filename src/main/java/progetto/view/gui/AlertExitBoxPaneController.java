package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertExitBoxPaneController {

    private static GUIView view;
    private static Stage alertStage;

    public static synchronized void setup(GUIView guiView){
        view = guiView;
        FXMLLoader fxmlLoader = new FXMLLoader(AlertExitBoxPaneController.class
                .getResource("AlertExitBoxPane.fxml"));
        Pane pane;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            pane = null;
        }
        alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setScene(new Scene(pane));
        alertStage.setTitle("Uscita dal gioco");
    }

    public static void display(){
        alertStage.showAndWait();
    }

    @FXML
    private void onNoButtonClicked(){
        alertStage.close();
    }

    @FXML
    private void onYesButtonClicked(){
        view.getController().shutDown();
    }

}
