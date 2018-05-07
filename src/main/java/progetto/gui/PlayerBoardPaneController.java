package progetto.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import progetto.game.PlayerBoard;

import java.io.IOException;

public class PlayerBoardPaneController extends AbstractController<PlayerBoard, PlayerBoard> {

    FXMLLoader fxmlLoader = new FXMLLoader();

    private Pane windowFramePane;
    private Pane pickedDicesSlotPane;
    private Pane dicePlacedFramePane;

    //COSI NON PENSO FUNZIONI!



    public void setUp() throws IOException{

        windowFramePane = fxmlLoader.load(WindowFramePaneController.class.getResource("WindowFramePane.fxml"));
        pickedDicesSlotPane = fxmlLoader.load(PickedDicesSlotPaneController.class.getResource("PickedDicesSlotPane.fxml"));


    }

    @Override
    protected void update() {



    }

    @Override
    protected void onObserverReplaced(){




    }

}
