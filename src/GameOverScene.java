import yield.YldGame;
import yield.objects.YldScene;
import yield.util.YldAudio;
import yield.util.YldInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class GameOverScene extends YldScene {

    private YldAudio gameOverAudio = new YldAudio("/gameover.wav");
    public static Image gameOverImage;

    @Override
    public void enter() {
        super.enter();
        PlayScene.wind.stop();
        gameOverAudio.play(false);
        setFrames(0);
    }

    @Override
    public void update() {
        super.update();
        if(YldInput.isKeyPressed(KeyEvent.VK_ENTER) && getFrames() > 60) {
            gameOverAudio.stop();
            Player.lifes = 3;
            Player.items.clear();
            TileSystem.actLevel = null;
            TileSystem.toAddEnemiesW.clear();
            YldGame.switchScene("PlayScene");
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2 = (Graphics2D) g;
        int a = 255 - getFrames();
        int a2 = 400 - getFrames();
        if(a < 0) a = 0;
        if(a2 < 0) a2 = 0;
        if(a2 > 255) a2 = 255;

        String gameOver = "Game Over";
        String enter = "press enter";
        g.setColor(Color.white);
        g.setFont(new Font("arial", 0, 20));
        g.drawString(gameOver, YldGame.getImage().getWidth() / 2 - g.getFontMetrics().stringWidth(gameOver) / 2, YldGame.getImage().getHeight() / 2 + g.getFont().getSize() / 2 - 10);

        g.setFont(new Font("arial", 0, 10));
        g.drawString(enter, YldGame.getImage().getWidth() / 2 - g.getFontMetrics().stringWidth(enter) / 2, YldGame.getImage().getHeight() / 2 + g.getFont().getSize() / 2 + 20 - 10);
        g2.setColor(new Color(0, 0, 0, a2));

        g.fillRect(0, YldGame.getImage().getHeight() / 2 - g.getFont().getSize() / 2 + 20 - 10, YldGame.getImage().getWidth(), 100);




        if(gameOverImage != null) {
            g.drawImage(gameOverImage, 0, 0, null);
        }
        g2.setColor(new Color(0, 0, 0, a));
        g.fillRect(0, 0, YldGame.getImage().getWidth(), YldGame.getImage().getHeight());
    }
}
