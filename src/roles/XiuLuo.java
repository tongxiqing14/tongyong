package roles;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/3/6.
 */
public class XiuLuo implements Role{

    Motion xiuLuo_motion;

    public XiuLuo() {
        xiuLuo_motion = new Motion("/hero/yellow1/yellow1.anu",250,250);
        xiuLuo_motion.keepId(0);
    }

    public void update() {
        xiuLuo_motion.update(300, 300);
        xiuLuo_motion.keepId(0);
    }

    public void draw(Graphics g) {
        xiuLuo_motion.draw(g);
    }

}
