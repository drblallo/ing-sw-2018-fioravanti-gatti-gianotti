package progetto.view.gui;

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
import java.util.Collections;
import java.util.Comparator;

/**
 * this is the class that handles the choose end game fxml. This class is only instanced by javafx, this mean that
 * must have a default constructor.
 * @author Federica
 */
public class EndGamePaneController extends AbstractStateController {

    @FXML
    private Label winner;
    @FXML
    private TextArea score;
    @FXML
    private AnchorPane myPane;
    private static final String YOU_WIN = "vinto!";
    private static final String YOU_LOSE = "perso";

    /**
     * set up this object, it is equivalent to a constructor since there is no access to it
     */
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

    /**
     * called every time this window is displayed
     * update results
     */
    @Override
    public void onPreShow(){
        IModel model = getModel();
        score.clear();
        if (model.getMainBoard().getData().getPlayerCount()!=1)
            multiPlayerScore(model);
        else singlePlayerScore(model);
    }

    /**
     * calculate the result of a multi player game
     * @param model current model
     */
    private void multiPlayerScore(IModel model){
        ArrayList<PlayerRanking> playerRankingArrayList = new ArrayList<>();
        for (int i = 0; i < model.getMainBoard().getData().getPlayerCount(); i++) {
            playerRankingArrayList.add(new PlayerRanking(i, model.getPlayerBoard(i).getData().getScore()));
        }
        playerRankingArrayList.sort(Comparator.comparingInt(PlayerRanking::getRanking));
        Collections.reverse(playerRankingArrayList);

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

        RoomView roomView = getStateManager().getGuiView().getController().getCurrentRoom();

        for (int i = 0; i<playerRankingArrayList.size(); i++){
            score.appendText((i+1) + "° Classificato: ");

            PlayerView playerView = roomView.getPlayerOfChair(playerRankingArrayList.get(i).getNumberOfPlayer());

            if (playerView!= null && playerView.getName()!=null)
                score.appendText(playerView.getName());
            else score.appendText("giocatore numero " + playerRankingArrayList.get(i).getNumberOfPlayer());

            score.appendText(" con " + playerRankingArrayList.get(i).getRanking() + " punti\n");
        }
    }

    /**
     * calculate the result of a single player game
     * @param model current model
     */
    private void singlePlayerScore(IModel model){
        int target = model.getMainBoard().getData().getSinglePlayerTarget();
        int actualScore = model.getPlayerBoard(0).getData().getScore();
        score.appendText("Il punteggio da raggiungere era: " + target);
        score.appendText("\nHai totalizzato: " + actualScore);
        if (target<actualScore)
            winner.setText(YOU_WIN);
        else winner.setText(YOU_LOSE);
    }

    /**
     * called when menuButton is clicked
     * change the scene to Starting Pane
     */
    @FXML
    private void onMenuButtonClicked(){
        getStateManager().getStateFromName("StartingPane.fxml").show(false);
    }

    /**
     * called when turnOffButton is clicked
     * show the AlertExitBox stage
     */
    @FXML
    private void onTurnOffButtonClicked(){
        AlertExitBoxPaneController.display();
    }

}
