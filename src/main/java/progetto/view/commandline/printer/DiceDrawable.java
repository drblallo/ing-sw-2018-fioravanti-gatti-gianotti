package progetto.view.commandline.printer;

import progetto.model.GameColor;
import progetto.model.Value;

/**
 * drawable rappresenting a dice
 */
public class DiceDrawable extends Drawable {

    private static final int DICE_EMPTY_COLUMN_SIZE = 1;
    private final Drawable color;
    private final Drawable value;

    /**
     *
     * @param gameColor the color of the dice
     * @param val the val of the dice
     */
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

}
