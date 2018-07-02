package progetto.view.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import progetto.model.*;
import progetto.utils.IObserver;

import java.util.List;

public class ObjectiveCardsController extends AbstractController{

    @FXML
    private TilePane objectiveCardsTilePane;
    private int myOldChair = -2;
    private Container<PlayerBoardData> playerBoardDataContainer;
    private IObserver<PlayerBoardData> playerBoardDataIObserver = ogg -> Platform.runLater(this::update);
    private static final int BACK_PRIVATE_OBJECTIVE = 5;
    private static final int BACK_PUBLIC_OBJECTIVE = 10;

    @Override
    public void setUp(GUIView view){
        super.setUp(view);

        view.getController().getObservable().getMainBoard().addObserver(ogg -> update());
        view.getController().getRoomViewCallback().addObserver(ogg -> Platform.runLater(this::onRoomViewChanged));
    }

    private void update(){

        TextureDatabase textureDatabase = TextureDatabase.getTextureDatabase();
        IModel model = getController().getModel();
        int playerChair = Math.max(0, getController().getChair());

        updatePrivateObjective(model, playerChair, textureDatabase);
        updatePublicObjectives(model, textureDatabase);
    }

    private void updatePublicObjectives(IModel model, TextureDatabase textureDatabase){

        List<AbstractPublicObjectiveCard> abstractPublicObjectiveCards =
                model.getMainBoard().getData().getPublicObjectiveCards();

        int position = 1;
        int numberOfPublicObjectives = 3;
        if (model.getMainBoard().getData().getPlayerCount() == 1){
            position = 2;
            numberOfPublicObjectives = 2;
        }

        for (int i = 0; i<numberOfPublicObjectives; i++){
            ImageView imageView = (ImageView) objectiveCardsTilePane.getChildren().get(position);
            if (abstractPublicObjectiveCards.isEmpty()){
                if (imageView.getImage()!=textureDatabase.getPublicObjective(BACK_PUBLIC_OBJECTIVE))
                    imageView.setImage(textureDatabase.getPublicObjective(BACK_PUBLIC_OBJECTIVE));
            }
            else if (imageView.getImage() != textureDatabase.getPublicObjective(i))
                imageView.setImage(textureDatabase.getPublicObjective(abstractPublicObjectiveCards.get(i).getCardID()));
            position ++;
        }
    }

    private void updatePrivateObjective(IModel model, int playerChair, TextureDatabase textureDatabase){
        List<AbstractPrivateObjectiveCard> abstractPrivateObjectiveCardList
                = model.getPlayerBoard(playerChair).getData().getPrivateObjectiveCard();
            int numberOfPrivateObjectives = 1;
            if (model.getMainBoard().getData().getPlayerCount() == 1)
                numberOfPrivateObjectives = 2;

            for (int i = 0; i<numberOfPrivateObjectives; i++){
                ImageView imageView = ((ImageView) objectiveCardsTilePane.getChildren().get(i));
                if (abstractPrivateObjectiveCardList.isEmpty()){
                    if (imageView.getImage() != textureDatabase.getPrivateObjective(BACK_PRIVATE_OBJECTIVE))
                        imageView.setImage(textureDatabase.getPrivateObjective(BACK_PRIVATE_OBJECTIVE));}
                else {
                    if (imageView.getImage() != textureDatabase.getPrivateObjective
                            (abstractPrivateObjectiveCardList.get(i).getCardID()))
                        imageView.setImage(textureDatabase.getPrivateObjective
                                (abstractPrivateObjectiveCardList.get(i).getCardID()));
        }
            }
    }

    private void onRoomViewChanged(){
        int newChair = Math.max(getController().getChair(), 0);
        if (myOldChair != newChair)
        {
            myOldChair = newChair;
            if (playerBoardDataContainer != null)
                playerBoardDataContainer.removeObserver(playerBoardDataIObserver);
            playerBoardDataContainer = getController().getObservable().getPlayerBoard(newChair);
            playerBoardDataContainer.addObserver(playerBoardDataIObserver);
            update();
        }
    }
}
