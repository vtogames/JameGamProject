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
            Tile.TILE_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/TILE_SPRITESHEET.png")));
            Player.PLAYER_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/PLAYER_SPRITESHEET.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Tile.WALL_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(0, 0, 16, 16);
        Tile.GROUND_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(16, 0, 16, 16);
        add(new YldGame(285, 160, playScene = new PlayScene()));
        setVisible(true);
    }

}
