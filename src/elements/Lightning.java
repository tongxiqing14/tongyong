package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/26.
 */
public class Lightning implements Effect{

    private Motion lighting_motion;
    private int lighting_x = 360;
    private int frameCount = 0;
    private int speedCount = 0;

    public Lightning() {
        lighting_motion = new Motion("/effect2/evocation.big/lightning/lightning.anu",50,50);
        lighting_motion.keepId(0);
    }

    public void update(int falling_fire_y) {

         if((speedCount+3)%3 == 0){

             if(frameCount < lighting_motion.getCountFrame()){
                 lighting_motion.keepId(0);
                 lighting_motion.update(lighting_x,200);

                 frameCount ++;
             }
             speedCount = 0;
         }
        speedCount ++;
    }

    public void draw(Graphics g) {

        if(frameCount < lighting_motion.getCountFrame()){
            lighting_motion.draw(g);
        }

    }
}
