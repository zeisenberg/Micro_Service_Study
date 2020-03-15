package com.szkingdom.user.server.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @Description 商品 controller
 * @Author zhaizhengwei
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换为对象
     *
     * @param str
     * @return
     */
    public static <T> T fromJson(String str, Class<T> t) {
        try {
            return objectMapper.readValue(str, t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换为对象List，方法一
     *
     * @param str
     * @return
     */
    public static <T> T fromJsonToList1(String str, Class<T> t) {
        try {
            return objectMapper.readValue(str, new TypeReference<T>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转换为对象List，方法二
     *
     * @param str
     * @return
     */
    public static <T> T fromJsonToList2(String str, Class<T> t) {
        try {
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, t);
            return objectMapper.readValue(str, collectionType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
