import yield.YldGame;
import yield.display.YldGraphical;
import yield.objects.YldObject;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class SnowFilter extends YldObject {

    public static Random random = new Random();
    public HashSet<SnowFlake> snowFlakes = new HashSet<>();

    @Override
    public void update() {
        super.update();
        setLayer(50);
        if (random.nextInt(5) == 0) {
            SnowFlake snowFlake = new SnowFlake();
            snowFlake.x = random.nextInt(YldGame.getImage().getWidth() + YldGame.getImage().getWidth() / 2) - YldGame.getImage().getWidth() / 2f;
            snowFlake.speed = random.nextFloat() + 1f;
            snowFlakes.add(snowFlake);
        }
        snowFlakes.forEach(sf -> {
            sf.x += 0.5 * sf.speed;
            sf.y += sf.speed;
            if (sf.x > YldGame.getImage().getWidth())
                snowFlakes.remove(sf);
            if (sf.y > YldGame.getImage().getHeight())
                snowFlakes.remove(sf);
        });
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.white);
        snowFlakes.forEach(sf -> {
            g.fillRect((int) sf.x, (int) sf.y, 1, 1);
        });
    }

}
