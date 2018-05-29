package progetto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import progetto.game.DicePlacedFrameData;
import progetto.game.WindowFrame;

public class WindowFramePaneController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label favorToken;

    public void setup(WindowFrame windowFrame){

        favorToken.setText(Integer.toString(windowFrame.getFavorToken()));

        gridPane.getChildren().clear();

        for(int i = 0; i< DicePlacedFrameData.MAX_NUMBER_OF_ROWS; i++){

            for (int j=0; j<DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; j++){

                if(windowFrame.getColorBond(i,j)!=null){

                    gridPane.add(new Label(windowFrame.getColorBond(i,j).toString()),j,i);

                }

                else if(windowFrame.getValueBond(i,j)!=null){

                    gridPane.add(new Label(windowFrame.getValueBond(i,j).toString()), j,i);

                }

            }

        }

    }
}
