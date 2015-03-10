package roles;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/3/6.
 */
public class JinGang implements Role{

    Motion jingang_motion;

    public JinGang() {
        jingang_motion = new Motion("/hero/yellow/yellow.anu",250,250);
        jingang_motion.keepId(0);
    }

    public void update() {
        jingang_motion.update(300, 300);
        jingang_motion.keepId(0);
    }

    public void draw(Graphics g) {
        jingang_motion.draw(g);
    }

}
