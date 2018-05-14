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

    private DicePlacedFrameData dicePlacedFrameData;

    @Override
    protected void update() {

        DicePlacedFrameData newDicePlacedFrameData = getObservable().getDicePlacedFrameData();

        if(dicePlacedFrameData==newDicePlacedFrameData){

            return;

        }

        dicePlacedFrameData = newDicePlacedFrameData;

        number.setText(Integer.toString(dicePlacedFrameData.getNDices()));

        //gridPane.getChildren().clear();

        for(int i=0; i<3; i++){

            for (int j=0; j<4; j++){

                if(dicePlacedFrameData.getDice(i,j)!=null){

                    gridPane.add(new Label(dicePlacedFrameData.getDice(i,j).toString()),i,j);

                }

            }

        }
    }
}
