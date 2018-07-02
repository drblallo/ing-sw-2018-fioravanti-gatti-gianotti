package progetto.view.commandline.commands;

import progetto.model.IPlayerBoard;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;
import progetto.view.commandline.states.AbstractCLViewState;

public class ShowPlayerBoardCommand extends AbstractStateSwitcherCommand {

    private int nPlayer;
    private Printer printer;

    public ShowPlayerBoardCommand(CommandLineView commandLineView, int nPlayer,
                                  Printer printer, AbstractCLViewState abstractCLViewState) {
        super(commandLineView, abstractCLViewState);
        this.nPlayer = nPlayer;
        this.printer = printer;
    }

    @Override
    protected void perform(String[] params) {
        IPlayerBoard playerBoard = getModel().getPlayerBoard(nPlayer);
        write(printer.printPlayerBoard(playerBoard.getDicePlacedFrame().getData(),
                playerBoard.getData().getWindowFrame(),
                getModel().getMainBoard().getData().getPlayerCount() == 1));
    }

    @Override
    public String getHelp() {
        if(nPlayer == getController().getChair())
            return "Mia scheda";

        if (getController().getCurrentRoom().getPlayerOfChair(nPlayer) == null)
            return "Scheda del giocatore numero " + nPlayer;

        return "Scheda di " + getController().getCurrentRoom().getPlayerOfChair(nPlayer).getName();
    }
}
