import yield.YldGame;
import yield.YldTime;
import yield.components.YldComponent;
import yield.display.YldGraphical;

import java.awt.*;

public class TileDraw extends YldComponent implements YldGraphical {

    public static int cx, cy;

    @Override
    public void draw(Graphics g) {
        setLayer(5);
        cx = (int) Camera.x;
        cy = (int) Camera.y;
        if (TileSystem.tiles != null) {
            g.setColor(Color.white);
            g.fillRect(0, 0, YldGame.getImage().getWidth(), YldGame.getImage().getHeight());
            for (Tile tile : TileSystem.tiles) {

                if (tile != null) {
                    boolean canRender = true;

                    if (tile.getX() < - Camera.x - Tile.getWidth())
                        canRender = false;
                    if (tile.getY() < - Camera.y - Tile.getHeight())
                        canRender = false;
                    if (tile.getX() > - Camera.x + YldGame.getImage().getWidth())
                        canRender = false;
                    if (tile.getY() > - Camera.y + YldGame.getImage().getHeight())
                        canRender = false;

                    if (canRender)
                        g.drawImage(tile.getTileImage(), tile.getX() + cx, tile.getY() + cy, Tile.getWidth(), Tile.getHeight(), null);
                }


            }
        }
    }
}
