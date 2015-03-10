package roles;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/3/6.
 */
public class XingTian implements Role{

    Motion xingtian_motion;

    public XingTian() {
        xingtian_motion = new Motion("/hero/blue/blue.anu",250,250);
        xingtian_motion.keepId(0);
    }

    public void update() {
        xingtian_motion.update(300, 300);
        xingtian_motion.keepId(0);
    }

    public void draw(Graphics g) {
        xingtian_motion.draw(g);
    }

}
