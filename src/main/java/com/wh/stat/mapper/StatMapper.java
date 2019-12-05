package com.wh.stat.mapper;

import com.wh.stat.domain.BaseEntity;

import java.util.List;

/**
 * 统计分析的dao层
 *
 * @author 万浩
 * @data 2019/12/4 19:15
 * @description
 */
public interface StatMapper {
    /**
     * 查询客户地区的数据
     * @return
     */
    List<BaseEntity> queryCustomerAreaStat();
    /**
     * 查询业务员年度数据
     * @return
     */
    List<BaseEntity> queryOpernameYearGradeStat(String year);
    /**
     * 加载公司年度统计数据
     * @param year
     * @return
     */
    List<Double> queryCompanyYearGradeStat(String year);

}