import yield.YldGame;
import yield.display.YldFrame;
import yield.util.YldAudio;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Main extends YldFrame {

    public static PlayScene playScene;

    public static int w, h;

    public static void main(String[] args) {
        new Main();
    }

    Main() {
        super();
        try {
            Tile.TILE_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/tile_spritesheet.png")));
            Player.PLAYER_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/player_spritesheet.png")));
            NPC.NPC_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/npc_spritesheet.png")));
            Enemy.ENEMY_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/enemy_spritesheet.png")));
            Item.ITEM_SPRITESHEET = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/item_spritesheet.png")));
            Tile.STOCK = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/stock.png")));
            Tile.SANTAS_HOUSE = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/House.png")));
            Tile.BIG_HOUSE = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/bighouse.png")));
            GameOverScene.gameOverImage = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/gameover.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(Objects.requireNonNull(Main.class.getResource("/yield/blank.png"))).getImage(), new Point(0, 0), "blank"));

        Tile.WALL_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(0, 0, 16, 16);
        Tile.WOOD_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(0, 32, 16, 16);
        Tile.FLOOR_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(16, 32, 16, 16);
        Tile.CARPET_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(32, 32, 16, 16);
        Tile.WALL_FREZED = Tile.TILE_SPRITESHEET.getSubimage(16, 16, 16, 16);
        Tile.GROUND_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(16, 0, 16, 16);
        Tile.LAVA_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(16 * 2, 0, 16, 16);
        Tile.SUN_IMAGE1 = Tile.TILE_SPRITESHEET.getSubimage(16 * 3, 0, 16, 16);
        Tile.SUN_IMAGE2 = Tile.TILE_SPRITESHEET.getSubimage(32, 16, 16, 16);
        Tile.SUN_IMAGE3 = Tile.TILE_SPRITESHEET.getSubimage(48, 16, 16, 16);
        Tile.FIRE_IMAGE1 = Tile.TILE_SPRITESHEET.getSubimage(128, 0, 16, 16);
        Tile.FIRE_IMAGE2 = Tile.TILE_SPRITESHEET.getSubimage(144, 0, 16, 16);
        Tile.ICE_IMAGE = Tile.TILE_SPRITESHEET.getSubimage(0, 16, 16, 16);
        Tile.WATER_1 = Tile.TILE_SPRITESHEET.getSubimage(112, 16, 16, 16);
        Tile.WATER_2 = Tile.TILE_SPRITESHEET.getSubimage(112 + 16, 16, 16, 16);
        Tile.WATER_3 = Tile.TILE_SPRITESHEET.getSubimage(112 + 16 * 2, 16, 16, 16);
        Item.HAT_IMAGE = Item.ITEM_SPRITESHEET.getSubimage(0, 0, 16, 16);
        Item.SNOW_BALL_IMAGE = Item.ITEM_SPRITESHEET.getSubimage(0, 16, 16, 16);
        Item.HEART_IMAGE = Item.ITEM_SPRITESHEET.getSubimage(16, 16, 16, 16);
        Item.KEY_IMAGE = Item.ITEM_SPRITESHEET.getSubimage(32, 16, 16, 16);
        for(int i = 0; i < Item.TOY.length; i++) {
            Item.TOY[i] = Item.ITEM_SPRITESHEET.getSubimage(16 * i, 32, 16, 16);
        }
        for(int i = 0; i < Item.GIFT_IMAGES.length; i++) {
            Item.GIFT_IMAGES[i] = Item.ITEM_SPRITESHEET.getSubimage(16 * i + 80, 0, 16, 16);
        }


        add(new YldGame(285, 160, playScene = new PlayScene()));
        YldGame.addScene(new GameOverScene());
        YldGame.addScene(new DoorScene());
        YldGame.addScene(new BombScene());
        setVisible(true);
        w = getWidth();
        h = getHeight();
        requestFocus();
    }

}
