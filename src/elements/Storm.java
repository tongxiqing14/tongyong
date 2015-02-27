package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/27.
 */
public class Storm implements Effect {

    private Motion[] storm_motion;

    public Storm() {
        storm_motion = new Motion[4];

        for (int i=0; i<storm_motion.length; i++){
            storm_motion[i] = new Motion("/effect2/storm/storm.anu",50,50);
            storm_motion[i].keepId(0);
        }

    }

    public void update(int falling_fire_y) {
        storm_motion[0].keepId(0);
        storm_motion[0].update(falling_fire_y, 100);

        storm_motion[1].keepId(0);
        storm_motion[1].update(falling_fire_y, 300);

        storm_motion[2].keepId(0);
        storm_motion[2].update(falling_fire_y+100, 100);

        storm_motion[3].keepId(0);
        storm_motion[3].update(falling_fire_y+100, 300);
    }

    public void draw(Graphics g) {
        storm_motion[0].draw(g);
        storm_motion[1].draw(g);
        storm_motion[2].draw(g);
        storm_motion[3].draw(g);
    }

}
