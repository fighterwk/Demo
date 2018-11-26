package com.example.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Description描述: Gson 对象转换工具类
 * @Author作者: Kyle
 * @Date日期: 2017/10/20
 */
public class GsonUtil {


    private static Gson gson = null;

    static {
        gson = new Gson();
    }


    private GsonUtil() {
        throw new RuntimeException();
    }


    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(object);
        }
        return json;
    }


    /**
     * 将json转成bean
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T toBean(String json, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, cls);
        }
        return t;
    }

    /**
     * 将json转成bean
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T toBean(String json, Type type) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, type);
        }
        return t;
    }

    /**
     * 转成list
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }


    /**
     * 转成map的
     *
     * @param json
     * @return
     */
    public static <T> Map<String, T> toMap(String json) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

}  