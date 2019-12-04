package com.wh.bus.controller;

import com.wh.bus.entity.Rent;
import com.wh.bus.service.CheckService;
import com.wh.bus.service.RentService;
import com.wh.bus.vo.CheckVo;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * 检查单管理的控制器
 *
 * @author 万浩
 * @data 2019/12/4 15:16
 * @description
 */
@RequestMapping("/check")
@RestController
public class CheckController {
    @Autowired
    private RentService rentService;
    @Autowired
    private CheckService checkService;

    /***
     * 根据出租单号查询出租单信息
     */
    @RequestMapping("checkRentExist")
    public Rent checkRentExist(String rentid) {
        //null   返回对象
        Rent rent=rentService.queryRentByRentId(rentid);
        return rent;
    }

    /**
     * 根据出租单号 加载检查单的表单数据初始化
     */
    @RequestMapping("/initCheckFormData")
    public Map<String,Object> initCheckFormData(String rentid){
        return this.checkService.initCheckFormData(rentid);
    }
    /**
     * 保存检查单数据,汽车入库
     */
    @RequestMapping("/saveCheck")
    public ResultObj saveCheck(CheckVo checkVo) {
        try {
            //设置创建时间
            checkVo.setCreatetime(new Date());
            this.checkService.addCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询
     * @param checkVo
     * @return
     */
    @RequestMapping("loadAllCheck")
    public DataGridViewUtils loadAllCheck(CheckVo checkVo) {
        return this.checkService.queryAllCheck(checkVo);
    }

    /**
     * 修改检查单数据
     */
    @RequestMapping("updateCheck")
    public ResultObj updateCheck(CheckVo checkVo) {
        try {
            this.checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}
