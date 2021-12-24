import yield.YldGame;
import yield.display.Vector;
import yield.objects.YldObject;
import yield.util.YldAudio;
import yield.util.YldInput;
import yield.util.YldMouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Player extends YldObject {

    public static boolean key, canWalk = true, goToSpawn, burning, hitting, startHitting, renderPlayer = true, lastSideR, lastSideU = true, showFire, showHud;
    // public static int horizontal, vertical;
    public static float speed = 1f, walkSpeed = 1f, runSpeed = 1.3f, spawnX, spawnY;
    public int animFrame, animFrameMax = 4, animFrameC, animFrameCMax = 8, fireAct, fireActC, hittingDamage = 0, invincibleLife, lx, ly;
    public static Vector movementVector = new Vector(0, 0);
    public static BufferedImage PLAYER_SPRITESHEET;
    public boolean moving;
    public boolean created;
    public boolean running;
    public boolean canPressMouse, canCreatePresent;
    public static boolean green;
    public YldAudio snow1 = new YldAudio("/snow1.wav"), snow2 = new YldAudio("/snow2.wav");
    public static int life, lifeColorC, lifeFrameC, lifeFrameCMax = 60, enterColorC, counterToGameOver, invincible, actInvincible, ctgoC, lifes = 3, totalToys;
    public static Image idleR[], idleL[], idleU[], runD[], runU[], runL[], runR[], drawImage, lifeGoodImage, lifeMediumImage, lifeBadImage, deadImage, fireImage[], invincibleImages[];
    public static Image dialogImage;
    public static ArrayList<ItemType> items = new ArrayList<>();
    public static char dir = 'u';
    public static HashSet<RIP> rips = new HashSet<>();
    public YldAudio hitSound = new YldAudio("/hit.wav"), deadSound = new YldAudio("/dead.wav"), hatsong = new YldAudio("/hatsong.wav");
    public static YldAudio openningDoor;

    @Override
    public void create() {
        super.create();
        openningDoor = new YldAudio("/openningdoor.wav");
        lifeGoodImage = Tile.TILE_SPRITESHEET.getSubimage(64, 0, 16, 16);
        lifeMediumImage = Tile.TILE_SPRITESHEET.getSubimage(64 + 16, 0, 16, 16);
        lifeBadImage = Tile.TILE_SPRITESHEET.getSubimage(64 + 16 * 2, 0, 16, 16);
        deadImage = Tile.TILE_SPRITESHEET.getSubimage(64 + 16 * 3, 0, 16, 16);
        invincibleImages = new Image[6];
        invincibleImages[0] = Tile.TILE_SPRITESHEET.getSubimage(64, 16, 16, 16);
        invincibleImages[1] = Tile.TILE_SPRITESHEET.getSubimage(64, 16, 16, 16);
        invincibleImages[2] = Tile.TILE_SPRITESHEET.getSubimage(64 + 16, 16, 16, 16);
        invincibleImages[3] = Tile.TILE_SPRITESHEET.getSubimage(64 + 16, 16, 16, 16);
        invincibleImages[4] = Tile.TILE_SPRITESHEET.getSubimage(64 + 32, 16, 16, 16);
        invincibleImages[5] = Tile.TILE_SPRITESHEET.getSubimage(64 + 32, 16, 16, 16);
        RIP.ripImage = deadImage;
        dialogImage = Player.PLAYER_SPRITESHEET.getSubimage(0, 48, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
        idleR = new Image[4];
        idleR[0] = PLAYER_SPRITESHEET.getSubimage(0, 0, 16, 16);
        idleR[1] = PLAYER_SPRITESHEET.getSubimage(16, 0, 16, 16);
        idleR[2] = PLAYER_SPRITESHEET.getSubimage(16 * 2, 0, 16, 16);
        idleR[3] = PLAYER_SPRITESHEET.getSubimage(16 * 3, 0, 16, 16);
        idleL = new Image[4];
        idleL[0] = PLAYER_SPRITESHEET.getSubimage(0, 16, 16, 16);
        idleL[1] = PLAYER_SPRITESHEET.getSubimage(16, 16, 16, 16);
        idleL[2] = PLAYER_SPRITESHEET.getSubimage(16 * 2, 16, 16, 16);
        idleL[3] = PLAYER_SPRITESHEET.getSubimage(16 * 3, 16, 16, 16);
        idleU = new Image[4];
        idleU[0] = PLAYER_SPRITESHEET.getSubimage(0, 32, 16, 16);
        idleU[1] = PLAYER_SPRITESHEET.getSubimage(16, 32, 16, 16);
        idleU[2] = PLAYER_SPRITESHEET.getSubimage(16 * 2, 32, 16, 16);
        idleU[3] = PLAYER_SPRITESHEET.getSubimage(16 * 3, 32, 16, 16);
        runR = new Image[4];
        runR[0] = PLAYER_SPRITESHEET.getSubimage(64, 0, 16, 16);
        runR[1] = PLAYER_SPRITESHEET.getSubimage(80, 0, 16, 16);
        runR[2] = PLAYER_SPRITESHEET.getSubimage(96, 0, 16, 16);
        runR[3] = PLAYER_SPRITESHEET.getSubimage(112, 0, 16, 16);
        runU = new Image[4];
        runU[0] = PLAYER_SPRITESHEET.getSubimage(64, 16, 16, 16);
        runU[1] = PLAYER_SPRITESHEET.getSubimage(80, 16, 16, 16);
        runU[2] = PLAYER_SPRITESHEET.getSubimage(96, 16, 16, 16);
        runU[3] = PLAYER_SPRITESHEET.getSubimage(112, 16, 16, 16);
        runL = new Image[4];
        runL[0] = PLAYER_SPRITESHEET.getSubimage(64, 32, 16, 16);
        runL[1] = PLAYER_SPRITESHEET.getSubimage(80, 32, 16, 16);
        runL[2] = PLAYER_SPRITESHEET.getSubimage(96, 32, 16, 16);
        runL[3] = PLAYER_SPRITESHEET.getSubimage(112, 32, 16, 16);
        runD = new Image[4];
        runD[0] = PLAYER_SPRITESHEET.getSubimage(64, 48, 16, 16);
        runD[1] = PLAYER_SPRITESHEET.getSubimage(80, 48, 16, 16);
        runD[2] = PLAYER_SPRITESHEET.getSubimage(96, 48, 16, 16);
        runD[3] = PLAYER_SPRITESHEET.getSubimage(112, 48, 16, 16);
        fireImage = new Image[2];
        fireImage[0] = Tile.FIRE_IMAGE1;
        fireImage[1] = Tile.FIRE_IMAGE2;
        enterColorC = 255;
        renderPlayer = true;
        canWalk = true;
        hitting = false;
        lifeFrameC = 0;
        lifeColorC = 0;
        life = 100;
        created = true;
    }

    @Override
    public void enter() {
        super.enter();
        showFire = false;
        enterColorC = 255;
        renderPlayer = true;
        canWalk = true;
        hitting = false;
        lifeFrameC = 0;
        lifeColorC = 0;

    }

    @Override
    public void update() {
        super.update();

        if (getFrames() < 4) {
            life = 100;
        }
        if (goToSpawn) {
            goToSpawn = false;
            renderPlayer = true;
            axis.position.teleport(new Vector(spawnX, spawnY));
        }
        if (canWalk) {
            try {
                if (YldInput.isKeyPressed(KeyEvent.VK_UP) || YldInput.isKeyPressed(KeyEvent.VK_W)) {
                    movementVector.setY(-1 * speed);
                    lx = -1;
                    ly = 0;
                    dir = 'u';
                }
                if (YldInput.isKeyPressed(KeyEvent.VK_DOWN) || YldInput.isKeyPressed(KeyEvent.VK_S)) {
                    movementVector.setY(1 * speed);
                    lx = 1;
                    ly = 0;
                    dir = 'd';
                }

                if (YldInput.isKeyPressed(KeyEvent.VK_LEFT) || YldInput.isKeyPressed(KeyEvent.VK_A)) {
                    movementVector.setX(-1 * speed);
                    ly = -1;
                    lx = 0;
                    dir = 'l';
                }
                if (YldInput.isKeyPressed(KeyEvent.VK_RIGHT) || YldInput.isKeyPressed(KeyEvent.VK_D)) {
                    movementVector.setX(1 * speed);
                    ly = 1;
                    lx = 0;
                    dir = 'r';
                }

                /*if (YldInput.isKeyPressed(KeyEvent.VK_SHIFT)) {
                    running = true;
                    speed = runSpeed;
                } else {
                    running = false;
                    speed = walkSpeed;
                }*/
            } catch (Exception ignore) {

            }

        }
        moving = movementVector.getX() != 0 || movementVector.getY() != 0;
        Vector toTranslate = new Vector(0, 0);
        if (TileSystem.tiles != null)
            for (Tile tile : TileSystem.tiles) {
                if (tile != null) {
                    if (tile.getTileType() == TileType.SUN) {
                        if (axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                            burning = true;
                            hittingDamage = 8;
                        }
                    }

                    if (tile.getTileType() == TileType.NEXT_LEVEL) {
                        if (axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                            if (TileSystem.actLevel == "map1")
                                TileSystem.tilesFromImagePath("map2");
                            else if (TileSystem.actLevel == "map4")
                                TileSystem.tilesFromImagePath("map5");

                        }
                        if(TileSystem.actLevel == "map5") {
                            if(axis.position.getY() < tile.getY()) {
                                YldGame.switchScene("BombScene");
                            }
                        }
                    }

                    if (tile.getTileType() == TileType.LAVA) {
                        if (axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                            life = 0;
                        }
                    }

                    if (tile.getTileType() == TileType.SHOW_HUD) {
                        if (axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                            Player.showHud = true;
                        }
                    }
                    if (tile.getTileType() == TileType.WALL || tile.getTileType() == TileType.WALL_FREZED) {

                        //up left
                        if (axis.position.getX() - 1 >= tile.getX() && axis.position.getX() - 1 <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight()) {
                            toTranslate.setX(toTranslate.getX() + speed);
                            toTranslate.setY(toTranslate.getY() + speed);
                        }

                        //up right
                        if (axis.position.getX() - 1 + axis.scale.getX() >= tile.getX() && axis.position.getX() - 1 + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight()) {
                            toTranslate.setX(toTranslate.getX() + -speed);
                            toTranslate.setY(toTranslate.getY() + speed);
                        }

                        //down left
                        if (axis.position.getX() - 1 >= tile.getX() && axis.position.getX() - 1 <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                            toTranslate.setX(toTranslate.getX() + speed);
                            toTranslate.setY(toTranslate.getY() + -speed);
                        }

                        //down right
                        if (axis.position.getX() - 1 + axis.scale.getX() >= tile.getX() && axis.position.getX() - 1 + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                            toTranslate.setX(toTranslate.getX() + -speed);
                            toTranslate.setY(toTranslate.getY() + -speed);
                        }


                    }
                }

            }
        if (YldMouse.isPressing()) {
            if (canPressMouse) {

                canPressMouse = false;
                for (ItemType item : items) {
                    if (item == ItemType.SNOW_BALL) {
                        items.remove(item);
                        SnowBall ball = new SnowBall();
                        ball.x = axis.position.getX();
                        ball.y = axis.position.getY();
                        ball.attackPlayer = false;
                        ball.life = 80;
                        ball.speed = 2f;
                        float x = movementVector.getX(), y = movementVector.getY();
                        if (x == 0 && y == 0) {
                            x = ly;
                            y = lx;
                        }
                        if (x > 0) {
                            ball.xForce = ball.speed;
                        }
                        if (x < 0) {
                            ball.xForce = -ball.speed;
                        }
                        if (y > 0) {
                            ball.yForce = ball.speed;
                        }
                        if (y < 0) {
                            ball.yForce = -ball.speed;
                        }

                        YldAudio throwSnow = new YldAudio("/throwsnow.wav");
                        throwSnow.play();
                        throwSnow.remove();
                        SnowFilter.snowFlakes.add(ball);

                        break;
                    }
                }
            }
        } else {
            canPressMouse = true;
        }
        AtomicInteger tt = new AtomicInteger();
        Item.items.forEach(item -> {
            if (item.type == ItemType.TOY) {
                tt.getAndIncrement();
            }
            if (axis.position.getX() >= item.getX() && axis.position.getX() <= item.getX() + Tile.getWidth() && axis.position.getY() >= item.getY() && axis.position.getY() <= item.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= item.getX() && axis.position.getX() + axis.scale.getX() <= item.getX() + Tile.getWidth() && axis.position.getY() >= item.getY() && axis.position.getY() <= item.getY() + Tile.getHeight() || axis.position.getX() >= item.getX() && axis.position.getX() <= item.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= item.getY() && axis.position.getY() + axis.scale.getY() <= item.getY() + Tile.getHeight() || axis.position.getX() + axis.scale.getX() >= item.getX() && axis.position.getX() + axis.scale.getX() <= item.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= item.getY() && axis.position.getY() + axis.scale.getY() <= item.getY() + Tile.getHeight()) {
                if (item.type != ItemType.GIFT) {
                    Item.items.remove(item);
                    items.add(item.type);
                }

            }
        });
        totalToys = tt.get();

        if (TileSystem.actLevel == "map4") {
            if (YldInput.isKeyPressed(KeyEvent.VK_ENTER)) {
                if (canCreatePresent) {
                    canCreatePresent = false;
                    Item.items.add(new Item(ItemType.GIFT, (int) axis.position.getX() / Tile.getWidth(), (int) axis.position.getY() / Tile.getHeight()));
                    System.out.println("dwdw");

                }
            } else {
                canCreatePresent = true;
            }
        }

        if (totalToys == 0 && TileSystem.actLevel == "map3") {
            PlayScene.switchBlack = false;
            YldGame.switchScene("PlayScene");
            TileSystem.tilesFromImagePath("map4");

        }
        if (items.contains(ItemType.HAT)) {
            invincible = 60 * 20;
            lifeColorC = 255;
            items.remove(ItemType.HAT);
            invincibleLife = life;
            green = true;
        }
        if (items.contains(ItemType.KEY)) {
            items.remove(ItemType.KEY);
            key = true;
        }

        if (invincible > 0) {
            if (invincible == 1) {
                enterColorC = 255;
            }
            hatsong.setCustomVolume(true);
            hatsong.setVolume(30);
            PlayScene.wind.setCustomVolume(true);
            PlayScene.wind.setVolume(0);
            hatsong.play(true);
            invincible--;
            if (life < invincibleLife) {
                life = invincibleLife;
            } else {
                invincibleLife = life;
            }

            actInvincible++;
            if (actInvincible >= invincibleImages.length) {
                actInvincible = 0;
            }
        } else {
            PlayScene.wind.setCustomVolume(false);
            hatsong.stop();
        }
        if (toTranslate.getX() > speed) toTranslate.setX(speed);
        if (toTranslate.getX() < -speed) toTranslate.setX(-speed);
        if (toTranslate.getY() > speed) toTranslate.setY(speed);
        if (toTranslate.getY() < -speed) toTranslate.setY(-speed);

        axis.position.translate(toTranslate);

        axis.position.translate(movementVector);

        movementVector.setX(0);
        movementVector.setY(0);

        int mult = 1;

        if (moving) {
            mult = 1;
            if (enterColorC > 0)
                enterColorC -= 3;
            if (dir == 'r') {
                lastSideR = true;
                lastSideU = false;
                drawImage = runR[animFrame];
            } else if (dir == 'u') {
                lastSideU = true;
                drawImage = runU[animFrame];
            } else if (dir == 'l') {
                lastSideR = false;
                lastSideU = false;
                drawImage = runL[animFrame];
            } else if (dir == 'd') {
                lastSideR = false;
                lastSideU = false;
                drawImage = runD[animFrame];
            }

        } else {
            mult = 4;
            if (lastSideU)
                drawImage = idleU[animFrame];
            else if (lastSideR)
                drawImage = idleR[animFrame];
            else
                drawImage = idleL[animFrame];
        }

        if (showFire) {
            fireActC++;
            if (fireActC > animFrameCMax) {
                fireActC = 0;
                fireAct++;
            }
            if (fireAct > 1)
                fireAct = 0;
        }

        animFrameC++;
        if (animFrameC >= animFrameCMax * mult) {
            animFrame++;
            animFrameC = 0;
        }
        if (animFrame == 0) {
            if (moving)
                snow1.play(false);
        }
        if (animFrame == 2) {
            if (moving)
                snow2.play(false);
        }
        if (animFrame >= animFrameMax) {
            animFrame = 0;
        }

        if (burning) {
            showFire = true;
            burning = false;
            if (lifeFrameC == 0)
                hitting = true;
        }

        if (hitting) {
            if (startHitting) lifeFrameC = lifeFrameCMax;
            startHitting = false;
            lifeFrameC++;
            if (lifeFrameC > lifeFrameCMax) {
                lifeFrameC = 0;
                showFire = false;
                hit(hittingDamage);
                hitting = false;
            }
        } else {
            startHitting = true;
        }

        if (TileSystem.actLevel == "map1") {

        }


        if (drawImage == null) drawImage = idleR[0];
        axis.scale.setImageScale(drawImage);
        Camera.x = axis.position.getX() * -1 + YldGame.getImage().

                getWidth() / 2f - drawImage.getWidth(null) / 2f;
        Camera.y = axis.position.getY() * -1 + YldGame.getImage().

                getHeight() / 2f - drawImage.getHeight(null) / 2f;

        if (life <= 0 && created) {
            life = 0;

            renderPlayer = false;
            canWalk = false;
            if (counterToGameOver == 0) {
                rips.add(new RIP((int) axis.position.getX(), (int) axis.position.getY()));
                TileSystem.toAddEnemiesW.add(new EnemyCache(EnemyType.SELF, axis.position.getX(), axis.position.getY()));
                deadSound.play(false);
                PlayScene.dialogueBox.end = true;
                if (TileSystem.actLevel != "map1")
                    lifes--;
            }


            counterToGameOver++;
            if (counterToGameOver == 500) {
                if (lifes > 0)
                    YldGame.switchScene("PlayScene");
                else
                    YldGame.switchScene("GameOverScene");
            }
            try {
                if (YldInput.isKeyPressed(KeyEvent.VK_ENTER) && counterToGameOver > 60) counterToGameOver = 499;
            } catch (Exception ignore) {
            }

        } else {
            counterToGameOver = 0;
        }

        if (lifeColorC > 0) lifeColorC -= 2;


        if (enterColorC > 0) enterColorC -= 1;

    }

    public static void hit(int hittingDamage) {
        if (counterToGameOver == 0) {
            if (hittingDamage >= 0) {
                showHud = true;
                green = false;
                PlayScene.player.hitSound.getClip().setFramePosition(0);
                PlayScene.player.hitSound.play(false);
                life -= hittingDamage;
                lifeColorC = 120;
            } else {
                showHud = true;
                green = true;
                PlayScene.player.hitSound.getClip().setFramePosition(0);
                PlayScene.player.hitSound.play(false);
                life -= hittingDamage;
                lifeColorC = 120;
            }

        }

    }

    @Override
    public void draw(Graphics g) {
        if (!created) return;
        if (drawImage == null) drawImage = idleR[0];

        Graphics2D g2 = (Graphics2D) g;
        setLayer(30);

        if (renderPlayer) {
            g2.setColor(new Color(0, 0, 0, 20));
            g.fillOval((int) (axis.position.getX() + axis.scale.getX() / 8 + Camera.x), (int) (axis.position.getY() + axis.scale.getY() - 2 + Camera.y), (int) (axis.scale.getX() / 1.5), 4);
            g.drawImage(drawImage, (int) (axis.position.getX() + Camera.x), (int) (axis.position.getY() + Camera.y), Tile.getWidth(), Tile.getHeight(), null);
            if (showFire) {
                g.drawImage(fireImage[fireAct], (int) (axis.position.getX() + Camera.x), (int) (axis.position.getY() + Camera.y), Tile.getWidth(), Tile.getHeight(), null);
            }

        }

        if (invincible > 0) {
            g.drawImage(invincibleImages[actInvincible], (int) (axis.position.getX() + Camera.x), (int) (axis.position.getY() + Camera.y), Tile.getWidth(), Tile.getHeight(), null);
        }

    }
}
