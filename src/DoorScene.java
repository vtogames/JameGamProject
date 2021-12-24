import yield.YldGame;
import yield.objects.YldScene;

public class DoorScene extends YldScene {

    public void enter() {
        super.enter();
        setFrames(0);
        Player.openningDoor.play();
    }

    @Override
    public void update() {
        super.update();
        if (getFrames() > 6 * 60) {
            PlayScene.wind.stop();
            YldGame.switchScene("PlayScene");
            TileSystem.tilesFromImagePath("map3");
            PlayScene.switchBlack = true;
        }

    }
}
