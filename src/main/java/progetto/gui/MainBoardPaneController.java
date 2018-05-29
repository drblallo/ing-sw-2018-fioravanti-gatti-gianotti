package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import progetto.game.AbstractMainBoard;
import progetto.game.MainBoardData;

public class MainBoardPaneController extends AbstractController<MainBoardData, AbstractMainBoard> {

    @FXML
    private Label numberOfPlayers;

    @FXML
    private Label gameState;


    @FXML
    private ExtractedDicesPaneController extractedDicesPaneController;

    @FXML
    private Label currentPlayer;

    @Override
    protected void onObserverReplaced() {

        extractedDicesPaneController.setObservable(getObservable().getExtractedDices());

    }

    @Override
    protected void update() {

        MainBoardData mainBoardData = getObservable().getData();

        numberOfPlayers.setText(Integer.toString(mainBoardData.getPlayerCount()));

        gameState.setText(mainBoardData.getGameState().getName());

        currentPlayer.setText("" + mainBoardData.getCurrentPlayer());

    }
}
