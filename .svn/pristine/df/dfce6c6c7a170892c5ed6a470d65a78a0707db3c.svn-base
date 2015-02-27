package elements;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

//import screens.GamingScreen;

import common.GameVariables;
import common.Globe;

public class Road {
	public int x = 0;
	public int y = 0;
	public int len = 0;
	Image src;// º”…œª∫¥Ê
	public int type = 0;
	private int num = 1;
	public boolean active = true;

	public Road(int type, int HeroX, int HeroY, int len) {
		this.x = HeroX;
		this.y = HeroY;
		this.type = type;
		this.len = len;
		switch (type) {
		case 0:
			src = null;
			break;
		case 1:
            src = null;
//			src = GamingScreen.mapHead;
			break;
		case 2:
            src = null;
//			src = GamingScreen.mapBody;
			break;
		case 3:
            src = null;
//			src = GamingScreen.mapEnd;
			break;
		}
		if (null != src) {
			this.len = src.getWidth();
		}else {
//			if (GamingScreen.isFillYun) {
//				GamingScreen.numFillYun --;
//			}
		}
	}

	public void update() {

//		x -= GamingScreen.bgMoveFram;
		if (x < -100) {
//			GamingScreen.isAddMap = true;
//			GamingScreen.moveCount++;
//			GamingScreen.roadVec.removeElement(this);
			this.active = false;
		}
		if (src == null) {
//			if (GamingScreen.isFillYun && GamingScreen.numFillYun > 0) {
//                src = null;
//				src = GamingScreen.imgFillYun;
//				type = 4;
//				num = len / src.getWidth() > 1 ? len / src.getWidth() : 1;
//			}else {
//				num = 1;
//				GamingScreen.numFillYun = Hero.heroXiangYun[GameVariables.heroIndex];
//				GamingScreen.isFillYun = false;
//			}
		}
	}

	public void draw(Graphics g) {

		if (src != null) {
			for (int i = 0; i < num; i++) {
				g.drawImage(src, x + i * src.getWidth(), Globe.SH * 2 / 3, Globe.ANCHOR_T_L);
			}
		}
	}
}
