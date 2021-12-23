import java.awt.*;

public class SnowBall extends SnowFlake {

    public float xForce, yForce;
    public int life;
    public boolean attackPlayer = true;
    public final static Image SNOW_BALL_IMAGE = Item.ITEM_SPRITESHEET.getSubimage(16, 0, 16, 16);

}
