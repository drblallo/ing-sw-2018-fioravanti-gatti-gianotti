package progetto.view.commandline.printer;

/**
 * a drawable object is a object that contains chars as pixel that can be composed to create a image
 *
 */
public class Drawable {

    private final int height;
    private final int width;
    private Character[][] area;


    /**
     *
     * @param height the height of the image
     * @param width the width of the image
     */
    public Drawable(int height, int width) {
        this.height = height;
        this.width = width;
        area = new Character[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                area[i][j] = ' ';
            }
        }
    }

    /**
     *
     * @return the height of the image
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return the size of the image
     */
    public int getWidth() {
        return width;
    }

    /**
     * replace a pixel
     * @param x the x position of the pixel
     * @param y the y position of the pixel
     * @param c the char to be replaced
     */
    public void setPixel(int x, int y, char c) {
        area[y][x] = c;
    }

    /**
     *
     * @param x the x position of the pixel
     * @param y the y position of the pixel
     * @return the pixel in the provided position
     */
    public char getPixel(int x, int y) {
        return area[y][x];
    }

    /**
     * replaced the content of the target with the content of this object
     * @param target the object which image will be replaced
     * @param x the x starting position
     * @param y the y starting position
     */
    public void drawOnTo(Character[][] target, int x, int y)
    {
        for (int i = 0; i<Math.min(target[0].length, this.width); i++){
            for (int j = 0; j<Math.min(target.length, this.height); j++){
                target[j + y][i + x] = getPixel(i,j);
            }
        }
    }

    /**
     *
     * replaced the content of the target with the content of this object
     * @param target the object which image will be replaced
     * @param x the x starting position
     * @param y the y starting position
     */
    public void drawOnTo(Drawable target, int x, int y){
        drawOnTo(target.area, x, y);
    }

    /**
     *
     * @return the string to r
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();

        for (int b = 0; b < height; b++){
            for (int a = 0; a < width; a++)
                builder.append(getPixel(a, b));
            builder.append("\n");
        }
        return builder.toString();
    }
}
