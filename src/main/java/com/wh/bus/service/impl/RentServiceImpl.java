package com.wh.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.bus.entity.Car;
import com.wh.bus.entity.Rent;
import com.wh.bus.mapper.CarMapper;
import com.wh.bus.mapper.RentMapper;
import com.wh.bus.service.RentService;
import com.wh.bus.vo.RentVo;
import com.wh.sys.constant.SysConstant;
import com.wh.sys.utils.DataGridViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 万浩
 * @data 2019/12/4 9:26
 * @description
 */
@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CarMapper carMapper;

    /**
     * 添加出租单号
     * 并且设置汽车状态
     * @param rentVo
     */
    @Override
    public void addRent(RentVo rentVo) {
        //添加汽车出租单号,修改bus_rent表
        this.rentMapper.insertSelective(rentVo);
        //更改汽车状态,需要修改bus_car表
        Car car = new Car();
        car.setCarnumber(rentVo.getCarnumber());
        //设置汽车状态:已出租
        car.setIsrenting(SysConstant.RENT_CAR_TRUE);
        carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 全查询 汽车出租单号
     * @param rentVo
     * @return
     */
    @Override
    public DataGridViewUtils queryAllRent(RentVo rentVo) {
        Page<Object> page= PageHelper.startPage(rentVo.getPage(), rentVo.getLimit());
        List<Rent> data = this.rentMapper.queryAllRent(rentVo);
        return new DataGridViewUtils(page.getTotal(), data);
    }

    /**
     * 修改汽车出租单号
     * @param rentVo
     */
    @Override
    public void updateRent(RentVo rentVo) {
        this.rentMapper.updateByPrimaryKeySelective(rentVo);
    }

    /**
     * 删除汽车出租单号:用于汽车已归还,添加到bus_check检查单
     *
     * @param rentid
     */
    @Override
    public void deleteRent(String rentid) {
        //更改汽车的状态
        //获取订单信息
        Rent rent=this.rentMapper.selectByPrimaryKey(rentid);
        Car car=new Car();
        //设置汽车信息的id
        car.setCarnumber(rent.getCarnumber());
        //设置汽车的状态:未出租
        car.setIsrenting(SysConstant.RENT_CAR_FALSE);
        //修改汽车信息
        carMapper.updateByPrimaryKeySelective(car);
        //删除出租单中的记录  bus_rent表
        this.rentMapper.deleteByPrimaryKey(rentid);
    }

    /**
     * 通过id查询出汽车出租信息
     * @param rentid
     * @return
     */
    @Override
    public Rent queryRentByRentId(String rentid) {
        return this.rentMapper.selectByPrimaryKey(rentid);
    }
}
