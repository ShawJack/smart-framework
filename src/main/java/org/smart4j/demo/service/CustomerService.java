package org.smart4j.demo.service;

import org.smart4j.demo.model.Customer;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by ithink on 2017-6-18.
 */
@Service
public class CustomerService {

    public List<Customer> getCustomerList(){
        String sql = "select * from customer;";
        List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class, sql);

        return customerList;
    }

    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap){
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    public Customer getCustomer(long id){
        String sql = "select * from customer where id=?;";
        Customer customer = DatabaseHelper.queryEntity(Customer.class, sql, id);
        return customer;
    }

    @Transaction
    public boolean editCustomer(Map<String, Object> fieldMap, long id){
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    @Transaction
    public boolean deleteCustomer(long id){
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }

}
