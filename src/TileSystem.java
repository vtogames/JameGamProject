import yield.objects.YldObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TileSystem extends YldObject {

    public static Tile[] tiles;

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

        tiles = new Tile[map.getWidth() * map.getHeight()];

        map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

        for (int xx = 0; xx < map.getWidth(); xx++) {
            for (int yy = 0; yy < map.getHeight(); yy++) {
                int actPixel = pixels[xx + (yy * map.getWidth())];
                switch (actPixel) {
                    case 0xFF000000:

                        break;
                }

            }
        }
    }

}
