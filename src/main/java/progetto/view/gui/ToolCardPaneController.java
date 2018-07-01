package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.controller.UseToolCardAction;
import progetto.model.IModel;
import progetto.model.PreGameState;
import progetto.model.ToolCard;

import java.util.List;

public class ToolCardPaneController {

    @FXML
    private TilePane toolCardTilePane;
    @FXML
    private ChangeDiceValuePaneController changeDiceValuePaneController;
    private static final int MAX_NUMBER_OF_TOOL_CARDS = 3;
    private static final int BACK_TOOL_CARD = 13;
    private GUIView view;

    public void setup(GUIView view){
        this.view = view;
        changeDiceValuePaneController.setup(view);
        for (int i = 0; i<MAX_NUMBER_OF_TOOL_CARDS; i++){
            ImageView imageView = (ImageView) toolCardTilePane.getChildren().get(i);
            final int j = i;
            imageView.setOnMouseClicked(event -> {
                UseToolCardAction useToolCardAction = new UseToolCardAction(
                        view.getController().getChair(), j);
                if (useToolCardAction.canBeExecuted(view.getController().getModel()))
                    view.getController().sendAction(useToolCardAction);
            });
        }
        view.getController().getObservable().getMainBoard().addObserver(ogg -> Platform.runLater(this::update));
    }

    private void update(){
        IModel model = view.getController().getModel();
        List<ToolCard> toolCardList = model.getMainBoard().getData().getToolCards();
        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        ImageView imageView;
        if(model.getMainBoard().getData().getGameState().getClass() ==
                PreGameState.class){
            for (int i = 0; i<MAX_NUMBER_OF_TOOL_CARDS; i++){
                imageView = (ImageView) toolCardTilePane.getChildren().get(i);
                if (imageView.getImage()!=textureDatabase.getToolCard(BACK_TOOL_CARD))
                    imageView.setImage(textureDatabase.getToolCard(BACK_TOOL_CARD));
            }
        }
        else {
            for (int i = 0; i < MAX_NUMBER_OF_TOOL_CARDS; i++) {
                imageView = (ImageView) toolCardTilePane.getChildren().get(i);
                if (imageView.getImage() != textureDatabase.getToolCard(toolCardList.get(i).getIndex()))
                    imageView.setImage(textureDatabase.getToolCard(toolCardList.get(i).getIndex()));
            }
        }
    }
}
