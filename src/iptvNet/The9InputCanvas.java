package iptvNet;


import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

public class The9InputCanvas extends Canvas {
	
	The9InputScreen tis;
	Object canvas;
	MIDlet myMid;
	Object ob = new Object();
	
	public boolean isPayOK(){
		return tis.isPayOk();
	}
	
	public boolean isPayEnd(){
		return tis.isPayEnd();
	}
	
	public The9InputCanvas(MIDlet mid,NetHander netHander,int price,int rechargeNum,String wareName){
		setFullScreenMode(true);
		myMid = mid;
		tis = new The9InputScreen(netHander,price,rechargeNum,wareName);
		canvas = Display.getDisplay(mid).getCurrent();
	}
	
	public void start(){
		Display.getDisplay(myMid).setCurrent(this);
		synchronized (ob) {
			try {
				ob.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void paint(Graphics g) {
		// TODO Auto-generated method stub
		tis.draw(g);
	}

	protected void keyPressed(int keycode) {
		if(tis!=null){
//			tis.update(keycode);
			if(tis.isPayEnd()){
				synchronized (ob) {
					ob.notify();
				}
			}
		}
		repaint();
	}
	
	public void end(){
		Display.getDisplay(myMid).setCurrent((Displayable)canvas);
	}

}
