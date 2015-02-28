package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/28.
 */
public class Hurricane implements Effect{

    private Motion hurricane_motion;

    public Hurricane() {
        hurricane_motion = new Motion("/effect2/dazhao1/dazhao1.anu",50,50);
        hurricane_motion.keepId(0);
    }

    public void update(int falling_fire_y) {
        hurricane_motion.keepId(0);
        hurricane_motion.update(falling_fire_y,200);
    }

    public void draw(Graphics g) {
        hurricane_motion.draw(g);
    }

}
