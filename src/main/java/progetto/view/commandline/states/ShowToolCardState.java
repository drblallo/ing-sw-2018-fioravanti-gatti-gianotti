package progetto.view.commandline.states;

import progetto.model.ToolCardState;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.commands.UseToolCardCommand;

/**
 * State where the user can see the available tool cards and can select one of them
 */
public class ShowToolCardState extends AbstractCLViewState {

    /**
     * public constructor
     * @param cl the command line view that this state will be applied to
     */
    public ShowToolCardState(CommandLineView cl) {
        super("Show tool card state", cl);
    }

    /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass()!= ToolCardState.class;
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply() {

        for (int i = 0; i < getModel().getMainBoard().getData().getToolCards().size(); i++)
            registerCommand(new UseToolCardCommand(getView(), i));
        registerCommand(new ReturnCommand(getView(), new GameTransitionState(getView()), "Indietro"));
    }

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {

        StringBuilder toReturn = new StringBuilder();
        toReturn.append("\nScegli quale carta utilizzare tra quelle proposte:\n");
        if (getModel().getMainBoard().getData().getPlayerCount() != 1)
            toReturn.append("Hai " + getModel().getPlayerBoard(getController().getChair()).getData().getToken()
            + " punti favore\n");
        return toReturn.toString();
    }
}
