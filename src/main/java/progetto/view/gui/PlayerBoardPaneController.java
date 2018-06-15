package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.model.*;
import progetto.utils.IObserver;

public class PlayerBoardPaneController {

    private Container<DicePlacedFrameData> dicePlacedFrame;
    private AbstractPlayerBoard playerBoard;
    private WindowFrame windowFrame;

    @FXML
    private TilePane tilePane;

    @FXML
    private PickedDicesSlotPaneController pickedDicesSlotPaneController;

    private IObserver<DicePlacedFrameData> dicePlacedFrameDataIObserver =
            ogg -> Platform.runLater(() -> updateDicePlacedFrame(ogg));
    private IObserver<PlayerBoardData> playerBoardDataIObserver =
            ogg -> Platform.runLater(() -> updatePlayerBoard(ogg));

    public void setObservers(Container<DicePlacedFrameData> newDicePlacedFrame, AbstractPlayerBoard newPlayerBoard) {

        if (dicePlacedFrame != null) {
            dicePlacedFrame.removeObserver(dicePlacedFrameDataIObserver);
        }
        dicePlacedFrame = newDicePlacedFrame;
        dicePlacedFrame.addObserver(dicePlacedFrameDataIObserver);

        if (playerBoard != null) {
            playerBoard.removeObserver(playerBoardDataIObserver);
        }
        playerBoard = newPlayerBoard;
        playerBoard.addObserver(playerBoardDataIObserver);

        pickedDicesSlotPaneController.setObservable(playerBoard.getPickedDicesSlot());

    }

    private void updatePlayerBoard(PlayerBoardData playerBoardData) {

        WindowFrame newWindowFrame = playerBoardData.getWindowFrame();
        if (newWindowFrame == windowFrame || newWindowFrame == null) {
            return;
        }

        windowFrame = newWindowFrame;
        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        ImageView imageView;

        for (int i = 0; i < DicePlacedFrameData.MAX_NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; j++) {
                imageView = (ImageView) tilePane.getChildren()
                        .get(i*DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS +j);

                if (windowFrame.getColorBond(i, j) != null) {
                    imageView.setImage(textureDatabase.getDice(windowFrame.getColorBond(i, j), -1));
                } else if (windowFrame.getValueBond(i, j) != null) {
                    imageView.setImage(textureDatabase.getDice(null,
                            windowFrame.getValueBond(i, j).ordinal() + 1));
                }
            }
        }
    }

    private void updateDicePlacedFrame(DicePlacedFrameData dicePlacedFrameData){
        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        ImageView imageView;

        for (int i = 0; i < DicePlacedFrameData.MAX_NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; j++) {
                    if(dicePlacedFrameData.getDice(i,j)!=null){
                        imageView = (ImageView) tilePane.getChildren()
                                .get(i*DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS +j);
                        imageView.setImage(textureDatabase.getDice
                                (dicePlacedFrameData.getDice(i,j).getGameColor(),
                                        dicePlacedFrameData.getDice(i,j).getValue().ordinal()+1 ));
                }
            }
        }
    }
}