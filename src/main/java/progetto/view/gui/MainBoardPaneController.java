package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import progetto.model.AbstractMainBoard;
import progetto.model.MainBoardData;

public class MainBoardPaneController extends AbstractController<MainBoardData, AbstractMainBoard> {

    @FXML
    private Label numberOfPlayers;

    @FXML
    private Label gameState;

    @FXML
    private Label currentPlayer;

    @FXML
    private ExtractedDicesPaneController extractedDicesPaneController;

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
