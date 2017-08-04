package org.smart4j.demo.aspect;

import com.sun.javafx.binding.StringFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * Created by ithink on 2017-6-20.
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy{
    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long startTime;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        LOGGER.debug("------------begin-----------");
        LOGGER.debug(String.format("class : %s", cls.getName()));
        LOGGER.debug(String.format("method : %s", method.getName()));
        startTime = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        LOGGER.debug(String.format("time : %dms", System.currentTimeMillis()-startTime));
        LOGGER.debug("------------end-----------");
    }
}
