package progetto.view.commandline.states;

import progetto.model.FrameSelectionState;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.Printer;
import progetto.view.commandline.commands.SelectWindowCommand;

public class FrameSelectionViewState extends AbstractCLViewState {

    private static final int NUMBER_OF_WINDOW = 4;

    public FrameSelectionViewState(CommandLineView view) {
        super("frame selection view state", view);
    }

    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == FrameSelectionState.class;
    }

    @Override
    public void addObservers() {
        if (getView().getController().getChair() >= 0) {
            getDifferenceDescriptors().add(
                    new DifferenceDescriptor<>
                            (
                                    getController().getObservable().getPlayerBoard(getController().getChair()),
                                    (data1, data2) -> true,
                                    (oldData, newData) -> write(new Printer().printWindowFrameChoices(newData,
                                            getModel().getMainBoard().getData().getPlayerCount() == 1))
                            ));
        }
    }

    @Override
    public void onApply()
    {
        if (getView().getController().getChair() >= 0)
            for (int i = 1; i< NUMBER_OF_WINDOW+1; i++)
                registerCommand(new SelectWindowCommand(getView(), i));
	}


    @Override
    public String getMessage() {
        if(getController().getChair()<0)
            return "\nAttendere l'inizio della partita!\n";
        String string = "\nScegliere una tra le vetrate proposte:\n";
        if(getModel().getPlayerBoard(getController().getChair()).getData()
                .getExtractedWindowFrameCouplesWindowFrame()!=null)
             string = string + new Printer().printWindowFrameChoices
                (getModel().getPlayerBoard(getController().getChair()).getData(),
                        getModel().getMainBoard().getData().getPlayerCount() == 1);

        return string;
    }
}
