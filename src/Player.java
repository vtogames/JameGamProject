import yield.Yld;
import yield.YldGame;
import yield.YldTime;
import yield.components.YldImage;
import yield.display.Vector;
import yield.display.YldGraphical;
import yield.objects.YldObject;
import yield.util.YldInput;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends YldObject implements YldGraphical {

    public static boolean canWalk = true, goToSpawn;
    // public static int horizontal, vertical;
    public static float speed = 1f, spawnX, spawnY;
    public int animFrame, animFrameMax = 4, animFrameC, animFrameCMax = 5;
    public static Vector movementVector = new Vector(0, 0);
    public static BufferedImage PLAYER_SPRITESHEET;
    public boolean moving, created;
    public Image idle[], runD[], runU[], runL[], runR[], drawImage;
    public static char dir = 'd';

    @Override
    public void create() {
        super.create();
        idle = new Image[4];
        idle[0] = PLAYER_SPRITESHEET.getSubimage(0, 0, 16, 16);
        idle[1] = PLAYER_SPRITESHEET.getSubimage(16, 0, 16, 16);
        idle[2] = PLAYER_SPRITESHEET.getSubimage(16 * 2, 0, 16, 16);
        idle[3] = PLAYER_SPRITESHEET.getSubimage(16 * 3, 0, 16, 16);
        runR = new Image[6];
        runR[0] = PLAYER_SPRITESHEET.getSubimage(64, 0, 16, 16);
        runR[1] = PLAYER_SPRITESHEET.getSubimage(80, 0, 16, 16);
        runR[2] = PLAYER_SPRITESHEET.getSubimage(96, 0, 16, 16);
        runR[3] = PLAYER_SPRITESHEET.getSubimage(112, 0, 16, 16);
        runR[4] = PLAYER_SPRITESHEET.getSubimage(128, 0, 16, 16);
        runR[5] = PLAYER_SPRITESHEET.getSubimage(144, 0, 16, 16);
        runU = new Image[6];
        runU[0] = PLAYER_SPRITESHEET.getSubimage(64, 16, 16, 16);
        runU[1] = PLAYER_SPRITESHEET.getSubimage(80, 16, 16, 16);
        runU[2] = PLAYER_SPRITESHEET.getSubimage(96, 16, 16, 16);
        runU[3] = PLAYER_SPRITESHEET.getSubimage(112, 16, 16, 16);
        runU[4] = PLAYER_SPRITESHEET.getSubimage(128, 16, 16, 16);
        runU[5] = PLAYER_SPRITESHEET.getSubimage(144, 16, 16, 16);
        runL = new Image[6];
        runL[0] = PLAYER_SPRITESHEET.getSubimage(64, 32, 16, 16);
        runL[1] = PLAYER_SPRITESHEET.getSubimage(80, 32, 16, 16);
        runL[2] = PLAYER_SPRITESHEET.getSubimage(96, 32, 16, 16);
        runL[3] = PLAYER_SPRITESHEET.getSubimage(112, 32, 16, 16);
        runL[4] = PLAYER_SPRITESHEET.getSubimage(128, 32, 16, 16);
        runL[5] = PLAYER_SPRITESHEET.getSubimage(144, 32, 16, 16);
        runD = new Image[6];
        runD[0] = PLAYER_SPRITESHEET.getSubimage(64, 48, 16, 16);
        runD[1] = PLAYER_SPRITESHEET.getSubimage(80, 48, 16, 16);
        runD[2] = PLAYER_SPRITESHEET.getSubimage(96, 48, 16, 16);
        runD[3] = PLAYER_SPRITESHEET.getSubimage(112, 48, 16, 16);
        runD[4] = PLAYER_SPRITESHEET.getSubimage(128, 48, 16, 16);
        runD[5] = PLAYER_SPRITESHEET.getSubimage(144, 48, 16, 16);
        setLayer(10);
        created = true;
    }

    @Override
    public void update() {
        super.update();
        if (goToSpawn) {
            goToSpawn = false;
            axis.position.teleport(new Vector(spawnX, spawnY));
        }
        if (canWalk) {
            if (YldInput.isKeyPressed(KeyEvent.VK_UP) || YldInput.isKeyPressed(KeyEvent.VK_W)) {
                movementVector.setY(-1 * speed);
                dir = 'u';
            }
            if (YldInput.isKeyPressed(KeyEvent.VK_DOWN) || YldInput.isKeyPressed(KeyEvent.VK_S)) {
                movementVector.setY(1 * speed);
                dir = 'd';
            }

            if (YldInput.isKeyPressed(KeyEvent.VK_LEFT) || YldInput.isKeyPressed(KeyEvent.VK_A)) {
                movementVector.setX(-1 * speed);
                dir = 'l';
            }
            if (YldInput.isKeyPressed(KeyEvent.VK_RIGHT) || YldInput.isKeyPressed(KeyEvent.VK_D)) {
                movementVector.setX(1 * speed);
                dir = 'r';
            }
        }
        moving = movementVector.getX() != 0 || movementVector.getY() != 0;
        Vector toTranslate = new Vector(0, 0);
        for (Tile tile : TileSystem.tiles) {
            if (tile.getTileType() == TileType.WALL) {

                //up left
                if (axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight()) {
                    toTranslate.setX(toTranslate.getX() + speed);
                    toTranslate.setY(toTranslate.getY() + speed);
                }

                //up right
                if (axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() >= tile.getY() && axis.position.getY() <= tile.getY() + Tile.getHeight()) {
                    toTranslate.setX(toTranslate.getX() + -speed);
                    toTranslate.setY(toTranslate.getY() + speed);
                }

                //down left
                if (axis.position.getX() >= tile.getX() && axis.position.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                    toTranslate.setX(toTranslate.getX() + speed);
                    toTranslate.setY(toTranslate.getY() + -speed);
                }

                //down right
                if (axis.position.getX() + axis.scale.getX() >= tile.getX() && axis.position.getX() + axis.scale.getX() <= tile.getX() + Tile.getWidth() && axis.position.getY() + axis.scale.getY() >= tile.getY() && axis.position.getY() + axis.scale.getY() <= tile.getY() + Tile.getHeight()) {
                    toTranslate.setX(toTranslate.getX() + -speed);
                    toTranslate.setY(toTranslate.getY() + -speed);
                }


            }
        }
        if (toTranslate.getX() > speed)
            toTranslate.setX(speed);
        if (toTranslate.getX() < -speed)
            toTranslate.setX(-speed);
        if (toTranslate.getY() > speed)
            toTranslate.setY(speed);
        if (toTranslate.getY() < -speed)
            toTranslate.setY(-speed);

        axis.position.translate(toTranslate);

        axis.position.translate(movementVector);

        movementVector.setX(0);
        movementVector.setY(0);

        animFrameC++;
        if (animFrameC >= animFrameCMax) {
            animFrame++;
            animFrameC = 0;
        }
        if (animFrame >= animFrameMax) {
            animFrame = 0;
        }

        if (moving) {
            if (dir == 'r') {
                drawImage = runR[animFrame];
            } else if (dir == 'u') {
                drawImage = runU[animFrame];
            } else if (dir == 'l') {
                drawImage = runL[animFrame];
            } else if (dir == 'd') {
                drawImage = runD[animFrame];
            }

        } else {
            drawImage = idle[animFrame];
        }


        if (drawImage == null)
            drawImage = idle[0];
        axis.scale.setImageScale(drawImage);
        Camera.x = axis.position.getX() * -1 + YldGame.getImage().getWidth() / 2f - drawImage.getWidth(null) / 2f;
        Camera.y = axis.position.getY() * -1 + YldGame.getImage().getHeight() / 2f - drawImage.getHeight(null) / 2f;
    }

    @Override
    public void draw(Graphics g) {
        if (!created)
            return;
        if (drawImage == null)
            drawImage = idle[0];

        g.drawImage(drawImage, (int) (axis.position.getX() + Camera.x), (int) (axis.position.getY() + Camera.y), Tile.getWidth(), Tile.getHeight(), null);
    }
}
