package screens;

import Entry.LWGameCanvas;
import common.Globe;
import common.NetInfo;
import common.Screen;
import common.Utility;
import elements.*;
import iptvNet.IptvNetException;
import iptvNet.NetHander;
import motion.Motion;
import org.json.me.JSONArray;
import org.json.me.JSONException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.util.Enumeration;
import java.util.Hashtable;

/**
* Created with IntelliJ IDEA.
* User: tongxiqing
* Date: 14-12-5
* Time: 下午1:27
* To change this template use File | Settings | File Templates.
*/
public class WorriesFightScreen extends Screen {

    public WorriesFightScreen(int screenId) {
        super(screenId);

        left_atack_2_motion = new Motion[2];
        renwu_huonv_motion = new Motion[2];
        enemy_motion_2 = new Motion[2];
        enemy_motion_3 = new Motion[2];

        dead_motion = new Motion("/dead/dead.anu",240,250);
        dead_motion.keepId(0);
        dead_motion_1 = new Motion("/dead/dead.anu",480,250);
        dead_motion_1.keepId(0);
        atomic_elec_motion = new Motion("/effect2/atomic.elect/atomic.elec.anu",480,250);
        atomic_elec_motion.keepId(0);

        eff_point_Coco_atk_motion = new Motion("/effect2/point_Coco/eff_point_Coco_atk.anu",350,300);
        eff_point_Coco_atk_motion.keepId(0);
        eff_point_Coco_atk_motion1 = new Motion("/effect2/point_Coco/eff_point_Coco_atk.anu",400,300);
        eff_point_Coco_atk_motion1.keepId(0);
        eff_point_Coco_atk_motion2 = new Motion("/effect2/point_Coco/eff_point_Coco_atk.anu",450,300);
        eff_point_Coco_atk_motion2.keepId(0);
        eff_point_Coco_atk_motion3 = new Motion("/effect2/point_Coco/eff_point_Coco_atk.anu",500,300);
        eff_point_Coco_atk_motion3.keepId(0);

        gripper_motion = new Motion("/effect2/gripper/gripper.anu",420,250);
        gripper_motion.keepId(0);

    }

