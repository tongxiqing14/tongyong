package common;

import java.util.Random;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import motion.Motion;
import net.jscience.util.MathFP;
import download.Download;
import elements.Enemy;
import elements.Hero;

public class Globe {

	public static int SW = 640;
	public static int SH = 530;
	public static Download download;
	
	public static int downloadStage=0;
	public static boolean downloadOver=false;

	public static int token = 0;
	public static int needMoreToken=0;
	public static boolean isAutoTopUp=false;
	public static boolean isEndLess=false;

    public static int confirmScreenId;
	
	public static final int ANCHOR_T_L = Graphics.TOP | Graphics.LEFT;
	public static final int ANCHOR_H_V = Graphics.HCENTER | Graphics.VCENTER;
	public static final int ANCHOR_T_H = Graphics.TOP | Graphics.HCENTER;
	/**
	 * 每帧的时间
	 */
	public static final int SLEEP_TIME = 60;

	/********************************************************/
	/*************** 翻转参数 *****************/
	/********************************************************/
	public static final int TRANS_MIRROR = 2;
	public static final int TRANS_NONE = 0;

	/********************************************************/
	/*************** 按键键值 *****************/
	/********************************************************/
	public static int keyBuff = 0;
	public final static int KEY_UP = -1;
	public final static int KEY_DOWN = -2;
	public final static int KEY_LEFT = -3;
	public final static int KEY_RIGHT = -4;
	public final static int KEY_SOFT_L = -6;// left soft key
	public final static int KEY_SOFT_R = -7;// right soft key
	public final static int KEY_KEY3 = 7;// right soft key
	public final static int KEY_KEY4 = -31;// right soft key
	public final static int KEY_OK = -5;
	public final static int KEY_0 = 48;
	public final static int KEY_1 = 49;
	public final static int KEY_2 = 50;
	public final static int KEY_3 = 51;
	public final static int KEY_4 = 52;
	public final static int KEY_5 = 53;
	public final static int KEY_6 = 54;
	public final static int KEY_7 = 55;
	public final static int KEY_8 = 56;
	public final static int KEY_9 = 57;

	public final static int M_KEY_UP = 1 << 24;
	public final static int M_KEY_DOWN = 1 << 23;
	public final static int M_KEY_LEFT = 1 << 22;
	public final static int M_KEY_RIGHT = 1 << 21;
	public final static int M_KEY_SOFT_L = 1 << 20;// left soft key
	public final static int M_KEY_SOFT_R = 1 << 19;// right soft key
	public final static int M_KEY_KEY3 = 1 << 18;// right soft key
	public final static int M_KEY_OK = 1 << 17;
	public final static int M_KEY_0 = 1 << 16;
	public final static int M_KEY_1 = 1 << 15;
	public final static int M_KEY_2 = 1 << 14;
	public final static int M_KEY_3 = 1 << 13;
	public final static int M_KEY_4 = 1 << 12;
	public final static int M_KEY_5 = 1 << 11;
	public final static int M_KEY_6 = 1 << 10;
	public final static int M_KEY_7 = 1 << 9;
	public final static int M_KEY_8 = 1 << 8;
	public final static int M_KEY_9 = 1 << 7;

	/********************************************************/
	/*************** 获取自适用的字体 *****************/
	/********************************************************/
	// 字体
	public static Font defaultFont = Font.getDefaultFont();
	// 粗的大字体
	public static Font BigBoldFont = Font.getFont(0, 1, 16);
	// 小的粗字体
	public static Font TinyBoldFont = Font.getFont(0, 1, 8);

	public static Font TinyDefaultFont = Font.getFont(0, 0, 8);
	public static final int CORRECT_FONT_W = 15;
	public static Font[] getFont = { defaultFont, BigBoldFont, TinyBoldFont,
			TinyDefaultFont };
	public static Font currentFont = defaultFont;

