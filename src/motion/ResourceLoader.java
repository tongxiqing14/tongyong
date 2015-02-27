/*
 * COPYRIGHT - MOTIONWELDER
 */
package motion;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import motion.studio.MSpriteImageLoader;

import common.Globe;



/**
 * Resource Loader: Class to load Images
 * @author Nitin Pokar (pokar.nitin@gmail.com)
 *
 */
public class ResourceLoader implements MSpriteImageLoader{

    /** Making Class Singleton */
    static private ResourceLoader resourceLoader;
    private ResourceLoader(){}

    static public ResourceLoader getInstance(){
        if(resourceLoader==null){
            resourceLoader = new ResourceLoader();
        }
        return resourceLoader;
    }

    /**
     *  Function : LoadImage will be called while loading .anu.
     *  This version of Load Image will be called when .anu is loaded without chopping images
     *  In this example we have not loaded any .anu where we have passed false to MSpriteLoader, hence this function will never be called
     */
    public Image loadImage(String spriteName,int imageId){
        // determine whether i need flipped version in my game
        Image baseImage=null;

        if (spriteName.equals("/hero/red/red_h.anu")) {
            baseImage = Globe.getImage("hero/red/action"+(imageId+1)+"_06.png");
        }else if (spriteName.equals("/follow/left/2/left_atack_2.anu")) {
            baseImage = Globe.getImage("follow/left/2/2atack"+imageId+".png");
        }else if (spriteName.equals("/follow/left/2/left_atack_2_h.anu")) {
            baseImage = Globe.getImage("follow/left/2/2atack"+imageId+".png");
        }else if (spriteName.equals("/follow/left/1/renwu_huonv.anu")) {
            baseImage = Globe.getImage("follow/left/1/renwu"+(imageId+1)+"_03.png");
        }else if (spriteName.equals("/follow/left/1/renwu_huonv_h.anu")) {
            baseImage = Globe.getImage("follow/left/1/renwu"+(imageId+1)+"_03.png");
        }else if (spriteName.equals("/dead/dead.anu")) {
            baseImage = Globe.getImage("dead/siwangyan"+(imageId+1)+".png");
        }else if (spriteName.equals("/effect2/atomic.elect/atomic.elec.anu")) {
            baseImage = Globe.getImage("effect2/atomic.elect/atomic_elect"+imageId+".png");
        }else if (spriteName.equals("/effect2/lava.serie/falling.fire/falling_fire.anu")) {
            baseImage = Globe.getImage("effect2/lava.serie/falling.fire/falling.fire"+imageId+".png");
        }else if (spriteName.equals("/effect2/point_Coco/eff_point_Coco_atk.anu")) {
            baseImage = Globe.getImage("effect2/point_Coco/eff_point_Coco_atk"+(imageId+1)+".png");
        }else if (spriteName.equals("/effect2/gripper/gripper.anu")) {
            baseImage = Globe.getImage("effect2/gripper/gripper"+(imageId+1)+".png");
        }else if (spriteName.equals("/menu/mouse.anu")) {
            baseImage = Globe.getImage("menu/mouse.png");
        }else

         //hero animation
        if (spriteName.equals("/hero/blue/blue.anu")) {
            baseImage = Globe.getImage("hero/blue/action"+(imageId+1)+"_03.png");
        }else if (spriteName.equals("/hero/blue1/blue1.anu")) {
            baseImage = Globe.getImage("hero/blue1/"+(imageId+1)+".png");
        }else if (spriteName.equals("/hero/purple/purple.anu")) {
            baseImage = Globe.getImage("hero/purple/hero"+(imageId+1)+"_03.png");
        }else if (spriteName.equals("/hero/red/red.anu")) {
            baseImage = Globe.getImage("hero/red/action"+(imageId+1)+"_06.png");
        }else if (spriteName.equals("/hero/red1/red1.anu")) {
            baseImage = Globe.getImage("hero/red1/"+(imageId+1)+".png");
        }else if (spriteName.equals("/hero/yellow/yellow.anu")) {
            baseImage = Globe.getImage("hero/yellow/action"+(imageId+1)+"_03.png");
        }else if (spriteName.equals("/hero/yellow1/yellow1.anu")) {
            baseImage = Globe.getImage("hero/yellow1/"+(imageId+1)+".png");
        }else

        //follow animation
        if (spriteName.equals("/follow/left/3/follow_left_3.anu")) {
            baseImage = Globe.getImage("follow/left/3/3"+(imageId+1)+".png");
        }else if (spriteName.equals("/follow/left/4/follow_left_4.anu")) {
            baseImage = Globe.getImage("follow/left/4/4"+(imageId+1)+".png");
        }else if (spriteName.equals("/follow/left/5/follow_left_5.anu")) {
            baseImage = Globe.getImage("follow/left/5/5"+(imageId+1)+".png");
        }else if (spriteName.equals("/follow/left/6/follow_left_6.anu")) {
            baseImage = Globe.getImage("follow/left/6/6"+(imageId+1)+".png");
        }else if (spriteName.equals("/follow/left/7/follow_left_7.anu")) {
            baseImage = Globe.getImage("follow/left/7/7"+(imageId+1)+".png");
        }else if (spriteName.equals("/follow/left/8/follow_left_8.anu")) {
            baseImage = Globe.getImage("follow/left/8/8"+(imageId+1)+".png");
        }else if (spriteName.equals("/follow/left/9/follow_left_9.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("follow/left/9/3.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("follow/left/9/11.png");
            }else if(imageId == 2){
                baseImage = Globe.getImage("follow/left/9/111.png");
            }
        }else if (spriteName.equals("/follow/left/10/follow_left_10.anu")) {
            baseImage = Globe.getImage("follow/left/10/"+(imageId+1)+".png");
        }else if (spriteName.equals("/follow/left/11/follow_left_11.anu")) {
            baseImage = Globe.getImage("follow/left/11/11"+(imageId+1)+".png");
        }else if (spriteName.equals("/follow/left/12/follow_left_12.anu")) {
            baseImage = Globe.getImage("follow/left/12/bluemonster"+(imageId+1)+".png");
        }

        //pets animation
        if (spriteName.equals("/pets1/left/bat-5.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/bat-5.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/bat-5a.png");
            }
        }else if (spriteName.equals("/pets1/left/bird-7.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/bird-7.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/bird-7a.png");
            }
        }else if (spriteName.equals("/pets1/left/clown-3.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/clown-3.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/clown-3a.png");
            }
        }else if (spriteName.equals("/pets1/left/frogfish-2.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/frogfish-2.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/frogfish-2a.png");
            }
        }else if (spriteName.equals("/pets1/left/horse-1.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/horse-1.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/horse-1a.png");
            }
        }else if (spriteName.equals("/pets1/left/mouse-8.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/mouse-8.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/mouse-8a.png");
            }
        }else if (spriteName.equals("/pets1/left/rabbit-4.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/rabbit-4.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/rabbit-4a.png");
            }
        }else if (spriteName.equals("/pets1/left/tiger-6.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/tiger-6.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/tiger-6a.png");
            }
        }else if (spriteName.equals("/pets1/left/tortoise-9.anu")) {
            if(imageId == 0){
                baseImage = Globe.getImage("pets1/left/tortoise-9.png");
            }else if(imageId == 1){
                baseImage = Globe.getImage("pets1/left/tortoise-9a.png");
            }
        }

        //effects animation
        else if (spriteName.equals("/effect2/evocation.big/lightning/lightning.anu")) {
            baseImage = Globe.getImage("effect2/evocation.big/lightning/lightning"+(imageId+1)+".png");
        }

        return baseImage;
    }

    /**
     *  If you are using Nokia DirectGraphics, please don't load flipped image, Instead modify MPlayer to flip it at runtime
     *
     *  Function : LoadImageClip will be called while loading .anu.
     *  This version of Load Image will be called when .anu is loaded with chopped images
     *  In this example we have loaded .anu with passing true in MSpriteLoader, hence this function will be called
     */
//	public Vector tempVector=new Vector();
    public Image loadImageClip(String spriteName,Image img,int x,int y,int w,int h,int orientationUsedInStudio){
        Image image = Image.createImage(img,x,y,w,h,Sprite.TRANS_NONE);
        return image;
    }

    public static Image loadImage(String str){
        try{
            return Image.createImage(str);
        }catch (Exception e) {
            //System.out.println("Error loading Image " + str);
        }
        return null;
    }

    public static Image downloadImage(String str){
        Image img = null;
        img = Globe.download.creatImage(str);
        return img;
    }
}
