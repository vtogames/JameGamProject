public class Tile {

    private int x, y;
    private static int width = 16, height = 16;
    private TileType tileType;

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
}
