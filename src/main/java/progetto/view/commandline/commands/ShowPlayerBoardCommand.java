package progetto.view.commandline.commands;

import progetto.model.IPlayerBoard;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.printer.DrawableUtils;
import progetto.view.commandline.states.AbstractCLViewState;

/**
 * Command to show the playerboard of a player
 * @author Federica
 */
public class ShowPlayerBoardCommand extends AbstractStateSwitcherCommand {

    private int nPlayer;

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     * @param nPlayer the number of the player of which the command will show the playerboard
     * @param abstractCLViewState the state where to go after showing the playerboard
     */
    public ShowPlayerBoardCommand(CommandLineView commandLineView, int nPlayer, AbstractCLViewState abstractCLViewState) {
        super(commandLineView, abstractCLViewState);
        this.nPlayer = nPlayer;
    }

    /**
     * Show the playerboard of the player passed in the constructor
     * @param params no input needed
     */
    @Override
    protected void perform(String[] params) {
        IPlayerBoard playerBoard = getModel().getPlayerBoard(nPlayer);
        write(DrawableUtils.getPlayerBoard(playerBoard,
                getModel().getMainBoard().getData().getPlayerCount() == 1).toString());
    }

    /**
     * Return infos about what playerboard this command shows
     * @return infos about what playerboard this command shows
     */
    @Override
    public String getHelp() {
        if(nPlayer == getController().getChair())
            return "Mia scheda";

        if (getController().getCurrentRoom().getPlayerOfChair(nPlayer) == null)
            return "Scheda del giocatore numero " + nPlayer;

        return "Scheda di " + getController().getCurrentRoom().getPlayerOfChair(nPlayer).getName();
    }
}
