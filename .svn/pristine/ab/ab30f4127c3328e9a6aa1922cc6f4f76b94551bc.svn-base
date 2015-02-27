package motion;

import java.util.Hashtable;
import common.Vector;

import javax.microedition.lcdui.Graphics;

import motion.studio.MPlayer;
import motion.studio.MSprite;
import motion.studio.MSpriteAnimationPlayer;
import motion.studio.MSpriteData;
import motion.studio.MSpriteLoader;


public class Motion implements MSprite{

	public String name;
	private int x = 0;
	private int y = 0;
	private int buffX = 0;
	private int buffY = 0;
	public static final byte LEFT = 1;
	public static final byte RIGHT = 0;
	private byte direction = RIGHT;
	private int loop = 0;
	private int currLoop = 0;
	
	public MPlayer player;
	
	private int vx;
	private int vy;
	
	private int collied[];
	
	public static Vector vecMotionData = new Vector();
	
	public MSpriteData motionData;
	private boolean isEnd = false;


	
	public Motion(String str,int x,int y){
		name = str;
//		MSpriteData motionData = null;
		motionData = getMotionData(str);
		if(motionData==null){
			try {
				motionData = MSpriteLoader.loadMSprite(str,true,ResourceLoader.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vecMotionData.addElement(new MotionData(str,motionData));
		}
		this.x = x;
		this.y = y;
		this.buffX = x;
		this.buffY = y;
		direction = RIGHT;
		player = new MSpriteAnimationPlayer(motionData,this);
		currLoop = 0;
		isEnd = false;
	}
	
	public void setWay(byte way){
		direction = way;
	}
	
	public int getCurrentId(){
		return player.getAnimation();
	}
	
	public int getCurrentFrame(){
		return player.getCurrentFrame();
	}
	
	public int getCountFrame(){
		return player.getFrameCount();
	}
	
	public void setId(int id,int num){
		player.setAnimation(id);
		//player.setFrame(0);
		player.setLoopOffset(-1);
		currLoop = 0;
		this.loop = num;
		isEnd = false;
		setControlXY(true);
	}
	public void setFrame(int frame){
		player.setFrame(frame);
	}
	public void keepId(int id){
		player.setAnimation(id);
		player.setFrameForKeep(player.getCurrentFrame()%player.getFrameCount());
		currLoop = 0;
		this.loop = 1;
//		isEnd = false;
	}
	
	public int getVx(){
		return vx;
	}
	
	public int getVy(){
		return vy;
	}
	
	/**
	 * true为自己控制坐标，false为编辑器控制坐标
	 */
	private boolean isControl = true;
	/**
	 * true为自己控制坐标，false为编辑器控制坐标
	 */
	public void setControlXY(boolean isControl){
		this.isControl = isControl;
	}
	
	public MSpriteData getMotionData(String str){
		MSpriteData mspriteData = null;
		for(int i = 0;i<vecMotionData.size();i++){
			MotionData md = (MotionData)vecMotionData.elementAt(i);
			if(md.key.equals(str)){
				mspriteData = md.mspriteData;
				break;
			}
		}
		return mspriteData;
	}
	
	private int offsetVx = 0;
	private int offsetVy = 0;
	/**
	 * 如果坐标由编辑器控制，同时，程序要控制整个动画的偏移，则调用此方法
	 */
	public void setOffsetVxVy(int offsetVx,int offsetVy){
		this.offsetVx = offsetVx;
		this.offsetVy = offsetVy;
	}
	
	public void update(int x,int y){
		if(loop<=0){
			player.update();
		}else{
			if(currLoop<loop){
				player.update();
			}else{
				isEnd = true;
			}
		}
		if(isControl){
			this.offsetVx = 0;
			this.offsetVy = 0;
			this.x = x;
			this.y = y;
		}else{
			this.x+=offsetVx;
			this.y+=offsetVy;
		}
		if(player.getCurrentFrame()==player.getFrameCount()-1){
			currLoop++;
		}
		this.vx = x-buffX;
		this.vy = y-buffY;
		this.offsetVx = 0;
		this.offsetVy = 0;
		this.buffX = x;
		this.buffY = y;
	}
	
	public boolean isEnd(){
		return isEnd;
	}
	
	public void draw(Graphics g){
		player.drawFrame(g);
	}

	public void endOfAnimation() {
		// TODO Auto-generated method stub
//		player.setFrame(0);
	}

	public int getSpriteDrawX() {
		// TODO Auto-generated method stub
		return x;
	}

	public int getSpriteDrawY() {
		// TODO Auto-generated method stub
		return y;
	}

	public byte getSpriteOrientation() {
		// TODO Auto-generated method stub
		return (byte)direction;
	}

	public void updateSpritePosition(int offsetX, int offsetY) {
		// TODO Auto-generated method stub
		x+=offsetX; 
		y+=offsetY;
		collied = getCollied(0);
	}
	
	public int[] getCollied(int index){
		int temp[] = player.getCollisionRect(index);
		return temp;
	}
	
	public static void clear(){
		for(int i = 0;i<vecMotionData.size();i++){
			((MotionData)vecMotionData.elementAt(i)).mspriteData.imageVector.removeAllElements();
			vecMotionData.removeElementAt(i);
		}
	}
	
	public static void clear(String str){
		for(int i = 0;i<vecMotionData.size();i++){
			if(((MotionData)vecMotionData.elementAt(i)).key.equals(str)){
				((MotionData)vecMotionData.elementAt(i)).mspriteData.imageVector.removeAllElements();
				vecMotionData.removeElementAt(i);
			}
		}
	}
}
