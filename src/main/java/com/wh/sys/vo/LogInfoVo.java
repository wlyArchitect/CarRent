package com.wh.sys.vo;

import com.wh.sys.entity.LogInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 万浩
 * @data 2019/11/29 16:31
 * @description
 */
public class LogInfoVo extends LogInfo {
    /**
     * 分页参数:页码,每页的记录数
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
    /**
     * 接收多个用户id
     */
    private Integer[] ids;

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

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
