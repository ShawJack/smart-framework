package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by ithink on 2017-6-20.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value();

}
