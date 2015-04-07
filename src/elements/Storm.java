package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/27.
 */
public class Storm implements Effect {

    private Motion[] storm_motion;
    private int storm_x = 0;

    public Storm() {
        storm_motion = new Motion[4];

        for (int i=0; i<storm_motion.length; i++){
            storm_motion[i] = new Motion("/effect2/storm/storm.anu",50,50);
            storm_motion[i].keepId(0);
        }

    }

    public void update(int falling_fire_y) {

        if(storm_x < 640){
            storm_motion[0].keepId(0);
            storm_motion[0].update(storm_x, 160);

            storm_motion[1].keepId(0);
            storm_motion[1].update(storm_x, 320);

            storm_motion[2].keepId(0);
            storm_motion[2].update(storm_x+100, 160);

            storm_motion[3].keepId(0);
            storm_motion[3].update(storm_x+100, 320);

            storm_x += 10;
        }

    }

    public void draw(Graphics g) {

        if(storm_x < 640){
            storm_motion[0].draw(g);
            storm_motion[1].draw(g);
            storm_motion[2].draw(g);
            storm_motion[3].draw(g);
        }

    }

}
