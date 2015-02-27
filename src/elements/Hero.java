package elements;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import motion.Motion;
//import screens.GamingScreen;

import common.GameVariables;
import common.Globe;
import common.Warrior;

public class Hero extends Warrior {

    public static int[] gotHeroID = { 0, 1, 2, 3 };
    public static String[] heroName = { "美琪", "美雪", "小蓝", "游乐" };
    public static int[] initHeroChongTime = { 5, 6, 6, 6 };
    public static int[] initHeroInvincibleTime = { 5, 6, 6, 6 };


    public static boolean[] isGot={true,false,false,false};
    //	public static int[] initHeroSpeedUp={90,90,100,100};
//	public static int[] initHeroProtect={500,500,600,600};
    public static int[] initHeroXiangYun={2,2,2,2};
    public static int[] initHeroSuckStar={5,5,5,5};

    public static int[] initHeroChongTimeLV={1,1,1,1};
    public static int[] initHeroInvincibleTimeLV={1,1,1,1};
    public static int[] initHeroXiangYunLV={1,1,1,1};
    public static int[] initHeroSuckStarLV={1,1,1,1};

    public static int[] heroChongTime={0,0,0,0};
    public static int[] heroInvincibleTime={0,0,0,0};
    public static int[] heroXiangYun={0,0,0,0};
    public static int[] heroSuckStar={0,0,0,0};

    public static int[] heroChongTimeLV={0,0,0,0};
    public static int[] heroInvincibleTimeLV={0,0,0,0};
    public static int[] heroXiangYunLV={0,0,0,0};
    public static int[] heroSuckStarLV={0,0,0,0};


    // 人物动作定义
    public int stage = STAGE_MOVE;// 初始动作定位空闲状态
    public static final int STAGE_STANCE = 0;
    public static final int STAGE_MOVE = 1;
    public static final int STAGE_JUMP = 2;
    public static final int STAGE_BEATTACK = 3;
    public static final int STAGE_DEADING = 4;
    public static final int STAGE_DEAD = 5;
    public static final int STAGE_CHONG = 6;
    public static final int STAGE_INVINCIBLE = 7;
    public static final int STAGE_XIJIN = 8;
    public static final int STAGE_BUFF = 9;
    public static final int STAGE_BOUNCE = 10;

    public static int maxChongTime;
    public static int maxInvincibleTime;
    public int chongTime;
    public int invincibleTime;
    public static Image imgHuDun;

    // 人物type
    public static final byte HERO_MEIQI = 0;
    public static final byte HERO_MEIXUE = HERO_MEIQI + 1;
    public static final byte HERO_XIAOLAN = HERO_MEIXUE + 1;
    public static final byte HERO_YOULE = HERO_XIAOLAN + 1;

    // 跳跃
    protected float slideSpeed_y = 0; // Y方向滑行速度
	public static float G_Y = 1.9f;// 重力Y方向加速度

    // 人物特效时间

    public int bodyWidth, bodyHeight;// 身体的宽高
    public int lifeCurrent = lifeMax;// 当前生命
    public Motion motion;
    //	public Motion chongMotion;
    public Motion deadMotion;
    public Motion chongfengMotion;
    public Motion xinjinMotion;
    public boolean isfightingdead = false;// 碰到怪后死亡

    public static Pet pet;
    public static Pet pet2;
	/*
	 * 0 无 1 1个宠物 2 2个宠物
	 */

    public Hero(int x, int y, int type) {
        super(x, y, type);
        isChong = false;
        isInvincible = false;
        isXiJin = false;
        canSetInvincible = true;
        if (GameVariables.initPet[0][0] == 1) {
            pet2 = new Pet(GameVariables.initPet[0][1] + GameVariables.isLandPetGot.length, x + 80, y - 180);
            G_Y -= 0.4;
        }
        if (GameVariables.initPet[1][0] == 1) {
            pet = new Pet(GameVariables.initPet[1][1], x - 100, y);
            G_Y -= 0.4;
        }
        switch (type) {
            case 0:
                bodyWidth = 66;
                bodyHeight = 70;
                break;
            case 1:
                bodyWidth = 66;
                bodyHeight = 70;
                break;
            case 2:
                bodyWidth = 66;
                bodyHeight = 70;
                break;
            case 3:
                bodyWidth = 66;
                bodyHeight = 70;
                break;
        }

        motion = new Motion("/game/hero" + type + "/hero" + type + ".anu", posX, posY);
        deadMotion = new Motion("/game/siwang/siwangyan.anu", posX, posY);
        chongfengMotion = new Motion("/effect/chongfeng.anu", posX, posY);
    }

