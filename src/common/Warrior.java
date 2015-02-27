package common;


public class Warrior {

	public Warrior(int x,int y ,int type)
	{
		setPosition(x, y);
		
		this.type=type;
		this.isDead=false;
	}

	//人物动作持续定义
	public static final int STATE_IDLE=0;
	public static final int STATE_MOVE=STATE_IDLE+1;

	public int state=STATE_MOVE;

	public int lifeMax=100;//生命上限
	public boolean isDead;//是否死亡

	public int posX,posY;
	public int type;//类型
	private  int lan = 100; //英雄的蓝值

	public int getLan() {
		return lan;
	}

	/**
	 * 改变主角的状态 
	 */
	public void setState(int stage){
		this.state = stage;
	}
	public int getStage(){
		return this.state;
	}
	//设置坐标
	public void setPosition(int x,int y)
	{
		
		this.posX=x;
		this.posY=y;
	}
	public int getPosX()
	{
		return posX;
	}
	public int getPosY()
	{
		return posY;
	}

	public void setDead(boolean isDead) {
		//System.out.println("setDead " + isDead);
		this.isDead = isDead;
		
	}
	public void setLan(int lan) {
		if(lan >= 100)
		{
			this.lan =100;
		}else
		{
		this.lan = lan;
		}
	}
}
