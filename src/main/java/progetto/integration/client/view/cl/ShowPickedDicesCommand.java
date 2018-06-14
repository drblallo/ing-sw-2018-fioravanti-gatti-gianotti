package progetto.integration.client.view.cl;

import progetto.model.PickedDicesSlotData;

public class ShowPickedDicesCommand extends AbstractStateSwitcherCommand {

    private Printer printer;
    private int numberOfCommand;

    public ShowPickedDicesCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView, new RoundViewState(commandLineView));
        printer = new Printer();
        this.numberOfCommand = numberOfCommand;
    }

    @Override
     protected void perform(String[] params) {
        PickedDicesSlotData pickedDicesSlotData = getController().getModel()
                .getPlayerBoard(getController().getChair()).getPickedDicesSlot().getData();
        write(printer.printDices(pickedDicesSlotData));
    }

    @Override
    public String getName() {
        return numberOfCommand + "";
    }

    @Override
    public String getHelp() {
        return "Mostra i miei dadi presi ";
    }
}
