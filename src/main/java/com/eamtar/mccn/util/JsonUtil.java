package com.eamtar.mccn.util;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

public class JsonUtil {

	JSONObject obj;

	public JsonUtil(String videoId) throws JSONException {
		obj = new JSONObject(videoId);
	}

	public String getValue(String key) throws JSONException {
		String value = obj.getString(key);
		if (value != null)
			value = value.replace("\\", "");
		return obj.getString(key);
	}

}
