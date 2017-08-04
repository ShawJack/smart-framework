package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图
 * Created by ithink on 2017-6-17.
 */
public class View {

    /**
     * 请求转发路径
     */
    private String path;


    /**
     * 请求参数
     */
    private Map<String, Object> model;

    public View(String path){
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value){
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

}
