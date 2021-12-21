import yield.objects.YldScene;

public class PlayScene extends YldScene {

    public static TileSystem tileSystem;
    public static Player player;

    @Override
    public void create() {
        super.create();

        add(new GameInterface());
        add(tileSystem = new TileSystem());
        add(player = new Player());
        tileSystem.tilesFromImagePath("/testmap.png");
    }
}
