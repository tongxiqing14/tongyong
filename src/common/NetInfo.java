package common;

import Entry.LWGameCanvas;
import iptvNet.IptvNetException;
import iptvNet.NetHander;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

//import screens.ConfirmFailureScreen;

import elements.Hero;
import screens.*;

public class NetInfo {
	public static NetHander netHander;
    public static Screen returnScreen;

	/**
	 * 道具; 0~5 购买人物; 6~9 训练; 10~12 购买道具; 13: 复活; 14: 特殊技能;
	 * */

	/**
	 *Type说明:
	 * 
	 * 0~5:英雄角色信息; 10~14道具信息; 20:获取最大关卡 21:获取成就;
	 */
	public static void getGameScore(){
		try {
			GameVariables.point=netHander.getMyScore(0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IptvNetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateAchieve() {
		JSONObject jo = new JSONObject();
		try {
			JSONArray ja0 = new JSONArray();
			JSONArray ja1 = new JSONArray();
			jo.put("achieve", ja0);
			jo.put("bonus", ja1);
			netHander.saveGameData(jo.toString(), 21);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IptvNetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void saveStageInfo(boolean[] o)
	{
        try {

            JSONObject jsonStage = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < o.length; i++) {
                jsonArray.put(o[i]);
            }

            jsonStage.put("stageLockOrUnlock", jsonArray);
			NetInfo.netHander.saveGameData(jsonStage.toString(), 33);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IptvNetException e) {
			e.printStackTrace();
		}
		
	}
	public static void getStageInfo()
	{
		try {
			String petInfo1 = netHander.getGameData(33);
			JSONObject json1 = new JSONObject(petInfo1);
			JSONArray arr1 = json1.getJSONArray("stageLockOrUnlock");
//			for (int i = 0; i < arr1.length(); i++) {
//                SelectStageScreen.stageLockOrUnlocks[i] = arr1.getBoolean(i);
//			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IptvNetException e) {
			e.printStackTrace();
		}
		
	}
	public static void saveScore(int score,int type){
		try {
			NetInfo.netHander.saveScore(score, type);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IptvNetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void getAchieve() {
		try {
			String achieve = netHander.getGameData(21);
			JSONObject jo0 = new JSONObject(achieve);
			JSONArray achieveArray = jo0.getJSONArray("achieve");
			for (int i = 0; i < achieveArray.length(); i++) {
//				AchieveScreen.achievement[i] = achieveArray.getBoolean(i);
			}

			String bouns = netHander.getGameData(21);
			JSONObject jo1 = new JSONObject(bouns);
			JSONArray bounsArray = jo1.getJSONArray("bonus");
			for (int i = 0; i < bounsArray.length(); i++) {
//				AchieveScreen.bonus[i] = bounsArray.getBoolean(i);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IptvNetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateMapStage() {
		JSONObject jo = new JSONObject();
		try {
			jo.put("maxStage", GameVariables.maxMapStage);
			netHander.saveGameData(jo.toString(), 10);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IptvNetException e) {
			e.printStackTrace();
		}
	}

	public static void getMapStage() {
		try {
			String mapStage = netHander.getGameData(10);
			JSONObject jo = new JSONObject(mapStage);
			GameVariables.maxMapStage = jo.getInt("maxStage");
		} catch (JSONException e) {

			e.printStackTrace();
		} catch (IptvNetException e) {

			e.printStackTrace();
		}
	}

	public static void initData() {

//		for (int i = 0; i < SelectFishScreen.KrillNum; i++) {
//			JSONObject jo = new JSONObject();
//			try {
//				jo.put("isGot", SelectFishScreen.isGot[i]);
//                jo.put("krillNum", SelectFishScreen.initKrillNums[i]);
//                jo.put("fishSizeLevel", FishroomScreen.fishSizeLevel[i]);
//                jo.put("fishHpLevel", FishroomScreen.fishHpLevel[i]);
//
//                SelectFishScreen.krillNums[i]=SelectFishScreen.initKrillNums[i];
//				netHander.saveGameData(jo.toString(), i);
//			} catch (JSONException e1) {
//				e1.printStackTrace();
//			} catch (IptvNetException e) {
//				e.printStackTrace();
//			}
//		}

//        for (int i = 0; i < 8; i++) {
//            JSONObject jo = new JSONObject();
//            try {
//                jo.put("stageLockOrUnlock", SelectStageScreen.stageLockOrUnlocks[i]);
//                netHander.saveGameData(jo.toString(), i);
//            } catch (JSONException e1) {
//                e1.printStackTrace();
//            } catch (IptvNetException e) {
//                e.printStackTrace();
//            }
//        }
		

	}
	
	public static void getHeroData(int heroType) {
//		try {
//			String heroData = netHander.getGameData(heroType);
//			JSONObject jo = new JSONObject(heroData);
//			SelectFishScreen.isGot[heroType] = jo.getBoolean("isGot");
//            SelectFishScreen.krillNums[heroType] = jo.getInt("krillNum");
//            FishroomScreen.fishSizeLevel[heroType] = jo.getInt("fishSizeLevel");
//            FishroomScreen.fishHpLevel[heroType] = jo.getInt("fishHpLevel");
//
//		}catch (JSONException e) {
//			e.printStackTrace();
//		} catch (IptvNetException e) {
//			e.printStackTrace();
//		}

//        try {
//            for (int i = 0; i < 8; i++) {
//                String stageLockData = netHander.getGameData(i);
//                JSONObject jo = new JSONObject(stageLockData);
//                SelectStageScreen.stageLockOrUnlocks[i] = jo.getBoolean("stageLockOrUnlock");
//            }
//        }catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IptvNetException e) {
//            e.printStackTrace();
//        }
	}

	public static void getItemInfo() {
		for (int i = 0; i < 5; i++) {
			try {
				String itemInfo = netHander.getGameData(10 + i);
				JSONObject jo = new JSONObject(itemInfo);
//				Hero.itemNum[10 + i] = jo.getInt("itemNum");
				
			} catch (JSONException e) {
	
				e.printStackTrace();
			} catch (IptvNetException e) {

				e.printStackTrace();
			}
		}
//		Hero.itemNum[14]=10;

	}

	public static void updateItemInfo(int itemType) {
		JSONObject jo = new JSONObject();
		try {
//			jo.put("itemNum", Hero.itemNum[itemType]);
			netHander.saveGameData(jo.toString(), itemType);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IptvNetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateHeroData(int heroType) {
//		JSONObject jo = new JSONObject();
//		try {
//			jo.put("isGot", SelectFishScreen.isGot[heroType]);
//            jo.put("krillNum", SelectFishScreen.krillNums[heroType]);
//
//            jo.put("fishSizeLevel", FishroomScreen.fishSizeLevel[heroType]);
//            jo.put("fishHpLevel", FishroomScreen.fishHpLevel[heroType]);
//
//			netHander.saveGameData(jo.toString(), heroType);
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		} catch (IptvNetException e) {
//			e.printStackTrace();
//		}
	}
	public static void buyItemNotEnoughToken(){
			int tempNum = 0;

			Hero.gotHeroID = new int[tempNum];

	}
	public static void buyItem(int token, String wareName, int screenID) {
		boolean consume = false;
		try {
			consume = netHander.topupAndConsumeMoney(token, wareName);



            Globe.token = NetInfo.netHander.getBalance();


            if (consume) {
                if(Globe.confirmScreenId == 1){         //speed up
//                    if(SelectStageScreen.stageSelectIndex==0){
//                        GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                        GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                        GamingScreen.dStepX = 60;
//                        GamingScreen.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==1){
//                        GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                        GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                        GamingScreen2.dStepX = 60;
//                        GamingScreen2.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==2){
//                        GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                        GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                        GamingScreen3.dStepX = 60;
//                        GamingScreen3.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==3){
//                        GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                        GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                        GamingScreen4.dStepX = 60;
//                        GamingScreen4.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==4){
//                        GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                        GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                        GamingScreen5.dStepX = 60;
//                        GamingScreen5.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==5){
//                        GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                        GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                        GamingScreen6.dStepX = 60;
//                        GamingScreen6.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==6){
//                        GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                        GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                        GamingScreen7.dStepX = 60;
//                        GamingScreen7.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==7){
//                        GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                        GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                        GamingScreen8.dStepX = 60;
//                        GamingScreen8.hudunTime = 100;
//                    }
                }else if(Globe.confirmScreenId == 2){
//                    if(SelectStageScreen.stageSelectIndex==0){
//                        GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                        GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                        GamingScreen.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==1){
//                        GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                        GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                        GamingScreen2.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==2){
//                        GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                        GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                        GamingScreen3.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==3){
//                        GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                        GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                        GamingScreen4.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==4){
//                        GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                        GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
////                    GamingScreen5.dStepX = 60;
//                        GamingScreen5.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==5){
//                        GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                        GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                        GamingScreen6.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==6){
//                        GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                        GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                        GamingScreen7.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==7){
//                        GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                        GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                        GamingScreen8.hudunTime = 100;
//                    }
                } else if(Globe.confirmScreenId == 3){        //jump
//                    if(SelectStageScreen.stageSelectIndex==0){
//                        GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                        GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                        GamingScreen.dStepX = 15;
//                        GamingScreen.hudunTime = 100;
//                        GamingScreen.direct = "up";
//                        GamingScreen.dStepY = 20;
//                        GamingScreen.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==1){
//                        GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                        GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                        GamingScreen2.dStepX = 15;
//                        GamingScreen2.hudunTime = 100;
//                        GamingScreen2.direct = "up";
//                        GamingScreen2.dStepY = 20;
//                        GamingScreen2.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==2){
//                        GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                        GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                        GamingScreen3.dStepX = 15;
//                        GamingScreen3.hudunTime = 100;
//                        GamingScreen3.direct = "up";
//                        GamingScreen3.dStepY = 20;
//                        GamingScreen3.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==3){
//                        GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                        GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                        GamingScreen4.dStepX = 15;
//                        GamingScreen4.hudunTime = 100;
//                        GamingScreen4.direct = "up";
//                        GamingScreen4.dStepY = 20;
//                        GamingScreen4.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==4){
//                        GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                        GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                        GamingScreen5.dStepX = 15;
//                        GamingScreen5.hudunTime = 100;
//                        GamingScreen5.direct = "up";
//                        GamingScreen5.dStepY = 20;
//                        GamingScreen5.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==5){
//                        GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                        GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                        GamingScreen6.dStepX = 15;
//                        GamingScreen6.hudunTime = 100;
//                        GamingScreen6.direct = "up";
//                        GamingScreen6.dStepY = 20;
//                        GamingScreen6.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==6){
//                        GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                        GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                        GamingScreen7.dStepX = 15;
//                        GamingScreen7.hudunTime = 100;
//                        GamingScreen7.direct = "up";
//                        GamingScreen7.dStepY = 20;
//                        GamingScreen7.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==7){
//                        GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                        GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                        GamingScreen8.dStepX = 15;
//                        GamingScreen8.hudunTime = 100;
//                        GamingScreen8.direct = "up";
//                        GamingScreen8.dStepY = 20;
//                        GamingScreen8.djumpStepX = 0;
//                    }
                } else if(Globe.confirmScreenId == 4){
//                    FishroomScreen.yuerDx = 10;
                } else if(Globe.confirmScreenId == 5){
//                    if(!(SelectFishScreen.krillNums[SelectFishScreen.fishSelectIndex]>0)){
//                        KrillElement ke = new KrillElement();
//                        ke.setFetchIndex(0);
//                        ke.setSum(3);
//                        ke.setType(1);
//                        SelectFishScreen.krillElement.addElement(ke);
//                        SelectFishScreen.krillNums[SelectFishScreen.fishSelectIndex]=3;
//                        SelectFishScreen.isGot[SelectFishScreen.fishSelectIndex]=true;
//                    }
//                    NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
                } else if(Globe.confirmScreenId == 6){
//                    if(SelectStageScreen.stageSelectIndex==0){
//                        ((GamingScreen) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==1){
//                        ((GamingScreen2) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==2){
//                        ((GamingScreen3) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==3){
//                        ((GamingScreen4) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==4){
//                        ((GamingScreen5) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==5){
//                        ((GamingScreen6) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==6){
//                        ((GamingScreen7) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==7){
//                        ((GamingScreen8) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }
//
//                    //Jump
//                    if(SelectStageScreen.stageSelectIndex==0){
//                        GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                        GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                        GamingScreen.dStepX = 15;
//                        GamingScreen.hudunTime = 100;
//                        GamingScreen.direct = "up";
//                        GamingScreen.dStepY = 20;
//                        GamingScreen.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==1){
//                        GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                        GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                        GamingScreen2.dStepX = 15;
//                        GamingScreen2.hudunTime = 100;
//                        GamingScreen2.direct = "up";
//                        GamingScreen2.dStepY = 20;
//                        GamingScreen2.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==2){
//                        GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                        GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                        GamingScreen3.dStepX = 15;
//                        GamingScreen3.hudunTime = 100;
//                        GamingScreen3.direct = "up";
//                        GamingScreen3.dStepY = 20;
//                        GamingScreen3.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==3){
//                        GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                        GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                        GamingScreen4.dStepX = 15;
//                        GamingScreen4.hudunTime = 100;
//                        GamingScreen4.direct = "up";
//                        GamingScreen4.dStepY = 20;
//                        GamingScreen4.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==4){
//                        GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                        GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                        GamingScreen5.dStepX = 15;
//                        GamingScreen5.hudunTime = 100;
//                        GamingScreen5.direct = "up";
//                        GamingScreen5.dStepY = 20;
//                        GamingScreen5.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==5){
//                        GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                        GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                        GamingScreen6.dStepX = 15;
//                        GamingScreen6.hudunTime = 100;
//                        GamingScreen6.direct = "up";
//                        GamingScreen6.dStepY = 20;
//                        GamingScreen6.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==6){
//                        GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                        GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                        GamingScreen7.dStepX = 15;
//                        GamingScreen7.hudunTime = 100;
//                        GamingScreen7.direct = "up";
//                        GamingScreen7.dStepY = 20;
//                        GamingScreen7.djumpStepX = 0;
//                    }else if(SelectStageScreen.stageSelectIndex==7){
//                        GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                        GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                        GamingScreen8.dStepX = 15;
//                        GamingScreen8.hudunTime = 100;
//                        GamingScreen8.direct = "up";
//                        GamingScreen8.dStepY = 20;
//                        GamingScreen8.djumpStepX = 0;
//                    }
                } else if(Globe.confirmScreenId == 7){
//                    if(SelectStageScreen.stageSelectIndex==0){
//                        ((GamingScreen) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==1){
//                        ((GamingScreen2) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==2){
//                        ((GamingScreen3) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==3){
//                        ((GamingScreen4) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==4){
//                        ((GamingScreen5) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==5){
//                        ((GamingScreen6) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==6){
//                        ((GamingScreen7) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }else if(SelectStageScreen.stageSelectIndex==7){
//                        ((GamingScreen8) returnScreen).setHpNum(316);
//                        NetInfo.updateHeroData(SelectFishScreen.fishSelectIndex);
//                    }
//
//                    //speed up
//                    if(SelectStageScreen.stageSelectIndex==0){
//                        GamingScreen.hudunNum =  GamingScreen.hudun_jinduImg.getWidth();
//                        GamingScreen.dHudunNum = GamingScreen.hudunNum/80;
//                        GamingScreen.dStepX = 60;
//                        GamingScreen.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==1){
//                        GamingScreen2.hudunNum =  GamingScreen2.hudun_jinduImg.getWidth();
//                        GamingScreen2.dHudunNum = GamingScreen2.hudunNum/80;
//                        GamingScreen2.dStepX = 60;
//                        GamingScreen2.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==2){
//                        GamingScreen3.hudunNum =  GamingScreen3.hudun_jinduImg.getWidth();
//                        GamingScreen3.dHudunNum = GamingScreen3.hudunNum/80;
//                        GamingScreen3.dStepX = 60;
//                        GamingScreen3.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==3){
//                        GamingScreen4.hudunNum =  GamingScreen4.hudun_jinduImg.getWidth();
//                        GamingScreen4.dHudunNum = GamingScreen4.hudunNum/80;
//                        GamingScreen4.dStepX = 60;
//                        GamingScreen4.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==4){
//                        GamingScreen5.hudunNum =  GamingScreen5.hudun_jinduImg.getWidth();
//                        GamingScreen5.dHudunNum = GamingScreen5.hudunNum/80;
//                        GamingScreen5.dStepX = 60;
//                        GamingScreen5.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==5){
//                        GamingScreen6.hudunNum =  GamingScreen6.hudun_jinduImg.getWidth();
//                        GamingScreen6.dHudunNum = GamingScreen6.hudunNum/80;
//                        GamingScreen6.dStepX = 60;
//                        GamingScreen6.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==6){
//                        GamingScreen7.hudunNum =  GamingScreen7.hudun_jinduImg.getWidth();
//                        GamingScreen7.dHudunNum = GamingScreen7.hudunNum/80;
//                        GamingScreen7.dStepX = 60;
//                        GamingScreen7.hudunTime = 100;
//                    }else if(SelectStageScreen.stageSelectIndex==7){
//                        GamingScreen8.hudunNum =  GamingScreen8.hudun_jinduImg.getWidth();
//                        GamingScreen8.dHudunNum = GamingScreen8.hudunNum/80;
//                        GamingScreen8.dStepX = 60;
//                        GamingScreen8.hudunTime = 100;
//                    }
                } else if(Globe.confirmScreenId == 8){
//                    if(SelectStageScreen.stageSelectIndex==0){
//                        SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                        NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//                    }else if(SelectStageScreen.stageSelectIndex==1){
//                        SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                        NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//                    }else if(SelectStageScreen.stageSelectIndex==2){
//                        SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                        NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//                    }else if(SelectStageScreen.stageSelectIndex==3){
//                        SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                        NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//                    }else if(SelectStageScreen.stageSelectIndex==4){
//                        SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                        NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//                    }else if(SelectStageScreen.stageSelectIndex==5){
//                        SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                        NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//                    }else if(SelectStageScreen.stageSelectIndex==6){
//                        SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                        NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//                    }else if(SelectStageScreen.stageSelectIndex==7){
//                        SelectStageScreen.stageLockOrUnlocks[SelectStageScreen.stageSelectIndex] = true;
//                        NetInfo.saveStageInfo(SelectStageScreen.stageLockOrUnlocks);
//                    }
                }

            } else {
                //System.out.println("netHander.getMessage:"+netHander.getReceiveString());
//			ConfirmFailureScreen.tipInfo = "消费失败,请去大厅充值!";
//			ConfirmFailureScreen.confirmStage = 2;
                switch (screenID){
                    case 1:
//                        ConfirmBuyFishScreen.confirmStatus = 2;
                        break;
                }
            }
			
		} catch (JSONException e) {
//			ConfirmFailureScreen.confirmStage = 2;
			e.printStackTrace();
		} catch (IptvNetException e) {
//			ConfirmFailureScreen.confirmStage = 2;
			//System.out.println("nethandle:" + netHander.getReceiveString());
			e.printStackTrace();
		}

	}

}
