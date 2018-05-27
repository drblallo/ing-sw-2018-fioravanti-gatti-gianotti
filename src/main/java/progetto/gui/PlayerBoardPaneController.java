package progetto.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import progetto.game.*;


public class PlayerBoardPaneController extends AbstractController<PlayerBoardData, PlayerBoard> {

    @FXML
    private Parent dicePlacedFramePane;

    @FXML
    private Parent windowFramePane;

    @FXML
    private Parent pickedDicesSlotPane;

    @FXML
    private DicePlacedFramePaneController dicePlacedFramePaneController;

    @FXML
    private WindowFramePaneController windowFramePaneController;

    @FXML
    private PickedDicesSlotPaneController pickedDicesSlotPaneController;


    protected void setup() {

        dicePlacedFramePaneController.setObservable(getObservable().getDicePlacedFrame());

        if(getObservable().getPlayerBoardData().getWindowFrame()!=null){

            windowFramePaneController.setup(getObservable().getPlayerBoardData().getWindowFrame());

        }
        pickedDicesSlotPaneController.setObservable(getObservable().getPickedDicesSlot());

        Platform.runLater(this::update);

    }

    @Override
    protected void onObserverReplaced(){

        dicePlacedFramePaneController.setObservable(getObservable().getDicePlacedFrame());
        if(getObservable().getPlayerBoardData().getWindowFrame()!=null){

            windowFramePaneController.setup(getObservable().getPlayerBoardData().getWindowFrame());

        }
        pickedDicesSlotPaneController.setObservable(getObservable().getPickedDicesSlot());

    }


    @Override
    protected void update() {

        //

    }

}
