import yield.Yld;
import yield.objects.YldScene;

public class PlayScene extends YldScene {

    public static Player player;
    public static DialogueBox dialogueBox;

    @Override
    public void create() {
        super.create();
        Yld.setRenderFps(30);
        add(player = new Player());
       add(new TileSystem());
        add(new SnowFilter());
        add(dialogueBox = new DialogueBox());
        add(new NPCController());
    }

    @Override
    public void update() {
        super.update();
        //System.out.println(YldTime.getRenderFPS());
    }

    @Override
    public void enter() {
        super.enter();
        TileSystem.tilesFromImagePath("testmap3");
    }
}
