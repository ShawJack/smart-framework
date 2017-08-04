package org.smart4j.framework.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by ithink on 2017-6-13.
 */
public class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

        if(CollectionUtil.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>, Object> entry : beanMap.entrySet()){
                Class<?> cls = entry.getKey();
                Object obj = entry.getValue();

                Field[] fields = cls.getDeclaredFields();
                if(ArrayUtils.isNotEmpty(fields)){
                    for(Field field : fields){
                        if(field.isAnnotationPresent(Inject.class)){
                            Class<?> fieldClass = field.getType();
                            Object fieldInstance = beanMap.get(fieldClass);
                            if(fieldInstance != null) {
                                ReflectionUtil.setField(obj, field, fieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }

}
