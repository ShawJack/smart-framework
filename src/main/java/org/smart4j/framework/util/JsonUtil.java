package org.smart4j.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by ithink on 2017-6-18.
 */
public class JsonUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 转为Json
     */
    public static <T> String toJson(T obj){
        String json;

        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("to json failure", e);
            throw new RuntimeException(e);
        }

        return json;
    }

    public static <T> T fromJson(String json, Class<T> cls){
        T pojo;

        try {
            pojo = OBJECT_MAPPER.readValue(json, cls);
        } catch (IOException e) {
            LOGGER.error("convert json failure", e);
            throw new RuntimeException(e);
        }

        return pojo;
    }

}
