package progetto.view.commandline.printer;

import progetto.model.GameColor;

import java.util.EnumMap;
import java.util.Map;

public class DiceColorDrawable extends Drawable{

    private static final int NAME_HEIGHT = 6;
    private static final int NAME_WIDTH = 1;

    private static final Map<GameColor, Drawable> drawable = getValues();
    private static final Drawable empty = new DiceColorDrawable("");

    public DiceColorDrawable(String text) {
        super(NAME_HEIGHT, NAME_WIDTH);
        for (int a = 0; a < text.length(); a++)
            setPixel(0, a , text.charAt(a));
    }

    private static Map<GameColor, Drawable> getValues()
    {
        Map<GameColor, Drawable> toReturn = new EnumMap<>(GameColor.class);

        toReturn.put(GameColor.YELLOW,new DiceColorDrawable( "GIALLO"));
        toReturn.put(GameColor.PURPLE, new DiceColorDrawable("VIOLA"));
        toReturn.put(GameColor.RED, new DiceColorDrawable("ROSSO"));
        toReturn.put(GameColor.GREEN, new DiceColorDrawable("VERDE"));
        toReturn.put(GameColor.BLUE, new DiceColorDrawable(" BLU"));

        return toReturn;
    }

    public static Drawable getColor(GameColor color)
    {
        if (color == null)
            return empty;

        return drawable.get(color);
    }

}
