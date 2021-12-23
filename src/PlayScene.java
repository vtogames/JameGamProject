import yield.Yld;
import yield.objects.YldScene;
import yield.util.YldAudio;

public class PlayScene extends YldScene {

    public static Player player;
    public static DialogueBox dialogueBox;
    public YldAudio wind;

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
        wind = new YldAudio("/wind.wav");
    }

    @Override
    public void update() {
        super.update();
        if(TileSystem.actLevel == "map1") {
            wind.play(true);
        }
    }

    @Override
    public void enter() {
        super.enter();
        TileSystem.tilesFromImagePath("map1");
    }
}
