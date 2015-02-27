package Entry;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class LWGameMidlet extends MIDlet {

	Display dis;
	LWGameCanvas rc;
	
	public LWGameMidlet() {
		// TODO Auto-generated constructor stub
		dis = Display.getDisplay(this);
		rc = new LWGameCanvas(this);
	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		dis.setCurrent(rc);
	}

	public void exit(){
		try {
			destroyApp(true);
		} catch (MIDletStateChangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notifyDestroyed();
	}

}
