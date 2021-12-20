import yield.components.YldString;
import yield.objects.YldObject;

public class GameInterface extends YldObject {

    @Override
    public void create() {
        super.create();
        add(new InterfaceRenderer());
    }
}
