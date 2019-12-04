package com.wh.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.sys.entity.LogInfo;
import com.wh.sys.mapper.LogInfoMapper;
import com.wh.sys.service.LogInfoService;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 万浩
 * @data 2019/12/1 10:14
 * @description
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    /**
     * 查询 所有的日志&模糊查询
     *
     * 分页展示在前台:从数据库种获取,layui+list数据格式展示在前台
     * @param logInfoVo
     * @return
     */
    @Override
    public DataGridViewUtils queryAllLogInfo(LogInfoVo logInfoVo) {
        //使用分页插件PageHelper
        Page<Object> page = PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
        List<LogInfo> data = this.logInfoMapper.queryAllLogInfo(logInfoVo);
        return new DataGridViewUtils(page.getTotal(), data);

    }

    /**
     * 添加日志
     * @param logInfoVo
     */
    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
           this.logInfoMapper.insertSelective(logInfoVo);
    }

    /**
     * 删除日志
     * @param logInfoid
     */
    @Override
    public void deleteLogInfo(Integer logInfoid) {
        this.logInfoMapper.deleteByPrimaryKey(logInfoid);
    }

    /**
     * 批量删除日志
     * @param ids
     */
    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer id : ids) {
            this.logInfoMapper.deleteByPrimaryKey(id);
        }
    }
}
