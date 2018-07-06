package progetto.view.commandline.printer;

import progetto.model.*;

import java.util.ArrayList;
import java.util.List;

public class DrawableUtils{

    private static final int HORIZONTAL_SIZE = 35;
    private static final int VERTICAL_SIZE = 24;
    private static final int VERTICAL_SPACING = 2;
    private static final int HORIZONTAL_SPACING = 2;


    private static Drawable getWindowFrame(WindowFrame f) {
        DiceCouple[][] dice = new DiceCouple[WindowFrame.MAX_NUMBER_OF_ROWS][WindowFrame.MAX_NUMBER_OF_COLUMNS];

        for (int a = 0; a < WindowFrame.MAX_NUMBER_OF_ROWS; a++)
            for (int b = 0; b < WindowFrame.MAX_NUMBER_OF_COLUMNS; b++)
                dice[a][b] = new DiceCouple(f.getValueBond(a, b), f.getColorBond(a, b));

        return getFromMatrix(dice);
    }

    public static Drawable getSourronded(Drawable d)
    {
        Drawable ret = new Drawable(d.getHeight() + 2, d.getWidth() + 2);

        d.drawOnTo(ret, 1, 1);

        SquareDrawable vert = new SquareDrawable(d.getHeight(), 1, '|');
        SquareDrawable horz = new SquareDrawable(1, d.getWidth(), '-');

        vert.drawOnTo(ret, 0, 1);
        vert.drawOnTo(ret, ret.getWidth()-1, 1);
        horz.drawOnTo(ret, 1, 0);
        horz.drawOnTo(ret, 1, ret.getHeight()-1);

        return ret;
    }

    public static Drawable getFromMatrix(DiceCouple[][] dices)
    {
        Drawable d = new DiceDrawable(null, null);
        d = getSourronded(d);
        Drawable toReturn = new Drawable(d.getHeight() *dices.length, d.getWidth() *dices[0].length);

        for (int a = 0; a < dices.length; a++)
        {
            for (int b = 0; b < dices[0].length; b++)
            {
                DiceCouple dice = dices[a][b];
                Drawable val;
                if (dice != null)
                    val = new DiceDrawable(dice.getC(), dice.getV());
                else
                    val = new DiceDrawable(null, null);

                val = getSourronded(val);
                val.drawOnTo(toReturn, (d.getWidth() -1) * b, (d.getHeight()-1) * a);

            }
        }


        return toReturn;

    }


    public static Drawable getPlacedDice(DicePlacedFrameData data)
    {
        DiceCouple[][] dice = new DiceCouple[WindowFrame.MAX_NUMBER_OF_ROWS][WindowFrame.MAX_NUMBER_OF_COLUMNS];

        for (int a = 0; a < WindowFrame.MAX_NUMBER_OF_ROWS; a++)
            for (int b = 0; b < WindowFrame.MAX_NUMBER_OF_COLUMNS; b++)
                if (data.getDice(a, b) != null)
                    dice[a][b] = new DiceCouple(data.getDice(a, b).getValue(), data.getDice(a, b).getGameColor());

        return getFromMatrix(dice);
    }

    public static Drawable getPlayerBoard(IPlayerBoard data, boolean isSinglePlayer)
    {
        Drawable frame = getAddTextOver(getWindowFrame(data.getData().getWindowFrame()), "Finestra");
        Drawable placed =  getAddTextOver(getPlacedDice(data.getDicePlacedFrame().getData()), "Dadi piazzati");

        Drawable w = new Drawable(frame.getHeight(),
                frame.getWidth() + placed.getWidth() + HORIZONTAL_SPACING);

        frame.drawOnTo(w, 0, 0);
        placed.drawOnTo(w, frame.getWidth()+ HORIZONTAL_SPACING, 0);

        if (!isSinglePlayer)
            return getAddTextOver(w, "Punti favore "+ data.getData().getToken());
        else
            return w;
    }

    private static String getWindowFrameDescription(boolean isSinglePlayer, WindowFrame windowFrame, int num)
    {
        if (!isSinglePlayer)
            return "Finestra " + num + " Punti Favore: "+windowFrame.getFavorToken();
        else
            return "Finestra " + num;
    }

