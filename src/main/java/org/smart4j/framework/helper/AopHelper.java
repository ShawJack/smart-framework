package org.smart4j.framework.helper;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;
import org.smart4j.framework.proxy.TransactionProxy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * AOP助手类
 * Created by ithink on 2017-6-20.
 */
public class AopHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Set<Class<?>> serviceClassSet = ClassHelper.getServiceClassSet();
            proxyMap.put(TransactionProxy.class, serviceClassSet);

            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);

            for(Map.Entry<Class<?>, List<Proxy>> entry : targetMap.entrySet()){
                final Class<?> cls = entry.getKey();
                final List<Proxy> list = entry.getValue();

                Object proxy = ProxyManager.createProxy(cls, list);

                BeanHelper.setBean(cls, proxy);
            }
        } catch (Exception e){
            LOGGER.error("aop failure", e);
        }

    }

    /**
     * 代理类与被代理类呈一对多的关系
     */
    public static Map<Class<?>, Set<Class<?>>> createProxyMap(){
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> classSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for(Class<?> cls : classSet){
            if(cls.isAnnotationPresent(Aspect.class)){
                Aspect aspect = cls.getAnnotation(Aspect.class);
                Class<? extends Annotation> targetAnnotation = aspect.value();
                if(targetAnnotation!=null && !targetAnnotation.equals(Aspect.class)){
                    Set<Class<?>> targetClassSet = ClassHelper.getClassSetByAnnotation(targetAnnotation);
                    proxyMap.put(cls, targetClassSet);
                }
            }
        }

        return proxyMap;
    }

    /**
     * 目标类与代理类呈一对多的关系
     */
    public static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();

        for(Map.Entry<Class<?>, Set<Class<?>>> entry : proxyMap.entrySet()){
            Class<?> proxyCls = entry.getKey();
            Set<Class<?>> targetClassSet = entry.getValue();

            for(Class<?> target : targetClassSet){
                Proxy proxy = (Proxy) proxyCls.newInstance();
                if(targetMap.containsKey(target)){
                    targetMap.get(target).add(proxy);
                } else{
                    List<Proxy> list = new ArrayList<Proxy>();
                    list.add(proxy);
                    targetMap.put(target, list);
                }
            }
        }

        return targetMap;
    }

    @Test
    public void test() throws Exception{
        List<Proxy> list = new ArrayList<Proxy>();
        list.add(new AProxy());
        list.add(new BProxy());

        Hello proxy = (Hello) ProxyManager.createProxy(Hello.class, list);
        proxy.sayHello("Jack");
    }

    @Test
    public void test2(){
        File f = new File("C:\\Users\\ithink\\Desktop\\est.txt");
        File parentDir = f.getParentFile();
        if(!parentDir.exists()){
            System.out.println(parentDir.toString());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write("test");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Hello{
    public void sayHello(String name){
        System.out.println("Hello, " + name);
    }
}

class AProxy extends AspectProxy{
    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        System.out.println("------A before------");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        System.out.println("------A after------");
    }
}

class BProxy extends AspectProxy{
    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        System.out.println("------B before------");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        System.out.println("------B after------");
    }
}