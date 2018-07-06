package progetto.view.commandline.printer;

/**
 * simple class to rappresent a block of pixel made of the same char
 */
public class SquareDrawable extends Drawable {

    /**
     *
     * @param height of the block
     * @param width of the block
     * @param c character contained by the block
     */
    public SquareDrawable(int height, int width, char c)
    {
        super(height, width);
        for (int a = 0; a < height; a++)
            for (int b = 0; b < width; b++)
                setPixel(b, a, c);
    }
}
