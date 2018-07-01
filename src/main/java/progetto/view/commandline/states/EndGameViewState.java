package progetto.view.commandline.states;

import progetto.model.EndGameState;
import progetto.view.PlayerRanking;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.CloseGameCommand;
import progetto.view.commandline.commands.StartNewGameCommand;

import java.util.ArrayList;
import java.util.Comparator;

public class EndGameViewState extends AbstractCLViewState {

    public EndGameViewState(CommandLineView cl) {
        super("End round view state", cl);
    }

    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == EndGameState.class;
    }

    @Override
    public void onApply() {
        registerCommand(new StartNewGameCommand(getView()));
        registerCommand(new CloseGameCommand(getView()));
    }

    @Override
    public String getMessage() {

        StringBuilder result = new StringBuilder();

        ArrayList<PlayerRanking> playerRankingArrayList = new ArrayList<>();
        for (int i = 0; i < getModel().getMainBoard().getData().getPlayerCount(); i++) {
            playerRankingArrayList.add(new PlayerRanking(i, getModel().getPlayerBoard(i).getData().getScore()));
        }
        playerRankingArrayList.sort(Comparator.comparingInt(PlayerRanking::getRanking));

        for (int i = 0; i < playerRankingArrayList.size(); i++) {
            result.append((i + 1) + "° Classifcato: Giocatore numero "
                    + playerRankingArrayList.get(i).getNumberOfPlayer() + " con punteggio di " +
                    playerRankingArrayList.get(i).getRanking() + '\n');
        }

        result.append("Scegli cosa fare ora:\n");
        return "\nLa partita è finita!\nClassifica: \n" + result.toString();
    }
}