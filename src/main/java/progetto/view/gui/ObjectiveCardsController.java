package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.model.*;
import progetto.utils.IObserver;

import java.util.List;

public class ObjectiveCardsController {

    @FXML
    private TilePane objectiveCardsTilePane;
    private GUIView view;
    private int myOldChair = -2;
    private Container<PlayerBoardData> playerBoardDataContainer;
    private IObserver<PlayerBoardData> playerBoardDataIObserver = ogg -> Platform.runLater(this::update);
    private static final int BACK_PRIVATE_OBJECTIVE = 5;
    private static final int BACK_PUBLIC_OBJECTIVE = 10;
    private static final int NUMBER_OF_PUBLIC_OBJECTIVES = 3;

    public void setup(GUIView view){

        this.view = view;

        view.getController().getObservable().getMainBoard().addObserver(ogg -> update());
        view.getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(this::onRoomViewChanged));
    }

    private void update(){

        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        IModel model = view.getController().getModel();
        int playerChair = Math.max(0, view.getController().getChair());

        updatePrivateObjective(model, playerChair, textureDatabase);
        updatePublicObjectives(model, textureDatabase);
    }

    private void updatePublicObjectives(IModel model, TextureDatabase textureDatabase){

        List<AbstractPublicObjectiveCard> abstractPublicObjectiveCards =
                model.getMainBoard().getData().getPublicObjectiveCards();

        ImageView imageView;
        if (abstractPublicObjectiveCards.isEmpty()){
            for (int i = 1; i<NUMBER_OF_PUBLIC_OBJECTIVES + 1;i++){
                imageView = (ImageView) objectiveCardsTilePane.getChildren().get(i);
                if (imageView.getImage()!=textureDatabase.getPublicObjective(BACK_PUBLIC_OBJECTIVE))
                    imageView.setImage(textureDatabase.getPublicObjective(BACK_PUBLIC_OBJECTIVE));
            }
        }
        else {
            for (int i = 1; i < NUMBER_OF_PUBLIC_OBJECTIVES + 1; i++) {
                imageView = (ImageView) objectiveCardsTilePane.getChildren().get(i);
                AbstractPublicObjectiveCard abstractPublicObjectiveCard = abstractPublicObjectiveCards.get(i - 1);
                if (imageView.getImage() != textureDatabase.getPublicObjective(abstractPublicObjectiveCard.getCardID()))
                    imageView.setImage(textureDatabase.getPublicObjective(abstractPublicObjectiveCard.getCardID()));
            }
        }
    }

    private void updatePrivateObjective(IModel model, int playerChair, TextureDatabase textureDatabase){
        List<AbstractPrivateObjectiveCard> abstractPrivateObjectiveCardList
                = model.getPlayerBoard(playerChair).getData().getPrivateObjectiveCard();
        ImageView imageView = ((ImageView)objectiveCardsTilePane.getChildren().get(0));
        if (abstractPrivateObjectiveCardList.isEmpty()){
            if (imageView.getImage()!=textureDatabase.getPrivateObjective(BACK_PRIVATE_OBJECTIVE))
                imageView.setImage(textureDatabase.getPrivateObjective(BACK_PRIVATE_OBJECTIVE));
        }
        else if (imageView.getImage() != textureDatabase.getPrivateObjective
                (abstractPrivateObjectiveCardList.get(0).getCardID())){
            imageView.setImage(textureDatabase.getPrivateObjective
                    (abstractPrivateObjectiveCardList.get(0).getCardID()));
        }
    }

    private void onRoomViewChanged(){
        int newChair = Math.max(view.getController().getChair(), 0);
        if (myOldChair != newChair)
        {
            myOldChair = newChair;
            if (playerBoardDataContainer != null)
                playerBoardDataContainer.removeObserver(playerBoardDataIObserver);
            playerBoardDataContainer = view.getController().getObservable().getPlayerBoard(newChair);
            playerBoardDataContainer.addObserver(playerBoardDataIObserver);
            update();
        }
    }
}
