package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/28.
 */
public class IceSmall implements Effect{

    private Motion[] icesmall_motion;

    public IceSmall() {
        icesmall_motion = new Motion[3];

        for (int i=0; i<icesmall_motion.length; i++){
            icesmall_motion[i] = new Motion("/effect2/ice/icesmall.anu",50,50);
            icesmall_motion[i].keepId(0);
        }

    }

    public void update(int falling_fire_y) {
        icesmall_motion[0].keepId(0);
        icesmall_motion[0].update(falling_fire_y-50, 230);

        icesmall_motion[1].keepId(0);
        icesmall_motion[1].update(falling_fire_y, 260);

        icesmall_motion[2].keepId(0);
        icesmall_motion[2].update(falling_fire_y+50, 290);

    }

    public void draw(Graphics g) {
        icesmall_motion[0].draw(g);
        icesmall_motion[1].draw(g);
        icesmall_motion[2].draw(g);
    }

}
