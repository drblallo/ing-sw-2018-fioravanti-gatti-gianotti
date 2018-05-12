package progetto.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import progetto.game.MainBoard;
import progetto.game.MainBoardData;

public class MainBoardPaneController extends AbstractController<MainBoardData, MainBoard> {

    @FXML
    private Parent extractedDicesPane;

    @FXML
    private Label numberOfPlayers;

    private MainBoardData mainBoardData;

    private ExtractedDicesPaneController extractedDicesPaneController;


    public void setup(){

        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setRoot(extractedDicesPaneController);

        extractedDicesPaneController = fxmlLoader.<ExtractedDicesPaneController>getController();

        extractedDicesPaneController.setObservable(getObservable().getExtractedDices());

        Platform.runLater(this::update);

    }

    @Override
    protected void onObserverReplaced() {

        extractedDicesPaneController.setObservable(getObservable().getExtractedDices());

    }

    @Override
    protected void update() {

        MainBoardData newMainBoardData = getObservable().getMainBoardData();

        if(newMainBoardData==mainBoardData){

            return;

        }

        mainBoardData = newMainBoardData;

        numberOfPlayers.setText(Integer.toString(newMainBoardData.getPlayerCount()));
    }
}
