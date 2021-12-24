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

    public static Image HAT_IMAGE, SNOW_BALL_IMAGE, HEART_IMAGE, KEY_IMAGE, TOY[] = new Image[10], GIFT_IMAGES[] = new Image[5];
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
            case KEY:
                itemImage[0] = KEY_IMAGE;
                break;
            case TOY:
                int n = SnowFilter.random.nextInt(TOY.length - 1);
                itemImage[0] = TOY[n];
                break;
            case GIFT:
                int n2 = SnowFilter.random.nextInt(GIFT_IMAGES.length - 1);
                itemImage[0] = GIFT_IMAGES[n2];
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
