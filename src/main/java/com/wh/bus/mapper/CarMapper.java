package com.wh.bus.mapper;

import com.wh.bus.entity.Car;

import java.util.List;

public interface CarMapper {
    int deleteByPrimaryKey(String carnumber);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String carnumber);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    /**
     * 全查询&模糊查询所有的汽车
     */
    List<Car> queryAllCar(Car car);
}