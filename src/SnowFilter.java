import yield.YldGame;
import yield.display.YldGraphical;
import yield.objects.YldObject;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class SnowFilter extends YldObject {

    public static Random random = new Random();
    public static HashSet<SnowFlake> snowFlakes = new HashSet<>();

    @Override
    public void update() {
        super.update();
        if (TileSystem.actLevel != "map3") {
            if (random.nextInt(5) == 0) {
                SnowFlake snowFlake = new SnowFlake();
                snowFlake.x = random.nextInt(YldGame.getImage().getWidth() + YldGame.getImage().getWidth() / 2) - YldGame.getImage().getWidth() / 2f;
                snowFlake.speed = random.nextFloat() + 1f;
                snowFlakes.add(snowFlake);
            }
        }

        snowFlakes.forEach(sf -> {
            if (!(sf instanceof SnowBall)) {
                sf.x += 0.5 * sf.speed;
                sf.y += sf.speed;
                if (sf.x > YldGame.getImage().getWidth())
                    snowFlakes.remove(sf);
                if (sf.y > YldGame.getImage().getHeight())
                    snowFlakes.remove(sf);
            } else {
                if (((SnowBall) sf).attackPlayer) {
                    if (PlayScene.player.getAxis().position.getX() > sf.x - Tile.getWidth() && PlayScene.player.getAxis().position.getX() < sf.x + Tile.getWidth() + PlayScene.player.getAxis().scale.getX()
                            && PlayScene.player.getAxis().position.getY() > sf.y - Tile.getHeight() && PlayScene.player.getAxis().position.getY() < sf.y + Tile.getHeight() + PlayScene.player.getAxis().scale.getY()) {
                        snowFlakes.remove(sf);
                        Player.hit(10);
                    }
                } else {
                    EnemyController.enemies.forEach(e -> {
                        if (e.x > sf.x - Tile.getWidth() && e.x < sf.x + Tile.getWidth() + e.enemyIdle[0].getWidth(null)
                                && e.y > sf.y - Tile.getHeight() && e.y < sf.y + Tile.getHeight() + e.enemyIdle[0].getHeight(null)) {
                            snowFlakes.remove(sf);
                            e.life--;
                        }
                    });
                }

                sf.x += ((SnowBall) sf).xForce;
                sf.y += ((SnowBall) sf).yForce;

                ((SnowBall) sf).life--;

                if (((SnowBall) sf).life < 0)
                    snowFlakes.remove(sf);
            }

        });
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.white);
        setLayer(50);
        snowFlakes.forEach(sf -> {
            if (!(sf instanceof SnowBall)) {
                //g.fillRect((int) sf.x, (int) sf.y, 1, 1);
            }

        });
        if (TileSystem.actLevel == "map3") {
            g2.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, YldGame.getImage().getWidth(), YldGame.getImage().getHeight());
            String toysString = "TOYS REMAINING: " + Player.totalToys;
            g.setColor(Color.white);
            g.setFont(new Font("arial", 1, 10));
            g.drawString(toysString, YldGame.getImage().getWidth() - g.getFontMetrics().stringWidth(toysString), g.getFont().getSize());
        }
        //g.drawImage(Item.GIFT_IMAGES[0], 0, 0, null);
    }

}
