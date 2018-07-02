package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import progetto.model.IModel;
import progetto.network.PlayerView;
import progetto.network.RoomView;
import progetto.view.PlayerRanking;

import java.util.ArrayList;
import java.util.Comparator;

public class EndGamePaneController extends AbstractClientStateController{

    @FXML
    private Label winner;
    @FXML
    private TextArea score;
    @FXML
    private AnchorPane myPane;
    private static final String YOU_WIN = "vinto!";
    private static final String YOU_LOSE = "perso";

    @Override
    public void setup() {
        Image image = new Image(GamePaneController.class.getResourceAsStream("toolcard_large.png"));
        BackgroundSize backgroundSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        myPane.setBackground(background);
    }

    @Override
    public void onPreShow(){
        Platform.runLater(this::update);
    }

    private void update(){
        IModel model = getViewStateMachine().getGuiView().getController().getModel();
        score.clear();
        if (model.getMainBoard().getData().getPlayerCount()!=1)
            multiPlayerScore(model);
        else singlePlayerScore(model);
    }

    private void multiPlayerScore(IModel model){
        ArrayList<PlayerRanking> playerRankingArrayList = new ArrayList<>();
        for (int i = 0; i < model.getMainBoard().getData().getPlayerCount(); i++) {
            playerRankingArrayList.add(new PlayerRanking(i, model.getPlayerBoard(i).getData().getScore()));
        }
        playerRankingArrayList.sort(Comparator.comparingInt(PlayerRanking::getRanking));

        if (getController().getChair()!=-1){
            if (playerRankingArrayList.get(0).getNumberOfPlayer() == getController().getChair())
                winner.setText(YOU_WIN);
            else winner.setText(YOU_LOSE);
        }
        else {
            if (playerRankingArrayList.get(0).getNumberOfPlayer() == 0)
                winner.setText(YOU_WIN);
            else winner.setText(YOU_LOSE);
        }

        RoomView roomView = getViewStateMachine().getGuiView().getController().getCurrentRoom();

        for (int i = 0; i<playerRankingArrayList.size(); i++){
            score.appendText((i+1) + "° Classificato: ");

            PlayerView playerView = roomView.getPlayer(playerRankingArrayList.get(i).getNumberOfPlayer());

            if (playerView!= null && playerView.getName()!=null)
                score.appendText(playerView.getName());
            else score.appendText("giocatore numero " + playerRankingArrayList.get(i).getNumberOfPlayer());

            score.appendText(" con " + playerRankingArrayList.get(i).getRanking() + " punti\n");
        }
    }

    private void singlePlayerScore(IModel model){
        int target = model.getMainBoard().getData().getSinglePlayerTarget();
        int actualScore = model.getPlayerBoard(0).getData().getScore();
        score.appendText("Il punteggio da raggiungere era: " + target);
        score.appendText("\nHai totalizzato: " + actualScore);
        if (target<actualScore)
            winner.setText(YOU_WIN);
        else winner.setText(YOU_LOSE);
    }

    @FXML
    private void onMenuButtonClicked(){
        getViewStateMachine().getStateFromName("StartingPane.fxml").show(false);
    }

    @FXML
    private void onTurnOffButtonClicked(){
        AlertExitBoxPaneController.display();
    }

}
