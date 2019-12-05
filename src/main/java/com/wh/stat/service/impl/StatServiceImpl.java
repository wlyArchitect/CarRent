package com.wh.stat.service.impl;

import com.wh.stat.domain.BaseEntity;
import com.wh.stat.mapper.StatMapper;
import com.wh.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 统计分析数据的实现类
 *
 * @author 万浩
 * @data 2019/12/4 19:12
 * @description
 */
@Service
public class StatServiceImpl implements StatService {
    @Autowired
    private StatMapper statMapper;
    /**
     * 加载用户地区的统计数据
     * @return
     */
    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        return statMapper.queryCustomerAreaStat();
    }

    /**
     * 加载业务员的年度业绩统计数据
     * @param year
     * @return
     */
    @Override
    public List<BaseEntity> loadOpernameYearGradeStatList(String year) {
        return statMapper.queryOpernameYearGradeStat(year);
    }

    /**
     * 加载公司的月度月份业绩统计数据
     * @param year
     * @return
     */
    @Override
    public List<Double> loadCompanyYearGradeStatList(String year) {
        return statMapper.queryCompanyYearGradeStat(year);
    }
}
