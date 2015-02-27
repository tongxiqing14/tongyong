package elements;

import motion.Motion;
import screens.WorriesFightScreen;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/25.
 */
public class PointCoco implements Effect{

    private Motion[] point_fire_motion;
    private int falling_fire_x;
    private int[] falling_fire_x_array = {350,430,430,510,510};
    private int[] falling_fire_y_array = {250,200,300,180,320};
    private int[] pos_y_array = {0, 20, 90, 150, 90, 20, 0};

    public PointCoco(int id, int falling_fire_x, int falling_fire_y) {
        this.falling_fire_x = falling_fire_x;

        point_fire_motion = new Motion[5];

        for (int i=0; i<point_fire_motion.length; i++){
            point_fire_motion[i] = new Motion("/effect2/point_Coco/eff_point_Coco_atk.anu",falling_fire_x,falling_fire_y);
            point_fire_motion[i].keepId(0);
        }

    }

    public void update(int falling_fire_y) {

        for (int i=0; i<point_fire_motion.length; i++){
            if(i<WorriesFightScreen.pos_y_array.length)
                WorriesFightScreen.pos_y_array[i] = point_fire_motion[i].getCollied(0)[1]+point_fire_motion[i].getSpriteDrawY();
            point_fire_motion[i].keepId(0);
            point_fire_motion[i].update(falling_fire_x_array[i], falling_fire_y_array[i]);
        }
    }

    public void draw(Graphics g) {
        for (int i=0; i<point_fire_motion.length; i++){
            point_fire_motion[i].draw(g);
        }
    }
}
