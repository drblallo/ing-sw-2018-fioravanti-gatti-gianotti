package progetto.view.commandline;

import progetto.model.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to print on the command line WindowFrames and PlayerBoards
 * @author Federica
 */
public class Printer {

    private static final Logger LOGGER = Logger.getLogger(Printer.class.getName());
    private static final int SQUARE_SIZE = 3;
    private static final int COLOR_SIZE = 6;
    private static final int WINDOW_HORIZONTAL_DIMENSION = 100;
    private static final int WINDOW_VERTICAL_DIMENSION = 33;
    private static final int FRAME_HORIZONTAL_DIMENSION = 40;
    private static final int FRAME_VERTICAL_DIMENSION = 28;
    private static final int FAVOUR_TOKENS_POSITION = 18;
    private static final int CELLS_HORIZONTAL_DIMENSION = 8;
    private static final int CELLS_VERTICAL_DIMENSION = 7;
    private static final int SECOND_FRAME_HORIZONTALE_POSITION = 50;
    private static final int WINDOWFRAME_VERTICAL_DISTANCE = 5;
    private static final int SECOND_WINDOWFRAME_VERTICAL_POSITION = 35;
    private static final int NUMBER_OF_WINDOW_FRAME_POSITION = 25;
    private static final int WINDOW_NAME_POSITION = 17;
    private static final int DICEPLACE_NAME_POSITION = 14;
    private static final int NUMBER_OF_VERTICAL_LINES = 6;
    private static final int NUMBER_OF_HORIZONTAL_LINES = 5;
    private static final int RADIX = 10;
    private static final int MAX_N_DICES_ROUNDTRACK = 9;
    private Character[][] window;
    private ArrayList<Character[][]> numbers;
    private ArrayList<Character[][]> colors;
    private int windowVertical;
    private int windowHorizontal;

    /**
     * Constructor
     */
    public Printer(){

        numbers = createNumbers();
        colors = createColors();

    }

    private void initializeWindow(){
        window = new Character[windowHorizontal][windowVertical];
        for (int i = 0; i < windowHorizontal; i++) {
            for (int j = 0; j < windowVertical; j++) {
                window[i][j] = ' ';
            }
        }
    }

