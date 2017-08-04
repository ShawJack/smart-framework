package org.smart4j.framework.proxy;

/**
 * 代理接口
 * Created by ithink on 2017-6-20.
 */
public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
