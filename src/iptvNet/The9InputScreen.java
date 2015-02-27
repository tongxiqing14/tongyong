package iptvNet;



import java.io.IOException;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import Entry.LWGameCanvas;
import common.*;
import org.json.me.JSONException;

import elements.Hero;
import screens.*;


public class The9InputScreen extends Screen{
    private static int SCREEN_W = 640;
    private static int SCREEN_H = 530;

    private static int KEY_UP = -1;
    private static int KEY_DOWN = -2;
    private static int KEY_LEFT = -3;
    private static int KEY_RIGHT = -4;

    private static int KEY_0 = 48;
    private static int KEY_1 = 49;
    private static int KEY_2 = 50;
    private static int KEY_3 = 51;
    private static int KEY_4 = 52;
    private static int KEY_5 = 53;
    private static int KEY_6 = 54;
    private static int KEY_7 = 55;
    private static int KEY_8 = 56;
    private static int KEY_9 = 57;
    private static int KEY_OK = -5;
    private static int KEY_SOFT_R0 = -7;// right soft key
    private static int KEY_SOFT_R1 = 7;// right soft key
    private static int KEY_SOFT_R2 = -31;// right soft key
    private static int KEY_SOFT_R3 = -8;// right soft key


    private static int INPUT_STRING_MAX_LENGHT=8;

    private Image img[];

    private Image shibcg_bg2Img;

    private final int STAGE_INPUT_RECHARGE = 0;//充值界面
    private final int STAGE_INPUT_PASSWORD = 1;//输入童锁界面
    private int stage = STAGE_INPUT_RECHARGE;

    private NetHander netHander;
    private boolean hasVerificationCode = false;
    private boolean isPayEnd = false;
    private boolean isPayOk = false;
    private String inputString = "";

    private String question;
    private String answer;

    /**
     * 道具价格
     */
    private int price = 0;
    /**
     * 需要充值
     */
    private int rechargeNum = 0;
    private String wareName = "";

    private String showInfo = "";
    private boolean isShow = false;
    private int showInfoIndex = 0;
    private long showStartTime = 0;


    /**
     * 选择的焦点
     */
    private int selectIndex = 0;
    private int selectIndexUD = 0;

    public static Screen returnScreen;

    private String payStage = "";

    private Image bgImg;

    public The9InputScreen(NetHander nHander,int price,int rechargeNum,String wareName){
        super(0);
        this.netHander = nHander;
        this.price = price;
        this.rechargeNum = rechargeNum;
        this.wareName = wareName;
        init();
    }

