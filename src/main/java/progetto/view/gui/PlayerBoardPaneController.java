package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import progetto.model.AbstractPlayerBoard;
import progetto.model.PlayerBoardData;
import progetto.model.WindowFrame;


public class PlayerBoardPaneController extends AbstractController<PlayerBoardData, AbstractPlayerBoard> {

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

    private WindowFrame windowFrame;

    @Override
    protected void onObserverReplaced(){

        dicePlacedFramePaneController.setObservable(getObservable().getDicePlacedFrame());

        pickedDicesSlotPaneController.setObservable(getObservable().getPickedDicesSlot());

    }


    @Override
    protected void update() {

        WindowFrame newWindowFrame = getObservable().getData().getWindowFrame();

        if(newWindowFrame == windowFrame || newWindowFrame == null){

            return;

        }

        windowFramePaneController.setup(getObservable().getData().getWindowFrame());

        windowFrame = newWindowFrame;

    }

}
