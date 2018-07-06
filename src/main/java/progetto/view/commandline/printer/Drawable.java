package progetto.view.commandline.printer;

public class Drawable {

    private final int height;
    private final int width;
    private Character[][] area;


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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setPixel(int x, int y, char c) {
        area[y][x] = c;
    }

    public char getPixel(int x, int y) {
        return area[y][x];
    }

    public void drawOnTo(Character[][] target, int x, int y)
    {
        for (int i = 0; i<Math.min(target[0].length, this.width); i++){
            for (int j = 0; j<Math.min(target.length, this.height); j++){
                target[j + y][i + x] = getPixel(i,j);
            }
        }
    }

    public void drawOnTo(Drawable target, int x, int y){
        drawOnTo(target.area, x, y);
    }

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
