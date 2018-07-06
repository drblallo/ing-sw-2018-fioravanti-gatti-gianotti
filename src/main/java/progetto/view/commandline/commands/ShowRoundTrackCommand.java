package progetto.view.commandline.commands;

import progetto.model.RoundTrackData;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;

/**
 * Command to show the round track
 * @author Federica
 */
public class ShowRoundTrackCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modify
     */
    public ShowRoundTrackCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * Show the round track
     * @param args input not needed
     */
    @Override
    public void exec(String[] args) {

        Printer printer = new Printer();
        RoundTrackData roundTrackData = getModel().getRoundTrack().getData();
        if(!roundTrackData.isFree(0))
            write(printer.printRoundTrack(roundTrackData));
        else write("\nIl tracciato dei round è ancora vuoto!");
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Mostra tracciato dei round";
    }
}