    public boolean isJump = false;
    int moveFrame;
    public static boolean isChong = false;
    public static boolean isInvincible = false;
    public static boolean canSetInvincible;
    public static boolean isXiJin = false;
    private long chongBuffTime;
    private long InvincibleBuffTime;
    int oldY;
    int intervalFrame = 0;
    int motionState = 0;

    public void setState(int stage) {
        //System.out.println("setState------" + stage + " getX "+ tempX + " getY "+ tempY);
        super.setState(stage);
    }
    public void setDead(boolean isDead) {
        if (state != STAGE_BUFF) {
            super.setDead(isDead);
        }
    }
    public void update() {
        if (!isDead) {
            if (null != pet) {
                pet.update();
            }
            if (null != pet2) {
                pet2.update();
            }
            if (state == STAGE_DEAD){
                isJump = false;
            }
            if (isJump && state != STAGE_BOUNCE && state != STAGE_BUFF && state!= STAGE_CHONG&& state!= STAGE_INVINCIBLE&& state!= STAGE_XIJIN) {
                state = STAGE_JUMP;
            }
            switch (getStage()) {
                case STAGE_MOVE:
                    isJump = false;
                    motionState = 0;
                    if (oldY > 0) {
                        setPosition(tempX, oldY);
                        oldY = 0;
                        safeTime();
                    }
                    break;
                case STAGE_CHONG:
                    isJump = false;
                    motionState = 1;
                    maxChongTime = heroChongTime[GameVariables.heroIndex] + updateChongTime;
                    chongBuffTime = System.currentTimeMillis();
                    setState(STAGE_STANCE);
                    isChong = true;
                    oldY = 353;
                    setPosition(tempX, 253);
                    canSetInvincible = false;
                    break;
                case STAGE_STANCE:

                    break;
                case STAGE_DEAD:
                    isJump = false;
                    //System.out.println("deading------");
//				tempX -= speed;
                    slideSpeed_y = 0;
                    slideSpeed_y += 10;
                    setState(STAGE_DEADING);
                    break;
                case STAGE_DEADING:
                    slideSpeed_y += 3;// 变换速度
                    tempY += slideSpeed_y;// 变换速度
                    if (tempY >= 540) {
                        tempY = Globe.SH * 2 / 3 + 2;
                        isDead = true;
                        canSetInvincible = false;
                    }
                    setPosition(tempX, tempY);
                    break;
                case STAGE_BEATTACK:
                    isDead = true;
                    isfightingdead = true;
                    break;
                case STAGE_INVINCIBLE:
                    updateInvincibleTime = 0;
                    if (null == imgHuDun) {
                        imgHuDun = Globe.download.creatImage("effect/hudun.png");
                    }
                    isInvincible = true;
                    setState(STAGE_MOVE);
                    InvincibleBuffTime = System.currentTimeMillis();
                    maxInvincibleTime += heroInvincibleTime[GameVariables.heroIndex] + updateInvincibleTime;
                    break;
                case STAGE_XIJIN:
                    isXiJin = true;
                    xinjinMotion = new Motion("/effect/xijin.anu", posX, posY);
                    xinjinMotion.setId(0, 3);
                    setState(STAGE_STANCE);
                    break;
                case STAGE_JUMP:
                    slideSpeed_y += G_Y;// 变换速度
                    tempY += slideSpeed_y;// 变换速度
                    if (tempY >= 353) {
                        tempY = Globe.SH * 2 / 3 + 2;
                        setState(STAGE_MOVE);
                        isJump = false;
                    }
                    setPosition(tempX, tempY);
                    break;
                case STAGE_BUFF:
                    //state = STAGE_STANCE;
                    isJump = false;
                    tempY = Globe.SH * 2 / 3 + 2;
                    setPosition(tempX, tempY);
                    if (!isBuff) {
                        safeTime();
                    }
                    break;
                case STAGE_BOUNCE:
                    //System.out.println("STAGE_BOUNCE");
                    setState(STAGE_JUMP);
                    isJump = true;
                    slideSpeed_y = 0;
                    slideSpeed_y += -10;
                    break;
            }
            if (isInvincible) {
                updateInvibleTime();
            }
            if (isChong) {
                updateChongTime();
                chongfengMotion.keepId(0);
                chongfengMotion.update(tempX, tempY);
            } else {
//                GamingScreen.bgMoveFram = 10;
            }
            if (state != STAGE_DEADING) {
                motion.keepId(motionState);
            }
            motion.update(tempX, tempY);
            if (null != xinjinMotion) {
                if (xinjinMotion.isEnd()) {
                    isXiJin = false;
                    xinjinMotion = null;
                } else {
                    xinjinMotion.update(tempX + bodyWidth / 2, tempY - bodyHeight / 2);
                }
            }
            if (!isJump && state != STAGE_DEADING) {
                switch (Globe.keyBuff) {
                    case Globe.M_KEY_UP:
                        setState(STAGE_JUMP);
                        isJump = true;
                        slideSpeed_y = 0;
                        slideSpeed_y += -23;
                        Globe.keyBuff = 0;
                        isChong = false;
                        chongTime = 0;
                        break;
                }
            }
            intervalFrame++;
            if (intervalFrame % 6 == 0) {
//                GamingScreen.achieveLen += GamingScreen.bgMoveFram / 5;
            }
        }
        // 判断碰到怪时播放死亡动画
        if (isfightingdead) {
            if (deadMotion != null) {
                deadMotion.keepId(0);
                deadMotion.update(tempX, tempY);
            }
        }

    }
    boolean isBuff = false;
    private void safeTime() {
        isBuff = true;
        //System.out.println("safeTime od buff-----");
        updateInvincibleTime = 0;
        if (null == imgHuDun) {
            imgHuDun = Globe.download.creatImage("effect/hudun.png");
        }
//        GamingScreen.numFillYun = 2;
//        GamingScreen.isFillYun = true;
        isInvincible = true;
        InvincibleBuffTime = System.currentTimeMillis();
        maxInvincibleTime = 1;
    }