    public static Drawable getChooseWindowFrame(PlayerBoardData plb, boolean isSinglePlayer)
    {
        List<Drawable> d = new ArrayList<>();
        int num = 1;
        for (WindowFrameCouple w : plb.getExtractedWindowFrameCouplesWindowFrame()) {
            for (int a = 0; a < 2; a++) {
                WindowFrame frame = w.getWindowFrame(0);
                d.add(getAddTextOver(getWindowFrame(frame), getWindowFrameDescription(isSinglePlayer, frame, num++)));
            }
        }

        Drawable toReturn = new Drawable(d.get(0).getHeight() * 2 + (VERTICAL_SPACING),
                d.get(0).getWidth() * 2  + (2*HORIZONTAL_SPACING));

        for (int a = 0; a < 2; a++)
        {
            for (int b = 0; b < 2; b++)
            {
                Drawable dice = d.get(a * 2 + b);
                dice.drawOnTo(toReturn, b * (HORIZONTAL_SPACING + dice.getWidth()),
                        a * (VERTICAL_SPACING + dice.getHeight()));

            }
        }

        return toReturn;
    }

    public static Drawable getAddTextOver(Drawable d, String s)
    {
        Drawable toReturn = new Drawable(d.getHeight() + 1, d.getWidth());
        d.drawOnTo(toReturn, 0, 1);
        for (int a = 0; a < s.length(); a++)
            toReturn.setPixel(a, 0, s.charAt(a));
        return toReturn;
    }

    public static Drawable getPicked(PickedDicesSlotData data)
    {
        List<Dice> d = new ArrayList<>();
        for (int a = 0; a < data.getNDices(); a++)
            d.add(data.getDicePlacementCondition(a).getDice());

        return getFromBlock(d);
    }

    private static Drawable getFromBlock(List<Dice> dices)
    {
        Drawable t = getAddTextOver(new DiceDrawable(null, null), "");
        Drawable d = new Drawable(t.getHeight(), (t.getWidth() + HORIZONTAL_SPACING) * dices.size());

        for (int a = 0; a < dices.size(); a++)
        {
            Dice dice = dices.get(a);
            Drawable drawable = new DiceDrawable(null, null);
            if (dice != null)
                drawable = new DiceDrawable(dice.getGameColor(), dice.getValue());

            drawable = getAddTextOver(drawable, a +"");
            drawable.drawOnTo(d, a * (t.getWidth() + HORIZONTAL_SPACING), 0);
        }

        return d;

    }

    public static Drawable getExtracted(ExtractedDicesData data)
    {
        List<Dice> d = new ArrayList<>();
        for (int a = 0; a < data.getNumberOfDices(); a++)
            d.add(data.getDice(a));

        return getFromBlock(d);
    }

    public static Drawable getRoundTrackTurn(RoundTrackData t, int turn)
    {
        int a = 0;
        List<Drawable> d = new ArrayList<>();
        Dice b;
        while ((b = t.getDice(turn, a)) != null)
        {
            Drawable dice = new DiceDrawable(b.getGameColor(), b.getValue());
            d.add(getAddTextOver(dice, "dado:"+a));
            a++;
        }

        Drawable toReturn = new Drawable(d.get(0).getHeight(), d.get(0).getWidth() * d.size());

        for (int n = 0; n < d.size(); n++)
            d.get(n).drawOnTo(toReturn, d.get(n).getHeight() * n,0);
        return toReturn;
    }

    public static Drawable getRoundTrack(RoundTrackData t)
    {
        int a = 0;
        List<Drawable> d = new ArrayList<>();
        while (!t.isFree(a))
        {
            d.add(getAddTextOver(getRoundTrackTurn(t, a),"round: "+a));
            a++;
        }

        Drawable toRet = new Drawable(d.get(0).getHeight() * d.size(),d.get(0).getWidth());
        for (int  y = 0; y < d.size(); y++)
            d.get(y).drawOnTo(toRet, 0, d.get(y).getHeight() * y);

        return toRet;
    }
}
