package org.smart4j.demo.controller;

import org.smart4j.demo.model.Customer;
import org.smart4j.demo.service.CustomerService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.List;
import java.util.Map;

/**
 * Created by ithink on 2017-6-18.
 */
@Controller
public class CustomerController {

    @Inject
    CustomerService customerService;

    @Action("get:/customer")
    public View index(Param param){
        List<Customer> customerList = customerService.getCustomerList();

        return new View("customer.jsp").addModel("customerList", customerList);
    }

    @Action("get:/customer_create")
    public View create(Param param){
        return new View("customer_create.jsp");
    }

    @Action("post:/customer_create")
    public Data createSubmit(Param param){
        Map<String, Object> fieldMap = param.getParamMap();
        boolean result = customerService.createCustomer(fieldMap);
        return new Data(result);
    }

    @Action("get:/customer_edit")
    public View edit(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_edit.jsp").addModel("customer", customer);
    }

    @Action("post:/customer_edit")
    public Data editSubmit(Param param){
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getParamMap();
        boolean result = customerService.editCustomer(fieldMap, id);
        return new Data(result);
    }

    @Action("get:/customer_delete")
    public View deleteCustomer(Param param){
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new View("/customer");
    }

}