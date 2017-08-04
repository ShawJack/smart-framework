package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手类
 * Created by ithink on 2017-6-13.
 */
public class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    static {
        CLASS_SET = ClassUtil.getClassSet(ConfigHelper.getBasePath());
    }

    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取所有Service类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> serviceClassSet = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                serviceClassSet.add(cls);
            }
        }

        return serviceClassSet;
    }

    /**
     * 获取所有Controller类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> controllerClassSet = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                controllerClassSet.add(cls);
            }
        }

        return controllerClassSet;
    }

    /**
     * 获取所有Bean类
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getControllerClassSet());
        beanClassSet.addAll(getServiceClassSet());

        return beanClassSet;
    }

    /**
     * 获取某父类的所有子类，或接口的所有实现类
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(superClass.isAssignableFrom(cls) && cls!=superClass && !cls.equals(superClass)){
                classSet.add(cls);
            }
        }

        return classSet;
    }

    /**
     * 获取带有某注解的类集合
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }

        return classSet;
    }

}
