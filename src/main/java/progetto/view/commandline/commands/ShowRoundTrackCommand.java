package progetto.view.commandline.commands;

import progetto.model.RoundTrackData;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;

public class ShowRoundTrackCommand extends AbstractCLViewCommand {

    public ShowRoundTrackCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    @Override
    public void exec(String[] args) {

        Printer printer = new Printer();
        RoundTrackData roundTrackData = getModel().getRoundTrack().getData();
        if(!roundTrackData.isFree(0))
            write(printer.printRoundTrack(roundTrackData));
        else write("Il tracciato dei round è ancora vuoto!");
    }

    @Override
    public String getHelp() {
        return "Mostra tracciato dei round";
    }
}
