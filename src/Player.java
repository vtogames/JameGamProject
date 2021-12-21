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
    public int animFrame, animFrameMax = 4;
    public static Vector movementVector = new Vector(0, 0);
    public static BufferedImage PLAYER_SPRITESHEET;
    public boolean moving, created;
    public Image idle[], run[], drawImage;

    @Override
    public void create() {
        super.create();
        idle = new Image[4];
        idle[0] = PLAYER_SPRITESHEET.getSubimage(0, 0, 16, 16);
        idle[1] = PLAYER_SPRITESHEET.getSubimage(32, 0, 16, 16);
        idle[2] = PLAYER_SPRITESHEET.getSubimage(32 * 2, 0, 16, 16);
        idle[3] = PLAYER_SPRITESHEET.getSubimage(32 * 3, 0, 16, 16);
        run = new Image[4];
        run[0] = PLAYER_SPRITESHEET.getSubimage(0, 32, 16, 16);
        run[1] = PLAYER_SPRITESHEET.getSubimage(32, 32, 16, 16);
        run[2] = PLAYER_SPRITESHEET.getSubimage(32 * 2, 32, 16, 16);
        run[3] = PLAYER_SPRITESHEET.getSubimage(32 * 3, 32, 16, 16);
        setLayer(10);
        created = true;
    }

    @Override
    public void update() {
        super.update();
        if(goToSpawn) {
            goToSpawn = false;
            axis.position.teleport(new Vector(spawnX, spawnY));
        }
        if (canWalk) {
            if (YldInput.isKeyPressed(KeyEvent.VK_UP) || YldInput.isKeyPressed(KeyEvent.VK_W)) {
                movementVector.setY(-1 * speed);
            } else if (YldInput.isKeyPressed(KeyEvent.VK_DOWN) || YldInput.isKeyPressed(KeyEvent.VK_S)) {
                movementVector.setY(1 * speed);
            } else {
                movementVector.setY(0);
            }

            if (YldInput.isKeyPressed(KeyEvent.VK_LEFT) || YldInput.isKeyPressed(KeyEvent.VK_A)) {
                movementVector.setX(-1 * speed);
            } else if (YldInput.isKeyPressed(KeyEvent.VK_RIGHT) || YldInput.isKeyPressed(KeyEvent.VK_D)) {
                movementVector.setX(1 * speed);
            } else {
                movementVector.setX(0);
            }
        }
        moving = movementVector.getX() != 0 || movementVector.getY() != 0;

        axis.position.translate(movementVector);

        if (drawImage == null)
            drawImage = idle[0];

        Camera.x = axis.position.getX() * -1 + YldGame.getImage().getWidth() / 2f - drawImage.getWidth(null) / 2f;
        Camera.y = axis.position.getY() * -1  + YldGame.getImage().getHeight() / 2f - drawImage.getHeight(null) / 2f;
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