    private int updateInvincibleTime = 0;

    private void updateInvibleTime() {
        invincibleTime = (int) (System.currentTimeMillis() - InvincibleBuffTime) / 1000;
        if (updateInvincibleTime >= 0) {
            updateInvincibleTime = maxInvincibleTime - invincibleTime;
        } else {
            isInvincible = false;
            maxInvincibleTime = 1;
            if (!isChong) {
                isBuff = false;
                state = STAGE_MOVE;
            }
        }
    }

    private int updateChongTime = 0;

    private void updateChongTime() {
        chongTime = (int) (System.currentTimeMillis() - chongBuffTime) / 1000;
        if (updateChongTime >= 0) {
            updateChongTime = maxChongTime - chongTime;
        } else {
//            GamingScreen.numFillYun = 2;
//            GamingScreen.isFillYun = true;
            updateChongTime = 0;
            isChong = false;
            setState(STAGE_MOVE);
        }
    }

    int tempX, tempY;

    public void draw(Graphics g) {
        if (!isDead) {
            switch (type) {
                case HERO_MEIQI:
                    tempX = getPosX();
                    tempY = getPosY();
                    break;
                case HERO_MEIXUE:
                    tempX = getPosX();
                    tempY = getPosY();
                    break;
                case HERO_XIAOLAN:
                    tempX = getPosX();
                    tempY = getPosY();
                    break;
                case HERO_YOULE:
                    tempX = getPosX();
                    tempY = getPosY();
                    break;
            }

            if (null != pet) {
                pet.draw(g);
            }
            if (null != pet2) {
                pet2.draw(g);
            }
            motion.draw(g);
            if (null != xinjinMotion) {
                xinjinMotion.draw(g);
            }
            if (isChong && chongfengMotion != null) {
                chongfengMotion.draw(g);
//                Globe.drawNum(g, updateChongTime, tempX - bodyWidth / 2, tempY - bodyHeight - 30, GamingScreen.imgNUM, 1);
            }
            if (isInvincible) {
                g.drawImage(imgHuDun, tempX - imgHuDun.getWidth() / 2 + bodyWidth, tempY - imgHuDun.getHeight() / 2, Graphics.HCENTER
                        | Graphics.VCENTER);
//                Globe.drawNum(g, updateInvincibleTime, tempX - bodyWidth, tempY - bodyHeight - 30, GamingScreen.imgNUM, 1);
            }
        } else if (deadMotion != null && isfightingdead) {
            deadMotion.draw(g);
        }

    }
}
