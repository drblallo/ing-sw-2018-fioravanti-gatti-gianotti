package progetto.view.commandline.states;

import progetto.network.RoomView;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.commands.ReturnCommand;
import progetto.view.commandline.commands.SitOnChairCommand;

public class ChairSelectionState extends AbstractCLViewState {

    public ChairSelectionState(CommandLineView view) {
        super("ChairSelectionState",view);
    }

    @Override
    public void onApply() {

        RoomView roomView = getController().getCurrentRoom();

        int numberOfPlayers = getModel().getMainBoard().getData().getPlayerCount();

        int i;
        for(i = 0; i < numberOfPlayers; i++)
        {

            if(roomView.getPlayerOfChair(i) == null)
                registerCommand(new SitOnChairCommand(getView(), i));

        }

        registerCommand(new ReturnCommand(getView(), new PreGameViewState(getView()), "Indietro"));

    }

    @Override
    public String getMessage() {
        return "\nSeleziona la sedia sulla quale desideri sederti:";
    }
}
