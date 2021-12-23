import yield.YldGame;
import yield.YldTime;
import yield.components.YldComponent;
import yield.display.YldGraphical;

import java.awt.*;

public class TileDraw extends YldComponent implements YldGraphical {

    public static int cx, cy;

    @Override
    public void update() {
        super.update();
        Item.items.forEach(item -> {
            boolean canRender = true;

            if (item.getX() < -Camera.x - Tile.getWidth())
                canRender = false;
            if (item.getY() < -Camera.y - Tile.getHeight())
                canRender = false;
            if (item.getX() > -Camera.x + YldGame.getImage().getWidth())
                canRender = false;
            if (item.getY() > -Camera.y + YldGame.getImage().getHeight())
                canRender = false;

            item.canRender = canRender;

            if (item.canRender) {
                item.actC++;
                if (item.actC >= item.recool) {
                    item.actC = 0;
                    item.actImage++;
                }
                if (item.actImage >= item.itemImage.length) {
                    item.actImage = 0;
                }
            }
        });
    }

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
                    if(tile.onTop == false)
                    tile.actC++;
                    if (tile.actC >= tile.recool) {
                        tile.actC = 0;
                        tile.actImage++;
                    }
                    if (tile.actImage >= tile.getTileImage().length) {
                        tile.actImage = 0;
                    }
                    boolean canRender = true;

                    if (tile.getX() < -Camera.x - Tile.getWidth())
                        canRender = false;
                    if (tile.getY() < -Camera.y - Tile.getHeight())
                        canRender = false;
                    if (tile.getX() > -Camera.x + YldGame.getImage().getWidth())
                        canRender = false;
                    if (tile.getY() > -Camera.y + YldGame.getImage().getHeight())
                        canRender = false;

                    if (canRender) {
                        g.drawImage(tile.getTileImage()[tile.actImage], tile.getX() + cx, tile.getY() + cy, null);
                    }

                }


            }
            for (Tile tile : TileSystem.tiles) {

                if (tile != null) {
                    if(tile.onTop) {
                        boolean canRender = true;

                        if (tile.getX() < -Camera.x - tile.getTileImage()[tile.actImage].getWidth(null))
                            canRender = false;
                        if (tile.getY() < -Camera.y - tile.getTileImage()[tile.actImage].getHeight(null))
                            canRender = false;
                        if (tile.getX() > -Camera.x + YldGame.getImage().getWidth())
                            canRender = false;
                        if (tile.getY() > -Camera.y + YldGame.getImage().getHeight())
                            canRender = false;

                        if (canRender) {
                            g.drawImage(tile.getTileImage()[tile.actImage], tile.getX() + cx, tile.getY() + cy, null);
                        }
                    }


                }


            }

        }
        Item.items.forEach(item -> {
            boolean canRender = true;

            if (item.getX() < -Camera.x - Tile.getWidth())
                canRender = false;
            if (item.getY() < -Camera.y - Tile.getHeight())
                canRender = false;
            if (item.getX() > -Camera.x + YldGame.getImage().getWidth())
                canRender = false;
            if (item.getY() > -Camera.y + YldGame.getImage().getHeight())
                canRender = false;

            item.canRender = canRender;

            if (item.canRender) {
                g.drawImage(item.itemImage[item.actImage], item.getX() + cx, item.getY() + cy, null);
            }
        });
    }
}
