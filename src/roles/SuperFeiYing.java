package roles;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/3/6.
 */
public class SuperFeiYing implements Role{

    Motion superfeiying_motion;

    public SuperFeiYing() {
        superfeiying_motion = new Motion("/hero/blue1/blue1.anu",250,250);
        superfeiying_motion.keepId(0);
    }

    public void update() {
        superfeiying_motion.update(300, 300);
        superfeiying_motion.keepId(0);
    }

    public void draw(Graphics g) {
        superfeiying_motion.draw(g);
    }

}
