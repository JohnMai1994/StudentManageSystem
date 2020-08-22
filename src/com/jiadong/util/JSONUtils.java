package com.jiadong.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public class JSONUtils {
    public static JSONArray fromMapList(List<Map<String , Object>> mapList) throws  Exception {
        JSONArray jsonArray = new JSONArray();
        for (Map<String, Object> map : mapList) {
            JSONObject jsonObject = new JSONObject();
            for (String key: map.keySet()){
                jsonObject.put(key, map.get(key).toString());
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

}
