import yield.YldGame;
import yield.display.Vector;
import yield.objects.YldObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class TileSystem extends YldObject {

    public static Tile[] tiles;
    public static List<NPC> npcs = new ArrayList<>();
    public static HashSet<EnemyCache> toAddEnemies = new HashSet<>();
    public static HashSet<EnemyCache> toAddEnemiesW = new HashSet<>();
    public static String actLevel;

    @Override
    public void create() {
        super.create();
        add(new TileDraw());
        setLayer(5);
    }

    public static void tilesFromImagePath(String name) {

        actLevel = name;
        EnemyController.enemies.clear();
        Player.items.clear();
        npcs.clear();
        Item.items.clear();
        Player.key = false;
        try {
            PlayScene.dialogueBox.showing = false;
        } catch (Exception ignore) {
        }


        BufferedImage map = null;
        try {
            map = ImageIO.read(Objects.requireNonNull(TileSystem.class.getResource("/" + name + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert map != null;
        int[] pixels = new int[map.getWidth() * map.getHeight()];

        int WIDTH = map.getWidth();
        int HEIGHT = map.getHeight();

        tiles = new Tile[WIDTH * HEIGHT];

        pixels = map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);

        for (int xx = 0; xx < WIDTH; xx++) {
            for (int yy = 0; yy < HEIGHT; yy++) {
                int actPixel = pixels[xx + (yy * WIDTH)];
                int actTile = xx + (yy * WIDTH);
                switch (actPixel) {
                    case 0xFFD9414E:
                        Player.spawnX = xx * Tile.getWidth();
                        Player.spawnY = yy * Tile.getHeight();
                        Player.goToSpawn = true;
                        Player.life = 100;
                        break;
                    case 0xFFF6FFFF:
                        tiles[actTile] = new Tile(TileType.GROUND, xx, yy, false);
                        break;
                    case 0xFFCADDFF:
                        tiles[actTile] = new Tile(TileType.WALL, xx, yy, false);
                        break;
                    case 0xFF02007F:
                        tiles[actTile] = new Tile(TileType.WALL_FREZED, xx, yy, true);
                        break;
                    case 0xFFE5E5E5:
                        tiles[actTile] = new Tile(TileType.WALL, xx, yy, true);

                        break;
                    case 0xFFB4CCFF:
                        tiles[actTile] = new Tile(TileType.WALL_FREZED, xx, yy, false);
                        break;
                    case 0xFFFDFFE5:
                        tiles[actTile] = new Tile(TileType.SUN, xx, yy, false);
                        break;
                    case 0xFFFF8533:
                        tiles[actTile] = new Tile(TileType.LAVA, xx, yy, false);
                        break;
                    case 0xFFB2FCFF:
                        tiles[actTile] = new Tile(TileType.ICE, xx, yy, false);
                        break;
                    case 0xFF0500AB:
                        tiles[actTile] = new Tile(TileType.WATER, xx, yy, false);
                        break;
                    case 0xFFEA4656:
                        Item.items.add(new Item(ItemType.HAT, xx, yy));
                        break;
                    case 0xFFFFFFFF:
                        Item.items.add(new Item(ItemType.SNOW_BALL, xx, yy));
                        break;
                    case 0xFFDDD600:
                        Item.items.add(new Item(ItemType.KEY, xx, yy));
                        break;
                    case 0xFFFF77FC:
                        npcs.add(new NPC(NPCType.DOOR_LOCKED, xx, yy));
                        break;
                    case 0xFFDB4CBE:
                        npcs.add(new NPC(NPCType.BIG_HOUSE_DOOR, xx, yy));
                        break;
                    case 0xFFFF7130:
                        npcs.add(new NPC(NPCType.GOBLIN_1, xx, yy));
                        break;
                    case 0xFFE2622B:
                        npcs.add(new NPC(NPCType.GOBLIN_2, xx, yy));
                        break;
                    case 0xFFE2802B:
                        npcs.add(new NPC(NPCType.GOBLIN_3, xx, yy));
                        break;
                    case 0xFFC46D27:
                        npcs.add(new NPC(NPCType.GOBLIN_4, xx, yy));
                        break;
                    case 0xFFEA9A19:
                        npcs.add(new NPC(NPCType.GOBLIN_5, xx, yy));
                        break;
                    case 0xFFE8A843:
                        npcs.add(new NPC(NPCType.GOBLIN_6, xx, yy));
                        break;
                    case 0xFFE8A852:
                        npcs.add(new NPC(NPCType.GOBLIN_7, xx, yy));
                        break;
                    case 0xFF54240F:
                        npcs.add(new NPC(NPCType.TRASH, xx, yy));
                        break;
                    case 0xFF37946E:
                        npcs.add(new NPC(NPCType.SNOW_TREE, xx, yy));
                        break;
                    case 0xFF008FFF:
                        tiles[actTile] = new Tile(TileType.ICE, xx, yy, false);
                        Item.items.add(new Item(ItemType.TOY, xx, yy));
                        break;
                    case 0xFF00FF1D:
                        tiles[actTile] = new Tile(TileType.NEXT_LEVEL, xx, yy, false);
                        break;

                    case 0xFF5CCFE6:
                        toAddEnemies.add(new EnemyCache(EnemyType.ICE_BOUNCER, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFF8FA7D5:
                        toAddEnemies.add(new EnemyCache(EnemyType.AXOLOTL, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFF52B8CC:
                        toAddEnemies.add(new EnemyCache(EnemyType.ICE_STATUE, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFF26BEB1:
                        toAddEnemies.add(new EnemyCache(EnemyType.SELF, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFF66E6FF:
                        toAddEnemies.add(new EnemyCache(EnemyType.ANAO, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFFDF7126:
                        toAddEnemies.add(new EnemyCache(EnemyType.PUMPKIN, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFF306082:
                        toAddEnemies.add(new EnemyCache(EnemyType.WIZARD, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFF4E4E4E:
                        toAddEnemies.add(new EnemyCache(EnemyType.WOLF, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFF664233:
                        toAddEnemies.add(new EnemyCache(EnemyType.ELF, xx * Tile.getWidth(), yy * Tile.getHeight()));
                        break;
                    case 0xFFC2E8EC:
                        tiles[actTile] = new Tile(TileType.BOUNCE_GROUND, xx, yy, true);
                        break;
                        /*case 0xFFFF7130:
                            //npcs.add(new NPC(NPCType.one, xx, yy));
                            npcs.add(new NPC(NPCType.SELF_ENEMY, xx, yy));
                            break;*/

                    case 0xFFFF4486:
                        tiles[actTile] = new Tile(TileType.SANTAS_HOUSE, xx, yy, true);
                        break;
                    case 0xFFFF9FFF:
                        npcs.add(new NPC(NPCType.SANTAS_DOOR, xx, yy));
                        break;
                    case 0xFF1B3328:
                        tiles[actTile] = new Tile(TileType.STOCK, xx, yy, true);
                        break;


                    case 0xFF5C6B99:
                        tiles[actTile] = new Tile(TileType.BIG_HOUSE, xx, yy, true);
                        break;


                }
                if (tiles[actTile] == null)
                    tiles[actTile] = new Tile(TileType.GROUND, xx, yy, false);

            }
        }

        Player.showHud = false;

        for (EnemyCache e : toAddEnemies) {
            EnemyController.enemies.add(new Enemy(e.type, e.x, e.y));
        }
        for (EnemyCache e : toAddEnemiesW) {
            EnemyController.enemies.add(new Enemy(e.type, e.x, e.y));
        }
        toAddEnemies.clear();
    }

}
