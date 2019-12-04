package com.wh.sys.vo;

import com.wh.sys.entity.User;

import java.util.Arrays;


/**
 *
 *
 * @author 万浩
 * @data 2019/11/15 20:27
 * @description
 */
public class UserVo extends User {
    /**
     * 分页参数:页码,每页的记录数
     */
    private Integer page;
    private Integer limit;
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

    @Override
    public String toString() {
        return "UserVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", ids=" + Arrays.toString(ids) +
                '}';
    }
}
