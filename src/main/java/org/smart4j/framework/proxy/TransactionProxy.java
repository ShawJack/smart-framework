package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;

import java.lang.reflect.Method;

/**
 * 事务代理
 * Created by ithink on 2017-6-26.
 */
public class TransactionProxy implements Proxy {

    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    public Object doProxy(ProxyChain proxyChain) throws Throwable {

        Object result;

        Method method = proxyChain.getTargetMethod();
        if(method!=null && method.isAnnotationPresent(Transaction.class)){
            FLAG_HOLDER.set(true);
            try{
                DatabaseHelper.beginTransaction();
                LOGGER.debug("begin transaction");
                result = proxyChain.doProxy();
                LOGGER.debug("commit transaction");
                DatabaseHelper.commitTransaction();
            } catch(Exception e){
                DatabaseHelper.rollbackTransaction();
                LOGGER.error("rollback transaction");
                throw e;
            } finally {
                FLAG_HOLDER.remove();
            }
        } else{
            result = proxyChain.doProxy();
        }

        return result;
    }

}
