package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/4/8.
 */
public class Lightning_H implements Effect{

    private Motion lighting_motion;

    public Lightning_H() {
        lighting_motion = new Motion("/effect2/evocation.big/lightning/lightning_h.anu",50,50);
        lighting_motion.keepId(0);
    }

    public void update(int falling_fire_y) {
        lighting_motion.keepId(0);
        lighting_motion.update(200,200);
    }

    public void draw(Graphics g) {
        lighting_motion.draw(g);
    }

}
