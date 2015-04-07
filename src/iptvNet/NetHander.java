package iptvNet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.midlet.MIDlet;

import Entry.LWGameCanvas;
import common.NetInfo;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

public class NetHander {

	public static String serverURL = "";
	public static String account = "";
	public static String adAccount = "";
	public static String gameId = "";
	public static String userToken = "";
    public static String selected_stage = "";
	public static boolean isAutoTopup = false;// 自动充值

	public NetHander(MIDlet midlet) {
		System.out.println("GET");
		try {
			serverURL = midlet.getAppProperty("ServerURL");
			isAutoTopup = midlet.getAppProperty("IsAutoTopUp").equals("true");
			account = midlet.getAppProperty("Account");
			adAccount = midlet.getAppProperty("ADAccount");
			userToken = midlet.getAppProperty("UserToken");
			gameId = midlet.getAppProperty("GameID");
            selected_stage = midlet.getAppProperty("selected_stage");
		} catch (Exception e) {
			System.out
					.println("NetHander's structure Exception,please check the JAD");
		}
	}

	/**
	 * 获取代币余额
	 * 
//	 * @param adAccount
//	 * @param account
	 * @return
	 * @throws iptvNet.IptvNetException
	 * @throws org.json.me.JSONException
	 * @throws
	 * @throws Exception
	 */
	public int getBalance() throws JSONException, IptvNetException {
		int ret = 0;
		String str = "";
		String url = serverURL + "GetBalance.ashx";
		url = put(url, "ADAccount", adAccount, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			ret = Integer.parseInt(jo.getString("Description"));
		} else {
			ret = 0;
			throw new IptvNetException("the 9 http Exception");
		}
		return ret;
	}

	/**
	 * 获取昵称
	 * 
	 * @return
	 * @throws iptvNet.IptvNetException
	 * @throws org.json.me.JSONException
	 */
	public String getNickName() throws IptvNetException, JSONException {
		String nickName = "";
		String str = "";
		String url = serverURL + "GetNickName.ashx";
		url = put(url, "Account", account, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			nickName = jo.getString("Description");
		} else {
			nickName = "";
			throw new IptvNetException("the 9 http Exception");
		}
		return nickName;
	}

	/**
	 * 保存分数
	 * 
	 * @return
	 * @throws org.json.me.JSONException
	 * @throws iptvNet.IptvNetException
	 */
	public boolean saveScore(int score, int type) throws JSONException,
			IptvNetException {
		boolean isSucess = false;
		String str = "";
		String url = serverURL + "SaveScore.ashx";
		url = put(url, "Account", account, false);
		url = put(url, "Score", "" + score, false);
		url = put(url, "Type", "" + type, false);
		url = put(url, "GameID", "" + gameId, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			isSucess = jo.getString("Description").endsWith("sucess");
		} else {
			isSucess = false;
			throw new IptvNetException("the 9 http Exception");
		}
		return isSucess;
	}

	/**
	 * 获取分数
	 * 
	 * @return
	 * @throws org.json.me.JSONException
	 * @throws iptvNet.IptvNetException
	 */
	public int getMyScore(int type) throws JSONException, IptvNetException {
		int score = 0;
		String str = "";
		String url = serverURL + "GetAccountMyScore.ashx";
		url = put(url, "Account", account, false);
		url = put(url, "Type", "" + type, false);
		url = put(url, "GameID", "" + gameId, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		// try {
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			score = Integer.parseInt(jo.getString("Description"));
		} else {
			score = 0;
			throw new IptvNetException("the 9 http Exception");
		}
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return score;
	}

	/**
	 * 获取名次
	 * 
	 * @return
	 * @throws org.json.me.JSONException
	 * @throws iptvNet.IptvNetException
	 */
	public int getMyRank(int type) throws JSONException, IptvNetException {
		int rank = 0;
		String str = "";
		String url = serverURL + "GetMyRank.ashx";
		url = put(url, "Account", account, false);
		url = put(url, "Type", "" + type, false);
		url = put(url, "GameID", "" + gameId, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		// try {
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			rank = Integer.parseInt(jo.getString("Description"));
		} else {
			rank = 0;
			throw new IptvNetException("the 9 http Exception");
		}
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return rank;
	}

