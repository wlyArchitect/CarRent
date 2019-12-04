package com.wh.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.bus.entity.Customer;
import com.wh.bus.mapper.CustomerMapper;
import com.wh.bus.service.CustomerService;
import com.wh.bus.vo.CustomerVo;
import com.wh.sys.utils.DataGridViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 万浩
 * @data 2019/12/2 19:16
 * @description
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    /**
     * 查询所有的客户 & 全查询
     * @param customerVo
     * @return
     */
    @Override
    public DataGridViewUtils queryAllCustomer(CustomerVo customerVo) {
        Page<Object> page= PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
        List<Customer> data = this.customerMapper.queryAllCustomer(customerVo);
        return new DataGridViewUtils(page.getTotal(), data);
    }

    /**
     * 添加客户
     * @param customerVo
     */
    @Override
    public void addCustomer(CustomerVo customerVo) {
        this.customerMapper.insertSelective(customerVo);
    }

    /**
     * 删除客户信息
     * @param identity
     */
    @Override
    public void deleteCustomer(String identity) {
        this.customerMapper.deleteByPrimaryKey(identity);
    }

    /**
     * 批量删除
     * @param identitys
     */
    @Override
    public void deleteBatchCustomer(String[] identitys) {
        for (String identity : identitys) {
            this.deleteCustomer(identity);
        }
    }

    /**
     * 根据身份证查询客户信息
     * @param identity
     * @return
     */
    @Override
    public Customer queryCustomerByIdentity(String identity) {
        return this.customerMapper.selectByPrimaryKey(identity);
    }

    /**
     * 修改客户信息
     * @param customerVo
     */
    @Override
    public void updateCustomer(CustomerVo customerVo) {
        this.customerMapper.updateByPrimaryKeySelective(customerVo);
    }
}