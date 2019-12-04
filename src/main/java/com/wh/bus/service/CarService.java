package com.wh.bus.service;

import com.wh.bus.entity.Car;
import com.wh.bus.vo.CarVo;
import com.wh.sys.utils.DataGridViewUtils;

/**
 * @author 万浩
 * @data 2019/12/2 19:44
 * @description
 */
public interface CarService {
    /**
     * 查询所有车辆
     * @param carVo
     * @return
     */
    public DataGridViewUtils queryAllCar(CarVo carVo);
    /**
     * 添加车辆
     * @param carVo
     */
    public void addCar(CarVo carVo);
    /**
     * 修改车辆
     * @param carVo
     */
    public void updateCar(CarVo carVo);
    /**
     * 根据id删除车辆
     * @param carnumber
     */
    public void deleteCar(String carnumber);
    /**
     * 批量删除车辆
     * @param carnumbers
     */
    public void deleteBatchCar(String [] carnumbers);

    /**
     * 根据车牌号查询
     * @param carnumber
     * @return
     */
    Car queryCarByCarNumber(String carnumber);
}
