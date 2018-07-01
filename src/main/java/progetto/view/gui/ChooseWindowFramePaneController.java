package progetto.view.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import progetto.IClientController;
import progetto.controller.FrameSetAction;
import progetto.model.DicePlacedFrameData;
import progetto.model.MainBoardData;
import progetto.model.PlayerBoardData;
import progetto.model.WindowFrame;

public class ChooseWindowFramePaneController {

    @FXML
    private TilePane mainPane;

    public void setup(PlayerBoardData playerBoardData, IClientController clientController) {
        for (int i = 0; i< MainBoardData.MAX_NUM_PLAYERS; i++){
            AnchorPane currentContainer = (AnchorPane) mainPane.getChildren().get(i);
            VBox currentWindow = (VBox) currentContainer.getChildren().get(0);
            showWindowFrame(currentWindow, playerBoardData.getExtractedWindowFrameCouplesWindowFrame()[i/2]
                    .getWindowFrame(i%2));
            final int j = i;
            currentWindow.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    clientController.sendAction(new FrameSetAction
                            (clientController.getChair(), j/2, j%2));
                    mainPane.getScene().getWindow().hide();
                }
            });
        }
    }

    private void showWindowFrame(VBox currentWindow, WindowFrame windowFrame) {
        TilePane currentWindowFrame = (TilePane) currentWindow.getChildren().get(0);
        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        ImageView imageView;
        for (int y = 0; y < DicePlacedFrameData.MAX_NUMBER_OF_ROWS; y++) {
            for (int x = 0; x < DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; x++) {
                imageView = (ImageView) currentWindowFrame.getChildren()
                        .get(y * DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS + x);
                if (windowFrame.getColorBond(y, x) != null) {
                    imageView.setImage(textureDatabase.getDice(windowFrame.getColorBond(y, x), -1));
                } else if (windowFrame.getValueBond(y, x) != null) {
                    imageView.setImage(textureDatabase.getDice(null,
                            windowFrame.getValueBond(y, x).ordinal() + 1));
                } else imageView.setImage(textureDatabase.getDice(null, -1));
            }
        }
        HBox hBox = (HBox) currentWindow.getChildren().get(1);
        Label numberOfTokens = (Label) hBox.getChildren().get(2);
        numberOfTokens.setText(windowFrame.getFavorToken() + "");
    }
}
