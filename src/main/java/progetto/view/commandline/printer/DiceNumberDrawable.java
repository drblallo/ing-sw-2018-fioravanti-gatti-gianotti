package progetto.view.commandline.printer;

import progetto.model.Value;

import java.util.EnumMap;
import java.util.Map;

public class DiceNumberDrawable extends Drawable
{
    private static final int DICE_BORDER_SIZE = 1;
    private static final int DICE_NUMBER_CORE = 3;

    private static final int DICE_NUMBER_SIZE = DICE_NUMBER_CORE + (2*DICE_BORDER_SIZE);

    private static final SquareDrawable verticalLine = new SquareDrawable(DICE_NUMBER_CORE, DICE_BORDER_SIZE, '|');
    private static final SquareDrawable  horizontalLine = new SquareDrawable(DICE_BORDER_SIZE, DICE_NUMBER_CORE, '-');
    private static final Map<Value, Drawable> dicesNumber = getDicesNumber();
    private static final Drawable empty = new DiceNumberDrawable("         ");

    public DiceNumberDrawable(String s){
        super(DICE_NUMBER_SIZE, DICE_NUMBER_SIZE);
        verticalLine.drawOnTo(this, 0, DICE_BORDER_SIZE);
        verticalLine.drawOnTo(this, DICE_NUMBER_SIZE - 1, DICE_BORDER_SIZE);
        horizontalLine.drawOnTo(this, DICE_BORDER_SIZE, 0);
        horizontalLine.drawOnTo(this, DICE_BORDER_SIZE, DICE_NUMBER_SIZE - 1);

        int c = 0;
        for (int a = DICE_BORDER_SIZE; a < DICE_BORDER_SIZE + DICE_NUMBER_CORE; a++)
            for (int b = DICE_BORDER_SIZE; b < DICE_BORDER_SIZE + DICE_NUMBER_CORE; b++)
            {
                setPixel(a, b, s.charAt(c));
                c++;
            }
    }


    private static Map<Value, Drawable> getDicesNumber()
    {
        Map<Value, Drawable> toBeReturned = new EnumMap<>(Value.class);

        toBeReturned.put(Value.ONE, new DiceNumberDrawable("    o    "));
        toBeReturned.put(Value.TWO, new DiceNumberDrawable("o       o"));
        toBeReturned.put(Value.THREE, new DiceNumberDrawable("o   o   o"));
        toBeReturned.put(Value.FOUR, new DiceNumberDrawable("o o   o o"));
        toBeReturned.put(Value.FIVE, new DiceNumberDrawable("o o o o o"));
        toBeReturned.put(Value.SIX, new DiceNumberDrawable("ooo   ooo"));

        return toBeReturned;
    }

    public static Drawable getDice(Value value)
    {
        if (value == null)
            return empty;
        return dicesNumber.get(value);
    }


}
