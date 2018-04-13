package tools;

import org.bson.json.JsonMode;

import com.alibaba.fastjson.*;

public class DealJSON {

	private static JSONObject APInfo;
	private static JSONArray APInfoArray = new JSONArray();
	private static JSONObject signal = new JSONObject();
	private static JSONArray signalArray = new JSONArray();
	private static JSONObject APUser;
	private static JSONArray APUserArray = new JSONArray();
	private static JSONObject anaResult = new JSONObject();
	private static JSONArray anaResultArray = new JSONArray();

	public static JSONArray getAnaResultArray() {
		return anaResultArray;
	}

	public static JSONArray getAPUserArray() {
		return APUserArray;
	}

	public static JSONObject getAPInfo() {
		return APInfo;
	}

	public static JSONObject getSignal() {
		return signal;
	}

	public static JSONArray getSignalArray() {
		return signalArray;
	}

	public static void addAPInfo(JSONObject apInfo) {
		
		APInfoArray.add(apInfo);


		
	}

	public static void addAPuser(String[] str) {
		APUser= new JSONObject();
		APUser.put("APMAC", str[5].trim());
		//APUser.put("Channel", str[4].trim());
		APUser.put("Power", str[3].trim());
		APUser.put("uMAC", str[0].trim());
		APUser.put("Packets", str[4].trim());
		APUser.put("Time", str[1].trim());
		APUserArray.add(APUser);
		
	}

	public static void addAnaResult(String[] str) {
		anaResult=new JSONObject();
		anaResult.put("srcMAC", str[0]);
		anaResult.put("dstMAC", str[1]);
		anaResult.put("time", str[2]);
		anaResult.put("srcIP", str[3]);
		anaResult.put("dstIP", str[4]);
		anaResult.put("keyWord", str[5]);
		anaResultArray.add(anaResult);
		
		
	}

	public static JSONArray getAPInfoArray() {
		return APInfoArray;
	}

	public static void clear() {
		APInfoArray.clear();
		signalArray.clear();
	}

	public static void addSignal(String jsonString, String freq, String time) {
		signal = JSON.parseObject(jsonString);
		Boolean hasExist = false;
		for (int i = 0; i < signalArray.size(); i++) {
			JSONObject temp = signalArray.getJSONObject(i);
			if (temp.containsValue(freq)) {
				temp.replace("time", time);
				hasExist = true;
			}
		}
		if (!hasExist) {
			signalArray.add(signal);
			hasExist = false;
		}
		//System.out.println(signalArray.toString());
	}

}
