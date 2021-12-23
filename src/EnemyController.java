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
            if (e.bouncer) {
                if(PlayScene.player.getAxis().position.getX() > e.x - YldGame.getImage().getWidth() * 2 && PlayScene.player.getAxis().position.getX() < e.x + YldGame.getImage().getWidth() * 2
                && PlayScene.player.getAxis().position.getY() > e.y - YldGame.getImage().getHeight() * 2 && PlayScene.player.getAxis().position.getY() < e.y + YldGame.getImage().getHeight() * 2) {
                    e.walking = true;
                    if (e.actDir == 'r') {
                        e.x += e.speed;
                    }
                    if (e.actDir == 'd') {
                        e.y += e.speed;
                    }
                    if (e.actDir == 'l') {
                        e.x -= e.speed;
                    }
                    if (e.actDir == 'u') {
                        e.y -= e.speed;
                    }
                }

            }

            if (e.life <= 0) {
                enemies.remove(e);
                if (e.toDrop != null)
                    Item.items.add(new Item(e.toDrop, (int) e.x, (int) e.y));
            }

            if (e.canRender && e.archer) {
                if (PlayScene.player.getAxis().position.getX() > e.x - Tile.getWidth() * e.vision * 2 && PlayScene.player.getAxis().position.getX() < e.x + Tile.getWidth() * e.vision * 2 + PlayScene.player.getAxis().scale.getX()
                        && PlayScene.player.getAxis().position.getY() > e.y - Tile.getHeight() * e.vision * 2 && PlayScene.player.getAxis().position.getY() < e.y + Tile.getHeight() * e.vision * 2 + PlayScene.player.getAxis().scale.getY()) {
                    e.actDamage++;
                    if (e.actDamage > e.damageRecool) {
                        e.actDamage = 0;
                        float xforce = 0, yforce = 0;
                        if (PlayScene.player.getAxis().position.getX() > e.x + Tile.getWidth()) {
                            xforce = e.throwForce;
                        }
                        if (PlayScene.player.getAxis().position.getX() < e.x - Tile.getWidth()) {
                            xforce = -e.throwForce;
                        }
                        if (PlayScene.player.getAxis().position.getY() > e.y + Tile.getHeight()) {
                            yforce = e.throwForce;
                        }
                        if (PlayScene.player.getAxis().position.getY() < e.y - Tile.getHeight()) {
                            yforce = -e.throwForce;
                        }
                        SnowBall ball = new SnowBall();
                        if (e.ballLife == 0)
                            e.ballLife = 80;
                        ball.life = e.ballLife;
                        ball.xForce = xforce;
                        ball.yForce = yforce;
                        ball.x = e.x + e.enemyIdle[0].getWidth(null) / 4f;
                        ball.y = e.y + e.enemyIdle[0].getHeight(null) / 4f;

                        SnowFilter.snowFlakes.add(ball);
                    }
                }
            }

            if (TileSystem.tiles != null)
                for (Tile tile : TileSystem.tiles) {
                    if (tile != null) {
                        if (tile.getTileType() == TileType.WALL || tile.getTileType() == TileType.WALL_FREZED) {

                            if (e.bouncer) {
                                if(e.walking) {
                                    if (e.x - 1 >= tile.getX() && e.x - 1 <= tile.getX() + Tile.getWidth() && e.y >= tile.getY() && e.y <= tile.getY() + Tile.getHeight()
                                            || e.x - 1 + e.enemyIdle[0].getWidth(null) >= tile.getX() && e.x - 1 + e.enemyIdle[0].getWidth(null) <= tile.getX() + Tile.getWidth() && e.y >= tile.getY() && e.y <= tile.getY() + Tile.getHeight()
                                            || e.x - 1 >= tile.getX() && e.x - 1 <= tile.getX() + Tile.getWidth() && e.y + e.enemyIdle[0].getHeight(null) >= tile.getY() && e.y + e.enemyIdle[0].getHeight(null) <= tile.getY() + Tile.getHeight()
                                            || e.x - 1 + e.enemyIdle[0].getWidth(null) >= tile.getX() && e.x - 1 + e.enemyIdle[0].getWidth(null) <= tile.getX() + Tile.getWidth() && e.y + e.enemyIdle[0].getHeight(null) >= tile.getY() && e.y + e.enemyIdle[0].getHeight(null) <= tile.getY() + Tile.getHeight()) {
                                        if (e.actDir == 'r') {
                                            e.x -= e.speed;
                                            e.actDir = 'd';
                                        } else if (e.actDir == 'd') {
                                            e.y -= e.speed;
                                            e.actDir = 'l';
                                        } else if (e.actDir == 'l') {
                                            e.x += e.speed;
                                            e.actDir = 'u';
                                        } else if (e.actDir == 'u') {
                                            e.y += e.speed;
                                            e.actDir = 'r';
                                        }
                                    }
                                }

                            } else if (e.canRender) {
                                //up left
                                if (e.x - 1 >= tile.getX() && e.x - 1 <= tile.getX() + Tile.getWidth() && e.y >= tile.getY() && e.y <= tile.getY() + Tile.getHeight()) {
                                    e.x += e.speed;
                                    e.y += e.speed;
                                }

                                //up right
                                if (e.x - 1 + e.enemyIdle[0].getWidth(null) >= tile.getX() && e.x - 1 + e.enemyIdle[0].getWidth(null) <= tile.getX() + Tile.getWidth() && e.y >= tile.getY() && e.y <= tile.getY() + Tile.getHeight()) {
                                    e.x -= e.speed;
                                    e.y += e.speed;
                                }

                                //down left
                                if (e.x - 1 >= tile.getX() && e.x - 1 <= tile.getX() + Tile.getWidth() && e.y + e.enemyIdle[0].getHeight(null) >= tile.getY() && e.y + e.enemyIdle[0].getHeight(null) <= tile.getY() + Tile.getHeight()) {
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


            if (canRender) {

                if (!e.bouncer) {
                    if (PlayScene.player.getAxis().position.getX() > e.x - Tile.getWidth() * e.vision && PlayScene.player.getAxis().position.getX() < e.x + Tile.getWidth() * e.vision + PlayScene.player.getAxis().scale.getX()
                            && PlayScene.player.getAxis().position.getY() > e.y - Tile.getHeight() * e.vision && PlayScene.player.getAxis().position.getY() < e.y + Tile.getHeight() * e.vision + PlayScene.player.getAxis().scale.getY()) {
                        e.walking = true;
                        if (e.walk) {
                            if (e.runFromPlayer) {
                                if (PlayScene.player.getAxis().position.getX() > e.x) {
                                    e.x -= e.speed;
                                    if (!e.forward)
                                        e.facingR = true;
                                }
                                if (PlayScene.player.getAxis().position.getX() < e.x) {
                                    e.x += e.speed;
                                    if (!e.forward)
                                        e.facingR = false;
                                }
                                if (PlayScene.player.getAxis().position.getY() < e.y) {
                                    e.y += e.speed;
                                    if (e.forward)
                                        e.facingR = false;
                                }
                                if (PlayScene.player.getAxis().position.getY() > e.y) {
                                    e.y -= e.speed;
                                    if (e.forward)
                                        e.facingR = true;

                                }

                            } else {
                                if (PlayScene.player.getAxis().position.getX() > e.x) {
                                    e.x += e.speed;
                                    if (!e.forward)
                                        e.facingR = true;
                                }
                                if (PlayScene.player.getAxis().position.getX() < e.x) {
                                    e.x -= e.speed;
                                    if (!e.forward)
                                        e.facingR = false;
                                }
                                if (PlayScene.player.getAxis().position.getY() < e.y) {
                                    e.y -= e.speed;
                                    if (e.forward)
                                        e.facingR = false;
                                }
                                if (PlayScene.player.getAxis().position.getY() > e.y) {
                                    e.y += e.speed;
                                    if (e.forward)
                                        e.facingR = true;

                                }

                            }
                        }


                    } else {
                        e.walking = false;
                    }
                }
                if (e.damage != 0 && !e.archer) {
                    if (PlayScene.player.getAxis().position.getX() > e.x - Tile.getWidth() * e.hitVision && PlayScene.player.getAxis().position.getX() < e.x + Tile.getWidth() * e.hitVision + PlayScene.player.getAxis().scale.getX()
                            && PlayScene.player.getAxis().position.getY() > e.y - Tile.getHeight() * e.hitVision && PlayScene.player.getAxis().position.getY() < e.y + Tile.getHeight() * e.hitVision + PlayScene.player.getAxis().scale.getY()) {
                        e.actDamage++;
                        if (e.actDamage > e.damageRecool) {
                            e.actDamage = 0;
                            if (!e.spawner) {
                                Player.hit((int) e.damage);
                                if (e.attack != null) {
                                    e.attacking = true;
                                    e.actImage = 0;
                                }
                            } else {
                                enemies.add(new Enemy(e.toSpawn, e.x, e.y));
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
                if (e.bouncer) {
                    if (e.actDir == 'r') {
                        actImages = e.enemyWalkingR;
                    }
                    if (e.actDir == 'd') {
                        actImages = e.enemyWalkingR;
                    }
                    if (e.actDir == 'l') {
                        actImages = e.enemyWalkingL;
                    }
                    if (e.actDir == 'u') {
                        actImages = e.enemyWalkingL;
                    }
                } else if (e.walking)
                    if (e.facingR)
                        actImages = e.enemyWalkingR;
                    else
                        actImages = e.enemyWalkingL;

                if (e.y < PlayScene.player.getAxis().position.getY())
                    setLayer(PlayScene.player.getLayer() - 1);
                else
                    setLayer(PlayScene.player.getLayer() + 1);

                if (e.attacking) {
                    actImages = e.attack;
                }

                e.actImageC++;
                if (e.actImageC >= Enemy.imageCMax) {
                    e.actImageC = 0;
                    e.actImage++;
                }
                int length = 0;
                if (actImages != null)
                    length = actImages.length;
                if (e.actImage >= length) {
                    e.actImage = 0;
                    e.attacking = false;

                }
                if (e.bouncer) {

                    if (e.actDir == 'l' || e.actDir == 'r') {
                        if (e.actImage > (actImages).length / 2 - 1)
                            e.actImage = 0;
                    }
                    if (e.actDir == 'u' || e.actDir == 'd') {
                        if (e.actImage < (actImages).length / 2)
                            e.actImage = (actImages).length / 2;
                    }
                }

                Image toDraw = e.enemyIdle[0];

                if (actImages != null) {
                    toDraw = actImages[e.actImage];
                }

                g.drawImage(toDraw, (int) (e.x + TileDraw.cx), (int) (e.y + TileDraw.cy), null);
            }

        });

    }

}
