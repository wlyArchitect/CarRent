package com.wh.sys.controller;

import com.wh.sys.service.LogInfoService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.ResultObj;
import com.wh.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理控制器
 * @author 万浩
 * @data 2019/12/1 11:01
 * @description
 */
@RestController
@RequestMapping("/logInfo")
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;
    /**
     * 加载日志列表返回DataGridView
     * 全查询&模糊查询
     */
    @RequestMapping("loadAllLogInfo")
    public DataGridViewUtils loadAllLogInfo(LogInfoVo logInfoVo) {
        return this.logInfoService.queryAllLogInfo(logInfoVo);
    }

    /**
     * 删除日志
     */
    @RequestMapping("/deleteLogInfo")
    public ResultObj deleteLogInfo(LogInfoVo logInfoVo) {
        try {
            this.logInfoService.deleteLogInfo(logInfoVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     */
    @RequestMapping("/deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(LogInfoVo logInfoVo) {
        try {
            this.logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
