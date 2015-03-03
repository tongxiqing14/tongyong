package Entry;

import common.*;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import iptvNet.IptvNetException;
import iptvNet.NetHander;
import org.json.me.JSONException;
import org.json.me.JSONObject;
import screens.*;

import download.Download;

public class LWGameCanvas extends Canvas implements Runnable {

    private static Vector currentScreenVer = new Vector();// 当前的屏幕列表
    public static LWGameMidlet rmidlet;
    Thread th;
    public static boolean isExit = false;
    public static Image imgBuff;

    public boolean isImageBuff=false;
    public static boolean isOnOK=false;

    public static int ret = 0;
    public static int chest_num = 0;
    public static double sum_enemy_hp_num = 0;
    public static double sum_hero_hp_num = 0;
    public static double sum_fight_num = 0;
    public static double sum_enemy_fight_num = 0;

    public LWGameCanvas(LWGameMidlet mid) {
        rmidlet = mid;

        if(isImageBuff){
            imgBuff=Image.createImage(Globe.SW,Globe.SH);
        }
        this.setFullScreenMode(true);
        th = new Thread(this);
        th.start();
    }


    public void initGame() {
        Globe.download = new Download();

        isExit = false;
        Globe.getCorrectFont();
        currTime = System.currentTimeMillis();

        initNet();
        addScreen(new WorriesFightScreen(GameVariables.MENU_SCREEN_ID));
    }

    public static boolean isShowNetInfo=true;
    public void initNet()
    {
        isShowNetInfo=true;
        NetInfo.netHander = new NetHander(rmidlet);

        try {
            ret = NetInfo.netHander.getGameInfo();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IptvNetException e) {
            e.printStackTrace();
        }

//        if(rmidlet.getAppProperty("IsActiveOnOK").equals("true")){
//            isOnOK=true;
//        }
//        if (rmidlet.getAppProperty("IsAutoTopUp").equals("true")) {
//            Globe.isAutoTopUp = true;
//        }

//        try {
//            Globe.token = NetInfo.netHander.getBalance();
//
//            if(!NetInfo.netHander.getGameData(-1).equals("init050333")){
//                GameVariables.maxMapStage = 1;
//                NetInfo.updateMapStage();
//
//                NetInfo.initData();
//                NetInfo.netHander.saveScore(0, 0);
//                NetInfo.netHander.saveGameData("init050333", -1);
//            }else{
//                for (int i = 0; i < 5; i++) {
//                    NetInfo.getHeroData(i);
//                }
//            }
//
//            if(!NetInfo.netHander.getGameData(-2).equals("init2")){    //for no inference to the old users' info
//                NetInfo.netHander.saveGameData("init2", -2);
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//
//            }else {
//                NetInfo.getStageInfo();
//            }
//
//            NetInfo.getMapStage();
//            NetInfo.getGameScore();
//            GameVariables.nickName=NetInfo.netHander.getNickName();
//        } catch (IptvNetException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        isShowNetInfo = false;

    }

    public long currTime = 0;
    public long sleepTime = 0;

    public int initFrame=5;
    public static long memoryFree = 0;
    public static long memoryTotal = 0;
    public static long memoryUsed = 0;
    public static boolean isCheckMemory=false;
    //当前选择项的索引
    public static int menuItemIndex;
    /**
     * 垂直或水平切换菜单项
     *
     * @param itemsCount
     *            the items count
     * @param keyCodeMinus 往上或往左的按键
     * @param keyCodeAdd 往右或往下的按键
     */
    public static void  switchMenuItemVH(int itemsCount,int keyCodeMinus,int keyCodeAdd) {
        if (iskeyPressed(keyCodeMinus)) {
            menuItemIndex--;
            keyReset();
        } else if (iskeyPressed(keyCodeAdd)) {
            menuItemIndex++;
            keyReset();
        }
        menuItemIndex = (menuItemIndex + itemsCount) % itemsCount;
    }

    public static void  switchMenuItemVX(int itemsCount,int keyCodeMinus,int keyCodeAdd) {
        if (iskeyPressed(keyCodeMinus)) {
            menuItemIndex = 0;
            keyReset();
        } else if (iskeyPressed(keyCodeAdd)) {
            menuItemIndex = 1;
            keyReset();
        }
        menuItemIndex = (menuItemIndex + itemsCount) % itemsCount;
    }

