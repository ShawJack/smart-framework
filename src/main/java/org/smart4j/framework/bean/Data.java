package org.smart4j.framework.bean;


/**
 * 封装返回的数据对象
 * Created by ithink on 2017-6-17.
 */
public class Data {

    private Object model;

    public Data(Object model){
        this.model = model;
    }

    public Object getModel() {
        return model;
    }

}
