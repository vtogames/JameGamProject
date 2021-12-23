import yield.Yld;
import yield.objects.YldScene;
import yield.util.YldAudio;

public class PlayScene extends YldScene {

    public static Player player;
    public static DialogueBox dialogueBox;
    public static YldAudio wind;

    @Override
    public void create() {
        super.create();
        Yld.setRenderFps(30);
        add(player = new Player());
       add(new TileSystem());
        add(new SnowFilter());
        add(dialogueBox = new DialogueBox());
        add(new NPCController());
        add(new EnemyController());
        add(new RIPS());
        wind = new YldAudio("/wind.wav");
    }

    @Override
    public void update() {
        super.update();
        if(TileSystem.actLevel == "map1" || TileSystem.actLevel == "map2") {
            wind.play(true);
        }
    }

    public static void nextLevel() {
        if(TileSystem.actLevel == "map1") {
            TileSystem.tilesFromImagePath("map2");
        }
    }

    @Override
    public void enter() {
        super.enter();
        if(TileSystem.actLevel == null) {
            TileSystem.actLevel = "map1";
        }
        TileSystem.tilesFromImagePath(TileSystem.actLevel);
    }
}
