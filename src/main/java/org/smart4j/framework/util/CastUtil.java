package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ithink on 2017-6-13.
 */
public class CastUtil {

    /**
     * 转为String型
     */
    public static String castString(Object obj){
        return castString(obj, "");
    }

    /**
     * 转为String型（提供默认值）
     */
    public static String castString(Object obj, String defaultValue){
        return obj!=null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为double型
     */
    public static double castDouble(Object obj){
        return castDouble(obj, 0.0);
    }

    /**
     * 转为double型（提供默认智）
     */
    public static double castDouble(Object obj, double defaultValue){
        double value = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try {
                    value = Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    /**
     * 转为long型
     */
    public static long castLong(Object obj){
        return castLong(obj, 0L);
    }

    /**
     * 转为long型（指定默认值）
     */
    public static long castLong(Object obj, long defaultValue){
        long value = defaultValue;

        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value = Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    /**
     * 转为int型
     */
    public static int castInt(Object obj){
        return castInt(obj, 0);
    }

    /**
     * 转为int型（可指定默认值）
     */
    public static int castInt(Object obj, int defaultValue){
        int value = defaultValue;

        if(obj != null){
            String strValue = castString(obj);

            if(StringUtil.isNotEmpty(strValue)){
                try {
                    value = Integer.parseInt(strValue);
                }catch(NumberFormatException e){
                    value = defaultValue;
                }
            }
        }

        return value;
    }

    /**
     * 转为boolean型
     */
    public static boolean castBoolean(Object obj){
        return castBoolean(obj, false);
    }

    /**
     * 转为boolean型
     */
    public static boolean castBoolean(Object obj, boolean defaultValue){
        boolean value = defaultValue;

        if(obj != null){
            String strValue = castString(obj);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value = Boolean.parseBoolean(strValue);
                }catch(NumberFormatException e){
                    value = defaultValue;
                }
            }
        }

        return value;
    }

}
