package com.yunwan.erling.Tools;

import com.alibaba.fastjson.JSON;

/**
 * Created by zuo on 2016/12/17.
 */

public class Tools {
    //    public static <T> T model(String result, Class<T> clazz) {
//        ValueFilter valueFilter = new ValueFilter() {
//            @Override
//            public Object process(Object object, String name, Object value) {
//                if (value == null)
//                    return "";
//                return value;
//            }
//        };
//        T obj = JSON.parseObject(result, clazz);
//        String json = JSON.toJSONString(obj, valueFilter);
//        obj = JSON.parseObject(json, clazz);
//        return obj;
//    }
    public static <T> T model(String result, Class<T> clazz) {

        T obj = JSON.parseObject(result, clazz);
        return obj;
    }
}
