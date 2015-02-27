package screens;

import Entry.LWGameCanvas;
//import com.sun.perseus.model.Font;
import common.Globe;
import common.NetInfo;
import common.Screen;
import common.Vector;
import elements.*;
import iptvNet.IptvNetException;
import iptvNet.NetHander;
import motion.Motion;
import org.json.me.JSONArray;
import org.json.me.JSONException;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

/**
 * Created with IntelliJ IDEA.
 * User: tongxiqing
 * Date: 14-12-5
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
public class WorriesFightScreen  extends Screen {

    private Image mainBG;
    Motion motion;
    Motion red_h_motion;
    Motion left_atack_2_motion;
    Motion left_atack_2_motion_h;
    Motion renwu_huonv_motion;
    Motion renwu_huonv_h_motion;
    Motion dead_motion;
    Motion dead_motion_1;
    Motion atomic_elec_motion;
//    Motion falling_fire_motion;

    Motion eff_point_Coco_atk_motion;
    Motion eff_point_Coco_atk_motion1;
    Motion eff_point_Coco_atk_motion2;
    Motion eff_point_Coco_atk_motion3;

    Motion gripper_motion;
    Image anger_unfull_img;
    Image bottom_02_img;
    Image title_2_13_img;
    Image chest_2_07_img;
    Image jewelry_2_05_img;
    Image hourglass_img;
    Image shadow_hero_2;
    Image shadow_monster_2;
    Image shadow_monster_l;
    Image hp_img;
    Image anger_img;
    Image successfailure_img;
    Image success_img;

    Effect fallingFires;
    Effect pointCocos;
    Effect lighting;
    Effect watership;

    private JSONArray fighterInfo; // fighter数据

    public WorriesFightScreen(int screenId) {
        super(screenId);
        motion = new Motion("/hero/red/red.anu",210,250);
        motion.keepId(0);
        red_h_motion = new Motion("/hero/red/red_h.anu",390,250);
        red_h_motion.keepId(0);

        left_atack_2_motion_h = new Motion("/follow/left/2/left_atack_2_h.anu",480,250);
        left_atack_2_motion_h.keepId(0);
//        renwu_huonv_motion = new Motion("/follow/left/1/renwu_huonv.anu",180,250);
//        renwu_huonv_motion.keepId(0);
        renwu_huonv_h_motion = new Motion("/follow/left/1/renwu_huonv_h.anu",480,250);
        renwu_huonv_h_motion.keepId(0);
        dead_motion = new Motion("/dead/dead.anu",240,250);
        dead_motion.keepId(0);
        dead_motion_1 = new Motion("/dead/dead.anu",480,250);
        dead_motion_1.keepId(0);
        atomic_elec_motion = new Motion("/effect2/atomic.elect/atomic.elec.anu",480,250);
        atomic_elec_motion.keepId(0);
//        falling_fire_motion = new Motion("/effect2/lava.serie/falling.fire/falling_fire.anu",200,250);
//        falling_fire_motion.keepId(0);

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

        fallingFires = new FallingFires(0,200,250);
        pointCocos = new PointCoco(0,300,250);
        lighting = new Lightning();
        watership = new WaterShip();
    }

    public void init() {
//        List list = new List("",0);  list.get
//        List<String> dfda =
//        list.a
        mainBG = Globe.getImage("menu/bg8.jpg");
        anger_unfull_img = Globe.getImage("nw_game_screen/anger_unfull.png");
        bottom_02_img = Globe.getImage("nw_game_screen/bottom_02.png");
        title_2_13_img = Globe.getImage("nw_game_screen/title-2_13.png");
        chest_2_07_img = Globe.getImage("nw_game_screen/chest-2_07.png");
        jewelry_2_05_img = Globe.getImage("nw_game_screen/jewelry-2_05.png");
        hourglass_img = Globe.getImage("nw_game_screen/hourglass.png");
        shadow_hero_2 = Globe.getImage("titles/shadow_hero/2.png");
        hp_img = Globe.getImage("nw_game_screen/hp.png");
        anger_img = Globe.getImage("nw_game_screen/anger.png");
        shadow_monster_2 = Globe.getImage("titles/shadow_monster/c.png");
        shadow_monster_l = Globe.getImage("titles/shadow_monster/l.png");
        successfailure_img = Globe.getImage("menu/successfailure.png");
        success_img = Globe.getImage("menu/success.png");

        hp_img_width = hp_img.getWidth();
        anger_img_width = 0;

        try {
            fighterInfo = NetInfo.netHander.getFighterInfo();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IptvNetException e) {
            e.printStackTrace();
        }

        try {

            for(int i=0; i<fighterInfo.length(); i++){

                motionImpl(Integer.valueOf((String)fighterInfo.getJSONObject(i).get("type")).intValue(),
                        Integer.valueOf((String)fighterInfo.getJSONObject(i).get("fighter_id")).intValue());

            }

            motion.keepId(0);
            left_atack_2_motion.keepId(0);
            renwu_huonv_motion.keepId(0);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    boolean mustExit = false;
    private int falling_fire_y = 0;
    public static int[] pos_y_array = {250, 300, 250};
    private int watership_x = 0;

    public void update() {

        motion.keepId(0);
        motion.update(250,250);

        red_h_motion.keepId(0);
        red_h_motion.update(390,pos_y_array[0]);

        left_atack_2_motion.keepId(0);
        left_atack_2_motion.update(120,300);

        left_atack_2_motion_h.keepId(0);
        left_atack_2_motion_h.update(450,pos_y_array[1]);

        renwu_huonv_motion.keepId(0);
        renwu_huonv_motion.update(180,250);
        renwu_huonv_h_motion.keepId(0);
        renwu_huonv_h_motion.update(480,pos_y_array[2]);  //pos_y control by PointCoco class
        dead_motion.keepId(0);
        dead_motion.update(240,250);
        dead_motion_1.keepId(0);
        dead_motion_1.update(480,250);
        atomic_elec_motion.keepId(0);
        atomic_elec_motion.update(240,250);

        /**falling fire path*/
        if(falling_fire_y < 250) falling_fire_y += 10;
