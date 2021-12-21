import yield.YldGame;
import yield.components.YldComponent;
import yield.display.YldGraphical;

import java.awt.*;

public class TileDraw extends YldComponent implements YldGraphical {

    @Override
    public void draw(Graphics g) {
        if (TileSystem.tiles != null) {
            for (int x = 0; x < YldGame.getImage().getWidth() / Tile.getWidth() + 1; x++) {
                for (int y = 0; y < YldGame.getImage().getHeight() / Tile.getHeight(); y++) {
                    g.drawImage(Tile.LAVA_IMAGE, x * Tile.getWidth(), y * Tile.getHeight(), null);
                }
            }
            for (Tile tile : TileSystem.tiles) {

                if (tile != null) {
                    boolean canRender = true;

                    if (tile.getX() < -Camera.x - Tile.getWidth())
                        canRender = false;
                    if (tile.getY() < -Camera.y - Tile.getHeight())
                        canRender = false;
                    if (tile.getX() > -Camera.x + YldGame.getImage().getWidth())
                        canRender = false;
                    if (tile.getY() > -Camera.y + YldGame.getImage().getHeight())
                        canRender = false;

                    if (canRender)
                        g.drawImage(tile.getTileImage(), tile.getX() + (int) Camera.x, tile.getY() + (int) Camera.y, Tile.getWidth(), Tile.getHeight(), null);
                }


            }
        }
    }
}
