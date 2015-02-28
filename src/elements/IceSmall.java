package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/28.
 */
public class IceSmall implements Effect{

    private Motion icesmall_motion;

    public IceSmall() {
        icesmall_motion = new Motion("/effect2/ice/icesmall.anu",50,50);
        icesmall_motion.keepId(0);
    }

    public void update(int falling_fire_y) {
        icesmall_motion.keepId(0);
        icesmall_motion.update(falling_fire_y,200);
    }

    public void draw(Graphics g) {
        icesmall_motion.draw(g);
    }

}
