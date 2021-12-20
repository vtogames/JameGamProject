import yield.components.YldComponent;
import yield.display.YldGraphical;

import java.awt.*;

public class InterfaceRenderer extends YldComponent implements YldGraphical {

    @Override
    public void create() {
        super.create();
        setLayer(20);
    }

    @Override
    public void draw(Graphics g) {

    }
}
