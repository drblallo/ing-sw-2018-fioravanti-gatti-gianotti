package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import progetto.model.*;

public class MainBoardPaneController {

	private ComposableController<MainBoardData, AbstractMainBoard> mainBoard = new ComposableController<>();
	private ComposableController<RoundInformationData, Container<RoundInformationData>> roundInfo = new ComposableController<>();

	public MainBoardPaneController()
	{
		mainBoard.getOnModifiedCallback().addObserver((ogg) -> update());
		roundInfo.getOnModifiedCallback().addObserver((ogg) -> update());
	}

    @FXML
    private Label numberOfPlayers;

    @FXML
    private Label gameState;

    @FXML
    private Label currentPlayer;

    @FXML
    private ExtractedDicesPaneController extractedDicesPaneController;


    public void onGameChanged(IModel model) {

    	mainBoard.setObservable(model.getMainBoard());
    	roundInfo.setObservable(model.getRoundInformation());
        extractedDicesPaneController.setObservable(model.getMainBoard().getExtractedDices());

    }

    private void update() {

        MainBoardData mainBoardData = mainBoard.getLastData();

        numberOfPlayers.setText(Integer.toString(mainBoardData.getPlayerCount()));

        gameState.setText(mainBoardData.getGameState().getName());

        currentPlayer.setText("" + roundInfo.getLastData().getCurrentPlayer());

    }
}
