package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;

import java.util.Map;

/**
 * 请求参数封装
 * Created by ithink on 2017-6-17.
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap){
        this.paramMap = paramMap;
    }

    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getParamMap(){
        return paramMap;
    }

}
