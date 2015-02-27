package elements;

import javax.microedition.lcdui.Graphics;

//import screens.GamingScreen;

import motion.Motion;

import common.GameVariables;
import common.Globe;

public class Pet {
	public int x;
	public int y;
	public int distance;
	public int speed;
	public int vx;
	public int vy;
	public int type;
	public Motion motion ;
	public Pet(int type, int HeroX,int HeroY){
		this.x = HeroX;
		this.y = HeroY;
		this.type = type;
		switch (type) {
		case 0:
			motion=new Motion("/game/pets/qiu.anu", x, y);
			break;
		case 1:
			motion=new Motion("/game/pets/tuzi.anu", x, y);
			break;
		case 2:
			motion=new Motion("/game/pets/yang.anu", x, y);
			break;
		case 3:
			motion=new Motion("/game/pets/yangtuo.anu", x, y);
			break;
		case 4:
			motion=new Motion("/game/pets/zhu.anu", x, y);
			break;
		case 5:
			motion=new Motion("/game/pets/ciwei.anu", x, y);
			break;
		case 6:
			motion=new Motion("/game/pets/xiaoniao.anu", x, y);
			break;
		case 7:
			motion=new Motion("/game/pets/daniao.anu", x, y);
			break;
		case 8:
			motion=new Motion("/game/pets/feiniao.anu", x, y);
			break;
		case 9:
			motion=new Motion("/game/pets/mianmian.anu", x, y);
			break;
		case 10:
			motion=new Motion("/game/pets/wuzhizhi.anu", x, y);
			break;
		case 11:
			motion=new Motion("/game/pets/xuefeifei.anu", x, y);
			break;
		}
		
	}
	
	public void update(){
		
		motion.keepId(0);
		motion.update(x, y);
		//int hx = GamingScreen.hero.getPosX();
		if (type < 6) {
//			int hy =  GamingScreen.hero.getPosY() + GamingScreen.hero.bodyHeight / 2;
//			distance = (hy - y) * (hy - y);
			if(distance < 1600){
				return;
			}else if(distance < 2500){
//				speed = GamingScreen.hero.moveFrame * 3 / 4;
			}else if(distance > 2500){
//				speed = GamingScreen.hero.moveFrame * 5 / 4;
			}
			
//			int vxy[] = Globe.getAng(0, hy, 0, y, 30);
//			vy = vxy[1];
//			y += vy;
			if (y > Globe.SH * 2 / 3 + 2) {
				y = Globe.SH * 2 / 3 + 2;
			}
		}
	}
	
	public void draw(Graphics g){

		motion.draw(g);
	}
}
