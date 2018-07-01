package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import progetto.model.IModel;
import progetto.model.MainBoardData;
import progetto.model.PreGameState;

public class MainBoardPaneController {

    @FXML
    private Label numberOfPlayers;
    @FXML
    private Label gameState;
    @FXML
    private Label currentPlayer;
    @FXML
    private HBox mainHBox;
    @FXML
    private AnchorPane preGameAnchorPane;
    @FXML
    private ExtractedDicesPaneController extractedDicesPaneController;
    private GUIView view;

    public void setup(GUIView view){
        this.view = view;
        extractedDicesPaneController.setup(view);
        view.getController().getObservable().getMainBoard().addObserver(ogg -> Platform.runLater(this::update));
        view.getController().getObservable().getRoundInformation().addObserver(ogg -> Platform.runLater(this::update));
    }

    private void update() {

        IModel model = view.getController().getModel();
        MainBoardData mainBoardData = model.getMainBoard().getData();
        if (mainBoardData.getGameState().getClass()!=PreGameState.class
                && mainHBox.getChildren().contains(preGameAnchorPane))
            mainHBox.getChildren().remove(preGameAnchorPane);
        numberOfPlayers.setText(Integer.toString(mainBoardData.getPlayerCount()));
        gameState.setText(mainBoardData.getGameState().getName());
        currentPlayer.setText("" + model.getRoundInformation().getData().getCurrentPlayer());

    }
}
