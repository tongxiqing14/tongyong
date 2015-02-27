package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/27.
 */
public class FireBall implements Effect{

    private Motion fire_ball_motion;

    public FireBall() {
        fire_ball_motion = new Motion("/effect2/fireball/fireball.anu",50,50);
        fire_ball_motion.keepId(0);
    }

    public void update(int falling_fire_y) {
        fire_ball_motion.keepId(0);
        fire_ball_motion.update(falling_fire_y,200);
    }

    public void draw(Graphics g) {
        fire_ball_motion.draw(g);
    }

}
