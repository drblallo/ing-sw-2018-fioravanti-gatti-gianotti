package progetto.view.commandline.commands;

import progetto.controller.PlaceDiceAction;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.states.RoundViewState;

/**
 * Command to place a dice on the playerboard
 */
public class PlaceDiceCommand extends AbstractStateSwitcherCommand {

    /**
     * pubblic constructor
     * @param commandLineView the command line view that this command will modify
     */
    public PlaceDiceCommand(CommandLineView commandLineView) {
        super(commandLineView, new RoundViewState(commandLineView));
    }

    /**
     * if params contains a valid position of a dice among the picked ones and a valid position where that dice
     * can be placed, check if PlaceDiceAction can be executed, if positive send the action to the controller
     * @param params input by the user, should be the position of the selected dice among the picked ones and
     *               a valid position where that dice can be placed
     */
    @Override
    protected void perform(String[] params) {

        String toReturn = "Digitare una posizione valida!";

        if(params!=null && params.length == 3)
            try {
                int nDice = Integer.parseInt(params[0]);
                int posX = Integer.parseInt(params[1]);
                int posY = Integer.parseInt(params[2]);

                PlaceDiceAction placeDiceAction = new PlaceDiceAction(getController().getChair(), nDice, posX, posY);
                if(placeDiceAction.canBeExecuted(getModel())){
                    getController().sendAction(placeDiceAction);
                    write("Dado piazzato!");
                }
                else write(toReturn);
            } catch (NumberFormatException e) {
                write(toReturn);
            }
        else
			write(toReturn);
    }

    /**
     * Return infos about what this command does and how the input should be written
     * @return infos about what this command does and how the input should be written
     */
    @Override
    public String getHelp() {
        return "Posiziona un dado: (Formato: " + getName() + " <Numero del dado> <Posizione x> <Posizione y>)";
    }
}
