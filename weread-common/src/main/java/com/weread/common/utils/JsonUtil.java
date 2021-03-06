package com.weread.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	public static String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	public static String toJson(Object obj,String... ignores){
		return JSON.toJSONString(obj, (PropertyFilter) (object, name, value) -> !ArrayUtils.contains(ignores,name));
	}
	
	public static <T>T parseObject(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}
	
	public static <T>T parseObject(String json,Type type){
		return JSON.parseObject(json, type);
	}
	
	public static <T>List<T> parseArray(String json,Class<T> clazz){
		return JSON.parseArray(json, clazz);
	}
	
	public static JSONObject parseObject(String json) {
		return JSON.parseObject(json);
	}
	
	public static String map2Json(Map<String,String> map) {
		if (null == map || map.isEmpty()) {
			return "";
		}
		String jsonObject = JSONObject.toJSONString(map);
		return jsonObject;
	}
	
	public static Map<String, Object> jsonStringToMap(String jsonStr){
		@SuppressWarnings("unchecked")
		Map<String, Object> mapType = JSON.parseObject(jsonStr,Map.class);  
        return mapType;
	}
}
