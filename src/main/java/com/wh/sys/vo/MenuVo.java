package com.wh.sys.vo;

import com.wh.sys.entity.Menu;

/**
 * @author 万浩
 * @data 2019/11/16 20:47
 * @description
 */
public class MenuVo extends Menu {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

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