	/**
	 * 获取排行榜
	 * 
	 * @param type
	 * @param getNum
	 * @return
	 * @throws org.json.me.JSONException
	 * @throws iptvNet.IptvNetException
	 */
	public String[][] getScoreList(int type, int getNum) throws JSONException,
			IptvNetException {
		String ret[][] = null;
		String url = serverURL + "GetScoreList.ashx";//
		url = put(url, "Type", "" + type, false);
		url = put(url, "GameID", "" + gameId, false);
		url = put(url, "Number", "" + getNum, false);
		String str = doSend(url);
		receiveString = str;
		JSONObject jo;
		// try {
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			String jo1 = jo.getString("Description");
			JSONArray ja = new JSONArray(jo1);
			if (ja.length() <= 0) {
				return ret;
			}
			ret = new String[ja.length()][2];
			for (int i = 0; i < ja.length(); i++) {
				String temp = ja.getString(i);
				JSONObject jo2 = new JSONObject(temp);
				ret[i][1] = jo2.getString("Socre");
				ret[i][0] = jo2.getString("NickName");
			}
		} else {
			throw new IptvNetException("the 9 http Exception");
		}
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return ret;
	}

	/**
	 * 保存游戏数据
	 * 
	 * @return
	 * @throws org.json.me.JSONException
	 * @throws iptvNet.IptvNetException
	 */
	public boolean saveGameData(String data, int type) throws JSONException,
			IptvNetException {
		boolean isSucess = false;
		String str = "";
		String url = serverURL + "SaveGameData.ashx";
		url = put(url, "Account", account, false);
		url = put(url, "GameData", data, false);
		url = put(url, "GameID", "" + gameId, false);
		url = put(url, "Type", "" + type, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		// try {
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			isSucess = jo.getString("Description").equals("sucess");
		} else {
			isSucess = false;
			throw new IptvNetException("the 9 http Exception");
		}
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return isSucess;
	}

	/**
	 * 获取游戏数据
	 * 
	 * @return
	 * @throws org.json.me.JSONException
	 * @throws iptvNet.IptvNetException
	 */
	public String getGameData(int type) throws JSONException, IptvNetException {
		String ret = "";
		String str = "";
		String url = serverURL + "LoadGameData.ashx";
		url = put(url, "Account", account, false);
		url = put(url, "GameID", "" + gameId, false);
		url = put(url, "type", "" + type, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		// try {
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			ret = jo.getString("Description");
		} else {
			ret = "";
			throw new IptvNetException("the 9 http Exception");
		}
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return ret;
	}

	public boolean hasVerificationCode() throws JSONException, IptvNetException {
		boolean has = false;
		answer = "get the answer is null";
		question = "get the question is null";
		String ret = "";
		String str = "";
		String url = serverURL + "JudgeIsYanZhengMa.ashx";
		url = put(url, "GameID", "" + gameId, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		jo = new JSONObject(str);
		System.out.println("result = " + jo.getInt("Result"));
		if (jo.getInt("Result") == 0) {
		} else if (jo.getInt("Result") == 1028) {
			has = true;
			ret = jo.getString("Description");
			JSONObject jo1 = new JSONObject(ret);
			answer = jo1.getString("Answer");
			question = jo1.getString("Question");
		} else {
			throw new IptvNetException("the 9 http Exception");
		}
		return has;
	}

	/**
	 * 
	 * @param money
	 *            消费代币数目
	 * @param wareName
	 *            消费说明
	 * @return
	 * @throws org.json.me.JSONException
	 * @throws iptvNet.IptvNetException
	 */
	public boolean topupAndConsumeMoney(int money, String wareName)
			throws JSONException, IptvNetException {
		boolean ret = false;
		String str = "";
		String url = serverURL + "TopupAndConsumeMoney.ashx";
		url = put(url, "ADAccount", adAccount, false);
		url = put(url, "UserToken", userToken, false);
		url = put(url, "Money", "" + money, false);
		url = put(url, "Account", account, false);
		url = put(url, "GameID", "" + gameId, false);
		url = put(url, "WareName", wareName, true);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			ret = jo.getString("Description").equals("sucess");
		} else {
			ret = false;
//			throw new IptvNetException("the 9 http Exception");
		}
		return ret;
	}

	/**
	 * 
	 * @param money
	 * @param wareName
	 * @param password
	 * @return 返回0、1、2分别表示 成功、童锁密码不正确、失败
	 * @throws org.json.me.JSONException
	 * @throws iptvNet.IptvNetException
	 */
	public int topupAndConsumeMoney(int money, String wareName, String password)
			throws JSONException, IptvNetException {
		int ret = 2;
		String str = "";
		String url = serverURL + "JudgeIsTopupPassword.ashx";
		url = put(url, "ADAccount", adAccount, false);
		url = put(url, "UserToken", userToken, false);
		url = put(url, "Money", "" + money, false);
		url = put(url, "Account", account, false);
		url = put(url, "GameID", "" + gameId, false);
		url = put(url, "WareName", wareName, true);
		url = put(url, "Password", password, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			ret = 0;
		} else if (jo.getInt("Result") == 1023) {
			ret = 1;
		} else {
			ret = 2;
//			throw new IptvNetException("the 9 http Exception");
		}
		return ret;
	}

	public String getCurrTime() throws JSONException, IptvNetException {
		String ret = "";
		String str = "";
		String url = serverURL + "GetServerTime.ashx";
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			ret = jo.getString("Description");
		} else {
			ret = "";
			throw new IptvNetException("the 9 http Exception");
		}
		return ret;
	}

	public String getConfig(int type) throws JSONException, IptvNetException {
		String ret = "";
		String str = "";
		String url = serverURL + "GetConfig.ashx";
		url = put(url, "type", "" + type, false);
		url = put(url, "GameID", "" + gameId, false);
		str = doSend(url);
		receiveString = str;
		JSONObject jo;
		jo = new JSONObject(str);
		if (jo.getInt("Result") == 0) {
			ret = jo.getString("Description");
		} else {
			ret = "";
			throw new IptvNetException("the 9 http Exception");
		}
		return ret;
	}

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public int getGameInfo()
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("game_info_url");
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        str = doSend(url);
        receiveString = str;
        JSONObject jo;
        jo = new JSONObject(str);
        if (jo.getInt("Result") == 0) {
            ret = jo.getInt("id");
            LWGameCanvas.chest_num = jo.getInt("chestnum");
            LWGameCanvas.sum_enemy_hp_num = jo.getDouble("enemyhp");
            LWGameCanvas.sum_hero_hp_num = jo.getDouble("herohp");
            LWGameCanvas.sum_fight_num = jo.getDouble("fightnum");
            LWGameCanvas.sum_enemy_fight_num = jo.getDouble("enemyfightnum");
        } else if (jo.getInt("Result") == 1) {
            ret = 1;
        } else {
            ret = 2;
            throw new IptvNetException("the 9 http Exception");
        }
        return ret;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public JSONArray getGameInfoII()
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/GameInfoIIServlet.ashx";
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        str = doSend(url);
        JSONArray jsonArray = new JSONArray(str);

        return jsonArray;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public JSONArray getGameInfoIII()
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/GameInfoIIIServlet.ashx";
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        str = doSend(url);
        JSONArray jsonArray = new JSONArray(str);

        return jsonArray;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public JSONArray getFighterHpDown()
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/FighterHpDownServlet.ashx";
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        str = doSend(url);
        JSONArray jsonArray = new JSONArray(str);

        return jsonArray;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public JSONArray getFighterHpDownII()
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/FighterHpDownIIServlet.ashx";
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        str = doSend(url);
        JSONArray jsonArray = new JSONArray(str);

        return jsonArray;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public JSONArray getEnemyInfo()
            throws JSONException, IptvNetException {
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/StageEnemyServlet.ashx";
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        str = doSend(url);
        JSONArray jsonArray = new JSONArray(str);
        return jsonArray;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public JSONArray getFighterInfo()
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/FighterInfoServlet.ashx";
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        str = doSend(url);
        JSONArray jsonArray = new JSONArray(str);

        return jsonArray;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public int screenToPageAction(String followingPage)
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/ScreenToPageAction.ashx";
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        url = put(url, "followingPage", followingPage, false);
        str = doSend(url);
        receiveString = str;
        JSONObject jo;
        jo = new JSONObject(str);
        if (jo.getInt("Result") == 0) {
            ret = jo.getInt("id");
        } else if (jo.getInt("Result") == 1) {
            ret = 1;
        } else {
            ret = 2;
            throw new IptvNetException("the 9 http Exception");
        }
        return ret;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public int stageInfoAction()
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/StageInfoServlet.ashx";
        url = put(url, "sessionId", LWGameCanvas.rmidlet.getAppProperty("sessionId"), false);
        str = doSend(url);
        receiveString = str;
        JSONObject jo;
        jo = new JSONObject(str);
        if (jo.getInt("Result") == 0) {
            ret = jo.getInt("id");
        } else if (jo.getInt("Result") == 1) {
            ret = 1;
        } else {
            ret = 2;
            throw new IptvNetException("the 9 http Exception");
        }
        return ret;
    }

    /**
     *
     * @throws JSONException
     * @throws IptvNetException
     */
    public  JSONArray testStr(String testStr)
            throws JSONException, IptvNetException {
        int ret = 2;
        String str = "";
        String url = LWGameCanvas.rmidlet.getAppProperty("return_url")+"/HttpService/TestServlet.ashx";
        url = put(url, "testStr", testStr, false);
        str = doSend(url);
        JSONArray jsonArray = new JSONArray(str);

        return jsonArray;
    }

    public static void main(String[] args){
        try {
            NetInfo.netHander.testStr("");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IptvNetException e) {
            e.printStackTrace();
        }
    }

	private String answer = "";
	private String question = "";

	public String getAnswer() {
		return answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getReceiveString() {
		return receiveString;
	}
	
	String putBuff = "";

	private String put(String url, String key, String v, boolean isEncode) {
		if(putBuff!=null){
			if(!putBuff.equals("")){
				putBuff+=("&" + key + "=" + (isEncode ? encode(v) : v));
			}else{
				putBuff+=("" + key + "=" + (isEncode ? encode(v) : v));
			}
		}
		return url;
	}

	private int sendNum = 0;

	private String doSend(String url) {
		this.receiveString = "";
		sendNum = 0;
		return doSend_(url);
	}

	private String receiveString = "";

	private String doSend_(String url) {
		sendNum++;
		String str = "";
		HttpConnection httpConn = null;
		DataOutputStream data_Out = null;
		DataInputStream data_In = null;
		ByteArrayOutputStream bytearrayoutputstream = null;
		
		try {
			httpConn = (HttpConnection) Connector.open(url);
			httpConn.setRequestMethod(HttpConnection.POST);
			httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			data_Out = httpConn.openDataOutputStream();
			data_Out.write(putBuff.getBytes());
			
			if (HttpConnection.HTTP_OK == httpConn.getResponseCode()) {
				str = "no null";
				data_In = httpConn.openDataInputStream();
				bytearrayoutputstream = new ByteArrayOutputStream();
				int c = 0;
//                byte[] l = new byte[1];
//                data_In.read(l, 0, 1);
//				int length = getInt(l);
//				for (int i = 0; i < length; i++) {
//					c = data_In.read();
//					bytearrayoutputstream.write(c);
//				}
                while ((c = data_In.read()) != -1) {
                    bytearrayoutputstream.write(c);
                }
				try {
					byte temp[] = bytearrayoutputstream.toByteArray();
					str = new String(temp);
				} catch (Exception e) {
					str = "no null";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			putBuff = "";
			if(data_Out != null){
				try {
					data_Out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				data_Out = null;
			}
			if (bytearrayoutputstream != null) {
				try {
					bytearrayoutputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bytearrayoutputstream = null;
			}
			if (data_In != null) {
				try {
					data_In.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				data_In = null;
			}
			
			if (httpConn != null) {
				try {
					httpConn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				httpConn = null;
			}
		}
		if ((str == null || str.equals(""))) {
			str = doSend_(url);
		}
		return str;
	}

	private int getInt(byte[] data) {
		if (data == null)
			return -1;

		int num = 0;

		// for (int i = 0; i < start + 4 * n; i += 4) {
		// num = ((data[3] & 0xff) << 24) | ((data[2] & 0xff) << 16)
		// | ((data[1] & 0xff) << 8) | ((data[0] & 0xff));
		// }
		for (int i = 0; i < data.length; i++) {
			num += ((data[i] & 0xff) << (8 * i));
		}
		return num;
	}

	private String deCode(String str) {
		String rstr = "";
		String temp = "";
		boolean isStar = false;
		char[] ch = str.toCharArray();
		try {
			for (int i = 0; i < ch.length; i++) {
				if (isStar) {
					if (ch[i] == '|') {
						isStar = false;
						int tempInt = Integer.parseInt(temp);
						char chTemp = (char) tempInt;
						rstr += chTemp;
						temp = "";
					} else {
						temp += ch[i];
					}
				} else {
					if (ch[i] == '|') {
						isStar = true;
						temp = "";
					} else {
						rstr += ch[i];
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("decoding excption");
		}
		return rstr;
	}

	private String encode(String s) {
		String ret = "";
		char[] ch = s.toCharArray();
		int[] changeInt = new int[ch.length];
		for (int i = 0; i < ch.length; i++) {
			changeInt[i] = (int) ch[i];
			ret = ret + "|" + changeInt[i] + "|";
		}
		return ret;
	}
}