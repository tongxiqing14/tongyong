package common;

import javax.microedition.lcdui.Graphics;

public abstract class Screen {
	public int screenId = 0;
	public static final int SCREENTYPE_ALL=0;
	public static final int SCREENTYPE_DRAW=SCREENTYPE_ALL+1;
	public int screenType=SCREENTYPE_ALL;
	private boolean isActive = false;
	public Screen(int screenId){
		this.screenId = screenId;
//		JVMGameCanvas.keyReset();
	}
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics g);
	public abstract void clear();
	
	public void setActive(boolean isActive){
		this.isActive = isActive;
	}
	
	public boolean getActive(){
		return isActive;
	}
	
}
