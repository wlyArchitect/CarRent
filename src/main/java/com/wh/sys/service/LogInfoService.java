package com.wh.sys.service;

import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.vo.LogInfoVo;

/**
 * 服务层: 角色的日志
 * @author 万浩
 * @data 2019/11/29 16:55
 * @description
 */
public interface LogInfoService {
    /**
     * 查询所有日志&模糊查询
     * @param logInfoVo
     * @return
     */
    public DataGridViewUtils queryAllLogInfo(LogInfoVo logInfoVo);
    /**
     * 添加日志
     * @param logInfoVo
     */
    public void addLogInfo(LogInfoVo logInfoVo);
    /**
     * 根据id删除日志
     * @param logInfoid
     */
    public void deleteLogInfo(Integer logInfoid);
    /**
     * 批量删除日志
     * @param ids
     */
    public void deleteBatchLogInfo(Integer [] ids);

}
