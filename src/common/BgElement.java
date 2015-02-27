package common;

public class BgElement {
	public int width = 0;
	public int height = 0;
	public int type = 0;
	public int x = 0;
	public int y = 0;
	
//	public BgElement(int x,int y,int width,int height){
//		this.x = x;
//		this.y = y;
//		this.type = 101;
//		this.width = width;
//		this.height = height;
//	}

    public BgElement(){

    }
	
	public BgElement(int x, int y, int type, int width, int height){
		this.x = x;
		this.y = y;
		this.type = type;
		this.width = width;
		this.height = height;
	}
}
