package org.apache.cordova.Idbox_Plugin.util;

import android.content.Context;

//import com.facebook.react.bridge.Arguments;
//import com.facebook.react.bridge.ReactContext;
//import com.facebook.react.bridge.ReadableMap;
//import com.facebook.react.bridge.ReadableMapKeySetIterator;
//import com.facebook.react.bridge.ReadableType;
//import com.facebook.react.bridge.WritableArray;
//import com.facebook.react.bridge.WritableMap;
//import com.facebook.react.bridge.WritableNativeArray;
//import com.facebook.react.bridge.WritableNativeMap;
import com.intellisolutions.capacitor.plugin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapUtil {

  /*
    Map each api response into Readable Map objects and pass it back to RN applications
   */
//  public static ReadableMap getMapApiResponse(ReactContext context, String taskId, boolean success,
//                                              int resultCode, String response) {
//    WritableMap map = Arguments.createMap();
//    map.putString(context.getString(R.string.MAP_API_RESPONSE_TASK), taskId);
//    map.putBoolean(context.getString(R.string.MAP_API_RESPONSE_SUCCESS), success);
//    map.putInt(context.getString(R.string.MAP_API_RESPONSE_RESULT_CODE), resultCode);
//    map.putString(context.getString(R.string.MAP_API_RESPONSE_RESULT), response);
//    return map;
//  }
//
//  /*
//    Map generic response/message into Readable Map object and pass it back to RN application
//   */
//  public static ReadableMap getMapResolverStrResponse(ReactContext context, String response){
//    WritableMap map = Arguments.createMap();
//    if(response != null){
//      map.putString( context.getString(R.string.RESOLVER_MAP_MSG_KEY) , response);
//    }
//    return map;
//  }
//
//  /*
//    Map generic response/message into Readable Map object and pass it back to RN application
//   */
//  public static ReadableMap getMapResolverLongResponse(ReactContext context,long response){
//    WritableMap map = Arguments.createMap();
//    map.putDouble(context.getString(R.string.RESOLVER_MAP_MSG_KEY) , response);
//    return map;
//  }
//
//  public static JSONObject readableMapToJSONObject(ReadableMap readableMap) throws JSONException {
//    JSONObject jsonObject = new JSONObject();
//
//    ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
//
//    while (iterator.hasNextKey()) {
//      String key = iterator.nextKey();
//      ReadableType type = readableMap.getType(key);
//
//      switch (type) {
//        case Null:
//          jsonObject.put(key, null);
//          break;
//        case Boolean:
//          jsonObject.put(key, readableMap.getBoolean(key));
//          break;
//        case Number:
//          jsonObject.put(key, readableMap.getDouble(key));
//          break;
//        case String:
//          jsonObject.put(key, readableMap.getString(key));
//          break;
//        case Map:
//          jsonObject.put(key, MapUtil.readableMapToJSONObject(readableMap.getMap(key)));
//          break;
//        case Array:
//          jsonObject.put(key, ArrayUtil.toJSONArray(readableMap.getArray(key)));
//          break;
//      }
//    }
//
//    return jsonObject;
//  }
//
//  public static JSONObject WritableMapToJSONObjects(WritableMap readableMap) throws JSONException {
//    JSONObject jsonObject = new JSONObject();
//
//    ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
//
//    while (iterator.hasNextKey()) {
//      String key = iterator.nextKey();
//      ReadableType type = readableMap.getType(key);
//
//      switch (type) {
//        case Null:
//          jsonObject.put(key, null);
//          break;
//        case Boolean:
//          jsonObject.put(key, readableMap.getBoolean(key));
//          break;
//        case Number:
//          jsonObject.put(key, readableMap.getDouble(key));
//          break;
//        case String:
//          jsonObject.put(key, readableMap.getString(key));
//          break;
//        case Map:
//          jsonObject.put(key, MapUtil.readableMapToJSONObject(readableMap.getMap(key)));
//          break;
//        case Array:
//          jsonObject.put(key, ArrayUtil.toJSONArray(readableMap.getArray(key)));
//          break;
//      }
//    }
//
//    return jsonObject;
//  }
//  public static Map<String, Object> toMap(JSONObject jsonObject) throws JSONException {
//    Map<String, Object> map = new HashMap<>();
//    Iterator<String> iterator = jsonObject.keys();
//
//    while (iterator.hasNext()) {
//      String key = iterator.next();
//      Object value = jsonObject.get(key);
//
//      if (value instanceof JSONObject) {
//        value = MapUtil.toMap((JSONObject) value);
//      }
//      if (value instanceof JSONArray) {
//        value = ArrayUtil.toArray((JSONArray) value);
//      }
//
//      map.put(key, value);
//    }
//
//    return map;
//  }
//  public static WritableMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
//    WritableMap map = new WritableNativeMap();
//
//    Iterator<String> iterator = jsonObject.keys();
//    while (iterator.hasNext()) {
//      String key = iterator.next();
//      Object value = jsonObject.get(key);
//      if (value instanceof JSONObject) {
//        map.putMap(key, convertJsonToMap((JSONObject) value));
//      } else if (value instanceof  JSONArray) {
//        map.putArray(key, convertJsonToArray((JSONArray) value));
//      } else if (value instanceof  Boolean) {
//        map.putBoolean(key, (Boolean) value);
//      } else if (value instanceof  Integer) {
//        map.putInt(key, (Integer) value);
//      } else if (value instanceof  Double) {
//        map.putDouble(key, (Double) value);
//      } else if (value instanceof String)  {
//        map.putString(key, (String) value);
//      } else {
//        map.putString(key, value.toString());
//      }
//    }
//    return map;
//  }
//
//  private static WritableArray convertJsonToArray(JSONArray jsonArray) throws JSONException {
//    WritableArray array = new WritableNativeArray();
//
//    for (int i = 0; i < jsonArray.length(); i++) {
//      Object value = jsonArray.get(i);
//      if (value instanceof JSONObject) {
//        array.pushMap(convertJsonToMap((JSONObject) value));
//      } else if (value instanceof  JSONArray) {
//        array.pushArray(convertJsonToArray((JSONArray) value));
//      } else if (value instanceof  Boolean) {
//        array.pushBoolean((Boolean) value);
//      } else if (value instanceof  Integer) {
//        array.pushInt((Integer) value);
//      } else if (value instanceof  Double) {
//        array.pushDouble((Double) value);
//      } else if (value instanceof String)  {
//        array.pushString((String) value);
//      } else {
//        array.pushString(value.toString());
//      }
//    }
//    return array;
//  }
//  public static Map<String, Object> toMap(ReadableMap readableMap) {
//    Map<String, Object> map = new HashMap<>();
//    ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
//
//    while (iterator.hasNextKey()) {
//      String key = iterator.nextKey();
//      ReadableType type = readableMap.getType(key);
//
//      switch (type) {
//        case Null:
//          map.put(key, null);
//          break;
//        case Boolean:
//          map.put(key, readableMap.getBoolean(key));
//          break;
//        case Number:
//          map.put(key, readableMap.getDouble(key));
//          break;
//        case String:
//          map.put(key, readableMap.getString(key));
//          break;
//        case Map:
//          map.put(key, MapUtil.toMap(readableMap.getMap(key)));
//          break;
//        case Array:
//          map.put(key, ArrayUtil.toArray(readableMap.getArray(key)));
//          break;
//      }
//    }
//
//    return map;
//  }
//
//  public static WritableMap toWritableMap(Map<String, Object> map) {
//    WritableMap writableMap = Arguments.createMap();
//    Iterator iterator = map.entrySet().iterator();
//
//    while (iterator.hasNext()) {
//      Map.Entry pair = (Map.Entry)iterator.next();
//      Object value = pair.getValue();
//
//      if (value == null) {
//        writableMap.putNull((String) pair.getKey());
//      } else if (value instanceof Boolean) {
//        writableMap.putBoolean((String) pair.getKey(), (Boolean) value);
//      } else if (value instanceof Double) {
//        writableMap.putDouble((String) pair.getKey(), (Double) value);
//      } else if (value instanceof Integer) {
//        writableMap.putInt((String) pair.getKey(), (Integer) value);
//      } else if (value instanceof String) {
//        writableMap.putString((String) pair.getKey(), (String) value);
//      } else if (value instanceof Map) {
//        writableMap.putMap((String) pair.getKey(), MapUtil.toWritableMap((Map<String, Object>) value));
//      }
//      else if (value.getClass() != null && value.getClass().isArray()) {
//        writableMap.putArray((String) pair.getKey(), ArrayUtil.toWritableArray((Object[]) value));
//      }
//
//      iterator.remove();
//    }
//
//    return writableMap;
//  }
//
  public static SubHeaderText toSubHeader(Context context, JSONObject map) throws Exception {
    SubHeaderText subHeaderText = new SubHeaderText();
    subHeaderText.setDrawable( map.getString(context.getString(R.string.CAMERA_OPTIONS_DRAWABLE)));
//    if(map.has(context.getString(R.string.CAMERA_OPTIONS_SUB_HEADER_TEXT))) {
//      String type = map. (context.getString(R.string.CAMERA_OPTIONS_SUB_HEADER_TEXT));
//      switch (type){
//        case Array:
//          subHeaderText.setSubHeaderTextArr(ArrayUtil.toStringArray(map.getArray(context.getString(R.string.CAMERA_OPTIONS_SUB_HEADER_TEXT))));
//          break;
//        case String:
//          subHeaderText.setSubHeaderText(map.getString(context.getString(R.string.CAMERA_OPTIONS_SUB_HEADER_TEXT)));
//          break;
//      }
//    }
//      subHeaderText.setSubHeaderText(map.getString(context.getString(R.string.CAMERA_OPTIONS_SUB_HEADER_TEXT)));
//    else if(map.hasKey(context.getString(R.string.CAMERA_OPTIONS_SUB_HEADER_TEXT_LINES)))
//      subHeaderText.setSubHeaderTextArr(ArrayUtil.toStringArray(map.getArray(context.getString(R.string.CAMERA_OPTIONS_SUB_HEADER_TEXT_LINES))));
    return subHeaderText;
  }
  public static LayoutParameters toLayoutParams(Context context, JSONObject map) {
    LayoutParameters layoutParameters = new LayoutParameters();
    layoutParameters.setWidth(map.optInt(context.getString(R.string.LAYOUT_WIDTH)));
    layoutParameters.setHeight(map.optInt(context.getString(R.string.LAYOUT_HEIGHT)));
    return layoutParameters;
  }
}
