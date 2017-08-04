package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropsUtil;

import java.util.Properties;

/**
 * 配置文件助手类
 * Created by ithink on 2017-6-13.
 */
public class ConfigHelper {

    private final static Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 应用基础包
     */
    public static String getBasePath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.BASE_PACKAGE);
    }

    /**
     * JSP路径
     */
    public static String getJspPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JSP_PATH);
    }

    /**
     * 静态资源路径
     */
    public static String getAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.ASSET_PATH);
    }

    /**
     * JDBC驱动
     */
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * JDBC URL
     */
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * JDBC 用户名
     */
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * JDBC 密码
     */
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }


}
