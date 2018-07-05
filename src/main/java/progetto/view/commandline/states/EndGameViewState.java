package progetto.view.commandline.states;

import progetto.model.EndGameState;
import progetto.model.IModel;
import progetto.network.PlayerView;
import progetto.network.RoomView;
import progetto.view.PlayerRanking;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.CloseGameCommand;
import progetto.view.commandline.commands.StartNewGameCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * State shown at the end of a game
 */
public class EndGameViewState extends AbstractCLViewState {

    private static final String YOU_WIN = "HAI VINTO!!\n\n";
    private static final String YOU_LOSE = "HAI PERSO\n\n";
    /**
     * public constructor
     * @param cl the command line view that this state will be applied to
     */
    public EndGameViewState(CommandLineView cl) {
        super("End round view state", cl);
    }

    /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == EndGameState.class;
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {
        registerCommand(new StartNewGameCommand(getView()));
        registerCommand(new CloseGameCommand(getView()));
    }

    /**
     * Return the result of the game and ask to the user what he want to do
     * @return the result of the game and ask to the user what he want to do
     */
    @Override
    public String getMessage() {

        StringBuilder result;

        if (getModel().getMainBoard().getData().getPlayerCount() == 1)
            result = singlePlayerScore();
        else result = multiPlayerScore();

        result.append(" \n\nScegli cosa fare ora:\n");
        return result.toString();
    }

    /**
     * Calculate the result of a single player game
     * @return the result of a single player game
     */
    private StringBuilder singlePlayerScore(){
        IModel model = getModel();
        StringBuilder result = new StringBuilder();
        int target = model.getMainBoard().getData().getSinglePlayerTarget();
        int actualScore = model.getPlayerBoard(0).getData().getScore();

        if (target<actualScore)
            result.append(YOU_WIN);
        else result.append(YOU_LOSE);

        result.append("Il punteggio da raggiungere era: " + target);
        result.append("\nHai totalizzato: " + actualScore);

        return result;
    }

    /**
     * Calculate the result of a multi player game
     * @return the result of a multi player game
     */
    private StringBuilder multiPlayerScore(){

        StringBuilder result = new StringBuilder();

        ArrayList<PlayerRanking> playerRankingArrayList = new ArrayList<>();
        for (int i = 0; i < getModel().getMainBoard().getData().getPlayerCount(); i++) {
            playerRankingArrayList.add(new PlayerRanking(i, getModel().getPlayerBoard(i).getData().getScore()));
        }
        playerRankingArrayList.sort(Comparator.comparingInt(PlayerRanking::getRanking));
        Collections.reverse(playerRankingArrayList);

        if (getController().getChair()!=-1) {
            if (playerRankingArrayList.get(0).getNumberOfPlayer() == getController().getChair())
                result.append(YOU_WIN);
            else result.append(YOU_LOSE);
        }
        else {
            if (playerRankingArrayList.get(0).getNumberOfPlayer() == 0)
                result.append(YOU_WIN);
            else result.append(YOU_LOSE);
        }

        PlayerView playerView;
        RoomView roomView = getController().getCurrentRoom();

        for (int i = 0; i < playerRankingArrayList.size(); i++) {

            result.append((i+1) + "° Classificato: ");
            playerView = roomView.getPlayer(playerRankingArrayList.get(i).getNumberOfPlayer());
            if (playerView!=null && playerView.getName()!=null)
                result.append(playerView.getName());
            else result.append("Giocatore numero " + playerRankingArrayList.get(i).getNumberOfPlayer());
            result.append(" con " + playerRankingArrayList.get(i).getRanking() + " punti\n");
        }

        return result;
    }

}