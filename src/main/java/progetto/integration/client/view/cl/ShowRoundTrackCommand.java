package progetto.integration.client.view.cl;

import progetto.model.RoundTrackData;

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
