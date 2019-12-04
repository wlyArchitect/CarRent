package com.wh.bus.mapper;

import com.wh.bus.entity.Check;
import com.wh.bus.vo.CheckVo;

import java.util.List;

public interface CheckMapper {
    int deleteByPrimaryKey(String checkid);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(String checkid);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);

    /**
     * 全查询
     * @param checkVo
     * @return
     */
    List<Check> queryAllCheck(CheckVo checkVo);
}