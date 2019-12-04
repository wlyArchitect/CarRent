package com.wh.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wh.bus.entity.Car;
import com.wh.bus.entity.Check;
import com.wh.bus.entity.Customer;
import com.wh.bus.entity.Rent;
import com.wh.bus.mapper.CarMapper;
import com.wh.bus.mapper.CheckMapper;
import com.wh.bus.mapper.CustomerMapper;
import com.wh.bus.mapper.RentMapper;
import com.wh.bus.service.CheckService;
import com.wh.bus.vo.CheckVo;
import com.wh.sys.constant.SysConstant;
import com.wh.sys.entity.User;
import com.wh.sys.utils.DataGridViewUtils;
import com.wh.sys.utils.RandomUtils;
import com.wh.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 万浩
 * @data 2019/12/4 15:17
 * @description
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CarMapper carMapper;

    /**
     * 据出租单号 加载检查单的表单数据初始化
     * @param rentid
     * @return
     */
    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租单信息
        Rent rent=this.rentMapper.selectByPrimaryKey(rentid);
        //通过出租单信息 查询客户
        Customer customer=this.customerMapper.selectByPrimaryKey(rent.getIdentity());
        //通过出租单信息 查询车辆
        Car car=this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        //组装Check
        Check check=new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstant.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user=(User) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());
        //添加Map信息
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("rent", rent);
        map.put("customer", customer);
        map.put("car", car);
        map.put("check", check);
        return map;
    }

    /**
     * 添加 出租单检查 信息
     * 并且设置bus_rent出租表已归还
     * bus_car汽车表未出租
     *
     * @param checkVo
     */
    @Override
    public void addCheck(CheckVo checkVo) {
        //插入bus_check表
        this.checkMapper.insertSelective(checkVo);
        //更改出租单bus_rent表的状态:已归还
        Rent rent=this.rentMapper.selectByPrimaryKey(checkVo.getRentid());
        rent.setRentflag(SysConstant.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(rent);
        //更改汽车的状态:未出租
        Car car=new Car();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(SysConstant.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 全查询 出租单检查信息
     * @param checkVo
     * @return
     */
    @Override
    public DataGridViewUtils queryAllCheck(CheckVo checkVo) {
        Page<Object> page= PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        List<Check> data = this.checkMapper.queryAllCheck(checkVo);
        return new DataGridViewUtils(page.getTotal(), data);
    }

    /**
     * 修改 出租单检查信息
     * @param checkVo
     */
    @Override
    public void updateCheck(CheckVo checkVo) {

    }
}
