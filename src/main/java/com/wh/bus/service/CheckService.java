package com.wh.bus.service;

import com.wh.bus.vo.CheckVo;
import com.wh.sys.utils.DataGridViewUtils;

import java.util.Map;

/**
 * @author 万浩
 * @data 2019/12/4 15:17
 * @description
 */
public interface CheckService {
    /**
     * 据出租单号 加载检查单的表单数据初始化
     *
     * @param rentid
     * @return
     */
    Map<String, Object> initCheckFormData(String rentid);

    /**
     * 添加出租单信息
     *
     * @param checkVo
     */
    void addCheck(CheckVo checkVo);

    /**
     * 查询所有的 出租单检查 信息
     *
     * @param checkVo
     * @return
     */
    DataGridViewUtils queryAllCheck(CheckVo checkVo);

    /**
     * 修改出租单信息
     *
     * @param checkVo
     */
    void updateCheck(CheckVo checkVo);


}
