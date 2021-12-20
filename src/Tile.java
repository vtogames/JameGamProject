import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private int x, y;
    private static int width = 16, height = 16;
    private TileType tileType;
    private Image tileImage;

    public static Image WALL_IMAGE, GROUND_IMAGE;
    public static BufferedImage TILE_SPRITESHEET;

    public Tile(TileType tileType, int x, int y) {
        this.x = x * width;
        this.y = y * height;
        this.tileType = tileType;
        switch (tileType) {
            case WALL:
                tileImage = WALL_IMAGE;
                break;
            case GROUND:
                tileImage = GROUND_IMAGE;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Tile.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Tile.height = height;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }
}
