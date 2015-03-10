package roles;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/3/6.
 */
public class SuperXingTian implements Role{

    Motion superxingtian_motion;

    public SuperXingTian() {
        superxingtian_motion = new Motion("/hero/red1/red1.anu",250,250);
        superxingtian_motion.keepId(0);
    }

    public void update() {
        superxingtian_motion.update(300, 300);
        superxingtian_motion.keepId(0);
    }

    public void draw(Graphics g) {
        superxingtian_motion.draw(g);
    }

}