    public void update() {
        // TODO Auto-generated method stub
        if(isPayEnd){
            return;
        }

        if(payStage.equals("return")||payStage.equals("failure")||payStage.equals("success")){
//            if(LWGameCanvas.iskeyPressed(Globe.M_KEY_OK)){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(payStage.equals("return")||payStage.equals("failure")){
                setPayEnd(true);
                setPayOK(false);
                LWGameCanvas.keyReset();
                LWGameCanvas.deleteScreen(this);
                LWGameCanvas.setActive(returnScreen, true);
            }else if(payStage.equals("success")){
                Globe.token=0;
                setPayEnd(true);
                paySuccess();
            }
//            }
        } else {
            switch(stage){
                case STAGE_INPUT_RECHARGE:
                    updateStageRecharge();
                    break;
                case STAGE_INPUT_PASSWORD:
                    updateStagePassword();
                    break;
            }
            updateShowInfo();
        }
        LWGameCanvas.keyReset();
    }

    //定义字体??
    private Font largeFont=Font.getFont(Font.FACE_SYSTEM,Font.STYLE_BOLD,Font.SIZE_LARGE);
    private Font mediumFont=Font.getFont(Font.FACE_SYSTEM,Font.STYLE_BOLD,Font.SIZE_MEDIUM);

    public void draw(Graphics g) {
        if(isPayEnd){
            return;
        }
        if(shibcg_bg2Img!=null){
            g.drawImage(shibcg_bg2Img, 180, 150, 20);
            g.drawRegion(shibcg_bg2Img, 0,0,shibcg_bg2Img.getWidth(),shibcg_bg2Img.getHeight(),
                    2,150+shibcg_bg2Img.getWidth(), 150+shibcg_bg2Img.getHeight()/2, Graphics.LEFT|Graphics.VCENTER);
        }
        if(payStage.equals("success")){
            g.setColor(0x030303);
            g.setFont(mediumFont);
//            g.drawImage(bgimageReturn, Globe.SW >> 1, Globe.SH >> 1, Graphics.HCENTER|Graphics.VCENTER);
            g.drawString("消费成功！", 290, 230 + Globe.currentFont.getHeight(), Graphics.TOP|Graphics.LEFT);
//            g.drawImage(consumeSuccessImg, 290, 250 + Globe.currentFont.getHeight(), Graphics.HCENTER|Graphics.VCENTER);
//            g.drawImage(confirmImageBtn, 270, 300, 20);
        }else if(payStage.equals("failure")) {
            g.setColor(0x030303);
//            g.setFont(mediumFont);
//            g.drawImage(bgimageReturn, Globe.SW >> 1, Globe.SH >> 1, Graphics.HCENTER|Graphics.VCENTER);
            g.drawString("消费失败，请去大厅充值！", 215, 230 + Globe.currentFont.getHeight(), Graphics.TOP|Graphics.LEFT);
//            g.drawImage(consumeFailureImg, 270, 250 + Globe.currentFont.getHeight(), Graphics.HCENTER|Graphics.VCENTER);
//            g.drawImage(confirmImageBtn, 270, 300, 20);
        }else if(payStage.equals("return")) {
            g.setColor(0x030303);
//            g.setFont(mediumFont);
//            g.drawImage(bgimageReturn, Globe.SW >> 1, Globe.SH >> 1, Graphics.HCENTER|Graphics.VCENTER);
            g.drawString("消费失败，请去大厅充值！", 215, 230 + Globe.currentFont.getHeight(), Graphics.TOP|Graphics.LEFT);
//            g.drawImage(consumeFailureImg, 270, 250 + Globe.currentFont.getHeight(), Graphics.HCENTER|Graphics.VCENTER);
//            g.drawImage(confirmImageBtn, 270, 300, 20);
        }else {
            g.drawImage(bgImg, SCREEN_W>>1, SCREEN_H>>1, Graphics.RIGHT|Graphics.VCENTER);
            g.drawRegion(bgImg, 0,0,bgImg.getWidth(),bgImg.getHeight(),
                    2,SCREEN_W>>1, SCREEN_H>>1, Graphics.LEFT|Graphics.VCENTER);
            switch(stage){
                case STAGE_INPUT_RECHARGE:
                    drawRecharge(g);
                    break;
                case STAGE_INPUT_PASSWORD:
                    drawPassword(g);
                    break;
            }
            drawShowInfo(g);
        }
    }

    private void setPayEnd(boolean isEnd){
        isPayEnd = isEnd;
        clear();
    }

    private void setPayOK(boolean isOk){
        isPayOk = isOk;
    }

    public void clear() {
        // TODO Auto-generated method stub
        if(img!=null){
            for(int i = 0;i<img.length;i++){
                img[i] = null;
            }
            img = null;
        }
        shibcg_bg2Img = null;
//        bgimageReturn = null;
//        confirmImageBtn = null;
//        bgImg = null;
        Runtime.getRuntime().gc();
    }

    public void init() {
        // TODO Auto-generated method stub
        inputString = "";
        hasVerificationCode = false;
        setPayEnd(false);
        setPayOK(false);
        try {
            hasVerificationCode = netHander.hasVerificationCode();
            question = netHander.getQuestion();
            answer = netHander.getAnswer();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IptvNetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stage = STAGE_INPUT_RECHARGE;
//			if(hasVerificationCode){
//				selectIndexUD = 0;
//			}else{
        selectIndexUD = 1;
        selectIndex = 1;
//			}
        img = new Image[8];
        try {
            for (int i = 0; i < img.length; i++) {
                img[i]=Image.createImage("/the9Input/"+i+".png");
            }
            bgImg = Image.createImage("/the9Input/bg.png");

        } catch (IOException e) {
            e.printStackTrace();
        }

        shibcg_bg2Img = Globe.getImage("final/shibcg_bg2.png");

    }

    private void updateStageRecharge(){
        if(isShow){
            return;
        }
        if(LWGameCanvas.iskeyPressed(Globe.M_KEY_LEFT)){
            if(selectIndexUD==1){
                selectIndex--;
            }

        }else if(LWGameCanvas.iskeyPressed(Globe.M_KEY_RIGHT)){
            if(selectIndexUD==1){
                selectIndex++;
            }
        }else if(LWGameCanvas.iskeyPressed(Globe.M_KEY_UP)){
            if(hasVerificationCode){
                if(selectIndexUD==1){
                    selectIndexUD--;
                }
            }
        }else if(LWGameCanvas.iskeyPressed(Globe.M_KEY_DOWN)){
            if(hasVerificationCode){
                if(selectIndexUD==0){
                    selectIndexUD++;
                }
            }
        }
        selectIndex = (selectIndex+2)%2;
        if(selectIndexUD==0){
            if(hasVerificationCode){
                int keyTemp[] = {Globe.M_KEY_0,Globe.M_KEY_1,Globe.M_KEY_2,Globe.M_KEY_3,Globe.M_KEY_4,Globe.M_KEY_5,Globe.M_KEY_6,Globe.M_KEY_7,Globe.M_KEY_8,Globe.M_KEY_9};
                for(int i = 0;i<10;i++){
                    if(LWGameCanvas.iskeyPressed(keyTemp[i])){
                        if(inputString.length()<INPUT_STRING_MAX_LENGHT){
                            inputString+=(i+"");
                        }
                        break;
                    }
                }
            }
            if(LWGameCanvas.iskeyPressed(Globe.M_KEY_OK)){
                if(hasVerificationCode){
                    if(inputString.equals(answer)){
                        inputString = "";//第一次调用,password为空，如果成功，表示没有童锁
                        payPassWord();
                    }else{
                        setShow("输入不正确，请重新输入");
                    }
                }else{
                    payPassWord();
                }
            }
        }

        if(LWGameCanvas.iskeyPressed(Globe.M_KEY_OK)){
            if(selectIndexUD==1){
                if(selectIndex==0){
                    if(hasVerificationCode){
                        if(inputString.equals(answer)){
                            inputString = "";//第一次调用,password为空，如果成功，表示没有童锁
                            payPassWord();
                        }else{
                            setShow("输入不正确，请重新输入");
                        }
                    }else{
                        payPassWord();
                    }
                }else if(selectIndex==1){
                    payStage="return";
                }
            }
        }else if(LWGameCanvas.iskeyPressed(Globe.M_KEY_SOFT_R)){
            if(selectIndexUD==0){
                if(hasVerificationCode){//如果有验证码
                    if(inputString.length()>0){
                        inputString = inputString.substring(0, inputString.length()-1);
                    }
                }
            }
        }
        LWGameCanvas.keyReset();
    }

    private void paySuccess(){
        if(Globe.confirmScreenId == 1){          //speed up
//            if(SelectStageScreen.stageSelectIndex==0){
//                GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                GamingScreen.dStepX = 60;
//                GamingScreen.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==1){
//                GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                GamingScreen2.dStepX = 60;
//                GamingScreen2.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==2){
//                GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                GamingScreen3.dStepX = 60;
//                GamingScreen3.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==3){
//                GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                GamingScreen4.dStepX = 60;
//                GamingScreen4.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==4){
//                GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                GamingScreen5.dStepX = 60;
//                GamingScreen5.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==5){
//                GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                GamingScreen6.dStepX = 60;
//                GamingScreen6.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==6){
//                GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                GamingScreen7.dStepX = 60;
//                GamingScreen7.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==7){
//                GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                GamingScreen8.dStepX = 60;
//                GamingScreen8.hudunTime = 100;
//            }
        }else if(Globe.confirmScreenId == 2){
//            if(SelectStageScreen.stageSelectIndex==0){
//                GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                GamingScreen.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==1){
//                GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                GamingScreen2.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==2){
//                GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                GamingScreen3.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==3){
//                GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                GamingScreen4.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==4){
//                GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                GamingScreen5.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==5){
//                GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                GamingScreen6.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==6){
//                GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                GamingScreen7.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==7){
//                GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                GamingScreen8.hudunTime = 100;
//            }
        } else if(Globe.confirmScreenId == 3){         // Jump
//            if(SelectStageScreen.stageSelectIndex==0){
//                GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                GamingScreen.dStepX = 15;
//                GamingScreen.hudunTime = 100;
//                GamingScreen.direct = "up";
//                GamingScreen.dStepY = 20;
//                GamingScreen.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==1){
//                GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                GamingScreen2.dStepX = 15;
//                GamingScreen2.hudunTime = 100;
//                GamingScreen2.direct = "up";
//                GamingScreen2.dStepY = 20;
//                GamingScreen2.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==2){
//                GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                GamingScreen3.dStepX = 15;
//                GamingScreen3.hudunTime = 100;
//                GamingScreen3.direct = "up";
//                GamingScreen3.dStepY = 20;
//                GamingScreen3.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==3){
//                GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                GamingScreen4.dStepX = 15;
//                GamingScreen4.hudunTime = 100;
//                GamingScreen4.direct = "up";
//                GamingScreen4.dStepY = 20;
//                GamingScreen4.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==4){
//                GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                GamingScreen5.dStepX = 15;
//                GamingScreen5.hudunTime = 100;
//                GamingScreen5.direct = "up";
//                GamingScreen5.dStepY = 20;
//                GamingScreen5.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==5){
//                GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                GamingScreen6.dStepX = 15;
//                GamingScreen6.hudunTime = 100;
//                GamingScreen6.direct = "up";
//                GamingScreen6.dStepY = 20;
//                GamingScreen6.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==6){
//                GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                GamingScreen7.dStepX = 15;
//                GamingScreen7.hudunTime = 100;
//                GamingScreen7.direct = "up";
//                GamingScreen7.dStepY = 20;
//                GamingScreen7.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==7){
//                GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                GamingScreen8.dStepX = 15;
//                GamingScreen8.hudunTime = 100;
//                GamingScreen8.direct = "up";
//                GamingScreen8.dStepY = 20;
//                GamingScreen8.djumpStepX = 0;
//            }
        } else if(Globe.confirmScreenId == 4){
//            FishroomScreen.yuerDx = 10;
        } else if(Globe.confirmScreenId == 5){
//            if(!(SelectFishScreen.krillNums[SelectFishScreen.fishSelectIndex]>0)){
//                KrillElement ke = new KrillElement();
//                ke.setFetchIndex(0);
//                ke.setSum(3);
//                ke.setType(1);
//                SelectFishScreen.krillElement.addElement(ke);
//                SelectFishScreen.krillNums[SelectFishScreen.fishSelectIndex]=3;
//                SelectFishScreen.isGot[SelectFishScreen.fishSelectIndex]=true;
//            }
//            NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
        } else if(Globe.confirmScreenId == 6){
//            if(SelectStageScreen.stageSelectIndex==0){
//                ((GamingScreen) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==1){
//                ((GamingScreen2) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==2){
//                ((GamingScreen3) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==3){
//                ((GamingScreen4) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==4){
//                ((GamingScreen5) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==5){
//                ((GamingScreen6) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==6){
//                ((GamingScreen7) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==7){
//                ((GamingScreen8) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//        }
//
//            //Jump
//            if(SelectStageScreen.stageSelectIndex==0){
//                GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                GamingScreen.dStepX = 15;
//                GamingScreen.hudunTime = 100;
//                GamingScreen.direct = "up";
//                GamingScreen.dStepY = 20;
//                GamingScreen.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==1){
//                GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                GamingScreen2.dStepX = 15;
//                GamingScreen2.hudunTime = 100;
//                GamingScreen2.direct = "up";
//                GamingScreen2.dStepY = 20;
//                GamingScreen2.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==2){
//                GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                GamingScreen3.dStepX = 15;
//                GamingScreen3.hudunTime = 100;
//                GamingScreen3.direct = "up";
//                GamingScreen3.dStepY = 20;
//                GamingScreen3.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==3){
//                GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                GamingScreen4.dStepX = 15;
//                GamingScreen4.hudunTime = 100;
//                GamingScreen4.direct = "up";
//                GamingScreen4.dStepY = 20;
//                GamingScreen4.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==4){
//                GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                GamingScreen5.dStepX = 15;
//                GamingScreen5.hudunTime = 100;
//                GamingScreen5.direct = "up";
//                GamingScreen5.dStepY = 20;
//                GamingScreen5.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==5){
//                GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                GamingScreen6.dStepX = 15;
//                GamingScreen6.hudunTime = 100;
//                GamingScreen6.direct = "up";
//                GamingScreen6.dStepY = 20;
//                GamingScreen6.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==6){
//                GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                GamingScreen7.dStepX = 15;
//                GamingScreen7.hudunTime = 100;
//                GamingScreen7.direct = "up";
//                GamingScreen7.dStepY = 20;
//                GamingScreen7.djumpStepX = 0;
//            }else if(SelectStageScreen.stageSelectIndex==7){
//                GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                GamingScreen8.dStepX = 15;
//                GamingScreen8.hudunTime = 100;
//                GamingScreen8.direct = "up";
//                GamingScreen8.dStepY = 20;
//                GamingScreen8.djumpStepX = 0;
//            }
        } else if(Globe.confirmScreenId == 7){
//            if(SelectStageScreen.stageSelectIndex==0){
//                ((GamingScreen) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==1){
//                ((GamingScreen2) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==2){
//                ((GamingScreen3) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==3){
//                ((GamingScreen4) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==4){
//                ((GamingScreen5) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==5){
//                ((GamingScreen6) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==6){
//                ((GamingScreen7) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }else if(SelectStageScreen.stageSelectIndex==7){
//                ((GamingScreen8) returnScreen).setHpNum(316);
//                NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//            }
//
//            //speed up
//            if(SelectStageScreen.stageSelectIndex==0){
//                GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                GamingScreen.dStepX = 60;
//                GamingScreen.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==1){
//                GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                GamingScreen2.dStepX = 60;
//                GamingScreen2.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==2){
//                GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                GamingScreen3.dStepX = 60;
//                GamingScreen3.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==3){
//                GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                GamingScreen4.dStepX = 60;
//                GamingScreen4.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==4){
//                GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                GamingScreen5.dStepX = 60;
//                GamingScreen5.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==5){
//                GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                GamingScreen6.dStepX = 60;
//                GamingScreen6.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==6){
//                GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                GamingScreen7.dStepX = 60;
//                GamingScreen7.hudunTime = 100;
//            }else if(SelectStageScreen.stageSelectIndex==7){
//                GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                GamingScreen8.dStepX = 60;
//                GamingScreen8.hudunTime = 100;
//            }
        } else if(Globe.confirmScreenId == 8){
//            if(SelectStageScreen.stageSelectIndex==0){
//                SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//            }else if(SelectStageScreen.stageSelectIndex==1){
//                SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//            }else if(SelectStageScreen.stageSelectIndex==2){
//                SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//            }else if(SelectStageScreen.stageSelectIndex==3){
//                SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//            }else if(SelectStageScreen.stageSelectIndex==4){
//                SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//            }else if(SelectStageScreen.stageSelectIndex==5){
//                SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//            }else if(SelectStageScreen.stageSelectIndex==6){
//                SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//            }else if(SelectStageScreen.stageSelectIndex==7){
//                SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//            }
        }

        LWGameCanvas.deleteScreen(this);
        LWGameCanvas.setActive(returnScreen, true);
    }

    private void updateStagePassword(){
        if(isShow){
            return;
        }
        if(LWGameCanvas.iskeyPressed(Globe.M_KEY_LEFT)){
            if(selectIndexUD==1){
                selectIndex--;
            }

        }else if(LWGameCanvas.iskeyPressed(Globe.M_KEY_RIGHT)){
            if(selectIndexUD==1){
                selectIndex++;
            }
        }else if(LWGameCanvas.iskeyPressed(Globe.M_KEY_UP)){
            if(selectIndexUD==1){
                selectIndexUD--;
            }
        }else if(LWGameCanvas.iskeyPressed(Globe.M_KEY_DOWN)){
            if(selectIndexUD==0){
                selectIndexUD++;
            }
        }
        selectIndex = (selectIndex+2)%2;
        if(selectIndexUD==0){
            int keyTemp[] = {Globe.M_KEY_0,Globe.M_KEY_1,Globe.M_KEY_2,Globe.M_KEY_3,Globe.M_KEY_4,Globe.M_KEY_5,Globe.M_KEY_6,Globe.M_KEY_7,Globe.M_KEY_8,Globe.M_KEY_9};
            for(int i = 0;i<10;i++){
                if(LWGameCanvas.iskeyPressed(keyTemp[i])){
                    if(inputString.length()<INPUT_STRING_MAX_LENGHT){
                        inputString+=(i+"");
                    }
                    break;
                }
            }
            if(LWGameCanvas.iskeyPressed(Globe.M_KEY_OK)){
                int temp = payPassWord();
                if(temp==1){
                    setShow("密码输入有误，请重新输入");
                }
            }
        }

        if(LWGameCanvas.iskeyPressed(Globe.M_KEY_OK)){
            if(selectIndexUD==1){
                if(selectIndex==0){
                    int temp = payPassWord();
                    if(temp==1){
                        setShow("密码输入有误，请重新输入");
                    }
                }else if(selectIndex==1){
                    payStage="return";
                }
            }
        }else if(LWGameCanvas.iskeyPressed(Globe.M_KEY_SOFT_R)){
            if(selectIndexUD==0){
                if(inputString.length()>0){
                    inputString = inputString.substring(0, inputString.length()-1);
                }
            }
        }
        LWGameCanvas.keyReset();
    }

    private void drawShowInfo(Graphics g){
        if(isShow){
            g.drawImage(img[5], SCREEN_W>>1, SCREEN_H>>1, Graphics.HCENTER|Graphics.VCENTER);
        }
    }

    private void setStagePassword(){
        stage = STAGE_INPUT_PASSWORD;
        if(img==null){
            img = new Image[5];
            try {
                img[0] = Image.createImage("/the9Input/bg.png");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            img[1] = Image.createImage("/the9Input/9.png");
            img[2] = Image.createImage("/the9Input/8.png");
            img[3] = Image.createImage("/the9Input/12.png");
            img[4] = Image.createImage("/the9Input/11.png");
            img[5] = Image.createImage("/the9Input/10.png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        selectIndex = 0;
        selectIndexUD = 0;
        inputString = "";
    }

    private void setShow(String str){
        isShow = true;
        showInfo = str;
        showInfoIndex = 0;
        showStartTime = System.currentTimeMillis();
    }

    private void updateShowInfo(){
        if(isShow){
            showInfoIndex++;
            if(showInfoIndex==40||(showInfoIndex>10&&!LWGameCanvas.iskeyPressed(Globe.M_KEY_0))||(System.currentTimeMillis()-showStartTime>500&&!LWGameCanvas.iskeyPressed(Globe.M_KEY_0))){
                isShow = false;
                showInfoIndex = 0;
                inputString = "";
            }
        }
    }


    private void drawPassword(Graphics g){

        int startY = SCREEN_H/2;
        g.drawImage(img[1], SCREEN_W>>1, startY-60, Graphics.HCENTER|Graphics.BOTTOM);
        g.drawImage(img[2], SCREEN_W>>1, startY, Graphics.HCENTER|Graphics.BOTTOM);
        g.drawImage(img[3], (SCREEN_W>>1), startY+20, Graphics.TOP|Graphics.HCENTER);
        g.setFont(Font.getFont(0, 1, 16));
        g.setColor(0x000000);
        String temp = "";
        for(int i = 0;i<inputString.length();i++){
            temp+="*";
        }
        g.drawString(temp, SCREEN_W>>1, startY+68,  Graphics.BOTTOM|Graphics.HCENTER);
        g.drawImage(img[4], SCREEN_W>>1, startY+100, Graphics.TOP|Graphics.HCENTER);
        g.setColor(0xffff00);

        int w = 150;
        int h = 60;
        if(selectIndexUD==0){
            g.drawRect((SCREEN_W>>1)-112-1, startY+43-h/2-1, w+2+75, h+2);
            g.drawRect((SCREEN_W>>1)-112+1, startY+43-h/2+1, w-2+75, h-2);
            g.drawRect((SCREEN_W>>1)-112, startY+43-h/2, w+75, h);
        }else{
            g.drawRect((SCREEN_W>>1)-120-w/2+(selectIndex*240)-1, startY+122-h/2-1, w+2, h+2);
            g.drawRect((SCREEN_W>>1)-120-w/2+(selectIndex*240)+1, startY+122-h/2+1, w-2, h-2);
            g.drawRect((SCREEN_W>>1)-120-w/2+(selectIndex*240), startY+122-h/2, w, h);
        }
    }

    private void drawRecharge(Graphics g){
        int w = 125;
        int h = 50;
        int startY = SCREEN_H/2;
        g.drawImage(img[5], 240, 50, Graphics.TOP|Graphics.LEFT);
        g.drawImage(img[4], 85, 135, Graphics.TOP|Graphics.LEFT);
        g.drawImage(img[2], 85, 220, Graphics.TOP|Graphics.LEFT);
        g.drawImage(img[3], 85, 260, Graphics.TOP|Graphics.LEFT);
        g.drawImage(img[6], 230, 280, Graphics.TOP|Graphics.LEFT);
        g.drawImage(img[7], 280, 340, Graphics.TOP|Graphics.LEFT);
        g.setColor(0xffffff);
        g.setFont(Font.getFont(0, 1, 16));
        g.drawString(""+rechargeNum, 250, 345, Graphics.TOP|Graphics.LEFT);
        g.drawImage(img[selectIndex], 150, 390, Graphics.TOP|Graphics.LEFT);
        g.drawRect((SCREEN_W>>1)-113-w/2+(selectIndex*210)-1, startY+146-h/2-1, w+2, h+2);
        g.drawRect((SCREEN_W>>1)-113-w/2+(selectIndex*210)+1, startY+146-h/2+1, w-2, h-2);
        g.drawRect((SCREEN_W>>1)-113-w/2+(selectIndex*210), startY+146-h/2, w, h);
    }

    private int payPassWord(){
        int ret = 2;
        try {
            ret = netHander.topupAndConsumeMoney(price, wareName, inputString);
            if(ret==0){
                payStage="success";
                setPayOK(true);
            }else if(ret==1){
                setStagePassword();
            }else if(ret==2){
                payStage="failure";
                setPayOK(false);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            setPayEnd(true);
            setPayOK(false);
            e.printStackTrace();
        } catch (IptvNetException e) {
            // TODO Auto-generated catch block
            setPayEnd(true);
            setPayOK(false);
            e.printStackTrace();
        }
        return ret;
    }

    /**
     *
     * @return true为支付成功,flase为支付失败
     */
    public boolean isPayOk(){
        return isPayOk;
    }

    /**
     *
     * @return true为验证码也童锁支付过程完成，false为还未完成
     */
    public boolean isPayEnd(){
        return isPayEnd;
    }


    /**
     * 此接口预留,默认值是640*530,如果界面大小不是这个尺寸，可以调用这个方法修改
     * @param w
     * @param h
     */
    public static void setScreenWidthAndHeight(int w,int h){
        SCREEN_W = w;
        SCREEN_H = h;
    }

    /**
     *  此接口预留,设置按键键值（现有机顶盒的键值都已经设置好了，此接口预留）
     * @param key 这里面元素是新分配的简直，顺序分别是
     * 上，下，左，右，数字键0-9，确认键，返回键1，返回键2,返回键3,返回键4
     */
    public static void setKeyCode(int[]key){
        KEY_UP = key[0];
        KEY_DOWN = key[1];
        KEY_LEFT = key[2];
        KEY_RIGHT = key[3];
        KEY_0 = key[4];
        KEY_1 = key[5];
        KEY_2 = key[6];
        KEY_3 = key[7];
        KEY_4 = key[8];
        KEY_5 = key[9];
        KEY_6 = key[10];
        KEY_7 = key[11];
        KEY_8 = key[12];
        KEY_9 = key[13];
        KEY_OK = key[14];
        KEY_SOFT_R0 = key[15];
        KEY_SOFT_R1 = key[16];
        KEY_SOFT_R2 = key[17];
        KEY_SOFT_R3 = key[18];
    }

    /**
     * 此接口预留，默认值是8位，调用此方法可以修改输入字符串的最大长度
     * @param lenght 要修改的字符串输入长度（默认值是8位）
     */
    public static void setInputStringMaxLength(int lenght){
        INPUT_STRING_MAX_LENGHT = lenght;
    }

}
