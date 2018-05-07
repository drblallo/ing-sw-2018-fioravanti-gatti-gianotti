package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import progetto.game.PickedDicesSlot;

public class PickedDicesSlotPaneController extends AbstractController <PickedDicesSlot, PickedDicesSlot> {

    @FXML
    private ListView listView;

    @Override
    protected void update() {

        listView.getItems().clear();

        //aspetta getter di mike

    }
}
