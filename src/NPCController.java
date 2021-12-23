import yield.YldGame;
import yield.objects.YldObject;
import yield.util.YldInput;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NPCController extends YldObject {

    @Override
    public void create() {
        super.create();
        setLayer(29);
    }

    @Override
    public void update() {
        super.update();
        TileSystem.npcs.forEach(npc -> {
            if (PlayScene.player.getAxis().position.getX() > npc.x - 20 && PlayScene.player.getAxis().position.getX() < npc.x + 20 + PlayScene.player.getAxis().scale.getX()
                    && PlayScene.player.getAxis().position.getY() > npc.y - 20 && PlayScene.player.getAxis().position.getY() < npc.y + 20 + PlayScene.player.getAxis().scale.getY()) {

                try {
                    if (npc.interactable) {
                        if (YldInput.isKeyPressed(KeyEvent.VK_SPACE)) {
                            if (PlayScene.dialogueBox.can)
                                PlayScene.dialogueBox.show(npc.dialog, npc.dialogBoxImage, Player.dialogImage, npc.dialogName);
                        }
                        DialogueBox.canInteract = true;
                    }

                } catch (Exception ignore) {
                }

            }
        });
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        for (NPC npc : TileSystem.npcs) {
            boolean canRender = true;

            if (npc.x < -Camera.x - Tile.getWidth() - npc.xOff)
                canRender = false;
            if (npc.y < -Camera.y - Tile.getHeight() - npc.yOff)
                canRender = false;
            if (npc.x > -Camera.x + YldGame.getImage().getWidth())
                canRender = false;
            if (npc.y > -Camera.y + YldGame.getImage().getHeight())
                canRender = false;

            if (canRender) {
                if (PlayScene.player.getAxis().position.getX() > npc.x - 20 && PlayScene.player.getAxis().position.getX() < npc.x + 20 + PlayScene.player.getAxis().scale.getX()
                        && PlayScene.player.getAxis().position.getY() > npc.y - 20 && PlayScene.player.getAxis().position.getY() < npc.y + 20 + PlayScene.player.getAxis().scale.getY()) {

                    if (npc.y + npc.yOff < PlayScene.player.getAxis().position.getY())
                        setLayer(PlayScene.player.getLayer() - 1);
                    else
                        setLayer(PlayScene.player.getLayer() + 1);

                }
                if(PlayScene.player.ctgoC > 0 || Player.lifeColorC > 0 || Player.enterColorC > 0) {
                    setLayer(PlayScene.player.getLayer() - 1);
                }

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

        }

    }
}
