package elements;

import javax.microedition.lcdui.Graphics;

/**
 * Created by tongxiqing on 2015/2/12.
 */
public interface Effect {

    public void init();

    public boolean isOver();

    public void update(int falling_fire_y);       //

    public void draw(Graphics g);

}
