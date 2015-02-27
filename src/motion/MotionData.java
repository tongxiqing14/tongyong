package motion;


import motion.studio.MSpriteData;

public class MotionData {

	public MSpriteData mspriteData;
	public String key = "";
	
	public MotionData(String key,MSpriteData mspriteData){
		this.key = key;
		this.mspriteData = mspriteData;
	}
	
}
