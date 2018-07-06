package progetto.view.commandline.printer;

import progetto.model.GameColor;
import progetto.model.Value;

class DiceCouple
{
    private Value v;
    private GameColor c;

    DiceCouple(Value v, GameColor c)
    {
        this.v = v;
        this.c = c;
    }

    public Value getV() {
        return v;
    }

    public GameColor getC() {
        return c;
    }
}
