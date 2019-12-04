package com.wh.bus.vo;

import com.wh.bus.entity.Rent;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 没加批量删除
 * @author 万浩
 * @data 2019/12/4 9:25
 * @description
 */
public class RentVo extends Rent {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    /**
     * 时间,用于用户查询
     * 这个注解用于前台传入时间的格式转换
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
