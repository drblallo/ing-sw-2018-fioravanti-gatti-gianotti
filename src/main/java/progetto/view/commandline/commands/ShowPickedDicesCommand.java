package progetto.view.commandline.commands;

import progetto.model.PickedDicesSlotData;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;

/**
 * Command to show picked dices of the user
 * @author Federica
 */
public class ShowPickedDicesCommand extends AbstractCLViewCommand {

    /**
     * public constructor
     * @param commandLineView the command line view that this command will modifiy
      */
    public ShowPickedDicesCommand(CommandLineView commandLineView) {
        super(commandLineView);
    }

    /**
     * Show the picked dices of the user
     * @param params input not needed
     */
    @Override
     public void exec(String[] params) {

        Printer printer = new Printer();
        PickedDicesSlotData pickedDicesSlotData = getController().getModel()
                .getPlayerBoard(getController().getChair()).getPickedDicesSlot().getData();
        if(pickedDicesSlotData.getNDices()!=0)
            write(printer.printDices(pickedDicesSlotData));
    }

    /**
     * Return infos about what this command does
     * @return infos about what this command does
     */
    @Override
    public String getHelp() {
        return "Mostra i miei dadi presi ";
    }
}