    /**
     * Print a windowFrame or a PlayerBoard on the command line
     */
    private String print(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\n');
        for(int i=0; i<windowVertical; i++){
            for (int j=0; j<windowHorizontal; j++){
                stringBuilder.append(window[j][i]);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
    /**
     * Prepare a windowFrame to be printed on the command line
     * @param windowFrame windowFrame to prepare
     */
    private void prepareWindowFrame(WindowFrame windowFrame, int x, int y, boolean isSinglePlayer){
        if(windowFrame == null){
            LOGGER.log(Level.SEVERE, "Recived null windowFrame");
            return;
        }
        printName(true, x,y);
        if (!isSinglePlayer) {
            printTokens(x, y);
            window[x + FAVOUR_TOKENS_POSITION][y + WINDOW_VERTICAL_DIMENSION - 1] =
                    Character.forDigit(windowFrame.getFavorToken(), RADIX);
        }
        y = y + 2;
        printBox(x,y);

        int firstX;
        x++;
        firstX = x;
        y++;

        for(int i = 0; i<DicePlacedFrameData.MAX_NUMBER_OF_ROWS; i++){
            for(int j = 0; j<DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; j++){
                prepareDice(windowFrame.getValueBond(i,j), windowFrame.getColorBond(i,j), x,y);
                x = x + CELLS_HORIZONTAL_DIMENSION;
            }
            x = firstX;
            y = y + CELLS_VERTICAL_DIMENSION;
        }
    }

    /**
     * Prepare a dicePlacedFrameData to be printed on the command line
     * @param dicePlacedFrameData dicePlacedFrameData to prepare
     */
    private void prepareDicePlacedFrame(DicePlacedFrameData dicePlacedFrameData){

        int x = SECOND_FRAME_HORIZONTALE_POSITION;
        int y = 0;
        int firstX;
        printName(false, x,y);
        y = y +2;
        printBox(x,y);

        if (dicePlacedFrameData == null){
            LOGGER.log(Level.SEVERE, "Recived null dicePlacedFrameData");
            return;
        }

        x++;
        firstX = x;
        y++;

        for(int i = 0; i<DicePlacedFrameData.MAX_NUMBER_OF_ROWS; i++){
            for(int j = 0; j<DicePlacedFrameData.MAX_NUMBER_OF_COLUMNS; j++){
                if(dicePlacedFrameData.getDice(i,j)!=null)
                prepareDice(dicePlacedFrameData.getDice(i,j).getValue(),
                        dicePlacedFrameData.getDice(i,j).getGameColor(), x,y);
                x = x + CELLS_HORIZONTAL_DIMENSION;
            }
            x = firstX;
            y = y + CELLS_VERTICAL_DIMENSION;
        }

    }

    /**
     * Print a PlayerBoard to be printed on the command line
     * @param dicePlacedFrameData dicePlacedFrameData associated to the PlayerBoard
     * @param windowFrame windowFrame associated to the PlayerBoard
     */
    public String printPlayerBoard(DicePlacedFrameData dicePlacedFrameData,
                                   WindowFrame windowFrame, boolean isSinglePlayer){

        windowHorizontal = WINDOW_HORIZONTAL_DIMENSION;
        windowVertical = WINDOW_VERTICAL_DIMENSION;
        initializeWindow();

        prepareWindowFrame(windowFrame,0,0, isSinglePlayer);
        prepareDicePlacedFrame(dicePlacedFrameData);

        return print();

    }

    public String printWindowFrameChoices(PlayerBoardData playerBoardData, boolean isSinglePlayer){

        windowHorizontal = WINDOW_HORIZONTAL_DIMENSION;
        windowVertical = WINDOW_VERTICAL_DIMENSION*2 +WINDOWFRAME_VERTICAL_DISTANCE;
        initializeWindow();

        WindowFrameCouple[] windowFrameCouple = playerBoardData.getExtractedWindowFrameCouplesWindowFrame();

        prepareWindowFrame(windowFrameCouple[0].getWindowFrame(0),0,0, isSinglePlayer);
        prepareNumberOfWindowFrame(0,0,'1');
        prepareWindowFrame(windowFrameCouple[0].getWindowFrame(1), SECOND_FRAME_HORIZONTALE_POSITION,0, isSinglePlayer);
        prepareNumberOfWindowFrame(SECOND_FRAME_HORIZONTALE_POSITION,0,'2');
        prepareWindowFrame(windowFrameCouple[1].getWindowFrame(0),0,SECOND_WINDOWFRAME_VERTICAL_POSITION, isSinglePlayer);
        prepareNumberOfWindowFrame(0,SECOND_WINDOWFRAME_VERTICAL_POSITION,'3');
        prepareWindowFrame(windowFrameCouple[1].getWindowFrame(1),
                SECOND_FRAME_HORIZONTALE_POSITION,SECOND_WINDOWFRAME_VERTICAL_POSITION, isSinglePlayer);
        prepareNumberOfWindowFrame(SECOND_FRAME_HORIZONTALE_POSITION,SECOND_WINDOWFRAME_VERTICAL_POSITION,'4');


        return print();
    }

    public String printDices(ExtractedDicesData extractedDicesData){

        windowHorizontal = (CELLS_HORIZONTAL_DIMENSION +1)*extractedDicesData.getNumberOfDices();
        windowVertical = CELLS_VERTICAL_DIMENSION;
        initializeWindow();
        int x = 0;

        for(int i = 0; i< extractedDicesData.getNumberOfDices(); i++){
            prepareNumberOfDice(i,x,0);
            prepareDice(extractedDicesData.getDice(i).getValue(), extractedDicesData.getDice(i).getGameColor(),x,1);
            x = x + CELLS_HORIZONTAL_DIMENSION + 1;
        }
        return print();
    }

    public String printDices(PickedDicesSlotData pickedDicesSlotData){

        windowHorizontal = (CELLS_HORIZONTAL_DIMENSION +1)*pickedDicesSlotData.getNDices();
        windowVertical = CELLS_VERTICAL_DIMENSION;
        initializeWindow();
        int x = 0;

        for(int i = 0; i< pickedDicesSlotData.getNDices(); i++){
            prepareNumberOfDice(i,x,0);
            prepareDice(pickedDicesSlotData.getDicePlacementCondition(i).getDice().getValue(),
                    pickedDicesSlotData.getDicePlacementCondition(i).getDice().getGameColor(),x,1);
            x = x + CELLS_HORIZONTAL_DIMENSION +1;
        }
        return print();
    }

    public String printRoundTrack(RoundTrackData roundTrackData){

        int i = 0;
        while (!roundTrackData.isFree(i)){
            i++;
        }
        windowHorizontal = (CELLS_HORIZONTAL_DIMENSION + 1)*MAX_N_DICES_ROUNDTRACK;
        windowVertical = (CELLS_VERTICAL_DIMENSION + 3)*i;

        initializeWindow();
        int x;
        int y = 0;
        int k;

        for (int j = 0; j<i; j++){
            x=0;
            printNumberOfRound(j+1, x,y);
            y = y+2;
            k = 0;

            while (roundTrackData.getDice(j,k)!=null){
                prepareNumberOfDice(k,x,y);
                y = y+2;
                prepareDice(roundTrackData.getDice(j,k).getValue(),
                        roundTrackData.getDice(j,k).getGameColor(), x,y);
                x = x + CELLS_HORIZONTAL_DIMENSION;
                k++;
                y = y-2;
            }
            y = y + CELLS_VERTICAL_DIMENSION;
        }

        return print();
    }

    private void printNumberOfRound(int i, int x, int y){
        window[x][y] = 'R';
        x++;
        window[x][y] = 'o';
        x++;
        window[x][y] = 'u';
        x++;
        window[x][y] = 'n';
        x++;
        window[x][y] = 'd';
        x = x+2;
        window[x][y] = 'N';
        x++;
        window[x][y] = '°';
        x++;
        window[x][y] = Character.forDigit(i, RADIX);
    }

    private void prepareNumberOfWindowFrame(int x, int y, char numero){

        int k = NUMBER_OF_WINDOW_FRAME_POSITION;
        window[x+k][y] = 'N';
        k++;
        window[x+k][y] = '°';
        k++;
        window[x+k][y] = numero;
    }

    private void printTokens(int x, int y){

        y = y + WINDOW_VERTICAL_DIMENSION - 1;
        window[x][y] = 'N';
        x++;
        window[x][y] = 'u';
        x++;
        window[x][y] = 'm';
        x++;
        window[x][y] = 'e';
        x++;
        window[x][y] = 'r';
        x++;
        window[x][y] = 'o';
        x = x +2;
        window[x][y] = 'd';
        x++;
        window[x][y] = 'i';
        x = x+2;
        window[x][y] = 'g';
        x++;
        window[x][y] = 'e';
        x++;
        window[x][y] = 't';
        x++;
        window[x][y] = 't';
        x++;
        window[x][y] = 'o';
        x++;
        window[x][y] = 'n';
        x++;
        window[x][y] = 'i';
        x++;
        window[x][y] = ':';

    }

    private void printName(boolean isWindowFrame, int x, int y){

        if(isWindowFrame){
            int k = WINDOW_NAME_POSITION;
            window[x + k][y] = 'V';
            k++;
            window[x + k][y] = 'E';
            k++;
            window[x + k][y] = 'T';
            k++;
            window[x + k][y] = 'R';
            k++;
            window[x + k][y] = 'A';
            k++;
            window[x + k][y] = 'T';
            k++;
            window[x + k][y] = 'A';

            return;
        }
            int j = DICEPLACE_NAME_POSITION;
            window[x+j][y] = 'D';
            j++;
            window[x+j][y] = 'A';
            j++;
            window[x+j][y] = 'D';
            j++;
            window[x+j][y] = 'I';
            j = j+2;
            window[x+j][y] = 'P';
            j++;
            window[x+j][y] = 'I';
            j++;
            window[x+j][y] = 'A';
            j++;
            window[x+j][y] = 'Z';
            j++;
            window[x+j][y] = 'Z';
            j++;
            window[x+j][y] = 'A';
            j++;
            window[x+j][y] = 'T';
            j++;
            window[x+j][y] = 'I';

    }

    private void printBox(int x, int y){

        printFrameHorizontalLine(x,y);
        printFrameVerticalLine(x,y);

    }

    private void printFrameVerticalLine(int x, int y){
        y++;
        int firstY = y;
        for(int j = 0; j<NUMBER_OF_VERTICAL_LINES; j++) {
            for (int i = 0; i < FRAME_VERTICAL_DIMENSION-1; i++) {
                window[x][y] = '|';
                y++;
            }
            x = x + CELLS_HORIZONTAL_DIMENSION;
            y = firstY;
        }
    }

    private void printFrameHorizontalLine(int x, int y){
        x++;
        int firstX = x;
        for(int j=0; j<NUMBER_OF_HORIZONTAL_LINES;j++) {
            for (int i = 0; i < FRAME_HORIZONTAL_DIMENSION-1; i++) {
                window[x][y] = '-';
                x++;
            }
            y = y + CELLS_VERTICAL_DIMENSION;
            x = firstX;
        }
    }

    private void prepareDice(Value value, GameColor color, int x, int y){

        if(value!=null){
            printDiceBox(x,y);
            printNumber(value.ordinal()+1, x,y);
        }
        if(color!=null)
            printColor(color, x, y);
    }

    private void prepareNumberOfDice(int number, int x, int y){

        window[x][y] = 'D';
        x++;
        window[x][y] = 'a';
        x++;
        window[x][y] = 'd';
        x++;
        window[x][y] = 'o';
        x++;
        window[x][y] = 'N';
        x++;
        window[x][y] = '°';
        x++;
        window[x][y] = Character.forDigit(number, RADIX);



    }
    private void printDiceBox(int x, int y){

        printDiceHorizontalLine(x,y);
        printDiceHorizontalLine(x, y+ SQUARE_SIZE +1);
        printDiceVerticalLine(x,y);
        printDiceVerticalLine(x+ SQUARE_SIZE +1, y);

    }

    private void printDiceHorizontalLine(int x, int y){
        x++;
        for(int i = 0; i< SQUARE_SIZE; i++){
            window[x][y] = '-';
            x++;
        }
    }

    private void printDiceVerticalLine(int x, int y){
        y++;
        for(int i = 0; i< SQUARE_SIZE; i++){
            window[x][y] = '|';
            y++;
        }
    }

    private void printNumber(int number, int x, int y){

        int firstY;
        Character[][] numberToPrint = numbers.get(number-1);
        x++;
        y++;
        firstY = y;
        for(int i = 0; i< SQUARE_SIZE; i++){
            y=firstY;
            for(int j = 0; j< SQUARE_SIZE; j++){
                window[x][y] = numberToPrint[i][j];
                y++;
            }
            x++;
        }

    }

    private void printColor(GameColor color, int x, int y){

        x = x+ COLOR_SIZE;
        Character[][] colorToPrint = colors.get(color.ordinal());
        for(int i = 0; i< COLOR_SIZE; i++){
            window[x][y] = colorToPrint[0][i];
            y++;
        }

    }

    private ArrayList<Character[][]> createNumbers(){

        ArrayList<Character[][]> newNumbers = new ArrayList<>();
        //Number 1
        Character[][] number = createNumber();
        number[1][1] = 'o';
        newNumbers.add(number);
        //Number 2
        number = createNumber();
        number[0][0] = 'o';
        number[2][2] = 'o';
        newNumbers.add(number);
        //Number 3
        number = createNumber();
        number[0][0] = 'o';
        number[1][1] = 'o';
        number[2][2] = 'o';
        newNumbers.add(number);
        //Number 4
        number = createNumber();
        number[0][0] = 'o';
        number[0][2] = 'o';
        number[2][0] = 'o';
        number[2][2] = 'o';
        newNumbers.add(number);
        //Number 5
        number = createNumber();
        number[0][0] = 'o';
        number[0][2] = 'o';
        number[2][0] = 'o';
        number[2][2] = 'o';
        number[1][1] = 'o';
        newNumbers.add(number);
        //Number 6
        number = createNumber();
        number[0][0] = 'o';
        number[0][1] = 'o';
        number[0][2] = 'o';
        number[2][0] = 'o';
        number[2][1] = 'o';
        number[2][2] = 'o';
        newNumbers.add(number);

        return newNumbers;

    }

    private static ArrayList<Character[][]> createColors(){

        ArrayList<Character[][]> newColors = new ArrayList<>();
        //Yellow
        Character[][] color = new Character[1][COLOR_SIZE];
        int i = 0;
        color[0][i] = 'G';
        i++;
        color[0][i] = 'I';
        i++;
        color[0][i] = 'A';
        i++;
        color[0][i] = 'L';
        i++;
        color[0][i] = 'L';
        i++;
        color[0][i] = 'O';
        newColors.add(color);
        //Red
        color = new Character[1][COLOR_SIZE];
        i=0;
        color[0][i] = 'R';
        i++;
        color[0][i] = 'O';
        i++;
        color[0][i] = 'S';
        i++;
        color[0][i] = 'S';
        i++;
        color[0][i] = 'O';
        i++;
        color[0][i] = ' ';
        newColors.add(color);
        //Blue
        color = new Character[1][COLOR_SIZE];
        i=0;
        color[0][i] = ' ';
        i++;
        color[0][i] = 'B';
        i++;
        color[0][i] = 'L';
        i++;
        color[0][i] = 'U';
        i++;
        color[0][i] = ' ';
        i++;
        color[0][i] = ' ';
        newColors.add(color);
        //Green
        color = new Character[1][COLOR_SIZE];
        i=0;
        color[0][i] = 'V';
        i++;
        color[0][i] = 'E';
        i++;
        color[0][i] = 'R';
        i++;
        color[0][i] = 'D';
        i++;
        color[0][i] = 'E';
        i++;
        color[0][i] = ' ';
        newColors.add(color);
        //Purple
        color = new Character[1][COLOR_SIZE];
        i=0;
        color[0][i] = 'V';
        i++;
        color[0][i] = 'I';
        i++;
        color[0][i] = 'O';
        i++;
        color[0][i] = 'L';
        i++;
        color[0][i] = 'A';
        i++;
        color[0][i] = ' ';
        newColors.add(color);

        return newColors;
    }

    private static Character[][] createNumber(){
        Character[][] num = new Character[SQUARE_SIZE][SQUARE_SIZE];
        for(int x = 0; x< SQUARE_SIZE; x++){
            for (int y = 0; y< SQUARE_SIZE; y++){
                num[x][y] = ' ';
            }
        }
        return num;
    }

}
