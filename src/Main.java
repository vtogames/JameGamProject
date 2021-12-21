import yield.YldGame;
import yield.display.YldFrame;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Main extends YldFrame {

    public static PlayScene playScene;

    public static void main(String[] args) {
        new Main();
    }

    Main() {
        super();
        try {
            Tile.TILE_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/tile_spritesheet.png")));
            Player.PLAYER_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/player_spritesheet.png")));
            NPC.NPC_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/npc_spritesheet.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Tile.WALL_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(0, 0, 16, 16);
        Tile.GROUND_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(16, 0, 16, 16);
        Tile.LAVA_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(16 * 2, 0, 16, 16);
        Tile.SUN_IMAGE1 = Tile.TILE_SPRITESHEET.getSubimage(16 * 3, 0, 16, 16);
        Tile.SUN_IMAGE2 = Tile.TILE_SPRITESHEET.getSubimage(32, 16, 16, 16);
        Tile.SUN_IMAGE3 = Tile.TILE_SPRITESHEET.getSubimage(48, 16, 16, 16);
        Tile.FIRE_IMAGE1 = Tile.TILE_SPRITESHEET.getSubimage(128, 0, 16, 16);
        Tile.FIRE_IMAGE2 = Tile.TILE_SPRITESHEET.getSubimage(144, 0, 16, 16);

        add(new YldGame(285, 160, playScene = new PlayScene()));
        setVisible(true);
    }

}
