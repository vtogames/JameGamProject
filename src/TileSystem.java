import yield.YldGame;
import yield.display.Vector;
import yield.objects.YldObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TileSystem extends YldObject {

    public static Tile[] tiles;

    @Override
    public void create() {
        super.create();
        add(new TileDraw());
    }

    public void tilesFromImagePath(String path) {
        BufferedImage map = null;
        try {
            map = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert map != null;
        int[] pixels = new int[map.getWidth() * map.getHeight()];

        int WIDTH = map.getWidth();
        int HEIGHT = map.getHeight();

        tiles = new Tile[WIDTH * HEIGHT];

        pixels = map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);

        for (int xx = 0; xx < WIDTH; xx++) {
            for (int yy = 0; yy < HEIGHT; yy++) {
                int actPixel = pixels[xx + (yy * WIDTH)];
                int actTile = xx + (yy * WIDTH);
                switch (actPixel) {
                    case 0xFF00FFFF:
                        Player.spawnX = xx * Tile.getWidth();
                        Player.spawnY = yy * Tile.getHeight();
                        Player.goToSpawn = true;
                        break;
                    case 0xFFFFFFFF:
                        tiles[actTile] = new Tile(TileType.WALL, xx, yy);
                        break;

                }
                if (tiles[actTile] == null)
                    tiles[actTile] = new Tile(TileType.GROUND, xx, yy);

            }
        }
    }

}
