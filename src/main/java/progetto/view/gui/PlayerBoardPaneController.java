package progetto.view.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.model.*;

public class PlayerBoardPaneController {

    private WindowFrame windowFrame;

    @FXML
    private TilePane tilePane;

    @FXML
    private PickedDicesSlotPaneController pickedDicesSlotPaneController;

    private ComposableController<DicePlacedFrameData, Container<DicePlacedFrameData>>
            dicePlacedFrame = new ComposableController<>();
    private ComposableController<PlayerBoardData, AbstractPlayerBoard> playerBoard = new ComposableController<>();

    public PlayerBoardPaneController(){
        dicePlacedFrame.getOnModifiedCallback().addObserver(this::updateDicePlacedFrame);
        playerBoard.getOnModifiedCallback().addObserver(this::updatePlayerBoard);
    }

    public void setObservers(ObservableModel model, int nPlayerBoard) {

        playerBoard.setObservable(model.getPlayerBoard(nPlayerBoard));
        dicePlacedFrame.setObservable(model.getPlayerBoard(nPlayerBoard).getDicePlacedFrame());

        pickedDicesSlotPaneController.setObservable(model.getPlayerBoard(nPlayerBoard).getPickedDicesSlot());

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