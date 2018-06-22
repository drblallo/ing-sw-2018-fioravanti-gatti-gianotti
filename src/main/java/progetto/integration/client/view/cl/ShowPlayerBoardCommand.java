package progetto.integration.client.view.cl;

import progetto.model.IPlayerBoard;

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
        IPlayerBoard plb = getModel().getPlayerBoard(nPlayer);
        write(printer.printPlayerBoard(plb.getDicePlacedFrame().getData(), plb.getData().getWindowFrame()));
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
