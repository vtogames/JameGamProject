import yield.YldGame;
import yield.objects.YldScene;
import yield.util.YldInput;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScene extends YldScene {

    @Override
    public void create() {
        super.create();
        //if(YldInput.isKeyPressed(KeyEvent.VK_ENTER)) {
        //Player.counterToGameOver = 0;
            YldGame.switchScene("PlayScene");
        //}
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.white);
        g.setFont(new Font("arial", 1, 20));
        //g.drawString("Game Over!", 100, 100);
    }
}
