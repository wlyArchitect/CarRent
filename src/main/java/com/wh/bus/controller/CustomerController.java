package com.wh.bus.controller;

import com.wh.bus.service.CustomerService;
import com.wh.bus.vo.CustomerVo;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 客户管理控制器
 *
 * @author 万浩
 * @data 2019/12/2 19:17
 * @description
 */
@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    /**
     * 加载客户列表返回DataGridView
     */
    @RequestMapping("/loadAllCustomer")
    public DataGridViewUtils loadAllCustomer(CustomerVo customerVo) {
        return this.customerService.queryAllCustomer(customerVo);
    }
    /**
     * 添加客户
     */
    @RequestMapping("/addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo) {
        try {
            //设置创建时间
            customerVo.setCreatetime(new Date());
            this.customerService.addCustomer(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改客户
     */
    @RequestMapping("/updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo) {
        try {
            this.customerService.updateCustomer(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除客户
     */
    @RequestMapping("/deleteCustomer")
    public ResultObj deleteCustomer(CustomerVo customerVo) {
        try {
            this.customerService.deleteCustomer(customerVo.getIdentity());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除客户
     */
    @RequestMapping("deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(CustomerVo customerVo) {
        try {
            this.customerService.deleteBatchCustomer(customerVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
