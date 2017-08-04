package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.ClassUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean容器
 * Created by ithink on 2017-6-13.
 */
public class BeanHelper {

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();

        for(Class<?> cls : classSet){
            BEAN_MAP.put(cls, ReflectionUtil.newInstance(cls));
        }

    }

    /**
     * 获取Bean Map
     */
    public static Map<Class<?>, Object> getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> cls){
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 添加映射
     */
    public static void setBean(Class<?> cls, Object obj){
        BEAN_MAP.put(cls, obj);
    }

}
