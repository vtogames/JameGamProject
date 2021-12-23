import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {

    public Image enemyIdle[], enemyWalkingR[], enemyWalkingL[], attack[];
    public boolean walking, canRender, bouncer, spawner, walk = true, forward = false, attacking;
    public float speed = 1f, x, y, vision, throwForce, hitVision = 0.5f;
    public boolean facingR, archer, runFromPlayer;
    public char actDir = 'd';
    public int actImage, actImageC, damageRecool = 20, actDamage = damageRecool, damage, ballLife, life;
    public static int imageCMax = 6;
    public ItemType toDrop;
    public EnemyType type, toSpawn;
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
                life = 2;
                damage = 2;
                vision = 2;
                damageRecool = 30;
                speed = 0.4f;
                actDamage = damageRecool;
                break;
            case ICE_BOUNCER:
                enemyIdle = new Image[1];
                enemyWalkingR = new Image[8];
                enemyWalkingR[0] = ENEMY_SPRITESHEET.getSubimage(0, 64, 16, 16);
                enemyWalkingR[1] = ENEMY_SPRITESHEET.getSubimage(16, 64, 16, 16);
                enemyWalkingR[2] = ENEMY_SPRITESHEET.getSubimage(32, 64, 16, 16);
                enemyWalkingR[3] = ENEMY_SPRITESHEET.getSubimage(48, 64, 16, 16);
                enemyWalkingR[4] = ENEMY_SPRITESHEET.getSubimage(0, 16, 16, 16);
                enemyWalkingR[5] = ENEMY_SPRITESHEET.getSubimage(16, 16, 16, 16);
                enemyWalkingR[6] = ENEMY_SPRITESHEET.getSubimage(32, 16, 16, 16);
                enemyWalkingR[7] = ENEMY_SPRITESHEET.getSubimage(48, 16, 16, 16);
                enemyWalkingL = new Image[8];
                enemyWalkingL[0] = ENEMY_SPRITESHEET.getSubimage(0, 48, 16, 16);
                enemyWalkingL[1] = ENEMY_SPRITESHEET.getSubimage(16, 48, 16, 16);
                enemyWalkingL[2] = ENEMY_SPRITESHEET.getSubimage(32, 48, 16, 16);
                enemyWalkingL[3] = ENEMY_SPRITESHEET.getSubimage(48, 48, 16, 16);
                enemyWalkingL[4] = ENEMY_SPRITESHEET.getSubimage(0, 32, 16, 16);
                enemyWalkingL[5] = ENEMY_SPRITESHEET.getSubimage(16, 32, 16, 16);
                enemyWalkingL[6] = ENEMY_SPRITESHEET.getSubimage(32, 32, 16, 16);
                enemyWalkingL[7] = ENEMY_SPRITESHEET.getSubimage(48, 32, 16, 16);
                life = 5;
                bouncer = true;
                damage = 15;
                vision = 1;
                damageRecool = 45;
                speed = 1f;
                enemyIdle[0] = enemyWalkingR[0];
                actDamage = damageRecool;
                break;
            case PUMPKIN:
                enemyIdle = new Image[8];
                enemyIdle[0] = ENEMY_SPRITESHEET.getSubimage(128, 16, 16, 16);
                enemyIdle[1] = enemyIdle[0];
                enemyIdle[2] = enemyIdle[0];
                enemyIdle[3] = enemyIdle[0];
                enemyIdle[4] = ENEMY_SPRITESHEET.getSubimage(128 + 16, 16, 16, 16);
                enemyIdle[5] = enemyIdle[4];
                enemyIdle[6] = enemyIdle[4];
                enemyIdle[7] = enemyIdle[4];
                enemyWalkingR = new Image[4];
                enemyWalkingR[0] = ENEMY_SPRITESHEET.getSubimage(64, 16, 16, 16);
                enemyWalkingR[1] = ENEMY_SPRITESHEET.getSubimage(64 + 16, 16, 16, 16);
                enemyWalkingR[2] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 2, 16, 16, 16);
                enemyWalkingR[3] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 3, 16, 16, 16);
                enemyWalkingL = new Image[4];
                enemyWalkingL[0] = ENEMY_SPRITESHEET.getSubimage(64, 32, 16, 16);
                enemyWalkingL[1] = ENEMY_SPRITESHEET.getSubimage(64 + 16, 32, 16, 16);
                enemyWalkingL[2] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 2, 32, 16, 16);
                enemyWalkingL[3] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 3, 32, 16, 16);
                life = 3;
                forward = true;
                archer = true;
                runFromPlayer = true;
                vision = 2;
                ballLife = 45;
                throwForce = 1f;
                damageRecool = 120;
                actDamage = damageRecool;
                break;
            case WIZARD:
                enemyIdle = new Image[4];
                enemyIdle[0] = ENEMY_SPRITESHEET.getSubimage(64, 48, 16, 16);
                enemyIdle[1] = ENEMY_SPRITESHEET.getSubimage(64 + 16, 48, 16, 16);
                enemyIdle[2] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 2, 48, 16, 16);
                enemyIdle[3] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 3, 48, 16, 16);
                walk = false;
                spawner = true;
                toSpawn = EnemyType.ANAO;
                hitVision = 4;
                damage = 1;
                damageRecool = 5 * 60;
                life = 5;
                break;
            case ANAO:
                enemyIdle = new Image[8];
                enemyIdle[0] = ENEMY_SPRITESHEET.getSubimage(128, 64, 16, 16);
                enemyIdle[1] = enemyIdle[0];
                enemyIdle[2] = enemyIdle[0];
                enemyIdle[3] = enemyIdle[0];
                enemyIdle[4] = ENEMY_SPRITESHEET.getSubimage(128 + 16, 64, 16, 16);
                enemyIdle[5] = enemyIdle[4];
                enemyIdle[6] = enemyIdle[4];
                enemyIdle[7] = enemyIdle[4];
                life = 2;
                enemyWalkingR = new Image[4];
                enemyWalkingR[0] = ENEMY_SPRITESHEET.getSubimage(64, 80, 16, 16);
                enemyWalkingR[1] = ENEMY_SPRITESHEET.getSubimage(64 + 16, 80, 16, 16);
                enemyWalkingR[2] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 2, 80, 16, 16);
                enemyWalkingR[3] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 3, 80, 16, 16);
                enemyWalkingL = new Image[4];
                enemyWalkingL[0] = ENEMY_SPRITESHEET.getSubimage(64, 64, 16, 16);
                enemyWalkingL[1] = ENEMY_SPRITESHEET.getSubimage(64 + 16, 64, 16, 16);
                enemyWalkingL[2] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 2, 64, 16, 16);
                enemyWalkingL[3] = ENEMY_SPRITESHEET.getSubimage(64 + 16 * 3, 64, 16, 16);
                damage = 10;
                vision = 8;
                damageRecool = 45;
                speed = .5f;
                actDamage = damageRecool;
                break;
            case WOLF:
                enemyIdle = new Image[8];
                enemyIdle[0] = ENEMY_SPRITESHEET.getSubimage(128, 80, 16, 16);
                enemyIdle[1] = enemyIdle[0];
                enemyIdle[2] = enemyIdle[0];
                enemyIdle[3] = enemyIdle[0];
                enemyIdle[4] = ENEMY_SPRITESHEET.getSubimage(128 + 16, 80, 16, 16);
                enemyIdle[5] = enemyIdle[4];
                enemyIdle[6] = enemyIdle[4];
                enemyIdle[7] = enemyIdle[4];
                enemyWalkingR = new Image[4];
                enemyWalkingR[0] = ENEMY_SPRITESHEET.getSubimage(0, 80, 16, 16);
                enemyWalkingR[1] = ENEMY_SPRITESHEET.getSubimage(16, 80, 16, 16);
                enemyWalkingR[2] = ENEMY_SPRITESHEET.getSubimage(16 * 2, 80, 16, 16);
                enemyWalkingR[3] = ENEMY_SPRITESHEET.getSubimage(16, 80, 16, 16);
                enemyWalkingL = new Image[4];
                enemyWalkingL[0] = ENEMY_SPRITESHEET.getSubimage(0, 80 + 16, 16, 16);
                enemyWalkingL[1] = ENEMY_SPRITESHEET.getSubimage(16, 80 + 16, 16, 16);
                enemyWalkingL[2] = ENEMY_SPRITESHEET.getSubimage(16 * 2, 80 + 16, 16, 16);
                enemyWalkingL[3] = ENEMY_SPRITESHEET.getSubimage(16, 80 + 16, 16, 16);
                attack = new Image[3];
                attack[0] = ENEMY_SPRITESHEET.getSubimage(112, 96, 16, 16);
                attack[1] = ENEMY_SPRITESHEET.getSubimage(112 + 16, 96, 16, 16);
                attack[2] = ENEMY_SPRITESHEET.getSubimage(112 + 16 * 2, 96, 16, 16);
                forward = true;
                life = 4;
                damage = 20;
                vision = 2;
                damageRecool = 30;
                speed = 0.3f;
                actDamage = damageRecool;
                break;
        }
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                ", dir(bouncer)=" + actDir +
                '}';
    }
}
