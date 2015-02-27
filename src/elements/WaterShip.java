package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/27.
 */
public class WaterShip implements Effect {

    private Motion water_ship_motion;

    public WaterShip() {
        water_ship_motion = new Motion("/effect2/watership/watership.anu",50,50);
        water_ship_motion.keepId(0);
    }

    public void update(int falling_fire_y) {
        water_ship_motion.keepId(0);
        water_ship_motion.update(falling_fire_y,200);
    }

    public void draw(Graphics g) {
        water_ship_motion.draw(g);
    }

}
