package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

    @Override
    public void onPreShow(){
        Platform.runLater(this::update);
    }

    private void update(){
        GUIView view = getViewStateMachine().getGuiView();
        ArrayList<PlayerRanking> playerRankingArrayList = new ArrayList<>();
        IModel model = view.getController().getModel();
        for (int i = 0; i < model.getMainBoard().getData().getPlayerCount(); i++) {
            playerRankingArrayList.add(new PlayerRanking(i, model.getPlayerBoard(i).getData().getScore()));
        }
        playerRankingArrayList.sort(Comparator.comparingInt(PlayerRanking::getRanking));

        RoomView roomView = view.getController().getCurrentRoom();
        PlayerView winnerPlayer = roomView.getPlayerOfChair(0);
        if (winnerPlayer.getName()!=null)
            winner.setText(roomView.getPlayerOfChair(playerRankingArrayList.get(0).getNumberOfPlayer()).getName());
        else winner.setText("il giocatore n° " + winnerPlayer.getChairID());

        for (int i = 0; i<playerRankingArrayList.size(); i++){
            PlayerView playerView = roomView.getPlayer(playerRankingArrayList.get(i).getNumberOfPlayer());
            score.appendText((i+1) + "° Classificato: ");
            if (playerView.getName()!=null)
                score.appendText(playerView.getName());
            else score.appendText("giocatore numero " + playerView.getChairID());
            score.appendText("con " + playerRankingArrayList.get(i).getRanking() + " punti\n");
        }
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
