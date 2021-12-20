import yield.objects.YldScene;

public class PlayScene extends YldScene {

    public static TileSystem tileSystem;

    @Override
    public void create() {
        super.create();

        add(new GameInterface());
        add(tileSystem = new TileSystem());
        add(new Player());
        tileSystem.tilesFromImagePath("/testmap.png");
    }
}
