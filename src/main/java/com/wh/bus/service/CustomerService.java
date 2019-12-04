package com.wh.bus.service;

import com.wh.bus.entity.Customer;
import com.wh.bus.vo.CustomerVo;
import com.wh.sys.utils.DataGridViewUtils;


/**
 * @author 万浩
 * @data 2019/12/2 19:14
 * @description
 */
public interface CustomerService {
    /**
     * 查询所有的客户
     */
    DataGridViewUtils queryAllCustomer(CustomerVo customerVo);
    /**
     * 添加客户
     * @param customerVo
     */
    public void addCustomer(CustomerVo customerVo);
    /**
     * 修改客户
     * @param customerVo
     */
    public void updateCustomer(CustomerVo customerVo);
    /**
     * 根据id删除客户
     * @param identity
     */
    public void deleteCustomer(String identity);
    /**
     * 批量删除客户
     * @param identitys
     */
    public void deleteBatchCustomer(String [] identitys);

    /**
     * 查询客户根据身份证
     * @param identity
     * @return
     */
    Customer queryCustomerByIdentity(String identity);
}