//        falling_fire_motion.keepId(0);
//        falling_fire_motion.update(200,falling_fire_y);
        fallingFires.update(falling_fire_y);

        pointCocos.update(250);
        lighting.update(250);

        if(watership_x < 640) watership.update(watership_x+=20);

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

//            try {
//                mustExit = LWGameCanvas.rmidlet.platformRequest(LWGameCanvas.rmidlet.getAppProperty("return_url") + "/teamscreen.jsp");
//            } catch (ConnectionNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            if(mustExit){
//                LWGameCanvas.rmidlet.notifyDestroyed();
//                LWGameCanvas.rmidlet.exit();
//            }
            LWGameCanvas.isExit = true;
        }
    }

    private int hp_img_width;
    private int anger_img_width;

    public void draw(Graphics g) {

        hp_img_width -= 2;
        if(anger_img_width < anger_img.getWidth()) anger_img_width += 2;

        g.drawImage(mainBG, 0, 0, 20);
//        LWGameCanvas.rmidlet.getAppProperty("");

        g.setColor(0xffffff);

        /**falling fire draw*/
//        fallingFires.draw(g);

        /**point cocos draw*/
//        pointCocos.draw(g);

        motion.draw(g);
        red_h_motion.draw(g);

//        if(fighterInfo.length()>3){
//            left_atack_2_motion.draw(g);
//        }

        left_atack_2_motion.draw(g);

        left_atack_2_motion_h.draw(g);
        renwu_huonv_motion.draw(g);
        renwu_huonv_h_motion.draw(g);
        dead_motion.draw(g);
        dead_motion_1.draw(g);
        atomic_elec_motion.draw(g);

        /**lighting draw*/
//        lighting.draw(g);

        /**water ship draw*/
        watership.draw(g);

//        falling_fire_motion.draw(g);

//        eff_point_Coco_atk_motion.draw(g);
//        eff_point_Coco_atk_motion1.draw(g);
//        eff_point_Coco_atk_motion2.draw(g);
//        eff_point_Coco_atk_motion3.draw(g);

//        gripper_motion.draw(g);

//        g.setFont();

        g.drawImage(hourglass_img, 50, 20, Globe.ANCHOR_T_L);

        g.drawImage(title_2_13_img, 400, 20, Globe.ANCHOR_T_L);
        g.drawImage(chest_2_07_img, 410, 20, Globe.ANCHOR_T_L);
        g.drawImage(title_2_13_img, 500, 20, Globe.ANCHOR_T_L);
        g.drawImage(jewelry_2_05_img, 520, 20, Globe.ANCHOR_T_L);

        g.drawImage(anger_unfull_img, 100, 350, Globe.ANCHOR_T_L);
        g.drawImage(anger_unfull_img, 250, 350, Globe.ANCHOR_T_L);
        g.drawImage(anger_unfull_img, 400, 350, Globe.ANCHOR_T_L);

        g.drawImage(shadow_hero_2, 110, 360, Globe.ANCHOR_T_L);
        g.drawImage(shadow_monster_2, 260, 360, Globe.ANCHOR_T_L);
        g.drawImage(shadow_monster_l, 410, 360, Globe.ANCHOR_T_L);

//        g.drawImage(hp_img, 100, 440, Globe.ANCHOR_T_L);
        //绘制hp条
        g.setClip(100, 440, hp_img_width, hp_img.getHeight());
        g.drawImage(hp_img,  100, 440, 20);
        g.setClip(0, 0, Globe.SW, Globe.SH);
        g.setClip(250, 440, hp_img_width, hp_img.getHeight());
        g.drawImage(hp_img,  250, 440, 20);
        g.setClip(0, 0, Globe.SW, Globe.SH);
        g.setClip(400, 440, hp_img_width, hp_img.getHeight());
        g.drawImage(hp_img,  400, 440, 20);
        g.setClip(0, 0, Globe.SW, Globe.SH);
//        g.drawImage(hp_img, 250, 440, Globe.ANCHOR_T_L);
//        g.drawImage(hp_img, 400, 440, Globe.ANCHOR_T_L);

        //绘制anger条
        g.setClip(100, 450, anger_img_width, anger_img.getHeight());
        g.drawImage(anger_img,  100, 450, 20);
        g.setClip(0, 0, Globe.SW, Globe.SH);
//        g.drawImage(anger_img, 100, 450, Globe.ANCHOR_T_L);
        g.setClip(250, 450, anger_img_width, anger_img.getHeight());
        g.drawImage(anger_img,  250, 450, 20);
        g.setClip(0, 0, Globe.SW, Globe.SH);
//        g.drawImage(anger_img, 250, 450, Globe.ANCHOR_T_L);
        g.setClip(400, 450, anger_img_width, anger_img.getHeight());
        g.drawImage(anger_img,  400, 450, 20);
        g.setClip(0, 0, Globe.SW, Globe.SH);
//        g.drawImage(anger_img, 400, 450, Globe.ANCHOR_T_L);

        g.drawImage(bottom_02_img, 0, 500, Globe.ANCHOR_T_L);
        g.drawString("123", 100, 20, Globe.ANCHOR_T_H);


//        g.drawString(LWGameCanvas.rmidlet.getAppProperty("sessionId"), Globe.SW/2, Globe.SH/2, Globe.ANCHOR_T_H);


        g.drawString(LWGameCanvas.ret+"", 460, 20, Globe.ANCHOR_T_H);
        g.drawString(LWGameCanvas.chest_num+"", 560, 20, Globe.ANCHOR_T_H);

        g.drawString(LWGameCanvas.sum_enemy_hp_num+"", 460, 100, Globe.ANCHOR_T_H);
        g.drawString(LWGameCanvas.sum_hero_hp_num+"", 560, 100, Globe.ANCHOR_T_H);

        g.drawString("length: "+fighterInfo.length()+"", 50, 300, Globe.ANCHOR_T_H);

//        g.drawString(LWGameCanvas.rmidlet.getAppProperty("return_url")+"", 50, 300, Globe.ANCHOR_T_H);
//        g.drawImage(successfailure_img, 100, 100, Globe.ANCHOR_T_L);
//        g.drawImage(success_img, 190, 30, Globe.ANCHOR_T_L);

//        g.setColor(0x000000);
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

        //hero animation
        if(type == 0){
            if(fighter_id == 1){
                motion = new Motion("/hero/blue/blue.anu",250,250);
            }else if(fighter_id == 2){
                motion = new Motion("/hero/blue1/blue1.anu",250,250);
            }else if(fighter_id == 3){
                motion = new Motion("/hero/purple/purple.anu",250,250);
            }else if(fighter_id == 4){
                motion = new Motion("/hero/red/red.anu",250,250);
            }else if(fighter_id == 5){
                motion = new Motion("/hero/red1/red1.anu",250,250);
            }else if(fighter_id == 6){
                motion = new Motion("/hero/yellow/yellow.anu",250,250);
            }else if(fighter_id == 7){
                motion = new Motion("/hero/yellow1/yellow1.anu",250,250);
            }
        }

        //follow animation
        else if(type == 1){
            if(fighter_id == 8){
                left_atack_2_motion = new Motion("/follow/left/3/follow_left_3.anu",250,250);
            }else if(fighter_id == 9){
                left_atack_2_motion = new Motion("/follow/left/4/follow_left_4.anu",250,250);
            }else if(fighter_id == 10){
                left_atack_2_motion = new Motion("/follow/left/5/follow_left_5.anu",250,250);
            }else if(fighter_id == 11){
                left_atack_2_motion = new Motion("/follow/left/6/follow_left_6.anu",250,250);
            }else if(fighter_id == 12){
                left_atack_2_motion = new Motion("/follow/left/7/follow_left_7.anu",250,250);
            }else if(fighter_id == 13){
                left_atack_2_motion = new Motion("/follow/left/8/follow_left_8.anu",250,250);
            }else if(fighter_id == 14){
                left_atack_2_motion = new Motion("/follow/left/9/follow_left_9.anu",250,250);
            }else if(fighter_id == 15){
                left_atack_2_motion = new Motion("/follow/left/10/follow_left_10.anu",250,250);
            }else if(fighter_id == 16){
                left_atack_2_motion = new Motion("/follow/left/3/follow_left_3.anu",250,250);
            }else if(fighter_id == 17){
                left_atack_2_motion = new Motion("/follow/left/3/follow_left_3.anu",250,250);
            }else if(fighter_id == 18){
                left_atack_2_motion = new Motion("/follow/left/3/follow_left_3.anu",250,250);
            }else if(fighter_id == 19){
                left_atack_2_motion = new Motion("/follow/left/3/follow_left_3.anu",250,250);
            }
        }

        //pets animation
        else if(type == 2){
            if(fighter_id == 20){
                renwu_huonv_motion = new Motion("/pets1/left/bat-5.anu",250,250);
            }else if(fighter_id == 21){
                renwu_huonv_motion = new Motion("/pets1/left/bird-7.anu",250,250);
            }else if(fighter_id == 22){
                renwu_huonv_motion = new Motion("/pets1/left/clown-3.anu",250,250);
            }else if(fighter_id == 23){
                renwu_huonv_motion = new Motion("/pets1/left/frogfish-2.anu",250,250);
            }else if(fighter_id == 24){
                renwu_huonv_motion = new Motion("/pets1/left/horse-1.anu",250,250);
            }else if(fighter_id == 25){
                renwu_huonv_motion = new Motion("/pets1/left/mouse-8.anu",250,250);
            }else if(fighter_id == 26){
                renwu_huonv_motion = new Motion("/pets1/left/rabbit-4.anu",250,250);
            }else if(fighter_id == 27){
                renwu_huonv_motion = new Motion("/pets1/left/tiger-6.anu",250,250);
            }else if(fighter_id == 28){
                renwu_huonv_motion = new Motion("/pets1/left/tortoise-9.anu",250,250);
            }
        }

    }

}
