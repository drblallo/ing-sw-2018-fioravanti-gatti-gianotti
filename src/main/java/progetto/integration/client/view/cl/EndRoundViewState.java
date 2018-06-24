package progetto.integration.client.view.cl;

import progetto.model.EndRoundState;

import java.util.ArrayList;
import java.util.Comparator;

public class EndRoundViewState extends AbstractCLViewState {

    public EndRoundViewState(CommandLineView cl) {
        super("End round view state", cl);
    }

    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == EndRoundState.class;
    }

    @Override
    public void onApply() {
        registerCommand(new StartNewGameCommand(getView()));
        registerCommand(new CloseGameCommand(getView()));
    }

    @Override
    public String getMessage() {

        StringBuilder result = new StringBuilder();

        if (getModel().getMainBoard().getData().getPlayerCount() == 1){
            result.append("Il punteggio da raggiungere era: ")
                    .append(getModel().getMainBoard().getData().getSinglePlayerTarget())
                    .append("\nIl punteggio che hai ottenuto è: ")
                    .append(getModel().getPlayerBoard(getController().getChair()).getData().getScore())
                    .append("\n");
            if (getModel().getMainBoard().getData().getSinglePlayerTarget() <=
                    getModel().getPlayerBoard(getController().getChair()).getData().getScore())
                result.append("Hai vinto!\n");
            else result.append("Hai perso!\n");}
        else {
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
        }

        result.append("Scegli cosa fare ora:\n");
        return "\nLa partita è finita!\nClassifica: \n" + result.toString();
    }
}