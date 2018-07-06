package progetto.view.commandline.printer;

import progetto.model.GameColor;
import progetto.model.Value;

/**
 * simple struct that holds the data of a dice
 */
class DiceCouple
{
    private Value v;
    private GameColor c;

    DiceCouple(Value v, GameColor c)
    {
        this.v = v;
        this.c = c;
    }

    /**
     *
     * @return the value
     */
    public Value getV() {
        return v;
    }

    /**
     *
     * @return the color
     */
    public GameColor getC() {
        return c;
    }
}
