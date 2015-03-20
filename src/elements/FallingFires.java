package elements;

import motion.Motion;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/12.
 */
public class FallingFires implements Effect{

    private int animid;
    private int falling_fire_y;
    private Motion[] falling_fire_motion;

    public FallingFires(int id, int falling_fire_x, int falling_fire_y) {
        this.animid = id;
        falling_fire_motion = new Motion[4];

        for (int i=0; i<falling_fire_motion.length; i++){
            falling_fire_motion[i] = new Motion("/effect2/lava.serie/falling.fire/falling_fire.anu",falling_fire_x,falling_fire_y);
            falling_fire_motion[i].keepId(animid);
        }

    }

    public void update(int falling_fire_y__){

        /**falling fire path*/
        if(falling_fire_y < 300) falling_fire_y += 20;

        for (int i=0; i<falling_fire_motion.length; i++){
            falling_fire_motion[i].keepId(animid);
            falling_fire_motion[i].update(100+i*75, falling_fire_y);
        }

    }

    public void draw(Graphics g){

        if(falling_fire_y < 300){
            for (int i=0; i<falling_fire_motion.length; i++){
                falling_fire_motion[i].draw(g);
            }
        }

    }

}
