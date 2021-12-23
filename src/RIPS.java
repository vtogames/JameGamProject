import yield.YldGame;
import yield.objects.YldObject;

import java.awt.*;

public class RIPS extends YldObject {


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2 = (Graphics2D) g;

        SnowFilter.snowFlakes.forEach(sf -> {
            if (sf instanceof SnowBall) {
                g.drawImage(SnowBall.SNOW_BALL_IMAGE, (int) (sf.x + Camera.x), (int) (sf.y + Camera.y), null);
            }
        });

        Player.rips.forEach(r -> {
            if (PlayScene.player.getAxis().position.getX() > r.x - 20 && PlayScene.player.getAxis().position.getX() < r.x + 20 + PlayScene.player.getAxis().scale.getX()
                    && PlayScene.player.getAxis().position.getY() > r.y - 20 && PlayScene.player.getAxis().position.getY() < r.y + 20 + PlayScene.player.getAxis().scale.getY()) {
                if (r.y < PlayScene.player.getAxis().position.getY())
                    setLayer(PlayScene.player.getLayer() - 1);
                else
                    setLayer(PlayScene.player.getLayer() + 1);

            } else {
                setLayer(40);
            }
            g.drawImage(RIP.ripImage, r.x + TileDraw.cx, r.y + TileDraw.cy, null);

        });
        Image lifeImage = Player.lifeGoodImage;

        setLayer(40);
        if (Player.life < 60 && Player.life > 30)
            lifeImage = Player.lifeMediumImage;
        if (Player.life <= 30)
            lifeImage = Player.lifeBadImage;
        if (Player.life == 0)
            lifeImage = Player.deadImage;

        if (Player.showHud) {
            String lifeString = "life: " + Player.life;
            g.setFont(new Font("arial", 1, 10));
            g2.setColor(new Color(0, 0, 0, 150));
            g.fillRoundRect(YldGame.getImage().getWidth() - g.getFontMetrics().stringWidth(lifeString) - 2, 0, g.getFontMetrics().stringWidth(lifeString) + 2, g.getFont().getSize() + 5 + lifeImage.getHeight(null), 8, 8);
            g.setColor(Color.white);

            g.drawString(lifeString, YldGame.getImage().getWidth() - g.getFontMetrics().stringWidth(lifeString), g.getFont().getSize());
            g.drawImage(lifeImage, YldGame.getImage().getWidth() - g.getFontMetrics().stringWidth(lifeString) / 2 - lifeImage.getWidth(null) / 2, g.getFont().getSize() + 2, null);
        }

        if(TileSystem.actLevel != "map1") {
            String lifesString = Player.lifes + "";
            Image i = Player.dialogImage.getScaledInstance(16, 16, Image.SCALE_FAST);
            g2.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, i.getWidth(null) + 6, i.getHeight(null));
            g.drawImage(i, 0, 0, Tile.getWidth(), Tile.getHeight(), null);
            g.setColor(Color.white);
            g.setFont(new Font("arial", 1, 8));
            g.drawString(lifesString, i.getWidth(null), i.getHeight(null) / 2 + g.getFont().getSize() / 2);
        }


        if (Player.lifeColorC > 255) Player.lifeColorC = 255;
        if (Player.lifeColorC < 0) Player.lifeColorC = 0;
        Player.ctgoC = Player.counterToGameOver - 150;
        if (Player.ctgoC > 255) Player.ctgoC = 255;
        if (Player.ctgoC < 0) Player.ctgoC = 0;

        g2.setColor(new Color(255, 0, 0, Player.lifeColorC));
        g.fillRect(0, 0, YldGame.getImage().getWidth(), YldGame.getImage().getHeight());
        g2.setColor(new Color(0, 0, 0, Player.ctgoC));
        g.fillRect(0, 0, YldGame.getImage().

                getWidth(), YldGame.

                getImage().

                getHeight());
        if (Player.enterColorC < 0) Player.enterColorC = 0;
        if (Player.enterColorC > 255) Player.enterColorC = 255;
        g2.setColor(new Color(255, 255, 255, Player.enterColorC));
        g.fillRect(0, 0, YldGame.getImage().getWidth(), YldGame.getImage().getHeight());
    }
}
