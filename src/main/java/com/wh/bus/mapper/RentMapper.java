package com.wh.bus.mapper;

import com.wh.bus.entity.Rent;

import java.util.List;

public interface RentMapper {
    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    Rent selectByPrimaryKey(String rentid);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);
    /**
     * 全查询&模糊查询
     */
    List<Rent> queryAllRent(Rent rent);
}