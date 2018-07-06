package progetto.view.commandline.states;

import progetto.model.ExtractedDicesData;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.PickDiceCommand;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.printer.DrawableUtils;

/**
 * State where the user can pick a dice from the extracted ones
 */
public class PickDiceState extends AbstractCLViewState
{
    /**
     * public constructor
     * @param cl the command line view that this state will be applied to
     */
    public PickDiceState(CommandLineView cl)
    {
        super("Pick dice state", cl);
    }

    /**
     * load the commands associated to this stage and print the extracted dices
     */
    @Override
    public void onApply() {

        ExtractedDicesData extractedDicesData = getView().getController()
                .getModel().getMainBoard().getExtractedDices().getData();

        for (int i=0; i< extractedDicesData.getNumberOfDices(); i++){
            registerCommand(new PickDiceCommand(getView(), i));
        }

        registerCommand(new ReturnCommand(getView(), new GameTransitionState(getView()), "Indietro"));
        getView().write(DrawableUtils.getExtracted(extractedDicesData).toString());

    }

    /**
     * returns a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {
        return "\nSeleziona un dato tra quelli estratti: \n";
    }
}