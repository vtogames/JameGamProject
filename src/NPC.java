import yield.YldGame;
import yield.objects.YldObject;
import yield.util.YldAudio;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC {

    public Image npcIdle[], dialogBoxImage;
    public int x, y, actImage, actImageC, yOff, xOff;
    public static int imageCMax = 6;
    public NPCType type;
    public static BufferedImage NPC_SPRITESHEET;
    public String[] dialog;
    public String dialogName;
    public boolean disapear, interactable = true;

    public NPC(NPCType type, int x, int y) {
        this.type = type;
        this.x = x * Tile.getWidth();
        this.y = y * Tile.getHeight();
        switch (type) {
            case one:
                npcIdle = new Image[4];
                npcIdle[0] = Player.PLAYER_SPRITESHEET.getSubimage(0, 0, 16, 16);
                npcIdle[1] = Player.PLAYER_SPRITESHEET.getSubimage(16, 0, 16, 16);
                npcIdle[2] = Player.PLAYER_SPRITESHEET.getSubimage(16 * 2, 0, 16, 16);
                npcIdle[3] = Player.PLAYER_SPRITESHEET.getSubimage(16 * 3, 0, 16, 16);
                break;
            case SELF_ENEMY:
                npcIdle = new Image[4];
                npcIdle[0] = Enemy.ENEMY_SPRITESHEET.getSubimage(0, 0, 16, 16);
                npcIdle[1] = Enemy.ENEMY_SPRITESHEET.getSubimage(0, 0, 16, 16);
                npcIdle[2] = Enemy.ENEMY_SPRITESHEET.getSubimage(16, 0, 16, 16);
                npcIdle[3] = Enemy.ENEMY_SPRITESHEET.getSubimage(16, 0, 16, 16);
                dialogBoxImage = npcIdle[0].getScaledInstance(64, 64, Image.SCALE_FAST);
                dialog = new String[]{"2OIII, tu sabe onde vende eCola da boa?",
                        "1Não sou usuario de drogras pirralho sai da minha frente",
                        "2Ah é?!? seu veio careca deve ter usado muito quando criança pra sobra apenas o skeleto",
                        "1Eu não... Eu não usei drogras quando criança!",
                        "1Sempre fui um jovem muito saudavel e...",
                        "2muito viciado em cigarro pelo visto",
                        "1Ei! cigarro não é uma droga! é um vicio que me ajuda com ansiedade",
                        "2deve tar fazendo bem pro seu pulmão se não cala boca",
                        "1Argh... Se eu te fala onde vende você me deixa em paz",
                        "2Simmm, mas se já não ta em paz? tu ta morto",
                        "1EU TAVA EM PAZ ANTES DE VOCÊ APARECE!!!",
                        "2Eita veio fico doido, alguém chama o samu!",
                        "1Eu já to morto besta pra que chama o samu?",
                        "2Alguém chama um exorcitsta que essa alma tá corrompida!!!"};
                break;
            case GOBLIN_1:
                if (TileSystem.actLevel == "map1") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";

                    dialog = new String[]{"2Hmmm, I think I'm color blind...",
                            "1...",
                            "2Oh, hi buddy!", "2I'm new around here!", "2What is your name?",


                            "1My name is Toglin, the Goblin.", "1What is yours?",
                            "2My name is...", "2...", "2I forgot.",
                            "1Did you forget your name? Let me give you one!", "1You'll be Snow Boy!", "2Okay...",
                            "1What made you come here?",
                            "2I spawned right there, I don't know what I'm doing here.",
                            "2What day is today?",
                            "1Well... today is December 24th, the Christmas wait", "1Or has already been...",
                            "2What do you mean by: 'has already been'?",
                            "1You're going to find out in a little while."
                    };
                } else if (TileSystem.actLevel == "map2") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";

                    dialog = new String[]{"1To save Christmas, we need gifts.",
                            "1Because Christmas has nothing to do with family and stuff...",
                            "1It's all about gifts.",
                            "2I always knew that.",
                            "1And to get presents we need to steal them.",
                            "2Why?",
                            "1Do you want to save Christmas or not?",
                            "2Yea, but...",
                            "1Shut!"
                    };
                } else if (TileSystem.actLevel == "map4") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";

                    dialog = new String[]{"1Okay! Now just deliver the gifts to the houses in this neighborhood!",
                            "2And how is the rest of the world that is also without Christmas?",
                            "1They have money",
                            "1They can buy presents themselves.",
                            "1But now...",
                            "1Press ENTER to deliver gifts!"
                    };
                } else if (TileSystem.actLevel == "map5") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";

                    dialog = new String[]{"1But now we need to save Santa!",
                            "1Otherwise he will never get rid of eCola!",
                            "2But how are we going to do this?",
                            "1Come and I'll show you."
                    };
                }
                break;
            case GOBLIN_2:
                if (TileSystem.actLevel == "map1") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";
                    dialog = new String[]{"1This is Santa's house.", "1He hasn't been out of there in four months.",
                            "2gosh"};
                } else if (TileSystem.actLevel == "map2") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";
                    dialog = new String[]{"1Let's get the presents from this house!",
                            "2Okay",
                            "1To enter we will need a key...",
                            "1It must be somewhere in this forest...",
                            "1Search.",
                            "2Okay"
                    };
                }

                break;
            case GOBLIN_3:
                if (TileSystem.actLevel == "map1") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";
                    dialog = new String[]{"1Santa Claus is in a difficult time.",
                            "1He's addicted to eCola.",
                            "2Ahhh, I know!",
                            "2That soda that was banned in 50 countries?",
                            "1Exactly.",
                            "2aw man"};
                }
                break;
            case GOBLIN_4:
                if (TileSystem.actLevel == "map1") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";
                    dialog = new String[]{"1He is in no condition to make the Christmas.",
                            "2Is there anything we can do?",
                            "1Yea! but I'm lazy.",
                            "2...",
                            "1..."};
                }
                break;
            case GOBLIN_5:
                if (TileSystem.actLevel == "map1") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";
                    dialog = new String[]{"1That's 'the stock'.",
                            "1Here where he keeps his entire arsenal of eCola",
                            "2How does he get here without leaving his house?",
                            "1idk",
                            "2cool"};
                }

                break;

            case SANTAS_DOOR:
                npcIdle = new Image[1];
                dialogName = "";
                dialog = new String[]{
                        "2Hello!!!",
                        "2Anyone home?",
                        "1...",
                        "1Nobody answers."};
                break;
            case DOOR_LOCKED:
                npcIdle = new Image[1];
                dialogName = "";
                dialog = new String[]{
                        "1The door is locked."};
                break;
            case TRASH:
                npcIdle = new Image[1];
                npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 16, 32, 32);
                interactable = false;
                yOff = 16;
                xOff = 16;
                break;
            case SNOW_TREE:
                npcIdle = new Image[1];
                npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(32, 0, 32, 32);
                interactable = false;
                yOff = 16;
                xOff = 16;
                break;
            case GOBLIN_6:
                if (TileSystem.actLevel == "map1") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";
                    dialog = new String[]{"1This is the dump.",
                            "1And watch out, the sun is starting to melt the snow here.",
                            "2No problem.",
                            "2What could happen if a snowman goes into the blazing sun?"
                    };
                }
                break;
            case GOBLIN_7:
                if (TileSystem.actLevel == "map1") {
                    npcIdle = new Image[12];
                    npcIdle[0] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[1] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[2] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[3] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[4] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[5] = NPC.NPC_SPRITESHEET.getSubimage(0, 0, 16, 16);
                    npcIdle[6] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[7] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[8] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[9] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[10] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    npcIdle[11] = NPC.NPC_SPRITESHEET.getSubimage(16, 0, 16, 16);
                    dialogBoxImage = NPC.NPC_SPRITESHEET.getSubimage(144, 0, 16, 16).getScaledInstance(64, 64, Image.SCALE_FAST);
                    disapear = true;
                    dialogName = "TOGLIN, THE GOBLIN";
                    dialog = new String[]{"2Toglin.",
                            "1What?",
                            "2Let's save the Christmas?",
                            "1Yep.",
                            "2...",
                            "1...",
                            "2Easy like that?",
                            "1I have nothing better to do today."
                    };
                }
                break;
            case BIG_HOUSE_DOOR:
                npcIdle = new Image[1];
                disapear = true;
                dialogName = "";
                dialog = new String[]{"1Opening the door..."
                };
                break;
            case GIFT:
                System.out.println("gift");
                int n2 = SnowFilter.random.nextInt(Item.GIFT_IMAGES.length - 1);
                npcIdle = new Image[1];
                npcIdle[0] = Item.GIFT_IMAGES[n2];
                interactable = false;
                break;
        }
    }

    public void onDesapear() {
        if (type == NPCType.BIG_HOUSE_DOOR) {
            YldGame.switchScene("DoorScene");
        }
    }

}
