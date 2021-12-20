import yield.YldGame;
import yield.display.YldFrame;

public class Main extends YldFrame {

    public static void main(String[] args) {
        new Main();
    }

    Main() {
        super();
        add(new YldGame(427, 240, new PlayScene()));
        setVisible(true);
    }

}
