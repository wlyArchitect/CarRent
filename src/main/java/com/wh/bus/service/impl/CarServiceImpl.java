package com.wh.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.bus.entity.Car;
import com.wh.bus.mapper.CarMapper;
import com.wh.bus.service.CarService;
import com.wh.bus.vo.CarVo;
import com.wh.sys.constant.SysConstant;
import com.wh.sys.utils.AppFileUtils;
import com.wh.sys.utils.DataGridViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 万浩
 * @data 2019/12/2 19:45
 * @description
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;
    /**
     * 全查询所有的汽车
     * @param carVo
     * @return
     */
    @Override
    public DataGridViewUtils queryAllCar(CarVo carVo) {
        Page<Object> page= PageHelper.startPage(carVo.getPage(), carVo.getLimit());
        List<Car> data = this.carMapper.queryAllCar(carVo);
        return new DataGridViewUtils(page.getTotal(), data);
    }

    /**
     * 添加汽车
     * @param carVo
     */
    @Override
    public void addCar(CarVo carVo) {
        this.carMapper.insertSelective(carVo);
    }

    /**
     * 删除汽车
     * @param carnumber
     */
    @Override
    public void deleteCar(String carnumber) {
        //先删除图片
        Car car = this.carMapper.selectByPrimaryKey(carnumber);
        //不是默认图片 才进行硬盘的文件删除
        if (!car.getCarimg().equals(SysConstant.DEFAULT_CAR_IMG)) {
            System.err.println("删除路径操作...");
            AppFileUtils.deleteFileUsePath(car.getCarimg());
        }
        //再在数据库中删除这条记录
        this.carMapper.deleteByPrimaryKey(carnumber);
    }

    /**
     * 批量删除汽车
     * @param carnumbers
     */
    @Override
    public void deleteBatchCar(String[] carnumbers) {
        for (String carnumber : carnumbers) {
            this.deleteCar(carnumber);
        }
    }

    /**
     * 根据车牌号查询车
     * @param carnumber
     * @return
     */
    @Override
    public Car queryCarByCarNumber(String carnumber) {
        return this.carMapper.selectByPrimaryKey(carnumber);
    }

    /**
     * 修改汽车
     * @param carVo
     */
    @Override
    public void updateCar(CarVo carVo) {
        this.carMapper.updateByPrimaryKeySelective(carVo);
    }

}
