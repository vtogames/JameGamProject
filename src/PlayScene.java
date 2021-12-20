import yield.objects.YldScene;

public class PlayScene extends YldScene {

    @Override
    public void create() {
        super.create();
        add(new GameInterface());
    }
}
