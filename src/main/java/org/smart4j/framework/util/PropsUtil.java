package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ithink on 2017-6-13.
 */
public class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String filename){
        InputStream is = null;
        Properties properties = null;

        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            properties = new Properties();
            properties.load(is);
        } catch(IOException e){
            LOGGER.error("load properties file failure", e);
            throw new RuntimeException(e);
        } finally {
            if(is != null){
                try{
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close inputstream failure", e);
                    throw new RuntimeException(e);
                }
            }
        }

        return properties;
    }

    /**
     * 获取字符串属性
     */
    public static String getString(Properties properties, String key){
        return getString(properties, key, "");
    }

    /**
     * 获取字符串属性
     */
    public static String getString(Properties properties, String key, String defaultValue){
        String value = defaultValue;

        if(properties.containsKey(key)){
            value = properties.getProperty(key);
        }

        return value;
    }

}
