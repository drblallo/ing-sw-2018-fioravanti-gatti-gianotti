package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.game.PickedDicesSlot;
import progetto.game.PickedDicesSlotData;

public class PickedDicesSlotPaneController extends AbstractController <PickedDicesSlotData, PickedDicesSlot> {

    @FXML
    private ListView<Label> listView;


    @Override
    protected void update() {

        PickedDicesSlotData pickedDicesSlotData = getObservable().getPickedDicesSlotData();

        listView.getItems().clear();

        for(int i=0; i<pickedDicesSlotData.getNDices(); i++){

            listView.getItems().add(new Label(pickedDicesSlotData.getDicePlacementCondition(i).getDice().toString()));

        }

    }
}
