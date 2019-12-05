package com.wh.stat.controller;

import com.wh.stat.domain.BaseEntity;
import com.wh.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计分析的控制器
 *
 * @author 万浩
 * @data 2019/12/4 18:44
 * @description
 */
@Controller
@RequestMapping("/stat")
public class StatisticsController {

    @Autowired
    private StatService statService;

    /**
     * todo 未作统计分析路由
     * 跳转到客户地区统计界面
     */
    @RequestMapping("/toCustomerAreaStat")
    public String toCustomerAreaStat(){
        return "stat/customerAreaStat";
    }
    /**
     * 查询 客户地区数据
     * @return
     */
    @RequestMapping("/loadCustomerAreaStatJson")
    @ResponseBody
    public List<BaseEntity> toLoadCustomerAreaStatJson(){
        return this.statService.loadCustomerAreaStatList();
    }


    /**
     * todo 未作统计分析路由
     * 跳转到业务员年度统计页面
     */
    @RequestMapping("/toOpernameYearGradeStat")
    public String toOpernameYearGradeStat() {
        return "stat/opernameYearGradeStat";
    }
    /**
     * 加载业务员年度统计数据
     */
    @RequestMapping("/loadOpernameYearGradeStat")
    @ResponseBody
    public Map<String,Object> opernameYearGradeStat(String year){
        List<BaseEntity> entities=this.statService.loadOpernameYearGradeStatList(year);
        Map<String,Object> map=new HashMap<>(12);
        List<String> names=new ArrayList<String>();
        List<Double> values=new ArrayList<Double>();
        for (BaseEntity baseEntity : entities) {
            names.add(baseEntity.getName());
            values.add(baseEntity.getValue());
        }
        map.put("name", names);
        map.put("value", values);
        return map;
    }


    /**
     * todo 未作统计分析路由
     * 跳转到公司年度统计页面
     */
    @RequestMapping("/toCompanyYearGradeStat")
    public String toCompanyYearGradeStat() {
        return "stat/companyYearGradeStat";
    }
    /**
     * 加载公司年度月份的统计数据
     */
    @RequestMapping("/loadCompanyYearGradeStat")
    @ResponseBody
    public List<Double> loadCompanyYearGradeStat(String year){
        List<Double> entities=this.statService.loadCompanyYearGradeStatList(year);
        //对数据进行判空处理,如果为空就显示为0
        for (int i = 0; i < entities.size(); i++) {
            if(null==entities.get(i)) {
                entities.set(i, 0.0);
            }
        }
        return entities;
    }

}
