package progetto.integration.client.view.cl;

import progetto.model.PickedDicesSlotData;

public class ShowPickedDicesCommand extends AbstractCLViewCommand {

    private int numberOfCommand;

    public ShowPickedDicesCommand(CommandLineView commandLineView, int numberOfCommand) {
        super(commandLineView);
        this.numberOfCommand = numberOfCommand;
    }

    @Override
     public void exec(String[] params) {

        Printer printer = new Printer();
        PickedDicesSlotData pickedDicesSlotData = getController().getModel()
                .getPlayerBoard(getController().getChair()).getPickedDicesSlot().getData();
        if(pickedDicesSlotData.getNDices()!=0)
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
