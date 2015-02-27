package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/26.
 */
public class Lightning implements Effect{

    private Motion lighting_motion;

    public Lightning() {
        lighting_motion = new Motion("/effect2/evocation.big/lightning/lightning.anu",50,50);
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
