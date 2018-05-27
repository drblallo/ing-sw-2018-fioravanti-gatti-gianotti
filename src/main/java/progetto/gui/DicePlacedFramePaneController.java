package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import progetto.game.DicePlacedFrame;
import progetto.game.DicePlacedFrameData;

public class DicePlacedFramePaneController extends AbstractController <DicePlacedFrameData, DicePlacedFrame> {

    @FXML
    private Label number;

    @FXML
    private GridPane gridPane;

    @Override
    protected void update() {

        DicePlacedFrameData dicePlacedFrameData = getObservable().getDicePlacedFrameData();

        number.setText(Integer.toString(dicePlacedFrameData.getNDices()));

        for(int i=0; i<DicePlacedFrameData.MAX_NUMBER_OF_ROWS-1; i++){

            for (int j=0; j<DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS-1; j++){

                if(dicePlacedFrameData.getDice(i,j)!=null){

                    gridPane.add(new Label(dicePlacedFrameData.getDice(i,j).toString()),i,j);

                }

            }

        }
    }
}
