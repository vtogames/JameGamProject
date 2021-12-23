import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class Item {

    private int x, y;
    public ItemType type;
    public Image[] itemImage;
    public boolean canRender;
    public static HashSet<Item> items = new HashSet<>();

    public int actImage, actC, recool = 40;

    public static Image HAT_IMAGE, SNOW_BALL_IMAGE, HEART_IMAGE;
    public static BufferedImage ITEM_SPRITESHEET;

    public Item(ItemType type, int x, int y) {
        this.x = x * Tile.getWidth();
        this.y = y * Tile.getHeight();
        this.type = type;
        itemImage = new Image[1];
        switch (type) {
            case HAT:
                itemImage[0] = HAT_IMAGE;
                break;
            case SNOW_BALL:
                itemImage[0] = SNOW_BALL_IMAGE;
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

}
