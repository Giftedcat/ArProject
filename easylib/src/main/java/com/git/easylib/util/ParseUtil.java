package com.git.easylib.util;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json串解析工具类
 * 
 * @author zengliang
 */
public class ParseUtil {

	/**
	 * 直接解析json数据为实体,jsonObject没有再套一层jsonObject
	 * 
	 * @param object
	 *            json实体
	 * @param clazz
	 *            对应实体类字节码
	 * @return 返回对应实体
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T parseDataToEntity(JSONObject object, Class<T> clazz) {

		// T t = clazz.newInstance();
		Gson gson = new Gson();
		T entity = gson.fromJson(object.toString(), clazz);
		return entity;
	}

	/**
	 * 解析json数据为实体,jsonObject再套一层jsonObject
	 * 
	 * @param object
	 *            json实体
	 * @param key
	 *            包含json数据的key值
	 * @param clazz
	 *            对应实体类字节码
	 * @return 返回对应实体
	 * @throws Exception
	 */
	public static <T> T parseDataToEntity(JSONObject object, String key,
			Class<T> clazz) throws Exception {
		Gson gson = new Gson();
		T entity = null;
		if (key != null && object.has(key)) {
			entity = gson.fromJson(object.getJSONObject(key).toString(), clazz);
		} else {
			entity = gson.fromJson(object.toString(), clazz);
		}
		return entity;
	}

	/**
	 * 解析json数据为实体集合
	 * 
	 * @param object
	 *            json实体
	 * @param key
	 *            包含json数组的key值
	 * @param clazz
	 *            对应实体类字节码
	 * @return 返回对应实体集合
	 * @throws Exception
	 */
	public static <T> List<T> parseDataToCollection(JSONObject object,
			String key, Class<T> clazz) throws Exception {

		Gson gson = new Gson();
		JSONArray jsonArray = null;
		if (object.has(key)) {
			jsonArray = object.getJSONArray(key);
		} else {
			return null;
		}
		if (jsonArray == null || jsonArray.length() == 0) {
			return null;
		}
		List<T> data = new ArrayList<T>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject entityObj = jsonArray.getJSONObject(i);
			T entity = gson.fromJson(entityObj.toString(), clazz);
			data.add(entity);
		}
		return data;
	}

	/**
	 * 解析json实体为Map集合,Map集合中的value值为 实体类型
	 * 
	 * @param object
	 * @param key
	 * @param mapKey
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> Map<Object, T> parseDataToMap(JSONObject object,
			String key, String mapKey, Class<T> clazz) throws Exception {

		Gson gson = new Gson();
		JSONArray jsonArray = null;
		if (object.has(key)) {
			jsonArray = object.getJSONArray(key);
		} else {
			return null;
		}
		if (jsonArray == null || jsonArray.length() == 0) {
			return null;
		}
		Map<Object, T> data = new HashMap<Object, T>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject entityObj = jsonArray.getJSONObject(i);
			T entity = gson.fromJson(entityObj.toString(), clazz);
			data.put(entityObj.opt(mapKey), entity);
		}
		return data;
	}

	/**
	 * 解析json集合为Map集合 Map集合中的value值为 实体类型
	 * 
	 * @param jsonArray
	 * @param mapKey
	 * @param clazz
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> Map<Object, T> parseDataToMap(JSONArray jsonArray,
			String mapKey, Class<T> clazz) throws Exception {

		Gson gson = new Gson();
		if (jsonArray == null || jsonArray.length() == 0) {
			return null;
		}
		Map<Object, T> data = new HashMap<Object, T>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject entityObj = jsonArray.getJSONObject(i);
			T entity = gson.fromJson(entityObj.toString(), clazz);
			data.put(entityObj.opt(mapKey), entity);
		}
		return data;
	}

	/**
	 * 解析json数据为Map集合
	 * 
	 * @param object
	 *            json串
	 * @param key
	 *            json串的key值
	 * @param mapKey
	 *            String 类型key
	 * @param mapValue
	 *            String 类型value
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseDataToMap(JSONObject object,
			String key, String mapKey, String mapValue) throws Exception {
		JSONArray jsonArray = null;
		if (object.has(key)) {
			jsonArray = object.getJSONArray(key);
		} else {
			return null;
		}
		if (jsonArray == null || jsonArray.length() == 0) {
			return null;
		}
		Map<String, String> data = new HashMap<String, String>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject entityObj = jsonArray.getJSONObject(i);
			data.put(entityObj.optString(mapKey), entityObj.optString(mapValue));
		}
		return data;
	}

	/**
	 * 解析json数据为实体集合
	 * 
	 * @param jsonArray
	 *            json数组
	 * @param clazz
	 *            对应实体类字节码
	 * @return 返回对应实体集合
	 * @throws Exception
	 */
	public static <T> List<T> parseDataToCollection(JSONArray jsonArray,
			Class<T> clazz) throws Exception {

		Gson gson = new Gson();
		if (jsonArray == null || jsonArray.length() == 0) {
			return null;
		}
		List<T> data = new ArrayList<T>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject entityObj = jsonArray.getJSONObject(i);
			T entity = gson.fromJson(entityObj.toString(), clazz);
			data.add(entity);
		}
		return data;
	}

}
