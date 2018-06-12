package progetto.view.gui;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import progetto.model.GameColor;

import java.util.*;

public final class TextureDatabase {

    private static TextureDatabase textureDatabase = new TextureDatabase();
    private static final String DIMENSION = "-64.png";
    private static final int MIN_DICE_NUMBER = 1;
    private static final int MAX_DICE_NUMBER = 6;
    private static final String PLAYERBOARD = "playerboard/";
    private static final double IMAGE_ADAPTER = 1.5;
    private Map<GameColor, Image> colors;
    private Map<GameColor, List<Image>> dices;
    private ArrayList<Image> numbers;
    private Image white;


    private TextureDatabase(){

        white = new Image(getClass().getResourceAsStream(PLAYERBOARD + "/WHITE" + DIMENSION));
        colors = new EnumMap<>(GameColor.class);
        loadColors();
        numbers = new ArrayList<>();
        loadNumbers();
        dices = new EnumMap<>(GameColor.class);
        loadDices();

    }

    public static TextureDatabase getTextureDatabase(){
        return textureDatabase;
    }

    public Image getDice(GameColor gameColor, int number){

        Image imageToBeReturned = null;

        if(number>MAX_DICE_NUMBER)
            throw new IllegalArgumentException();
        else if(gameColor == null && number<MIN_DICE_NUMBER)
            imageToBeReturned = white;
        else if(gameColor == null)
            imageToBeReturned = numbers.get(number-1);
        else if(number<MIN_DICE_NUMBER)
            imageToBeReturned = colors.get(gameColor);
        else imageToBeReturned = dices.get(gameColor).get(number-1);
        return imageToBeReturned;

    }

    private void loadColors(){
        for (GameColor gameColor : GameColor.values()) {
            colors.put(gameColor, new Image(getClass().getResourceAsStream(PLAYERBOARD + gameColor + DIMENSION)));
        }
    }

    private void loadNumbers(){
        for (int i = MIN_DICE_NUMBER; i<MAX_DICE_NUMBER+1; i++){
            numbers.add(new Image(getClass().getResourceAsStream(PLAYERBOARD + i + DIMENSION)));
        }
    }

    private void loadDices(){
        for (GameColor gameColor : GameColor.values()
             ) {
            ArrayList<Image> imageArrayList = new ArrayList<>();
            dices.put(gameColor, imageArrayList);
            for(int i = MIN_DICE_NUMBER; i<MAX_DICE_NUMBER+1; i++){
                imageArrayList.add(createDice(gameColor, i));
            }
        }
    }

    private Image createDice(GameColor gameColor, int number){

        Image numberImage = new Image(getClass().getResourceAsStream(PLAYERBOARD + number + DIMENSION));
        Image colorImage = new Image(getClass().getResourceAsStream(PLAYERBOARD+ gameColor + DIMENSION));
        Color diceBackground;
        Color diceColor;
        Color newColor;
        double sum;
        WritableImage writableImage = new WritableImage(((int) numberImage.getWidth()), (int) numberImage.getHeight());
        for (int i=0; i<numberImage.getWidth();i++){
            for (int j=0; j<numberImage.getHeight();j++){
                diceBackground = numberImage.getPixelReader().getColor(i,j);
                diceColor = colorImage.getPixelReader().getColor(i,j);
                sum = (diceBackground.getBlue() + diceBackground.getGreen() + diceBackground.getRed())/(IMAGE_ADAPTER);
                sum = Math.min(1,sum);
                newColor = Color.color(diceColor.getRed()*sum,
                        diceColor.getGreen()*sum, diceColor.getBlue()*sum);
                writableImage.getPixelWriter().setColor(i,j,newColor);
            }
        }

        return writableImage;
    }



}