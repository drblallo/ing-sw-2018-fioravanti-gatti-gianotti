package progetto.view.commandline.printer;

import progetto.model.Dice;
import progetto.model.GameColor;
import progetto.model.Value;

import java.util.ArrayList;
import java.util.List;

public class DiceDrawable extends Drawable {

    private static final int DICE_EMPTY_COLUMN_SIZE = 1;
    private final Drawable color;
    private final Drawable value;

    public DiceDrawable(GameColor gameColor, Value val){
        super(
                DiceColorDrawable.getColor(gameColor).getHeight(),
                DiceColorDrawable.getColor(gameColor).getWidth() +
                DiceNumberDrawable.getDice(val).getWidth() +
                DICE_EMPTY_COLUMN_SIZE
        );

        color = DiceColorDrawable.getColor(gameColor);
        value = DiceNumberDrawable.getDice(val);

        value.drawOnTo(this, 0, 0);
        color.drawOnTo(this, value.getWidth() + DICE_EMPTY_COLUMN_SIZE, 0);
    }

    public static void main(String[] args)
    {
        for (Value v : Value.values())
            for (GameColor col : GameColor.values())
            System.out.println(new DiceDrawable(col, v).toString());
    }
}
