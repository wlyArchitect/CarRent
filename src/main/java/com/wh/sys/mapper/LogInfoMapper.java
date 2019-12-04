package com.wh.sys.mapper;

import com.wh.sys.entity.LogInfo;

import java.util.List;

public interface LogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo record);

    int insertSelective(LogInfo record);

    LogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);
    /**
     * 查询日志:全查询&模糊查询
     */
    List<LogInfo> queryAllLogInfo(LogInfo logInfo);
}