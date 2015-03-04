package common;

import motion.Motion;

/**
 * Created by tongxiqing on 2015/3/2.
 */
public class Utility {

    public static Motion motionImpl(int type, int fighter_id){
        //hero animation
        if(type == 0){
            if(fighter_id == 1){
                return new Motion("/hero/blue/blue.anu",250,250);
            }else if(fighter_id == 2){
                return new Motion("/hero/red/red.anu",250,250);
            }else if(fighter_id == 3){
                return new Motion("/hero/yellow/yellow.anu",250,250);
            }else if(fighter_id == 4){
                return new Motion("/hero/blue1/blue1.anu",250,250);
            }else if(fighter_id == 5){
                return new Motion("/hero/red1/red1.anu",250,250);
            }else if(fighter_id == 6){
                return new Motion("/hero/yellow1/yellow1.anu",250,250);
            }else if(fighter_id == 7){
                return new Motion("/hero/purple/purple.anu",250,250);
            }
        }

        //follow animation
        else if(type == 1){
            if(fighter_id == 8){
                return new Motion("/follow/left/4/follow_left_4.anu",250,250);
            }else if(fighter_id == 9){
                return new Motion("/follow/left/3/follow_left_3.anu",250,250);
            }else if(fighter_id == 10){
                return new Motion("/follow/left/2/left_atack_2.anu",250,250);
            }else if(fighter_id == 11){
                return new Motion("/follow/left/12/follow_left_12.anu",250,250);
            }else if(fighter_id == 12){
                return new Motion("/follow/left/11/follow_left_11.anu",250,250);
            }else if(fighter_id == 13){
                return new Motion("/follow/left/10/follow_left_10.anu",250,250);
            }else if(fighter_id == 14){
                return new Motion("/follow/left/9/follow_left_9.anu",250,250);
            }else if(fighter_id == 15){
                return new Motion("/follow/left/8/follow_left_8.anu",250,250);
            }else if(fighter_id == 16){
                return new Motion("/follow/left/7/follow_left_7.anu",250,250);
            }else if(fighter_id == 17){
                return new Motion("/follow/left/6/follow_left_6.anu",250,250);
            }else if(fighter_id == 18){
                return new Motion("/follow/left/5/follow_left_5.anu",250,250);
            }else if(fighter_id == 19){
                return new Motion("/follow/left/1/renwu_huonv.anu",250,250);
            }
        }

        //pets animation
        else if(type == 2){
            if(fighter_id == 20){
                return new Motion("/pets1/left/bat-5.anu",250,250);
            }else if(fighter_id == 21){
                return new Motion("/pets1/left/bird-7.anu",250,250);
            }else if(fighter_id == 22){
                return new Motion("/pets1/left/clown-3.anu",250,250);
            }else if(fighter_id == 23){
                return new Motion("/pets1/left/frogfish-2.anu",250,250);
            }else if(fighter_id == 24){
                return new Motion("/pets1/left/horse-1.anu",250,250);
            }else if(fighter_id == 25){
                return new Motion("/pets1/left/mouse-8.anu",250,250);
            }else if(fighter_id == 26){
                return new Motion("/pets1/left/rabbit-4.anu",250,250);
            }else if(fighter_id == 27){
                return new Motion("/pets1/left/tiger-6.anu",250,250);
            }else if(fighter_id == 28){
                return new Motion("/pets1/left/tortoise-9.anu",250,250);
            }
        }

        return null;
    }

}
