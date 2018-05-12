package progetto.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import progetto.game.*;


public class PlayerBoardPaneController extends AbstractController<PlayerBoardData, PlayerBoard> {

    @FXML
    private Parent dicePlacedFramePane;

    @FXML
    private Parent windowFramePane;

    @FXML
    private Parent pickedDicesSlotPane;

    private DicePlacedFramePaneController dicePlacedFramePaneController;

    private WindowFramePaneController windowFramePaneController;

    private PickedDicesSlotPaneController pickedDicesSlotPaneController;


    protected void setup() {

        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setRoot(dicePlacedFramePane);
        dicePlacedFramePaneController = fxmlLoader.<DicePlacedFramePaneController>getController();

        fxmlLoader.setRoot(windowFramePane);
        windowFramePaneController = fxmlLoader.<WindowFramePaneController>getController();

        fxmlLoader.setRoot(pickedDicesSlotPane);
        pickedDicesSlotPaneController = fxmlLoader.<PickedDicesSlotPaneController>getController();

        dicePlacedFramePaneController.setObservable(getObservable().getDicePlacedFrame());
        windowFramePaneController.setup(getObservable().getPlayerBoardData().getWindowFrame());
        pickedDicesSlotPaneController.setObservable(getObservable().getPickedDicesSlot());

        Platform.runLater(this::update);

    }

    @Override
    protected void onObserverReplaced(){

        dicePlacedFramePaneController.setObservable(getObservable().getDicePlacedFrame());
        windowFramePaneController.setup(getObservable().getPlayerBoardData().getWindowFrame());
        pickedDicesSlotPaneController.setObservable(getObservable().getPickedDicesSlot());

    }


    @Override
    protected void update() {

        //

    }

}
