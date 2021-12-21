import yield.YldGame;
import yield.objects.YldObject;
import yield.util.YldInput;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NPCController extends YldObject {

    @Override
    public void update() {
        super.update();
        TileSystem.npcs.forEach(npc -> {
            if (PlayScene.player.getAxis().position.getX() > npc.x - 20 && PlayScene.player.getAxis().position.getX() < npc.x + 20 + PlayScene.player.getAxis().scale.getX()
                    && PlayScene.player.getAxis().position.getY() > npc.y - 20 && PlayScene.player.getAxis().position.getY() < npc.y + 20 + PlayScene.player.getAxis().scale.getY()) {

                try {
                    if (YldInput.isKeyPressed(KeyEvent.VK_SPACE)) {
                        if(PlayScene.dialogueBox.can)
                        PlayScene.dialogueBox.show(new String[]{"1OI AMIGO", "2OLA, TUDO BEM?", }, npc.npcIdle[0].getScaledInstance(64, 64, Image.SCALE_FAST), npc.npcIdle[3].getScaledInstance(64, 64, Image.SCALE_FAST));
                    }
                } catch (Exception ignore) {
                }

            }
        });
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        TileSystem.npcs.forEach(npc -> {
            boolean canRender = true;

            if (npc.x < -Camera.x - Tile.getWidth())
                canRender = false;
            if (npc.y < -Camera.y - Tile.getHeight())
                canRender = false;
            if (npc.x > -Camera.x + YldGame.getImage().getWidth())
                canRender = false;
            if (npc.y > -Camera.y + YldGame.getImage().getHeight())
                canRender = false;

            if (canRender) {
                if (npc.y < PlayScene.player.getAxis().position.getY())
                    setLayer(PlayScene.player.getLayer() - 1);
                else
                    setLayer(PlayScene.player.getLayer() + 1);
                npc.actImageC++;
                if (npc.actImageC >= NPC.imageCMax) {
                    npc.actImageC = 0;
                    npc.actImage++;
                }
                if (npc.actImage >= npc.npcIdle.length) {
                    npc.actImage = 0;
                }
                g.drawImage(npc.npcIdle[npc.actImage], npc.x + TileDraw.cx, npc.y + TileDraw.cy, null);
            }

        });

    }
}
