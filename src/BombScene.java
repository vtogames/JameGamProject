import yield.YldGame;
import yield.objects.YldScene;

import java.awt.*;

public class BombScene extends YldScene {

    public int x, y;

    @Override
    public void enter() {
        super.enter();
        setFrames(0);
    }

    @Override
    public void update() {
        super.update();
        x = getFrames() - 500;
        y = x - YldGame.getImage().getHeight();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.white);
        g.setFont(new Font("arial", 1, 20));
        g.drawString("end of chapter 1", 0, getFrames());
        if(getFrames() > 300)
            System.exit(0);

    }
}
