import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private int x, y;
    private final static int width = 16, height = 16;
    private TileType tileType;
    private Image[] tileImage;
    public boolean onTop;

    public int actImage, actC, recool = 40;

    public static Image WALL_IMAGE, WALL_FREZED, GROUND_IMAGE, WOOD_IMAGE, FLOOR_IMAGE, CARPET_IMAGE, LAVA_IMAGE, SUN_IMAGE1, SUN_IMAGE2, SUN_IMAGE3, FIRE_IMAGE1, FIRE_IMAGE2, ICE_IMAGE, WATER_1, WATER_2, WATER_3, SANTAS_HOUSE, STOCK, BIG_HOUSE;
    public static BufferedImage TILE_SPRITESHEET;

    public Tile(TileType tileType, int x, int y, boolean onTop) {
        this.onTop = onTop;
        this.x = x * width;
        this.y = y * height;
        this.tileType = tileType;
        tileImage = new Image[1];
        switch (tileType) {
            case WALL:
                if (TileSystem.actLevel == "map3")
                    tileImage[0] = WOOD_IMAGE;
                else
                    tileImage[0] = WALL_IMAGE;
                if (onTop) {
                    this.onTop = false;
                    tileImage[0] = GROUND_IMAGE;
                }
                break;
            case WALL_FREZED:
                tileImage[0] = WALL_FREZED;
                if (onTop) {
                    this.onTop = false;
                    tileImage = new Image[3];
                    tileImage[0] = WATER_1;
                    tileImage[1] = WATER_2;
                    tileImage[2] = WATER_3;
                }
                break;
            case GROUND:
            case SHOW_HUD:
            case NEXT_LEVEL:
            case BOUNCE_GROUND:
                if (TileSystem.actLevel == "map3")
                    tileImage[0] = FLOOR_IMAGE;
                else
                    tileImage[0] = GROUND_IMAGE;
                break;
            case SUN:
                int r = SnowFilter.random.nextInt(3);
                if (r == 0) {
                    tileImage[0] = SUN_IMAGE1;
                } else if (r == 1) {
                    tileImage[0] = SUN_IMAGE2;
                } else {
                    tileImage[0] = SUN_IMAGE3;
                }
                break;
            case LAVA:
                tileImage[0] = LAVA_IMAGE;
                break;
            case ICE:
                if (TileSystem.actLevel == "map3")
                    tileImage[0] = CARPET_IMAGE;
                else
                    tileImage[0] = ICE_IMAGE;
                break;
            case WATER:
                tileImage = new Image[3];
                tileImage[0] = WATER_1;
                tileImage[1] = WATER_2;
                tileImage[2] = WATER_3;
                break;
            case SANTAS_HOUSE:
                tileImage[0] = SANTAS_HOUSE;
                break;
            case STOCK:
                tileImage[0] = STOCK;
                break;
            case BIG_HOUSE:
                tileImage[0] = BIG_HOUSE;
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

    public static int getHeight() {
        return height;
    }

    public Image[] getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image[] tileImage) {
        this.tileImage = tileImage;
    }
}