	public static void getCorrectFont() {
		int tempCorrectFontW = Math.min(Math.min(Math.abs(getFont[0]
				.charWidth('工')
				- CORRECT_FONT_W), Math.abs(getFont[1].charWidth('工')
				- CORRECT_FONT_W)), Math.min(Math.abs(getFont[2].charWidth('工')
				- CORRECT_FONT_W), Math.abs(getFont[3].charWidth('工')
				- CORRECT_FONT_W)));
		for (int i = 0; i < getFont.length; i++) {
			if (tempCorrectFontW == Math.abs(CORRECT_FONT_W
					- getFont[i].charWidth('工'))) {
				Globe.currentFont = getFont[i];
			}
		}
	}
	/**
	 * 正常状态碰撞检测
	 * @param hero
	 * @param enemy_m
	 */
	public static int getnormalCollision(Hero hero,Enemy enemy_m)
	{
		int hero_w = hero.motion.getCollied(0)[2];
		int hero_h = hero.motion.getCollied(0)[3];
		int hero_x = hero.motion.getCollied(0)[0]+hero.motion.getSpriteDrawX()+(hero_w>>1);
		int hero_y = hero.motion.getCollied(0)[1]+hero.motion.getSpriteDrawY()+hero_h;
		int enemy_w = enemy_m.getWidth();
		int enemy_x = enemy_m.getPosx()+(enemy_w>>1);
		int enemy_y = enemy_m.getPosy();
		
		if ((hero_y - enemy_y) >0)
		{
			if (Math.abs(hero_y - enemy_y) < (hero_h / 3)
					&& (Math.abs(hero_x - enemy_x) < ((hero_w + enemy_w) >> 1) - 5)) {
				return 1;

			} else if (((hero_y - enemy_y) > (hero_h /3) && (Math.abs(hero_x- enemy_x) < ((hero_w + enemy_w) >> 1) - 5))) {
				return 0;
			}
		}
		return -1;
	}
	/**
	 * 冲状态碰撞检测
	 * @param hero
	 * @param enemy_m
	 */
	public static boolean getchongCollision(Motion motion,Enemy enemy_m)
	{
		int hero_w = motion.getCollied(0)[2];
		int hero_h = motion.getCollied(0)[3];
		int hero_x = motion.getCollied(0)[0]+motion.getSpriteDrawX()+(hero_w>>1);
		int hero_y = motion.getCollied(0)[1]+motion.getSpriteDrawY()+(hero_h>>1);
		int enemy_w = enemy_m.getWidth();
		int enemy_h = enemy_m.getHeight();
		int enemy_x = enemy_m.getPosx()+(enemy_w>>1);
		int enemy_y = enemy_m.getPosy()+(enemy_h>>1);
		
			if((Math.abs(hero_y -enemy_y) < (hero_h + enemy_h)>>1) && Math.abs(hero_x-enemy_x)<(hero_w +enemy_w)>>1)
			{
				return true;
			}else
			{
				return false;
			}
	}
	/**
	 * 切割字符串的方法
	 * 
	 * @param strIn
	 *            要切割的字符串，换行要有特殊标记“/n”
	 * @param w
	 *            切割的宽度
	 * @param font
	 *            字体
	 * @return
	 */
	public static String[] splitString(String strIn, int w, Font font) {
		String str[] = null;
		Vector ver = new Vector();
		Vector verS = new Vector();
		for (int star = 0;;) {
			int index = strIn.indexOf("/n", star);
			if (index == -1) {
				ver.addElement(strIn.substring(star, strIn.length()));
				break;
			} else {
				ver.addElement(strIn.substring(star, index));
				star = (index + 2);
			}
		}
		int charW = font.stringWidth("我");
		int lenght = w / charW;
		for (int i = 0; i < ver.size(); i++) {
			String s = (String) ver.elementAt(i);
			for (int star = 0, end = 0;;) {
				end = star + lenght;
				if (end >= s.length()) {
					verS.addElement(s.substring(star, s.length()));
					break;
				} else {
					String s_;
					for (;;) {
						s_ = s.substring(star, end);
						if (font.stringWidth(s_) >= w || end == s.length() - 1) {
							break;
						} else {
							end++;
							if (end == s.length() - 1) {
								break;
							}
						}
					}
					verS.addElement(s_);
					star = end;
				}
			}
		}
		str = new String[verS.size()];
		for (int i = 0; i < verS.size(); i++) {
			str[i] = (String) verS.elementAt(i);
		}
		return str;
	}

