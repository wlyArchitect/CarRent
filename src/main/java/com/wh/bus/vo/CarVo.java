package com.wh.bus.vo;

import com.wh.bus.entity.Car;

/**
 * @author 万浩
 * @data 2019/12/2 19:43
 * @description
 */
public class CarVo extends Car {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 接收多个id
     */
    private String[] ids;

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

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
