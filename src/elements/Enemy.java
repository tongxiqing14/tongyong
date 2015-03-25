package elements;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import motion.Motion;
//import screens.GamingScreen;

import common.GameVariables;
import common.Globe;

public class Enemy {
	public int factX = 0;
	public int factY = 0;
	public int drawY = 0;
	public int type = 0;
	public Motion deadMotion;
	public boolean isActive = true;
	public static final int STATE_ACTIVE = 0;
	public boolean isVisible = true;
	public Image img;
	public int flag = 0;
	public int speed;
	private int width;
	private int height;
	private int posx;
	private int posy;
	public int state = STATE_ACTIVE;
	public Motion motion;

	public int Y = 0;//加蓝提示字符的Y轴坐标
	public int X = 0;//加蓝提示字符的Y轴坐标
	public int lanFrame = 0;//加蓝提示字符计数器

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public Enemy(int type) {
		this.type = type;
		init();
	}
	public Enemy(int type, int faceX, int faceY) {
		this(type);
		this.factX = faceX;
		this.factY = faceY;
		if (type == 14) {
			isVisible = false;
		}
	}

	public void setFactX(int factX) {
		this.factX = factX;
	}

	public void update() {
		if (isActive) {
//			if (GamingScreen.hero.isChong && factX < Globe.SW >> 2 && type != 16) {
//				isActive = false;
//				return;
//			}
			if (factX < -100) {
				isActive = false;
				return;
			}
			if (type != 17) {
//				speed = GamingScreen.bgMoveFram;
				factX = factX - speed;
			}
			drawY = factY;
			flag++;
			if (type == 16) {
				//speed *= 2;
				factX = factX - speed;
				motion.keepId(0);
				motion.update(factX, factY);
			}
			if (type == 13 || type == 14) {
//				if (GamingScreen.hero.isXiJin || GamingScreen.hero.isChong) {
//					int[] VXY =Globe.getAng(GamingScreen.hero.getPosX(), GamingScreen.hero.getPosY(),factX, factY,Hero.heroSuckStar[GameVariables.heroIndex]);
//					factX += VXY[0];
//					factY += VXY[1];
//				}
				motion.keepId(0);
				motion.update(factX, factY);
			}
			if (type == 14) {
				if (factY < 333) {
					int[] VXY = Globe.getAng( factX + 70, factY,Globe.SW >> 2 + 150, 338, 20);
					factX += VXY[0];
					factY -= VXY[1];
				}else {
					isVisible = true;
				}
				motion.keepId(0);
				motion.update(factX, factY);
			}
			if (type == 17) {
				motion.keepId(0);
				motion.update(factX, factY);
			}
		} else {
			if (factX > 0) {
				lanFrame++;
				if (lanFrame == 1) {
					X = factX;
					Y = factY;
				}
			}
			if (deadMotion != null) {
				deadMotion.setId(0, 1);
				deadMotion.update(posx + (width >> 1), posy + height);
				// deadMotion.keepId(0);
				if(deadMotion.isEnd())
				{
					deadMotion = null;
				}
			}
			if(Y <480){
			int[] VXY = Globe.getAng( X, Y,10, 480, 5);
			X -= VXY[0];
			Y -= VXY[1];
			}
		}

	}

	public void draw(Graphics g) {

		int tempval = (flag % 6 < 3) ? 0 : 1;//更新频率
		if (!isActive) {
			if (deadMotion != null) {
				deadMotion.draw(g);
			}
			g.setColor(255,0,0);

//			g.drawString("+1", X, Y, 20);
			if(factX>0 && lanFrame>0)
			{
//				Globe.drawNum(g, 1,X, Y, GamingScreen.imgNUM, 0);
			}
		} else {
			switch (type) {
			case 0:
				g.drawRegion(img, tempval == 0 ? 0 : 119, 0, tempval == 0 ? 119 : 100, 72, 0, factX + tempval * 15,
						factY - tempval * 12, Graphics.TOP | Graphics.LEFT);
				break;
			case 1:
				g.drawRegion(img, tempval == 0 ? 0 : 65, 0, tempval == 0 ? 65 : 64, img.getHeight(), 0, factX, factY,
						Graphics.TOP | Graphics.LEFT);
				break;
			case 2:
				g.drawRegion(img, tempval == 0 ? 0 : 92, 0, 92, 84, 0, factX, factY, Graphics.TOP | Graphics.LEFT);
				break;
			case 3:
				g.drawRegion(img, tempval == 0 ? 0 : 76, 0, tempval == 0 ? 76 : 75, 91, 0, factX, factY, Graphics.TOP
						| Graphics.LEFT);
				break;
			case 4:
				g.drawRegion(img, tempval == 0 ? 0 : 63, 0, 63, 63, 0, factX, factY, Graphics.TOP | Graphics.LEFT);
				break;
			case 5:
				g.drawRegion(img, tempval == 0 ? 0 : 72, 0, 72, 79, 0, factX, factY, Graphics.TOP | Graphics.LEFT);
				break;
			case 6:
				g.drawRegion(img, tempval == 0 ? 0 : 83, 0, 83, 83, 0, factX, factY, Graphics.TOP | Graphics.LEFT);
				break;
			case 7:
				g.drawRegion(img, tempval == 0 ? 0 : 68, 0, tempval == 0 ? 68 : 67, 90, 0, factX, factY, Graphics.TOP
						| Graphics.LEFT);
				break;
			case 8:
				g.drawRegion(img, tempval == 0 ? 0 : 90, 0, 90, 95, 0, factX, factY, Graphics.TOP | Graphics.LEFT);
				break;
			case 9:
				g.drawRegion(img, tempval == 0 ? 0 : 61, 0, tempval == 0 ? 61 : 60, 61, 0, factX, factY, Graphics.TOP
						| Graphics.LEFT);
				break;
			case 10:
				g.drawRegion(img, tempval == 0 ? 0 : 87, 0, tempval == 0 ? 87 : 86, 64, 0, factX, factY, Graphics.TOP
						| Graphics.LEFT);
				break;
			case 11:
				g.drawRegion(img, tempval == 0 ? 0 : 74, 0, 74, 62, 0, factX, factY, Graphics.TOP | Graphics.LEFT);
				break;
			case 12:
				g.drawRegion(img, tempval == 0 ? 0 : 57, 0, tempval == 0 ? 57 : 56, 61, 0, factX, factY, Graphics.TOP
						| Graphics.LEFT);
				break;
			case 13:
			case 14:
				motion.draw(g);
				break;
			case 15:
				g.drawImage(img, factX, factY, Graphics.TOP | Graphics.LEFT);
				break;
			case 16:
			case 17:
				motion.draw(g);
				break;
			}

		}
		if (type == 0) {

			if (tempval == 0) {
				height = 45;
				width = 116;
				posx = factX;
				posy = factY+20;

			} else {
				height = 65;
				width = 100;
				posx = factX + 10;
				posy = factY - 12;
			}
		} else if (type == 13 || type == 14|| type == 16 || type == 17) {
			width = 51;
			height = 49;
			posx = factX - (width >> 1);
			posy = factY - height;
		} else {
			height = img.getHeight()-20;
			width = (img.getWidth() >> 1)-20;
			posx = factX+5;
			posy = factY+5;

		}
	}

