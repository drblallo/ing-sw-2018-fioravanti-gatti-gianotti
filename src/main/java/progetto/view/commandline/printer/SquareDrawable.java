package progetto.view.commandline.printer;

public class SquareDrawable extends Drawable {

    public SquareDrawable(int height, int width, char c)
    {
        super(height, width);
        for (int a = 0; a < height; a++)
            for (int b = 0; b < width; b++)
                setPixel(b, a, c);
    }
}
