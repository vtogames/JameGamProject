import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {

    public Image enemyIdle[], enemyWalkingR[], enemyWalkingL[];
    public boolean walking, canRender, bouncer;
    public float speed = 1f, x, y, vision;
    public boolean facingR;
    public char actDir = 'r';
    public int actImage, actImageC, actDamage, damageRecool = 20, damage;
    public static int imageCMax = 6;
    public EnemyType type;
    public static BufferedImage ENEMY_SPRITESHEET;

    public Enemy(EnemyType type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
        switch (type) {
            case SELF:
                enemyIdle = new Image[8];
                enemyIdle[0] = ENEMY_SPRITESHEET.getSubimage(0, 0, 16, 16);
                enemyIdle[1] = ENEMY_SPRITESHEET.getSubimage(0, 0, 16, 16);
                enemyIdle[2] = ENEMY_SPRITESHEET.getSubimage(0, 0, 16, 16);
                enemyIdle[3] = ENEMY_SPRITESHEET.getSubimage(0, 0, 16, 16);
                enemyIdle[4] = ENEMY_SPRITESHEET.getSubimage(16, 0, 16, 16);
                enemyIdle[5] = ENEMY_SPRITESHEET.getSubimage(16, 0, 16, 16);
                enemyIdle[6] = ENEMY_SPRITESHEET.getSubimage(16, 0, 16, 16);
                enemyIdle[7] = ENEMY_SPRITESHEET.getSubimage(16, 0, 16, 16);
                enemyWalkingR = new Image[4];
                enemyWalkingR[0] = ENEMY_SPRITESHEET.getSubimage(32, 0, 16, 16);
                enemyWalkingR[1] = ENEMY_SPRITESHEET.getSubimage(32 + 16, 0, 16, 16);
                enemyWalkingR[2] = ENEMY_SPRITESHEET.getSubimage(32 + 16 * 2, 0, 16, 16);
                enemyWalkingR[3] = ENEMY_SPRITESHEET.getSubimage(32 + 16, 0, 16, 16);
                enemyWalkingL = new Image[4];
                enemyWalkingL[0] = ENEMY_SPRITESHEET.getSubimage(32 + 48, 0, 16, 16);
                enemyWalkingL[1] = ENEMY_SPRITESHEET.getSubimage(32 + 16 + 48, 0, 16, 16);
                enemyWalkingL[2] = ENEMY_SPRITESHEET.getSubimage(32 + 16 * 2 + 48, 0, 16, 16);
                enemyWalkingL[3] = ENEMY_SPRITESHEET.getSubimage(32 + 16 + 48, 0, 16, 16);
                damage = 2;
                vision = 2;
                damageRecool = 30;
                speed = 0.4f;
                break;
            case WATER_BOUNCER:
                enemyIdle = new Image[1];
                enemyWalkingR = new Image[8];
                enemyWalkingR[0] = ENEMY_SPRITESHEET.getSubimage(0, 16, 16, 16);
                enemyWalkingR[1] = ENEMY_SPRITESHEET.getSubimage(16, 16, 16, 16);
                enemyWalkingR[2] = ENEMY_SPRITESHEET.getSubimage(32, 16, 16, 16);
                enemyWalkingR[3] = ENEMY_SPRITESHEET.getSubimage(48, 16, 16, 16);
                enemyWalkingR[4] = ENEMY_SPRITESHEET.getSubimage(0, 32, 16, 16);
                enemyWalkingR[5] = ENEMY_SPRITESHEET.getSubimage(16, 32, 16, 16);
                enemyWalkingR[6] = ENEMY_SPRITESHEET.getSubimage(32, 32, 16, 16);
                enemyWalkingR[7] = ENEMY_SPRITESHEET.getSubimage(48, 32, 16, 16);
                enemyWalkingL = new Image[8];
                enemyWalkingL[0] = ENEMY_SPRITESHEET.getSubimage(0, 48, 16, 16);
                enemyWalkingL[1] = ENEMY_SPRITESHEET.getSubimage(16, 48, 16, 16);
                enemyWalkingL[2] = ENEMY_SPRITESHEET.getSubimage(32, 48, 16, 16);
                enemyWalkingL[3] = ENEMY_SPRITESHEET.getSubimage(48, 48, 16, 16);
                enemyWalkingL[4] = ENEMY_SPRITESHEET.getSubimage(0, 64, 16, 16);
                enemyWalkingL[5] = ENEMY_SPRITESHEET.getSubimage(16, 64, 16, 16);
                enemyWalkingL[6] = ENEMY_SPRITESHEET.getSubimage(32, 64, 16, 16);
                enemyWalkingL[7] = ENEMY_SPRITESHEET.getSubimage(48, 64, 16, 16);
                bouncer = true;
                break;
        }
    }

}
