package org.smart4j.framework.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 * Created by ithink on 2017-6-17.
 */
public class ControllerHelper {

    private final static Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static{
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            for(Class<?> cls : controllerClassSet){

                if(cls.isAnnotationPresent(Controller.class)){
                    Method[] methods = cls.getDeclaredMethods();

                    if(ArrayUtils.isNotEmpty(methods)){
                        for(Method method : methods){
                            if(method.isAnnotationPresent(Action.class)){
                                Action action = method.getAnnotation(Action.class);
                                String mapping = action.value();
                                if(mapping.matches("\\w+:/\\w+")){
                                    String[] array = mapping.split(":");
                                    if(ArrayUtils.isNotEmpty(array) && array.length==2){
                                        String requestMethod = array[0];
                                        String requestPath = array[1];
                                        Request request = new Request(requestMethod, requestPath);
                                        Handler handler = new Handler(cls, method);

                                        ACTION_MAP.put(request, handler);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     */
    public static Handler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

}