	/***
	 * 
	 * @param x1 被追踪
	 * @param y1
	 * @param x2 追踪
	 * @param y2
	 * @return
	 */
	public static int[] getAng(int x1,int y1,int x2,int y2,int speed)
	{
		
		int tempX_FP = x1-x2;
		int tempY_FP = y2-y1;
		int VXY[]=new int[2];
		if (tempX_FP == 0)
		{
			if (tempY_FP > 0) { //被追踪者在正上方
				VXY[0]= 0;
				VXY[1]=-speed;
				return VXY;
			} else if (tempY_FP < 0) { //被追踪者在正下方
				VXY[0]= 0;
				VXY[1]=speed;
				return VXY;
				
			} else {
				VXY[0]= 0;
				VXY[1]=speed;
				return VXY;
			}
		}
		
		int radians_FP=MathFP.div(MathFP.toFP(Math.abs(tempY_FP)),MathFP.toFP(Math.abs(tempX_FP)));
		
		int angle=MathFP.atan(radians_FP);
		
		VXY[0]= MathFP.mul(MathFP.toFP(speed), MathFP.cos(angle));
		VXY[1]=-MathFP.mul(MathFP.toFP(speed), MathFP.sin(angle));
	
		if (tempX_FP < 0 && tempY_FP > 0) {//2
			VXY[0]=-VXY[0];
		} else if (tempX_FP < 0 && tempY_FP <= 0) {//3
			VXY[0]=-VXY[0];
			VXY[1]=-VXY[1];
		} else if (tempX_FP > 0 && tempY_FP < 0) {//4
			VXY[1]=-VXY[1];
		}
		VXY[0]=MathFP.toInt(VXY[0]);
		VXY[1]=MathFP.toInt(VXY[1]);
		return VXY;
	}

	/**
	 * 居中对齐
	 * 
	 * @param g
	 * @param num
	 * @param x
	 * @param y
	 * @param imgNum
	 * @param type
	 *            0,居中对齐 1,左边对齐 2,右边对齐
	 */
	public static void drawNum(Graphics g, int num, int x, int y, Image imgNum,
			int type) {
		if(num<0){
			return;
		}
		int weishu = 1;
		for (int tempN = num;; weishu++) {
			if ((tempN = tempN / 10) <= 0) {
				break;
			}
		}
		int tempX[] = { x + (weishu * imgNum.getWidth() / 10) / 2,
				x + weishu * (imgNum.getWidth() / 10), x };

		int anchor[] = { Graphics.HCENTER | Graphics.TOP,
				Graphics.LEFT | Graphics.TOP, Graphics.RIGHT | Graphics.TOP, };

		for (int i = 0; i < weishu; i++) {
			int tempNum = num % 10;
			g.drawRegion(imgNum, (tempNum) * imgNum.getWidth() / 10, 0, imgNum
					.getWidth() / 10, imgNum.getHeight(), Globe.TRANS_NONE,
					tempX[type] - i * imgNum.getWidth() / 10, y, anchor[type]);
			num /= 10;
		}
	}

    /**
     * 居中对齐
     *
     * @param g
     * @param num
     * @param x
     * @param y
     * @param imgNum
     * @param type
     *            0,居中对齐 1,左边对齐 2,右边对齐
     */
    public static void drawNum(Graphics g, int num, int x, int y, Image[] imgNum,
                               int type) {
        if(num<0){
            return;
        }
        int weishu = 1;
        for (int tempN = num;; weishu++) {
            if ((tempN = tempN / 10) <= 0) {
                break;
            }
        }
        int tempX[] = { x + (weishu * imgNum[2].getWidth()) / 2,
                x + weishu * (imgNum[2].getWidth()), x };

        int anchor[] = { Graphics.HCENTER | Graphics.TOP,
                Graphics.LEFT | Graphics.TOP, Graphics.RIGHT | Graphics.TOP, };

        for (int i = 0; i < weishu; i++) {
            int tempNum = num % 10;
            g.drawRegion(imgNum[tempNum], 0, 0, imgNum[tempNum]
                    .getWidth(), imgNum[tempNum].getHeight(), Globe.TRANS_NONE,
                    tempX[type] - i * imgNum[2].getWidth(), y, anchor[type]);
            num /= 10;
        }
    }

	public static Random random = new Random();

	public static int getRandom(int max) {
		return (random.nextInt() % max + max) % max;
	}

	public static Image getImage(String filename) {
		try {
			return Image.createImage("/" + filename);
		} catch (Exception ex) {
			System.out
					.println("getImage error:" + ex + " filename=" + filename);
			return null;
		}
	}

}
