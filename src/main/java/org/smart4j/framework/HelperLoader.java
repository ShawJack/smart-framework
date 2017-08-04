package org.smart4j.framework;

import org.junit.Test;
import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * Created by ithink on 2017-6-17.
 */
public class HelperLoader {

    public static void init(){
        Class<?>[] classList = {
            ClassHelper.class, BeanHelper.class, AopHelper.class, IocHelper.class, ControllerHelper.class
        };

        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(), true);
        }
    }

    @Test
    public void test(){
        init();
    }

}
