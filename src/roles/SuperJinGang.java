package roles;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/3/6.
 */
public class SuperJinGang implements Role{

    Motion superJinGang_motion;

    public SuperJinGang() {
        superJinGang_motion = new Motion("/hero/yellow1/yellow1.anu",250,250);
        superJinGang_motion.keepId(0);
    }

    public void update() {
        superJinGang_motion.update(300, 300);
        superJinGang_motion.keepId(0);
    }

    public void draw(Graphics g) {
        superJinGang_motion.draw(g);
    }

}
