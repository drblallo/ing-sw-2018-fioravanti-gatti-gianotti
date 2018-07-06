package progetto.view.commandline.states;

import progetto.model.FrameSelectionState;
import progetto.view.commandline.CommandLineView;
import progetto.view.commandline.DifferenceDescriptor;
import progetto.view.commandline.commands.SelectWindowCommand;
import progetto.view.commandline.printer.DrawableUtils;

/**
 * State where the user can choose his window frame at the beginning of a game
 */
public class FrameSelectionViewState extends AbstractCLViewState {

    private static final int NUMBER_OF_WINDOW = 4;

    /**
     * public constructor
     * @param view the command line view that this state will be applied to
     */
    public FrameSelectionViewState(CommandLineView view) {
        super("frame selection view state", view);
    }

     /**
     * Check if this state is still valid
     * @return if this state is still valid
     */
    @Override
    public boolean isStillValid() {
        return getModel().getMainBoard().getData().getGameState().getClass() == FrameSelectionState.class;
    }

    /**
     * add difference descriptors to this state
     */
    @Override
    public void addObservers() {
        if (getView().getController().getChair() >= 0) {
            getDifferenceDescriptors().add(
                    new DifferenceDescriptor<>
                            (
                                    getController().getObservable().getPlayerBoard(getController().getChair()),
                                    (data1, data2) -> true,
                                    (oldData, newData) -> write(DrawableUtils.getChooseWindowFrame(newData,
                                            getModel().getMainBoard().getData().getPlayerCount() == 1).toString())
                            ));
        }
    }

    /**
     * load the commands associated to this stage
     */
    @Override
    public void onApply()
    {
        if (getView().getController().getChair() >= 0)
            for (int i = 1; i< NUMBER_OF_WINDOW+1; i++)
                registerCommand(new SelectWindowCommand(getView(), i));
	}

    /**
     * Return a message associated to this stage
     * @return a message associated to this stage
     */
    @Override
    public String getMessage() {
        if(getController().getChair()<0)
            return "\nAttendere l'inizio della partita!\n";
        String string = "\nScegliere una tra le vetrate proposte:\n";
        if(getModel().getPlayerBoard(getController().getChair()).getData()
                .getExtractedWindowFrameCouplesWindowFrame()!=null)
             string = string + DrawableUtils.getChooseWindowFrame(
                (getModel().getPlayerBoard(getController().getChair()).getData()),
                        getModel().getMainBoard().getData().getPlayerCount() == 1).toString();

        return string;
    }
}