    public void init() {

//        try {
//            NetInfo.netHander.testStr("test1");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IptvNetException e) {
//            e.printStackTrace();
//        }

        try {
            NetInfo.netHander.testStr(bgImgPaths[Integer.valueOf(NetHander.selected_stage).intValue()]);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IptvNetException e) {
            e.printStackTrace();
        }

        mainBG = Globe.download.creatImage(bgImgPaths[Integer.valueOf(NetHander.selected_stage).intValue()]);

        anger_unfull_img = new Image[2];
        anger_unfull_img[0] = Globe.getImage("nw_game_screen/anger_unfull.png");
        anger_unfull_img[1] = Globe.getImage("nw_game_screen/anger_full.png");

        bottom_02_img = Globe.getImage("nw_game_screen/bottom_02.png");
        title_2_13_img = Globe.getImage("nw_game_screen/title-2_13.png");
        chest_2_07_img = Globe.getImage("nw_game_screen/chest-2_07.png");
        jewelry_2_05_img = Globe.getImage("nw_game_screen/jewelry-2_05.png");
        hourglass_img = Globe.getImage("nw_game_screen/hourglass.png");

        hp_img = Globe.getImage("nw_game_screen/hp.png");
        anger_img = Globe.getImage("nw_game_screen/anger.png");

        successfailure_img = Globe.getImage("menu/successfailure.png");
        success_img = Globe.getImage("menu/success.png");

        shadow_hero_2 = new Image[7][];
        for(int j=0; j<shadow_hero_2.length; j++){
            shadow_hero_2[j] = new Image[2];
            for(int i=0; i<shadow_hero_2[j].length; i++){
                if(i==0)
                    shadow_hero_2[j][i] = Globe.getImage("titles/shadow_hero/"+(j+1)+".png");
                else
                    shadow_hero_2[j][i] = Globe.getImage("titles/hero/"+(j+1)+".png");
            }
        }

        shadow_monster_2 = new Image[12][];
        for(int j=0; j<shadow_monster_2.length; j++){
            shadow_monster_2[j] = new Image[2];
            for (int i=0; i<shadow_monster_2[j].length; i++){
                if(i == 0)
                    shadow_monster_2[j][i] = Globe.getImage("titles/shadow_monster/"+((char)('a'+j))+".png");
                else
                    shadow_monster_2[j][i] = Globe.getImage("titles/monster/"+((char)('a'+j))+".png");
            }
        }

        shadow_monster_l = new Image[9][];
        for(int j=0; j<shadow_monster_l.length; j++){
            shadow_monster_l[j] = new Image[2];
            for (int i=0; i<shadow_monster_l[j].length; i++){
                if(i == 0)
                    shadow_monster_l[j][i] = Globe.getImage("titles/shadow_pets/"+((char)('A'+j))+".png");
                else
                    shadow_monster_l[j][i] = Globe.getImage("titles/pets/"+((char)('A'+j))+".png");
            }
        }

        try {

            fighterInfo = NetInfo.netHander.getFighterInfo();
            enemyInfo = NetInfo.netHander.getEnemyInfo();
            enemyTeamList = NetInfo.netHander.getGameInfoII();
            monsterList = NetInfo.netHander.getGameInfoIII();

            hpImgWidthList = NetInfo.netHander.getFighterHpDown();

            anger_img_width = new int[hpImgWidthList.length()];
            for(int i=0; i<anger_img_width.length; i++){
                anger_img_width[i] = 0;
            }

            hp_img_widthhashtable = new Hashtable();
            for(int i=0; i<hpImgWidthList.length(); i++){
                hp_img_widthhashtable.put(Integer.valueOf((String) hpImgWidthList.getJSONObject(i).get("fighterId")), Integer.valueOf((String) hpImgWidthList.getJSONObject(i).get("hpImgWidth")));
            }

            enemy_motion_1 = new Motion((String) enemyInfo.getJSONObject(0).getJSONArray("ken").get(0),390,250);
            enemy_motion_1.keepId(0);
            enemy_motion_2[0] = new Motion((String) enemyInfo.getJSONObject(0).getJSONArray("ken").get(1),480,250);
            enemy_motion_2[0].keepId(0);
            enemy_motion_2[1] = new Motion((String) enemyInfo.getJSONObject(0).getJSONArray("ken").get(3),480,250);
            enemy_motion_2[1].keepId(0);
            enemy_motion_3[0] = new Motion((String) enemyInfo.getJSONObject(0).getJSONArray("ken").get(2),480,250);
            enemy_motion_3[0].keepId(0);
            enemy_motion_3[1] = new Motion((String) enemyInfo.getJSONObject(0).getJSONArray("ken").get(4),480,250);
            enemy_motion_3[1].keepId(0);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IptvNetException e) {
            e.printStackTrace();
        }

        fighterEffects = new Effect[fighterInfo.length()];              //todo

        try {
            Class[] effectclasses = new Class[32];
            effectclasses[0] = Class.forName("elements.FallingFires");
            effectclasses[1] = Class.forName("elements.PointCoco");
            effectclasses[2] = Class.forName("elements.FireBall");
            effectclasses[3] = Class.forName("elements.IceSmall");
            effectclasses[4] = Class.forName("elements.WaterShip");
            effectclasses[5] = Class.forName("elements.Hurricane");
            effectclasses[6] = Class.forName("elements.Lightning");
            effectclasses[7] = Class.forName("elements.Storm");
            effectclasses[8] = Class.forName("elements.FallingFires");
            effectclasses[9] = Class.forName("elements.PointCoco");
            effectclasses[10] = Class.forName("elements.FireBall");
            effectclasses[11] = Class.forName("elements.IceSmall");
            effectclasses[12] = Class.forName("elements.WaterShip");
            effectclasses[13] = Class.forName("elements.Hurricane");
            effectclasses[14] = Class.forName("elements.Lightning");
            effectclasses[15] = Class.forName("elements.Storm");
            effectclasses[16] = Class.forName("elements.FallingFires");
            effectclasses[17] = Class.forName("elements.PointCoco");
            effectclasses[18] = Class.forName("elements.FireBall");
            effectclasses[19] = Class.forName("elements.IceSmall");
            effectclasses[20] = Class.forName("elements.WaterShip");
            effectclasses[21] = Class.forName("elements.Hurricane");
            effectclasses[22] = Class.forName("elements.Lightning");
            effectclasses[23] = Class.forName("elements.Storm");
            effectclasses[24] = Class.forName("elements.FallingFires");
            effectclasses[25] = Class.forName("elements.PointCoco");
            effectclasses[26] = Class.forName("elements.FireBall");
            effectclasses[27] = Class.forName("elements.IceSmall");
            effectclasses[28] = Class.forName("elements.WaterShip");
            effectclasses[29] = Class.forName("elements.Hurricane");
            effectclasses[30] = Class.forName("elements.Lightning");
            effectclasses[31] = Class.forName("elements.Storm");

            for(int i=0; i<fighterInfo.length(); i++){
                Object effectobj= effectclasses[Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")).intValue()-1].newInstance();
                fighterEffects[i] = (Effect)effectobj;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            for(int i=0; i<fighterInfo.length(); i++){
                motionImpl(Integer.valueOf((String)fighterInfo.getJSONObject(i).get("type")).intValue(),
                        Integer.valueOf((String)fighterInfo.getJSONObject(i).get("fighter_id")).intValue());
            }

            if(heroCount>0)
                motion.keepId(0);

            if(followCount>0)
                left_atack_2_motion[0].keepId(0);

            if(followCount>1)
                left_atack_2_motion[1].keepId(0);

            if(petCount>0)
                renwu_huonv_motion[0].keepId(0);

            if(petCount>1)
                renwu_huonv_motion[1].keepId(0);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void update() {

//        try {
//            NetInfo.netHander.testStr("test2");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IptvNetException e) {
//            e.printStackTrace();
//        }



        enemy_motion_1.keepId(0);
        enemy_motion_1.update(350,pos_y_array[0]+20);

        enemy_motion_2[0].keepId(0);
        enemy_motion_2[0].update(450, pos_y_array[1]-70);

        enemy_motion_2[1].keepId(0);
        enemy_motion_2[1].update(450, pos_y_array[1]+50);

        enemy_motion_3[0].keepId(0);
        enemy_motion_3[0].update(580, pos_y_array[2]-70);  //pos_y control by PointCoco class

        enemy_motion_3[1].keepId(0);
        enemy_motion_3[1].update(580, pos_y_array[2]+50);  //pos_y control by PointCoco class

        if(heroCount>0){
            motion.keepId(0);
            motion.update(250,270);
        }

        if(followCount>0){
            left_atack_2_motion[0].keepId(0);
            left_atack_2_motion[0].update(80, 200);
        }

        if(followCount>1){
            left_atack_2_motion[1].keepId(0);
            left_atack_2_motion[1].update(80,340);
        }

        if(petCount>0){
            renwu_huonv_motion[0].keepId(0);
            renwu_huonv_motion[0].update(180, 200);
        }

        if(petCount>1){
            renwu_huonv_motion[1].keepId(0);
            renwu_huonv_motion[1].update(180,340);
        }

        dead_motion.keepId(0);
        dead_motion.update(240,250);
        dead_motion_1.keepId(0);
        dead_motion_1.update(480,250);
        atomic_elec_motion.keepId(0);
        atomic_elec_motion.update(240,250);



        // effect release
        for(int i=0; i<anger_img_width.length; i++){
            if(anger_img_width[i] >= anger_img.getWidth()){

                hashtable.put(new Integer(i), fighterEffects[i]);
//                hashtable.remove(new Integer(i));
                anger_img_width[i]=0;
//                fighterEffects[i].update(0);      //0无效
            }
        }

        for(Enumeration it = hashtable.keys(); it.hasMoreElements(); ) {
            Integer key = (Integer) it.nextElement();
            Effect effect = (Effect) hashtable.get(key);

            effect.update(0);
        }

        eff_point_Coco_atk_motion.keepId(0);
        eff_point_Coco_atk_motion.update(350,300);
        eff_point_Coco_atk_motion1.keepId(0);
        eff_point_Coco_atk_motion1.update(400,300);
        eff_point_Coco_atk_motion2.keepId(0);
        eff_point_Coco_atk_motion2.update(450,300);
        eff_point_Coco_atk_motion3.keepId(0);
        eff_point_Coco_atk_motion3.update(500,300);

        gripper_motion.keepId(0);
        gripper_motion.update(420,250);

        try {
            hpImgWidthList = NetInfo.netHander.getFighterHpDownII();

            hp_img_widthhashtable = new Hashtable();
            for(int i=0; i<hpImgWidthList.length(); i++){
                hp_img_widthhashtable.put(Integer.valueOf((String) hpImgWidthList.getJSONObject(i).get("fighterId")), Integer.valueOf((String) hpImgWidthList.getJSONObject(i).get("hpImgWidth")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IptvNetException e) {
            e.printStackTrace();
        }

        if((secondCount+25)%25 == 0){      //游戏时长控制
            LWGameCanvas.sum_enemy_hp_num -= LWGameCanvas.sum_fight_num;
            LWGameCanvas.sum_hero_hp_num -= LWGameCanvas.sum_enemy_fight_num;
        }

        if(LWGameCanvas.sum_enemy_hp_num < 0){
            this.setActive(false);
            LWGameCanvas.addScreen(new SuccessScreen(0));
        }

        if(LWGameCanvas.sum_hero_hp_num < 0){
            this.setActive(false);
            LWGameCanvas.addScreen(new FailureScreen(0));
        }

        if(minuteCount == 0){
            this.setActive(false);
            LWGameCanvas.addScreen(new FailureScreen(0));
        }

        if(LWGameCanvas.iskeyPressed(Globe.M_KEY_1)){
            this.setActive(false);
            LWGameCanvas.addScreen(new SuccessScreen(0));
        }

        if(LWGameCanvas.iskeyPressed(Globe.M_KEY_2)){
            this.setActive(false);
            LWGameCanvas.addScreen(new FailureScreen(0));
        }

        if(LWGameCanvas.iskeyPressed(Globe.M_KEY_0)||
                LWGameCanvas.iskeyPressed(Globe.M_KEY_SOFT_R)){

            try {
                NetInfo.netHander.screenToPageAction("teamscreen.jsp");
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IptvNetException e) {
                e.printStackTrace();
            }

            LWGameCanvas.isExit = true;
        }
    }

    public void draw(Graphics g) {

//        try {
//            NetInfo.netHander.testStr("test3");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IptvNetException e) {
//            e.printStackTrace();
//        }

//        if((secondCount+5)%5 == 0){            //技能冷却时间控制
//        for(int i=0; i<anger_img_width.length; i++){
//            if(anger_img_width[i] < anger_img.getWidth()) anger_img_width[i] += 2;
//        }
//        }

        g.drawImage(mainBG, 0, 0, 20);
//        LWGameCanvas.rmidlet.getAppProperty("");

        g.setColor(0xffffff);

        /**falling fire draw*/
//        fallingFires.draw(g);

        /**point cocos draw*/
//        pointCocos.draw(g);
        if(heroCount>0){
            motion.draw(g);
        }

        enemy_motion_1.draw(g);

//        if(fighterInfo.length()>3){
//            left_atack_2_motion.draw(g);
//        }

        if(followCount>0)
            left_atack_2_motion[0].draw(g);
        if(followCount>1)
            left_atack_2_motion[1].draw(g);

        enemy_motion_2[0].draw(g);  /**enemy motion*/
        enemy_motion_2[1].draw(g);

        if(petCount>0)
            renwu_huonv_motion[0].draw(g);
        if(petCount>1)
            renwu_huonv_motion[1].draw(g);

        enemy_motion_3[0].draw(g);
        enemy_motion_3[1].draw(g);
        dead_motion.draw(g);
        dead_motion_1.draw(g);
//        atomic_elec_motion.draw(g);

        /**lighting draw*/
//        lighting.draw(g);

        /**water ship draw*/
//        watership.draw(g);

        /**fire ball draw*/
//        fireball.draw(g);

        /**storm draw*/
//        storm.draw(g);

        /**hurricane draw*/
//        hurricane.draw(g);

        /**ice small draw*/
//        for(int i=0; i<anger_img_width.length; i++){
//            if(anger_img_width[i] >= anger_img.getWidth()) fighterEffects[i].draw(g);
//        }

        for(Enumeration it = hashtable.keys(); it.hasMoreElements(); ) {
            Integer key = (Integer) it.nextElement();
            Effect effect = (Effect) hashtable.get(key);

            effect.draw(g);
        }

        g.drawImage(hourglass_img, 50, 20, Globe.ANCHOR_T_L);

        g.drawImage(title_2_13_img, 400, 20, Globe.ANCHOR_T_L);
        g.drawImage(chest_2_07_img, 410, 20, Globe.ANCHOR_T_L);
        g.drawImage(title_2_13_img, 500, 20, Globe.ANCHOR_T_L);
        g.drawImage(jewelry_2_05_img, 520, 20, Globe.ANCHOR_T_L);

        try {

            int followCount = 0;   //follow 框个数
            int petCount = 0;    //pet 框个数
            int allCount = 0;     //all 框个数
            int[] figureGap = {90,110,130};      //按个数确定头像间隔
//            fighter_ids = new int[5];

            for(int i=0; i<fighterInfo.length(); i++){
                if(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("type")).intValue() == 0){     //hero animation
                    g.drawImage(anger_unfull_img[anger_img_width[allCount] < anger_img.getWidth()?0:1], 100, 350, Globe.ANCHOR_T_L);
                    g.drawImage(shadow_hero_2[Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")).intValue()-1][((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue()<=0?0:1], 110, 360, Globe.ANCHOR_T_L);

                    //绘制hp条
                    g.setClip(100, 440, ((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue(), hp_img.getHeight());
                    g.drawImage(hp_img,  100, 440, 20);
                    g.setClip(0, 0, Globe.SW, Globe.SH);

                    //绘制anger条
                    g.setClip(100, 450, anger_img_width[allCount], anger_img.getHeight());
                    g.drawImage(anger_img,  100, 450, 20);
                    g.setClip(0, 0, Globe.SW, Globe.SH);

                    if(anger_img_width[allCount] < anger_img.getWidth()) anger_img_width[allCount] += 2;
                    allCount ++;

                }else if(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("type")).intValue() == 1){    //follow animation

                    if(followCount == 0){
                        followCount ++;

                        g.drawImage(anger_unfull_img[anger_img_width[allCount] < anger_img.getWidth()?0:1], 100+allCount*figureGap[5-fighterInfo.length()], 350, Globe.ANCHOR_T_L);
                        g.drawImage(shadow_monster_2[Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")).intValue()-8][((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue()<=0?0:1], 110+allCount*figureGap[5-fighterInfo.length()], 360, Globe.ANCHOR_T_L);

                        //绘制hp条
                        g.setClip(100+allCount*90, 440, ((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue(), hp_img.getHeight());
                        g.drawImage(hp_img,  100+allCount*figureGap[5-fighterInfo.length()], 440, 20);
                        g.setClip(0, 0, Globe.SW, Globe.SH);

                        //绘制anger条
                        g.setClip(100+allCount*90, 450, anger_img_width[allCount], anger_img.getHeight());
                        g.drawImage(anger_img,  100+allCount*figureGap[5-fighterInfo.length()], 450, 20);
                        g.setClip(0, 0, Globe.SW, Globe.SH);

                        if(anger_img_width[allCount] < anger_img.getWidth()) anger_img_width[allCount] += 4;
                        allCount ++;

                    }else {
                        g.drawImage(anger_unfull_img[anger_img_width[allCount] < anger_img.getWidth()?0:1], 100+allCount*figureGap[5-fighterInfo.length()], 350, Globe.ANCHOR_T_L);
                        g.drawImage(shadow_monster_2[Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")).intValue()-8][((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue()<=0?0:1], 110+allCount*figureGap[5-fighterInfo.length()], 360, Globe.ANCHOR_T_L);

                        //绘制hp条
                        g.setClip(100+allCount*90, 440, ((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue(), hp_img.getHeight());
                        g.drawImage(hp_img,  100+allCount*figureGap[5-fighterInfo.length()], 440, 20);
                        g.setClip(0, 0, Globe.SW, Globe.SH);

                        //绘制anger条
                        g.setClip(100+allCount*90, 450, anger_img_width[allCount], anger_img.getHeight());
                        g.drawImage(anger_img,  100+allCount*figureGap[5-fighterInfo.length()], 450, 20);
                        g.setClip(0, 0, Globe.SW, Globe.SH);

                        if(anger_img_width[allCount] < anger_img.getWidth()) anger_img_width[allCount] += 4;
                        allCount ++;
                    }

                }else if(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("type")).intValue() == 2){   //pets animation
                    if(petCount == 0){
                        petCount ++;
                        g.drawImage(anger_unfull_img[anger_img_width[allCount] < anger_img.getWidth()?0:1], 100+allCount*figureGap[5-fighterInfo.length()], 350, Globe.ANCHOR_T_L);
                        g.drawImage(shadow_monster_l[Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")).intValue()-20][((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue()<=0?0:1], 110+allCount*figureGap[5-fighterInfo.length()], 360, Globe.ANCHOR_T_L);

                        //绘制hp条
                        g.setClip(100+allCount*90, 440, ((Integer)hp_img_widthhashtable.get(Integer.valueOf((String)fighterInfo.getJSONObject(i).get("fighter_id")))).intValue(), hp_img.getHeight());
                        g.drawImage(hp_img,  100+allCount*figureGap[5-fighterInfo.length()], 440, 20);
                        g.setClip(0, 0, Globe.SW, Globe.SH);

                        //绘制anger条
                        g.setClip(100+allCount*90, 450, anger_img_width[allCount], anger_img.getHeight());
                        g.drawImage(anger_img,  100+allCount*figureGap[5-fighterInfo.length()], 450, 20);
                        g.setClip(0, 0, Globe.SW, Globe.SH);

                        if(anger_img_width[allCount] < anger_img.getWidth()) anger_img_width[allCount] += 6;
                        allCount ++;
                    }else {
                        g.drawImage(anger_unfull_img[anger_img_width[allCount] < anger_img.getWidth()?0:1], 100+allCount*figureGap[5-fighterInfo.length()], 350, Globe.ANCHOR_T_L);
                        g.drawImage(shadow_monster_l[Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")).intValue()-20][((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue()<=0?0:1], 110+allCount*figureGap[5-fighterInfo.length()], 360, Globe.ANCHOR_T_L);

                        //绘制hp条
                        g.setClip(100+allCount*90, 440, ((Integer)hp_img_widthhashtable.get(Integer.valueOf((String) fighterInfo.getJSONObject(i).get("fighter_id")))).intValue(), hp_img.getHeight());
                        g.drawImage(hp_img,  100+allCount*figureGap[5-fighterInfo.length()], 440, 20);
                        g.setClip(0, 0, Globe.SW, Globe.SH);

                        //绘制anger条
                        g.setClip(100+allCount*90, 450, anger_img_width[allCount], anger_img.getHeight());
                        g.drawImage(anger_img,  100+allCount*figureGap[5-fighterInfo.length()], 450, 20);
                        g.setClip(0, 0, Globe.SW, Globe.SH);

                        if(anger_img_width[allCount] < anger_img.getWidth()) anger_img_width[allCount] += 6;
                        allCount ++;
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        g.drawImage(bottom_02_img, 0, 500, Globe.ANCHOR_T_L);

        g.setFont(Globe.BigBoldFont);

        if(minuteCount >= 0) secondCount--;      /**倒计时*/

        g.drawString("倒计时:"+minuteCount+"分"+secondCount+"秒", 150, 30, Globe.ANCHOR_T_H);

        if(secondCount == 0 && minuteCount > 0){
            secondCount = 60;
            minuteCount -= 1;
        }

        g.drawString(LWGameCanvas.ret+"", 460, 20, Globe.ANCHOR_T_H);
        g.drawString(LWGameCanvas.chest_num+"", 560, 20, Globe.ANCHOR_T_H);


//        g.drawString(Integer.valueOf(NetHander.selected_stage).intValue()+"", 560, 120, Globe.ANCHOR_T_H);

//        g.drawString(enemyTeamList.toString()+"", 0, 50, Globe.ANCHOR_T_H);
//        g.drawString(monsterList.toString()+"", 0, 100, Globe.ANCHOR_T_H);
    }

    public void clear() {
        mainBG = null;
        anger_unfull_img = null;
        bottom_02_img = null;
        title_2_13_img = null;
        chest_2_07_img = null;
        jewelry_2_05_img = null;
        hourglass_img = null;
        shadow_hero_2 = null;
        shadow_monster_2 = null;
        shadow_monster_l = null;
        hp_img = null;
        anger_img = null;
        successfailure_img = null;
        success_img = null;
        Runtime.getRuntime().gc();
    }

    public void motionImpl(int type, int fighter_id){

        if(type == 0){     //hero animation
            heroCount ++;
            motion = Utility.motionImpl(type, fighter_id);
        }else if(type == 1){    //follow animation
            left_atack_2_motion[followCount++] = Utility.motionImpl(type, fighter_id);
        }else if(type == 2){   //pets animation
            renwu_huonv_motion[petCount++] = Utility.motionImpl(type, fighter_id);
        }
    }


    Motion motion;
    Motion[] left_atack_2_motion;
    Motion[] renwu_huonv_motion;

    /**enmey动画*/
    Motion enemy_motion_1;
    Motion[] enemy_motion_2;
    Motion[] enemy_motion_3;

    Motion dead_motion;
    Motion dead_motion_1;
    Motion atomic_elec_motion;

    Motion eff_point_Coco_atk_motion;
    Motion eff_point_Coco_atk_motion1;
    Motion eff_point_Coco_atk_motion2;
    Motion eff_point_Coco_atk_motion3;

    Motion gripper_motion;
    Image bottom_02_img;
    Image title_2_13_img;
    Image chest_2_07_img;
    Image jewelry_2_05_img;
    Image hourglass_img;

    Image[] anger_unfull_img;
    Image[][] shadow_hero_2;
    Image[][] shadow_monster_2;
    Image[][] shadow_monster_l;

    Image hp_img;
    Image anger_img;
    Image successfailure_img;
    Image success_img;

    Effect[] fighterEffects;
    //    int[] fighter_ids;
//    Hashtable heroHphashtable;
    Hashtable hp_img_widthhashtable;

//    final String[] effects_str = new String[]{"elements.FallingFires","elements.PointCoco","elements.FireBall","elements.WaterShip","elements.WaterShip"};

    String[] bgImgPaths = new String[]{"menu/bg1.jpg","menu/bg2.jpg","menu/bg3.jpg","menu/bg4.jpg","menu/bg5.jpg","menu/bg6.jpg","menu/bg7.jpg","menu/bg8.jpg","menu/bg9.jpg",
            "menu/bg10.jpg","menu/bg11.jpg","menu/bg12.jpg","menu/bg13.jpg","menu/bg14.jpg","menu/bg15.jpg","menu/bg16.jpg","menu/bg17.jpg","menu/bg18.jpg",
            "menu/bg19.jpg","menu/bg20.jpg","menu/bg21.jpg","menu/bg22.jpg","menu/bg23.jpg","menu/bg24.jpg","menu/bg25.jpg","menu/bg26.jpg","menu/bg27.jpg",
            "menu/bg28.jpg","menu/bg29.jpg","menu/bg30.jpg","menu/bg31.jpg","menu/bg32.jpg","menu/bg33.jpg","menu/bg34.jpg","menu/bg35.jpg","menu/bg36.jpg",
            "menu/bg37.jpg","menu/bg38.jpg","menu/bg39.jpg","menu/bg40.jpg","menu/bg41.jpg"};

    private int heroCount = 0;
    private int followCount = 0;
    private int petCount = 0;
    private Image mainBG;
    public static int[] pos_y_array = {250, 300, 300};

    private JSONArray fighterInfo; // fighter数据
    private JSONArray enemyInfo; // enemy数据
    private JSONArray enemyTeamList; // enemyTeamList数据
    private JSONArray monsterList; // monsterList数据

    private JSONArray hpImgWidthList; // hpDownStepValueList数据

    //    private int[] hp_img_width;
    private int[] anger_img_width;

    private int secondCount = 30;
    private int minuteCount = 3;

    private Hashtable hashtable = new Hashtable();
}