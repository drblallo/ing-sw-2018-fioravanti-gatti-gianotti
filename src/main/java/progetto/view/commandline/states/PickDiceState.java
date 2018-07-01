package progetto.view.commandline.states;

import progetto.model.ExtractedDicesData;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.Printer;
import progetto.view.commandline.commands.PickDiceCommand;

public class PickDiceState extends AbstractCLViewState
{

    public PickDiceState(CommandLineView cl)
    {
        super("Pick dice state", cl);
    }

    @Override
    public void onApply() {

        Printer printer = new Printer();
        ExtractedDicesData extractedDicesData = getView().getController()
                .getModel().getMainBoard().getExtractedDices().getData();

        for (int i=0; i< extractedDicesData.getNumberOfDices(); i++){
            registerCommand(new PickDiceCommand(getView(), i));
        }

        getView().write(printer.printDices(extractedDicesData));

    }

    @Override
    public String getMessage() {
        return "\nSeleziona un dato tra quelli estratti: \n";
    }
}