    public void run() {

        while (!isExit) {

            long starTime = System.currentTimeMillis();

            if(initFrame>0){
                if(initFrame==1){
                    initGame();
                }
                initFrame--;
            }
            update();
            repaint();

            long endTime = System.currentTimeMillis();
            long useTime = endTime-starTime;
            if (useTime < Globe.SLEEP_TIME ) {
                sleepTime = Globe.SLEEP_TIME - useTime;
                try {
                    if(sleepTime>=0){
                        Thread.sleep(sleepTime);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sleepTime = 0;
            }
            if (isCheckMemory) {
                memoryFree = Runtime.getRuntime().freeMemory();
                memoryTotal = Runtime.getRuntime().totalMemory();
                memoryUsed = memoryTotal - memoryFree;
            }
        }
        if (isExit) {
            freeResource();
            rmidlet.exit();
        }
    }

    public void keyReleased(int keyCode) {
        Globe.keyBuff = 0;
        canClearKeycode = false;
    }

    public static void keyReset() {
        if (canClearKeycode == true) {
            Globe.keyBuff = 0;
            canClearKeycode = false;
        }
    }

    private static boolean canClearKeycode = false;

    public static boolean iskeyPressed(int keyCode) {
        if ((Globe.keyBuff & keyCode) == keyCode) {
            canClearKeycode = true;
            return true;
        } else {
            return false;
        }
    }

    public void keyPressed(int keyCode) {

        keyPressedImp(keyCode);
    }

    public void keyPressedImp(int keyCode) {
        keyReset();
        if (keyCode == Globe.KEY_UP) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_UP;
        } else if (keyCode == Globe.KEY_DOWN) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_DOWN;
        } else if (keyCode == Globe.KEY_LEFT) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_LEFT;
        } else if (keyCode == Globe.KEY_RIGHT) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_RIGHT;
        } else if (keyCode == Globe.KEY_OK) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_OK;
        } else if (keyCode == Globe.KEY_SOFT_L) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_SOFT_L;
        } else if (keyCode == Globe.KEY_SOFT_R) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_KEY3;
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_SOFT_R;
        } else if (keyCode == Globe.KEY_KEY3 || keyCode == Globe.KEY_KEY4) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_SOFT_R;
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_KEY3;
        } else if (keyCode == Globe.KEY_0) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_0;
        } else if (keyCode == Globe.KEY_1) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_1;
        } else if (keyCode == Globe.KEY_2) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_2;
        } else if (keyCode == Globe.KEY_3) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_3;
        } else if (keyCode == Globe.KEY_4) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_4;
        } else if (keyCode == Globe.KEY_5) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_5;
        } else if (keyCode == Globe.KEY_6) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_6;
        } else if (keyCode == Globe.KEY_7) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_7;
        } else if (keyCode == Globe.KEY_8) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_8;
        } else if (keyCode == Globe.KEY_9) {
            Globe.keyBuff = Globe.keyBuff | Globe.M_KEY_9;
        }
    }


    public int gcFrm = 0;
    public void gc() {
        gcFrm++;
        if (gcFrm == 100) {
            gcFrm = 0;
            System.gc();
        }
    }
    public static void addScreen(Screen screen) {

        screen.init();
        currentScreenVer.addElement(screen);
        setActive(screen, true);
        keyReset();
    }
    //设置上一层screen的更新条件
    public static void addScreen(Screen screen,boolean isActive)
    {
        ((Screen)currentScreenVer.elementAt(currentScreenVer
                .size() - 1)).screenType=Screen.SCREENTYPE_DRAW;
        screen.init();
        currentScreenVer.addElement(screen);
        screen.setActive(isActive);
        keyReset();
    }
    public static void setScreenType(int screenId)
    {
        for (int i = 0; i < currentScreenVer.size(); i++) {
            ((Screen) currentScreenVer.elementAt(i)).screenType=Screen.SCREENTYPE_ALL;
            break;
        }
    }
    public static void deleteScreen(int screenId) {
        for (int i = 0; i < currentScreenVer.size(); i++) {
            if (((Screen) currentScreenVer.elementAt(i)).screenId == screenId) {
                deleteScreen((Screen) currentScreenVer.elementAt(i));
                break;
            }
        }
    }

    public static void deleteScreen(Screen screen) {
        currentScreenVer.removeElement(screen);
        if (currentScreenVer.size() > 0) {
            setActive((Screen) currentScreenVer.elementAt(currentScreenVer
                    .size() - 1), true);

            ((Screen) currentScreenVer.elementAt(currentScreenVer
                    .size() - 1)).screenType=Screen.SCREENTYPE_ALL;

        }
        screen.clear();
//		currentScreenVer.removeElement(screen);
    }

    public static void setActive(Screen screen, boolean isActive) {
        for (int i = 0; i < currentScreenVer.size(); i++) {
            ((Screen) currentScreenVer.elementAt(i)).setActive(false);
        }
        screen.setActive(isActive);
    }

    public void freeResource() {
        for (int i = 0; i < currentScreenVer.size(); i++) {
            ((Screen) currentScreenVer.elementAt(i)).clear();
        }
        currentScreenVer.removeAllElements();
    }

    public static void switchToScreen(Screen toScreen) {
        for (int i = 0; i < currentScreenVer.size(); i++) {
            deleteScreen((Screen) currentScreenVer.elementAt(i));
//			System.gc();
            i--;

        }
//		GameVariables.tipsRandomNum=;
        addScreen(toScreen);
        keyReset();
    }

    public void update() {

        for (int i = 0; i < currentScreenVer.size(); i++) {
//			if (((Screen) currentScreenVer.elementAt(i)).screenType==Screen.SCREENTYPE_ALL) {
            if (((Screen) currentScreenVer.elementAt(i)).getActive()) {
                ((Screen) currentScreenVer.elementAt(i)).update();
            }
        }
    }

    protected void paint(Graphics g) {
        if(isImageBuff){
            draw(imgBuff.getGraphics());
            g.drawImage(imgBuff, 0, 0, Graphics.TOP | Graphics.LEFT);
        }else{
            draw(g);
        }
        if(isShowNetInfo){
//            g.setColor(255,255,255);
//            g.fillRect(0,200,640,150);
//            g.setColor(0,0,0);
//            g.drawImage(Globe.getImage("menu/bg2.jpg"), 0, 0, 20);
//            g.drawString("网络正在初始化,请稍后......", Globe.SW/2, Globe.SH/2, Globe.ANCHOR_T_H);
        }
        if (isCheckMemory) {
            g.setColor(255,255,255);
            g.fillRect(0, 0, 200, 150);
            g.setColor(0xff0000);
            g.setFont(Globe.currentFont);
            g.drawString("总内存 = " + memoryTotal, 0, 0, Globe.ANCHOR_T_L);
            g.drawString("使用内存 = " + memoryUsed, 0, 20, Globe.ANCHOR_T_L);
            g.drawString("剩余内存 = " + memoryFree, 0, 40, Globe.ANCHOR_T_L);
        }
    }

    public void draw(Graphics g) {
        g.setFont(Globe.currentFont);
        for (int i = 0; i < currentScreenVer.size(); i++) {
            if (((Screen) currentScreenVer.elementAt(i)).getActive()) {
                ((Screen) currentScreenVer.elementAt(i)).draw(g);
            }
        }
    }
    public static Image commonBgImage;
    public static Image commonTopImage;
    public static Image commonDownImage;
    public static Image commonButterFly;
    public static void drawBg(Graphics g){
        if(commonBgImage!=null){
            g.drawImage(commonBgImage, 0, 0, 20);
        }

    }
    public static void drawTOPandDown(Graphics g)
    {
        if(commonTopImage!=null)
        {
            g.drawImage(commonTopImage, 0, 0, Graphics.TOP|Graphics.LEFT);

        }
        if(commonDownImage!=null)
        {
            g.drawImage(commonDownImage, 0, Globe.SH-commonDownImage.getHeight(), Graphics.TOP|Graphics.LEFT);
        }
        if(commonButterFly!=null)
        {
            g.drawImage(commonButterFly, 0, Globe.SH-commonButterFly.getHeight(), Graphics.TOP|Graphics.LEFT);
            g.drawRegion(commonButterFly, 0, 0, commonButterFly.getWidth(), commonButterFly.getHeight(), Globe.TRANS_MIRROR, Globe.SW-commonButterFly.getWidth(), Globe.SH-commonButterFly.getHeight(), Graphics.TOP|Graphics.LEFT);
        }
    }
    public static int tipRandNum=0;
    public static void drawDownloadBg(Graphics g) {
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, 640, 530);
        g.setColor(255, 255, 255);
        g.setFont(Globe.currentFont);
    }
}
