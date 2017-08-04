package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理链
 * Created by ithink on 2017-6-20.
 */
public class ProxyChain {

    private int proxyIndex = 0;
    private List<Proxy> proxyList;
    private Class<?> targetClass;
    private Object targetObject;
    private Method targetMethod;
    private MethodProxy methodProxy;

    private Object[] params;

    public ProxyChain(List<Proxy> proxyList, Class<?> targetClass, Object targetObject,
                      Method targetMethod, MethodProxy methodProxy, Object[] params){
        this.methodProxy = methodProxy;
        this.params = params;
        this.proxyList = proxyList;
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.targetObject = targetObject;
    }

    public Object doProxy() throws Throwable{
        Object result;
        if (proxyIndex < proxyList.size()){
            result = proxyList.get(proxyIndex++).doProxy(this);
        } else{
            result = methodProxy.invokeSuper(targetObject, params);
        }

        return result;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getParams() {
        return params;
    }

}
