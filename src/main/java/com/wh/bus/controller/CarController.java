package com.wh.bus.controller;

import com.wh.bus.entity.Car;
import com.wh.bus.service.CarService;
import com.wh.bus.vo.CarVo;
import com.wh.sys.constant.SysConstant;
import com.wh.sys.utils.AppFileUtils;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 汽车管理控制器
 * @author 万浩
 * @data 2019/12/2 19:47
 * @description
 */
@RequestMapping("/car")
@RestController
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 加载车辆列表返回DataGridView
     */
    @RequestMapping("/loadAllCar")
    public DataGridViewUtils loadAllCar(CarVo carVo) {
        return this.carService.queryAllCar(carVo);
    }

    /**
     * 添加车辆
     */
    @RequestMapping("/addCar")
    public ResultObj addCar(CarVo carVo) {
        try {
            carVo.setCreatetime(new Date());
            //如果不是默认图片,就去掉图片的_temp的后缀
            if(!carVo.getCarimg().equals(SysConstant.DEFAULT_CAR_IMG)) {
                String filePath= AppFileUtils.updateFileName(carVo.getCarimg(),SysConstant.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            this.carService.addCar(carVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改车辆
     */
    @RequestMapping("/updateCar")
    public ResultObj updateCar(CarVo carVo) {
        try {
            //对于修改时,可能传入带_temp后缀的图片,或者是默认图片,其他图片
            String carimg = carVo.getCarimg();
            //如果以_temp结尾,说明是临时文件
            if(carimg.endsWith(SysConstant.FILE_UPLOAD_TEMP)) {
                //去掉_temp后缀
                String filePath=AppFileUtils.updateFileName(carVo.getCarimg(),SysConstant.FILE_UPLOAD_TEMP);
                //设置为没有_temp的图片路径
                carVo.setCarimg(filePath);
                //把原来的图片路径删除,先找到这个车
                Car car=this.carService.queryCarByCarNumber(carVo.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());
            }
            //修改操作
            this.carService.updateCar(carVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除车辆
     */
    @RequestMapping("/deleteCar")
    public ResultObj deleteCar(CarVo carVo) {
        try {
            this.carService.deleteCar(carVo.getCarnumber());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除车辆
     */
    @RequestMapping("/deleteBatchCar")
    public ResultObj deleteBatchCar(CarVo carVo) {
        try {
            this.carService.deleteBatchCar(carVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
