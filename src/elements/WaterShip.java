package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/27.
 */
public class WaterShip implements Effect {

    private Motion water_ship_motion;
    private int water_ship_xx;

    public WaterShip() {
        water_ship_motion = new Motion("/effect2/watership/watership.anu",50,50);
        water_ship_motion.keepId(0);
    }

    public void update(int water_ship_x) {
        if(water_ship_xx < 600){
            water_ship_xx += 30;
            water_ship_motion.keepId(0);
            water_ship_motion.update(water_ship_xx,200);
        }
    }

    public void draw(Graphics g) {
        if(water_ship_xx < 600){
            water_ship_motion.draw(g);
        }
    }

}
