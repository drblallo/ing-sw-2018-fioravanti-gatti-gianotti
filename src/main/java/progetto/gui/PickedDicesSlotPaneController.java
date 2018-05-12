package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.game.PickedDicesSlot;
import progetto.game.PickedDicesSlotData;

public class PickedDicesSlotPaneController extends AbstractController <PickedDicesSlotData, PickedDicesSlot> {

    @FXML
    private ListView<Label> listView;

    private PickedDicesSlotData pickedDicesSlotData;


    @Override
    protected void update() {

        PickedDicesSlotData newPickedDiceSlotData = getObservable().getPickedDicesSlotData();

        if(newPickedDiceSlotData==pickedDicesSlotData){

            return;

        }

        pickedDicesSlotData = newPickedDiceSlotData;

        listView.getItems().clear();

        for(int i=0; i<pickedDicesSlotData.getNDices(); i++){

            listView.getItems().add(new Label(pickedDicesSlotData.getDicePlacementCondition(i).toString()));

        }

    }
}
