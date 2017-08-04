package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by ithink on 2017-6-20.
 */
public abstract class AspectProxy implements Proxy {
    private final static Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;

        Class<?> targetClass = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getParams();

        begin();
        try {
            if (intercept(targetClass, targetMethod, params)) {
                before(targetClass, targetMethod, params);
                result = proxyChain.doProxy();
                after(targetClass, targetMethod, params, result);
            } else {
                result = proxyChain.doProxy();
            }
        } catch (Exception e){
            LOGGER.error("proxy failure");
            error(targetClass, targetMethod, params, e);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    public void begin(){}

    public boolean intercept(Class<?> cls, Method method, Object[] params){
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params){

    }

    public void after(Class<?> cls, Method method, Object[] params, Object result){

    }

    public void error(Class<?> targetClass, Method targetMethod, Object[] params, Exception e){

    }

    public void end(){}
}
