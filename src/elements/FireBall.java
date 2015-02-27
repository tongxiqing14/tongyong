package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/27.
 */
public class FireBall implements Effect{

    private Motion[] fire_ball_motion;

    public FireBall() {

        fire_ball_motion = new Motion[4];

        for(int i=0; i<fire_ball_motion.length; i++){
            fire_ball_motion[i] = new Motion("/effect2/fireball/fireball.anu",50,50);
            fire_ball_motion[i].keepId(0);
        }
    }

    public void update(int falling_fire_y) {
        fire_ball_motion[0].keepId(0);
        fire_ball_motion[0].update(falling_fire_y, 100);

        fire_ball_motion[1].keepId(0);
        fire_ball_motion[1].update(falling_fire_y, 300);

        fire_ball_motion[2].keepId(0);
        fire_ball_motion[2].update(falling_fire_y+100, 100);

        fire_ball_motion[3].keepId(0);
        fire_ball_motion[3].update(falling_fire_y+100, 300);
    }

    public void draw(Graphics g) {
        fire_ball_motion[0].draw(g);
        fire_ball_motion[1].draw(g);
        fire_ball_motion[2].draw(g);
        fire_ball_motion[3].draw(g);
    }

}
