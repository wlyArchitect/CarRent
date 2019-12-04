package com.wh.bus.mapper;

import com.wh.bus.entity.Customer;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(String identity);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String identity);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    /**
     * 全查询&模糊查询
     */
    List<Customer> queryAllCustomer(Customer customer);
}