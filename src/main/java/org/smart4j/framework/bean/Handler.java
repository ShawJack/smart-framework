package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by ithink on 2017-6-17.
 */
public class Handler {

    /**
     * Controller类
     */
    private Class<?> controllerClass;

    /**
     * Action方法
     */
    private Method action;

    public Handler(Class<?> cls, Method method){
        controllerClass = cls;
        action = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getAction() {
        return action;
    }

}
