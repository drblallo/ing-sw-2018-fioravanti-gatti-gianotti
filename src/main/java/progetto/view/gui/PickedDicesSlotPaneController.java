package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import progetto.model.DataContainer;
import progetto.model.PickedDicesSlotData;

public class PickedDicesSlotPaneController extends AbstractController <PickedDicesSlotData,
        DataContainer<PickedDicesSlotData>> {

    @FXML
    private ListView<Label> listView;


    @Override
    protected void update() {

        PickedDicesSlotData pickedDicesSlotData = getObservable().getData();

        listView.getItems().clear();

        for(int i=0; i<pickedDicesSlotData.getNDices(); i++){

            listView.getItems().add(new Label(pickedDicesSlotData.getDicePlacementCondition(i).getDice().toString()));

        }

    }
}
