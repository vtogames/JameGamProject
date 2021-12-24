import yield.YldGame;
import yield.display.YldGraphical;
import yield.objects.YldObject;
import yield.util.YldAudio;
import yield.util.YldInput;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DialogueBox extends YldObject {

    public boolean showing, endAct, end, ending, can = true;
    public int actS, max, actC, cMax = 2, actM, startFrames, endFrames, canInteractFrames, toHideInteract;
    public String actMessage = "", messages[];
    public Image[] images;
    public boolean playerSpeaking, force;
    public Image playerImage;
    public YldAudio beep = new YldAudio("/beep.wav"), enter = new YldAudio("/enter.wav");
    public String otherName = "???";
    public NPC toDesapear;

    public static boolean canInteract;

    public void show(String[] message, Image image1, Image image2, String otherName) {
        showing = true;
        this.otherName = otherName;
        playerImage = image2;
        messages = message.clone();
        actM = -1;
        images = new Image[messages.length];
        Player.lastSideU = true;
        for (int i = 0; i < messages.length; i++) {
            String s = messages[i];
            if (s.startsWith("1")) {
                images[i] = image1;
            } else {
                images[i] = image2;
            }
            s = s.substring(1);
            messages[i] = s;
        }
        can = false;
    }

    @Override
    public void update() {
        super.update();
        setLayer(60);
        if (canInteractFrames > 0) {
            canInteractFrames++;
            if (canInteractFrames > 30) {
                canInteractFrames = 0;
                canInteract = false;
            }
        }
        toHideInteract--;
        if (canInteract) {
            toHideInteract = 10;
            if (canInteractFrames == 0)
                canInteractFrames = 1;
        }
        if (showing) {
            Player.canWalk = false;
            if (!endAct) {
                actC++;
                if (actC >= cMax) {
                    actC = 0;
                    actS++;
                    beep.play(false);
                }
            }
            if (actS > max) {
                actS = max;
                endAct = true;
            }
            if (YldInput.isKeyPressed(KeyEvent.VK_ESCAPE)) {
                force = true;
                end = true;
            }
            if (YldInput.isKeyPressed(KeyEvent.VK_SPACE) && getFrames() - startFrames > 20) {
                if (actM < messages.length - 1) {
                    force = false;
                    enter.play(false);
                    actS = 0;
                    startFrames = getFrames();
                    actM++;
                    actMessage = messages[actM];
                    max = actMessage.length();
                    actS = 0;
                    endAct = false;
                    xs = 0;
                    xs2 = 0;
                    xs3 = 0;
                    playerSpeaking = images[actM] == playerImage;
                } else {
                    end = true;
                }

            }
            if (end) {
                if(toDesapear != null && !force) {
                    toDesapear.onDesapear();
                    TileSystem.npcs.remove(toDesapear);
                }
                toDesapear = null;
                xs = 0;
                xs2 = 0;
                xs3 = 0;
                end = false;
                ending = true;
            }
        }
        if (ending) {
            endFrames++;
            showing = false;

            if (endFrames > 10) {
                Player.canWalk = true;
            }
            if (endFrames > 60) {
                endFrames = 0;
                can = true;
                ending = false;
            }
        }
    }

    int xs = 0, xs2 = 0, xs3 = 0;

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (showing) {

            g2.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, YldGame.getImage().getHeight() / 2, YldGame.getImage().getWidth(), YldGame.getImage().getHeight() / 2);
            g.setFont(new Font("arial", 1, 12));
            g.setColor(Color.white);
            String toShow = actMessage;
            try {
                toShow = actMessage.substring(0, actS);
            } catch (Exception ignore) {
            }

            String toShow1 = "", toShow2M = "", toShow2 = "", toShow3M = "", toShow3 = "", toShow4 = "";
            toShow1 = toShow;
            if (g.getFontMetrics().stringWidth(toShow) > YldGame.getImage().getWidth() / 2 + YldGame.getImage().getWidth() / 4 - 32) {
                if (xs == 0) {
                    xs = toShow.length();
                }
                toShow2M = toShow.substring(xs);
                toShow1 = toShow.substring(0, xs);
            }
            toShow2 = toShow2M;
            if (g.getFontMetrics().stringWidth(toShow2) > YldGame.getImage().getWidth() / 2 + YldGame.getImage().getWidth() / 4 - 32) {
                if (xs2 == 0) {
                    xs2 = toShow2.length();
                }
                toShow3M = toShow2.substring(xs2);
                toShow2 = toShow2M.substring(0, xs2);
            }
            toShow3 = toShow3M;
            if (g.getFontMetrics().stringWidth(toShow3) > YldGame.getImage().getWidth() / 2 + YldGame.getImage().getWidth() / 4 - 32) {
                if (xs3 == 0) {
                    xs3 = toShow3.length();
                }
                toShow4 = toShow3.substring(xs3);
                toShow3 = toShow3M.substring(0, xs3);
            }

            char add1 = ' ';
            if (toShow2 != "" && !toShow2.startsWith(" ")) {
                add1 = '-';
            }
            char add2 = ' ';
            if (toShow3 != "" && !toShow3.startsWith(" ")) {
                add2 = '-';
            }
            char add3 = ' ';
            if (toShow4 != "" && !toShow4.startsWith(" ")) {
                add3 = '-';
            }

            try {
                if (toShow1.charAt(0) == ' ')
                    toShow1 = toShow1.substring(1);
                if (toShow2.charAt(0) == ' ')
                    toShow2 = toShow2.substring(1);
                if (toShow3.charAt(0) == ' ')
                    toShow3 = toShow3.substring(1);
                if (toShow4.charAt(0) == ' ')
                    toShow4 = toShow4.substring(1);
            } catch (Exception ignore) {
            }

            g.drawString(toShow1 + add1, 8, YldGame.getImage().getHeight() / 2 + 14 + g.getFont().getSize());
            g.drawString(toShow2 + add2, 8, YldGame.getImage().getHeight() / 2 + 14 + g.getFont().getSize() * 2);
            g.drawString(toShow3 + add3, 8, YldGame.getImage().getHeight() / 2 + 14 + g.getFont().getSize() * 3);
            g.drawString(toShow4, 8, YldGame.getImage().getHeight() / 2 + 14 + g.getFont().getSize() * 4);

            String nameString = "YOU";

            if (!playerSpeaking)
                nameString = otherName;

            g2.setColor(new Color(0, 0, 0, 100));
            if(nameString != "")
            g.fillRect(YldGame.getImage().getWidth() / 2 - g.getFontMetrics().stringWidth(nameString) / 2 - 1, YldGame.getImage().getHeight() / 2, g.getFontMetrics().stringWidth(nameString) + 2, g.getFont().getSize() + 2);

            g.setColor(Color.white);

            g.drawString(nameString, YldGame.getImage().getWidth() / 2 - g.getFontMetrics().stringWidth(nameString) / 2, YldGame.getImage().getHeight() / 2 + g.getFont().getSize());

            try {
                g.drawImage(images[actM],
                        YldGame.getImage().getWidth() / 2 + YldGame.getImage().getWidth() / 4 - images[actM].getWidth(null) / 2 + 32,
                        YldGame.getImage().getHeight() / 2 + YldGame.getImage().getHeight() / 4 - images[actM].getHeight(null) / 2, null);
            } catch (Exception ignore) {
            }


        }
        if (toHideInteract > 0 && can) {
            g.setFont(new Font("arial", 1, 12));
            String interactMessage = "Press SPACE to interact";
            g2.setColor(new Color(0, 0, 0, 100));
            g.fillRect(YldGame.getImage().getWidth() / 2 - g.getFontMetrics().stringWidth(interactMessage) / 2 - 3, 20 - 1, g.getFontMetrics().stringWidth(interactMessage) + 6, g.getFont().getSize() + 5);
            g.setColor(Color.white);
            g.drawString(interactMessage, YldGame.getImage().getWidth() / 2 - g.getFontMetrics().stringWidth(interactMessage) / 2, 20 + g.getFont().getSize());
        }

    }
}
