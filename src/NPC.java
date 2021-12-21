import yield.objects.YldObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC {

    public Image npcIdle[];
    public int x, y, actImage, actImageC;
    public static int imageCMax = 6;
    public NPCType type;
    public static BufferedImage NPC_SPRITESHEET;

    public NPC(NPCType type, int x, int y) {
        this.type = type;
        this.x = x * Tile.getWidth();
        this.y = y * Tile.getHeight();
        switch (type) {
            case one:
                npcIdle = new Image[4];
                npcIdle[0] = Player.PLAYER_SPRITESHEET.getSubimage(0, 0, 16, 16);
                npcIdle[1] = Player.PLAYER_SPRITESHEET.getSubimage(16, 0, 16, 16);
                npcIdle[2] = Player.PLAYER_SPRITESHEET.getSubimage(16 * 2, 0, 16, 16);
                npcIdle[3] = Player.PLAYER_SPRITESHEET.getSubimage(16 * 3, 0, 16, 16);
                break;
        }
    }
}
