package progetto.integration.client.view.cl;

import progetto.model.RoundTrackData;

public class ShowRoundTrackCommand extends AbstractCLViewCommand {

    private int numberOfCommand;

    public ShowRoundTrackCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView);
        this.numberOfCommand = numberOfCommand;
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
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Mostra tracciato dei round";
    }
}