    public static Image img0;
    public static Image img1;
    public static Image img2;
    public static Image img3;
    public static Image img4;
    public static Image img5;
    public static Image img6;
    public static Image img7;
    public static Image img8;
    public static Image img9;
    public static Image img10;
    public static Image img11;
    public static Image img12;

	private void init() {
		if (type < 16) {
			deadMotion = new Motion("/game/siwang/siwangyan.anu", posx, posy);
		}
		switch (type) {
		case 0:
			if(img0==null)
                img0 = Globe.download.creatImage("gaming/bianfu/bianfu.png");
            img = img0;
			this.factX = 650;
			this.factY = 150;
			break;
		case 1:
            if(img1==null)
                img1 = Globe.download.creatImage("gaming/smalldragon/smalldragon.png");
            img = img1;
			this.factX = 650;
			this.factY = 260;
			break;
		case 2:
            if(img2==null)
                img2 = Globe.download.creatImage("gaming/dragon/dalong.png");
            img = img2;
			this.factX = 650;
			this.factY = 250;
			break;
		case 3:
            if(img3==null)
                img3 = Globe.download.creatImage("gaming/qie/daqie.png");
            img = img3;
			this.factX = 650;
			this.factY = 266;
			break;
		case 4:
            if(img4==null)
                img4 = Globe.download.creatImage("gaming/samllsnowbow/samllsnowbow.png");
            img = img4;
			this.factX = 650;
			this.factY = 294;
			break;
		case 5:
            if(img5==null)
                img5 = Globe.download.creatImage("gaming/shilaimu/shilaimu.png");
            img = img5;
			this.factX = 650;
			this.factY = 276;
			break;
		case 6:
            if(img6==null)
                img6 = Globe.download.creatImage("gaming/dachou/dachou.png");
            img = img6;
			this.factX = 650;
			this.factY = 272;
			break;
		case 7:
            if(img7==null)
                img7 = Globe.download.creatImage("gaming/snowbow/snowbow.png");
            img = img7;
			this.factX = 650;
			this.factY = 268;
			break;
		case 8:
            if(img8==null)
                img8 = Globe.download.creatImage("gaming/xianrenzhang/xianrenzhang.png");
            img = img8;
			this.factX = 650;
			this.factY = 266;
			break;
		case 9:
            if(img9==null)
                img9 = Globe.download.creatImage("gaming/xiaochou/xiaochou.png");
            img = img9;
			this.factX = 650;
			this.factY = 286;
			break;
		case 10:
            if(img10==null)
                img10 = Globe.download.creatImage("gaming/xiaoqie/xiaoqie.png");
            img = img10;
			this.factX = 650;
			this.factY = 292;
			break;
		case 11:
            if(img11==null)
                img11 = Globe.download.creatImage("gaming/xiaoshilaimu/xiaoshilaimu.png");
            img = img11;
			this.factX = 650;
			this.factY = 293;
			break;
		case 12:
            if(img12==null)
                img12 = Globe.download.creatImage("gaming/xiaoxianren/xiaoxianren.png");
            img = img12;
			this.factX = 650;
			this.factY = 296;
			break;
		case 13:
			motion = new Motion("/game/star.anu", this.factX, this.factY);
			break;
		case 14:
			motion = new Motion("/game/star14.anu", -30, -30);
			break;
		case 15:
			img = Globe.download.creatImage("enemy/dici.png");
			this.factX = 650;
			this.factY = 295;
			break;
		case 16:
			motion = new Motion("/effect/texiao.anu", this.factX, this.factY);
			break;
		case 17:
			motion = new Motion("/effect/alarm.anu", this.factX, this.factY);
			break;
		}
	}

}
