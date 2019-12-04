package com.wh.bus.service;

import com.wh.bus.entity.Rent;
import com.wh.bus.vo.RentVo;
import com.wh.sys.utils.DataGridViewUtils;

/**
 * @author 万浩
 * @data 2019/12/4 9:26
 * @description
 */
public interface RentService {
    /**
     * 保存出租单
     * @param rentVo
     */
    void addRent(RentVo rentVo);

    /**
     * 全查询
     * @param rentVo
     * @return
     */
    DataGridViewUtils queryAllRent(RentVo rentVo);

    /**
     * 修改出租单信息
     * @param rentVo
     */
    void updateRent(RentVo rentVo);

    /**
     * 删除租单
     * @param rentid
     */
    void deleteRent(String rentid);

    /**
     * 查询出租单号信息 通过id
     * @param rentid
     * @return
     */
    Rent queryRentByRentId(String rentid);
}
