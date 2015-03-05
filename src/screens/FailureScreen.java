package screens;

import Entry.LWGameCanvas;
import common.Globe;
import common.NetInfo;
import common.Screen;
import iptvNet.IptvNetException;
import motion.Motion;
import org.json.me.JSONException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Created by tongxiqing on 2015/2/6.
 */
public class FailureScreen extends Screen {

    Image successfailure_img;
    Image success_img;
    Image que_img;
    Image mouse_img;

    Motion mouse_motion;

    public FailureScreen(int screenId) {
        super(screenId);
    }

//    @java.lang.Override
    public void init() {
        successfailure_img = Globe.getImage("menu/successfailure.png");
        success_img = Globe.getImage("menu/failure.png");
        que_img = Globe.getImage("menu/que.png");
        mouse_img = Globe.getImage("menu/mouse.png");

        mouse_motion = new Motion("/menu/mouse.anu",250,290);
        mouse_motion.keepId(0);
    }

//    @java.lang.Override
    public void update() {

        mouse_motion.keepId(0);
        mouse_motion.update(340,330);

        if(LWGameCanvas.iskeyPressed(Globe.M_KEY_OK)){

            try {
                NetInfo.netHander.screenToPageAction("armorbasescreen.jsp?preScreen=mainscreen");
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IptvNetException e) {
                e.printStackTrace();
            }

            LWGameCanvas.isExit = true;
        }

    }

//    @java.lang.Override
    public void draw(Graphics g) {
        g.setColor(0xffffff);

        g.drawImage(successfailure_img, 100, 100, Globe.ANCHOR_T_L);
        g.drawImage(success_img, 190, 40, Globe.ANCHOR_T_L);

        g.drawImage(que_img, 270, 310, Globe.ANCHOR_T_L);

        g.setFont(Globe.BigBoldFont);
        g.drawString("纳尼？没有过关！快去训练等级吧", 300, 165, Globe.ANCHOR_T_H);
        g.drawString("加血量，加攻击力助你轻松过关！", 300, 195, Globe.ANCHOR_T_H);

        mouse_motion.draw(g);
    }

//    @java.lang.Override
    public void clear() {
        successfailure_img = null;
        success_img = null;
        que_img = null;
        Motion.clear();
        Runtime.getRuntime().gc();
    }

}
