import yield.YldGame;
import yield.display.Vector;
import yield.objects.YldObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

public class TileSystem extends YldObject {

    public static Tile[] tiles;
    public static HashSet<NPC> npcs = new HashSet<>();

    @Override
    public void create() {
        super.create();
        add(new TileDraw());
        setLayer(5);
    }

    public static void tilesFromImagePath(String name) {

        npcs.clear();

        BufferedImage map = null;
        try {
            map = ImageIO.read(Objects.requireNonNull(TileSystem.class.getResource("/" + name + ".png")));
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
                        Player.life = 100;
                        break;
                    case 0xFFFFFFFF:
                        tiles[actTile] = new Tile(TileType.WALL, xx, yy);
                        break;
                    case 0xFFEFFF14:
                        tiles[actTile] = new Tile(TileType.SUN, xx, yy);
                        break;
                    case 0xFFFF0000:
                        tiles[actTile] = new Tile(TileType.LAVA, xx, yy);
                        break;
                }
                if (Objects.equals(name, "testmap3")) {
                    switch (actPixel) {
                        case 0xFFFF7130:
                            npcs.add(new NPC(NPCType.one, xx, yy));
                            break;
                    }
                }
                if (tiles[actTile] == null)
                    tiles[actTile] = new Tile(TileType.GROUND, xx, yy);

            }
        }
    }

}
