package progetto.view.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import progetto.IClientController;
import progetto.controller.FrameSetAction;
import progetto.model.DicePlacedFrameData;
import progetto.model.MainBoardData;
import progetto.model.PlayerBoardData;
import progetto.model.WindowFrame;

public class ChooseWindowFramePaneController {

    @FXML
    private TilePane mainPane;
    private GUIView view;


    public void setup(PlayerBoardData playerBoardData, IClientController clientController, GUIView view) {

        this.view = view;

        Image image = new Image(GamePaneController.class.getResourceAsStream("background.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(Control.USE_COMPUTED_SIZE,Control.USE_COMPUTED_SIZE,
                true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        mainPane.setBackground(background);

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
        Label favourPointsLabel = (Label) hBox.getChildren().get(1);
        Label numberOfTokens = (Label) hBox.getChildren().get(2);

        if (view.getController().getModel().getMainBoard().getData().getPlayerCount() != 1){
            favourPointsLabel.setText("Punti favore: ");
            numberOfTokens.setText(windowFrame.getFavorToken() + "");
        }
        else {
            favourPointsLabel.setText("");
            numberOfTokens.setText("");
        }
    }
}
