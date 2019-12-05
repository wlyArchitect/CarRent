package com.wh.stat.service;

import com.wh.stat.domain.BaseEntity;

import java.util.List;

/**
 * 统计分析数据的实现层
 *
 * @author 万浩
 * @data 2019/12/4 19:12
 * @description
 */
public interface StatService {
    /**
     * 加载用户地区的统计数据
     * @return
     */
    List<BaseEntity> loadCustomerAreaStatList();

    /**
     * 加载业务员的年度业绩统计数据
     * @param year
     * @return
     */
    List<BaseEntity> loadOpernameYearGradeStatList(String year);

    /**
     * 加载公司的年度月份业绩统计数据
     * @param year
     * @return
     */
    List<Double> loadCompanyYearGradeStatList(String year);

}
