package progetto.view.commandline.commands;

import progetto.model.PickedDicesSlotData;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;

public class ShowPickedDicesCommand extends AbstractCLViewCommand {


    public ShowPickedDicesCommand(CommandLineView commandLineView) {
        super(commandLineView);
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
    public String getHelp() {
        return "Mostra i miei dadi presi ";
    }
}
