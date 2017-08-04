package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ithink on 2017-5-24.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        if(str != null){
            return StringUtils.isEmpty(str.trim());
        }

        return true;
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
