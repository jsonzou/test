package com.test.core.commons.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 王非
 * @date   2012-6-26-上午11:31:25
 */
@Deprecated
public class DateUtil {

	public static final String TIME_KEY = "format";
	public static final String TIME_DAY_EX = "StrToDay";
	public static final String TIME_SECOND_EX = "StrToSs";
	public static final String DAY_FORMAT = "yyyy-MM-dd";
	public static final String MIN_FORMAT = "yyyy-MM-dd hh:mm:ss";

	public static List<JSONObject> toTimeList(List<JSONObject> vestGroupList) {
		List<JSONObject> resList = new ArrayList<JSONObject>();
		for (Object obj : vestGroupList) {
			JSONObject json = (JSONObject) JSONObject.toJSON(obj);
			resList.add(getTimeJsonObject(json));
		}
		return resList;
	}

	public static List<JSONObject> CoverToTimeList(List<?> coverList) {
		List<JSONObject> resList = new ArrayList<JSONObject>();
		for (Object obj : coverList) {
			JSONObject json = (JSONObject) JSONObject.toJSON(obj);
			resList.add(CoverToTimeJsonObject(json));
		}
		return resList;
	}

	private static String formatDate(Date date, String formatStr) {
		SimpleDateFormat sf = new SimpleDateFormat(formatStr);
		String text = sf.format(date);
		return text;

	}

	public static JSONObject toTimeJsonObject(Object obj) {

		return getTimeJsonObject((JSONObject) JSONObject.toJSON(obj));

	}

	private static JSONObject CoverToTimeJsonObject(JSONObject json) {

		for (Entry<String, Object> entry : json.entrySet()) {
			if (entry.getValue() instanceof java.util.Date) {
				if (entry.getValue() != null) {
					Date date = (Date) entry.getValue();
					String text = formatDate(date, DAY_FORMAT);
					json.put(entry.getKey(), text);
				}

			}
		}
		return json;
	}

	private static JSONObject getTimeJsonObject(JSONObject json) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.putAll(json);
		for (Entry<String, Object> entry : json.entrySet()) {
			if (entry.getValue() instanceof java.util.Date) {
				if (entry.getValue() != null) {
					Date date = (Date) entry.getValue();
					String text = formatDate(date, DAY_FORMAT);
					jsonRes.put(entry.getKey() + TIME_DAY_EX, text);
					jsonRes.put(entry.getKey() + TIME_SECOND_EX, text);
				}
			}

		}
		return jsonRes;

	}

}
