package com.git.easylib.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 项目名称：sdk
 * 类描述：
 * 创建人：alibus
 * 创建时间：16-3-30 下午5:20
 * 修改人：alibus
 * 修改时间：16-3-30 下午5:20
 * 修改备注：
 */
public class CommonUtil {

    /**
     * map 转换成json字符类型
     * @param map
     * @return string
     */
    public static String hashMapToJson(HashMap map) {
        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
            Entry e = (Entry) it.next();
            string += "'" + e.getKey() + "':";
            string += "'" + e.getValue() + "',";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;
    }


    /**
     * 获取JsonObject
     * @param json
     * @return
     */
    public static JsonObject parseJson(String json){
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
        return jsonObj;
    }

    /**
     * 根据json字符串返回Map对象
     * @param json
     * @return
     */
    public static Map<String,Object> toMap(String json){
        return toMap(parseJson(json));
    }

    /**
     * 将JSONObjec对象转换成Map-List集合
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(JsonObject json){
        Map<String, Object> map = new HashMap<String, Object>();
        Set<Entry<String, JsonElement>> entrySet = json.entrySet();
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
            Entry<String, JsonElement> entry = iter.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof JsonArray)
                map.put((String) key, toList((JsonArray) value));
            else if(value instanceof JsonObject)
                map.put((String) key, toMap((JsonObject) value));
            else
                map.put((String) key, value);
        }
        return map;
    }

    /**
     * 将JSONArray对象转换成List集合
     * @param json
     * @return
     */
    public static List<Object> toList(JsonArray json){
        List<Object> list = new ArrayList<Object>();
        for (int i=0; i<json.size(); i++){
            Object value = json.get(i);
            if(value instanceof JsonArray){
                list.add(toList((JsonArray) value));
            }
            else if(value instanceof JsonObject){
                list.add(toMap((JsonObject) value));
            }
            else{
                list.add(value);
            }
        }
        return list;
    }







}
