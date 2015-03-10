package roles;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/3/6.
 */
public class FeiYing implements Role{

    Motion feiying_motion;

    public FeiYing() {
        feiying_motion = new Motion("/hero/blue/blue.anu",250,250);
        feiying_motion.keepId(0);
    }

    public void update() {
        feiying_motion.update(300, 300);
        feiying_motion.keepId(0);
    }

    public void draw(Graphics g) {
        feiying_motion.draw(g);
    }

}
