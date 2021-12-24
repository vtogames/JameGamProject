import yield.Yld;
import yield.YldGame;
import yield.objects.YldB;
import yield.util.YldInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class WindowController extends YldB {

    public boolean onFullscreen, f11 = true;

    @Override
    public void update() {
        try {
            if (YldInput.isKeyPressed(KeyEvent.VK_F11)) {
                if (f11) {
                    f11 = false;
                        Yld.getMainFrame().dispose();
                        Yld.getMainFrame().setSize(Toolkit.getDefaultToolkit().getScreenSize());
                        Yld.getMainFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
                        Yld.getMainFrame().setUndecorated(true);
                        Yld.getMainFrame().setVisible(true);
                        onFullscreen = true;
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void create() {

    }
}
