import yield.YldGame;
import yield.YldTime;
import yield.objects.YldObject;

import java.awt.*;
import java.util.HashSet;

public class EnemyController extends YldObject {

    public static HashSet<Enemy> enemies = new HashSet<>();

    @Override
    public void update() {
        super.update();
        enemies.forEach(e -> {
            boolean canRender = true;

            if (e.x < -Camera.x - Tile.getWidth())
                canRender = false;
            if (e.y < -Camera.y - Tile.getHeight())
                canRender = false;
            if (e.x > -Camera.x + YldGame.getImage().getWidth())
                canRender = false;
            if (e.y > -Camera.y + YldGame.getImage().getHeight())
                canRender = false;

            e.canRender = canRender;

            if (canRender) {

                if(e.type == EnemyType.SELF) {
                    if (PlayScene.player.getAxis().position.getX() > e.x - Tile.getWidth() * e.vision && PlayScene.player.getAxis().position.getX() < e.x + Tile.getWidth() * e.vision + PlayScene.player.getAxis().scale.getX()
                            && PlayScene.player.getAxis().position.getY() > e.y - Tile.getHeight() * e.vision && PlayScene.player.getAxis().position.getY() < e.y + Tile.getHeight() * e.vision + PlayScene.player.getAxis().scale.getY()) {
                        e.walking = true;
                        if (PlayScene.player.getAxis().position.getX() > e.x) {
                            e.x += e.speed;
                            e.facingR = true;
                        }
                        if (PlayScene.player.getAxis().position.getX() < e.x) {
                            e.x -= e.speed;
                            e.facingR = false;
                        }
                        if (PlayScene.player.getAxis().position.getY() > e.y) {
                            e.y += e.speed;
                        }
                        if (PlayScene.player.getAxis().position.getY() < e.y) {
                            e.y -= e.speed;
                        }
                    } else {
                        e.walking = false;
                    }

                    if (PlayScene.player.getAxis().position.getX() > e.x - Tile.getWidth() / 2f && PlayScene.player.getAxis().position.getX() < e.x + Tile.getWidth() / 2f + PlayScene.player.getAxis().scale.getX()
                            && PlayScene.player.getAxis().position.getY() > e.y - Tile.getHeight() / 2f && PlayScene.player.getAxis().position.getY() < e.y + Tile.getHeight() / 2f + PlayScene.player.getAxis().scale.getY()) {
                        e.actDamage++;
                        if(e.actDamage > e.damageRecool) {
                            e.actDamage = 0;
                            Player.hit((int) e.damage);
                        }
                    }
                }

                //terminar bouce

                if (TileSystem.tiles != null)
                    for (Tile tile : TileSystem.tiles) {
                        if (tile != null) {
                            if (tile.getTileType() == TileType.WALL || tile.getTileType() == TileType.WALL_FREZED) {

                                if (e.x >= tile.getX() && e.x <= tile.getX() + Tile.getWidth() && e.y >= tile.getY() && e.y <= tile.getY() + Tile.getHeight() || e.x + e.enemyIdle[0].getWidth(null) >= tile.getX() && e.x + e.enemyIdle[0].getWidth(null) <= tile.getX() + Tile.getWidth() && e.y >= tile.getY() && e.y <= tile.getY() + Tile.getHeight() || e.x >= tile.getX() && e.x <= tile.getX() + Tile.getWidth() && e.y + e.enemyIdle[0].getHeight(null) >= tile.getY() && e.y + e.enemyIdle[0].getHeight(null) <= tile.getY() + Tile.getHeight() || e.x + e.enemyIdle[0].getWidth(null) >= tile.getX() && e.x + e.enemyIdle[0].getWidth(null) <= tile.getX() + Tile.getWidth() && e.y + e.enemyIdle[0].getHeight(null) >= tile.getY() && e.y + e.enemyIdle[0].getHeight(null) <= tile.getY() + Tile.getHeight()) {
                                    if(e.actDir == 'r') {
                                        e.actDir = 'd';
                                        e.x -= e.speed;
                                    }
                                    if(e.actDir == 'd') {
                                        e.actDir = 'l';
                                        e.y -= e.speed;
                                    }
                                    if(e.actDir == 'l') {
                                        e.actDir = 'w';
                                        e.x += e.speed;
                                    }
                                    if(e.actDir == 'w') {
                                        e.actDir = 'r';
                                        e.y += e.speed;
                                    }
                                }

                                //up left
                                if (e.x - 1 >= tile.getX() && e.x - 1 <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight()) {
                                    e.x += e.speed;
                                    e.y += e.speed;
                                }

                                //up right
                                if (e.x - 1 + e.enemyIdle[0].getWidth(null) >= tile.getX() && e.x - 1 + e.enemyIdle[0].getWidth(null) <= tile.getX() + Tile.getWidth() && e.y >= tile.getY() && e.y <= tile.getY() + Tile.getHeight()) {
                                    e.x -= e.speed;
                                    e.y += e.speed;
                                }

                                //down left
                                if (e.x - 1 >= tile.getX() && e.x - 1 <= tile.getX() + Tile.getWidth() && e.y + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                                    e.x += e.speed;
                                    e.y -= e.speed;
                                }

                                //down right
                                if (e.x - 1 + e.enemyIdle[0].getWidth(null) >= tile.getX() && e.x - 1 + e.enemyIdle[0].getWidth(null) <= tile.getX() + Tile.getWidth() && e.y + e.enemyIdle[0].getHeight(null) >= tile.getY() && e.y + e.enemyIdle[0].getHeight(null) <= tile.getY() + Tile.getHeight()) {
                                    e.x -= e.speed;
                                    e.y -= e.speed;
                                }


                            }
                        }

                    }


            }

        });
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        enemies.forEach(e -> {
            if (e.canRender) {
                Image[] actImages = e.enemyIdle;
                if (e.walking)
                    if (e.facingR)
                        actImages = e.enemyWalkingR;
                    else
                        actImages = e.enemyWalkingL;

                if (e.y < PlayScene.player.getAxis().position.getY())
                    setLayer(PlayScene.player.getLayer() - 1);
                else
                    setLayer(PlayScene.player.getLayer() + 1);

                if(Player.counterToGameOver > 0 || Player.lifeColorC != 0)
                    setLayer(PlayScene.player.getLayer() - 1);
                e.actImageC++;
                if (e.actImageC >= Enemy.imageCMax) {
                    e.actImageC = 0;
                    e.actImage++;
                }
                if (e.actImage >= actImages.length) {
                    e.actImage = 0;
                }
                g.drawImage(actImages[e.actImage], (int) (e.x + TileDraw.cx), (int) (e.y + TileDraw.cy), null);
            }

        });

    }

}
